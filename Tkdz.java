package tkdz;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class Tkdz extends JFrame implements ActionListener{//游戏主函数类
	MyPanel mp=null;//定义面板
	GuankaPanel gkmp=null;//定义关卡面板
	JMenuBar cdt=null;//定义菜单栏
	JMenu cd1=null;//定义菜单
	JMenuItem cdx1=null;//定义菜单按钮
	JMenuItem cdx2=null;//定义菜单按钮
	JMenuItem cdx3=null;//定义菜单按钮
	JMenuItem cdx4=null;//定义菜单按钮
	JMenuItem cdx5=null;//定义菜单按钮
	
	public static void main(String[] args) //主函数，开始游戏
	{
		Tkdz tkdz=new Tkdz();
	}
	public Tkdz() {//主体函数（只被调用）
		cdt=new JMenuBar();//菜单栏
		cd1=new JMenu("游戏(G)");//菜单
		cd1.setMnemonic('G');//配置热键
		cdx1=new JMenuItem("新游戏(N)");//新游戏条目
		cdx1.addActionListener(this);//监听事件(之后重写)
		cdx1.setActionCommand("new");
		cd1.add(cdx1);
		cdx2=new JMenuItem("继续游戏(C)");//继续游戏条目
		cdx2.addActionListener(this);
		cdx2.setActionCommand("continue");
		cd1.add(cdx2);
		cdx4=new JMenuItem("存盘退出(S)");//保存条目
		cdx4.addActionListener(this);
		cdx4.setActionCommand("saveandexit");//动作标签
		cd1.add(cdx4);
		cdx5=new JMenuItem("退出(E)");//退出条目
		cdx5.addActionListener(this);
		cdx5.setActionCommand("exit");
		cd1.add(cdx5);
		cdt.add(cd1);

		cdx2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){//操作后监听事件
				
				try {
					continueGame();
				} catch (Exception e1) {
					// TODO 自动生成的 catch 块
					
				}
				
				
				
			}
		});
		cdx5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//操作后监听事件
				
				System.exit(0);
			}
		});
		cdx4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//操作后监听事件（重写）
				try {
					exitSave();
				} catch (Exception e1) {//退出失败后动作
					
					e1.printStackTrace();
					System.out.println("error!");//在控制台中弹出
				}
				System.exit(0);
			}
		});
		
		
		
		
		
		gkmp=new GuankaPanel();//关卡面板
		Thread t=new Thread(gkmp);//定义一个关卡面板线程
		t.start();//线程停掉，字不闪烁
		this.setJMenuBar(cdt);//放置工具条
		this.add(gkmp);

		this.setTitle("信管1902孟令哲");
		this.setIconImage((new ImageIcon("img/tank.jpg")).getImage());
		this.setSize(600,500);//界面放大
		this.setLocation(300,200);
		this.setResizable(false);//增加这行，界面不可缩放
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

	}
	
	
	public void exitSave() throws Exception{//保存方法
		File fileLife = new File("D:/desktop/relife.txt");
		File fileKill = new File("D:/desktop/kill.txt");//新建文件变量
		FileOutputStream fosLife = new FileOutputStream(fileLife);//打开文件流
		FileOutputStream fosKill = new FileOutputStream(fileKill);
		String strLife,strKill;//存储变量
		System.out.println(Record.getRelife()+Record.getSum());//记录数目，打印在控制台，方便调试
		strLife = String.valueOf(Record.getRelife());//转型
		strKill = String.valueOf(Record.getSum());
		fosLife.write(strLife.getBytes());//写入文件
		fosKill.write(strKill.getBytes());
		fosLife.close();//关闭文件流
		fosKill.close();
	}
	public void continueGame() throws Exception{//继续方法
		if(gkmp!=null) {this.remove(gkmp); }  //删除关卡面板
		if(mp!=null) {this.remove(mp); }  //删除mp的线程
		mp=new MyPanel("new");//新建mp面板
		this.add(mp); 
		FileReader fileLife = new FileReader("D:/desktop/relife.txt");//打开文件流
		FileReader fileKill = new FileReader("D:/desktop/kill.txt");
		
		int life = fileLife.read();
		int kill = fileKill.read();
		int dataLife = Integer.parseInt(String.valueOf((char)life));
		int dataKill = Integer.parseInt(String.valueOf((char)kill));//读取数据并转型
		System.out.println("read:"+dataLife+" "+dataKill );//验证行
		Record.setRelife(dataLife);
		Record.setSum(dataKill);
		Record.setDtnum(5-dataKill);
		Record.setMtnum(1);//记录到record类中
		fileLife.close();
		fileKill.close();//关闭文件
		this.addKeyListener(mp);//添加监听事件
		Thread t=new Thread(mp);
		t.start();//再次启动线程
		this.setVisible(true);
	}

	public void gameStart() {//新游戏方法（单独写出来，方便操作）
		if(gkmp!=null) {this.remove(gkmp); }  //删除关卡面板
		if(mp!=null) {this.remove(mp); }  //删除mp线程，防止出错
		mp=new MyPanel("new");
		this.add(mp); //新建面板
		Record.setSum(0);
		Record.setRelife(0);
		Record.setDtnum(5);
		Record.setMtnum(1);//设置初始数量
		this.addKeyListener(mp);
		Thread t=new Thread(mp);
		t.start();
		this.setVisible(true);
	}

	
	public void actionPerformed(ActionEvent e) {//重写监听事件，
		if(e.getActionCommand().equals("new")) {
			
			gameStart();
		}
	}
}  
class GuankaPanel extends JPanel implements Runnable{//关卡面板类
	int times=0;//控制闪烁的时间所用time变量
	public void paint(Graphics g) {//绘制坦克
		super.paint(g);
		g.fillRect(0,0,400,300);
		if(times%2==0) {
			g.setColor(Color.yellow);
			Font myFont=new Font("华文行楷",Font.BOLD,38);
			g.setFont(myFont);
			g.drawString("第1关", 140, 140);

		}
	}
	public void run() {//关卡面板线程run函数
		while(true) {
			try {
				Thread.sleep(600);
			}catch(Exception e) {}
			times++;
			this.repaint();
		}
	}
}
class MyPanel extends JPanel implements KeyListener,Runnable{//游戏面板类
	MyTank mt=null;//定义我方坦克
	Vector<DiTank> dtk=new Vector<DiTank>();//定义敌方坦克集合类
	Vector<Explosion> bzs=new Vector<Explosion>();//定义爆炸事件类
	int dinum=Record.getDtnum();

