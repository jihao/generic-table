package com.haojii.generic.table.mapper;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.haojii.generic.table.model.Link;
import com.haojii.generic.table.model.cells.IconLinksCell;
import com.haojii.generic.table.model.cells.TableCell;

/**
 * processor for generate cell which renders icon links/buttons
 * 
 * @author hao
 * 
 */
public class IconLinksCellMappingProcessor implements IMappingProcessor {
	private IconLinksCell iconLinksCell;
	
	/**
	 * @param iconLinksCell - icon link/button
	 */
	public IconLinksCellMappingProcessor(IconLinksCell iconLinksCell) {
		super();
		
		this.iconLinksCell = iconLinksCell;
	}
	

	@Override
	public TableCell process(Object dbModelBean) {
		String html = "";
		List<Link> iconLinkList = iconLinksCell.getIconLinkList();
		for (Iterator<Link> iterator = iconLinkList.iterator(); iterator.hasNext();) {
			Link link = (Link) iterator.next();
			
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

			html += buildAnchorHtml(link);
			

		}
		iconLinksCell.setHtml(html);
		
		return iconLinksCell;
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
		else
		{
			sb.append("&nbsp;");
		}
		sb.append("</a>");

		return sb.toString();
	}


	public IconLinksCell getIconLinksCell() {
		return iconLinksCell;
	}


	public void setIconLinksCell(IconLinksCell iconLinksCell) {
		this.iconLinksCell = iconLinksCell;
	}

}
