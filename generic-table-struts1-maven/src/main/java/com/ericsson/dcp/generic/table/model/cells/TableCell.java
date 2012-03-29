package com.ericsson.dcp.generic.table.model.cells;


/**
 * the basic table cell
 * 
 * <br>
 * 
 * &lt;td>&lt;div>&lt;a href="/edcp-portal/EnterpriseViewAction.do?id=6&amp;method=view">Game&lt;/a>&lt;/div>&lt;/td>
 * 
 * 
 * @author ehaojii
 *
 */
public class TableCell {
	
	public TableCell() {
	}
	
	public TableCell(String html) {
		this.html = html;
	}
	
	public TableCell(String html, boolean escape) {
		this.html = html;
		this.escape = escape;
	}

	private String html;
	private boolean escape = false;

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public boolean isEscape() {
		return escape;
	}

	public void setEscape(boolean escape) {
		this.escape = escape;
	}
	
	
}
