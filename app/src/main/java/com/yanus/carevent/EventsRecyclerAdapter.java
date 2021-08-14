package com.yanus.carevent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.yanus.carevent.model.EventModel;

import java.util.List;

public class EventsRecyclerAdapter extends RecyclerView.Adapter<EventsRecyclerAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<EventModel> states;

    EventsRecyclerAdapter(Context context, List<EventModel> states) {
        this.states = states;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public EventsRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //View view = inflater.inflate(R.layout., parent, false);
        return null;//new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventsRecyclerAdapter.ViewHolder holder, int position) {
        EventModel state = states.get(position);
//        holder.flagView.setImageResource(state.getFlagResource());
//        holder.nameView.setText(state.getName());
//        holder.capitalView.setText(state.getCapital());
    }

    @Override
    public int getItemCount() {
        return states.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
//        final ImageView flagView;
//        final TextView nameView, capitalView;

        ViewHolder(View view) {
            super(view);
//            flagView = (ImageView) view.findViewById(R.id.flag);
//            nameView = (TextView) view.findViewById(R.id.name);
//            capitalView = (TextView) view.findViewById(R.id.capital);
        }
    }

}
