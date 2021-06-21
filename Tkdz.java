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

public class Tkdz extends JFrame implements ActionListener{//��Ϸ��������
	MyPanel mp=null;//�������
	GuankaPanel gkmp=null;//����ؿ����
	JMenuBar cdt=null;//����˵���
	JMenu cd1=null;//����˵�
	JMenuItem cdx1=null;//����˵���ť
	JMenuItem cdx2=null;//����˵���ť
	JMenuItem cdx3=null;//����˵���ť
	JMenuItem cdx4=null;//����˵���ť
	JMenuItem cdx5=null;//����˵���ť
	
	public static void main(String[] args) //����������ʼ��Ϸ
	{
		Tkdz tkdz=new Tkdz();
	}
	public Tkdz() {//���庯����ֻ�����ã�
		cdt=new JMenuBar();//�˵���
		cd1=new JMenu("��Ϸ(G)");//�˵�
		cd1.setMnemonic('G');//�����ȼ�
		cdx1=new JMenuItem("����Ϸ(N)");//����Ϸ��Ŀ
		cdx1.addActionListener(this);//�����¼�(֮����д)
		cdx1.setActionCommand("new");
		cd1.add(cdx1);
		cdx2=new JMenuItem("������Ϸ(C)");//������Ϸ��Ŀ
		cdx2.addActionListener(this);
		cdx2.setActionCommand("continue");
		cd1.add(cdx2);
		cdx4=new JMenuItem("�����˳�(S)");//������Ŀ
		cdx4.addActionListener(this);
		cdx4.setActionCommand("saveandexit");//������ǩ
		cd1.add(cdx4);
		cdx5=new JMenuItem("�˳�(E)");//�˳���Ŀ
		cdx5.addActionListener(this);
		cdx5.setActionCommand("exit");
		cd1.add(cdx5);
		cdt.add(cd1);

		cdx2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){//����������¼�
				
				try {
					continueGame();
				} catch (Exception e1) {
					// TODO �Զ����ɵ� catch ��
					
				}
				
				
				
			}
		});
		cdx5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//����������¼�
				
				System.exit(0);
			}
		});
		cdx4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//����������¼�����д��
				try {
					exitSave();
				} catch (Exception e1) {//�˳�ʧ�ܺ���
					
					e1.printStackTrace();
					System.out.println("error!");//�ڿ���̨�е���
				}
				System.exit(0);
			}
		});
		
		
		
		
		
		gkmp=new GuankaPanel();//�ؿ����
		Thread t=new Thread(gkmp);//����һ���ؿ�����߳�
		t.start();//�߳�ͣ�����ֲ���˸
		this.setJMenuBar(cdt);//���ù�����
		this.add(gkmp);

		this.setTitle("�Ź�1902������");
		this.setIconImage((new ImageIcon("img/tank.jpg")).getImage());
		this.setSize(600,500);//����Ŵ�
		this.setLocation(300,200);
		this.setResizable(false);//�������У����治������
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

	}
	
	
	public void exitSave() throws Exception{//���淽��
		File fileLife = new File("D:/desktop/relife.txt");
		File fileKill = new File("D:/desktop/kill.txt");//�½��ļ�����
		FileOutputStream fosLife = new FileOutputStream(fileLife);//���ļ���
		FileOutputStream fosKill = new FileOutputStream(fileKill);
		String strLife,strKill;//�洢����
		System.out.println(Record.getRelife()+Record.getSum());//��¼��Ŀ����ӡ�ڿ���̨���������
		strLife = String.valueOf(Record.getRelife());//ת��
		strKill = String.valueOf(Record.getSum());
		fosLife.write(strLife.getBytes());//д���ļ�
		fosKill.write(strKill.getBytes());
		fosLife.close();//�ر��ļ���
		fosKill.close();
	}
	public void continueGame() throws Exception{//��������
		if(gkmp!=null) {this.remove(gkmp); }  //ɾ���ؿ����
		if(mp!=null) {this.remove(mp); }  //ɾ��mp���߳�
		mp=new MyPanel("new");//�½�mp���
		this.add(mp); 
		FileReader fileLife = new FileReader("D:/desktop/relife.txt");//���ļ���
		FileReader fileKill = new FileReader("D:/desktop/kill.txt");
		
		int life = fileLife.read();
		int kill = fileKill.read();
		int dataLife = Integer.parseInt(String.valueOf((char)life));
		int dataKill = Integer.parseInt(String.valueOf((char)kill));//��ȡ���ݲ�ת��
		System.out.println("read:"+dataLife+" "+dataKill );//��֤��
		Record.setRelife(dataLife);
		Record.setSum(dataKill);
		Record.setDtnum(5-dataKill);
		Record.setMtnum(1);//��¼��record����
		fileLife.close();
		fileKill.close();//�ر��ļ�
		this.addKeyListener(mp);//��Ӽ����¼�
		Thread t=new Thread(mp);
		t.start();//�ٴ������߳�
		this.setVisible(true);
	}

	public void gameStart() {//����Ϸ����������д���������������
		if(gkmp!=null) {this.remove(gkmp); }  //ɾ���ؿ����
		if(mp!=null) {this.remove(mp); }  //ɾ��mp�̣߳���ֹ����
		mp=new MyPanel("new");
		this.add(mp); //�½����
		Record.setSum(0);
		Record.setRelife(0);
		Record.setDtnum(5);
		Record.setMtnum(1);//���ó�ʼ����
		this.addKeyListener(mp);
		Thread t=new Thread(mp);
		t.start();
		this.setVisible(true);
	}

	
	public void actionPerformed(ActionEvent e) {//��д�����¼���
		if(e.getActionCommand().equals("new")) {
			
			gameStart();
		}
	}
}  
class GuankaPanel extends JPanel implements Runnable{//�ؿ������
	int times=0;//������˸��ʱ������time����
	public void paint(Graphics g) {//����̹��
		super.paint(g);
		g.fillRect(0,0,400,300);
		if(times%2==0) {
			g.setColor(Color.yellow);
			Font myFont=new Font("�����п�",Font.BOLD,38);
			g.setFont(myFont);
			g.drawString("��1��", 140, 140);

		}
	}
	public void run() {//�ؿ�����߳�run����
		while(true) {
			try {
				Thread.sleep(600);
			}catch(Exception e) {}
			times++;
			this.repaint();
		}
	}
}
class MyPanel extends JPanel implements KeyListener,Runnable{//��Ϸ�����
	MyTank mt=null;//�����ҷ�̹��
	Vector<DiTank> dtk=new Vector<DiTank>();//����з�̹�˼�����
	Vector<Explosion> bzs=new Vector<Explosion>();//���屬ը�¼���
	int dinum=Record.getDtnum();

