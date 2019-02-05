package com.teleone.chatadmin.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.teleone.chatadmin.PushNotifications.MobileToMobileMSG;
import com.teleone.chatadmin.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class SendActivity extends AppCompatActivity {
    private TextView tV_sendto;
    private EditText eT_Message;
    private Button btn_Send;
    private String myID,hisID,hisName;
    ProgressBar progressBar;
    private ImageView backBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        Initi();
        //do stuff
        tV_sendto.append(" "+hisName);
        btn_Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String message = eT_Message.getText().toString();
                if (!TextUtils.isEmpty(message)){
                    new MobileToMobileMSG(SendActivity.this).sendMSG("TeleOne",message,hisID,"note");

                }

            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }


    private void Initi(){
        tV_sendto = findViewById(R.id.Send_sendto);
        eT_Message = findViewById(R.id.Send_Message);
        btn_Send = findViewById(R.id.Send_doSend);
        progressBar= findViewById(R.id.Send_progress);
        backBtn = findViewById(R.id.note_back);

        //get extra
        hisID = getIntent().getStringExtra("userId");
        hisName = getIntent().getStringExtra("userName");
    }

}
