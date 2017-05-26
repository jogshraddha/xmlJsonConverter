package com.project.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import jeasyxbrl.JeasyXbrl;
import jeasyxbrl.taxonomy.instance.XbrlInstance;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.project.xmlJsonConverter.App;

public class XbrlConverter {

	public static int PRETTY_PRINT_INDENT_FACTOR = 4;

	public void convertXbrlData() {
		String extension = ".json";
		String[] extensions = new String[] { "xml" };
		final String directoryPath = App.class.getClassLoader()
				.getResource("XBRL").getPath();
		
		final String jsonDirPath = App.class.getClassLoader()
				.getResource("JSON").getPath();
		File directory = new File(directoryPath);
		if (directory.exists()) {
			@SuppressWarnings("unchecked")
			FileFilter fileFilter = new FileFilter() {
				public boolean accept(File file) {
					return file.isDirectory();
				}
			};
			File[] subDirectories = directory.listFiles(fileFilter);
			for (File file : subDirectories) {
				if (file.isDirectory()) {
					ArrayList<String> labelList = new ArrayList<String>();
					ArrayList<String> calList = new ArrayList<String>();
					ArrayList<String> defList = new ArrayList<String>();
					ArrayList<String> preList = new ArrayList<String>();
					ArrayList<String> instanceList = new ArrayList<String>();
					List<File> files = (List<File>) FileUtils.listFiles(file,
							extensions, true);
					StringBuilder jsonPathBuilder = new StringBuilder();
					jsonPathBuilder.append(jsonDirPath)
							.append(File.separator).append(file.getName())
							.append(extension);
					File jsonFile = new File(jsonPathBuilder.toString());
					System.out.println(jsonFile.getPath());
					for (File xmlFile : files) {
						String fileName = xmlFile.getName();
						System.out.println(fileName);
						StringBuilder xmlFilePath = new StringBuilder();
						xmlFilePath.append(file.getAbsolutePath())
								.append(File.separator).append(fileName);
						if (fileName.contains("lab")) {
							// Adding native XBRL linkbase file: my_dir/file
							labelList.add(xmlFilePath.toString());
						} else if (fileName.contains("def")) {
							defList.add(xmlFilePath.toString());
						} else if (fileName.contains("pre")) {
							preList.add(xmlFilePath.toString());
						} else if (fileName.contains("cal")) {
							calList.add(xmlFilePath.toString());
						} else {
							instanceList.add(xmlFilePath.toString());
						}
					}
					// loading Financial report to in-memory Java Class
					String json = convertXbrl(instanceList, labelList, calList, defList, preList);
					writeData(json, jsonFile);
				}
			}
		}
	}

	public static String convertXbrl(ArrayList<String> instanceNameList,
			ArrayList<String> labelNameList,
			ArrayList<String> calculationNameList,
			ArrayList<String> definitionNameList,
			ArrayList<String> presentationNameList) {
		JeasyXbrl jxbrl = new JeasyXbrl();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonObject jsonObject = new JsonObject();
		try {
			jxbrl.loadingInstancesInCache(instanceNameList, labelNameList,
					calculationNameList, null, presentationNameList,
					definitionNameList, null, 1);
			ArrayList<XbrlInstance> xbrlList = jxbrl
					.getXbrlInstanceListByCache();
			// From in-memory Java Class to Json
			String xjson = gson.toJson(xbrlList);
			JsonElement jsonElement = new JsonParser().parse(xjson);
			jsonObject.add("root", jsonElement);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gson.toJson(jsonObject);
	}

	public static void writeData(String jsonData, File jsonFile) {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(jsonFile);
			fileWriter.write(jsonData);
			// System.out.println(gson.toJson(jsonObject));
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fileWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
