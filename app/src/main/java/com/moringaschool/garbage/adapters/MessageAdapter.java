package com.moringaschool.garbage.adapters;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.moringaschool.garbage.R;
import com.moringaschool.garbage.models.ChatMessage;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageAdapter extends FirestoreRecyclerAdapter<ChatMessage, MessageAdapter.MessageViewHolder> {

    public MessageAdapter(@NonNull FirestoreRecyclerOptions<ChatMessage> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MessageViewHolder holder, int position, @NonNull ChatMessage model) {
        holder.mMessageText.setText(model.getMessageText());
        holder.mUser.setText(model.getMessageUser());
        holder.mTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)", model.getMessageTime()));
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message, parent, false);
        return new MessageViewHolder(view);
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.message_text)
        TextView mMessageText;
        @BindView(R.id.message_user) TextView mUser;
        @BindView(R.id.message_time) TextView mTime;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void deleteItem(int position){
        getSnapshots().getSnapshot(position).getReference().delete();
    }
}