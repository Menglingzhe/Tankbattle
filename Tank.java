package tkdz;

class Tank{//̹���࣬����̹�˵ĸ���
	int x=0,y=0;//����λ�ñ���
	int direction=0;//���巽�����
	int speed=1;//�����ٶȱ���
	boolean life=true;//���������жϱ���

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
	//���϶�Ϊ���������ݷ�װ����
	public Tank(int x,int y) {//tank�๹�췽��
		this.x=x;
		this.y=y;
	}
}




