package com.huatuo.clinics.cms.util;
import java.util.ArrayList;
import java.util.List;

import com.huatuo.clinics.cms.bean.CsOutpOrderDTO;

public class Random {
	
	/**
	 * 随机抽取数据
	 * @param list	全部数据
	 * @param number 抽取的记录条数
	 * @return
	 */
	 public static List<CsOutpOrderDTO> randomNumber(List<CsOutpOrderDTO> list,Integer number)throws Exception{
		 if(number >list.size()){
			 throw new Exception("抽取数据条数不能大于总数据条数");
		 }
		 List<CsOutpOrderDTO> dtolist=new ArrayList<CsOutpOrderDTO>();
	        //产生N个随机数
	        List<Integer> listNo = new ArrayList<Integer>();
	        int i;
	        while(listNo.size() < number){
	            i = (int) (Math.random() * list.size());
	            if(!listNo.contains(i)){
	            	listNo.add(i);
	            }
	        }
	        for (int j = 0; j < list.size(); j++) {
	        	if(j<listNo.size()){
	        		CsOutpOrderDTO dto=list.get(listNo.get(j));
		        	dtolist.add(dto);
	        	}
			}
		return dtolist;
	 }
	 
	 
	 
	 
	 //测试
	public static void main(String[] args) throws Exception {
		List<CsOutpOrderDTO> list =new ArrayList<CsOutpOrderDTO>();
		CsOutpOrderDTO s1=new CsOutpOrderDTO();
		CsOutpOrderDTO s2=new CsOutpOrderDTO();
		CsOutpOrderDTO s3=new CsOutpOrderDTO();
		CsOutpOrderDTO s4=new CsOutpOrderDTO();
		CsOutpOrderDTO s5=new CsOutpOrderDTO();
		s1.setVistiId("第2个");
		s2.setVistiId("第3个");
		s3.setVistiId("第4个");
		s4.setVistiId("第5个");
		s5.setVistiId("第6个");
		list.add(s1);
		list.add(s2);
		list.add(s3);
		list.add(s4);
		list.add(s5);
		List<CsOutpOrderDTO> aaa=Random.randomNumber(list, 10);
		for (CsOutpOrderDTO csOutpOrderDTO : aaa) {
			System.out.println(csOutpOrderDTO.getVistiId());
		}
	}
	
}