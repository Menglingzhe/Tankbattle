package tkdz;

class Bullet implements Runnable{//子弹类
	int x;//子弹x坐标
	int y;//子弹y坐标
	int direction;//子弹方向
	int speed=5;//子弹速度
	boolean life=true;//子弹生命

	public Bullet(int x,int y,int direction) {//子弹构造方法
		this.x=x;
		this.y=y;
		this.direction=direction;
	}
	public void run() {  //子弹线程run函数，使子弹速度加到方向上
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
