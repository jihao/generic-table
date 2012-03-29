package com.ericsson.dcp.generic.table.model;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.jsp.PageContext;

import com.ericsson.dcp.generic.table.model.cells.IconLinksCell;
import com.ericsson.dcp.generic.table.model.cells.TableCell;
import com.ericsson.dcp.generic.table.model.cells.TableRow;

/**
 * TableModel represents the generic list(table) 
 * <ul>
 * <li>the table takes care of generic filter,sort,paging function</li>
 * <li>the table row have configurable checkbox, icon buttons with link or javascript attached</li>
 * </ul>
 * 
 * <p><b>note:</b> the aim of this generic TableModel was to simplify most of the lists in edcp-portal project, if you need a list in edcp-portal, this is right to go<br><br>
 *  
 * <b>limitation:</b> it is not so flexible at the moment to handle other table/list requirement (eg: like extends a cell with on/off button)
 * <br>
 * <b>but:</b> you can still define more MappingProcessors to handle such issue 
 * <br>
 * 
 * </p>
 * 
 * @author ehaojii
 * 
 */
public class TableModel {

	/**
     * logger.
     */
    @SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger("TableModel");
    /**
     * list of HeaderCell.
     */
    private List<TableCell> headerCellList = new ArrayList<TableCell>();

    /**
     * full list (contains Row objects).
     */
    private List<TableRow> rowListFull = new ArrayList<TableRow>();

    /**
     * list of data to be displayed in page.
     */
    private List<TableRow> rowListPage = new ArrayList<TableRow>();

    /**
     * Name of the column currently sorted.
     */
    private String sortedColumnName;

    /**
     * sort order = ascending?
     */
    private boolean sortOrderAscending = true;

    /**
     * sort full List? (false sort only displayed page).
     */
    private boolean sortFullTable = true;

    /**
     * index of the sorted column (-1 if the table is not sorted).
     */
    private int sortedColumn = -1;

    /**
     * Starting offset for elements in the viewable list.
     */
    private int pageOffset;
    
    /**
     * total pages of list
     */
    private int pages;

    /**
     * Are we sorting locally? (Default True)
     */
    private boolean localSort = true;

    /**
     * Table footer.
     */
    private String footer;

    /**
     * Jsp page context.
     */
    private PageContext pageContext;


    /**
     * Uses post for links.
     */
    private String action;
    
    /**
     * show checkbox column
     */
    private boolean showCheckBoxColumn = false;
    
    /**
     * the field represents the checkbox column's value
     */
    private String checkBoxColumnParamName;
    
    /**
     * show filter column
     */
    private boolean showFilterColumn = false;
    
    /**
     * the icon links of the filter column (edit,delete icon and links)
     */
    private IconLinksCell iconLinksCell;
    
    

	

	public String getSortedColumnName() {
		return sortedColumnName;
	}

	public void setSortedColumnName(String sortedColumnName) {
		this.sortedColumnName = sortedColumnName;
	}

	public boolean isSortOrderAscending() {
		return sortOrderAscending;
	}

	public void setSortOrderAscending(boolean sortOrderAscending) {
		this.sortOrderAscending = sortOrderAscending;
	}

	public boolean isSortFullTable() {
		return sortFullTable;
	}

	public void setSortFullTable(boolean sortFullTable) {
		this.sortFullTable = sortFullTable;
	}

	public int getSortedColumn() {
		return sortedColumn;
	}

	public void setSortedColumn(int sortedColumn) {
		this.sortedColumn = sortedColumn;
	}

	public int getPageOffset() {
		return pageOffset;
	}

	public void setPageOffset(int pageOffset) {
		this.pageOffset = pageOffset;
	}

	public boolean isLocalSort() {
		return localSort;
	}

	public void setLocalSort(boolean localSort) {
		this.localSort = localSort;
	}

	public String getFooter() {
		return footer;
	}

	public void setFooter(String footer) {
		this.footer = footer;
	}

	public PageContext getPageContext() {
		return pageContext;
	}

	public void setPageContext(PageContext pageContext) {
		this.pageContext = pageContext;
	}

	public boolean isShowCheckBoxColumn() {
		return showCheckBoxColumn;
	}

	public void setShowCheckBoxColumn(boolean showCheckBoxColumn) {
		this.showCheckBoxColumn = showCheckBoxColumn;
	}

	public String getCheckBoxColumnParamName() {
		return checkBoxColumnParamName;
	}

	public void setCheckBoxColumnParamName(String checkBoxColumnParamName) {
		this.checkBoxColumnParamName = checkBoxColumnParamName;
	}

	public boolean isShowFilterColumn() {
		return showFilterColumn;
	}

	public void setShowFilterColumn(boolean showFilterColumn) {
		this.showFilterColumn = showFilterColumn;
	}

	public List<TableCell> getHeaderCellList() {
		return headerCellList;
	}

	public void setHeaderCellList(List<TableCell> headerCellList) {
		this.headerCellList = headerCellList;
	}

	public List<TableRow> getRowListFull() {
		return rowListFull;
	}

	public void setRowListFull(List<TableRow> rowListFull) {
		this.rowListFull = rowListFull;
	}

	public List<TableRow> getRowListPage() {
		return rowListPage;
	}

	public void setRowListPage(List<TableRow> rowListPage) {
		this.rowListPage = rowListPage;
	}

	public IconLinksCell getIconLinksCell() {
		return iconLinksCell;
	}

	public void setIconLinksCell(IconLinksCell iconLinksCell) {
		this.iconLinksCell = iconLinksCell;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}
}
