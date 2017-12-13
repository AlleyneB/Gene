package com.AlleyneB.Gene.system;

import java.util.Scanner;

public class GeneSeekController {

	public static void main(String[] args) {
		GeneSeekView view=new GeneSeekView();
		GeneSeekModel model=new GeneSeekModel(view);
		while(true) {
			model.checkCMD(getCMD());
		}
		



	}
	
	 //获取用户输入，并解析为字符串数组
	static String[] getCMD() {
		Scanner sc=new Scanner(System.in);
		return sc.nextLine().split(" +");//正则表达式匹配1或多个空格
	}

}
