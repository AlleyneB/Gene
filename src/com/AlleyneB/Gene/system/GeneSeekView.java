package com.AlleyneB.Gene.system;

import java.io.File;
import java.util.Scanner;

import com.AlleyneB.Gene.utils.GeneUtils;

public class GeneSeekView {
	
	public GeneSeekView() {
		this.start();
	}
	
	void start() {
		System.out.println("欢迎使用基因匹配程序!__如需帮助请输入help");
	}
	
	void help() {
		System.out.println("setseqs   +序列文件路径+要匹配的序列开头字符（以空格键分隔）————设置匹配序列");
		System.out.println("setcodes  +编码文件路径————设置编码表");
		System.out.println("seek      +基因文件路径————匹配基因并显示结果");
	}

	public void show(String string) {
		System.out.println(string);
		
	}

	public void showResult(File geneFile, String seqNums,String codeNum) {
		System.out.println(geneFile.getName());
		System.out.println("---R匹配结果---"+"\r\n"+seqNums);
		System.out.println("---T匹配结果---"+"\r\n"+codeNum);
		
	}
	

}
