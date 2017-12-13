package com.AlleyneB.Gene.utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileConnect {
	public static void FileConnect(File aimFile,String subFilesPath) throws IOException {
		aimFile.createNewFile();
		File[] subFiles=new File(subFilesPath).listFiles();
		char[] cbuf=new char[128];
		int hasRead=0;
		FileWriter fw=new FileWriter(aimFile);
		for(File file:subFiles) {
			FileReader fr=new FileReader(file);
			fw.write(">"+file.getName()+"\r\n");
			System.out.println(file.getName());
			while((hasRead=fr.read(cbuf))>0){fw.write(new String(cbuf,0,hasRead));}
			fw.write("\r\n\r\n");
			fr.close();
		}
		fw.close();
	}
}
