package vc.faber.uachat.activities;

import android.database.DataSetObserver;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.firebase.client.Firebase;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vc.faber.uachat.R;
import vc.faber.uachat.adapters.MessagesAdapter;
import vc.faber.uachat.model.Message;

public class MessagesActivity extends AppCompatActivity {
  public static final String usernameKey = "username";

  @Bind(R.id.message_input_layout)
  TextInputLayout messageLayout;

  @Bind(R.id.message_edit_text)
  TextInputEditText messageEditText;

  @Bind(R.id.message_list_view)
  ListView messagesListView;

  Firebase messagesFirebase = new Firebase("https://ua-chat.firebaseio.com/")
      .child("messages");
  String username;
  MessagesAdapter messagesAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_messages);
    ButterKnife.bind(this);
    initUser();
    messagesAdapter = new MessagesAdapter(this, username, messagesFirebase);
    messagesListView.setAdapter(messagesAdapter);
  }

  void sendMessage(String stringMessage) {
    if (username == null) {
      System.out.println("invalid user state");
      finish();
      return;
    }
    Message message = new Message(stringMessage, username);
    messagesFirebase.push().setValue(message);
  }

  void initUser() {
    if (getIntent() == null || getIntent().getExtras() == null) {
      finish();
      System.out.println("No arguments passed");
      return;
    }
    Bundle startBundle = getIntent().getExtras();
    username = (String) startBundle.get(MessagesActivity.usernameKey);

    if (username == null ) {
      finish();
      System.out.println("Invalid arguments");
      return;
    }
  }

  @OnClick(R.id.send_message_button)
  void sendMessage() {
    String message = messageEditText.getText().toString();
    if (message.isEmpty()) {
      System.out.println("you should write a message first");
      return;
    }
    sendMessage(message);
    messageEditText.getText().clear();
  }
}