	Image tp1=null;//定义爆炸图片的容器，下同
	Image tp2=null;
	Image tp3=null;

	public MyPanel(String s) {//构造函数，构造新MyPanel，新游戏
		mt=new MyTank(150,200);
		if(s.equals("new")) {

			for(int i=0;i<dinum;i++) {
				DiTank dt=new DiTank((i)*80+5,0);
				dt.dtkxl(dtk);//防止敌坦克重叠加上这句，把敌坦克向量传到敌坦克类
				dt.setDirection(2);
				Thread t2=new Thread(dt);
				t2.start();
				Bullet zd=new Bullet(dt.x+10,dt.y+30,2);//启动敌坦克子弹线程
				dt.dzd.add(zd);
				Thread t3=new Thread(zd);
				t3.start();
				dtk.add(dt);
			}
		}
		tp1=Toolkit.getDefaultToolkit().getImage("img/bzxg1.gif");	
		tp2=Toolkit.getDefaultToolkit().getImage("img/bzxg2.gif");	
		tp3=Toolkit.getDefaultToolkit().getImage("img/bzxg3.gif");

	}
	public void count(Graphics g)  //画出统计数据的样式，显示统计数据
	{
		this.drawTank(80,330, g, 0, 0);  //画黄色坦克把颜色改了，下一句改过来
		g.setColor(Color.black);
		g.drawString(Record.getMtnum()+"",116,350);//整型数据+字符串，会转为字符串
		this.drawTank(150, 330, g, 0, 1);
		g.setColor(Color.black);
		g.drawString(Record.getDtnum()+"",186,350);
        this.drawTank(450, 86, g, 0,1);
		g.setColor(Color.black);
		g.drawString(Record.getSum()+"",486,107);
		
		this.drawTank(450, 86, g, 0,1);
		g.setColor(Color.black);
		g.drawString(Record.getRelife()+"",486,190);
		
		g.setColor(Color.black);
		Font f=new Font("华文彩云",Font.BOLD,20);
		g.setFont(f);
		g.drawString("您消灭的坦克总数", 410, 40);
		g.drawString("您的复活次数", 410, 150);
	}
	public void paint(Graphics g) {//控制绘制函数
		super.paint(g);
		g.fillRect(0,0,400,300);//绘制画布
		this.count(g);//显示统计数据
		
		if(mt.life) {
			this.drawTank(mt.getX(),mt.getY(),g,mt.direction,0);//绘制我的坦克
		}
		
		for(int i=0;i<dtk.size();i++)//绘制敌人坦克和子弹
		{
			DiTank dt=dtk.get(i);
			if(dt.life)
			{
				this.drawTank(dt.getX(),dt.getY(),g,dt.direction,1);//绘制敌人坦克
				for(int j=0;j<dt.dzd.size();j++)//绘制敌人坦克的子弹
				{
					Bullet dtzd=dt.dzd.get(j);
					if(dtzd!=null&&dtzd.life)
					{
						g.setColor(Color.white);
						g.fill3DRect(dtzd.x,dtzd.y,3,3,false);
					}else{
						dt.dzd.remove(dtzd);
					}
				}
			}			
		}		

		for(int i=0;i<mt.aa.size();i++) {//绘制子弹
			Bullet zd=mt.aa.get(i);
			if(zd!=null&&zd.life==true) {   
				g.setColor(Color.yellow);
				g.fill3DRect(zd.x, zd.y,3,3,false);
			}
			if(zd.life==false) {
				mt.aa.remove(zd);
			}
		}
		for(int i=0;i<bzs.size();i++) {
			Sound bzsound=new Sound("img/bomb.wav");  //把爆炸声音文件也粘贴到img文件夹里面
            bzsound.start(); 
			Explosion bz=bzs.get(i);
			if(bz.lifevalue>6) {//从大到小显示爆炸图片
				g.drawImage(tp1,bz.x, bz.y, 30,30, this);
			}else if(bz.lifevalue>3) {
				g.drawImage(tp2,bz.x, bz.y, 30,30, this);
			}else {
				g.drawImage(tp3,bz.x, bz.y, 30,30, this);
			}

			bz.lifedown();
			if(bz.lifevalue==0) {
				bzs.remove(bz);
			}
		}
	}
	public void hitWo() {//我方被击中
		for(int i=0;i<this.dtk.size();i++) {//一个一个取出坦克
			DiTank dt=dtk.get(i);
			for(int j=0;j<dt.dzd.size();j++) {//把敌方坦克的子弹一个一个取出来
				Bullet zd=dt.dzd.get(j);
				if(mt.life) {
					this.hitTwo(zd,mt);
				}
			}
		}
	}
	public void hitDi() {//敌方被击中
		for(int i=0;i<mt.aa.size();i++) {//一个一个取出我方子弹
			Bullet zd=mt.aa.get(i);
			if(zd.life) {
				for(int j=0;j<dtk.size();j++) {//一个一个取出敌方坦克
					DiTank dt=dtk.get(j);
					if(dt.life) {
						this.hitTwo(zd,dt);
					}

				}
			}this.repaint();
		}
	}
	public void hitTwo(Bullet zd,Tank t) {//判断双方被击中的情况（多态）
		switch(t.direction) {
		case 0:

		case 2:
		
			if(zd.x>t.x&&zd.x<t.x+20&&zd.y>t.y&&zd.y<t.y+30) {
				zd.life=false;
				t.life=false;
				
				if(DiTank.class.isInstance(t)) {
					Record.tjs(t);
					Record.killsum();
				}else {
					Record.fuhuo();
					mt=new MyTank(t.x,t.y);
				}
				Explosion bz=new Explosion(t.x,t.y);
				bzs.add(bz);
			}break;
		case 1:

		case 3:
		
			if(zd.x>t.x&&zd.x<t.x+30&&zd.y>t.y&&zd.y<t.y+20) {
				zd.life=false;
				t.life=false;
				
				if(DiTank.class.isInstance(t)) {
					Record.tjs(t);
					Record.killsum();
				}else {
					Record.fuhuo();
					mt=new MyTank(t.x,t.y);
				}
				Explosion bz=new Explosion(t.x,t.y);
				bzs.add(bz);
			}break;
		}
	}
	public void drawTank(int x,int y,Graphics g,int direction,int type) {//绘制坦克函数
		switch(type)
		{
		case 0:
			g.setColor(Color.yellow);
			break;
		case 1:
			g.setColor(Color.green);
			break;
		}
		switch(direction)
		{
		case 0:
			g.fill3DRect(x, y, 5, 30,false);
			g.fill3DRect(x+15,y , 5, 30,false);
			g.fill3DRect(x+5,y+5 , 10, 20,false);
			g.fillOval(x+5, y+10, 10, 10);
			g.drawLine(x+10, y+15, x+10, y-3);
			break;
		case 1:
			g.fill3DRect(x, y, 30, 5,false);
			g.fill3DRect(x, y+15, 30, 5, false);
			g.fill3DRect(x+5, y+5, 20, 10, false);
			g.fillOval(x+10, y+5, 10, 10);
			g.drawLine(x+15, y+10, x-3, y+10);
			break;
		case 2:
			g.fill3DRect(x, y, 5, 30,false);
			g.fill3DRect(x+15,y , 5, 30,false);
			g.fill3DRect(x+5,y+5 , 10, 20,false);
			g.fillOval(x+5, y+10, 10, 10);
			g.drawLine(x+10, y+15, x+10, y+33);
			break;
		case 3:
			g.fill3DRect(x, y, 30, 5,false);
			g.fill3DRect(x, y+15, 30, 5, false);
			g.fill3DRect(x+5, y+5, 20, 10, false);
			g.fillOval(x+10, y+5, 10, 10);
			g.drawLine(x+15, y+10, x+33, y+10);
			break;			
		}
		//this.repaint();
	}
	//实现接口重写的键盘事件，下同
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	public void keyPressed(KeyEvent e) {//监听键盘按键被按下的情况
		if(e.getKeyCode()==KeyEvent.VK_W) {
			this.mt.setDirection(0);
			if(this.mt.getY()<5) {this.mt.setY(0);}
			else{this.mt.up();}
		}else if(e.getKeyCode()==KeyEvent.VK_A) {
			this.mt.setDirection(1);
			this.mt.left();
			if(this.mt.getX()<5) {this.mt.setX(0);}
		}else if(e.getKeyCode()==KeyEvent.VK_S) {
			this.mt.setDirection(2);
			this.mt.down();
			if(this.mt.getY()>230) {this.mt.setY(230);}
		}else if(e.getKeyCode()==KeyEvent.VK_D) {
			this.mt.setDirection(3);
			this.mt.right();
			if(this.mt.getX()>350) {this.mt.setX(350);}
		}

		if(e.getKeyCode()==KeyEvent.VK_J) {
			if(this.mt.aa.size()<8) 
			{
				this.mt.fire();
			}
		}
		repaint();
	}
	public void run() {//游戏界面线程的run函数
		while(true) {
			try {
				Thread.sleep(50);
			}catch(Exception e){}
			this.hitDi();	
			this.hitWo();
			this.repaint();
		}
	}
}



