package com.yanus.carevent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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
        View view = inflater.inflate(R.layout.event_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventsRecyclerAdapter.ViewHolder holder, int position) {
        EventModel eventModel = states.get(position);
        holder.eventImageItem.setImageURI(eventModel.getImage());
    }

    @Override
    public int getItemCount() {
        return states.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView eventImageItem;

        ViewHolder(View view) {
            super(view);
            eventImageItem = view.findViewById(R.id.imageViewEvent);
        }
    }

}
