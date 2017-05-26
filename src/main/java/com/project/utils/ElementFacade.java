package com.project.utils;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ElementFacade {
	@JsonProperty("position")
	private int position;
	
	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getXmlLine() {
		return xmlLine;
	}

	public void setXmlLine(String xmlLine) {
		this.xmlLine = xmlLine;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDecimals() {
		return decimals;
	}

	public void setDecimals(String decimals) {
		this.decimals = decimals;
	}

	public String getContextRef() {
		return contextRef;
	}

	public void setContextRef(String contextRef) {
		this.contextRef = contextRef;
	}

	public String getUnitRef() {
		return unitRef;
	}

	public void setUnitRef(String unitRef) {
		this.unitRef = unitRef;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<LabelFacade> getLabels() {
		return labels;
	}

	public void setLabels(List<LabelFacade> labels) {
		this.labels = labels;
	}

	@JsonProperty("xmlLine")
	private String xmlLine;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("decimals")
	private String decimals;
	
	@JsonProperty("contextRef")
	private String contextRef;
	
	@JsonProperty("unitRef")
	private String unitRef;
	
	@JsonProperty("value")
	private String value;
	
	@JsonProperty("labels")
	private List<LabelFacade> labels;
	
	@JsonProperty("tagName")
	private String tagName;

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

}
