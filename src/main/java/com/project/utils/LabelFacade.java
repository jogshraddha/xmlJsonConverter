package com.project.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LabelFacade {

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLabelattr() {
		return labelattr;
	}

	public void setLabelattr(String labelattr) {
		this.labelattr = labelattr;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@JsonProperty("xmlLine")
	private String xmlLine;

	@JsonProperty("type")
	private String type;

	@JsonProperty("labelattr")
	private String labelattr;

	@JsonProperty("lang")
	private String lang;

	@JsonProperty("role")
	private String role;

	@JsonProperty("value")
	private String value;

}
