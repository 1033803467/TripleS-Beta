package edu.ts.service.impl;

import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.ts.service.FaceService;
import edu.ts.util.SSLSkipUtil;

public class FaceServiceImpl implements FaceService{
	@Override
	public String getFaceId(String url) {
        String faceId= null;
        JSONObject jsonUrl = new JSONObject();
        try {
            jsonUrl.put("url",url);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        
        try {
            HttpClient httpClient = SSLSkipUtil.createHttpClient();
           // HttpClient httpClient = HttpClients.createDefault();
        	URIBuilder builder = new URIBuilder("https://api.cognitive.azure.cn/face/v1.0/detect");
            builder.setParameter("returnFaceId", "true");
            builder.setParameter("returnFaceLandmarks", "false");


            URI uri =  builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Content-Type","application/json");
            request.setHeader("Ocp-Apim-Subscription-Key","43270196fd024a998b6fd1ac2e8dd0f4");

            // Request body
            StringEntity reqEntity = new StringEntity(jsonUrl.toString());
            request.setEntity(reqEntity);

            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null)
            {
                String str = EntityUtils.toString(entity);     
                str = str.substring(1,str.length()-1);
                if(str.length()==0) {
                	System.out.println("=============没有识别到人脸==========");
                	return "-1";  
                }
                JSONObject jsonObject = new JSONObject(str);
                faceId = jsonObject.getString("faceId");

                return faceId;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return faceId;
    }
	@Override
    public String identifyFace(String personGroupId, String faceId) {
        String personId = null;

        //去包装body
        JSONObject jsonPerson = new JSONObject();
        try {
            JSONArray jsonFaceIds = new JSONArray();
            jsonPerson.put("personGroupId",personGroupId);
            jsonFaceIds.put(faceId);
            jsonPerson.put("faceIds",jsonFaceIds);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
        	HttpClient httpClient = SSLSkipUtil.createHttpClient();
        	//HttpClient httpClient = HttpClients.createDefault();
            URIBuilder builder = new URIBuilder("https://api.cognitive.azure.cn/face/v1.0/identify");

            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Ocp-Apim-Subscription-Key", "43270196fd024a998b6fd1ac2e8dd0f4");

            // Request body
            StringEntity reqEntity = new StringEntity(jsonPerson.toString());
            request.setEntity(reqEntity);

            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null)
            {
                String str = EntityUtils.toString(entity);
                str = str.substring(1,str.length()-1);
                System.out.println("============="+str+"==========");
                try{
                	JSONObject jsonObj = new JSONObject(str);
                	JSONArray jsonArray = jsonObj.getJSONArray("candidates");
                	if(jsonArray.length()==0) {
                    	System.out.println("===========没有匹配到============");
                    	return "-1"; 
                    }
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    personId = jsonObject.getString("personId");
                }catch(JSONException e){
                	System.out.println("===========json解析异常============");
                	return "-1";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return personId;
    }

}
