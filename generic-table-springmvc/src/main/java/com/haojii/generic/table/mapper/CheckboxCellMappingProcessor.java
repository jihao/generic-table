package com.haojii.generic.table.mapper;

import com.haojii.generic.table.model.cells.CheckboxCell;
import com.haojii.generic.table.model.cells.TableCell;

/**
 * processor for generate cell which shows a checkbox
 * 
 * @author hao
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
