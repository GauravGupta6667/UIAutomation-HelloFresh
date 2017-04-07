package com.hellofresh.utils;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;


public class JsonReader {

		private static String BASEPATH=System.getProperty("user.dir");	
		
		private static  String JOSONPATH=BASEPATH+"\\src\\test\\resources\\registeruser.json";

		private static Map<String, String> registerusermap=new HashMap<String, String>();
		
	public static Map<String, String>  getRegisterUserJsonData(){
		
		try{
			File file=new File(JOSONPATH);
			Path path=file.toPath();
			System.out.println("path:"+file.getAbsolutePath());
	
			StringBuilder sb=new StringBuilder();
			List<String> content=Files.readAllLines(path,Charset.defaultCharset());

			for (String string : content) {
		
				sb.append(string);
			}
	//		System.out.println(sb.toString());
			
			String jsonstring=sb.toString();
			JSONObject jsonObject=new JSONObject(jsonstring);
			
			JSONObject registerUser=jsonObject.getJSONObject("RegisterUser");
			
			//System.out.println("desired cap value:"+desiredcap.toString());
			
			String gender=registerUser.getString("gender");
			String 	firstname=registerUser.getString("firstname");
			String lastname=registerUser.getString("lastname");
			
			String email=registerUser.getString("email");
			
			String password=registerUser.getString("password");
			
			String birthmonth=registerUser.getString("birthmonth");
			
			
			String birthday=registerUser.getString("birthday");
			
			
			String birthyear=registerUser.getString("birthyear");
			
			
			registerusermap.put("gender", gender);
			registerusermap.put("firstname", firstname);
			registerusermap.put("lastname", lastname);
			registerusermap.put("email", email);
			
			registerusermap.put("password", password);
			registerusermap.put("birthmonth", birthmonth);
			registerusermap.put("birthday", birthday);
			registerusermap.put("birthyear", birthyear);
				
			
		}catch (Exception ex) {
            ex.printStackTrace();
        }
		return registerusermap;
	}

}
