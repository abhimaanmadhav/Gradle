package com.udacity.gradle.builditbigger;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.gradle.builditbigger.MainActivity;

import abhimaan.com.androidjokelib.DisplayJokeActivity;
import abhimaan.com.androidjokelib.JokeAsyncTask;
import abhimaan.com.androidjokelib.JokeCallBack;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener, JokeCallBack
{

    public MainActivityFragment()
        {
        }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
        {
            View root = inflater.inflate(R.layout.fragment_main, container, false);
            root.findViewById(R.id.tell_joke).setOnClickListener(this);
            // Create an ad request. Check logcat output for the hashed device ID to
            // get test ads on a physical device. e.g.
            // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."

            return root;
        }

    @Override
    public void onClick(View v)
        {
            tellJoke();
        }

    private void tellJoke()
        {
            ((MainActivity)getActivity()).startLoading();
            new JokeAsyncTask(this).execute();

        }


    @Override
    public void onRecieved(String joke)
        {

            Intent intent = new Intent(getActivity(), DisplayJokeActivity.class);
            intent.putExtra(DisplayJokeActivity.DISPLAY_TEXT, joke);
            ((MainActivity)getActivity()).stopLoading();
            startActivity(intent);
        }
}
