package ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJF extends JFrame implements KeyListener {
    int[][]a=new int[4][4];//创建二维数组放到成员变量的位置，因为有两个方法都要用到
    public GameJF(){
        initJF();//ctrl+alt+m抽取方法，方便管理

        initMenuBar();

        //初始化数据（打乱图片）
        initdata();

        //初始化图片
        initimg();

        this.setVisible(true);//让界面显示出来，一般写在最后
    }//抽取方法之后非常简单

    private void initdata() {
        int[] arr={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        Random random=new Random();
        for (int i = 0; i < arr.length; i++) {
            int index=random.nextInt(16);
            int temp=arr[index];
            arr[index]=arr[i];
            arr[i]=temp;

        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                a[i][j]=arr[i*4+j];

            }

        }
    }


    private void initimg() {

        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                int number=a[i][j];
                //创建一个图片ImageIcon的对象
                ImageIcon icon = new ImageIcon("image\\animal\\animal2\\"+number+".jpg");
                //创建一个JLabel的对象（管理容器）
                JLabel label =new JLabel(icon);
                //指定图像位置
                label.setBounds(100*i+95,100*j+125,100,100);
                //把管理容器添加到界面中
                //this.add(label);//this指的是当前界面

                label.setBorder(new BevelBorder(0));

                this.getContentPane().add(label);//先获取隐藏图片再
            }

        }

        ImageIcon icon2=new ImageIcon("image\\background.png");
        JLabel label2=new JLabel(icon2);
        label2.setBounds(40,25,508,560);
        this.getContentPane().add(label2);



    }



    private void initMenuBar() {
        //初始化菜单
        //创建整个菜单对象
        JMenuBar menuBar = new JMenuBar();

        //创建菜单上面的两个选项的对象
        JMenu function = new JMenu("菜单");
        JMenu aboutus = new JMenu("关于我们");

        //创建选项下面的条目
        JMenuItem replay=new JMenuItem("重新游戏");
        JMenuItem relogin=new JMenuItem("重新登陆");
        JMenuItem closegame=new JMenuItem("关闭游戏");

        JMenuItem jionqq=new JMenuItem("加入QQ群");

        //将每一个选项下面的条目添加到选项中
        function.add(replay);
        function.add(relogin);
        function.add(closegame);
        aboutus.add(jionqq);
        menuBar.add(function);
        menuBar.add(aboutus);
        this.setJMenuBar(menuBar);//给整个界面设置菜单
    }

    private void initJF() {
        this.setSize(600,650);
        //设置界面标题
        this.setTitle("拼图游戏v1.0");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//只要关掉其中一个界面虚拟机就会结束

        //取消默认的居中放置，只有取消了才会按照xy轴的形式添加组件
        this.setLayout(null);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}