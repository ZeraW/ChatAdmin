package com.teleone.chatadmin.Activity;

import android.content.ServiceConnection;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public abstract class BaseActivity extends AppCompatActivity {
     FirebaseFirestore mFireStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFireStore = FirebaseFirestore.getInstance();

    }



    public void goOnline(){
        if(FirebaseAuth.getInstance().getUid()!=null) {
            mFireStore.collection("Users").document(FirebaseAuth.getInstance().getUid()).update("status", "on");
        }
    }

    public void goOfline(String hisId){

            mFireStore.collection("Users").document(hisId).update("status", "off");

    }



}