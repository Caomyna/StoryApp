package com.example.storyapp2.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storyapp2.R;

import java.util.List;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.AccountViewHolder>{

    private List<Account> listAccount;

    public void setData(List<Account> list){
        this.listAccount = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AccountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_story, parent,false);
        return new AccountViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountViewHolder holder, int position) {
        Account account = listAccount.get(position);
        if (account == null){
            return;
        }
        holder.tvName.setText(account.getName());
        holder.tvPassword.setText(account.getPassword());
    }

    @Override
    public int getItemCount() {
        if (listAccount != null) {
            return listAccount.size();
        }
        return 0;
    }

    public class AccountViewHolder extends RecyclerView.ViewHolder{
        private TextView tvName;
        private TextView tvPassword;
        public AccountViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvPassword = itemView.findViewById(R.id.tvPassword);
        }

    }


}
