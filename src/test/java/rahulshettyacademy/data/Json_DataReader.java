package rahulshettyacademy.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Json_DataReader {
	
	
  //Create a method to get the jason data:
	
	  public List<HashMap<String, String>> gerJsonDataToHashMap(String filePath) throws IOException
	  {
		 //1. read and convert Json content to string 	  //it will scan the entire data of the Json and covert it into one string variable.
		  
		 String jsonContent =  FileUtils.readFileToString(new File(filePath),
				 StandardCharsets.UTF_8);  
	
		  
		  //2. String to HashMap convert:
		       // for this u have add new dependency into ur pom.xml: 'called Jackson Databind." 
		       // this dependency convert string to hashmap.
		 
		      //Also u have to create one java class ObjectMapper
		 
		      ObjectMapper mapper = new ObjectMapper();
		   
   List<HashMap<String, String>> listData =  mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
   
   // it will return list of HashMaps.
		    
          return listData;
		  
	  }
	

}
