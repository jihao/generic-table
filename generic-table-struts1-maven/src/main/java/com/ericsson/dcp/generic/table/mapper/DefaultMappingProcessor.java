package com.ericsson.dcp.generic.table.mapper;

import com.ericsson.dcp.generic.table.model.cells.TableCell;

/**
 * processor for generate cell which only shows text
 * 
 * @author ehaojii
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
