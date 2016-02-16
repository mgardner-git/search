package com.allegiant.search;

import java.util.List;

public class SearchRequest {

	private int pageNumber;
	private int itemsPerPage;
	private List<SearchPredicate> predicates;
	
	/**
	 * @return the pageNumber
	 */
	public int getPageNumber() {
		return pageNumber;
	}
	/**
	 * @param pageNumber the pageNumber to set
	 */
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	/**
	 * @return the itemsPerPage
	 */
	public int getItemsPerPage() {
		return itemsPerPage;
	}
	/**
	 * @param itemsPerPage the itemsPerPage to set
	 */
	public void setItemsPerPage(int itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}
	/**
	 * @return the predicates
	 */
	public List<SearchPredicate> getPredicates() {
		return predicates;
	}
	/**
	 * @param predicates the predicates to set
	 */
	public void setPredicates(List<SearchPredicate> predicates) {
		this.predicates = predicates;
	}

	
}
