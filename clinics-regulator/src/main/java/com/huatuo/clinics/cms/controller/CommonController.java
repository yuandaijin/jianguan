package com.huatuo.clinics.cms.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import sun.misc.BASE64Decoder;

import com.huatuo.clinics.cms.bean.CmsJgDistrictDTO;
import com.huatuo.clinics.cms.response.DistrictResponse;
import com.huatuo.clinics.cms.services.regulator.CmsJgDistrictService;
import com.huatuo.common.ConfigProperites;
import com.huatuo.common.FtpUtils;
import com.huatuo.common.MessageUtil;
import com.huatuo.common.Utils;

@Controller
@RequestMapping("/comm")
public class CommonController {
	@Autowired
	private CmsJgDistrictService cmsJgDistrictService;
	
	/**
	 * 地址三级联动(通过父级id查询，并返回区域)
	 * 
	 * @param parentId
	 */
	@RequestMapping(value = "/resultAddress", method = RequestMethod.GET)
	public @ResponseBody
	DistrictResponse resultAddress(
			@RequestParam(value = "parentId") String parentId,
			HttpServletRequest request) {
		DistrictResponse rep = new DistrictResponse();
		try {
			List<CmsJgDistrictDTO> list = cmsJgDistrictService
					.getDistrictByParent(Integer.parseInt(parentId));
			rep.setList(list);
			rep.setMessage(MessageUtil.SUCCESS);
		} catch (Exception e) {
			rep.setMessage(MessageUtil.FAILURE);
			rep.setCode(MessageUtil.CODE_FAILURE);
		}
		return rep;
	}
	/** 
     * 上传图片 
     * @param file 
     * @param request 
     * @param response 
     * @return 
     */  
    @ResponseBody  
    @RequestMapping(value="/uploadImages")  
    public Map<String, Object> images (MultipartFile upfile, HttpServletRequest request, HttpServletResponse response){  
        Map<String, Object> params = new HashMap<String, Object>();  
        try{  
             String visitUrl = ConfigProperites.getImageUrl();  
             //获取后缀
             String ext = Utils.getExt(upfile.getOriginalFilename());  
             String fileName = String.valueOf(System.currentTimeMillis()).concat(".").concat(ext);  
             
             String path = "/clinics_information_Images";
			 InputStream inputStream = upfile.getInputStream();
			 // 保存到服务器
			 FtpUtils ftpUtils = new FtpUtils();
			 ftpUtils.upLoadFromProduction(path, fileName, inputStream);
			 visitUrl = "/clinics_information_Images/" + fileName;
			 
             params.put("state", "SUCCESS");  
             params.put("url", visitUrl);  
             params.put("size", upfile.getSize());  
             params.put("original", fileName);  
             params.put("type", upfile.getContentType());  
        } catch (Exception e){  
        	System.out.println(e.getMessage());
             params.put("state", "ERROR");  
        }  
         return params;  
    }  
    
    /** 
     * 上传文件 
     * @param file 
     * @param request 
     * @param response 
     * @return 
     */  
    @ResponseBody  
    @RequestMapping(value="/uploadAttachments")  
    public Map<String, Object> attachments (MultipartFile upfile, HttpServletRequest request, HttpServletResponse response){  
    	Map<String, Object> params = new HashMap<String, Object>();  
    	try{  
    		String visitUrl = ConfigProperites.getImageUrl();  
    		//原名称
    		String name = upfile.getOriginalFilename();
    		//获取后缀
    		String ext = Utils.getExt(name);  
    		//保存的文件名称
    		String fileName = String.valueOf(System.currentTimeMillis()).concat(".").concat(ext);  
    		
    		String path = "/clinics_information_Attachments";
    		InputStream inputStream = upfile.getInputStream();
    		// 保存到服务器
    		FtpUtils ftpUtils = new FtpUtils();
    		ftpUtils.upLoadFromProduction(path, fileName, inputStream);
    		visitUrl = "/clinics_information_Attachments/" + fileName;
    		
    		params.put("state", "SUCCESS");  
    		params.put("url", visitUrl);  
    		params.put("size", upfile.getSize());  
    		params.put("original", name);  
    		params.put("type", upfile.getContentType());  
    	} catch (Exception e){  
    		System.out.println(e.getMessage());
    		params.put("state", "ERROR");  
    	}  
    	return params;  
    }  
    
    /** 
     * 上传图片 
     * @param file(Base64)
     * @param request 
     * @param response 
     * @return 
     */  
    @ResponseBody  
    @RequestMapping(value="/uploadImagesBase64",method=RequestMethod.POST)  
    public Map<String, Object> uploadImagesBase64 (String upfile, HttpServletRequest request, HttpServletResponse response){  
        Map<String, Object> params = new HashMap<String, Object>();  
        try{  
             String visitUrl = ConfigProperites.getImageUrl();  
             // 文件类型加后缀
             String typeAndExt = upfile.substring(
            		 upfile.indexOf(":") + 1, upfile.indexOf(";"));
             String type = typeAndExt.substring(0,
						typeAndExt.indexOf("/"));// 文件类型
             String ext = typeAndExt.substring(
						typeAndExt.indexOf("/") + 1, typeAndExt.length());
             String fileName = String.valueOf(System.currentTimeMillis()).concat(".").concat(ext);  
             String path = "/clinics_information_Attachments";
			 InputStream inputStream =base64ToFile(upfile);
			 // 保存到服务器
			 FtpUtils ftpUtils = new FtpUtils();
			 ftpUtils.upLoadFromProduction(path, fileName, inputStream);
			 visitUrl = "/clinics_information_Attachments/" + fileName;
			 
             params.put("state", "SUCCESS");  
             params.put("url", visitUrl);  
             params.put("original", fileName);  
             params.put("type", type);  
        } catch (Exception e){  
        	System.out.println(e.getMessage());
             params.put("state", "ERROR");  
        }  
         return params;  
    }  
    /**
	 * base64转换成流
	 * 
	 * @param base64
	 * @param name
	 * @return
	 */
	@SuppressWarnings({ "unused" })
	public static InputStream base64ToFile(String base64)
			throws IOException {
		String newbase64 = base64.substring(base64.indexOf(",") + 1,
				base64.length());// 去掉base前缀头信息
		ByteArrayInputStream stream = null;
		try {
			byte[] bytes = new BASE64Decoder().decodeBuffer(newbase64); // 将字符串转换为byte数组
			byte[] buffer = new byte[1024];
			for (int i = 0; i < bytes.length; ++i) {
				if (bytes[i] < 0) {//调整异常数据
					bytes[i] += 256;
				}
			}
			stream = new ByteArrayInputStream(bytes);  
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return stream;
	}
}
