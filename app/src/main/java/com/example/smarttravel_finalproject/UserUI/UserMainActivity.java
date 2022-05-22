package com.example.smarttravel_finalproject.UserUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.smarttravel_finalproject.R;
import com.example.smarttravel_finalproject.auth.Login;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Locale;

public class UserMainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    FragmentManager fm;
    private Locale myLocale;

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

            }
            return true;
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.userbarmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.userlogout:
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(this, Login.class);
                startActivity(i);
                return true;
            case R.id.changeLanguage:
                String lang = "en";
                if(lang == "en"){
                    lang="ar";
                }else {
                    lang="en";
                }
                changeLang(lang);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void loadLocale()
    {
        String langPref = "Language";
        SharedPreferences prefs = getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
        String language = prefs.getString(langPref, "");
        changeLang(language);
    }
    public void saveLocale(String lang)
    {
        String langPref = "Language";
        SharedPreferences prefs = getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(langPref, lang);
        editor.commit();
    }
    public void changeLang(String lang)
    {
        if (lang.equalsIgnoreCase(""))
            return;
        myLocale = new Locale(lang);
        saveLocale(lang);
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = myLocale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    }

    @Override
    public void onConfigurationChanged(android.content.res.Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (myLocale != null){
            newConfig.locale = myLocale;
            Locale.setDefault(myLocale);
            getBaseContext().getResources().updateConfiguration(newConfig, getBaseContext().getResources().getDisplayMetrics());
        }
    }
}