package com.haojii.generic.table.mapper;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.haojii.generic.table.model.Link;
import com.haojii.generic.table.model.cells.LinkCell;
import com.haojii.generic.table.model.cells.TableCell;

/**
 * processor for generate cell which renders a link
 * 
 * @author hao
 * 
 */
public class LinkCellMappingProcessor implements IMappingProcessor {
	
	/**
	 * the dbModelBean's field name to be fetched then shown as link contentHtml
	 */
	private String fieldName;
	private LinkCell linkCell;

	/**
	 * @param fieldName
	 *            - the dbModelBean's field name to be fetched then shown as
	 *            link contentHtml
	 * @param linkCell
	 *            - which link and link's attributes need to be filled , like
	 *            link's paramMap should be set to get required values
	 */
	public LinkCellMappingProcessor(String fieldName, LinkCell linkCell) {
		super();
		this.fieldName = fieldName;
		this.linkCell = linkCell;
	}

	@Override
	public TableCell process(Object dbModelBean) {
		Link link = linkCell.getLink();
		Map<String, String> map = link.getParamMap();
		Set<String> keySet = map.keySet();
		Iterator<String> itr = keySet.iterator();
		String queryString = "";
		while (itr.hasNext()) {
			String key = itr.next();
			String value = map.get(key);
			if (value == null) {
				value = TableCellMapper.getProperty(dbModelBean, key);
			}
			map.put(key, value);

			try {
				queryString += key + "=" + URLEncoder.encode(value, "UTF-8") + "&";
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		if(queryString.endsWith("&")){			
			queryString = queryString.substring(0, queryString.length() - 1);
		}
		
		String url = link.getUrl();
		if( url != null ){
			if(url.contains("?")){				
				url += "&" + queryString;
			}else{
				url += "?" + queryString;
			}
			link.setHref(url);
		}

		String contentHtml = TableCellMapper.getProperty(dbModelBean, fieldName);
		link.setContentHtml(contentHtml);

		String html = buildAnchorHtml(link);
		linkCell.setHtml(html);

		return linkCell;
	}

	/**
	 * 
	 * @param link
	 * @return the html for an anchor <br>
	 * <br>
	 *         see example: &lt;a
	 *         href="/edcp-portal/EnterpriseViewAction.do?id=7&method=view"
	 *         >Super Cool&lt;/a>
	 */
	private String buildAnchorHtml(Link link) {
		StringBuilder sb = new StringBuilder();
		sb.append("<a ");

		String onClickJavascript = link.getOnClickJavascript();
		if (onClickJavascript != null) {
			sb.append("onclick=\"");
			sb.append(onClickJavascript);
			sb.append("\" ");
		}
		String iconClass = link.getIconClass();
		if (iconClass != null) {
			sb.append("class=\"");
			sb.append(iconClass);
			sb.append("\" ");
		}
		
		String href = link.getHref();
		if (href != null) {
			sb.append("href=\"");
			sb.append(href);
			sb.append("\" ");
		} else {
			sb.append("href=\"javascript:void(0);\"");
		}
		
		String attributesHtml = link.getAttributesHtml();
		if (attributesHtml != null) {
			sb.append(attributesHtml);
		}
		sb.append(">");
		
		String contentHtml = link.getContentHtml();
		if (contentHtml != null) {
			sb.append(contentHtml);
		}
		sb.append("</a>");

		return sb.toString();
	}

}
