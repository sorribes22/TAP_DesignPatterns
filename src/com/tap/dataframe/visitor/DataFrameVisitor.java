package com.tap.dataframe.visitor;

import com.tap.dataframe.DataFrame;
import com.tap.dataframe.StringDataFrame;
import com.tap.dataframe.impl.DirectoryDataFrame;

public abstract class DataFrameVisitor {


	/**
	 * Label to apply the current operation
	 */
	protected String labelToApply = "Code";

	/**
	 * Visit a dataFrame that have implemented "accept" method and do the specific operation according to the Visitor
	 *
	 * @param df dataFrame to visit
	 */
	public void visit(DataFrame df) {
		if (df.getContent().containsKey(labelToApply)) {
			for (String value : df.getContent().get(labelToApply)) {
				try {
					calculate(Double.parseDouble(value));
				} catch (NumberFormatException e) {
					System.out.println("NO NUMBER FORMAT: " + value);
				}
			}
		}else if(!(df instanceof DirectoryDataFrame)) {
			System.out.println("LABEL " + labelToApply + " NOT FOUND IN " + df.getContent().keySet());
		}
	}


	/**
	 * Do the specific operation depending on the implementation
	 *
	 * @param actual number to operate
	 */
	public abstract void calculate(double actual);


	/**
	 * Get the result of the visitor
	 *
	 * @return result
	 */
	public abstract double getResult();

	/**
	 * Get the label we are applying
	 *
	 * @return label
	 */
	public String getLabelToApply(){
		return labelToApply;
	}


	/**
	 * Set a new label to apply the visitor
	 *
	 * @param label
	 */
	public void setLabelToApply(String label){
		labelToApply = label;
	}

}
