package com.ericsson.dcp.generic.table.mapper;

import com.ericsson.dcp.generic.table.model.cells.TableCell;

/**
 * the mapping/process interface
 * 
 * @author ehaojii
 * 
 */
public interface IMappingProcessor {

	public abstract TableCell process(Object dbModelBean);

}