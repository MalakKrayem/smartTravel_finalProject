package com.example.smarttravel_finalproject.AdminUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.smarttravel_finalproject.R;
import com.example.smarttravel_finalproject.auth.Login;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

public class AdminsMainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admins_main);
        setTitle("Admin");
        bottomNavigationView = findViewById(R.id.admin_bn);
        bottomNavigationView.setSelectedItemId(R.id.adminhome);
        fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.adminfcv, new AdminHomeFragment()).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.adminhome:
                        FragmentTransaction fthome = fm.beginTransaction();
                        fthome.replace(R.id.adminfcv, new AdminHomeFragment()).commit();
                        break;
                    case R.id.addadmin:
                        FragmentTransaction ftadd = fm.beginTransaction();
                        ftadd.replace(R.id.adminfcv, new AddAdminFragment()).commit();
                        break;
                    case R.id.addbus:
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.adminfcv, new AddBusFragment()).commit();
                        break;
                }
                return true;
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(this, Login.class);
                startActivity(i);
                return true;
            default:
               return super.onOptionsItemSelected(item);
        }
    }
}