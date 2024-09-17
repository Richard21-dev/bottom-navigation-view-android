package com.example.bottonnavview;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        // 8 atrapar el bottom navigation view del activity main
        bottomNav= findViewById(R.id.bottomNavView);
        //9 llamar al metodo para mostrar el home fragment
        replaceFragment(new HomeFragment());
        //10 llamar al metodo setOnItemSelectedListener para cambiar los fragments
        bottomNav.setOnItemSelectedListener(item -> {
            Fragment fragmentSelect= null;
            if(item.getItemId() ==R.id.home){
                fragmentSelect= new HomeFragment();
            }
            if(item.getItemId()==R.id.news){
                fragmentSelect= new NewsFragment();
            }
            if(item.getItemId()==R.id.chat){
                fragmentSelect= new ChatFragment();
            }
            if(item.getItemId()==R.id.notifications){
                fragmentSelect= new NotificationsFragment();
            }
            if(item.getItemId()==R.id.settings){
                fragmentSelect= new SettingsFragment();
            }

            if(fragmentSelect!=null){
                replaceFragment(fragmentSelect);
            }
            return true;
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    //7 crear el metodo replaceFragment
    private void replaceFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_main,fragment)
                .commit();
    }
}