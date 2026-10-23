package ui;

import javax.swing.*;
import java.awt.*;

public class LoginJF extends JFrame {//构造方法就是在创建对象的时候进行初始化的
    public LoginJF(){
        //在创建登陆页面的时候同时给这个界面设置一些信息
        //例如宽高直接展示，写在构造方法中，在main方法里面创建对象的时候就自动设置宽高展示
        this.setSize(400,500);
        //设置界面标题
        this.setTitle("拼图游戏登录");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//只要关掉其中一个界面虚拟机就会结束
        this.setVisible(true);//让显示显示出来，一般写在最后
    }
}
