package com.huatuo.clinics.cms.test;


import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.huatuo.clinics.cms.controller.OrderCheckController;
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/*-config.xml",
		"file:src/main/webapp/WEB-INF/dispatcher-servlet.xml" })
public class SsDicIcd10Test {
	@Resource
	private OrderCheckController is;
	
	@Test
	public void testBean() {
//		OrderCheckResponse rep=is.queryOrderDtl("9c7adb5f-65e5-41b8-8bb5-000e8f65ee6a");
//		System.out.println(rep);
	}
}
