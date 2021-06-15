package week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class main {
	public static void main(String[] args) throws IOException, ParseException {
		String clientID = "1TWSOJrGrJ74eoYY8kQC";
		String clientSecret = "OqPhEXWRp1";
		
		Scanner scan = new Scanner(System.in);
		System.out.print("검색어를 입력하세요: ");
		String searchStr = scan.nextLine();
		
		String text = URLEncoder.encode(searchStr, "UTF-8");
		String apiURL = "https://openapi.naver.com/v1/search/movie?query=" + text;
		URL url = new URL(apiURL);
		
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("X-Naver-Cleint-Id", clientID);
		conn.setRequestProperty("X-Naver-Client-Secret", clientSecret); 
		
		int responseCode = conn.getResponseCode();
		BufferedReader br;
		if(responseCode == 200) {
			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		
		String inputLine;
		StringBuffer response = new StringBuffer();
		while((inputLine = br.readLine()) != null) {
			response.append(inputLine);
		}
		br.close(); 
		
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject)jsonParser.parse(response.toString());
		JSONArray infoArray = (JSONArray)jsonObject.get("items");
		
		for(int i = 0; i < infoArray.size(); i++) {
			System.out.println("=item_" + i + "============================================");
			JSONObject itemObject = (JSONObject)infoArray.get(i);
			System.out.println("title:\t\t" + itemObject.get("title"));
			System.out.println("subtitle:\t\t" + itemObject.get("subtitle"));
			System.out.println("director:\t\t" + itemObject.get("director"));
			System.out.println("actor:\t\t" + itemObject.get("actor"));
			System.out.println("userRating:\t\t" + itemObject.get("userRating"));
			System.out.println();
		}
	}
}