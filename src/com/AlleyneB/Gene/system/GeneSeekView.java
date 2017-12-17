package com.AlleyneB.Gene.system;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import com.AlleyneB.Gene.utils.GeneUtils;

public class GeneSeekView {
	
	//窗口
	Frame frame=new Frame("基因检测程序");
	

	Panel panel=new Panel();
	
	Button chooseSeqsFile=new Button("选择序列文件");
	TextField seqsFile=new TextField(40);
	
	Button chooseCodesFile=new Button("选择编码文件");
	TextField codesFile=new TextField(40);
	
	Button chooseGeneFile=new Button("选择基因文件");
	TextField geneFile=new TextField(40);
	
	TextField heads=new TextField("GAG,AAA,AAT");

	TextArea display =new TextArea(5,20);
	
	GeneSeekController controller;
	public GeneSeekView(GeneSeekController controller) {
		this.controller=controller;
		this.init();
	}
	
	public void init() {
		
		//选择序列文件
		panel.add(chooseSeqsFile);
		FileDialog seqsFileDialog=new FileDialog(frame, "请选择序列文件", FileDialog.LOAD);
		chooseSeqsFile.addActionListener(e->{
			seqsFileDialog.setVisible(true);
			seqsFile.setText(seqsFileDialog.getFile());
			//controller.display(seqsFileDialog.getDirectory()+seqsFileDialog.getFile());
			controller.setSeqs(seqsFileDialog.getDirectory()+seqsFileDialog.getFile(),heads.getText().split(","));
		});
		panel.add(seqsFile);
		
		//选择编码文件
		panel.add(chooseCodesFile);
		FileDialog codesFileDialog=new FileDialog(frame, "请选择编码文件", FileDialog.LOAD);
		chooseCodesFile.addActionListener(e->{
			codesFileDialog.setVisible(true);
			codesFile.setText(codesFileDialog.getFile());
			controller.setCodes(codesFileDialog.getDirectory()+codesFileDialog.getFile());
		});
		panel.add(codesFile);
		
		//选择基因文件
		panel.add(chooseGeneFile);
		FileDialog geneFileDialog=new FileDialog(frame, "请选择基因文件", FileDialog.LOAD);
		chooseGeneFile.addActionListener(e->{
			geneFileDialog.setVisible(true);
			geneFile.setText(geneFileDialog.getFile());
			controller.seekGene(geneFileDialog.getDirectory()+geneFileDialog.getFile()); 
		});
		panel.add(geneFile);
		
		panel.add(heads);

		
		
		panel.setLayout(new GridLayout(4, 2));
		
		frame.add(panel);
		frame.add(display,BorderLayout.SOUTH);
		
		frame.setBounds(300, 300, 150, 220);
		frame.pack();		
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	
	void help() {
	}

	void show(String string) {
		display.setText(string);		
	}

	void showResult(File geneFile, String seqNums,String codeNum) {
		display.setText(geneFile.getName()+"\r\n"+"---R匹配结果---"+"\r\n"+seqNums+"\r\n"+"---T匹配结果---"+"\r\n"+codeNum);	
	}
		

}
