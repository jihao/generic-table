package com.ericsson.dcp.generic.table.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts.action.ActionForm;

/**
 * the action form for DCPTableAction
 * 
 * @author ehaojii
 *
 */
public class UniformTableForm extends ActionForm { 

	/**
	 * 
	 */
	private static final long serialVersionUID = -414078779785480356L;
	
	/**
	 * how it works:
	 * <a href="http://struts.apache.org/1.3.10/userGuide/building_controller.html#map_action_form_classes">
	 * http://struts.apache.org/1.3.10/userGuide/building_controller.html#map_action_form_classes</a>
	 */
	private final Map<String,Object> filterParamsMap = new HashMap<String,Object>();
	
	/**
     * Name of the column currently sorted.
     */
    private String sortedColumnName;

    /**
     * sort order = ascending?
     */
    private boolean sortOrderAscending = true;
    
    /**
     * the page offset
     */
    private int page;
    
	
    public void setFilter(String key, Object value) {
        filterParamsMap.put(key, value);
    }

    public Object getFilter(String key) {
        return filterParamsMap.get(key);
    }

	public String getSortedColumnName() {
		return sortedColumnName;
	}

	public void setSortedColumnName(String sortedColumnName) {
		this.sortedColumnName = sortedColumnName;
	}

	public boolean isSortOrderAscending() {
		return sortOrderAscending;
	}

	public void setSortOrderAscending(boolean sortOrderAscending) {
		this.sortOrderAscending = sortOrderAscending;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public Map<String, Object> getFilterParamsMap() {
		return filterParamsMap;
	}


}
