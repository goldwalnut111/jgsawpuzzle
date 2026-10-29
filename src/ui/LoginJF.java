package ui;

import user.User;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class LoginJF extends JFrame implements MouseListener {//构造方法就是在创建对象的时候进行初始化的
    //创建一个集合储存正确的用户名和密码
    static ArrayList<User> list = new ArrayList<>();
    static {
        list.add(new User("zhangsan","123"));
        list.add(new User("lisi","1234"));
    }


    //登录
    JButton login=new JButton();
    //注册
    JButton register = new JButton();
    //验证码
    JTextField code1 =new JTextField();


    //登录框
    JTextField username1=new JTextField();
    JPasswordField password1=new JPasswordField();



    public LoginJF(){
        //初始化界面
        initJF();

        //在这个界面中添加内容
        initview();

        this.setVisible(true);//让显示显示出来，一般写在最后
    }

    public void initview() {
        //添加用户名
        JLabel username=new JLabel(new ImageIcon("image\\login\\用户名.png"));
        username.setBounds(116,135,47,17);
        this.getContentPane().add(username);
        //添加用户名输入框

        username1.setBounds(195,134,200,30);
        this.getContentPane().add(username1);
        //添加密码文字
        JLabel password=new JLabel(new ImageIcon("image\\login\\密码.png"));
        password.setBounds(130,195,32,16);
        this.getContentPane().add(password);
        //添加密码框

        password1.setBounds(195,195,200,30);
        this.getContentPane().add(password1);
        //验证码,暂且当个摆设
        JLabel code =new JLabel(new ImageIcon("image\\login\\验证码.png"));
        code.setBounds(133,256,50,30);
        this.getContentPane().add(code);

        code1.setBounds(195,256,100,30);
        this.getContentPane().add(code1);

        //登录按钮

        login.setBounds(123, 310, 128, 47);
        login.setIcon(new ImageIcon("image\\login\\登录按钮.png"));
        //去除按钮的默认边框
        login.setBorderPainted(false);
        //去除按钮的默认背景
        login.setContentAreaFilled(false);
        this.getContentPane().add(login);
        login.addMouseListener(this);

        //6.添加注册按钮

        register.setBounds(256, 310, 128, 47);
        register.setIcon(new ImageIcon("image\\login\\注册按钮.png"));
        //去除按钮的默认边框
        register.setBorderPainted(false);
        //去除按钮的默认背景
        register.setContentAreaFilled(false);
        this.getContentPane().add(register);
        register.addMouseListener(this);

        //7.添加背景图片
        JLabel background = new JLabel(new ImageIcon("image\\login\\background.png"));
        background.setBounds(0, 0, 470, 390);
        this.getContentPane().add(background);


    }

    public void initJF() {
        //在创建登陆页面的时候同时给这个界面设置一些信息
        //例如宽高直接展示，写在构造方法中，在main方法里面创建对象的时候就自动设置宽高展示
        this.setSize(550,450);
        //设置界面标题
        this.setTitle("拼图游戏登录");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//只要关掉其中一个界面虚拟机就会结束
    }

    //要展示用户名或密码错误
    public void showJDialog(String content) {
        //创建一个弹框对象
        JDialog jDialog = new JDialog();
        //给弹框设置大小
        jDialog.setSize(200, 150);
        //让弹框置顶
        jDialog.setAlwaysOnTop(true);
        //让弹框居中
        jDialog.setLocationRelativeTo(null);
        //弹框不关闭永远无法操作下面的界面
        jDialog.setModal(true);

        //创建Jlabel对象管理文字并添加到弹框当中
        JLabel warning = new JLabel(content);
        warning.setBounds(0, 0, 200, 150);
        jDialog.getContentPane().add(warning);

        //让弹框展示出来
        jDialog.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        Object source = e.getSource();
        if(source==login){
            login.setIcon(new ImageIcon("image\\login\\登录按下.png"));
        }
        if(source==register){
            register.setIcon(new ImageIcon("image\\login\\注册按下.png"));
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Object source = e.getSource();
        String name= username1.getText();//注意这里提取输入框的文字函数
        String password=new String(password1.getPassword());//这里都是输入的信息

        if(source==login){
            if (name.isEmpty() || password.isEmpty()) {   // 先做廉价校验
                showJDialog("用户名和密码不能为空");
                return;}

            for(User u:list){//创建集合元素u，循环将集合元素逐个与输入相比
                if(u.getUsername().equals(name)&&u.getPassword().equals(password)){
                    this.setVisible(false);
                    this.showJDialog("登陆成功！");
                    new GameJF();
                    return;
                }
                this.showJDialog("用户名或密码错误");
            }

        }


    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
