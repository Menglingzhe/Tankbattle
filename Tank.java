package tkdz;

class Tank{//坦克类，敌我坦克的父类
	int x=0,y=0;//定义位置变量
	int direction=0;//定义方向变量
	int speed=1;//定义速度变量
	boolean life=true;//定义生命判断变量

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	//以上都为变量的数据封装函数
	public Tank(int x,int y) {//tank类构造方法
		this.x=x;
		this.y=y;
	}
}




