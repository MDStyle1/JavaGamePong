package com.mds.game.client;

import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

public class Request<T> {
    private RestTemplate restTemplate = new RestTemplate();
    public String status;
    public T answer;
    public boolean getRequest(String url, Class a){
        try{
            ResponseEntity<T> responseEntity = restTemplate.getForEntity(url,a);
            answer = responseEntity.getBody();
        }catch (HttpClientErrorException e){
            status = e.toString();
            return false;
        }catch (ResourceAccessException e){
            status = e.toString();
            return false;
        }catch (HttpServerErrorException e){
            status = e.toString();
            return false;
        }
        return true;
    }
    public <A>boolean postRequest(String url,Class a,A body) {
        try{
            RequestEntity httpEntity = RequestEntity.post(url).body(body);
            ResponseEntity<T> responseEntity=restTemplate.exchange(url, HttpMethod.POST,httpEntity,a);
            answer = responseEntity.getBody();
        }catch (HttpClientErrorException e){
            status = e.toString();
            return false;
        }catch (ResourceAccessException e){
            status = e.toString();
            return false;
        }catch (HttpServerErrorException e){
            status = e.toString();
            return false;
        }
        return true;
    }

    public Request() {
    }

    public Request(String name, String password) {
        restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(name,password));
    }
}
