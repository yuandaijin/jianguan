package com.huatuo.clinics.cms.comm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.huatuo.clinics.cms.db.bean.CmsJgMenuInfoDTO;


/**
 * 把一个list集合,里面的bean含有 parentId 转为树形式
 *
 */
public class TreeUtil {
	
	
	/**
	 * 根据父节点的ID获取所有子节点
	 * @param list 分类表
	 * @param praentId 传入的父节点ID
	 * @return list
	 */
	public static List<CmsJgMenuInfoDTO> getChildResourcess(List<CmsJgMenuInfoDTO> list,String praentId) {
		List<CmsJgMenuInfoDTO> returnList = new ArrayList<CmsJgMenuInfoDTO>();
		for (Iterator<CmsJgMenuInfoDTO> iterator = list.iterator(); iterator.hasNext();) {
			CmsJgMenuInfoDTO t = (CmsJgMenuInfoDTO) iterator.next();
			// 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
			if (praentId.equals(String.valueOf(t.getParentId()))) {
				recursionFn(list, t);
				returnList.add(t);
			}
		}
		return returnList;
	}
	
	/**
	 * 递归列表
	 * @param list
	 * @param Resources
	 */
	private static void  recursionFn(List<CmsJgMenuInfoDTO> list, CmsJgMenuInfoDTO t) {
		List<CmsJgMenuInfoDTO> childList = getChildList(list, t);// 得到子节点列表
		t.setChildren(childList);
		for (CmsJgMenuInfoDTO tChild : childList) {
			if (hasChild(list, tChild)) {// 判断是否有子节点
				//returnList.add(Resources);
				Iterator<CmsJgMenuInfoDTO> it = childList.iterator();
				while (it.hasNext()) {
					CmsJgMenuInfoDTO n = (CmsJgMenuInfoDTO) it.next();
					recursionFn(list, n);
				}
			}
		}
	}
	
	// 得到子节点列表
	private static List<CmsJgMenuInfoDTO> getChildList(List<CmsJgMenuInfoDTO> list, CmsJgMenuInfoDTO t) {
		
		List<CmsJgMenuInfoDTO> tlist = new ArrayList<CmsJgMenuInfoDTO>();
		Iterator<CmsJgMenuInfoDTO> it = list.iterator();
		while (it.hasNext()) {
			CmsJgMenuInfoDTO n = (CmsJgMenuInfoDTO) it.next();
			if (String.valueOf(t.getmId()).equals(String.valueOf(n.getParentId()))) {
				tlist.add(n);
			}
		}
		return tlist;
	}

	// 判断是否有子节点
	private static boolean hasChild(List<CmsJgMenuInfoDTO> list, CmsJgMenuInfoDTO t) {
		return getChildList(list, t).size() > 0 ? true : false;
	}
}
