package com.armani.myapplication.config;

import android.util.Log;

import com.google.gson.JsonObject;
import com.squareup.okhttp.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class Rest {

    public static ServiceResponse get(String ServiceUrl, Map<String, String> params, Boolean isJsonObject) {

        ServiceResponse serviceResponse = new ServiceResponse();
        try {

            HttpUrl.Builder urlBuilder = HttpUrl.parse(ServiceUrl).newBuilder();

            /*Set<String> set = params.keySet();

            for (String key : set) {
                urlBuilder.setQueryParameter(key, params.get(key));
            }*/

            String url = urlBuilder.build().toString();

            Request request = new Request.Builder()
                    .url(url).get()
                    .build();

            Response response = null;
            OkHttpClient okHttpClient = new OkHttpClient();
            try {
                okHttpClient.setConnectTimeout(30, TimeUnit.SECONDS);
                okHttpClient.setReadTimeout(30, TimeUnit.SECONDS);
                response = okHttpClient.newCall(request).execute();
                String serverResponse = response.body().string();
                serviceResponse.setResponseCode(response.code());
                if (isJsonObject)
                    serviceResponse.setJsonResult(new JSONObject(serverResponse));
                else
                    serviceResponse.setJsonArrayResult(new JSONArray(serverResponse));
            } catch (Exception ex) {
                Log.e("error", ex.getMessage());
            }
        } catch (Exception ex) {
            Log.e("error", ex.getMessage());
        }
        return serviceResponse;

    }

    public static ServiceResponse postWithoutAnyThing(String ServiceUrl) {
        ServiceResponse serviceResponse = new ServiceResponse();
        try {
            HttpUrl.Builder urlBuilder = HttpUrl.parse(ServiceUrl).newBuilder();

            final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            String url = urlBuilder.build().toString();
            JsonObject jsonObject = new JsonObject();
            RequestBody formBody = null;
            formBody = RequestBody.create(JSON, jsonObject.toString());


            Request request = new Request.Builder()
                    .url(url).post(formBody)
                    .build();

            Response response = null;
            OkHttpClient okHttpClient = new OkHttpClient();
            try {
                okHttpClient.setConnectTimeout(30, TimeUnit.SECONDS);
                okHttpClient.setReadTimeout(30, TimeUnit.SECONDS);
                response = okHttpClient.newCall(request).execute();
                String serverResponse = response.body().string();
                serviceResponse.setResponseCode(response.code());
            } catch (Exception ex) {
                Log.e("error", ex.getMessage());
            }
        } catch (Exception ex) {
            Log.e("error", ex.getMessage());
        }
        return serviceResponse;

    }

    public static byte[] postWithQueryParam(String ServiceUrl, Map<String, String> params, Boolean isJsonObject) {
        byte[] serverResponse = new byte[100000];
        ServiceResponse serviceResponse = new ServiceResponse();
        try {
            final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            HttpUrl.Builder urlBuilder = HttpUrl.parse(ServiceUrl).newBuilder();

            Set<String> set = params.keySet();

            for (String key : set) {
                urlBuilder.setQueryParameter(key, params.get(key));
            }

            String url = urlBuilder.build().toString();
            JsonObject jsonObject = new JsonObject();
            RequestBody formBody = null;
            formBody = RequestBody.create(JSON, jsonObject.toString());


            Request request = new Request.Builder()
                    .url(url).post(formBody)
                    .build();

            Response response = null;
            OkHttpClient okHttpClient = new OkHttpClient();
            try {
                okHttpClient.setConnectTimeout(30, TimeUnit.SECONDS);
                okHttpClient.setReadTimeout(30, TimeUnit.SECONDS);
                response = okHttpClient.newCall(request).execute();
                serverResponse = response.body().bytes();
                serviceResponse.setResponseCode(response.code());

            } catch (Exception ex) {
                Log.e("error", ex.getMessage());
            }
        } catch (Exception ex) {
            Log.e("error", ex.getMessage());
        }
        return serverResponse;
    }

    public static ServiceResponse postForRemoveFromBasket(String ServiceUrl, Map<String, String> params, JSONObject jsonObject, boolean isIncludeJsonBody) {

        ServiceResponse serviceResponse = new ServiceResponse();
        try {

            HttpUrl.Builder urlBuilder = HttpUrl.parse(ServiceUrl).newBuilder();

            JSONObject jsonParams = new JSONObject();

            final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            String url = urlBuilder.build().toString();
            FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();
            Set<String> set = params.keySet();
            for (String key : set) {
                formEncodingBuilder.add(key, params.get(key));
                jsonParams.put(key, params.get(key));
            }

            String jsonParamsString = jsonParams.toString();//.replace("{", "");

            RequestBody formBody = null;
            if (isIncludeJsonBody)
                formBody = RequestBody.create(JSON, jsonObject.toString());
            else
                formBody = RequestBody.create(JSON, jsonParamsString);


            Request request = new Request.Builder()
                    .url(url)
                    .post(formBody)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .build();

            Response response = null;
            OkHttpClient okHttpClient = new OkHttpClient();
            try {
                okHttpClient.setConnectTimeout(30, TimeUnit.SECONDS);
                okHttpClient.setReadTimeout(30, TimeUnit.SECONDS);
                response = okHttpClient.newCall(request).execute();
                String serverResponse = response.body().string();
                serviceResponse.setResponseCode(response.code());
                serviceResponse.setJsonResult(new JSONObject(serverResponse));
            } catch (Exception ex) {
                Log.e("error", ex.getMessage());
            }
        } catch (Exception ex) {
            Log.e("error", ex.getMessage());
        }
        return serviceResponse;

    }

    public static byte[] getForRetrieveImage(String ServiceUrl) {
        byte[] serverResponse = new byte[100000];
        ServiceResponse serviceResponse = new ServiceResponse();
        try {
            HttpUrl.Builder urlBuilder = HttpUrl.parse(ServiceUrl).newBuilder();

            String url = urlBuilder.build().toString();

            Request request = new Request.Builder()
                    .url(url).get()
                    .build();

            Response response = null;
            OkHttpClient okHttpClient = new OkHttpClient();
            try {
                okHttpClient.setConnectTimeout(30, TimeUnit.SECONDS);
                okHttpClient.setReadTimeout(30, TimeUnit.SECONDS);
                response = okHttpClient.newCall(request).execute();
                serverResponse = response.body().bytes();
                serviceResponse.setResponseCode(response.code());

            } catch (Exception ex) {
                Log.e("error", ex.getMessage());
            }
        } catch (Exception ex) {
            Log.e("error", ex.getMessage());
        }
        return serverResponse;
    }

    public static ServiceResponse post(String ServiceUrl, Map<String, String> params, JSONObject jsonObject, boolean isIncludeJsonBody) {

        ServiceResponse serviceResponse = new ServiceResponse();
        try {

            HttpUrl.Builder urlBuilder = HttpUrl.parse(ServiceUrl).newBuilder();

            JSONObject jsonParams = new JSONObject();

            final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            String url = urlBuilder.build().toString();
            FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();
            /*Set<String> set = params.keySet();
            for (String key : set) {
                formEncodingBuilder.add(key, params.get(key));
                jsonParams.put(key, params.get(key));
            }*/

            String jsonParamsString = jsonParams.toString();//.replace("{", "");

            RequestBody formBody = null;
            if (isIncludeJsonBody)
                formBody = RequestBody.create(JSON, jsonObject.toString());
            else
                formBody = RequestBody.create(JSON, jsonParamsString);


            Request request = new Request.Builder()
                    .url(url)
                    .post(formBody)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .build();

            Response response = null;
            OkHttpClient okHttpClient = new OkHttpClient();
            try {
                okHttpClient.setConnectTimeout(30, TimeUnit.SECONDS);
                okHttpClient.setReadTimeout(30, TimeUnit.SECONDS);
                response = okHttpClient.newCall(request).execute();
                String serverResponse = response.body().string();
                serviceResponse.setResponseCode(response.code());
                serviceResponse.setJsonResult(new JSONObject(serverResponse));
            } catch (Exception ex) {
                Log.e("error", ex.getMessage());
            }
        } catch (Exception ex) {
            Log.e("error", ex.getMessage());
        }
        return serviceResponse;

    }

    public static ServiceResponse put(String ServiceUrl, Map<String, String> params, JSONObject jsonObject, boolean isParamIncluded) {

        ServiceResponse serviceResponse = new ServiceResponse();
        try {

            HttpUrl.Builder urlBuilder = HttpUrl.parse(ServiceUrl).newBuilder();


            JSONObject jsonParams = new JSONObject();

            Set<String> set = params.keySet();
            final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            String url = urlBuilder.build().toString();
            FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();
            for (String key : set) {
                formEncodingBuilder.add(key, params.get(key));
                jsonParams.put(key, params.get(key));
            }

            String jsonParamsString = jsonParams.toString();//.replace("{", "");

            RequestBody formBody = null;
            if (isParamIncluded)
                formBody = RequestBody.create(JSON, jsonObject.toString());
            else
                formBody = RequestBody.create(JSON, jsonParamsString);


            Request request = new Request.Builder()
                    .url(url)
                    .put(formBody)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .build();

            Response response = null;
            OkHttpClient okHttpClient = new OkHttpClient();
            try {
                okHttpClient.setConnectTimeout(30, TimeUnit.SECONDS);
                okHttpClient.setReadTimeout(30, TimeUnit.SECONDS);
                response = okHttpClient.newCall(request).execute();
                String serverResponse = response.body().string();
                serviceResponse.setResponseCode(response.code());
                if (!serverResponse.equals(""))
                    serviceResponse.setJsonResult(new JSONObject(serverResponse));
            } catch (IOException ex) {
                Log.e("error", ex.getMessage());
            } catch (Exception ex) {
                Log.e("error", ex.getMessage());
            }
        } catch (Exception ex) {
            Log.e("error", ex.getMessage());
        }
        return serviceResponse;

    }

    public static ServiceResponse delete(String ServiceUrl) {

        ServiceResponse serviceResponse = new ServiceResponse();
        try {

            HttpUrl.Builder urlBuilder = HttpUrl.parse(ServiceUrl).newBuilder();
            String url = urlBuilder.build().toString();
            Request request = new Request.Builder()
                    .url(url)
                    .delete()
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .build();

            Response response = null;
            OkHttpClient okHttpClient = new OkHttpClient();
            try {
                okHttpClient.setConnectTimeout(30, TimeUnit.SECONDS);
                okHttpClient.setReadTimeout(30, TimeUnit.SECONDS);
                response = okHttpClient.newCall(request).execute();
                String serverResponse = response.body().string();
                serviceResponse.setResponseCode(response.code());

            } catch (IOException ex) {
                Log.e("error", ex.getMessage());
            } catch (Exception ex) {
                Log.e("error", ex.getMessage());
            }
        } catch (Exception ex) {
            Log.e("error", ex.getMessage());
        }
        return serviceResponse;

    }

    public ServiceResponse postFile(String ServiceUrl, Map<String, String> params, String filePath, String fileName, MediaType mediaType) {
        ServiceResponse serviceResponse = new ServiceResponse();

        try {
            final OkHttpClient client = new OkHttpClient();
            File file = new File(filePath);
            MultipartBuilder multipartBuilder = new MultipartBuilder()
                    .type(MultipartBuilder.FORM)
                    .addFormDataPart("file", fileName, RequestBody.create(mediaType, file));


            Set<String> set = params.keySet();
            for (String key : set) {
                multipartBuilder.addFormDataPart(key, params.get(key));
            }

            RequestBody requestBody = multipartBuilder.build();

            Request request = new Request.Builder()
                    .url(ServiceUrl)
                    .post(requestBody)
                    .build();

            Response response = null;

            response = client.newCall(request).execute();
            String serverResponse = response.body().string();
            serviceResponse.setResponseCode(response.code());
            if (!serverResponse.equals(""))
                serviceResponse.setJsonResult(new JSONObject(serverResponse));

            // System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return serviceResponse;
    }
}
