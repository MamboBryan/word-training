package com.mamboexample.roomwordexample.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mamboexample.roomwordexample.R;
import com.mamboexample.roomwordexample.databinding.ItemWordBinding;
import com.mamboexample.roomwordexample.entities.Word;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private final LayoutInflater mInflater;
    private List<Word> mWords;

    public WordListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    class WordViewHolder extends RecyclerView.ViewHolder {

        ItemWordBinding binding;

        private WordViewHolder(ItemWordBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }

    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ItemWordBinding binding = DataBindingUtil.inflate(mInflater, R.layout.item_word, parent, false);
        return new WordViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {

        if (mWords != null) {
            Word current = mWords.get(position);
            holder.binding.setWord(current);
        } else {
            // Covers the case of data not being ready yet.
            holder.binding.setWord(new Word("No Word"));
        }
    }

    public void setWords(List<Word> words) {
        mWords = words;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mWords != null)
            return mWords.size();
        else return 0;
    }

}
