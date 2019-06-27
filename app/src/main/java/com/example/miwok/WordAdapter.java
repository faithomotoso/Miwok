package com.example.miwok;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {
    private int mColorResourceID;

    public WordAdapter(Context context, ArrayList<Word> word, int colorResourceID) {
        super(context, 0, word);
        this.mColorResourceID = colorResourceID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Word word = getItem(position);

        // check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // view for data population
        TextView defaultView = convertView.findViewById(R.id.default_text_view);
        TextView miwokView = convertView.findViewById(R.id.miwok_text_view);
        ImageView imageView = convertView.findViewById(R.id.image_view);

        defaultView.setText(word.getDefaultTranslation());
        miwokView.setText(word.getMiwokTranslation());
        if (word.hasImage()){
            imageView.setImageResource(word.getImageResourceId());
            // set visibility for reused views
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.GONE);
        }

        int color = ContextCompat.getColor(getContext(), mColorResourceID);

        LinearLayout textContainer = convertView.findViewById(R.id.text_container);
        textContainer.setBackgroundColor(color);

//        return super.getView(position, convertView, parent);
        return convertView;
    }
}
