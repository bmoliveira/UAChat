package vc.faber.uachat.model;

/**
 * Created by bruno on 28/03/16.
 */
public class Message {
  String message;
  String username;
  String action = "message";

  //Required for firebase
  public Message() { }

  public Message(String message, String username) {
    this.message = message;
    this.username = username;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}
