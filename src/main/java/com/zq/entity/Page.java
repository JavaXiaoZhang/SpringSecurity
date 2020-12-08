package com.zq.entity;

import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.annotation.PostConstruct;
import java.util.LinkedList;

@Component
public class Page {
    int offset;
    int limit;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public Page(int offset, int limit) {
        this.offset = offset;
        this.limit = limit;
    }

    public Page() {
        System.out.println("construct...");
    }
    @PostConstruct
    public void cons(){
        System.out.println("post...");
    }
}
