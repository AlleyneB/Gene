package com.AlleyneB.Gene.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class GeneUtils {
	
	/*根据序列文件和序列开头限定词获取过滤过的序列map*/
	public static Map<String,String> getSeqsMap(File seqsFile,String[] heads){
		Map<String,String> rMap=new LinkedHashMap<>();
		try(BufferedReader bfr=new BufferedReader(new FileReader(seqsFile))){
			String str1=null;
			String str2=null;
			while((str1=bfr.readLine())!=null) {
				str2=bfr.readLine();		
				for(String head:heads) {//序列文件格式为三行一个单元
					if(str2.substring(0, 3).equals(head)) {//符合序列开头限定词的才添加到map中
						rMap.put(str1.substring(2),str2);//第一行截取编号，第二行是序列
						break;
					}
				}
				bfr.readLine();//第三行是空白行
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return rMap;
	}
	
	
	/*根据编码文件返回文件中标号与对应序列编码的map*/
	public static Map<String, String> getCodesMap(File codesFile){
		Map<String,String> tMap=new LinkedHashMap<>();
		try(BufferedReader bfr=new BufferedReader(new FileReader(codesFile))){
			String str=new String();
			String[] strs=new String[2];
			while((str=bfr.readLine())!=null) {
				strs=str.split(",");
				if(strs.length<2)continue;//跳过特殊行NT
				tMap.put(strs[0],strs[1]);
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return tMap;
	}
	
	/*根据基因文件和序列map，返回基因文件包含的基因序列的编码，以-连接*/
	public static String findGeneCode(File geneFile,Map<String, String> seqsMap){
		StringBuilder geneCodeSb=new StringBuilder();
		StringBuilder geneSb=new StringBuilder();
		String result=null;
		try(BufferedReader br=new BufferedReader(new FileReader(geneFile))){
			String str = null;
			
			while((str=br.readLine())!=null) geneSb.append(str);//将基因文件读入内存
			
		//从基因文件开头往后匹配，匹配到序列，则删除基因字符串开头匹配到的序列，未匹配到，则删除基因字符串第一个字符，继续匹配循环	,保证匹配顺序以及无重叠匹配
			while(geneSb.length()>0) {
				boolean flag=false;//匹配成功标志
				for(Entry<String, String> entry:seqsMap.entrySet()) {
					String k=(String) entry.getKey();
					String v=(String) entry.getValue();
					if(geneSb.length()>=v.length()&&v.equals(geneSb.substring(0, v.length()))) {
						geneSb.delete(0, v.length());
						geneCodeSb.append(k+"-");
						flag=true;
						break;
					}
					else {
						flag=false;
					}
				}
				if(!flag)geneSb.deleteCharAt(0);

			}

			result=geneCodeSb.toString();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		result=geneCodeSb.toString();
		if((result==null)||result.length()<=0) {
			return null;
		}
		else {
			return result.substring(0, result.length()-1);
		}
	}
	
	/*根据基因编码map和基因编码串，查找对应map中对应基因的编码的标号*/
	public static String findTCode(Map<String, String> codesMap,String geneCode) {
		StringBuilder tCodeSb = new StringBuilder();
		codesMap.forEach((k,v)->{
			if(geneCode.equals(v)) {
				tCodeSb.append(k);
			}
		});
		if(tCodeSb.length()<=0)return null;
		else return tCodeSb.toString();
	}
}
