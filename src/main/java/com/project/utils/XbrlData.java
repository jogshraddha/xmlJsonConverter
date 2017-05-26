package com.project.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class XbrlData {

	@JsonProperty("root")
	@JsonInclude(Include.NON_EMPTY)
	private List<ElementListFacade> root;

	public List<ElementListFacade> getRoot() {
		return root;
	}

	public void setRoot(List<ElementListFacade> root) {
		this.root = root;
	}
}