package com.example.ticaccontroller;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Login_request extends StringRequest {
    private static final String LOGIN_REQUEST_URL ="https://uteag1999.000webhostapp.com/Accontroller/login.php";
    private Map<String,String> params;
    public Login_request(String email, String password, Response.Listener<String> listener){
        super(Request.Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);
    }
    @Override
    public Map<String, String> getParams(){return params;}
}
