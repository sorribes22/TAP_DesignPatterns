package com.tap.query;

import java.util.Map;

public class StringComparison implements IQuery<Map<String, Object>> {

	private String attribute;

	private char operator;

	private String value;

	public StringComparison(String attribute, char operator, String value) {
		this.attribute = attribute;
		this.setOperator(operator);
		this.value = value;
	}

	private void setOperator (char operator) {
		// TODO raise exception if incorrect operator provided
		this.operator = operator;
	}

	@Override
	public boolean fulfill(Map<String, Object> item) {
		// TODO ASK podem seleccionar el caràcter de forma més dinàmica? p.e. item.get(this.attribute).compareWith(this.operator, this.value)
		// TODO ask on podriem definir que Map<String, Object extends Comparable<Object>>? O hem d'esperar a implementar composite?
		switch (this.operator) {
			case '=':
				return item.get(this.attribute).equals(this.value);
//			case '<':
//				return item.get(this.attribute).compareTo(value);
		}

//		return item.get(this.attribute) this.operator this.value;

		return false;
	}
}
