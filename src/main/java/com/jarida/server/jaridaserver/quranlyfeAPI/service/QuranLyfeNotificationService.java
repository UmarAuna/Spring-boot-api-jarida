package com.jarida.server.jaridaserver.quranlyfeAPI.service;

import com.google.gson.Gson;
import com.jarida.server.jaridaserver.exception.ResourceNotFoundException;
import com.jarida.server.jaridaserver.quranlyfeAPI.model.Data;
import com.jarida.server.jaridaserver.quranlyfeAPI.model.QuranLyfeNotification;
import com.jarida.server.jaridaserver.quranlyfeAPI.model.QuranLyfteNotificationDto;
import com.jarida.server.jaridaserver.quranlyfeAPI.repository.QuranLyfeNotificationRepository;
import com.jarida.server.jaridaserver.spring_security_2.payload.ApiResponseTwos;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class QuranLyfeNotificationService {
    @Autowired
    private QuranLyfeNotificationRepository quranLyfeNotificationRepository;

    public List<QuranLyfeNotification> getAllNotifications() {
        return quranLyfeNotificationRepository.findAll();
    }


    public void deleteNotification(Long id) {
      boolean exists = quranLyfeNotificationRepository.existsById(id);

      if(!exists) {
          throw new ResourceNotFoundException(
                  "QuranLyfe Notification with id " + id + " does not exists"
          );
      }
      quranLyfeNotificationRepository.deleteById(id);
    }

    public void delete(Long id) {
        quranLyfeNotificationRepository.deleteById(id);
    }

    public ResponseEntity<ApiResponseTwos> sendNotification(
            QuranLyfeNotification quranLyfeNotification
    ) throws NoHandlerFoundException {

        try{

            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost postRequest = new HttpPost(
                    "https://fcm.googleapis.com/fcm/send");

            QuranLyfteNotificationDto quranLyfteNotificationDto = new QuranLyfteNotificationDto();
            Data data = new Data();

            quranLyfteNotificationDto.setTo("/topics/quranLyfe");
            quranLyfteNotificationDto.setPriority("high");
            data.setTitle(quranLyfeNotification.getTitle());
            data.setMessage(quranLyfeNotification.getMessage());
            data.setIntent(quranLyfeNotification.getIntent());
            data.setImage(quranLyfeNotification.getUrl());
            quranLyfteNotificationDto.setData(data);

            Gson gson = new Gson();
            Type type = new TypeToken<QuranLyfteNotificationDto>() {
            }.getType();

            String json = gson.toJson(quranLyfteNotificationDto, type);

            StringEntity input = new StringEntity(json);
            input.setContentType("application/json");

            postRequest.addHeader("Authorization", "key=AAAAYPaH-9I:APA91bEM1vk6R0q12hTe0J9LFvGzTVVvQTr_fSU2tGQXV8Lbr5MoSp0XEEiPqpfC36yqg4HVUui85MawxrI5-z5C5KdIA2OhhhzwnFmvYNWDpz49IsIhqx4tfyktjKoXVtTUlg1oB9qm");
            postRequest.setEntity(input);

            System.out.println("request:" + json);

            HttpResponse response = httpClient.execute(postRequest);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            } else if (response.getStatusLine().getStatusCode() == 200) {
                System.out.println("response:" + EntityUtils.toString(response.getEntity()));

            }

            quranLyfeNotificationRepository.save(quranLyfeNotification);

            ApiResponseTwos apiResponse = new ApiResponseTwos(Boolean.TRUE, "Notification Sent");
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);

        }catch (Exception e) {
            throw new NoHandlerFoundException("", "", HttpHeaders.EMPTY);
        }

    }

    public ResponseEntity<ApiResponseTwos> sendNotificationNoDb(
            QuranLyfeNotification quranLyfeNotification
    ) throws NoHandlerFoundException {

        try{
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost postRequest = new HttpPost(
                    "https://fcm.googleapis.com/fcm/send");

            QuranLyfteNotificationDto quranLyfteNotificationDto = new QuranLyfteNotificationDto();
            Data data = new Data();

            quranLyfteNotificationDto.setTo("/topics/quranLyfe");
            quranLyfteNotificationDto.setPriority("high");
            data.setTitle(quranLyfeNotification.getTitle());
            data.setMessage(quranLyfeNotification.getMessage());
            data.setIntent(quranLyfeNotification.getIntent());
            data.setImage(quranLyfeNotification.getUrl());
            quranLyfteNotificationDto.setData(data);

            Gson gson = new Gson();
            Type type = new TypeToken<QuranLyfteNotificationDto>() {
            }.getType();

            String json = gson.toJson(quranLyfteNotificationDto, type);

            StringEntity input = new StringEntity(json);
            input.setContentType("application/json");

            postRequest.addHeader("Authorization", "key=AAAAYPaH-9I:APA91bEM1vk6R0q12hTe0J9LFvGzTVVvQTr_fSU2tGQXV8Lbr5MoSp0XEEiPqpfC36yqg4HVUui85MawxrI5-z5C5KdIA2OhhhzwnFmvYNWDpz49IsIhqx4tfyktjKoXVtTUlg1oB9qm");
            postRequest.setEntity(input);

            System.out.println("request:" + json);

            HttpResponse response = httpClient.execute(postRequest);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            } else if (response.getStatusLine().getStatusCode() == 200) {
                System.out.println("response:" + EntityUtils.toString(response.getEntity()));

            }

            ApiResponseTwos apiResponse = new ApiResponseTwos(Boolean.TRUE, "Notification Sent");
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);

        }catch (Exception e) {
            throw new NoHandlerFoundException("", "", HttpHeaders.EMPTY);
        }

    }

    public QuranLyfeNotification updateNotification(
            Long id,
            QuranLyfeNotification quranLyfeNotification) {

        return quranLyfeNotificationRepository.findById(id).map(quranLyfe -> {
            quranLyfe.setTitle(quranLyfeNotification.getTitle());
            quranLyfe.setMessage(quranLyfeNotification.getMessage());
            quranLyfe.setIntent(quranLyfeNotification.getIntent());
            quranLyfe.setUrl(quranLyfeNotification.getUrl());

            return quranLyfeNotificationRepository.save(quranLyfe);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "QuranLyfe Notification with " + id + " not found"
        ));
    }
}
