package vc.faber.uachat.adapters;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseListAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;
import vc.faber.uachat.R;
import vc.faber.uachat.model.Message;

/**
 * Created by bruno on 28/03/16.
 */
public class MessagesAdapter extends FirebaseListAdapter<Message> {
  private String currentUsername;

  //Public contructor
  // @Param activity activity where the adapter is being shown
  // @Param username username of the current user
  // @Param Firebase firebase to the array of messages
  public MessagesAdapter(Activity activity, String username, Firebase firebase) {
    // Here we know witch class and layout we will be using so we set them
    // as constants to the Super constructor
    super(activity, Message.class, R.layout.message_list_item, firebase);
    currentUsername = username;
  }


  @Override
  protected void populateView(View view, Message message) {
    // We are using a ViewHolder to improve listview performance
    // check http://developer.android.com/training/improving-layouts/smooth-scrolling.html
    MessageViewHolder viewHolder = (MessageViewHolder) view.getTag();
    if (viewHolder == null) {
      viewHolder = new MessageViewHolder(view);
      view.setTag(viewHolder);
    }

    // Check the message user to check current user
    boolean isFromMe = message.getUsername().equals(currentUsername);

    // Update view with data
    viewHolder.updateWithMessage(message, isFromMe, mActivity);
  }
}

class MessageViewHolder {
  @Bind(R.id.message_item_username_text)
  TextView userName;

  @Bind(R.id.message_item_message_text)
  TextView userMessage;

  MessageViewHolder(View view) {
    // Init values with butterknife
    ButterKnife.bind(this, view);
  }

  void updateWithMessage(Message message, boolean isFromCurrentUser, Activity activity) {

    int usernameColor = isFromCurrentUser ?
        activity.getColor(R.color.self_user) : activity.getColor(R.color.other_user);

    int messageColor = isFromCurrentUser ?
        activity.getColor(R.color.other_user) : activity.getColor(R.color.text_color);

    userName.setTextColor(usernameColor);
    userMessage.setTextColor(messageColor);

    userName.setText(isFromCurrentUser ?
        activity.getString(R.string.message_from_me) : message.getUsername());

    userMessage.setText(message.getMessage());
  }
}
