package com.haojii.generic.table.mapper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyAccessorFactory;

import com.haojii.generic.table.model.cells.TableCell;

/**
 * the engine of mapping a database/domain model bean to front-end model bean(TabelCell)
 * 
 * @author hao
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
			//value = BeanUtils.getProperty(object, fieldName);
			BeanWrapper wrapper = PropertyAccessorFactory.forBeanPropertyAccess(object);
			value = wrapper.getPropertyValue(fieldName).toString();
		} catch (BeansException e) {
			// eat it
		}
		
		return value;
	}
}
