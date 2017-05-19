package com.project.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import jeasyxbrl.JeasyXbrl;
import jeasyxbrl.taxonomy.instance.XbrlInstance;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class XbrlConverter {
	
	public static int PRETTY_PRINT_INDENT_FACTOR = 4;

	public static void main(String[] args) throws Throwable {
		// Adding native XBRL instance files: my_dir/file
		ArrayList<String> instanceList = new ArrayList<String>();
		instanceList.add(XbrlConverter.class.getClassLoader()
				.getResource("mmeg-20161231.xml").getPath());
		
		// Adding native XBRL linkbase file: my_dir/file
		ArrayList<String> labelList = new ArrayList<String>();
		labelList.add(XbrlConverter.class.getClassLoader()
				.getResource("mmeg-20161231_lab.xml").getPath());
		
		// loading Financial report to in-memory Java Class
		String json = convertXbrl(instanceList, labelList);
		writeData(json);
	}
	
	public static String convertXbrl(ArrayList<String> instanceList, ArrayList<String> labelList){
		JeasyXbrl jxbrl = new JeasyXbrl();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonObject jsonObject = new JsonObject();
		try {
			jxbrl.loadingInstancesInCache(instanceList, labelList, null, null,
					null, null, null, 1);
			ArrayList<XbrlInstance> xbrlList = jxbrl
					.getXbrlInstanceListByCache();
			// From in-memory Java Class to Json
			String xjson = gson.toJson(xbrlList);
			JsonElement jsonElement = new JsonParser().parse(xjson);
			jsonObject.add("root", jsonElement);
			
			//System.out.println(gson.toJson(jsonObject));
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gson.toJson(jsonObject);
	}

	public static void writeData(String jsonData) {
		File file = null;
		FileWriter fileWriter = null;
		try {
			file = new File(XbrlConverter.class.getClassLoader()
					.getResource("result.json").getPath());
			fileWriter = new FileWriter(file);
			fileWriter.write(jsonData);
			//System.out.println(gson.toJson(jsonObject));
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				fileWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
