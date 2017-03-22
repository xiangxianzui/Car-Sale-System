/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5192.webservice;

import fit5192.repository.BingImageSearch;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.ejb.Stateless;
import org.json.JSONObject;

/**
 *
 * @author Administrator
 */
@Stateless
public class BingImageSearchImpl implements BingImageSearch{
    String response = "";
    URL url;
    HttpURLConnection conn;
    String basePath = "https://bingapis.azure-api.net/api/v5/images/search";
    String paramsPath = "&count=5&offset=0&mkt=en-us&safeSearch=Moderate";
    String path = "";
 //   String path = "https://bingapis.azure-api.net/api/v5/images/search?q=Man%20United&count=1&offset=0&mkt=en-us&safeSearch=Moderate";
    String thumbnailUrl = "";


    //this method get the requested image url first, and then get the bitmap of that image url;
    @Override
    public String getImageUrl(String name){

        path = basePath + "?q=" + name.replaceAll(" ", "%20") + paramsPath;

//fistly get thumbnailUrl through Bing Image API.
        try{
            url = new URL(path);
            conn = (HttpURLConnection)url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestProperty("Ocp-Apim-Subscription-Key", "0b73ce4968794795ac8c0cebf369c348");

            int responseCode = conn.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                //Log.d("Output",br.toString());
                while ((line = br.readLine()) != null) {
                    response += line;
                    System.out.println("response: " + line);
                }
            } else{
                response = "";
            }

            if(!response.equals("") && !response.equals("[]")){
                JSONObject jsonObject = null;
                jsonObject = new JSONObject(response);
                thumbnailUrl = jsonObject.getJSONArray("value").getJSONObject(0).getString("thumbnailUrl");
                System.out.println("thumbnailUrl: " + thumbnailUrl);
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        
        return thumbnailUrl;
    }
    
//    public static void main(String[] args){
//        new BingImageSearchImpl().getImageUrl("car");
//    }

}


