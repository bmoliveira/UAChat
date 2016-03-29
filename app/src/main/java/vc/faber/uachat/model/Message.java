package vc.faber.uachat.model;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

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
    return message == null ? "" : message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getUsername() {
    return username == null ? "" : username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  // Generate avatar url with username
  public String generateAvatar() {
    return "https://api.adorable.io/avatars/100/"+ getUsername() +"%40faber-dev";
  }
}

