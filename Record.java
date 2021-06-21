package tkdz;

import java.util.Vector;

class Record//记录版类
{
	private static int dtnum=5;//需要用静态变量记录敌坦数量
	private static int mtnum=1;  //我坦数量
	private static int sum=0;   //杀敌总数
	private static int relife=0;   //杀敌总数
	
	private static Vector<DiTank> dtk=new Vector<DiTank>();//敌坦克集合类（向量）
//以下为纪录类封装函数，用于读取/写入数据
	public Vector<DiTank> getDtk() {
		return dtk;
	}
	public void setDtk(Vector<DiTank> dtk) {
		this.dtk = dtk;
	}
	public static int getRelife() {
		return relife;
	}
	public static void setRelife(int relife) {
		Record.relife = relife;
	}
	public static int getSum() {
		return sum;
	}
	public static void setSum(int sum) {
		Record.sum = sum;
	}
	public static int getDtnum() {  //读取敌坦克数量，静态方法
		return dtnum;
	}
	public static int getMtnum() {  //读取我坦克数量，静态方法
		return mtnum;
	}
	public static void setDtnum(int dtnum) {
		Record.dtnum = dtnum;
	}
	public static void setMtnum(int mtnum) {
		Record.mtnum = mtnum;
	}
	public static void tjs(Tank t)   //敌坦克减少方法，静态方法
	{
		dtnum--;
	}
	public static void fuhuo()   //复活次数统计
	{
		relife++;
	}

	
	public static void killsum()   //杀敌总数统计
	{
		sum++;
	}
}