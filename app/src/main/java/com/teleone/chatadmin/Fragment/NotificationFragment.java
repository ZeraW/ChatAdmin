package com.teleone.chatadmin.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.teleone.chatadmin.Adapter.NotificationAdapter;
import com.teleone.chatadmin.Model.UsersModel;
import com.teleone.chatadmin.R;

import java.util.ArrayList;
import java.util.List;


public class NotificationFragment extends Fragment {
    View view;
    private RecyclerView mRecyclerView;
    private NotificationAdapter mAdapter;
    private List<UsersModel> mList;
    private FirebaseFirestore mFireStore;


    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_notifications, container, false);
        mFireStore = FirebaseFirestore.getInstance();

        mList = new ArrayList<>();

        mAdapter = new NotificationAdapter(getContext(),mList);
        mRecyclerView = view.findViewById(R.id.Recycler_note);
        mRecyclerView.hasFixedSize();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);


        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        mList.clear();
        if (getActivity()!=null){


            mFireStore.collection("Users").addSnapshotListener(getActivity(), new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                    mList.add(new UsersModel("all","All Users","null","on"));
                    for (DocumentChange doc : documentSnapshots.getDocumentChanges()){
                        if (doc.getType()== DocumentChange.Type.ADDED){
                            String user_Id = doc.getDocument().getId();
                            String name = doc.getDocument().getString("name");
                            String image = doc.getDocument().getString("image");
                            String online = doc.getDocument().getString("status");


                            mList.add(new UsersModel(user_Id, name, image,online));
                            mAdapter.notifyDataSetChanged();

                        }

                    }
                }
            });
        }

    }

}
