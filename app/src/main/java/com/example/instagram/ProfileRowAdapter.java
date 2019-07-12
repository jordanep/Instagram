package com.example.instagram;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.instagram.models.Post;
import com.example.instagram.models.ProfileRow;
import com.parse.ParseFile;

import java.util.ArrayList;

public class ProfileRowAdapter  extends RecyclerView.Adapter<ProfileRowAdapter.ViewHolder> {

    private ArrayList<ProfileRow> profileRows;
    private Context context;

    public ProfileRowAdapter(ArrayList<ProfileRow> profileRows, Context context) {
        this.profileRows = profileRows;
        this.context = context;
    }

    @NonNull
    @Override
    public ProfileRowAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_profile_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileRowAdapter.ViewHolder holder, int position) {
        ProfileRow profileRow = profileRows.get(position);

        Post post1 = profileRow.getPost1();
        Post post2 = profileRow.getPost2();
        Post post3 = profileRow.getPost3();

        if (post1 != null) {
            ParseFile image1 = post1.getImage();
            String imageUrl1 = image1.getUrl();
            imageUrl1 = imageUrl1.substring(4);
            imageUrl1 = "https" + imageUrl1;
            Glide.with(context)
                    .load(imageUrl1)
                    .placeholder(android.R.color.white)
                    .error(R.drawable.camera_shadow_fill)
                    .into(holder.ivPost1);
        }
        if (post2 != null) {
            ParseFile image2 = post2.getImage();
            String imageUrl2 = image2.getUrl();
            imageUrl2 = imageUrl2.substring(4);
            imageUrl2 = "https" + imageUrl2;
            Glide.with(context)
                    .load(imageUrl2)
                    .placeholder(android.R.color.white)
                    .error(R.drawable.camera_shadow_fill)
                    .into(holder.ivPost2);
        }
        if (post3 != null) {
            ParseFile image3 = post3.getImage();
            String imageUrl3 = image3.getUrl();
            imageUrl3 = imageUrl3.substring(4);
            imageUrl3 = "https" + imageUrl3;
            Glide.with(context)
                    .load(imageUrl3)
                    .placeholder(android.R.color.white)
                    .error(R.drawable.camera_shadow_fill)
                    .into(holder.ivPost3);
        }
    }

    @Override
    public int getItemCount() {
        return profileRows.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivPost1;
        ImageView ivPost2;
        ImageView ivPost3;

        public ViewHolder(View itemView) {
            super(itemView);

            ivPost1 = itemView.findViewById(R.id.ivPost1);
            ivPost2 = itemView.findViewById(R.id.ivPost2);
            ivPost3 = itemView.findViewById(R.id.ivPost3);
        }

    }
}
