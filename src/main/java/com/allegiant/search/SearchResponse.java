package com.allegiant.search;

import java.util.List;

public class SearchResponse<T> {

	public List<T> results;
	public int numPages;
	public int pageNumber;
	
	public SearchResponse(List<T> inResults, int inNumPages){
		results = inResults;
		numPages = inNumPages;
		pageNumber = 1;
	}

	public int getNumPages(){return numPages;}
	public List<T> getResults(){return results;}

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
	
}
