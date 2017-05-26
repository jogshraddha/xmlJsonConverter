import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.github.wnameless.json.flattener.JsonFlattener;
import com.github.wnameless.json.unflattener.JsonUnflattener;

public class ArelleJsonConverter {
	public static void main(String[] args) {
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser
					.parse(new FileReader(
							"/home/synerzip/Work/vistalytics/xbrl-documents/JSONS/ALPH-PROD-0413-1022.json"));

			JSONObject jsonObject = (JSONObject) obj;

			Map<String, Object> flattenJson = JsonFlattener
					.flattenAsMap(jsonObject.toJSONString());

			Map<String, Object> clonedJson = JsonFlattener
					.flattenAsMap(jsonObject.toJSONString());
			System.out.println(flattenJson);
			Set<String> keys = flattenJson.keySet();

			for (String key : keys) {
				// System.out.println(flattenJson.get(key).toString());
				if (flattenJson.get(key).toString().equals("item")) {
					clonedJson.remove(key);
				}
			}
			String nestedJson = JsonUnflattener
					.unflatten(clonedJson.toString());
			// System.out.println(nestedJson);
			
			//System.out.println(nestedJson);
			// JSONObject jsonObj = new JSONObject(flattenJson);
		} catch (FileNotFoundException f) {

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}