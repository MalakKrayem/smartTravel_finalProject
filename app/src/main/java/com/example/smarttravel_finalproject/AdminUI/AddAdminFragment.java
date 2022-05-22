package com.example.smarttravel_finalproject.AdminUI;

import android.content.Context;
import android.content.Intent;
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

import com.example.smarttravel_finalproject.R;
import com.example.smarttravel_finalproject.auth.Login;
import com.example.smarttravel_finalproject.auth.Signup;
import com.example.smarttravel_finalproject.auth.UserAndAdmin;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class AddAdminFragment extends Fragment {
    EditText fname,lname,email,phone,password;
    Button addAdmin;
    FirebaseAuth auth;
    FirebaseDatabase database=FirebaseDatabase.getInstance();

    public AddAdminFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_add_admin, container, false);
        Context context=container.getContext();
        auth = FirebaseAuth.getInstance();
        DatabaseReference reference=database.getReference("Users&Admins");
        UserAndAdmin person=new UserAndAdmin();
        fname=view.findViewById(R.id.ed_fname_admin);
        lname=view.findViewById(R.id.ed_lname_admin);
        email=view.findViewById(R.id.ed_email_addadmin);
        phone=view.findViewById(R.id.ed_phoneAdmin);
        password=view.findViewById(R.id.ed_password_addadmin);
        addAdmin=view.findViewById(R.id.btn_addadmin);
        addAdmin.setOnClickListener(view1 -> {
            String emailAddress=email.getText().toString();
            String pass=password.getText().toString();
            if(emailAddress.equals("")){
                Log.d("mm","email null");
            }
            if(pass.equals("")){
                Log.d("mm","pass null");
            }
            Task<AuthResult> result=auth.createUserWithEmailAndPassword(emailAddress,pass);
            DatabaseReference modelLocation=reference.push();
            person.setId(modelLocation.getKey());
            person.setFirstName(fname.getText().toString());
            person.setLastName(lname.getText().toString());
            person.setEmail(emailAddress);
            person.setPhoneNumber(phone.getText().toString());
            person.setType("Admin");
            modelLocation.setValue(person);
            Log.d("mm","hello1");
            result.addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    Toast.makeText(context, "Added Successfuly", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "Added Faild", Toast.LENGTH_SHORT).show();
                }

            });
            Fragment fragment = new AdminHomeFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.adminfcv, fragment);
            fragmentTransaction.commit();
        });

        return view;
    }
}