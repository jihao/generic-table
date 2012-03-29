package com.ericsson.dcp.generic.table.model.cells;

import java.util.ArrayList;
import java.util.List;

import com.ericsson.dcp.generic.table.model.Link;


/**
 * the  table cell that holds edit | delete icon links
 * 
 * <br>
 * 
 * &lt;td><br>
 *	&nbsp;&lt;a class="btn_edit" href="/edcp-portal/EnterpriseEditAction.do?id=8&amp;method=toEdit_general">&nbsp;&lt;/a><br>
 *	&nbsp;&lt;a onclick="delete_item(event,this,8,'Enterprise');" class="btn_delete" href="#">&nbsp;&lt;/a><br>
 * &lt;/td><br>
 * 
 * 
 * @author ehaojii
 *
 */
public class IconLinksCell extends TableCell {
	
	private List<Link> iconLinkList = new ArrayList<Link>();

	public List<Link> getIconLinkList() {
		return iconLinkList;
	}

	public void setIconLinkList(List<Link> iconLinkList) {
		this.iconLinkList = iconLinkList;
	}
	
}
