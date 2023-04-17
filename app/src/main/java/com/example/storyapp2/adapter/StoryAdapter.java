package com.example.storyapp2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storyapp2.R;
import com.example.storyapp2.model.Story;

import java.util.List;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.StoryViewHolder>{

    private List<Story> listStory;

    private void setData(List<Story> list){
        this.listStory = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_story, parent,false);
        return new StoryViewHolder(view);
    }

    //hàm set dữ liệu lên
    @Override
    public void onBindViewHolder(@NonNull StoryViewHolder holder, int position) {
        Story story = listStory.get(position);
        if (story == null){
            return ;
        }
        holder.tvTitle.setText(story.getTitle());
        holder.tvAuthor.setText(story.getAuthor());
    }

    @Override
    public int getItemCount() {
        if (listStory != null) {
            return listStory.size();
        }
        return 0;
    }

    public class StoryViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTitle;
        private TextView tvAuthor;

        public StoryViewHolder(@NonNull View itemView) {
            super(itemView);

//            tvTitle = itemView.findViewById(R.id.title_tv);
//            tvAuthor = itemView.findViewById(R.id.title_tv);
        }
    }
}
