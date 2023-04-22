package com.example.storyapp2.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storyapp2.R;
import com.example.storyapp2.databinding.RowStoryAdminBinding;
import com.example.storyapp2.model.Story;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdminStoryAdapter extends RecyclerView.Adapter<AdminStoryAdapter.HolderStoryUser> implements Filterable {

    private List<Story> listStory;
    private List<Story> listStoryOld;
    private RowStoryAdminBinding binding;
    private OnItemClickListener onClick;

    private static final String TAG = "ADAPTER_STORY_USER_TAG";

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onEditItem(int position);
        void onDeleteItem(Story story);
    }

    public void setOnItemClickListener(OnItemClickListener onClick) {

        this.onClick = onClick;
    }

    public AdminStoryAdapter(List<Story> listStory, OnItemClickListener listener) {
        this.onClick = listener;
        this.listStory = listStory;
        this.listStoryOld = listStory;
    }

    public void setData(List<Story> list){
        this.listStory = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HolderStoryUser onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //bind the view
        binding = RowStoryAdminBinding.inflate(LayoutInflater.from(parent.getContext()), parent,false);
        return new HolderStoryUser(binding.getRoot());

    }

    @Override
    public void onBindViewHolder(@NonNull HolderStoryUser holder, @SuppressLint("RecyclerView") int position) {
        //get data
        Story story = listStory.get(position);
        if (story == null) {
            return;
        }
        String title = story.getTitle();
        String author = story.getAuthor();
        String image = story.getImage();

        //set data
        holder.titleTv.setText(title);
        holder.authorTv.setText(author);

        Picasso.get().load(image).placeholder(R.drawable.ic_load).error(R.drawable.ic_image).into(holder.imageView);


        //handle click show story details
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.onItemClick(position);

            }
        });


        //deleteBtn
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.onDeleteItem(story);
            }
        });

        //editBtn
//        holder.editBtn

    }

    @Override
    public int getItemCount() {
        if (listStory != null) {
            return listStory.size();
        }
        return 0;
    }

    class HolderStoryUser extends RecyclerView.ViewHolder{
        TextView titleTv, authorTv;
        ImageView imageView;
        ImageButton deleteButton, editBtn;
        public HolderStoryUser(@NonNull View itemView) {
            super(itemView);

            titleTv = binding.titleTv;
            authorTv = binding.authorTv;
            imageView = binding.image;
            deleteButton = binding.deleteButton;
            editBtn = binding.editBtn;

        }
    }


    //Search
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String strSearch = charSequence.toString();
                if (strSearch.isEmpty()) {
                    listStory = listStoryOld;
                }else {
                    List<Story> list = new ArrayList<>();
                    for (Story story : listStoryOld) {
                        if (story.getTitle().toLowerCase().contains(strSearch.toLowerCase())){
                            list.add(story);
                        }
                    }

                    listStory = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = listStory;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listStory = (List<Story>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

}
