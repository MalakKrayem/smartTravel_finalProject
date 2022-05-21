package com.example.smarttravel_finalproject.UserUI;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smarttravel_finalproject.AdminModel.Bus;
import com.example.smarttravel_finalproject.R;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;


public class UserHomeFragment extends Fragment {
    ArrayList<Bus> trips = new ArrayList<>();
    RecyclerView rv_trips;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    FirebaseRecyclerAdapter adapter;



    public UserHomeFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_user_home, container, false);
        Context context = container.getContext();
        DatabaseReference reference=database.getReference("Buses");

        Query query = reference.limitToLast(50);
        FirebaseRecyclerOptions<Bus> options=new FirebaseRecyclerOptions.Builder<Bus>()
                .setQuery(query,Bus.class).build();
        adapter=new FirebaseRecyclerAdapter<Bus,TripHolder>(options) {

            @NonNull
            @Override
            public TripHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new TripHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.bus_user_item,parent,false));

            }

            @Override
            protected void onBindViewHolder(@NonNull TripHolder holder, int position, @NonNull Bus model) {
                holder.source.setText(model.getSource());
                holder.destination.setText(model.getDestination());
                holder.seats.setText(model.getNumOfSeats()+ " seats");
                holder.price.setText(model.getTicketPrice()+ " $");
            }
        };
        rv_trips = view.findViewById(R.id.rv_UserTrips);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_trips.setLayoutManager(manager);
        rv_trips.setAdapter(adapter);
        Toast.makeText(context, "adapter fine", Toast.LENGTH_SHORT).show();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(adapter != null){
            Log.d("mm","check fine");

            adapter.startListening();
            Log.d("mm","listen fine");

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
class TripHolder extends RecyclerView.ViewHolder{
    TextView source,destination,price,seats;
    Button bookTrip;

    public TripHolder(@NonNull View itemView) {
        super(itemView);
        source=itemView.findViewById(R.id.tv_sourceUser);
        destination=itemView.findViewById(R.id.tv_destinationUser);
        price=itemView.findViewById(R.id.tv_priceUser);
        seats=itemView.findViewById(R.id.tv_seatsUser);
        bookTrip=itemView.findViewById(R.id.btn_bookTrip);
    }
}

//                holder.bookTrip.setOnClickListener(view1 -> {
////                    String avaliableSeats=model.getNumOfSeats();
////                    int seat=Integer.parseInt(avaliableSeats);
////                    seat-=seat;
////                    model.setNumOfSeats(seat+"");
////                    reference.child(model.getId()).setValue(model.getNumOfSeats());
////                    DatabaseReference reference=database.getReference("My Trips");
////                    Trip trip=new Trip();
////                    DatabaseReference modelLocation=reference.push();
////                    trip.setId(modelLocation.getKey());
////                    trip.setSource(model.getSource());
////                    trip.setDestination(model.getDestination());
////                    trip.setPrice(model.getTicketPrice());
////                    modelLocation.setValue(trip);
////                    Fragment fragment = new UserListFragment();
////                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
////                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
////                    fragmentTransaction.replace(R.id.userfcv, fragment);
////                    fragmentTransaction.commit();
//                });