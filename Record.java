package tkdz;

import java.util.Vector;

class Record//��¼����
{
	private static int dtnum=5;//��Ҫ�þ�̬������¼��̹����
	private static int mtnum=1;  //��̹����
	private static int sum=0;   //ɱ������
	private static int relife=0;   //ɱ������
	
	private static Vector<DiTank> dtk=new Vector<DiTank>();//��̹�˼����ࣨ������
//����Ϊ��¼���װ���������ڶ�ȡ/д������
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
	public static int getDtnum() {  //��ȡ��̹����������̬����
		return dtnum;
	}
	public static int getMtnum() {  //��ȡ��̹����������̬����
		return mtnum;
	}
	public static void setDtnum(int dtnum) {
		Record.dtnum = dtnum;
	}
	public static void setMtnum(int mtnum) {
		Record.mtnum = mtnum;
	}
	public static void tjs(Tank t)   //��̹�˼��ٷ�������̬����
	{
		dtnum--;
	}
	public static void fuhuo()   //�������ͳ��
	{
		relife++;
	}

	
	public static void killsum()   //ɱ������ͳ��
	{
		sum++;
	}
}