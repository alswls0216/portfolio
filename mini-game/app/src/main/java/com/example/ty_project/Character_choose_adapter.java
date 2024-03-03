package com.example.ty_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Character_choose_adapter extends RecyclerView.Adapter<Character_choose_adapter.ViewHolder> {

    private ArrayList<Character_choose_model> mData = null ;
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView stag_item_course, stag_item_quantity_course ;
        ImageView card_view_image;

        ViewHolder(View itemView) {
            super(itemView) ;
            stag_item_course = itemView.findViewById(R.id.stag_item_course) ;
            stag_item_quantity_course = itemView.findViewById(R.id.stag_item_quantity_course) ;
            card_view_image = itemView.findViewById(R.id.card_view_image) ;
        }
    }

    Character_choose_adapter(ArrayList<Character_choose_model> list) {
        mData = list ;
    }
    // 생성자 :: 데이터 리스트를 내부 변수인 mData에 저장

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.item_card, parent, false) ;
        ViewHolder vh = new ViewHolder(view) ;

        return vh ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Character_choose_model text = mData.get(position) ;
        holder.stag_item_course.setText(text.getCourseTitle()) ;
        holder.stag_item_quantity_course.setText(text.getCourseTitle()) ;
    }

    @Override
    public int getItemCount() {
        return mData.size() ;
    }
}
