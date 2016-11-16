package com.applications.severin.baron.syncup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class EventDetailActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_event_detail);
  }
}


//TODO: Note about switching views
/*

public void TextViewClicked() {
    ViewSwitcher switcher = (ViewSwitcher) findViewById(R.id.my_switcher);
    switcher.showNext(); //or switcher.showPrevious();
    TextView myTV = (TextView) switcher.findViewById(R.id.clickable_text_view);
    myTV.setText("value");
}

 */

//TODO: Remember to have the RV not scroll
