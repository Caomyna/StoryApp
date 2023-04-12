package com.example.storyapp2;

import android.widget.Filter;

import com.example.storyapp2.model.Category;
import com.example.storyapp2.model.CategoryAdapter;

import java.util.ArrayList;
import java.util.List;

public class FilterCategory extends Filter {

    //arrayList in which we want to search
    List<Category> filterList;
    //adapter in which filter need to be implemented
    CategoryAdapter categoryAdapter;

    //constructor

    public FilterCategory(List<Category> filterList, CategoryAdapter categoryAdapter) {
        this.filterList = filterList;
        this.categoryAdapter = categoryAdapter;
    }


    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();

        if (constraint != null && constraint.length() >0) {
            //tránh phân biệt chữ hoa và chữ thường
            constraint = constraint.toString().toUpperCase();
            ArrayList<Category> filteredModels = new ArrayList<>();
            for (int i=0; i<filterList.size(); i++){
                // validate
                if (filterList.get(i).getNameCategory().toUpperCase().contains(constraint)) {
                    //them vao danh sach filteredList
                    filteredModels.add(filterList.get(i));
                }
            }
            results.count = filteredModels.size();
            results.values = filteredModels;
        }else {
            results.count = filterList.size();
            results.values = filterList;
        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        //apply filter changes
        categoryAdapter.listCategory = (ArrayList<Category>)results.values;

        //notify changes
        categoryAdapter.notifyDataSetChanged();
    }
}
