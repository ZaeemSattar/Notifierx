package droid.zaeem.notifierx.adapter;

/**
 * Created by Droid on 6/30/2016.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import droid.zaeem.notifierx.R;
import droid.zaeem.notifierx.database.Message;


public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MyViewHolder> {
    /*ArrayList<String> messagesModels;
    ArrayList<String> messagesTitles;*/
    List<Message> messages;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, details;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            details = (TextView) view.findViewById(R.id.details);
        }
    }


   /* public MessagesAdapter(ArrayList<String> messagesModels, ArrayList<String> messagesTitles) {
        this.messagesModels = messagesModels;
        this.messagesTitles = messagesTitles;
    }*/

    public MessagesAdapter(List<Message> messages) {
        this.messages = messages;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.messages_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.title.setText(messages.get(position).getTitle());
        holder.details.setText(messages.get(position).getBody());

    }


    @Override
    public int getItemCount() {
        return messages.size();
    }
}