package com.example.smarttravel_finalproject.UserUI;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.smarttravel_finalproject.R;
import com.example.smarttravel_finalproject.UserModel.Trip;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class UserListFragment extends Fragment {
    RecyclerView userTrips;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    FirebaseRecyclerAdapter adapter;

    public UserListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_user_list, container, false);
        Context context=container.getContext();
        DatabaseReference reference=database.getReference("Buses");

        Query query = reference.limitToLast(50);
        FirebaseRecyclerOptions<Trip> options=new FirebaseRecyclerOptions.Builder<Trip>()
                .setQuery(query,Trip.class).build();

        adapter=new FirebaseRecyclerAdapter<Trip, UserTripsHolder> (options){
            @NonNull
            @Override
            public UserTripsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new UserTripsHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket,parent,false));
            }

            @Override
            protected void onBindViewHolder(@NonNull UserTripsHolder holder, int position, @NonNull Trip model) {
                holder.source.setText("From: "+model.getSource());
                holder.destination.setText("To: "+model.getDestination());
                holder.price.setText(model.getPrice());
            }
        };
        userTrips=view.findViewById(R.id.rv_UserTrips);
        LinearLayoutManager manager=new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        userTrips.setAdapter(adapter);
        userTrips.setLayoutManager(manager);
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        if(adapter != null){
            adapter.startListening();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(adapter != null){
            adapter.stopListening();
        }
    }
}
class UserTripsHolder extends RecyclerView.ViewHolder{
    TextView source,destination,price;

    public UserTripsHolder(@NonNull View itemView) {
        super(itemView);
        source=itemView.findViewById(R.id.tv_from);
        destination=itemView.findViewById(R.id.tv_todes);
        price=itemView.findViewById(R.id.tv_ticketPrice);

    }
}

