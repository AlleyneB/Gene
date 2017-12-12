package com.AlleyneB.Gene.system;

import java.util.Scanner;

public class GeneSeekView {
	
	public GeneSeekView() {
		this.start();
	}
	
	void start() {
		System.out.println("欢迎使用基因匹配程序!__如需帮助请输入\\help");
	}
	
	void help() {
		System.out.println("setsqs    序列文件路径————设置匹配序列");
		System.out.println("setcodes  编码文件路径————设置编码表");
		System.out.println("seek      基因文件路径————匹配基因并显示结果");
	}
	
	String[] getCMD() {
		Scanner sc=new Scanner(System.in);
		return sc.nextLine().split(" +");//正则表达式匹配1或多个空格
	}
}
