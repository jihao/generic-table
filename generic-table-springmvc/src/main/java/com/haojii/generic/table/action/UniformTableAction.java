package com.haojii.generic.table.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.haojii.generic.table.mapper.IMappingProcessor;
import com.haojii.generic.table.mapper.TableCellMapper;
import com.haojii.generic.table.model.TableModel;
import com.haojii.generic.table.model.cells.TableCell;
import com.haojii.generic.table.model.cells.TableRow;

/**
 * the abstract base action class for generic table/list
 * 
 * @author hao
 *
 */
public abstract class UniformTableAction  {
	
	private static final String NEXT_PAGE_DEFAULT = "list";
	protected static final int PAGE_SIZE_DEFAULT = 20;
	protected static String contextPath;

	@RequestMapping(value = NEXT_PAGE_DEFAULT, method = RequestMethod.GET)
	public String execute(UniformTableForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		contextPath = request.getContextPath();
		
		doPreExecute(form, request, response);
		
		String view = doExecute(form, request, response);
		
		doAfterExecute(form, request, response);
		
		return view;
	}

	/**
	 * sub class can override this method if need 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	protected void doAfterExecute(UniformTableForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
	}
	/**
	 * sub class can override this method if need 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	protected void doPreExecute(UniformTableForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
	}
	
	/**
	 * normally sub class do not need to override this method 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	protected String doExecute(UniformTableForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int pageNumber = (form.getPage()<=0) ? 1 : (form.getPage());
		
		TableModel tableModel = new TableModel();
		List<? extends Object> resultList = getResultList(form, request, pageNumber);
		if(resultList != null){
			
			for(int i = 0; i < resultList.size(); i++){
				List<IMappingProcessor> mappingProcessorList = getTableMappingProcessorList(form, request);
				
				List<TableCell> tableCellList = TableCellMapper.process(resultList.get(i), mappingProcessorList);
				TableRow row = new TableRow();
				row.setTableCellList(tableCellList);
				row.setIndex(i);
				//tableModel.getRowListFull().add(row);
				tableModel.getRowListPage().add(row);
			}
		}	
		
		tableModel.setHeaderCellList(getTableHeader());
		tableModel.setShowFilterColumn(showCheckbox());
		tableModel.setShowCheckBoxColumn(showFilter());
		tableModel.setPageOffset(pageNumber);
		tableModel.setPages(getPages(form, request));
		request.setAttribute("tableModel", tableModel);

		return getNextForwardName();
		
	}
	
	/**
	 *
	 * @return total pages
	 */
	private int getPages(UniformTableForm form, HttpServletRequest request) throws Exception {
		int count = getTotalCount(form, request);
		int pages = (int)((count / getPageSize()) + ((count % getPageSize() > 0) ? 1 : 0));
		return pages;
	}

	/**
	 * Get the total count of your table/list items <br>
	 * <b>Attention</b> when calculate the total count, please don't ignore the filter condition in UniformTableForm
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	protected abstract int getTotalCount(UniformTableForm form, HttpServletRequest request) throws Exception;
	/**
	 * Get the table header list <br><br>
	 * check one example implementation {@link com.haojii.generic.table.example.UserListAction#getTableHeader()}
	 * @return
	 */
	protected abstract List<TableCell> getTableHeader();
	/**
	 * get the MappingProcessor list which defines rules to show each column
	 * 
	 * @param form
	 * @param request
	 * @return
	 */
	protected abstract List<IMappingProcessor> getTableMappingProcessorList(UniformTableForm form, HttpServletRequest request);

	/**
	 * Get the items list <br>
	 * you can get sort & filter & page conditions form the UniformTableForm
	 * @param form
	 * @param request
	 * @param pageNumber
	 * @return
	 * @throws Exception
	 */
	protected abstract List<? extends Object> getResultList(UniformTableForm form, HttpServletRequest request,int pageNumber) throws Exception;
	
	protected boolean showCheckbox() {
		return true;
	}
	
	protected boolean showFilter() {
		return true;
	}

	protected String getNextForwardName(){
		return NEXT_PAGE_DEFAULT;
	}
	
	protected int getPageSize(){
		return PAGE_SIZE_DEFAULT;
	}

	

}
