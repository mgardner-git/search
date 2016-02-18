package com.allegiant.search;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * A SearchPredicate is a condition on a search, such as "firstName must contain the string "bob"", or "created date after 2015"
 * @author Owner
 *
 */
public class SearchPredicate {

	private String name;
	private String value;
	private PredicateOperator operator;	
	private PredicateType type;
	private String label;  //this attribute is necessary for congruence with the angular module, but is only visual, not used in any processing.
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
	 * 
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
	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}	
}
