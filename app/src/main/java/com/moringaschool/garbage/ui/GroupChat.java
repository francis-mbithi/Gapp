package com.moringaschool.garbage.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.moringaschool.garbage.R;
import com.moringaschool.garbage.adapters.MessageAdapter;
import com.moringaschool.garbage.models.ChatMessage;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GroupChat extends AppCompatActivity {
    private static final String TAG = "GroupChat";
    @BindView(R.id.messageEditText)
    EditText mMessageEditText;
    @BindView(R.id.fab)
    FloatingActionButton mFloatingActionButton;

    private FirebaseFirestore db;
    private CollectionReference chatRef;
    private MessageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_chat);
        getSupportActionBar().setTitle("Chats");
        ButterKnife.bind(this);


        db = FirebaseFirestore.getInstance();
        chatRef = db.collection("chats");

        setUpRecyclerView();
        addMessageToFirebase();
    }

    private void setUpRecyclerView() {
        Query query = chatRef.orderBy("messageTime", Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<ChatMessage> options = new FirestoreRecyclerOptions.Builder<ChatMessage>()
                .setQuery(query, ChatMessage.class)
                .build();
        adapter = new MessageAdapter(options);
        RecyclerView recyclerView = findViewById(R.id.messagesRecyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                adapter.deleteItem(viewHolder.getAdapterPosition());
            }
        }).attachToRecyclerView(recyclerView);
    }

    //add user and message to firebase
        private void addMessageToFirebase(){
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message =mMessageEditText.getText().toString().trim();
                String user = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
                if (message.equals(""))return;
                CollectionReference chatRef = FirebaseFirestore.getInstance().collection("chats");
                chatRef.add(new ChatMessage(message,user));
                Toast.makeText(GroupChat.this, "Message sent", Toast.LENGTH_SHORT).show();
                mMessageEditText.setText("");
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
