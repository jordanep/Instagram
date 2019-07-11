package com.example.instagram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.instagram.fragments.CreatePostFragment;
import com.example.instagram.fragments.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    public static final String HOME_FRAGMENT_TAG = "HOME";
    public static final String CREATE_POST_FRAGMENT_TAG = "CREATE_POST";
    public static final String NOTIFICATIONS_FRAGMENT_TAG = "NOTIFICATIONS";
    public static final String PROFILE_FRAGMENT_TAG = "PROFILE";

    private BottomNavigationView bottomNavigationView;

    public FragmentManager fragmentManager;
    public Fragment homeFragment;
    public Fragment createPostFragment;
    public Fragment notificationsFragment;
    public Fragment profileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        homeFragment = new HomeFragment();
        createPostFragment = new CreatePostFragment();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        fragmentManager.beginTransaction()
                                .replace(R.id.flPlaceholder, homeFragment, HOME_FRAGMENT_TAG)
                                .commit();
                        break;
                    case R.id.createPost:
                        fragmentManager.beginTransaction()
                                .replace(R.id.flPlaceholder, createPostFragment,
                                        CREATE_POST_FRAGMENT_TAG)
                                .commit();
                        break;
                    /*case R.id.notifications:
                        *//*fragmentManager.beginTransaction()
                                .replace(R.id.flPlaceholder, notificationsFragment,
                                        NOTIFICATIONS_FRAGMENT_TAG)
                                .commit();*//*
                        break;
                    case R.id.profile:
                        *//*fragmentManager.beginTransaction()
                                .replace(R.id.flPlaceholder, profileFragment,
                                        PROFILE_FRAGMENT_TAG)
                                .commit();*//*
                        break;*/
                    default:
                        return false;
                }
                return true;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.home);

    }
}
