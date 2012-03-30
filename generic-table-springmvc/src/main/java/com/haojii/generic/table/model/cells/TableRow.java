package com.haojii.generic.table.model.cells;

import java.util.ArrayList;
import java.util.List;

public class TableRow {

	private int index;
	private List<TableCell> tableCellList = new ArrayList<TableCell>();


	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public List<TableCell> getTableCellList() {
		return tableCellList;
	}

	public void setTableCellList(List<TableCell> tableCellList) {
		this.tableCellList = tableCellList;
	}}
