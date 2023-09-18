package com.lit.gui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public  class GameJFrame extends JFrame implements KeyListener, ActionListener {
    // JFrame 界面 窗体
    // 子类也是表示一个界面 窗体
    //  GameJFrame 这个界面表示的就是游戏的主界面
    // 以后跟游戏有关的的所有哦逻辑都写在这个类中

    // 创建一个二维数组
    // 目的： 用来管理数据
    // 加载图片得时候，会根据二位数组中得数据进行加载
    int[][] data = new int[4][4];


    // 定义x y 表示空白位置的索引
    int x = 0, y = 0;

    // 定义一个变量，记录当前展示图片的路劲】
    String path = "C:\\Users\\ywd\\Desktop\\java\\game'\\image\\animal\\animal3\\";

    // 定义一个二维数组，存储正确的数据
    int[][] win = {
        {1,2,3,4},
        {5,6,7,8},
        {9,10,11,12},
        {13,14,15,0}
    };

    // 定义变量用来统计步数
    int step = 0;

    // 创建选项下面的条目对象
    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reLoginItem = new JMenuItem("重新登录");
    JMenuItem closeItem = new JMenuItem("关闭游戏");


    JMenuItem accountItem = new JMenuItem("公众号");

    JMenuItem animalItem = new JMenuItem("动物");
    JMenuItem girlItem   = new JMenuItem("美女");
    JMenuItem sportItem  = new JMenuItem("运动");


    public GameJFrame (){
        //  初始化界面
        initFrame();


        // 初始化菜单
        initJMenuBar();


        // 初始化数据  （打乱）
        initData();


        // 初始化图片
        initImage();


        // 显示  建议写在最后
        this.setVisible(true);
    }

    private void initData() {
        // 1.定义一个一维数组
        int[] temperArr = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        // 2.打乱数组中得数据顺序
        // 遍历数组，拿到每一个元素，拿着每一个元素跟随机索引上得数据进行交换
        Random r = new Random();
        for (int i = 0; i < temperArr.length; i++) {
            // 获取随机索引
            int index =  r.nextInt(temperArr.length);
            // 拿着遍历到得每一个数据，跟随机索引上得数据进行交换
            int temp = temperArr[i];
            temperArr[i] = temperArr[index];
            temperArr[index] = temp;
        }

        // 3. 给二位数组添加数据
        // 遍历一维数组temperArr得到每一个元素，把每一个元素一次添加到二维数组当中
        for (int i = 0; i < temperArr.length; i++) {
            if(temperArr[i]  == 0){
                x = i / 4;
                y = i % 4;
            }
            data[i / 4][i % 4] = temperArr[i];
        }
    }

    private void initImage() {
        // 清空原本已经出现的所有图片
        this.getContentPane().removeAll();


        if(victory()){
            //显示胜利的图标
            JLabel winJLabel = new JLabel(new ImageIcon("C:\\Users\\ywd\\Desktop\\java\\game'\\image\\win.png"));
            winJLabel.setBounds(203,283,197,73);
            this.getContentPane().add(winJLabel);
        }


        JLabel stepCount = new JLabel("步数：" + step);
        stepCount.setBounds(50,50,100,20);
        this.getContentPane().add(stepCount);


        // 外循环 --- 把内循环重复四次
        for(int i = 0; i < 4 ; i++){
            // 内循环 --- 表示在一行加添4张图片
            for(int j = 0; j < 4 ; j++){
                // 创建一个图片ImageIcon的对象
                // 创建一个JLable的对象
                JLabel jLabel = new JLabel(new ImageIcon(path + data[i][j] +".jpg"));
                // 指定图片位置
                jLabel.setBounds(105 * j + 83,105 * i + 134,105,105);
                //给图片添加边框
                jLabel.setBorder(new BevelBorder(0));
                //把管理容器添加到界面中
                this.getContentPane().add(jLabel);

            }
        }


        // 添加背景图片
        JLabel background = new JLabel(new ImageIcon("C:\\Users\\ywd\\Desktop\\java\\game'\\image\\background.png"));
        background.setBounds(40,40,508,560);
        // 把背景图片添加到界面当中
        this.getContentPane().add(background);


        // 刷新一下界面
        this.getContentPane().repaint();


    }

    private void initJMenuBar() {
        // 创造整个的菜单对象
        JMenuBar jmenuBar = new JMenuBar();


        // 创建菜单上面的两个选项的对象 （功能 关于我们）
        JMenu functionJMenu = new JMenu("菜单");
        JMenu aboutJMenu = new JMenu("关于我们");


        // 创建更换图片的jmenu对象
        JMenu changeJMenu = new JMenu("更换图片");
        // 将更换图片放入功能里
        functionJMenu.add(changeJMenu);
        // 将三个选项放进 更换图片的 JMenu 里
        changeJMenu.add(animalItem);
        changeJMenu.add(girlItem);
        changeJMenu.add(sportItem);


        // 给条目绑定事件
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);

        animalItem.addActionListener(this);
        girlItem.addActionListener(this);
        sportItem.addActionListener(this);


        // 将菜单里面的两个选项添加到菜单当中
        jmenuBar.add(functionJMenu);
        jmenuBar.add(aboutJMenu);


        // 将每个选项下面的台哦木添加到选项之中
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);


        aboutJMenu.add(accountItem);

        // 给整个界面设置菜单
        this.setJMenuBar(jmenuBar);


    }

    private void initFrame() {
        // 设置界面的宽高
        this.setSize(603,680);
        // 设置界面的标题
        this.setTitle("拼图游戏单机版 v.10");
        // 设置界面置顶
        this.setAlwaysOnTop(true);
        // 设置界面居中
        this.setLocationRelativeTo(this);
        // 设置关闭模式
        this.setDefaultCloseOperation(3);

        // 取消默认的剧中防止，只有取消了才会按照xy轴的形式添加组件
        this.setLayout(null);

        // 给整个界面添加键盘监听事件
        this.addKeyListener(this);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        // 如果按的是A
        if(keyCode == 65){
            // 删除界面所有的图片
            this.getContentPane().removeAll();
            // 加载第一张完整的图片
            JLabel all = new JLabel(new ImageIcon( path+ "all.jpg"));
            all.setBounds(83,134,420,420);
            this.getContentPane().add(all);
            // 加载背景图片
            // 添加背景图片
            JLabel background = new JLabel(new ImageIcon("C:\\Users\\ywd\\Desktop\\java\\game'\\image\\background.png"));
            background.setBounds(40,40,508,560);
            // 把背景图片添加到界面当中
            this.getContentPane().add(background);
            // 刷新界面
            this.getContentPane().repaint();

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // 判断游戏是否胜利，如果胜利，此方法需要直接结束，不能再执行下面的移动代码了
        if(victory()){
            return ;
        }



        // 对上、下、左、右进行判断
        // 左：37 上: 38 右：39 下：40
        int keyCode = e.getKeyCode();

       if(keyCode == 37){
           // 逻辑：
           // 把空白方块 右边的往左移动


           if(y == 3){
               // 表示空白方块已经在最右边了，他的右面没有图片移动了
               return ;
           }
           data[x][y] = data[x][y + 1];
           data[x][y + 1] = 0;
           y++;
           //移动一次 step自增一次
           step++;
           // 调用方法按照最新的数字 加载图片
           initImage();
       }else if(keyCode == 38){
           if(x == 3){
               // 表示空白方块已经在最下边了，他的下面没有图片移动了
               return ;
           }
           // 逻辑：
           // 把空白方块下方的数字往上移动
           // x,y表示空白方块的下标
           // x+1 , y 表示空白方块下面的方块

           // 把空白方块下方的数字赋值给空白方块
           data[x][y] = data[x + 1][y];
           data[x + 1][y] = 0;
           x++;
           //移动一次 step自增一次
           step++;
           // 调用方法按照最新的数字 加载图片
           initImage();
       }else if(keyCode == 39){
           if(y == 0){
               // 表示空白方块已经在最左边了，他的左面没有图片移动了
               return ;
           }
           // 逻辑：
           // 把空白方块 左边的往右移动
           data[x][y] = data[x][y - 1];
           data[x][y - 1] = 0;
           y--;
           //移动一次 step自增一次
           step++;
           // 调用方法按照最新的数字 加载图片
           initImage();
       }else if(keyCode == 40){
           if(x == 0){
               // 表示空白方块已经在最上边了，他的上面没有图片移动了
               return ;
           }

           data[x][y] = data[x - 1][y];
           data[x - 1][y] = 0;
           x--;
           //移动一次 step自增一次
           step++;
           // 调用方法按照最新的数字 加载图片
           initImage();
       }else if(keyCode == 65){
           initImage();
        }else if(keyCode == 87){
           data = new int[][]{
                   {1,2,3,4},
                   {5,6,7,8},
                   {9,10,11,12},
                   {13,14,15,0}
           };
           initImage();
       }
    }

    // 判断data数组中的数据是否跟win数组相同
    // 如果全部相同， 返回true 否则返回flase
    public boolean victory(){
        for (int i = 0; i < data.length; i++) {
            // i：依次表示二维数组 data里面的索引
            // data[i]： 依次表示每一个一维数组
            for (int j = 0; j < data[i].length; j++) {
                if(data[i][j] != win[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 获取当前被点击的条目对象
        Object obj = e.getSource();

        // 判断
        if(obj == replayItem) {
            // 计步器清零
            step = 0;

            // 再次打乱二位数组的数据
            initData();

            // 重新加载图片
            initImage();
        }else if(obj == reLoginItem){
            // 关闭当前游戏界面
            this.setVisible(false);
            // 打开登录界面
            new LoginJFrame();
            // 获取
        }else if(obj == accountItem){
            // 创建一个弹框对象
            JDialog jDialog = new JDialog();
            // 创建一个管理图片的容器对象 JLable
            JLabel jLabel = new JLabel(new ImageIcon("C:\\Users\\ywd\\Desktop\\java\\game'\\image\\damie.jpg"));
            // 设置位置和宽高
            jLabel.setBounds(0,0,258,258);
            // 把图片添加到弹框中
            jDialog.getContentPane().add(jLabel);
            // 给弹框设置大小
            jDialog.setSize(344,344);
            // 让弹框指定
            jDialog.setAlwaysOnTop(true);
            // 弹框剧中
            jDialog.setLocationRelativeTo(null);
            // 弹框不关闭则无法操作下面的界面
            jDialog.setModal(true);
            // 显示弹框
            jDialog.setVisible(true);
        }else if(obj == closeItem){
            // 关闭游戏
            System.exit(0);
        }else if(obj == animalItem){
            // 随机获取1-8
            Random r = new Random();
            int[] animalArr = {1,2,3,4,5,6,7,8};
            int animalIndex = r.nextInt(8);
            int animalPath = animalArr[animalIndex];
//            System.out.println(animalpath);
            String animal = "animal\\animal" + animalPath + "\\";
            // 修改图片路劲
            path = "C:\\Users\\ywd\\Desktop\\java\\game'\\image\\" + animal;

            // 初始化界面
            initImage();
        }else if(obj == girlItem){
            // 随机获取1-13
            Random r = new Random();
            int[] girlArr = {1,2,3,4,5,6,7,8,9,10,11,12,13};
            int girlIndex = r.nextInt(13);
            int girlPath = girlArr[girlIndex];

            String girl = "girl\\girl" + girlPath + "\\";
            // 修改图片路劲
            path = "C:\\Users\\ywd\\Desktop\\java\\game'\\image\\" + girl;

            // 初始化界面
            initImage();
        }else if(obj == sportItem){
            // 随机获取1-10
            Random r = new Random();
            int[] sportArr = {1,2,3,4,5,6,7,8,9,10};
            int sportIndex = r.nextInt(10);
            int sportPath = sportArr[sportIndex];


            String sport = "sport\\sport" + sportPath + "\\";
            // 修改图片路劲
            path = "C:\\Users\\ywd\\Desktop\\java\\game'\\image\\" + sport;

            // 初始化界面
            initImage();
        }
    }
}
