<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true"%>
<%@ page import="com.ericsson.dcp.generic.table.model.cells.HeaderCell"%>
<%@ page import="com.ericsson.dcp.generic.table.model.cells.IconLinksCell"%>
<%@ page import="com.ericsson.dcp.generic.table.model.cells.CheckboxCell"%>
<%@ page import="com.ericsson.dcp.generic.table.model.cells.TableCell"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<c:set var="ctx" value="${pageContext['request'].contextPath}"/>

<script type="text/javascript">
$(document).ready(function() {
	
	//init highlight for table
	$(".basicList tr").hover(function(){
		$(".basicList tr").removeClass("hovered");
		$(this).addClass("hovered");
	});
	$(".basicList tr").mouseleave(function(){
		$(".basicList tr").removeClass("hovered");
	});
	//assign feature for checkbox
	$(".basicList #check_all").click(function(){
		if ($(this).attr("checked") == "1"){
			$(".basicList .ck_field input").attr("checked","1");
		}else{
			$(".basicList .ck_field input").attr("checked","");
		}
	});
	$('.col_filter').click(function(event){
		event.preventDefault();
		if($(this).hasClass("expand")){
			$(this).parent().parent().find(".filter_row").hide();
			$(this).removeClass("expand");
		}else{
			$(this).parent().parent().find(".filter_row").show();
			$(this).addClass("expand");
		}
										
	});
	
	$('.header').click(function(event){
		event.preventDefault();
		var up = $(this).hasClass("headerSortUp");
		var down = $(this).hasClass("headerSortDown");
		if(!up && !down)
		{
			$(this).addClass("headerSortUp");
		} else if (up)
		{
			$(this).removeClass("headerSortUp");
			$(this).addClass("headerSortDown");
		} else if (down) 
		{
			$(this).removeClass("headerSortDown");
			$(this).addClass("headerSortUp");
		}
		
		
		var sortOrderAscending = $(this).hasClass("headerSortUp");
		var sortedColumnName = $(this).find('input:hidden').first().attr('value');
		
		$('form #sortOrderAscending').val(sortOrderAscending);
		$('form #sortedColumnName').val(sortedColumnName);
		
		document.forms.uniformTableForm.submit();
	});
	
	$('input.filter_field').keypress(function(event) {
		var $my_evt_target = $(this);
		if (event.which == '13') {
	     	event.preventDefault();

		    $('input.filter_field').each(function(){
		    	var fieldName =  $(this).attr('name');
		    	var value = $(this).val();
		    	$('form #filter_'+fieldName).val(value);
		    });
		    $('form #page').val(1);
		    
		    document.forms.uniformTableForm.submit();
		}
	});
	
	$('a.page_icon').click(function(event) {
		event.preventDefault();
		$('form #page').val($(this).attr('name'));
		
		document.forms.uniformTableForm.submit();
	});
	
	//document.forms.uniformTableForm.submit
});

</script>

<html:form method="GET">
	<html:hidden styleId="sortedColumnName" property="sortedColumnName" />
	<html:hidden styleId="sortOrderAscending" property="sortOrderAscending" />
	<html:hidden styleId="page" property="page" />
	
	<c:forEach var="header" items="${tableModel.headerCellList}"> 
		<% 
		TableCell tableCell = (TableCell) pageContext.getAttribute("header");
		if(tableCell instanceof HeaderCell) {
		%>
		<c:set var="fieldName" value="<%= ((HeaderCell)tableCell).getFieldName() %>"></c:set>
		<html:hidden styleId="filter_${fieldName}" property="filter(${fieldName})" />
		<% } %> 
	</c:forEach> 
</html:form>

