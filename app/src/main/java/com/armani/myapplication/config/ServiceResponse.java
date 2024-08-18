package com.armani.myapplication.config;

import org.json.JSONArray;
import org.json.JSONObject;

public class ServiceResponse {

    JSONObject jsonResult;

    JSONArray jsonArrayResult;

    int responseCode;

    public JSONObject getJsonResult() {
        return jsonResult;
    }

    public void setJsonResult(JSONObject jsonResult) {
        this.jsonResult = jsonResult;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public JSONArray getJsonArrayResult() {
        return jsonArrayResult;
    }

    public void setJsonArrayResult(JSONArray jsonArrayResult) {
        this.jsonArrayResult = jsonArrayResult;
    }

}
