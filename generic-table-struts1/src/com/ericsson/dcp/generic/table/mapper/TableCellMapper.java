package com.ericsson.dcp.generic.table.mapper;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.ericsson.dcp.generic.table.model.cells.TableCell;

/**
 * the engine of mapping a database/domain model bean to front-end model bean(TabelCell)
 * 
 * @author ehaojii
 *
 */
public class TableCellMapper {
	/**
	 * fetch the data from database/domain model bean, map it to front-end model bean(TabelCell),
	 * and the mapping was done by a list of MappingProcessors 
	 * @param dbModelBean
	 * @param mappingProcessorList
	 * @return
	 */
	public static final List<TableCell> process(Object dbModelBean, List<IMappingProcessor> mappingProcessorList) {
		List<TableCell> tableCellList = new ArrayList<TableCell>();
		
		for (Iterator<IMappingProcessor> iterator = mappingProcessorList.iterator(); iterator.hasNext();) {
			IMappingProcessor processor = iterator.next();
			TableCell tc = processor.process(dbModelBean);
			
			tableCellList.add(tc);
		}
		
		return tableCellList;
	}
	
	/**
	 * Shortcut method of using reflection to get the property of the object
	 * <br>
	 *  {@link BeanUtils #getProperty(Object bean, String name) }
	 * 
	 * @param object - Bean whose property is to be extracted
	 * @param fieldName - Possibly indexed and/or nested name of the property to be extracted
	 * @return The property's value, converted to a String <br> or empty string if the field does not exist
	 * 
	 * 
	 */
	public static final String getProperty(Object object, String fieldName) {
		String value = "";
		try {
			value = BeanUtils.getProperty(object, fieldName);
		} catch (IllegalAccessException e) {
			//e.printStackTrace();
		} catch (InvocationTargetException e) {
			//e.printStackTrace();
		} catch (NoSuchMethodException e) {
			//e.printStackTrace();
		}
		return value;
	}
}
