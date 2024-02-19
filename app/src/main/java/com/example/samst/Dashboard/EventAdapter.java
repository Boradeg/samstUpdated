package com.example.samst.Dashboard;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.samst.DummyActivity;
import com.example.samst.R;

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
        return new ViewHolder(view, events); // Pass the events list to ViewHolder constructor
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

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView titleTextView;
        TextView eventLink;
        TextView descriptionTextView;
        TextView typeTextView;
        ImageView imageView;
        List<EventResponse> events; // Add this field to ViewHolder

        ViewHolder(@NonNull View itemView, List<EventResponse> events) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            eventLink = itemView.findViewById(R.id.eventLink);
            typeTextView = itemView.findViewById(R.id.Btn_type);
            imageView = itemView.findViewById(R.id.imageView);
            this.events = events; // Assign events from adapter to ViewHolder
            // Set click listener to the entire item view
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // Handle item click event here
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION && events != null) { // Check if events is not null
                // Get the clicked event from the ViewHolder's events list
                EventResponse clickedEvent = events.get(position);

                // Show a toast with the title of the clicked event
                if (clickedEvent != null) {
                    Intent intent = new Intent(itemView.getContext(), DummyActivity.class);

                    // Pass the YouTube link as an extra

                    // Start the YoutubeWebViewActivity
                    itemView.getContext().startActivity(intent);
                    Toast.makeText(itemView.getContext(), clickedEvent.getTitle(), Toast.LENGTH_SHORT).show();
                }
            }
        }

    }

}
