package com.example.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ColorsFragment extends Fragment {
    private Context context;
    private MediaPlayer mMediaPlayer;
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }};

    //  variables handling audio focus
    private AudioManager mAudioManager; // holds the audio manager variable
    private AudioManager.OnAudioFocusChangeListener audioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int i) {
            switch (i){
                case AudioManager.AUDIOFOCUS_LOSS:
                    releaseMediaPlayer();
                    break;

                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    mMediaPlayer.pause();
                    mMediaPlayer.seekTo(0);
                    break;

                case AudioManager.AUDIOFOCUS_GAIN:
                    mMediaPlayer.start();
                    break;
            }
        }
    };

    public ColorsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer(); // release media player resources when the activity is stopped.
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.words_list, container, false);

        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("red", "wetetti", R.drawable.color_red, R.raw.color_red));
        words.add(new Word("mustard yellow", "chiwiitə", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));
        words.add(new Word("dusty yellow", "topiisə", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        words.add(new Word("green", "chokokki", R.drawable.color_green, R.raw.color_green));
        words.add(new Word("brown", "takaakki", R.drawable.color_brown, R.raw.color_brown));
        words.add(new Word("gray", "topoppi", R.drawable.color_gray, R.raw.color_gray));
        words.add(new Word("black", "kululli", R.drawable.color_black, R.raw.color_black));
        words.add(new Word("white", "kelelli", R.drawable.color_white, R.raw.color_white));

        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_colors);
        ListView listView = rootView.findViewById(R.id.rootView);
        listView.setAdapter(adapter);

        mAudioManager = (AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // release media player if it exists before playing another file
                releaseMediaPlayer();

                // audio focus
                int result = mAudioManager.requestAudioFocus(audioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mMediaPlayer = MediaPlayer.create(getActivity(), words.get(i).getMediaResourceID());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }

            }
        });
        return rootView;
    }


    // cleanup media player by releasing its resources
    private void releaseMediaPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
            mAudioManager.abandonAudioFocus(audioFocusChangeListener);
        }

    }
}
