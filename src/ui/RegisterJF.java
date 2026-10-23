package ui;

import javax.swing.*;

public class RegisterJF extends JFrame {//JFrame是Java自带的标准窗口类，本身剧本很多功能例如放大缩小关闭等
    public RegisterJF(){
        this.setSize(400,500);
        //设置界面标题
        this.setTitle("拼图游戏注册");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//只要关掉其中一个界面虚拟机就会结束
        this.setVisible(true);//让显示显示出来，一般写在最后
    }
}
