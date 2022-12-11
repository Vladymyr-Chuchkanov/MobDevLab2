package com.chuchkanov.mobdevlab2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PhotoAdapter  extends RecyclerView.Adapter<PhotoAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final List<Helps> helps;

    PhotoAdapter(Context context, List<Helps> states) {
        this.helps = states;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public PhotoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PhotoAdapter.ViewHolder holder, int position) {
        Helps state = helps.get(position);
        holder.flagView.setImageResource(state.getFlagResource());
        holder.textView.setText(state.getText());
    }

    @Override
    public int getItemCount() {
        return helps.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView flagView;
        final TextView textView;
        ViewHolder(View view){
            super(view);
            flagView = view.findViewById(R.id.flag);
            textView = view.findViewById(R.id.text);
        }
    }
}