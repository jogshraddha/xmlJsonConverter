package com.project.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import com.project.xmlJsonConverter.App;

public class ConversionUtil {
	private static URL url = null;
	private static InputStream inputStream = null;
	public static int PRETTY_PRINT_INDENT_FACTOR = 4;

	public String convertToJson(File file) {
		String jsonPrettyPrintString = null;
		try {
			url = file.toURI().toURL();
			inputStream = url.openStream();
			String xml = IOUtils.toString(inputStream);
			JSONObject xmlJSONObj = XML.toJSONObject(xml);
			jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
///			System.out.println(jsonPrettyPrintString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException je) {
			System.out.println(je.toString());
		}
		return jsonPrettyPrintString;
	}
}
