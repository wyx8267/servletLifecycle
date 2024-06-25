package com.jirengu.hotel;

public class DatabaseConnection {
    public void connect(){
        System.out.println("连接数据库");
    }

    public void write(){
        System.out.println("写数据库");
    }

    public void close(){
        System.out.println("断开数据库");
    }
}
