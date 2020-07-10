package com.zq.ioc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

public class ConnectionDynamicProxy implements InvocationHandler {

    private Connection connection;

    public ConnectionDynamicProxy(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Connection.class.isAssignableFrom(proxy.getClass())&&"close".equals(method.getName())){
            DataSource.getInstance().recoveryConnection(connection);
            return null;
        }else {
            return method.invoke(connection,args);
        }
    }

    public Connection getConnectionProxy(){
        return (Connection) Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{Connection.class}, this);
    }
}
