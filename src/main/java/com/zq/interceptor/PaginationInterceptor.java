package com.zq.interceptor;

import com.alibaba.fastjson.JSON;
import com.zq.entity.Page;
import com.zq.util.PageUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Optional;

@Intercepts({@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class,
        RowBounds.class, ResultHandler.class})})
@Component
public class PaginationInterceptor implements Interceptor {
    Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        final MappedStatement mappedStatement = (MappedStatement) args[0];
        String id = mappedStatement.getId();
        //com.zq.mapper.UserMapper.getByUsername
        id = id.substring(id.lastIndexOf(".")+1);
        //getByUsername
        log.info("拦截sql:[{}]",id);
        Method method = invocation.getMethod();
        log.info("sql操作类型[{}]",method.getName());
        log.info("sql返回值类型[{}]",method.getReturnType().getName());

        Object parameter = args[1];
        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        Object parameterObject = boundSql.getParameterObject();
        String sql = boundSql.getSql().replaceAll("[\\s]", " ");
        if (!"zhang".equals(parameterObject)){
            sql = new StringBuilder(sql).append(" limit 0,10").toString();
        }
        //修改sql
        BoundSql newSql = new BoundSql(mappedStatement.getConfiguration(), sql, boundSql.getParameterMappings(), parameterObject);

        //重新构建MappedStatement
        MappedStatement build = getMappedStatement(mappedStatement, newSql);

        args[0] = build;
        // 重置 RowBound
        args[2] = new RowBounds(RowBounds.NO_ROW_OFFSET, RowBounds.NO_ROW_LIMIT);

        log.info("执行sql：[{}]   参数：[{}]",sql,JSON.toJSONString(parameterObject));
        long start = System.currentTimeMillis();
        Object proceed = invocation.proceed();
        log.info("执行时间：[{}]",System.currentTimeMillis()-start);
        log.info("执行结果：[{}]",JSON.toJSONString(proceed));
        return proceed;
    }

    private MappedStatement getMappedStatement(MappedStatement mappedStatement, BoundSql newSql) throws NoSuchFieldException, IllegalAccessException {
        /*Field declaredField = newSql.getClass().getDeclaredField("metaParameters");
        declaredField.setAccessible(true);
        MetaObject metaObject = (MetaObject)declaredField.get(newSql);
        declaredField.set(newSql,metaObject);*/
        MappedStatement.Builder builder = new MappedStatement.Builder(mappedStatement.getConfiguration(), mappedStatement.getId(), new BoundSqlSource(newSql), mappedStatement.getSqlCommandType());
        builder.keyColumn(delimitedArrayToString(mappedStatement.getKeyColumns()));
        builder.keyGenerator(mappedStatement.getKeyGenerator());
        String[] keyProperties = mappedStatement.getKeyProperties();
        builder.keyProperty(keyProperties==null?"":String.join(",",keyProperties));
        builder.resource(mappedStatement.getResource());
        builder.fetchSize(mappedStatement.getFetchSize());
        builder.statementType(mappedStatement.getStatementType());
        builder.timeout(mappedStatement.getTimeout());
        builder.resultSets(delimitedArrayToString(mappedStatement.getResultSets()));
        builder.parameterMap(mappedStatement.getParameterMap());
        builder.resultMaps(mappedStatement.getResultMaps());
        builder.cache(mappedStatement.getCache());
        builder.useCache(mappedStatement.isUseCache());
        return builder.build();
    }

    private static class BoundSqlSource implements SqlSource {
        BoundSql boundSql;
        public BoundSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }

        @Override
        public BoundSql getBoundSql(Object parameterObject) {
            return this.boundSql;
        }
    }

    public String delimitedArrayToString(String[] array) {

        if (array == null || array.length == 0) {
            return "";
        }
        return String.join(",",array);
    }
}
