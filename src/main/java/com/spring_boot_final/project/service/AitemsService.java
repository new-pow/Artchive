package com.spring_boot_final.project.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring_boot_final.project.dao.IAitemsDAO;
import com.spring_boot_final.project.model.ExhbnVO;
import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

@Service
public class AitemsService {

    //private String serviceId = "456332lr6qf"; //artchive
    private String serviceId = "su7ccl6ha39"; //AIItems

    @Autowired
    @Qualifier("IAitemsDAO")
    IAitemsDAO dao;

    @Value("${aitems.accessKey}")
    private String accessKey;

    @Value("${aitems.secretKey}")
    private String secretKey;

    public ArrayList<String> getRelatedItem(String itemId, int num) {

        try {

            String apiURL = "https://aitems.apigw.ntruss.com/api/v1/services/" + serviceId + "/infers/lookup?targetId=" + itemId + "&type=relatedItem";
            String url1 = "/api/v1/services/" + serviceId + "/infers/lookup?targetId=" + itemId + "&type=relatedItem";
            URL url = new URL(apiURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setUseCaches(false);
            conn.setDoOutput(true);
            conn.setDoInput(true);

            long time = System.currentTimeMillis();

            conn.setRequestProperty("accept", "application/json");
            conn.setRequestProperty("x-ncp-iam-access-key", accessKey);
            conn.setRequestProperty("x-ncp-apigw-signature-v2", makeSignature(url1, time, accessKey, secretKey));
            conn.setRequestProperty("x-ncp-apigw-timestamp", String.valueOf(time));

            BufferedReader br = null;
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {  // 오류 발생
                System.out.println("error!!!!!!! responseCode= " + responseCode);
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            }
            String inputLine;

            if (br != null) {
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                System.out.println(response.toString());
                return convertJsonToArray(response.toString(), num);
            } else {
                System.out.println("error !!!");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public ArrayList<String> getPersonalRecommend(String userId, int num) {

        try {

            String apiURL = "https://aitems.apigw.ntruss.com/api/v1/services/" + serviceId + "/infers/lookup?targetId=" + userId + "&type=personalRecommend";
            String url1 = "/api/v1/services/" + serviceId + "/infers/lookup?targetId=" + userId + "&type=personalRecommend";
            URL url = new URL(apiURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setUseCaches(false);
            conn.setDoOutput(true);
            conn.setDoInput(true);

            long time = System.currentTimeMillis();

            conn.setRequestProperty("accept", "application/json");
            conn.setRequestProperty("x-ncp-iam-access-key", accessKey);
            conn.setRequestProperty("x-ncp-apigw-signature-v2", makeSignature(url1, time, accessKey, secretKey));
            conn.setRequestProperty("x-ncp-apigw-timestamp", String.valueOf(time));

            BufferedReader br = null;
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {  // 오류 발생
                System.out.println("error!!!!!!! responseCode= " + responseCode);
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            }
            String inputLine;

            if (br != null) {
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                System.out.println(response.toString());
                return convertJsonToArray(response.toString(), num);
            } else {
                System.out.println("error !!!");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public ArrayList<ExhbnVO> getExhbnItems(ArrayList<String> items) {
        return dao.selectExhbn(items);
    }

    public void aiTest() {
        String[] users = {"user1000", "user1001", "user1002", "user1003", "user1004", "user1005", "user1006", "user1007", "user1008", "user1009"};
        String[] items = {"166147", "170893", "185859", "201265", "203580", "204681", "205080", "205263", "206135", "206743", "209436", "209437", "209448", "209723", "210015", "210149", "210404", "210589", "210593", "210600", "210761", "210863", "210868", "210962", "210981", "211100", "211179", "211205", "211349", "211403", "211541", "211643", "211647", "211664", "211665", "211756", "211997", "211999", "212102", "212190", "212201", "212202", "212321", "212343", "212458", "212459", "212461", "212545", "212562", "212763", "212882", "212965", "212966", "213041", "213121", "213243", "213434", "213754", "213756", "213781", "213876", "213877", "213970", "214159", "214160", "214181", "214182", "214844", "215048", "215092", "215093", "215199", "215263", "215264", "215283", "215379", "215393", "215402", "215484", "215590", "215602", "215603", "215665", "216140", "216226", "216442", "216490", "216491", "216616", "216702", "216825", "216826", "216921", "217034", "217035", "217300", "217601", "217842"};

        for (int i = 0; i < 100; i++) {

            long time;
            String user;
            String item;

            if (i % 5 == 0) {
                time = (long) (Math.random() * 1700) + 5;
                user = users[(int) (Math.random() * users.length)];
                item = "209436";

            } else if (i % 7 == 0) {
                time = (long) (Math.random() * 1700) + 5;
                user = users[(int) (Math.random() * users.length)];
                item = "212458";
            } else if (i % 10 == 0) {
                time = (long) (Math.random() * 1700) + 5;
                user = users[(int) (Math.random() * users.length)];
                item = "213781";
            } else if (i % 15 == 0) {
                time = (long) (Math.random() * 1700) + 5;
                user = users[(int) (Math.random() * users.length)];
                item = "203580";
            } else {
                time = (long) (Math.random() * 1700) + 5;
                user = users[(int) (Math.random() * users.length)];
                item = items[(int) (Math.random() * items.length)];
            }

            System.out.println("INSERT INTO finaldb.interaction_das (USER_ID, ITEM_ID, TIMESTAMP) VALUES ('" + user + "', '" + item + "', '" + time + "');");

        }

    }

    public String makeSignature(String url, long timestamp, String accessKey, String secretKey) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        String space = " ";                    // one space
        String newLine = "\n";                    // new line
        String method = "GET";                    // method
//        String url = "/photos/puppy.jpg?query1=&query2";    // url (include query string)
//        String timestamp = "{timestamp}";			// current timestamp (epoch)
//        String accessKey = "{accessKey}";			// access key id (from portal or Sub Account)
//        String secretKey = "{secretKey}";

        String message = new StringBuilder()
                .append(method)
                .append(space)
                .append(url)
                .append(newLine)
                .append(timestamp)
                .append(newLine)
                .append(accessKey)
                .toString();

        SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);

        byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
        String encodeBase64String = Base64.encodeBase64String(rawHmac);

        System.out.println(encodeBase64String);
        return encodeBase64String;
    }

    public ArrayList<String> convertJsonToArray(String json, int num) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Response vo = mapper.readValue(json, Response.class);
        ArrayList<String> items = dao.selectItems((num - vo.getValues().size() <= 0) ? 0 : num - vo.getValues().size());
        items.addAll(vo.getValues());

        return items;

    }

}

class Response {
    String field;
    ArrayList<String> values;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public ArrayList<String> getValues() {
        return values;
    }

    public void setValues(ArrayList<String> values) {
        this.values = values;
    }
}
