package com.huatuo.clinics.cms.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/*-config.xml",
		"file:src/main/webapp/WEB-INF/dispatcher-servlet.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class test {
//	@Resource
//	private ClinicResidentsService  clinicResidentsService;
	
//	@Test
	public void test() {
//		String code="'00068','00069','00070','00071','00072','00073','00074','00075','00076','00077'";
//		List<reportResultDTO> s=clinicResidentsService.queryFirstTenType("33162", "2016", "1", "12", "year", code);
//		for (reportResultDTO reportResultDTO : s) {
//			System.out.println(reportResultDTO);
//		}
	}
}
