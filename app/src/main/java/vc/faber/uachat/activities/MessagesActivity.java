package vc.faber.uachat.activities;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.Firebase;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
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

  // Reference to base firebase and messages array as branch
  Firebase messagesFirebase = new Firebase("https://ua-chat.firebaseio.com/")
      .child("messages");

  //Username must be set by previous activity
  String username;

  //FirebaseAdapter to Messages class, must be initialized on create
  MessagesAdapter messagesAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_messages);
    setTitle("Messages");
    //Bind views to variables
    ButterKnife.bind(this);

    //Fetch username from previous Activity paramenters
    initUser();

    //Init adapter
    messagesAdapter = new MessagesAdapter(this, username, messagesFirebase);
    messagesListView.setAdapter(messagesAdapter);
  }

  //Send message to firebase
  void sendMessage(String stringMessage) {
    //Verify valid username, unless finish current activity and set a username
    if (username == null) {
      System.out.println("invalid user state");
      finish();
      return;
    }

    // Check for empty message don't create object on firebase if you are not typing anything
    if (stringMessage.isEmpty()) {
      System.out.println("you should write a message first");
      return;
    }

    //Create new message object
    Message message = new Message(stringMessage, username);

    //Push Message object to Firebase
    messagesFirebase.push().setValue(message);

    //Reset text
    messageEditText.getText().clear();
  }

  //Get username from Bundle
  void initUser() {
    //Verify that previous activity has set the required extras
    if (getIntent() == null || getIntent().getExtras() == null) {
      finish();
      System.out.println("No arguments passed");
      return;
    }

    Bundle startBundle = getIntent().getExtras();

    //Check if extras contains the username
    if (!startBundle.containsKey(MessagesActivity.usernameKey)) {
      finish();
      System.out.println("Invalid arguments");
      return;
    }

    //Set the username
    username = (String) startBundle.get(MessagesActivity.usernameKey);
    messageLayout.setHint(username);
  }

  // Send message click listener set via butter knife
  @OnClick(R.id.send_message_button)
  void sendMessage() {
    //Get message from EditText
    String message = messageEditText.getText().toString();

    //Send message to firebase
    sendMessage(message);
  }

  //Keep track of editor changes
  @OnEditorAction(R.id.message_edit_text)
  boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
    if (actionId == EditorInfo.IME_ACTION_SEND) {
      sendMessage();
      return true;
    }
    return false;
  }
}
