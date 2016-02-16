package com.allegiant.search;
/**
 * A SearchPredicate is a condition on a search, such as "firstName must contain the string "bob"", or "created date after 2015"
 * @author Owner
 *
 */
public class SearchPredicate {

	private String name;
	private String value;
	PredicateOperator operator;
	PredicateType type;
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * @return the operator
	 */
	public PredicateOperator getOperator() {
		return operator;
	}
	/**
	 * @param operator the operator to set
	 */
	public void setOperator(PredicateOperator operator) {
		this.operator = operator;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the type
	 */
	public PredicateType getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(PredicateType type) {
		this.type = type;
	}
	
}
