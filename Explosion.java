package tkdz;


class Explosion{//��ը�¼���
	int x,y;//λ�ã����꣩
	int lifevalue=30;//̹������ֵ
	boolean life=true;//̹�˴�����
	public Explosion(int x,int y) {//��ը�๹�췽����ȷ����ը�ص�
		this.x=x;
		this.y=y;
	}
	public void lifedown() {//�����ڵݼ�
		if(lifevalue>0) {
			lifevalue--;
		}else {life=false;}
	}
}
