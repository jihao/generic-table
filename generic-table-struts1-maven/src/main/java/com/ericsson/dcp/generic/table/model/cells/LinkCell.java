package com.ericsson.dcp.generic.table.model.cells;

import com.ericsson.dcp.generic.table.model.Link;

/**
 *&lt;a href="/edcp-portal/EnterpriseViewAction.do?id=7&amp;method=view">Super Cool&lt;/a>
 * 
 * @author ehaojii
 *
 */
public class LinkCell extends TableCell {

	private Link link = new Link();

	public LinkCell(Link link) {
		super();
		this.link = link;
	}

	public Link getLink() {
		return link;
	}

	public void setLink(Link link) {
		this.link = link;
	}

}
