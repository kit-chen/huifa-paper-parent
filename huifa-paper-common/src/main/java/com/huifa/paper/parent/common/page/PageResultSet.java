package com.huifa.paper.parent.common.page;

import java.util.List;

public class PageResultSet {

	private List list; // 当前页的数据信息
	private Page page; // 当前页的信息

	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
}