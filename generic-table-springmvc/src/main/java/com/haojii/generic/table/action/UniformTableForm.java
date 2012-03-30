package com.haojii.generic.table.action;

import java.util.HashMap;
import java.util.Map;

/**
 * the action form for DCPTableAction
 * 
 * @author hao
 *
 */
public class UniformTableForm  { 

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
    private Boolean sortOrderAscending = true;
    
    /**
     * the page offset
     */
    private Integer page = 0;
    
	
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

	public Boolean isSortOrderAscending() {
		return sortOrderAscending;
	}

	public void setSortOrderAscending(Boolean sortOrderAscending) {
		this.sortOrderAscending = sortOrderAscending;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Map<String, Object> getFilterParamsMap() {
		return filterParamsMap;
	}


}
