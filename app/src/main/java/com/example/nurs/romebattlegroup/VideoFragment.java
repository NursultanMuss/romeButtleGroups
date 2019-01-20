package com.example.nurs.romebattlegroup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;

public class VideoFragment extends Fragment {
    private static final String API_KEY = "AIzaSyAB_VtQ1nNManutODJYtMH6v6SVDOZhbaw";
    private static YouTubePlayer youTubePlayer;
    private static String TAG = "Tag";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setRetainInstance(true);
        View v = inflater.inflate(R.layout.list_video_card,container,false);
//        TextView textView =(TextView) v.findViewById(R.id.text_video);
//        textView.setText("HAHASAAS");
        YTPlayerFragment video= YTPlayerFragment.newInstance("cBaflW0uKNU");
        getChildFragmentManager().beginTransaction().replace(R.id.youtubeFragment,video).commit();
//        youTubePlayerSupportFragment = (YouTubePlayerSupportFragment) getFragmentManager()
//                .findFragmentById(R.id.youtubePlayerView);
//        if(youTubePlayerSupportFragment == null)
//            Log.d("Tag", "Youtube Player View initialization failed");
//
//        youTubePlayerView.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
//            @Override
//            public void onInitializationSuccess(YouTubePlayer.Provider provider,
//                                                YouTubePlayer player, boolean wasRestored) {
//                if(!wasRestored){
//                    player.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
//                }
//                player.cueVideo("cBaflW0uKNU");
//                player.release();
//            }
//
//            @Override
//            public void onInitializationFailure(YouTubePlayer.Provider provider,
//                                                YouTubeInitializationResult youTubeInitializationResult) {
//                Log.e(TAG, "Youtube Player View initialization failed");
//            }
//        });
        return v;
    }

//    public static VideoFragment newInstance()
//    {
//         VideoFragment fragment = new VideoFragment();
//
//        return fragment;
//    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//		initRecyclerView();
    }


//    public void playVideo(final String videoId, YouTubePlayerView youTubePlayerView) {
//        //initialize youtube player view
//        youTubePlayerView.initialize(API_KEY,
//                new YouTubePlayer.OnInitializedListener() {
//                    @Override
//                    public void onInitializationSuccess(YouTubePlayer.Provider provider,
//                                                        YouTubePlayer youTubePlayer, boolean b) {
//                        youTubePlayer.cueVideo(videoId);
//                    }
//
//                    @Override
//                    public void onInitializationFailure(YouTubePlayer.Provider provider,
//                                                        YouTubeInitializationResult youTubeInitializationResult) {
//
//                    }
//                });
//    }
}
