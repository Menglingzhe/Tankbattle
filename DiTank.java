package tkdz;

import java.util.Vector;

class DiTank extends Tank  implements Runnable//�з�̹����
{  
	int speed=2;//�ٶ�
	int direction=2;//�˶�����
	int time=0;//��ʱ�������ڷ����ӵ�
	Vector<Bullet> dzd=new Vector<Bullet>();	//�ӵ�����������װ�ӵ�
	Vector<DiTank> dtk=new Vector<DiTank>();//̹��������װ̹��
	public void dtkxl(Vector<DiTank> dxl)//����dtk
	{
		this.dtk=dxl;
	}
	public DiTank(int x,int y){//���췽��������з�̹��λ������
		super(x,y);
	}
	public boolean collide()//�жϲ����Ƶз�̹��λ�ã�������run����
	{
		boolean b=false;//�߼��жϱ���
		switch(this.direction)
		{
		case 0:

			for(int i=0;i<dtk.size();i++)
			{
				DiTank dt=dtk.get(i);

				if(dt!=this)
				{
					if(dt.direction==0||dt.direction==2)
					{
						if(this.x>=dt.x && this.x<=dt.x+20 && this.y>=dt.y && this.y<=dt.y+30)
						{
							return true;
						}
						if(this.x+20>=dt.x && this.x+20<=dt.x+20 && this.y>=dt.y && this.y<=dt.y+30)
						{
							return true;
						}
					}
					if(dt.direction==3||dt.direction==1)
					{
						if(this.x>=dt.x && this.x<=dt.x+30 && this.y>=dt.y && this.y<=dt.y+20)
						{
							return true;
						}
						if(this.x+20>=dt.x && this.x+20<=dt.x+30 && this.y>=dt.y && this.y<=dt.y+20)
						{
							return true;
						}
					}
				}
			}
			break;
		case 1:
			for(int i=0;i<dtk.size();i++)
			{
				DiTank dt=dtk.get(i);
				if(dt!=this)
				{
					if(dt.direction==0||dt.direction==2)
					{
						if(this.x>=dt.x&&this.x<=dt.x+20&&this.y>=dt.y&&this.y<=dt.y+30)
						{
							return true;
						}
						//��һ��
						if(this.x>=dt.x&&this.x<=dt.x+20&&this.y+20>=dt.y&&this.y+20<=dt.y+30)
						{
							return true;
						}
					}
					if(dt.direction==3||dt.direction==1)
					{
						if(this.x>=dt.x&&this.x<=dt.x+30&&this.y>=dt.y&&this.y<=dt.y+20)
						{
							return true;
						}
						if(this.x>=dt.x&&this.x<=dt.x+30&&this.y+20>=dt.y&&this.y+20<=dt.y+20)
						{
							return true;
						}
					}
				}
			}


			break;
		case 2:
			for(int i=0;i<dtk.size();i++)
			{
				DiTank dt=dtk.get(i);
				if(dt!=this)
				{
					if(dt.direction==0||dt.direction==2)
					{
						if(this.x>=dt.x&&this.x<=dt.x+20&&this.y+30>=dt.y&&this.y+30<=dt.y+30)
						{
							return true;
						}
						if(this.x+20>=dt.x&&this.x+20<=dt.x+20&&this.y+30>=dt.y&&this.y+30<=dt.y+30)
						{
							return true;
						}
					}
					if(dt.direction==3||dt.direction==1)
					{
						if(this.x>=dt.x&&this.x<=dt.x+30&&this.y+30>=dt.y&&this.y+30<=dt.y+20)
						{
							return true;
						}

						if(this.x+20>=dt.x&&this.x+20<=dt.x+30&&this.y+30>=dt.y&&this.y+30<=dt.y+20)
						{
							return true;
						}
					}
				}
			}
			break;
		case 3:
			for(int i=0;i<dtk.size();i++)
			{
				DiTank dt=dtk.get(i);
				if(dt!=this)
				{
					if(dt.direction==0||dt.direction==2)
					{
						if(this.x+30>=dt.x && this.x+30<=dt.x+20 && this.y>=dt.y && this.y<=dt.y+30)
						{
							return true;
						}
						//�µ� 
						if(this.x+30>=dt.x && this.x+30<=dt.x+20 && this.y+20>=dt.y && this.y+20<=dt.y+30)
						{
							return true;
						}
					}
					if(dt.direction==3||dt.direction==1)
					{
						if(this.x+30>=dt.x && this.x+30<=dt.x+30 && this.y>=dt.y && this.y<=dt.y+20)
						{
							return true;
						}
						if(this.x+30>=dt.x && this.x+30<=dt.x+30 && this.y+20>=dt.y && this.y+20<=dt.y+20)
						{
							return true;
						}
					}
				}
			}

			break;
		}		
		return b;
	}
	public void run() //DiTank�߳�run���������Ƶз�̹���ƶ��Լ����
	{
		while(true)
		{try {
			Thread.sleep(50);
		} catch (Exception e){}

		this.direction=(int)(Math.random()*4);
		switch(this.direction){
		case 0:
			for(int i=0;i<30;i++)
			{
				if(y>0&& !collide() )
				{y-=speed;}	
				try {
					Thread.sleep(50);
				} catch (Exception e){}
			}		    	
			break;
		case 1:
			for(int i=0;i<30;i++)
			{
				if(x>0 && !collide())
				{x-=speed;}	
				try {
					Thread.sleep(50);
				} catch (Exception e){}
			}			    	
			break;
		case 2:
			for(int i=0;i<30;i++)
			{
				if(y<220 && !collide())
				{y+=speed;}	
				try {
					Thread.sleep(50);
				} catch (Exception e){}
			}			    	
			break;
		case 3:
			for(int i=0;i<30;i++)
			{
				if(x<320&& !collide() )
				{x+=speed;}	
				try {
					Thread.sleep(50);
				} catch (Exception e){}
			}		    	
			break;					 
		}  
		if(this.life==false)
		{
			break;
		}
		this.time++;

		if(time%2==0)
		{
			if(life)
			{
				if(dzd.size()<5)
				{
					Bullet zd=null;

					switch(direction)
					{
					case 0:
						zd=new Bullet(x+10,y,0);
						dzd.add(zd);
						break;
					case 1:
						zd=new Bullet(x,y+10,1);
						dzd.add(zd);
						break;
					case 2:
						zd=new Bullet(x+10,y+30,2);
						dzd.add(zd);
						break;
					case 3:
						zd=new Bullet(x+30,y+10,3);
						dzd.add(zd);
						break;
					}
					Thread t5=new Thread(zd);
					t5.start();
				}
			}
		}
		}
	}
}