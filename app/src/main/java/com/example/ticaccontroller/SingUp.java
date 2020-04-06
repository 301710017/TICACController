package com.example.ticaccontroller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class SingUp extends AppCompatActivity {

    Button btnSingIn, btnSingUp;
    EditText edtEmail, edtPassword, edtCoPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        btnSingIn=findViewById(R.id.btnSingIn);
        btnSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LoginActivity.class);
                startActivityForResult(intent, 0);
                finish();
            }
        });

        //Vinculamos los edit con los declarados en nuestro layout
        edtEmail=(EditText)findViewById(R.id.Email);
        edtPassword=(EditText)findViewById(R.id.Password);
        edtCoPassword=(EditText)findViewById(R.id.ConfirmPassword);
        btnSingUp=(Button)findViewById(R.id.btnSingUp);
        //Creamo el evento onClick para nuestro boton de agregar
        btnSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtPassword.getText().toString().equals(edtCoPassword.getText().toString())){
                    ejecutarServicio("https://uteag1999.000webhostapp.com/Accontroller/insertar_usuario.php");
                    Intent i = new Intent(SingUp.this, LoginActivity.class);
                    SingUp.this.startActivity(i);
                    SingUp.this.finish();

                }else{
                    AlertDialog.Builder alerta = new AlertDialog.Builder(SingUp.this);
                    alerta.setMessage("Las contraseñas no coinciden")
                            .setNegativeButton("Reintentar", null)
                            .create()
                            .show();
                }

            }
        });

    }
    //ejecutamos el servicio par insertar usuarios a nuestra tabla
    private void ejecutarServicio(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Operacion exitosa", Toast.LENGTH_SHORT).show();
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros= new HashMap<String, String>();

                //Le enviamos los datos con en metodo put, obtenemos los valores de los edit text
                parametros.put("email", edtEmail.getText().toString());
                parametros.put("password", edtPassword.getText().toString());

                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
