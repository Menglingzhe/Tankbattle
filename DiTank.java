package tkdz;

import java.util.Vector;

class DiTank extends Tank  implements Runnable//敌方坦克类
{  
	int speed=2;//速度
	int direction=2;//运动方向
	int time=0;//计时器，用于发射子弹
	Vector<Bullet> dzd=new Vector<Bullet>();	//子弹向量，用于装子弹
	Vector<DiTank> dtk=new Vector<DiTank>();//坦克向量，装坦克
	public void dtkxl(Vector<DiTank> dxl)//设置dtk
	{
		this.dtk=dxl;
	}
	public DiTank(int x,int y){//构造方法，定义敌方坦克位置属性
		super(x,y);
	}
	public boolean collide()//判断并控制敌方坦克位置，反馈给run程序
	{
		boolean b=false;//逻辑判断变量
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
						//下一点
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
						//下点 
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
	public void run() //DiTank线程run方法，控制敌方坦克移动以及射击
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