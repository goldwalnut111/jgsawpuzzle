package ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJF extends JFrame implements KeyListener, ActionListener {
    //胜利
    int win[][]=new int[][]{
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,0},
    };

    int step;

    String path="image\\animal\\animal2\\";
    int x,y;//记录空白图片的位置
    int m,n;//记录坐标
    int[][]a=new int[4][4];//创建二维数组放到成员变量的位置，因为有两个方法都要用到
    public GameJF(){
        initJF();//ctrl+alt+m抽取方法，方便管理

        initMenuBar();

        //初始化数据（打乱图片）
        initdata();

        //初始化图片
        initimg();

        //给整个界面添加键盘监听事件
        this.addKeyListener(this);

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

        for (n = 0; n < 4; n++) {
            for (m = 0; m < 4; m++) {
                a[n][m]=arr[n *4+ m];


                if(a[n][m]==0){
                    x= m;
                    y= n;

                }
            }

        }
    }


    private void initimg() {


        //删除已经出现的所有图片，此为第一步，不然所有出现在上面的方法都不会显示，因为刚显示就被删除了
        this.getContentPane().removeAll();

        //记步数
        JLabel label=new JLabel("步数:"+step);
        label.setBounds(30,30,100,20);
        this.getContentPane().add(label);

        //胜利
        if(victory()){
            JLabel label2=new JLabel(new ImageIcon("image\\win.png"));
            label2.setBounds(200,300,197,73);
            this.getContentPane().add(label2);
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int number=a[j][i];
                //创建一个图片ImageIcon的对象
                ImageIcon icon = new ImageIcon(path+number+".jpg");
                //创建一个JLabel的对象（管理容器）
                JLabel label3 =new JLabel(icon);
                //指定图像位置
                label3.setBounds(100* i +95,100* j +125,100,100);
                //把管理容器添加到界面中
                //this.add(label);//this指的是当前界面

                label3.setBorder(new BevelBorder(0));

                this.getContentPane().add(label3);//先获取隐藏图片再
            }

        }

        ImageIcon icon2=new ImageIcon("image\\background.png");
        JLabel label2=new JLabel(icon2);
        label2.setBounds(40,25,508,560);
        this.getContentPane().add(label2);


        //刷新界面
        this.getContentPane().repaint();

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
        //按住a不松会显示完整图片
        int code=e.getKeyCode();
        if(code==65){

            //删除所有图片
            this.getContentPane().removeAll();
            //添加完整图片
            JLabel all=new JLabel(new ImageIcon(path+"all.jpg"));
            all.setBounds(95,125,400,400);
            this.getContentPane().add(all);

            //添加背景图片
            ImageIcon icon2=new ImageIcon("image\\background.png");
            JLabel label2=new JLabel(icon2);
            label2.setBounds(40,25,508,560);
            this.getContentPane().add(label2);

            //刷新界面
            this.getContentPane().repaint();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        //胜利了就不能再移动了
        if(victory()) return;

        int code=e.getKeyCode();
        if(code==37){
            if(x==3) return;
            int temp=a[y][x];
            a[y][x]=a[y][x+1];
            a[y][x+1]=temp;
            x++;
            step++;
            System.out.println("向左移动");
        }
        if(code==38){
            if(y==3) return;
            int temp=a[y][x];
            a[y][x]=a[y+1][x];
            a[y+1][x]=temp;
            y++;
            step++;
            System.out.println("向下移动");
        }
        if(code==39){
            if(x==0) return;
            int temp=a[y][x];
            a[y][x]=a[y][x-1];
            a[y][x-1]=temp;
            x--;
            step++;
            System.out.println("向右移动");
        }
        if(code==40){
            if(y==0) return;
            int temp=a[y][x];
            a[y][x]=a[y-1][x];
            a[y-1][x]=temp;
            y--;
            step++;
            System.out.println("向上移动");
        }
        if(code==65){
            initimg();
        }
        if(code==87){
            x=3;
            y=3;
            a=new int[][]{
                    {1,2,3,4},
                    {5,6,7,8},
                    {9,10,11,12},
                    {13,14,15,0},
            };
            //Java 二维数组 a[第一][第二]，第一个索引是行（y方向），第二个索引是列（x方向）。因为这一点搞错,理清楚数组的横方向和竖方向
            initimg();
        }



        //刷新页面
        initimg();
    }

    public boolean victory(){
        //判断胜利
        for (int i = 0; i < win.length; i++) {
            for (int j = 0; j < win[i].length; j++) {
                if (win[i][j] != a[i][j])
                    return false;
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}