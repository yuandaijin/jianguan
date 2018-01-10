package com.huatuo.clinics.cms.db.bean;

/**
 * 报表公共db层bean
 * @author ydj
 *
 */
public class ReportResultDB {
	 private String qty; //数量
	    private String name;//名称
	    private String type;//类型
	    
	    
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getQty() {
			return qty;
		}
		public void setQty(String qty) {
			this.qty = qty;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
    
    
}