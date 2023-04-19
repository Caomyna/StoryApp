package com.example.storyapp2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storyapp2.databinding.HeaderMenuBinding;
import com.example.storyapp2.model.Account;

import java.util.List;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.AccountViewHolder>{

    private List<Account> listAccount;
    private HeaderMenuBinding binding;
    private Context context;

    public void setData(List<Account> list){
        this.listAccount = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AccountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = HeaderMenuBinding.inflate(LayoutInflater.from(context), parent,false);

        return new AccountViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull AccountViewHolder holder, int position) {
        Account account = listAccount.get(position);
        if (account == null){
            return;
        }

//        holder.nameTv.setText(account.getName());
//        holder.emailTv.setText(account.getEmail());
    }

    @Override
    public int getItemCount() {
        if (listAccount != null) {
            return listAccount.size();
        }
        return 0;
    }

    public class AccountViewHolder extends RecyclerView.ViewHolder{
        public AccountViewHolder(@NonNull View itemView) {
            super(itemView);
        }

    }


}
