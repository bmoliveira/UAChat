package vc.faber.uachat.model;

import java.net.URLEncoder;

public class AvatarHelper {
  public static String generateAvatarFom(Message message) {
    return "https://api.adorable.io/avatars/100/"+ URLEncoder.encode(message.getUsername()) +"%40faber-dev";
  }
}
