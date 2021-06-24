package com.example.firebaserealtimedb_recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {

    private ArrayList<MyItem> arrayList;
    private Context context;

    public MyRecyclerViewAdapter(ArrayList<MyItem> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // listView가 Adapter에 연결된 후, ViewHolder 최초 생성
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewAdapter.MyViewHolder holder, int position) {
        // 각 item 매칭

        Glide.with(holder.itemView)
                .load(arrayList.get(position).getProfile())
                .into(holder.img_profile);

        holder.tv_id.setText(arrayList.get(position).getId());
        holder.tv_name.setText(arrayList.get(position).getName());
        holder.tv_age.setText(String.valueOf(arrayList.get(position).getAge()));
    }

    @Override
    public int getItemCount() {
        if (arrayList != null)
            return arrayList.size();
        else
            return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView img_profile;
        TextView tv_id, tv_name, tv_age;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.img_profile = itemView.findViewById(R.id.img_profile);
            this.tv_id = itemView.findViewById(R.id.tv_id);
            this.tv_name = itemView.findViewById(R.id.tv_name);
            this.tv_age = itemView.findViewById(R.id.tv_age);
        }
    }
}
