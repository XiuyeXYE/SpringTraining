package com.xiuye.main;

import com.xiuye.util.SpeechUtil;

public class SpeechMain {

	public static void main(String []args){

		SpeechUtil.speak("你好啊");
		SpeechUtil.useEnglish();
		SpeechUtil.talkVolume(100);
		SpeechUtil.talkSpeed(-100);
		SpeechUtil.speak("You are son of bitch!");

	}

}
