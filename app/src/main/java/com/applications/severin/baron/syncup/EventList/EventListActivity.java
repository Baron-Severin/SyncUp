package com.applications.severin.baron.syncup.EventList;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.applications.severin.baron.syncup.R;

import butterknife.BindView;
import butterknife.OnClick;

public class EventListActivity extends AppCompatActivity {


  private SectionsPagerAdapter mSectionsPagerAdapter;
  @BindView(R.id.container) ViewPager mViewPager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_event_list);

    setUpToolbar();
    setUpPager();
  }

  private void setUpToolbar() {
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
  }

  private void setUpPager() {
    mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
    mViewPager.setAdapter(mSectionsPagerAdapter);
    TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
    tabLayout.setupWithViewPager(mViewPager);
  }

  @OnClick(R.id.fab)
  public void handleFabClick(View view) {
    // TODO: this will create a new event
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_event_list, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  /**
   * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
   * one of the sections/tabs/pages.
   */
  public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override
    public Fragment getItem(int position) {
      // getItem is called to instantiate the fragment for the given page.
      // Return a EventListFragment (defined as a static inner class below).
      return EventListFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
      return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
      switch (position) {
        case 0:
          return "My Events";
        case 1:
          return "All Events";
      }
      return null;
    }
  }

  /**
   * A placeholder fragment containing a simple view.
   */
  public static class EventListFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static EventListFragment newInstance(int sectionNumber) {
      EventListFragment fragment = new EventListFragment();
      Bundle args = new Bundle();
      args.putInt(ARG_SECTION_NUMBER, sectionNumber);
      fragment.setArguments(args);
      return fragment;
    }

    public EventListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View rootView = inflater.inflate(R.layout.fragment_event_list, container, false);
      TextView textView = (TextView) rootView.findViewById(R.id.section_label);
      textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
      return rootView;
    }
  }



}
