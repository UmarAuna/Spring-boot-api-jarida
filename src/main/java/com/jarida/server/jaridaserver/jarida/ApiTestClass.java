package com.jarida.server.jaridaserver.jarida;

import io.swagger.annotations.Api;
import org.jetbrains.annotations.NotNull;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class ApiTestClass {
    private static HttpURLConnection connection;
    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    public static void main(String[] args) throws Exception {
        ApiTestClass.GETRequest();
        ApiTestClass.POSTRequest();

        ApiTestClass api = new ApiTestClass();
        //api.sendPost();
        //api.sendGet();


    }

    public static void GETRequest() throws IOException{

        try {
            URL urlForGetRequest = new URL("http://localhost:8080/api/v1/instructors/");
            String readLine = null;
            connection = (HttpURLConnection) urlForGetRequest.openConnection();
            connection.setRequestMethod("GET");
            //connection.setRequestProperty("userId", "a1bcdef");  //set userId its a sample here
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                while ((readLine = in.readLine()) != null) {

                    response.append(readLine).append("\n");
                    response.append(System.lineSeparator());
                }
                in.close();
                //print result

                System.out.println("JSON String Result \n " + response.toString());
            } else {
                System.out.println("GET NOT  WORKED");
            }
        }catch (Exception e){
            connection.disconnect();
        }
    }

    public static void POSTRequest() throws IOException{
        final String POST_PARAMS = "{ " +
                "  \"firstName\": 103,\r\n" +
                "  \"lastName\": \"Test Title\",\r\n" +
                "    \"email\": \"Test Body\"" + "\n}";
        System.out.println(POST_PARAMS);

        URL obj =new URL("http://localhost:8080/api/v1/instructors/");
        HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
        postConnection.setDoOutput(true);
        postConnection.setRequestMethod("POST");
        postConnection.setRequestProperty("Content-Type", "application/json");
        postConnection.setRequestProperty("Content-Length", String.valueOf(POST_PARAMS.length()));
        postConnection.getOutputStream().write(POST_PARAMS.getBytes(StandardCharsets.UTF_8));
        postConnection.getOutputStream();

        OutputStream os = postConnection.getOutputStream();
        os.write(POST_PARAMS.getBytes());
        os.flush();
        os.close();

        int responseCode = postConnection.getResponseCode();
        System.out.println("POST Response Code : " + responseCode);
        System.out.println("POST Response Message : " + postConnection.getResponseMessage());

        if(responseCode == HttpURLConnection.HTTP_CREATED){
            BufferedReader in = new BufferedReader(new InputStreamReader(postConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null){
                response.append(inputLine);
            }in.close();

            //print result
            System.out.println(response.toString());
        }else {
            System.out.println("POST NOT WORKED");
        }

    }











 /*   private void sendGet() throws Exception {

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8080/api/v1/jarida"))
                .setHeader("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // print status code
        System.out.println(response.statusCode());

        // print response body
        System.out.println(response.body());

    }*/

   /* private void sendPost() throws Exception {

        // form parameters
        Map<Object, Object> data = new HashMap<>();
        data.put("firstName", "umar");
        data.put("lastName", "umar");
        data.put("email", "umar@gmail.com");
        //data.put("ts", System.currentTimeMillis());

        HttpRequest request = HttpRequest.newBuilder()
                .POST(buildFormDataFromMap(data))
                .uri(URI.create("http://localhost:8080/api/v1/instructors"))
                .setHeader("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // print status code
        System.out.println(response.statusCode());

        // print response body
        System.out.println(response.body());

    }

    @NotNull
    private static HttpRequest.BodyPublisher buildFormDataFromMap(@NotNull Map<Object, Object> data) {
        var builder = new StringBuilder();
        for (Map.Entry<Object, Object> entry : data.entrySet()) {
            if (builder.length() > 0) {
                builder.append("&");
            }
            builder.append(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8));
            builder.append("=");
            builder.append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
        }
        System.out.println(builder.toString());
        return HttpRequest.BodyPublishers.ofString(builder.toString());
    }*/



}
