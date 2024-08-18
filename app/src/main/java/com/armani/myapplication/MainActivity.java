package com.armani.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.armani.myapplication.config.BaseConfig;
import com.armani.myapplication.config.Rest;
import com.armani.myapplication.config.ServiceResponse;
import com.armani.myapplication.config.dto.UserDTO;
import com.armani.myapplication.user.dto.UserInfoDTO;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    Button btnLogin;
    EditText txtUsername;
    EditText txtPassword;
    Intent intent;
    String username;
    String family;
    String password;

    UserInfoDTO userInfoDTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userInfoDTO = new UserInfoDTO();

        btnLogin = findViewById(R.id.btnLogin);
        txtUsername = findViewById(R.id.username);
        txtPassword = findViewById(R.id.password);
        intent = new Intent(MainActivity.this, HomeActivity.class);
        family = "";

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = txtUsername.getText().toString();
                password = txtPassword.getText().toString();
                if (username.equals("") || password.equals(""))
                    msgBox("you have to enter user and password!");
                else
                    loadUserInfo();
            }
        });
    }

    private void loadUserInfo() {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {
                    JSONObject userRequestJSON = processRequest(username, password);
                    ServiceResponse serviceResponse = Rest.post(BaseConfig.USER_INFO, null, userRequestJSON, true);
                    if (serviceResponse.getResponseCode() == 200) {
                        JSONObject result = serviceResponse.getJsonResult();
                        userInfoDTO.setName(result.getString("name"));
                        userInfoDTO.setFamily(result.getString("family"));
                        userInfoDTO.setUsername(result.getString("username"));
                        loginSuccessful();
                    } else {
                        accessDenied();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

    }

    private void loginSuccessful() {
        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                username = userInfoDTO.getUsername();
                family = userInfoDTO.getFamily();
                if (!family.equals("")) {
                    BaseConfig.userDTO = new UserDTO(userInfoDTO.getUsername(), userInfoDTO.getName(), userInfoDTO.getFamily());
                    startActivity(intent);
                } else
                    msgBox("user or password is incorrect!");
            }
        });
    }

    private void accessDenied() {
        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                msgBox("user or password is incorrect!");
            }
        });
    }

    private JSONObject processRequest(String username, String password) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("username", username);
            jsonObject.put("password", password);
            return jsonObject;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void msgBox(String message) {
        BaseConfig.msgBox(MainActivity.this, message);
    }

}