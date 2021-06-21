package tkdz;

import java.util.Vector;

class MyTank extends Tank//我方坦克类
{   int speed=5;//速度
    Vector<Bullet>aa=new Vector<Bullet>();//子弹集合类（向量）
	Bullet zd=null;//定义子弹

	public MyTank(int x,int y){//继承父类的构造函数
		super(x,y);
	}
	//以下为方向控制函数
	public void up() {y-=speed;}
	public void left() {x-=speed;}
	public void down() {y+=speed;}
	public void right() {x+=speed;}
	public void fire() {//开炮函数
		switch(this.direction) {
		case 0:
			zd=new Bullet(x+10,y,0);
			aa.add(zd);  //发射一颗子弹添加一颗
			break;
		case 1:
			zd=new Bullet(x,y+10,1);
			aa.add(zd);  
			break;
		case 2:
			zd=new Bullet(x+10,y+30,2);
			aa.add(zd);  
			break;
		case 3:
			zd=new Bullet(x+30,y+10,3);
			aa.add(zd);  //发射一颗子弹添加一颗
			break;
		}
		Thread t=new Thread(zd);//子弹线程
		t.start();//判断过后，子弹线程开始
	}

}

