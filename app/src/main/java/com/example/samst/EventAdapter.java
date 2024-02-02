package com.example.samst;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    private List<EventResponse> events;

    public EventAdapter(List<EventResponse> events) {
        this.events = events;
    }

    public List<EventResponse> getEvents() {
        return events;
    }

    public void updateEvents(List<EventResponse> newEvents) {
        events = newEvents;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EventResponse event = events.get(position);
        holder.titleTextView.setText(event.getTitle());
        holder.descriptionTextView.setText(event.getDescription());
        holder.eventLink.setText(event.getLink());
        holder.typeTextView.setText(event.getType());


        // Load and display the image using Glide
        Glide.with(holder.itemView.getContext())
                .load(buildImageUrl(event.getImage()))  // Use the getImage method
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    private String buildImageUrl(String imagePath) {
        return "https://sidhman.in/skmarati/new/admin/admin-api/" + imagePath;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView eventLink;
        TextView descriptionTextView;
        TextView typeTextView;
        ImageView imageView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            eventLink = itemView.findViewById(R.id.eventLink);
            typeTextView = itemView.findViewById(R.id.Btn_type);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
