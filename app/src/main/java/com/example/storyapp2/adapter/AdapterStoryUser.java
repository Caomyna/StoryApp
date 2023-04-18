package com.example.storyapp2.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storyapp2.R;
import com.example.storyapp2.StoryDetailActivity;
import com.example.storyapp2.databinding.RowStoryBinding;
import com.example.storyapp2.model.Story;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterStoryUser extends RecyclerView.Adapter<AdapterStoryUser.HolderStoryUser> implements Filterable {

    private Context context;
    private List<Story> listStory;
    private List<Story> listStoryOld;
    private RowStoryBinding binding;
    private OnItemClickListener onClick;

    private static final String TAG = "ADAPTER_STORY_USER_TAG";

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onClick) {

        this.onClick = onClick;
    }

    public AdapterStoryUser(Context context,List<Story> listStory) {
        this.context = context;
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
        binding = RowStoryBinding.inflate(LayoutInflater.from(context), parent,false);
        return new HolderStoryUser(binding.getRoot());

    }

    @Override
    public void onBindViewHolder(@NonNull HolderStoryUser holder, int position) {
        //get data
        Story story = listStory.get(position);
        if (story == null) {
            return;
        }
        Integer idStory = story.getIdStory();
        String title = story.getTitle();
        String author = story.getAuthor();
        String image = story.getImage();
        String content = story.getContent();

        //set data
        holder.titleTv.setText(title);
        holder.authorTv.setText(author);

        Picasso.get().load(image).placeholder(R.drawable.ic_load).error(R.drawable.ic_image).into(holder.imageView);


        //handle click show story details
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, StoryDetailActivity.class);
                intent.putExtra("idStory", idStory);
                intent.putExtra("title", title);
                intent.putExtra("author", author);
                intent.putExtra("content", content);
                intent.putExtra("image", image);
                context.startActivity(intent);

            }
        });

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
        public HolderStoryUser(@NonNull View itemView) {
            super(itemView);

            titleTv = binding.titleTv;
            authorTv = binding.authorTv;
            imageView = binding.image;

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
