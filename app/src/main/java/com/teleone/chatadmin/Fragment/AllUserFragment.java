package com.teleone.chatadmin.Fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.teleone.chatadmin.Adapter.AllUsersAdapter;
import com.teleone.chatadmin.Model.UsersModel;
import com.teleone.chatadmin.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AllUserFragment extends Fragment {
    View view;
    private RecyclerView mRecyclerView;
    private AllUsersAdapter mAdapter;
    private List<UsersModel> mList;
    private FirebaseFirestore mFireStore;
    private SwipeRefreshLayout mRefreshLayout;


    public AllUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_users, container, false);
        mFireStore = FirebaseFirestore.getInstance();

        mList = new ArrayList<>();

        mRefreshLayout = view.findViewById(R.id.user_swipe_layout);


        mAdapter = new AllUsersAdapter(getContext(),mList);
        mRecyclerView = view.findViewById(R.id.recycler_users);
        mRecyclerView.hasFixedSize();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);


        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mList.clear();
                getUsers();
            }
        });


        return view;
    }




    @Override
    public void onStart() {
        super.onStart();
        getUsers();

    }

    private void getUsers(){
        mList.clear();
        if (getActivity()!=null){


            mFireStore.collection("Users").addSnapshotListener(getActivity(), new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                    for (DocumentChange doc : documentSnapshots.getDocumentChanges()){
                        if (doc.getType()== DocumentChange.Type.ADDED){
                            String user_Id = doc.getDocument().getId();
                            String name = doc.getDocument().getString("name");
                            String image = doc.getDocument().getString("image");
                            String online = doc.getDocument().getString("status");


                            mList.add(new UsersModel(user_Id, name, image,online));
                            mAdapter.notifyDataSetChanged();
                            mRefreshLayout.setRefreshing(false);


                        }

                    }
                }
            });
        }
    }
}


