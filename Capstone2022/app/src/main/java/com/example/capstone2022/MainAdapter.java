package com.example.capstone2022;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    //변수 생성
    ContactFragment activity;
    ArrayList<ContactModel> arrayList;
    //리스너 인터페이스 생성
    public interface OnItemClickListener
    {
        void onItemClick(View v, int pos);
    }
    public interface OnItemLongClickListener
    {
        void onItemLongClick(View v, int pos);
    }
    //프래그먼츠에서 호출할 전처리 함수
    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.mListener = listener;
    }
    public void setOnItemLongClickListener(OnItemLongClickListener listener)
    {
        this.mLongListener = listener;
    }
    // 리스너 객체 참조를 저장하는 변수
    private MainAdapter.OnItemClickListener mListener = null;
    private MainAdapter.OnItemLongClickListener mLongListener = null;

    // constructor 생성
    public MainAdapter(ContactFragment activity, ArrayList<ContactModel> arrayList) {
        this.activity = activity;
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //뷰 정의
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
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
        Log.i(getClass().getSimpleName(), "getItemCount called: " + arrayList.size());
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

            //터치 인식 리스너 설정
            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if (mListener != null) {
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(v, pos);
                        }
                    }
                }
            });
            //롱 터치(홀드) 인식 리스너 설정
            itemView.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {
                    if (mLongListener!=null) {
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION) {
                            mLongListener.onItemLongClick(v, pos);
                        }
                    }
                    return true;
                }
            });
        }
    }

}
