package com.example.capstone2022;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder>{
    //변수 생성
    ContactFragment activity;
    ArrayList<ContactModel> arrayList;

    // constructor 생성
    public MainAdapter(ContactFragment activity, ArrayList<ContactModel> arrayList){
        this.activity =activity;
        this.arrayList =arrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //뷰 정의
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contact, parent, false);
        //뷰 반환
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //ContactModel 정의
        ContactModel model = arrayList.get(position);

        //명의 설정
        holder.tvName.setText(model.getName());
        holder.tvNumber.setText(model.getNumber());
    }

    @Override
    public int getItemCount() {
        //리스트 사이즈 반환
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //변수 생성
        TextView tvName, tvNumber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //변수 설정
            tvName = itemView.findViewById(R.id.tv_name);
            tvNumber = itemView.findViewById(R.id.tv_number);
        }
    }
}
