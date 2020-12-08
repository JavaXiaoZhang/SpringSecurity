package com.zq.datasource;

public class DynamicDataSourceContextHolder {
    private static final ThreadLocal<String> DATASOURCE_HOLDER = new ThreadLocal<>();

    public static void setDataSourceType(String dataSourceType) {
        System.out.println("切换到" + dataSourceType + "数据源");
        DATASOURCE_HOLDER.set(dataSourceType);
    }

    public static String getDataSourceType() {
        System.out.println("获取数据源:" + DATASOURCE_HOLDER.get());
        return DATASOURCE_HOLDER.get();
    }

    public static void removeDataSource() {
        DATASOURCE_HOLDER.remove();
    }
}
