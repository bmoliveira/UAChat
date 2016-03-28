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
    ButterKnife.bind(this);
  }

  void goToMessages(String username) {
    Intent enterAppIntent = new Intent(this, MessagesActivity.class);
    enterAppIntent.putExtra(MessagesActivity.usernameKey, username);
    startActivity(enterAppIntent);
  }

  @OnClick(R.id.welcome_submit_button)
  void onSubmitPressed() {


    if (!isUsernameValid()) {
      usernameInputLayout.setError(getString(R.string.welcome_username_error_message));
      return;
    }

    usernameInputLayout.setError(null);
    goToMessages(usernameEditText.getText().toString());
  }

  boolean isUsernameValid() {
    return !usernameEditText.getText().toString().isEmpty();
  }
}
