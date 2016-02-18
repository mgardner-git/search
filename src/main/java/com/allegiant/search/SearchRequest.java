package com.allegiant.search;

import java.util.ArrayList;
import java.util.List;

public class SearchRequest {

	private int pageNumber=1;
	private int itemsPerPage=10;
	private List<SearchPredicate> predicates;
	private List<Sort> sorts;
	
	public SearchRequest(){
		predicates = new ArrayList<SearchPredicate>();
		sorts = new ArrayList<Sort>();
	}
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
	/**
	 * @return the sorts
	 */
	public List<Sort> getSorts() {
		return sorts;
	}
	/**
	 * @param sorts the sorts to set
	 */
	public void setSorts(List<Sort> sorts) {
		this.sorts = sorts;
	}

	
}
