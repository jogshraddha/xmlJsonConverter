package com.project.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.project.xmlJsonConverter.App;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;

public class JsonToESReader {
	public static void main(String[] args) {
		final String directoryPath = App.class.getClassLoader()
				.getResource("JSON").getPath();
		File directory = new File(directoryPath);
		File[] jsonFiles = directory.listFiles();
		TransportClient client = ESClient.getTransportClient("127.0.0.1", 9300);
		for (File file : jsonFiles) {
			ObjectMapper mapper = new ObjectMapper();
			XbrlData data = getJson(file);
			for (ElementListFacade elementList : data.getRoot()) {
				int i = 0;
				System.out.println("------------------------------------- " + elementList.getCompany());
				for (ElementFacade element : elementList.getElementList()) {
					System.out.println(i);
					Map<String, Object> map = mapper.convertValue(element,
							new TypeReference<Map<String, Object>>() {
							});
					String type = elementList.getCompany().replace(",", "");
					IndexResponse result = ESClient.doIndex(client,
							"vista", type, ""
									+ i, map);
					i++;

				}
			}
		}
		System.out.println("ALL DATA INGESTED TO ES");
		client.close();
	}

	public static XbrlData getJson(File file) {
		XbrlData xbrlData = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			xbrlData = mapper.readValue(file, XbrlData.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return xbrlData;

	}

}
