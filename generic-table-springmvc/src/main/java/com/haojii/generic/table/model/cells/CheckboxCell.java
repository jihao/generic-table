package com.haojii.generic.table.model.cells;


/**
 * <br>
 * &lt;th class="ck_field">&lt;input type="checkbox" name="check_all" value="8" >&lt;/th>
 * 
 * @author hao
 *
 */
public class CheckboxCell extends TableCell {
	
	/**
	 * the checkbox value parameter
	 */
	private String value;

	public CheckboxCell(String value) {
		super();
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
