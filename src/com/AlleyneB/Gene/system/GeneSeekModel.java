package com.AlleyneB.Gene.system;

import java.io.File;
import java.util.Map;

import com.AlleyneB.Gene.utils.GeneUtils;

public class GeneSeekModel {
	private Map<String, String> seqsMap=null;
	private Map<String, String> codesMap=null;
	private GeneSeekController controller;
	
	
//model的构造器
	public GeneSeekModel(GeneSeekController controller) {
		this.controller=controller;
	}


	void setSeqsMap(String seqsFilePath,String[] heads) {
		this.seqsMap =GeneUtils.getSeqsMap(new File(seqsFilePath), heads);
		controller.display("seqsMap已设置");
	}

	void setCodesMap(String codesFilePath) {
		this.codesMap =GeneUtils.getCodesMap(new File(codesFilePath));
		controller.display("codesMap已设置");
	}

	

//若已获取序列和编码文件，则执行匹配程序，否则报错	
	void seekGene(String genePath) {
		if(seqsMap==null||codesMap==null) {
			controller.display("请先设置序列文件及编码文件！");
			return;
		}
		File geneFile=new File(genePath);
		String seqNums = GeneUtils.findGeneCode(geneFile,seqsMap);
		if((seqNums!=null)&&seqNums.length()>0) {
			controller.displayResult(geneFile,seqNums,GeneUtils.findTCode(codesMap, seqNums));
		}
		
	}
}
