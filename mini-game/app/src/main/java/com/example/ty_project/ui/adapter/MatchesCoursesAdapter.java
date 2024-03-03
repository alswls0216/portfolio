/*
 * Copyright (c) 2020. rogergcc
 */

package com.example.ty_project.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ty_project.databinding.ItemShopCardBinding;
import com.example.ty_project.ui.listeners.MatchCourseClickListener;
import com.example.ty_project.ui.model.MatchCourse;
import com.bumptech.glide.Glide;

import java.util.List;

//####랭킹페이지 어댑터
public class MatchesCoursesAdapter extends RecyclerView.Adapter<MatchesCoursesAdapter.ViewHolder> {

    private final List<MatchCourse> mData;
    private final MatchCourseClickListener matchCourseClickListener;

    public MatchesCoursesAdapter(List<MatchCourse> mData, MatchCourseClickListener listener) {
        this.mData = mData;
        this.matchCourseClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        ItemShopCardBinding itemCardBinding = ItemShopCardBinding.inflate(inflater, parent, false);
        return new ViewHolder(itemCardBinding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.setBind(mData.get(position));

        holder.itemView.setOnClickListener(v -> matchCourseClickListener.onScrollPagerItemClick(mData.get(holder.getAdapterPosition()), holder.itemCardBinding.image));

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ItemShopCardBinding itemCardBinding;

        public ViewHolder(@NonNull ItemShopCardBinding cardBinding) {
            super(cardBinding.getRoot());
            this.itemCardBinding = cardBinding;

            //this.itemRecyclerMealBinding.
        }

        void setBind(MatchCourse matchCourse) {

            itemCardBinding.tvTitulo.setText(matchCourse.getName());
            itemCardBinding.tvCantidadCursos.setText(matchCourse.getNumberOfCourses());

            Glide.with(itemView.getContext())
                    .load(matchCourse.getImageResource())
//                .transform(new CenterCrop(), new RoundedCorners(24))
//                .transform(new RoundedCorners(40))
                    .into(itemCardBinding.image);
        }
    }

}
