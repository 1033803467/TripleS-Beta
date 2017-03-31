package edu.ts.dao.impl;

import edu.ts.dao.PersonDao;
import edu.ts.util.SSLSkipUtil;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;

/**
 * Created by jose on 2017/3/2.
 */
public class PersonDaoImpl implements PersonDao {
    public String createPerson(String personGroupId, String name, String userId) {
        String personId = null;
        // 包装groupName成json格式{body}
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("name",name);
            jsonObj.put("userData",userId);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        
        try {
        	 HttpClient httpClient = SSLSkipUtil.createHttpClient();
            URIBuilder builder = new URIBuilder("https://api.cognitive.azure.cn/face/v1.0/persongroups/"+personGroupId+"/persons");

            URI uri = builder.build();
            HttpPost request =  new HttpPost(uri);
            request.setHeader("Content-Type","application/json");
            request.setHeader("Ocp-Apim-Subscription-Key","43270196fd024a998b6fd1ac2e8dd0f4");

            //Request body
            StringEntity reqEntity = new StringEntity(jsonObj.toString());
            request.setEntity(reqEntity);

            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();

            if(entity != null){
                //   System.out.println(EntityUtils.toString(entity));
                String str =  EntityUtils.toString(entity);
                System.out.println(str);
                JSONObject jsonObject = new JSONObject(str);
                // 解析entity 得到personId
                personId = jsonObject.getString("personId");
                return personId;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return personId;
    }

    public void addFaceToPerson(String personGroupId, String personId, String userId, String url) {
        // 包装照片url成json 格式{body}
        JSONObject jsonUrl = new JSONObject();
        try {
            jsonUrl.put("url",url);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        try {
        	 HttpClient httpClient = SSLSkipUtil.createHttpClient();
            URIBuilder builder = new URIBuilder("https://api.cognitive.azure.cn/face/v1.0/persongroups/"+personGroupId+"/persons/"+personId+"/persistedFaces");

            builder.setParameter("userData",userId);
            URI uri = builder.build();
            HttpPost request =  new HttpPost(uri);
            request.setHeader("Content-Type","application/json");
            request.setHeader("Ocp-Apim-Subscription-Key","43270196fd024a998b6fd1ac2e8dd0f4");

            //Request body
            StringEntity reqEntity = new StringEntity(jsonUrl.toString());
            request.setEntity(reqEntity);

            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();

            if(entity != null){
                String str = EntityUtils.toString(entity);
                System.out.println(str);
                //  解析entity 得到persistedFaceId
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
