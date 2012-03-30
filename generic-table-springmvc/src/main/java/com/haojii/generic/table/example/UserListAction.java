package com.haojii.generic.table.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.haojii.generic.table.action.UniformTableAction;
import com.haojii.generic.table.action.UniformTableForm;
import com.haojii.generic.table.mapper.CheckboxCellMappingProcessor;
import com.haojii.generic.table.mapper.DefaultMappingProcessor;
import com.haojii.generic.table.mapper.DirectMappingProcessor;
import com.haojii.generic.table.mapper.IMappingProcessor;
import com.haojii.generic.table.mapper.IconLinksCellMappingProcessor;
import com.haojii.generic.table.mapper.LinkCellMappingProcessor;
import com.haojii.generic.table.model.Link;
import com.haojii.generic.table.model.cells.HeaderCell;
import com.haojii.generic.table.model.cells.IconLinksCell;
import com.haojii.generic.table.model.cells.LinkCell;
import com.haojii.generic.table.model.cells.TableCell;

@Controller
public class UserListAction extends UniformTableAction {
	private static final String REQUEST_PATH = "/users";

	@Override
	@RequestMapping(value = REQUEST_PATH, method = RequestMethod.GET)
	public String execute(UniformTableForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return super.execute(form, request, response);
	}
	@Override
	protected String getNextForwardName(){
		return "/user/list";
	}
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
		iconLinksCell.getIconLinkList().add(new Link(contextPath , "/user/edit", requiredParamMap, "btn_edit"));
		iconLinksCell.getIconLinkList().add(new Link("btn_delete","deleteUser(event, this);"));
		mappingProcessorList.add(new IconLinksCellMappingProcessor(iconLinksCell));
		
		Map<String,String> viewParamMap = new HashMap<String, String>();
		viewParamMap.put("id", null);
		LinkCell linkCell = new LinkCell(new Link(contextPath, "/user/view", viewParamMap));
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
		Boolean ascending = form.isSortOrderAscending();
		if (ascending == null) {
			ascending = true;
		}
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
					//String value = BeanUtils.getProperty(user, field);
					String value = (String)PropertyAccessorFactory.forBeanPropertyAccess(user).getPropertyValue(field);
					
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
