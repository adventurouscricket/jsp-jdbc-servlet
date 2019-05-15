package com.mrhenry.paging;

import com.mrhenry.sort.Sorter;

public class PageRequest implements IPageble {

	private Integer page;
	private Integer maxPageItem;
	private Sorter sorter;

	public PageRequest(Integer page, Integer maxPageItem, Sorter sorter) {
		this.page = page;
		this.maxPageItem = maxPageItem;
		this.sorter = sorter;
	}

	public PageRequest() {
		super();
	}

	@Override
	public Integer getPage() {
		// TODO Auto-generated method stub
		return this.page;
	}

	@Override
	public Integer getOffset() {
		if (this.page != null && this.maxPageItem != null) {
			return (this.page - 1) * (this.maxPageItem);
		}
		return null;
	}

	@Override
	public Integer getLimit() {
		// TODO Auto-generated method stub
		return this.maxPageItem;
	}

	@Override
	public Sorter getSorter() {
		if (this.sorter != null) {
			return sorter;
		}
		return null;
	}
}
