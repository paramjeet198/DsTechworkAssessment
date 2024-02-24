package com.example.myapplication.apitask;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.databinding.UserItemBinding;
import com.example.myapplication.apitask.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> users = new ArrayList<>();

    public UserAdapter(List<User> users) {
        this.users = users;
    }

    public void setApiData(List<User> users){
        this.users = users;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        UserItemBinding binding = UserItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new UserViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User item = users.get(position);
        holder.binding.name.setText(item.name);
        holder.binding.email.setText(item.email);
        holder.binding.phone.setText(item.phone);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        UserItemBinding binding;

        public UserViewHolder(@NonNull UserItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
