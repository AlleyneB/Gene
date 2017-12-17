package com.AlleyneB.Gene.system;

import java.io.File;

public class GeneSeekController {

	
	private GeneSeekView view;
	private GeneSeekModel model;
//controller init	
	void init() {

		view=new GeneSeekView(this);
		model=new GeneSeekModel(this);		
	}
//display	
	void display(String string) {
		view.show(string);
	}
	
//setSeqs	
	 void setSeqs(String seqsFilePath, String[] heads) {
		model.setSeqsMap(seqsFilePath, heads);	
	}
//setCodes
	void setCodes(String codesFilePath) {
		model.setCodesMap(codesFilePath);
		
	}
//seekGene
	void seekGene(String geneFilePath) {
		model.seekGene(geneFilePath);
		
	}
//display result	
	void displayResult(File geneFile, String seqNums, String codeNum) {
		view.showResult(geneFile, seqNums, codeNum);
		
	}

}
