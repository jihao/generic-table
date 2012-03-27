package com.ericsson.dcp.generic.table.mapper;

import com.ericsson.dcp.generic.table.model.cells.CheckboxCell;
import com.ericsson.dcp.generic.table.model.cells.TableCell;

/**
 * processor for generate cell which shows a checkbox
 * 
 * @author ehaojii
 *
 */
public class CheckboxCellMappingProcessor implements IMappingProcessor {
	private String fieldName;
	
	public CheckboxCellMappingProcessor(String fieldName) {
		super();
		this.fieldName = fieldName;
	}

	@Override
	public TableCell process(Object dbModelBean) {
		String value = TableCellMapper.getProperty(dbModelBean, fieldName);
		return new CheckboxCell(value);
	}
}
