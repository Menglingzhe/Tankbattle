package tkdz;


class Explosion{//爆炸事件类
	int x,y;//位置（坐标）
	int lifevalue=30;//坦克生命值
	boolean life=true;//坦克存活情况
	public Explosion(int x,int y) {//爆炸类构造方法，确定爆炸地点
		this.x=x;
		this.y=y;
	}
	public void lifedown() {//生存期递减
		if(lifevalue>0) {
			lifevalue--;
		}else {life=false;}
	}
}
