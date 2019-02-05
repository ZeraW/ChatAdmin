package com.teleone.chatadmin.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.teleone.chatadmin.Activity.ChatActivity;
import com.teleone.chatadmin.Activity.SendActivity;
import com.teleone.chatadmin.Model.UsersModel;
import com.teleone.chatadmin.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by Hima on 6/19/2018.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    Context context;
    private List<UsersModel> mList;

    public NotificationAdapter(Context context, List<UsersModel> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.row_users,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.userTitle.setText(mList.get(position).getName());

        if (position==0){
            Picasso.with(context).load(mList.get(position).getImage()).fit().centerCrop().placeholder(R.drawable.team).into(holder.userImg);

        }else {
            Picasso.with(context).load(mList.get(position).getImage()).fit().centerCrop().placeholder(R.drawable.default_avatar).into(holder.userImg);

        }


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,SendActivity.class);
                intent.putExtra("userId",mList.get(position).getId());
                intent.putExtra("userName",mList.get(position).getName());
                intent.putExtra("userImg",mList.get(position).getImage());
                intent.putExtra("status",mList.get(position).getStatus());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View mView;
        private CircleImageView userImg;
        private TextView userTitle;
        public ViewHolder(View itemView) {
            super(itemView);

            mView = itemView;

            userImg = mView.findViewById(R.id.row_img);
            userTitle = mView.findViewById(R.id.row_title);
        }
    }
}
