package com.allegiant.search;

public class Sort {

	private String columnName;
	private SortType sortType;
	/**
	 * @return the columnName
	 */
	public String getColumnName() {
		return columnName;
	}
	/**
	 * @param columnName the columnName to set
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	/**
	 * @return the sortType
	 */
	public SortType getSortType() {
		return sortType;
	}
	/**
	 * @param sortType the sortType to set
	 */
	public void setSortType(SortType sortType) {
		this.sortType = sortType;
	}
	
}
