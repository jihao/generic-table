package com.ericsson.dcp.generic.table.model.cells;


/**
 * the header cell
 * 
 * <br>
 * &lt;th nowrap="" class="header headerSortUp">&lt;span class="sort_indicator">&nbsp;&lt;/span>Activated Devices&lt;/th>
 * 
 * @author ehaojii
 *
 */
public class HeaderCell extends TableCell {
	
	
	public HeaderCell(String fieldName, String columnNameRSBundle, String columnName, boolean sortable) {
		super();
		this.fieldName = fieldName;
		this.columnNameRSBundle = columnNameRSBundle;
		this.columnName = columnName;
		this.sortable = sortable;
		this.filterable = true;
	}

	public HeaderCell(String fieldName, String columnNameRSBundle, String columnName, boolean sortable,
			boolean filterable) {
		super();
		this.fieldName = fieldName;
		this.columnNameRSBundle = columnNameRSBundle;
		this.columnName = columnName;
		this.sortable = sortable;
		this.filterable = filterable;
	}

	/**
	 * the field name in db model bean which is going to be mapped
	 */
	private String fieldName;
	
	/**
	 * resource bundle file that holds the columnName
	 */
	private String columnNameRSBundle;
	/**
	 * column name key in resource file
	 */
	private String columnName;
	
	/**
	 * allow if this column sortable
	 */
	private boolean sortable = false;
	
	/**
	 * allow if this column filter able
	 */
	private boolean filterable = false;
	
	/**
	 * sort order ascending
	 */
	private boolean sortOrderAscending = true;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public boolean isSortable() {
		return sortable;
	}

	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}

	public boolean isSortOrderAscending() {
		return sortOrderAscending;
	}

	public void setSortOrderAscending(boolean sortOrderAscending) {
		this.sortOrderAscending = sortOrderAscending;
	}

	public String getColumnNameRSBundle() {
		return columnNameRSBundle;
	}

	public void setColumnNameRSBundle(String columnNameRSBundle) {
		this.columnNameRSBundle = columnNameRSBundle;
	}

	public boolean isFilterable() {
		return filterable;
	}

	public void setFilterable(boolean filterable) {
		this.filterable = filterable;
	}
}
