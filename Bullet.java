package tkdz;

class Bullet implements Runnable{//�ӵ���
	int x;//�ӵ�x����
	int y;//�ӵ�y����
	int direction;//�ӵ�����
	int speed=5;//�ӵ��ٶ�
	boolean life=true;//�ӵ�����

	public Bullet(int x,int y,int direction) {//�ӵ����췽��
		this.x=x;
		this.y=y;
		this.direction=direction;
	}
	public void run() {  //�ӵ��߳�run������ʹ�ӵ��ٶȼӵ�������
		while(true) {
			try {
				Thread.sleep(100);}
			catch(Exception e) {}
			switch(direction) {
			case 0:
				y-=speed;break;
			case 1:
				x-=speed;break;
			case 2:
				y+=speed;break;
			case 3:
				x+=speed;break;
			}
			if(x<0||x>400||y<0||y>300) {
				this.life=false;
			}

		}
	}
}
