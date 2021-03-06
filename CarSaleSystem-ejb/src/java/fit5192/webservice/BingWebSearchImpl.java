/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5192.webservice;

import fit5192.repository.BingWebSearch;
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
public class BingWebSearchImpl implements BingWebSearch{
    String response = "";
    URL url;
    HttpURLConnection conn;
    String basePath = "https://bingapis.azure-api.net/api/v5/search";
    String paramsPath = "&count=5&offset=0&mkt=en-us&safeSearch=Moderate";
    String path = "";
    String description = "";
    String previewUrl = "";

    @Override
    public String[] getDescriptionAndPreviewUrl(String name){
        String[] result = new String[2];
        path = basePath + "?q=" + name.replaceAll(" ", "%20") + paramsPath;

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
                description = jsonObject.getJSONObject("webPages").getJSONArray("value").getJSONObject(0).getString("snippet");
                if(description.contains("...") && !description.startsWith("...")){
                    description = description.split("\\.")[0];
                }
                previewUrl = jsonObject.getJSONObject("webPages").getString("webSearchUrl");
                System.out.println("description: "+description);
                System.out.println("previewUrl: " + previewUrl);
            }

        } catch (Exception e){
            e.printStackTrace();
            description = "good car!";
            previewUrl = "http://www.caranddriver.com/best-luxury-cars";
        } finally {
            conn.disconnect();
        }
        result[0] = description;
        result[1] = previewUrl;
        return result;
    }
    
//    public static void main(String[] args){
//        new BingWebSearchImpl().getDescriptionAndPreviewUrl("united");
//    }

}

