package com.xiuye.util;

import com.xiuye.speech.tts.TTSHelper;

public class SpeechUtil {

	public static synchronized void speak(String msg){
		TTSHelper.speak(msg);
	}
	public static synchronized void useEnglish(){
		TTSHelper.setVoice(2);
	}
	public static synchronized void useChinese(){
		TTSHelper.setVoice(1);
	}
	public static synchronized void readBook(String filename){
		TTSHelper.readFile(filename);
	}
	public static synchronized void speakOnce(String msg){
		TTSHelper.speakOnce(msg);
	}
	public static synchronized void talkSpeed(int speed){
		TTSHelper.setSpeed(speed);
	}
	public static synchronized void talkVolume(int volume){
		TTSHelper.setVolume(volume);
	}

}
