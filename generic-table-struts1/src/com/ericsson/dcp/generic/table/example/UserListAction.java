package com.ericsson.dcp.generic.table.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import com.ericsson.dcp.generic.table.action.UniformTableAction;
import com.ericsson.dcp.generic.table.action.UniformTableForm;
import com.ericsson.dcp.generic.table.mapper.CheckboxCellMappingProcessor;
import com.ericsson.dcp.generic.table.mapper.DefaultMappingProcessor;
import com.ericsson.dcp.generic.table.mapper.DirectMappingProcessor;
import com.ericsson.dcp.generic.table.mapper.IMappingProcessor;
import com.ericsson.dcp.generic.table.mapper.IconLinksCellMappingProcessor;
import com.ericsson.dcp.generic.table.mapper.LinkCellMappingProcessor;
import com.ericsson.dcp.generic.table.model.Link;
import com.ericsson.dcp.generic.table.model.cells.HeaderCell;
import com.ericsson.dcp.generic.table.model.cells.IconLinksCell;
import com.ericsson.dcp.generic.table.model.cells.LinkCell;
import com.ericsson.dcp.generic.table.model.cells.TableCell;

public class UserListAction extends UniformTableAction {

	private static List<User> userList = new ArrayList<User>();
	private static String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	static {
		for (int i = 0; i < 50; i++) {
			Random seed = new Random();
			int next = seed.nextInt(characters.length());
			User user = new User("username_"+characters.charAt(next), "password_"+characters.charAt(next), "phone_"+characters.charAt(next), "email_"+characters.charAt(next)+"@email.com");
			user.setId(i);
			userList.add(user);
		}
	}
	@Override
	protected int getTotalCount(UniformTableForm form, HttpServletRequest request) throws Exception {
		Map<String, Object>  filterParamsMap = form.getFilterParamsMap();
		List<User> list = userList;
		if(filterParamsMap.size()>0) {
			list = filter(filterParamsMap);
		}
				
		return list.size();
	}

	@Override
	protected List<TableCell> getTableHeader() {
		List<TableCell> tableHeaderList = new ArrayList<TableCell>();
		tableHeaderList.add(new HeaderCell("id", "UserBundle","user.label.username",true, true));
		//tableHeaderList.add(new HeaderCell("name", "UserBundle","user.label.username",true, true));
		tableHeaderList.add(new HeaderCell("password", "UserBundle", "user.label.password",false, false));
		tableHeaderList.add(new HeaderCell("phone", "UserBundle", "user.label.phone",true, true));
		tableHeaderList.add(new HeaderCell("email", "UserBundle", "user.label.email",true, true));
		return tableHeaderList;
	}

	@Override
	protected List<IMappingProcessor> getTableMappingProcessorList(UniformTableForm form, HttpServletRequest request) {
		Map<String,String> requiredParamMap = new HashMap<String, String>();
		requiredParamMap.put("id", null);
		requiredParamMap.put("username", null);
		
		List<IMappingProcessor> mappingProcessorList = new ArrayList<IMappingProcessor>();
		mappingProcessorList.add(new CheckboxCellMappingProcessor("id"));
		
		IconLinksCell iconLinksCell = new IconLinksCell();
		iconLinksCell.getIconLinkList().add(new Link(contextPath , "/UserEditAction.do?method=edit", requiredParamMap, "btn_edit"));
		iconLinksCell.getIconLinkList().add(new Link("btn_delete","deleteUser(event, this);"));
		mappingProcessorList.add(new IconLinksCellMappingProcessor(iconLinksCell));
		
		Map<String,String> viewParamMap = new HashMap<String, String>();
		viewParamMap.put("id", null);
		LinkCell linkCell = new LinkCell(new Link(contextPath, "/UserViewAction.do?method=view", viewParamMap));
		mappingProcessorList.add(new LinkCellMappingProcessor("username", linkCell));
		mappingProcessorList.add(new DirectMappingProcessor("*****"));
		mappingProcessorList.add(new DefaultMappingProcessor("phone"));
		mappingProcessorList.add(new DefaultMappingProcessor("email"));
		return mappingProcessorList;
	}

	@Override
	protected List<? extends Object> getResultList(UniformTableForm form, HttpServletRequest request, int pageNumber)
			throws Exception {
		Map<String, Object>  filterParamsMap = form.getFilterParamsMap();
		List<User> list = userList;
		if(filterParamsMap.size()>0) {
			list = filter(filterParamsMap);
		}
		 
		String columnName = form.getSortedColumnName();
		boolean ascending = form.isSortOrderAscending();
		if(columnName!=null && !columnName.isEmpty()) {
			sort(list,columnName,ascending);
		}		
		
		int fromIndex = (pageNumber-1)*PAGE_SIZE_DEFAULT;
		int toIndex = (pageNumber*PAGE_SIZE_DEFAULT < list.size())?pageNumber*PAGE_SIZE_DEFAULT:list.size();
		return list.subList(fromIndex, toIndex);
	}
	
	/**
	 * Demonstrate in memory sort
	 * @param list
	 * @param columnName
	 * @param ascending
	 */
	private void sort(List<User> list, final String columnName, final boolean ascending) {
		Collections.sort(list, new UserComparator(columnName, ascending));		
	}

	/**
	 * Demonstrate in memory filter
	 * @param filterParamsMap
	 * @return
	 */
	private List<User> filter(Map<String, Object>  filterParamsMap)
	{
		List<User> list = new ArrayList<User>();
		for (User user : userList) {
			boolean match = true;
			Iterator<String> itr =  filterParamsMap.keySet().iterator();
			while(itr.hasNext())
			{
				String field = itr.next();
				String filterLikeValue = (String)filterParamsMap.get(field);
				try {
					String value = BeanUtils.getProperty(user, field);
					boolean result = value.contains(filterLikeValue);
					if(!result)
					{
						match = false;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			if(match) {
				list.add(user);
			}
		}
		return list;
	}

}
