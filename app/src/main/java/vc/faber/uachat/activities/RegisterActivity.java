package vc.faber.uachat.activities;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vc.faber.uachat.R;

public class RegisterActivity extends AppCompatActivity {

  @Bind(R.id.input_username)
  TextInputEditText usernameEditText;

  @Bind(R.id.input_layout_username)
  TextInputLayout usernameInputLayout;

  @Bind(R.id.welcome_submit_button)
  Button submitButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);

    //Bind views to variables
    ButterKnife.bind(this);
  }

  void goToMessages(String username) {
    //Create intent to open messages activity
    Intent enterAppIntent = new Intent(this, MessagesActivity.class);

    //Put username on the extras
    enterAppIntent.putExtra(MessagesActivity.usernameKey, username);

    //Start the messages activity
    startActivity(enterAppIntent);
  }

  @OnClick(R.id.welcome_submit_button)
  void onSubmitPressed() {

    //Validate username length
    if (!isUsernameValid()) {
      usernameInputLayout.setErrorEnabled(true);
      usernameInputLayout.setError(getString(R.string.welcome_username_error_message));
      return;
    }

    //If username is valid clear error
    usernameInputLayout.setError(null);
    usernameInputLayout.setErrorEnabled(false);

    //Start new acitivity
    goToMessages(usernameEditText.getText().toString());
  }

  //Check username typed before start new activity
  boolean isUsernameValid() {
    return !usernameEditText.getText().toString().isEmpty();
  }
}
