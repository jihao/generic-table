package com.haojii.generic.table.mapper;

import com.haojii.generic.table.model.cells.TableCell;

/**
 * the mapping/process interface
 * 
 * @author hao
 * 
 */
public interface IMappingProcessor {

	public abstract TableCell process(Object dbModelBean);

}