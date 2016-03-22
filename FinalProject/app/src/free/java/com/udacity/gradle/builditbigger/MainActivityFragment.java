package com.udacity.gradle.builditbigger;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import abhimaan.com.androidjokelib.DisplayJokeActivity;
import abhimaan.com.androidjokelib.JokeAsyncTask;
import abhimaan.com.androidjokelib.JokeCallBack;
import logger.Logger;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener, JokeCallBack
{

    public MainActivityFragment()
        {
        }

    InterstitialAd mInterstitialAd;
    String joke;

    @Override
    public void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            mInterstitialAd = new InterstitialAd(getActivity());
            mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

            requestNewInterstitial();

            // Set an AdListener.
            mInterstitialAd.setAdListener(new AdListener()
            {
                @Override
                public void onAdLoaded()
                    {

                    }

                @Override
                public void onAdClosed()
                    {
                        // Proceed to the next level.
                        showJoke();
                        requestNewInterstitial();
                    }
            });

        }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
        {
            View root = inflater.inflate(R.layout.fragment_main, container, false);
            root.findViewById(R.id.tell_joke).setOnClickListener(this);
            AdView mAdView = (AdView) root.findViewById(R.id.adView);
            // Create an ad request. Check logcat output for the hashed device ID to
            // get test ads on a physical device. e.g.
            // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
            AdRequest adRequest = new AdRequest.Builder()
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    .build();

            mAdView.loadAd(adRequest);
            return root;
        }

    @Override
    public void onClick(View v)
        {
            tellJoke();
        }

    private void tellJoke()
        {
            Logger.d(this, "calling joke service");
            ((MainActivity) getActivity()).startLoading();
            new JokeAsyncTask(this).execute();

        }


    @Override
    public void onRecieved(String joke)
        {
            Logger.d(this, "onrecived response " + joke + " adloaded " + mInterstitialAd.isLoaded
                    ());
            this.joke = joke;
            if (mInterstitialAd.isLoaded())
                {
                    mInterstitialAd.show();
                } else
                {

                    showJoke();
                }
            ((MainActivity) getActivity()).stopLoading();
        }

    void showJoke()
        {
            Intent intent = new Intent(getActivity(), DisplayJokeActivity.class);
            intent.putExtra(DisplayJokeActivity.DISPLAY_TEXT, joke);
            startActivity(intent);
        }

    void requestNewInterstitial()
        {
            AdRequest adRequest = new AdRequest.Builder()
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    .build();

            mInterstitialAd.loadAd(adRequest);
        }
}
