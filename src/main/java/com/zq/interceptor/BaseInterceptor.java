package com.zq.interceptor;

import com.zq.entity.Page;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.plugin.Interceptor;


public abstract class BaseInterceptor implements Interceptor {

    protected static final String PAGE = "page";

    protected static final String DELEGATE = "delegate";

    protected static final String MAPPED_STATEMENT = "mappedStatement";

    protected Log log = LogFactory.getLog(this.getClass());

    @SuppressWarnings("unchecked")
    protected static Page<Object> convertParameter(Object parameterObject) {
        try {
            if (parameterObject instanceof Page) {
                return (Page<Object>) parameterObject;
            } else {
                //向上查找属性
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}
