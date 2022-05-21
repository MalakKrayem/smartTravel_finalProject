package com.example.smarttravel_finalproject.UserAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smarttravel_finalproject.AdminModel.Bus;
import com.example.smarttravel_finalproject.R;

import java.util.ArrayList;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.ViewHolder> {
    ArrayList<Bus> trips;
    Context context;

    public TripAdapter(ArrayList<Bus> trips, Context context) {
        this.trips = trips;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("mm","start create");

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.bus_user_item, null, false);
        Log.d("mm","end create");

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("mm","start binding");
        Bus trip =trips.get(position);
        holder.source.setText(trip.getSource());
        holder.destination.setText(trip.getDestination());
        holder.seats.setText(trip.getNumOfSeats()+"seats");
        holder.price.setText( trip.getTicketPrice()+"$");
        Log.d("mm","end binding");

    }

    @Override
    public int getItemCount() {
        return trips.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView source,destination,price,seats;
        Button bookTrip;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            source=itemView.findViewById(R.id.tv_sourceUser);
            destination=itemView.findViewById(R.id.tv_destinationUser);
            price=itemView.findViewById(R.id.tv_priceUser);
            seats=itemView.findViewById(R.id.tv_seatsUser);
            bookTrip=itemView.findViewById(R.id.btn_bookTrip);
        }
    }
}
