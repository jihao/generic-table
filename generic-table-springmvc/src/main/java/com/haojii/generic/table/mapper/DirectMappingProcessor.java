package com.haojii.generic.table.mapper;

import com.haojii.generic.table.model.cells.TableCell;

/**
 * processor for generate cell which directly shows the html value passed in
 * 
 * @author ewenyee, hao
 *
 */
public class DirectMappingProcessor implements IMappingProcessor{

	private String html;
	
	/**
	 * 
	 * @param html - the html to be showing directly
	 */
	public DirectMappingProcessor(String html) {
		super();
		this.html = html;
	}
	
	@Override
	public TableCell process(Object obj) {
		return new TableCell(html);
	}

}