	Image tp1=null;//���屬ըͼƬ����������ͬ
	Image tp2=null;
	Image tp3=null;

	public MyPanel(String s) {//���캯����������MyPanel������Ϸ
		mt=new MyTank(150,200);
		if(s.equals("new")) {

			for(int i=0;i<dinum;i++) {
				DiTank dt=new DiTank((i)*80+5,0);
				dt.dtkxl(dtk);//��ֹ��̹���ص�������䣬�ѵ�̹������������̹����
				dt.setDirection(2);
				Thread t2=new Thread(dt);
				t2.start();
				Bullet zd=new Bullet(dt.x+10,dt.y+30,2);//������̹���ӵ��߳�
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
	public void count(Graphics g)  //����ͳ�����ݵ���ʽ����ʾͳ������
	{
		this.drawTank(80,330, g, 0, 0);  //����ɫ̹�˰���ɫ���ˣ���һ��Ĺ���
		g.setColor(Color.black);
		g.drawString(Record.getMtnum()+"",116,350);//��������+�ַ�������תΪ�ַ���
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
		Font f=new Font("���Ĳ���",Font.BOLD,20);
		g.setFont(f);
		g.drawString("�������̹������", 410, 40);
		g.drawString("���ĸ������", 410, 150);
	}
	public void paint(Graphics g) {//���ƻ��ƺ���
		super.paint(g);
		g.fillRect(0,0,400,300);//���ƻ���
		this.count(g);//��ʾͳ������
		
		if(mt.life) {
			this.drawTank(mt.getX(),mt.getY(),g,mt.direction,0);//�����ҵ�̹��
		}
		
		for(int i=0;i<dtk.size();i++)//���Ƶ���̹�˺��ӵ�
		{
			DiTank dt=dtk.get(i);
			if(dt.life)
			{
				this.drawTank(dt.getX(),dt.getY(),g,dt.direction,1);//���Ƶ���̹��
				for(int j=0;j<dt.dzd.size();j++)//���Ƶ���̹�˵��ӵ�
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

		for(int i=0;i<mt.aa.size();i++) {//�����ӵ�
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
			Sound bzsound=new Sound("img/bomb.wav");  //�ѱ�ը�����ļ�Ҳճ����img�ļ�������
            bzsound.start(); 
			Explosion bz=bzs.get(i);
			if(bz.lifevalue>6) {//�Ӵ�С��ʾ��ըͼƬ
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
	public void hitWo() {//�ҷ�������
		for(int i=0;i<this.dtk.size();i++) {//һ��һ��ȡ��̹��
			DiTank dt=dtk.get(i);
			for(int j=0;j<dt.dzd.size();j++) {//�ѵз�̹�˵��ӵ�һ��һ��ȡ����
				Bullet zd=dt.dzd.get(j);
				if(mt.life) {
					this.hitTwo(zd,mt);
				}
			}
		}
	}
	public void hitDi() {//�з�������
		for(int i=0;i<mt.aa.size();i++) {//һ��һ��ȡ���ҷ��ӵ�
			Bullet zd=mt.aa.get(i);
			if(zd.life) {
				for(int j=0;j<dtk.size();j++) {//һ��һ��ȡ���з�̹��
					DiTank dt=dtk.get(j);
					if(dt.life) {
						this.hitTwo(zd,dt);
					}

				}
			}this.repaint();
		}
	}
	public void hitTwo(Bullet zd,Tank t) {//�ж�˫�������е��������̬��
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
	public void drawTank(int x,int y,Graphics g,int direction,int type) {//����̹�˺���
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
	//ʵ�ֽӿ���д�ļ����¼�����ͬ
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	public void keyPressed(KeyEvent e) {//�������̰��������µ����
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
	public void run() {//��Ϸ�����̵߳�run����
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



