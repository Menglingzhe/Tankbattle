package tkdz;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

class Sound extends Thread{//������
	String wjm;  //�����ļ���
	
	public  Sound(String wjm){//��Ƶ�ļ�������������������
		this.wjm=wjm;
	}
	public void run(){//�����߳�run��������ȡ��Ƶ������
		File wjl=new File(wjm);
		AudioInputStream ypsrl=null;//��Ƶ������
		try{
			ypsrl=AudioSystem.getAudioInputStream(wjl);
		}catch(Exception e){}

		AudioFormat format=ypsrl.getFormat();
		SourceDataLine aqsj=null;
		DataLine.Info info=new DataLine.Info(SourceDataLine.class,format);//��ʽ������

		try{
			aqsj=(SourceDataLine)AudioSystem.getLine(info);
			aqsj.open(format);
		}catch(Exception e){}
		aqsj.start();

		int zjtj=0;
		byte[] hczj=new byte[1024];
		try{
			while(zjtj!=-1){
				zjtj=ypsrl.read(hczj,0,hczj.length);   //�ӻ�������д��Ƶ�ļ�
				if(zjtj>=0){
					aqsj.write(hczj,0,zjtj);
				}
			}
		}catch(Exception e){}
		finally{
			aqsj.drain();// �ſչܵ��������
			aqsj.close();
		}
	}
}