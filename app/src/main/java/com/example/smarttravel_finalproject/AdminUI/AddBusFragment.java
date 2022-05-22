package com.example.smarttravel_finalproject.AdminUI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smarttravel_finalproject.AdminModel.Bus;
import com.example.smarttravel_finalproject.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class AddBusFragment extends Fragment {
    EditText soure,destination,seatsNum,price;
    Button addBus;
    FirebaseDatabase database=FirebaseDatabase.getInstance();


    public AddBusFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_add_bus, container, false);
        soure=view.findViewById(R.id.ed_source);
        destination=view.findViewById(R.id.ed_destination);
        seatsNum=view.findViewById(R.id.ed_seatNum);
        price=view.findViewById(R.id.ed_ticketprice);
        addBus=view.findViewById(R.id.btn_addbus);

        DatabaseReference reference=database.getReference("Buses");
        Bus bus=new Bus();

        addBus.setOnClickListener(view1 -> {
            Toast.makeText(container.getContext(), soure.getText().toString(), Toast.LENGTH_SHORT).show();
            //حجز id
            DatabaseReference modelLocation=reference.push();
            bus.setId(modelLocation.getKey());
            bus.setSource(soure.getText().toString());
            bus.setDestination(destination.getText().toString());
            bus.setNumOfSeats(seatsNum.getText().toString());
            bus.setTicketPrice(price.getText().toString());
            modelLocation.setValue(bus);

            Fragment fragment = new AdminHomeFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.adminfcv, fragment);
            fragmentTransaction.commit();

        });

        return view;
    }
}