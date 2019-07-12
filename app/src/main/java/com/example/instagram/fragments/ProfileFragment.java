package com.example.instagram.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.instagram.ProfileRowAdapter;
import com.example.instagram.R;
import com.example.instagram.models.Post;
import com.example.instagram.models.ProfileRow;
import com.parse.FindCallback;
import com.parse.ParseException;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {

    public static final String TAG = "ProfileFragment";

    private TextView tvHandle;
    private RecyclerView rvProfile;
    private ProfileRowAdapter adapter;
    private ArrayList<ProfileRow> profileRows;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        tvHandle = view.findViewById(R.id.tvHandle);
        rvProfile = view.findViewById(R.id.rvProfile);

        profileRows = new ArrayList<>();
        adapter = new ProfileRowAdapter(profileRows, getContext());

        rvProfile.setAdapter(adapter);
        rvProfile.setLayoutManager(new LinearLayoutManager(getContext()));

        loadUserPosts();
    }

    private void loadUserPosts() {
        final Post.Query postQuery = new Post.Query();
        postQuery.getTop().fromUser();

        postQuery.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> objects, ParseException e) {
                if (e == null) {
                    for (int i = 0; i < objects.size() - (objects.size() % 3); i += 3) {
                        ProfileRow profileRow = new ProfileRow();
                        profileRow.setPost1(objects.get(i));
                        profileRow.setPost2(objects.get(i + 1));
                        profileRow.setPost3(objects.get(i + 2));
                        profileRows.add(profileRow);
                        adapter.notifyItemInserted(profileRows.size() - 1);
                    }
                    if (objects.size() % 3 == 1) {
                        ProfileRow profileRow = new ProfileRow();
                        profileRow.setPost1(objects.get(objects.size() - 1));
                        profileRows.add(profileRow);
                        adapter.notifyItemInserted(profileRows.size() - 1);
                    }
                    else if (objects.size() % 3 == 2) {
                        ProfileRow profileRow = new ProfileRow();
                        profileRow.setPost1(objects.get(objects.size() - 2));
                        profileRow.setPost2(objects.get(objects.size() - 1));
                        profileRows.add(profileRow);
                        adapter.notifyItemInserted(profileRows.size() - 1);
                    }
                } else {
                    Log.e(TAG, "Error loading user posts");
                    e.printStackTrace();
                }
            }
        });
    }
}
