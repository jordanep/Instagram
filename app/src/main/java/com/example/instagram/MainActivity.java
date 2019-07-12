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
import com.example.instagram.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    public static final String HOME_FRAGMENT_TAG = "HOME";
    public static final String CREATE_POST_FRAGMENT_TAG = "CREATE_POST";
    public static final String NOTIFICATIONS_FRAGMENT_TAG = "NOTIFICATIONS";
    public static final String PROFILE_FRAGMENT_TAG = "PROFILE";

    private BottomNavigationView bottomNavigationView;

    public FragmentManager fragmentManager;
    public static HomeFragment homeFragment;
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
        profileFragment = new ProfileFragment();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        Fragment fragment;
                        switch (menuItem.getItemId()) {
                            case R.id.home:
                                fragment = fragmentManager.findFragmentByTag(HOME_FRAGMENT_TAG);
                                if (fragment != null) {
                                    fragmentManager.beginTransaction()
                                            .replace(R.id.flPlaceholder, fragment)
                                            .commit();
                                } else {
                                    fragmentManager.beginTransaction()
                                            .replace(R.id.flPlaceholder, homeFragment, HOME_FRAGMENT_TAG)
                                            .commit();
                                }
                                break;
                            case R.id.createPost:
                                fragment = fragmentManager.findFragmentByTag(CREATE_POST_FRAGMENT_TAG);
                                if (fragment != null) {
                                    fragmentManager.beginTransaction()
                                            .replace(R.id.flPlaceholder, fragment)
                                            .commit();
                                } else {
                                    fragmentManager.beginTransaction()
                                            .replace(R.id.flPlaceholder, createPostFragment,
                                                    CREATE_POST_FRAGMENT_TAG)
                                            .commit();
                                }
                                break;
                            /*case R.id.notifications:
                                fragmentManager.beginTransaction()
                                        .replace(R.id.flPlaceholder, notificationsFragment,
                                                NOTIFICATIONS_FRAGMENT_TAG)
                                        .commit();
                                break;*/
                            case R.id.profile:
                                fragment = fragmentManager.findFragmentByTag(PROFILE_FRAGMENT_TAG);
                                if (fragment != null) {
                                    fragmentManager.beginTransaction()
                                            .replace(R.id.flPlaceholder, fragment)
                                            .commit();
                                } else {
                                    fragmentManager.beginTransaction()
                                            .replace(R.id.flPlaceholder, profileFragment,
                                                    PROFILE_FRAGMENT_TAG)
                                            .commit();
                                }
                                break;
                            default:
                                fragmentManager.beginTransaction()
                                        .replace(R.id.flPlaceholder, homeFragment, HOME_FRAGMENT_TAG)
                                        .commit();
                        }
                        return true;
                    }
                });
        bottomNavigationView.setSelectedItemId(R.id.home);

    }
}
