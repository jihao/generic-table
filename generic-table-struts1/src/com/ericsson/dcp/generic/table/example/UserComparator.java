package com.ericsson.dcp.generic.table.example;

import java.util.Comparator;

import org.apache.commons.beanutils.BeanUtils;

/**
 * The comparator for {@link User} in order to implement in memory sort for user list
 * 
 * @author ehaojii
 *
 */
public class UserComparator implements Comparator<User> {
	private String columnName;
	private boolean ascending;
	private User userTest=new User();

	public UserComparator(String columnName, boolean ascending) {
		super();
		this.columnName = columnName;
		this.ascending = ascending;
	}

	@Override
	public int compare(User o1, User o2) {
		int result = 0;
		try {
			String val1 = BeanUtils.getProperty(o1, columnName);
			String val2 = BeanUtils.getProperty(o2, columnName);
			result = val1.compareTo(val2);
			result = result * (ascending ? -1 : 1);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return result;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public boolean isAscending() {
		return ascending;
	}

	public void setAscending(boolean ascending) {
		this.ascending = ascending;
	}

	public User getUserTest() {
		return userTest;
	}

	public void setUserTest(User userTest) {
		this.userTest = userTest;
	}

}
