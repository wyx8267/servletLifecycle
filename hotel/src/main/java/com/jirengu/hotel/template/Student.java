package com.jirengu.hotel.template;

public class Student extends People {

    @Override
    public void qichuang(){
        System.out.println("起床：豹豹猫猫叫醒");
    }

    @Override
    public void quxuexiao(){
        // 默认实现
        System.out.println("去学校：豹豹猫猫开车送去学校");
    }

    @Override
    public void shangke(){
        System.out.println("上课：学习知识");
    }
}
