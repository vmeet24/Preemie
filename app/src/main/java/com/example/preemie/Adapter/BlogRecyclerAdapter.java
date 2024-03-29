package com.example.preemie.Adapter;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.example.preemie.Activity.CommentActivity;
import com.example.preemie.R;
import com.example.preemie.Model.UploadPosts;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;

public class BlogRecyclerAdapter extends RecyclerView.Adapter<BlogRecyclerAdapter.MyViewHolder> {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    //FirebaseDatabase firebaseLike;
    FirebaseAuth mAuth;
    private Context mContext;
    private List<UploadPosts> blogList;
    private boolean mProcess = false;
    private boolean mProcess1 = false;

    public BlogRecyclerAdapter(Context mContext, List<UploadPosts> blogList) {
        this.mContext = mContext;
        this.blogList = blogList;
    }
   /* public BlogRecyclerAdapter(FragmentActivity activity, List<UploadPosts> bloglist) {
    }*/

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.blog_list_item, parent, false);
        //mContext = parent.getContext();
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Posts");
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final BlogRecyclerAdapter.MyViewHolder holder, final int position) {
        holder.setIsRecyclable(false);

        SharedPreferences prefs = mContext.getSharedPreferences("pref",MODE_PRIVATE);
        String lang = prefs.getString("lang","en");
        final String blogPostId = blogList.get(position).BlogPostId;
        Log.d("BlogRecyclerAdapter",lang);
        if(lang.contains("en")){
            //blogPostId = blogList.get(position).BlogPostId;
            holder.postTitle.setText(blogList.get(position).getUploadTitle());
        }
        else if(lang.contains("hi")){
            //  blogPostId = blogList.get(position).BlogPostId;
            holder.postTitle.setText(blogList.get(position).getUploadTitleHindi());
        }

        final String userUploadId =blogList.get(position).getUploadId().toString();
        holder.userName.setText(blogList.get(position).getUploadId());
       DatabaseReference myref = firebaseDatabase.getReference("user_data");
       myref.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               for(DataSnapshot ds :dataSnapshot.getChildren()){
                   if(ds.getValue().toString().contains(userUploadId)){
                      String name= ds.child("fName").getValue().toString();
                      String url =ds.child("imageUrl").getValue().toString();
                      holder.userName.setText(name);

                      if(url.contains("default")){
                          Glide.with(mContext).load(R.drawable.medicologo).into(holder.blogUserImage);

                      }
                      else {
                          Glide.with(mContext).load(url).into(holder.blogUserImage);

                      }

                   }

               }
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });

        Glide.with(mContext).load(blogList.get(position).getUploadImageUrl()).into(holder.imagePost);
        final UploadPosts uploadPosts=new UploadPosts();
        holder.imagePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, CommentActivity.class);
                intent.putExtra("image_url",blogList.get(position).getUploadImageUrl());
                intent.putExtra("title",blogList.get(position).getUploadTitle());
                intent.putExtra("desc",blogList.get(position).getUploadSubject());
                intent.putExtra("blogPostId",blogList.get(position).getPostKey());
                mContext.startActivity(intent);
            }
        });
        //Glide.with(mContext).load(blogList.get(position).getUserPhoto()).into(holder.imgPostProfile);
        //long millisecond = (long)blogList.get(position).getTimeStamp();
        //long d = new Date(millisecond).getTime();
        // String dateString = DateFormat.format("MM/dd/yyyy",new Date()).toString();
        // holder.postDate.setText(dateString);
       // holder.setBlogLikeBtn(blogPostId);
        holder.setBlogLikeCount(blogPostId);
        /*holder.blogLikeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String currentUserId = mAuth.getCurrentUser().getUid();

                mProcess=true;
                databaseReference.child(blogPostId).child("Likes").child(currentUserId).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (mProcess) {
                            //dislike
                            if (dataSnapshot.exists()) {
                                databaseReference.child(blogPostId).child("Likes").child(currentUserId).removeValue();
                                mProcess = false;
                            } else {
                                //liking post
                                final HashMap<String, Object> hashmap = new HashMap<>();
                                hashmap.put("sender", ServerValue.TIMESTAMP);
                                databaseReference.child(blogPostId).child("Likes").child(currentUserId).setValue(hashmap);
                                //holder.blogLikeBtn.setImageDrawable(mContext.getDrawable(R.mipmap.action_like_accent));
                                mProcess = false;

                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return blogList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView postTitle;
        ImageView imagePost;
        TextView userName;
        TextView postDate;
        private ImageView blogLikeBtn;
        private TextView blogLikeCount;
        ConstraintLayout parent;
        CircleImageView blogUserImage;
        TextView blogUserName;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            postTitle = itemView.findViewById(R.id.blogTitle);
            imagePost = itemView.findViewById(R.id.blogImage);
            //postDate = itemView.findViewById(R.id.blogdate);
            //blogLikeBtn = itemView.findViewById(R.id.blogLikeBtn);
            blogLikeCount = itemView.findViewById(R.id.likeCount);
            blogUserImage=itemView.findViewById(R.id.blogUserImage);
            userName = itemView.findViewById(R.id.blogUserName);
            blogUserName = itemView.findViewById(R.id.blogUserName);
            mAuth=FirebaseAuth.getInstance();



        }
/*
        void setBlogLikeBtn(String blogPostId){
            databaseReference.child(blogPostId).child("Likes").child(mAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()){
                        blogLikeBtn.setImageDrawable(mContext.getDrawable(R.drawable.upvoteiconwhite1));
                    }
                    else{
                        blogLikeBtn.setImageDrawable(mContext.getDrawable(R.drawable.upvoteiconwhite0));
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }*/
        void setBlogLikeCount(String blogPostId){

            databaseReference.child(blogPostId).child("Likes").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    long count=dataSnapshot.getChildrenCount();
                    blogLikeCount.setText(count+" UpVotes");
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

    }
}

