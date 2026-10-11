package Data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	public List<HashMap<String, String>> getJasonDataToMap() throws IOException 
	{
		// Read json to string
		@SuppressWarnings("deprecation")
		String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//SeleniumFramework//src//test//java//Data//PurchaseData.json"));
		
		
		// String to HashMap jackson databind
		
		ObjectMapper mapper = new ObjectMapper();
		
		List<HashMap<String, String>> data = mapper.readValue(jsonContent.getBytes(StandardCharsets.UTF_8), 
				new TypeReference<List<HashMap<String,String>>>() {});
		
		// this data is a list now with two arguments (two HashMaps)
		
		return data;
		
	}
	
	
}
