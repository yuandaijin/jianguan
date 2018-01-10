package com.huatuo.clinics.cms.response;

import java.util.List;

import com.huatuo.common.BaseResponse;
import com.huatuo.db.bean.ImArticleChannel;
import com.huatuo.db.bean.ImArticleColumn;
import com.huatuo.db.bean.ImArticleContent;

public class ImArticleContentResponse extends BaseResponse{
	private static final long serialVersionUID = 1L;
	private ImArticleContent content;
	//栏目列表
	private List<ImArticleColumn> columns;
	//频道列表
	private List<ImArticleChannel> channels;
	//咨询列表
	private List<ImArticleContent> contents;
	public List<ImArticleColumn> getColumns() {
		return columns;
	}
	public void setColumns(List<ImArticleColumn> columns) {
		this.columns = columns;
	}
	public List<ImArticleChannel> getChannels() {
		return channels;
	}
	public void setChannels(List<ImArticleChannel> channels) {
		this.channels = channels;
	}
	public List<ImArticleContent> getContents() {
		return contents;
	}
	public void setContents(List<ImArticleContent> contents) {
		this.contents = contents;
	}
	public ImArticleContent getContent() {
		return content;
	}
	public void setContent(ImArticleContent content) {
		this.content = content;
	}
	
}
