package com.ehi.enterprise_nextgen_app.services;

import android.util.Log;

import com.ehi.enterprise_nextgen_app.bo.BaseResponse;
import com.google.gson.reflect.TypeToken;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;

/**
 * Created by M97A on 12/12/2017.
 */

public class RestServiceClient {

    public static final String TAG = RestServiceClient.class.getName();

    private static RestTemplate restTemplate;
    private static RestServiceClient restServiceClient;
/*

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
    }
*/

    public static RestServiceClient getInsatnce(){
        if(null == restServiceClient){
            Log.d(TAG,"Instatntiating singleton RestServiceClient ::");
            restServiceClient = new RestServiceClient();
        }
        initRestTemplate();
        return restServiceClient;
    }

    private static void initRestTemplate(){
        if(null == restTemplate){
            Log.d(TAG,"Instatntiating singleton RestTemplate ::");
            restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        }
    }


    /*public BaseResponse<T> postFormData(String url, MultiValueMap<String, String> formData,Class<T> clzz){

        MyParameterizedTypeImpl<UserProfile> mm = new MyParameterizedTypeImpl<UserProfile>(UserProfile.class);
        //mm.set(UserProfile.class);
        Log.i(TAG,"postFormData postFormData :: "+formData.toString());
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> httpEntity = new HttpEntity<Object>(formData, null);
        //Class<BaseResponse<UserProfile>> clazz = (BaseResponse<UserProfile>) (Class<BaseResponse<UserProfile>>);
        *//*MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("first_name", "Stancho");s
        formData.add("last_name", "Stanchev");*//*
        ;//new ParameterizedTypeReference<BaseResponse<UserProfile>>(){}

        *//*ObjectMapper mapper = new ObjectMapper();
        JavaType type = mapper.getTypeFactory().
                constructCollectionType((Class<? extends Collection>) BaseResponse.class, UserProfile.class);*//*

        ResponseEntity<BaseResponse<T>> responseEntity = restTemplate.exchange("https://ehi-login-app.herokuapp.com/login", HttpMethod.POST,httpEntity,mm);

        return responseEntity.getBody();
    }*/
    private <T> BaseResponse<T> makeRequestWithFormData(String url, MultiValueMap<String, String> formData, HttpMethod method) {
        Log.d(TAG,"In RestServiceClient.makeRequestWithFormData ::");

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> httpEntity = new HttpEntity<Object>(formData, null);
        ParameterizedTypeReference<BaseResponse<T>> responseTypeRef = ParameterizedTypeReferenceBuilder.fromTypeToken(new TypeToken<BaseResponse<T>>(){});
        ResponseEntity<BaseResponse<T>> response = restTemplate.exchange(url, method, httpEntity, responseTypeRef);
        return response.getBody();
    }

    public <T> BaseResponse<T> getRequestForm(String url, MultiValueMap<String, String> formData) {
        Log.d(TAG,"In RestServiceClient.getRequestForm ::");
        return makeRequestWithFormData(url,formData,HttpMethod.GET);
    }
    public <T> BaseResponse<T> postRequestForm(String url, MultiValueMap<String, String> formData) {
        Log.d(TAG,"In RestServiceClient.postRequestForm ::");
        return makeRequestWithFormData(url,formData,HttpMethod.POST);
    }
}
