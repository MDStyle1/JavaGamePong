package com.mds.game.client;

import org.springframework.http.*;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.*;

public class Request {
    private RestTemplate restTemplate = new RestTemplate();
    public String status;
    public Object answer;
    public <T>boolean getRequest(String url, Class a){
        try{
            ResponseEntity<T> responseEntity = restTemplate.getForEntity(url,a);
            HttpHeaders headers = responseEntity.getHeaders();
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
    public <T,A>boolean postRequest(String url,Class a,A body) {
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
        restTemplate.getInterceptors().add(new Interceptor());
    }

    public Request(String name, String password) {
        restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(name,password));
        restTemplate.getInterceptors().add(new Interceptor());
    }
}
