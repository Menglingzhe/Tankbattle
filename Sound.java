package tkdz;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

class Sound extends Thread{//声音类
	String wjm;  //定义文件名
	
	public  Sound(String wjm){//音频文件参数传进来创建对象
		this.wjm=wjm;
	}
	public void run(){//声音线程run函数，读取音频并播放
		File wjl=new File(wjm);
		AudioInputStream ypsrl=null;//音频输入流
		try{
			ypsrl=AudioSystem.getAudioInputStream(wjl);
		}catch(Exception e){}

		AudioFormat format=ypsrl.getFormat();
		SourceDataLine aqsj=null;
		DataLine.Info info=new DataLine.Info(SourceDataLine.class,format);//格式化处理

		try{
			aqsj=(SourceDataLine)AudioSystem.getLine(info);
			aqsj.open(format);
		}catch(Exception e){}
		aqsj.start();

		int zjtj=0;
		byte[] hczj=new byte[1024];
		try{
			while(zjtj!=-1){
				zjtj=ypsrl.read(hczj,0,hczj.length);   //从缓冲区读写音频文件
				if(zjtj>=0){
					aqsj.write(hczj,0,zjtj);
				}
			}
		}catch(Exception e){}
		finally{
			aqsj.drain();// 排空管道里的数据
			aqsj.close();
		}
	}
}