<table class="basicList" cellpadding="0" cellspacing="0"> 
	<thead>
		<tr>
			<c:if test="${tableModel.showCheckBoxColumn }">
				<th class="ck_field"><input type="checkbox" id="check_all" name="check_all" /></th>
			</c:if>
			<c:if test="${tableModel.showFilterColumn }">
				<th class="col_filter" nowrap><a href="#" class="btn_filter">&nbsp;</a></th>
			</c:if>
			<c:forEach var="header" items="${tableModel.headerCellList}"> 
				<% 
				TableCell tableCell = (TableCell) pageContext.getAttribute("header");
				if(tableCell instanceof HeaderCell)
				{
					HeaderCell headerCell = ((HeaderCell)tableCell);
					String fieldName = headerCell.getFieldName();
					String columnName = headerCell.getColumnName();
					String bundle = headerCell.getColumnNameRSBundle();
					
					//TODO: allow sortable configuration for each column
					boolean sortable = headerCell.isSortable();
					pageContext.setAttribute("sortable",(sortable?"header ":" "));
					
					pageContext.setAttribute("fieldName",fieldName);
					pageContext.setAttribute("columnName",columnName);
					pageContext.setAttribute("bundle",bundle);
					//we are calculating sort order and sort class 
					String sortedColumnName = request.getParameter("sortedColumnName");
					if(fieldName.equals(sortedColumnName))
					{
						boolean result = Boolean.valueOf(request.getParameter("sortOrderAscending"));
						pageContext.setAttribute("sortClass",(result?"headerSortUp":"headerSortDown"));
					}
					else
					{
						pageContext.setAttribute("sortClass","");
					}									
				%>
				<th class="${sortable}  ${sortClass}" nowrap >
					<span class="sort_indicator">&nbsp;</span>
					<bean:message bundle="${bundle}" key="${columnName}"/>
					<input type="hidden" value="${fieldName}"/>
				</th>
				<%
				} 
				%> 
			</c:forEach> 
		</tr> 
		<c:if test="${tableModel.showFilterColumn }">
			<tr class="filter_row">
				<c:if test="${tableModel.showCheckBoxColumn }"> 
					<th>&nbsp;</th>
				</c:if>
				<th>&nbsp;</th>
				<c:forEach var="header" items="${tableModel.headerCellList}">
				<% 
				TableCell tableCell = (TableCell) pageContext.getAttribute("header");
				if(tableCell instanceof HeaderCell)
				{
					HeaderCell headerCell = ((HeaderCell)tableCell);
					boolean filterable = headerCell.isFilterable();
					String fieldName = headerCell.getFieldName();
					String value = request.getParameter("filter("+fieldName+")");
					pageContext.setAttribute("fieldName",fieldName);
					pageContext.setAttribute("value",value);
					pageContext.setAttribute("filterable",filterable);
				%>	
					 <th><input ${(filterable==false)?"readonly='readonly' disabled='disabled'":"" } type="text" id="filter_ind0" name="${fieldName}" value="${value}" class="filter_field"/></th>
				<%
				}
				%>	  
				</c:forEach>
			</tr>  
		</c:if>
	</thead> 
	<tbody> 
	
	<c:forEach var="row" items="${tableModel.rowListPage}" varStatus="status">
		<tr class="${(status.index%2==0)?'odd':'even' }"> 
			
			<c:forEach var="tableCell" items="${row.tableCellList}">
				<% 
				TableCell tableCell = (TableCell) pageContext.getAttribute("tableCell");
				if(tableCell instanceof CheckboxCell)
				{
				%>
					<td class="ck_field">
						<input type="checkbox" id="check_ind0" name="check_list" value="${tableCell.value}"/>
					</td>
				<%
				} 
				else if(tableCell instanceof IconLinksCell)
				{
				
				%>
					<td>
						${tableCell.html}
					</td>
				<%
				} else {
				%>
				<td>
					<c:choose>
						<c:when test="${tableCell.escape}">
							<c:out value=" ${tableCell.html}"></c:out>
						</c:when>
						<c:otherwise>
							 ${tableCell.html}
						</c:otherwise>
					</c:choose>
				</td>
				<%
				}
				%>
			</c:forEach>
			
		</tr> 
	</c:forEach>
	</tbody>
</table> 
<hr class="tableLine2"></hr>
<div class="operation" >
						
	<div class="pageinfo">
		<c:set var="page" value="${tableModel.pageOffset}" scope="page"></c:set>
		<c:set var="pages" value="${tableModel.pages}" scope="page"></c:set>
		
		<c:if test="${pages > 0}">
			<c:set var="previous" value="${page-1}" scope="page"></c:set>
			<c:set var="next" value="${page+1}" scope="page"></c:set>
		
			<span><bean:message key="navigation.page" /></span>
			<c:if test="${ previous >0}">
				<a name="${previous}" class="btn_prev page_icon" href="javascript:void(0);">&nbsp;</a>
			</c:if>
			<c:if test="${pages >= 1 && previous <=0}">
				<a class="btn_prev_dis">&nbsp;</a>
			</c:if>
			
				<span>${page}/${pages}</span>
			
			<c:if test="${ next <= pages}">
				<a name="${next}" class="btn_next page_icon" href="javascript:void(0);">&nbsp;</a>
			</c:if>
			<c:if test="${pages >= 1 && next > pages}">
				<a class="btn_next_dis" >&nbsp;</a>
			</c:if>
		</c:if> 
	</div>
	<div class="cb"></div>
<!-- content end -->
</div>