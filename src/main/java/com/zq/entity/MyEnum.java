package com.zq.entity;

public enum MyEnum {
    A,B;

    private String a;

    public String getA() {
        return a;
    }

    MyEnum() {
    }

    MyEnum(String a) {
        this.a = a;
    }
}
