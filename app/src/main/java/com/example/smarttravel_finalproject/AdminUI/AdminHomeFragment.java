package com.example.smarttravel_finalproject.AdminUI;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.smarttravel_finalproject.AdminModel.Bus;
import com.example.smarttravel_finalproject.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class AdminHomeFragment extends Fragment {

    RecyclerView buses;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    FirebaseRecyclerAdapter adapter;

    public AdminHomeFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_admin_home, container, false);
        Context context=container.getContext();
        DatabaseReference reference=database.getReference("Buses");

        Query query = reference.limitToLast(50);
        FirebaseRecyclerOptions<Bus> options=new FirebaseRecyclerOptions.Builder<Bus>()
                .setQuery(query,Bus.class).build();

            adapter=new FirebaseRecyclerAdapter<Bus, ViewHolder> (options){

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.bus_item,parent,false));

            }

            @Override
            protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Bus model) {
                holder.source.setText(model.getSource());
                holder.destination.setText(model.getDestination());
                holder.seats.setText(model.getNumOfSeats()+ " seats");
                holder.price.setText(model.getTicketPrice()+ " $");
                holder.delete.setOnClickListener(view1 -> {
                    reference.child(model.getId()).removeValue();
                });
            }
        };
        buses=view.findViewById(R.id.rv_busAdmin);
        LinearLayoutManager manager=new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        buses.setLayoutManager(manager);
        buses.setAdapter(adapter);

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
class ViewHolder extends RecyclerView.ViewHolder{
    TextView source,destination,price,seats;
    Button delete,update;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        source=itemView.findViewById(R.id.tv_source);
        destination=itemView.findViewById(R.id.tv_destination);
        price=itemView.findViewById(R.id.tv_price);
        seats=itemView.findViewById(R.id.tv_seats);
        delete=itemView.findViewById(R.id.btn_deleteBus);
        update=itemView.findViewById(R.id.btn_updateBus);



    }
}

