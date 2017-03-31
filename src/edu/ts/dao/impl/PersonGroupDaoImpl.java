package edu.ts.dao.impl;

import edu.ts.dao.PersonGroupDao;
import edu.ts.util.SSLSkipUtil;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
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
public class PersonGroupDaoImpl implements PersonGroupDao {
    public void createPersonGroup(String groupName, String personGroupId) {
        // 包装groupName成json格式
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("name",groupName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
       
        try {
        	 HttpClient httpClient = SSLSkipUtil.createHttpClient();
            URIBuilder builder = new URIBuilder("https://api.cognitive.azure.cn/face/v1.0/persongroups/"+personGroupId);

            URI uri =  builder.build();
            HttpPut request = new HttpPut(uri);
            request.setHeader("Content-Type","application/json");
            request.setHeader("Ocp-Apim-Subscription-Key","43270196fd024a998b6fd1ac2e8dd0f4");

            //Request body
            StringEntity reqEntity =  new StringEntity(jsonObj.toString());
            request.setEntity(reqEntity);

            HttpResponse response = httpClient.execute(request);
            HttpEntity entity =  response.getEntity();

            if(entity != null){
                System.out.println(EntityUtils.toString(entity));
                //  解析entity
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getPersonGroupList() {
       

        try {
        	 HttpClient httpClient = SSLSkipUtil.createHttpClient();
            URIBuilder builder = new URIBuilder("https://api.cognitive.azure.cn/face/v1.0/persongroups");

            URI uri = builder.build();
            HttpGet request = new HttpGet(uri);
            request.setHeader("Ocp-Apim-Subscription-Key", "43270196fd024a998b6fd1ac2e8dd0f4");

            //StringEntity reqEntity = new StringEntity("{}");
            //request.setEntity(reqEntity);

            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null)
            {
                String str = EntityUtils.toString(entity);
                str = str.substring(1,str.length()-1);
                System.out.println(str);
                JSONObject jsonObj = new JSONObject(str);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public void trainPersongroup(String personGroupId) {
        

        try {
        	HttpClient httpClient = SSLSkipUtil.createHttpClient();
            URIBuilder builder = new URIBuilder("https://api.cognitive.azure.cn/face/v1.0/persongroups/"+personGroupId+"/train");
            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Ocp-Apim-Subscription-Key", "43270196fd024a998b6fd1ac2e8dd0f4");
            
            
            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            if (entity != null)
            {   
            	
                String str = EntityUtils.toString(entity);
                System.out.println("训练完成"+str);
            }

        } catch (Exception e) {

        }
    }
}
