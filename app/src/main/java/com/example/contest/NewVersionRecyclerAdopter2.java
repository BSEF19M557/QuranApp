package com.example.contest;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NewVersionRecyclerAdopter2 extends RecyclerView.Adapter<NewVersionRecyclerAdopter2.ViewHolder> {
    private Context context;
    private ArrayList<String> list,ENG,URDU;
    public NewVersionRecyclerAdopter2(Context context,ArrayList<String>list,ArrayList<String>ENG,ArrayList<String>URDU ){
        this.context=context;
        this.list=list;
        this.ENG=ENG;
        this.URDU=URDU;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.new_version_quran_recyclerview2, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FontAndTranslationOnOff setStyle=new FontAndTranslationOnOff();
        if(!setStyle.isUrdu())
            holder.uaya.setVisibility(View.GONE);
        if(!setStyle.isEng())
            holder.eaya.setVisibility(View.GONE);

        holder.aaya.setText(list.get(position));
        holder.aaya.setTypeface(Typeface.createFromAsset(context.getAssets(), "noorehuda.ttf"));
        holder.aaya.setTypeface(holder.aaya.getTypeface(),Typeface.BOLD);
        holder.aaya.setTextSize(setStyle.getFontSize());

        holder.uaya.setText(URDU.get(position));
        holder.uaya.setTypeface(Typeface.createFromAsset(context.getAssets(), "JameelNooriNastaleeq.ttf"));
        holder.uaya.setTextSize(setStyle.getFontSize());

        holder.eaya.setText(ENG.get(position));
        holder.eaya.setTypeface(Typeface.createFromAsset(context.getAssets(), "tnr.ttf"));
        holder.eaya.setTypeface(holder.eaya.getTypeface(),Typeface.ITALIC);
        holder.eaya.setTextSize(setStyle.getFontSize());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView aaya,uaya,eaya;
        public LinearLayout linearLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.aaya = (TextView) itemView.findViewById(R.id.Aaya);
            this.uaya = (TextView) itemView.findViewById(R.id.Uaya);
            this.eaya = (TextView) itemView.findViewById(R.id.Eaya);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout2);
        }
    }

}
