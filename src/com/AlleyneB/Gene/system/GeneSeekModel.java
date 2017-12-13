package com.AlleyneB.Gene.system;

import java.io.File;
import java.util.Arrays;
import java.util.Map;

import com.AlleyneB.Gene.utils.GeneUtils;

public class GeneSeekModel {
	private Map<String, String> seqsMap=null;
	private Map<String, String> codesMap=null;
	private GeneSeekView view;
//model的构造器
	public GeneSeekModel(GeneSeekView view) {
		this.view=view;
	}
//seqsMap&codesMap getter and Setter
	public Map<String, String> getSeqsMap() {
		return seqsMap;
	}
	public void setSeqsMap(Map<String, String> seqsMap) {
		this.seqsMap = seqsMap;
		view.show("seqsMap已设置");
	}
	public Map<String, String> getCodesMap() {
		return codesMap;
	}
	public void setCodesMap(Map<String, String> codesMap) {
		this.codesMap = codesMap;
		view.show("codesMap已设置");
	}

//根据用户输入执行程序	
	void checkCMD(String[] cmd) {
		switch (cmd[0]) {
		case "help":view.help();
			break;
		case "setseqs":setSeqsMap(GeneUtils.getSeqsMap(new File(cmd[1]), Arrays.copyOfRange(cmd, 2, cmd.length)));
			break;
		case "setcodes":setCodesMap(GeneUtils.getCodesMap(new File(cmd[1])));
			break;
		case "seek":seekGene(cmd[1]);	
			break;
		default:
			break;
		}
	}
//若已获取序列和编码文件，则执行匹配程序，否则报错	
	private void seekGene(String string) {
		if(seqsMap==null||codesMap==null) {
			view.show("请先设置序列文件及编码文件！");
			return;
		}
		File geneFile=new File(string);
		String seqNums = GeneUtils.findGeneCode(geneFile,seqsMap);
		if((seqNums!=null)&&seqNums.length()>0) {
			view.showResult(geneFile,seqNums,GeneUtils.findTCode(codesMap, seqNums));
		}
		
	}
}
