package tkdz;

import java.util.Vector;

class MyTank extends Tank//�ҷ�̹����
{   int speed=5;//�ٶ�
    Vector<Bullet>aa=new Vector<Bullet>();//�ӵ������ࣨ������
	Bullet zd=null;//�����ӵ�

	public MyTank(int x,int y){//�̳и���Ĺ��캯��
		super(x,y);
	}
	//����Ϊ������ƺ���
	public void up() {y-=speed;}
	public void left() {x-=speed;}
	public void down() {y+=speed;}
	public void right() {x+=speed;}
	public void fire() {//���ں���
		switch(this.direction) {
		case 0:
			zd=new Bullet(x+10,y,0);
			aa.add(zd);  //����һ���ӵ����һ��
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
			aa.add(zd);  //����һ���ӵ����һ��
			break;
		}
		Thread t=new Thread(zd);//�ӵ��߳�
		t.start();//�жϹ����ӵ��߳̿�ʼ
	}

}

