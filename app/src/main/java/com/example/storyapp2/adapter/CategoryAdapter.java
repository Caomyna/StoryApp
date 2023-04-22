package com.example.storyapp2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storyapp2.FilterCategory;
import com.example.storyapp2.databinding.RowCategoryBinding;
import com.example.storyapp2.model.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> implements Filterable {
    //view binding
    private RowCategoryBinding binding;
    private Context context;
    public List<Category> listCategory, filterList;

    private OnItemClickListener onClick;

    //instance of out filter class
    private FilterCategory filter;


    public interface OnItemClickListener{
        void onClickItemCategory(int id);
        void deleteCategory(Category category);
    }
    public void setOnItemClickListener(OnItemClickListener onClick) {

        this.onClick = onClick;
    }


    public CategoryAdapter (List list, OnItemClickListener listener){
        this.listCategory = list;
        this.onClick = listener;
        this.filterList = list;
    }

    public void setData(List<Category> list){
        this.listCategory = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //bind row_category.xml
        binding = RowCategoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent,false);

        return new CategoryViewHolder(binding.getRoot());
    }

    //set dữ liệu lên
    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.CategoryViewHolder holder, int position) {
        //get data
        Category category = listCategory.get(position);
        if (category == null) {
            return;
        }

        //set data
        int idCategory = category.getIdCategory();
        String nameCategory = category.getNameCategory();
        holder.categoryTv.setText(nameCategory);

        //itemLayout
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.onClickItemCategory(idCategory);
            }
        });

        //handle click delete
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.deleteCategory(category);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (listCategory != null) {
            return listCategory.size();
        }
        return 0;
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new FilterCategory(filterList,this);
        }
        return null;
    }


    //View holder class to hold IU views for row_category.xml
    public class CategoryViewHolder extends RecyclerView.ViewHolder{
        private TextView categoryTv;
        private ImageButton deleteBtn;
        public CategoryViewHolder(@NonNull View itemView) {

            super(itemView);
            categoryTv = binding.categoryTv;
            deleteBtn = binding.deleteBtn;
        }
    }
}
