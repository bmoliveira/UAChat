# UAChat

Android chat application built using Firebase for a workshop on Universidade de Aveiro

## Requirements
### Android Studio
 - Reccomended version 2.0 Beta 7 (for faster builds)

### Android SDK and Build tools
 - SDK Platform Android N Preview, Android 5.0 and Android 4.1
 - SDK Build Tools 24+ (faster builds)
 - Android Play Services
 
#### Extras note
 - If you dont have an Android device its highly recommended to install HAXM intel virtual machine tool to make android emulator run faster


## Workflow

 - Create Project with empty activity
 
 - Implement username activity Layout
 
 - TextInput Layout 
  - compile 'com.android.support:design:+'
 
 - String resources
 
 - ButterKnife (why does it matter)
  - compile 'com.jakewharton:butterknife:+'
 
 - Bind views
  - OnClick
  - Error (empty username)
 
 - Import Firebase
  - Through UI
  - Extend application
 
 - Create user class
  - generate random identifier
 
 - Create Message class
  - Don't forget the empty constructor for firebase 
 
 - Create Message Activity
 
 - Start activity with username
  - pass parameters
  - verify parameters on the other side
 
 - Create ui 
  - Add Listview
  - Make it stick to the bottom :
  android:transcriptMode="alwaysScroll"
  android:stackFromBottom="true"
  - Add inputView
  - Add messageButton
  - Create imagebutton from androidstudio
  - Bind views
 
 - implement send message
 
 - show message on dashboard
 
 - implement adapter
  - compile 'com.firebase:firebase-ui:0.2.0'
  - import firebase adapter
  - create message view 
  - Bind view on adapter
