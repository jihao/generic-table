package com.haojii.generic.table.model;

import java.util.HashMap;
import java.util.Map;

/**
 * The icon with a link action attached, one example
 * 
 * <br>
 * &lt;a onclick="delete_item(event,this);" class="btn_delete" href="#">&nbsp;&lt;/a>
 * 
 * @author hao
 *
 */
public class Link {
	
	public Link() {
		super();
	}
	/**
	 * Use this constructor when you only need a link 
	 * @param url
	 * @param paramMap
	 */
	public Link(String contextPath, String url, Map<String, String> paramMap) {
		super();
		this.url = contextPath + url;
		this.paramMap = paramMap;
	}
	/**
	 * Use this constructor when you need a icon link
	 * @param styleClass
	 * @param url
	 * @param paramMap
	 */
	public Link(String contextPath, String url, Map<String, String> paramMap, String styleClass) {
		super();
		this.styleClass = styleClass;
		this.url = contextPath + url;
		this.paramMap = paramMap;
	}
	/**
	 * Use this constructor when you need a icon button with javascript event 
	 * @param styleClass
	 * @param onClickJavascript
	 */
	public Link(String styleClass, String onClickJavascript) {
		super();
		this.styleClass = styleClass;
		this.onClickJavascript = onClickJavascript;
	}
	/**
	 * Link can be styled as icon button
	 */
	private String styleClass;
	private String onClickJavascript;
	
	private String url;
	private Map<String,String> paramMap = new HashMap<String, String>();
	
	private String href;
	
	/**
	 * Other standard html attributes are supported, it will be rendered without escape
	 */
	private String attributesHtml;
	/**
	 * The text content of the tag
	 */
	private String contentHtml;
	
	public String getIconClass() {
		return styleClass;
	}
	public void setIconClass(String iconClass) {
		this.styleClass = iconClass;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getOnClickJavascript() {
		return onClickJavascript;
	}
	public void setOnClickJavascript(String onClickJavascript) {
		this.onClickJavascript = onClickJavascript;
	}
	public Map<String, String> getParamMap() {
		return paramMap;
	}
	public void setParamMap(Map<String, String> paramMap) {
		this.paramMap = paramMap;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getAttributesHtml() {
		return attributesHtml;
	}
	public void setAttributesHtml(String attributesHtml) {
		this.attributesHtml = attributesHtml;
	}
	public String getContentHtml() {
		return contentHtml;
	}
	public void setContentHtml(String contentHtml) {
		this.contentHtml = contentHtml;
	}
	
}
