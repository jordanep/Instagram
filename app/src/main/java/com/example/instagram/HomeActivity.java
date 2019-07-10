package com.example.instagram;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;

import com.example.instagram.models.Post;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private Button bLogout;
    private Button bCreatePost;
    private RecyclerView rvPosts;
    private PostAdapter adapter;
    private ArrayList<Post> posts;
    private SwipeRefreshLayout swipeContainer;

    public static final int CREATE_POST_REQUEST_CODE = 42;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bLogout = findViewById(R.id.bLogout);
        bCreatePost = findViewById(R.id.bCreatePost);
        rvPosts = findViewById(R.id.rvPosts);
        swipeContainer = findViewById(R.id.swipeContainer);

        posts = new ArrayList<>();
        adapter = new PostAdapter(posts);

        rvPosts.setLayoutManager(new LinearLayoutManager(this));
        rvPosts.setAdapter(adapter);

        bLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        bCreatePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(HomeActivity.this, CreatePostActivity.class);
                startActivityForResult(intent, CREATE_POST_REQUEST_CODE);
            }
        });

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadTopPosts();
            }
        });
        swipeContainer.setColorSchemeResources(android.R.color.darker_gray);

        loadTopPosts();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CREATE_POST_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

            }
        }
    }

    private void loadTopPosts() {
        final Post.Query postQuery = new Post.Query();
        postQuery.getTop().withUser();

        postQuery.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> objects, ParseException e) {
                if (e == null) {
                    adapter.clear();
                    for (int i = 0; i < objects.size(); i++) {
                        Log.d("HomeActivity", "\nPost[" + i + "] = "
                                + objects.get(i).getDescription()
                                + ", username = " + objects.get(i).getUser().getUsername());
                        posts.add(objects.get(i));
                        adapter.notifyItemInserted(posts.size() - 1);
                    }
                    swipeContainer.setRefreshing(false);

                } else {
                    e.printStackTrace();
                }
            }
        });
    }

    private void logout() {
        ParseUser.logOut();

        final Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
