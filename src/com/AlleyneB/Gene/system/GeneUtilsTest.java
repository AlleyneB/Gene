package com.AlleyneB.Gene.system;

import java.io.File;
import java.util.Map;

import com.AlleyneB.Gene.utils.GeneUtils;

public class GeneUtilsTest {

	public static void main(String[] args) {
		String[] chosedGeneHead=new String[] {"GAG","AAA","AAT"};
		
		File seqsFile=new File("C:\\Users\\Alleyne\\Documents\\javaStudy\\R.txt");
		
		Map<String, String> seqsMap=GeneUtils.getSeqsMap(seqsFile,chosedGeneHead);
		
		File codesFile=new File("C:\\Users\\Alleyne\\Documents\\javaStudy\\T.txt");
		
		Map<String, String> codesMap=GeneUtils.getCodesMap(codesFile);
		
		File[] subFiles=new File("C:\\Users\\Alleyne\\Documents\\javaStudy\\pta").listFiles();
		String str=null;
		for(File geneFile:subFiles) {
			str=GeneUtils.findGeneCode(geneFile,seqsMap);
			if((str!=null)&&str.length()>0) {
				System.out.println(geneFile.getName());
				System.out.println("---R匹配结果---"+"\r\n"+str);
				System.out.println("---T匹配结果---"+"\r\n"+GeneUtils.findTCode(codesMap, str));
			}
		}

	}

}
