package com.zq.util;

import com.zq.entity.Page;

public class PageUtils {
    private static final ThreadLocal<Page> LOCAL_PAGE = new ThreadLocal<Page>();

    public static void setPageParams(int offset, int limit){
        LOCAL_PAGE.set(new Page(offset,limit));
    }

    public static Page getPageParams(){
        return LOCAL_PAGE.get();
    }

    public static void remove(){
        LOCAL_PAGE.remove();
    }
}
