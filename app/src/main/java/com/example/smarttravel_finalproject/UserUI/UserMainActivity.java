package com.example.smarttravel_finalproject.UserUI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.smarttravel_finalproject.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UserMainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);

        setTitle("Smart Travel");
        bottomNavigationView=findViewById(R.id.user_bn);
        bottomNavigationView.setSelectedItemId(R.id.userhome);
        fm=getSupportFragmentManager();
        FragmentTransaction ft= fm.beginTransaction();
        ft.add(R.id.userfcv,new UserHomeFragment()).commit();

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.userhome:
                    FragmentTransaction fthome= fm.beginTransaction();
                    fthome.replace(R.id.userfcv,new UserHomeFragment()).commit();
                    break;
                case R.id.userlist:
                    FragmentTransaction ftlist= fm.beginTransaction();
                    ftlist.replace(R.id.userfcv,new UserListFragment()).commit();
                    break;
                case R.id.usersupport:
                    FragmentTransaction ftsupport= fm.beginTransaction();
                    ftsupport.replace(R.id.userfcv,new UserSupportFragment()).commit();
                    break;
                case R.id.usersetting:
                    FragmentTransaction ftsetting= fm.beginTransaction();
                    ftsetting.replace(R.id.userfcv,new UserSettingFragment()).commit();
                    break;
            }
            return true;
        });
    }
}