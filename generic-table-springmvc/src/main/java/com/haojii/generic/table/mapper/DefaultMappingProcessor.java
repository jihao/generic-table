package com.haojii.generic.table.mapper;

import com.haojii.generic.table.model.cells.TableCell;

/**
 * processor for generate cell which only shows text
 * 
 * @author hao
 *
 */
public class DefaultMappingProcessor implements IMappingProcessor {
	private String fieldName;
	
	/**
	 * 
	 * @param fieldName - the fieldName of the property to be extracted
	 */
	public DefaultMappingProcessor(String fieldName) {
		super();
		this.fieldName = fieldName;
	}

	@Override
	public TableCell process(Object dbModelBean) {
		String value = TableCellMapper.getProperty(dbModelBean, fieldName);
		return new TableCell(value);
	}
}
