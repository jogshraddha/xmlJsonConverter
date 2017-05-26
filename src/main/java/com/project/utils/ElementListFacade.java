package com.project.utils;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ElementListFacade {
	@JsonProperty("id")
	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public List<ElementFacade> getElementList() {
		return elementList;
	}

	public void setElementList(List<ElementFacade> elementList) {
		this.elementList = elementList;
	}

	@JsonProperty("filename")
	private String fileName;
	
	@JsonProperty("company")
	private String company;
	
	@JsonProperty("eleList")
	private List<ElementFacade> elementList;
	
	@JsonProperty("size")
	private int size;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
}
