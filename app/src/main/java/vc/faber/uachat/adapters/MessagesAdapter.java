package vc.faber.uachat.adapters;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseListAdapter;

import vc.faber.uachat.R;
import vc.faber.uachat.model.Message;

/**
 * Created by bruno on 28/03/16.
 */
public class MessagesAdapter extends FirebaseListAdapter<Message> {
  private String currentUsername;

  public MessagesAdapter(Activity activity, String username, Firebase firebase) {
    super(activity, Message.class, R.layout.message_list_item, firebase);
    currentUsername = username;
  }


  @Override
  protected void populateView(View view, Message message) {
    TextView userName = (TextView) view.findViewById(R.id.message_item_username_text);
    TextView userMessage = (TextView) view.findViewById(R.id.message_item_message_text);

    boolean isFromMe = message.getUsername().equals(currentUsername);

    int usernameColor = isFromMe ?
        mActivity.getColor(R.color.self_user) : mActivity.getColor(R.color.other_user);

    int messageColor = isFromMe ?
        mActivity.getColor(R.color.other_user) : mActivity.getColor(R.color.text_color);

    userName.setTextColor(usernameColor);
    userMessage.setTextColor(messageColor);

    userName.setText(isFromMe ? mActivity.getString(R.string.message_from_me) : message.getUsername());
    userMessage.setText(message.getMessage());
  }
}
