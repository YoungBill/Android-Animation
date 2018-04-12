package com.android.animation;

/**
 * Created by baina on 18-4-11.
 */

public class Person {

    private String name;
    private int age = 10;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "{姓名:" + name + ",年龄:" + age + "}";
    }
}
