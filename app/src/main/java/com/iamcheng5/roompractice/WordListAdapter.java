package com.iamcheng5.roompractice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class WordListAdapter extends ListAdapter<Word, WordListAdapter.WordViewHolder> {
    private OnWordListItemClicked onWordListItemClicked;

    protected WordListAdapter(@NonNull OnWordListItemClicked onWordListItemClicked) {
        super(WORD_DIFF);
        this.onWordListItemClicked = onWordListItemClicked;
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final WordViewHolder wordViewHolder = new WordViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_word_item, parent, false));
        wordViewHolder.btnDelete.setOnClickListener(view -> {
            onWordListItemClicked.onItemClicked(getItem(wordViewHolder.getAdapterPosition()));
        });
        return wordViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        Word word = getItem(position);
        holder.tvWord.setText(word.getWord());

    }

    public static class WordViewHolder extends RecyclerView.ViewHolder {
        private TextView tvWord;
        private Button btnDelete;

        public WordViewHolder(@NonNull View itemView) {
            super(itemView);
            tvWord = itemView.findViewById(R.id.wordItemRec_tv_word);
            btnDelete = itemView.findViewById(R.id.wordItemRec_btn_delete);
        }

    }

    private final static DiffUtil.ItemCallback<Word> WORD_DIFF = new DiffUtil.ItemCallback<Word>() {
        @Override
        public boolean areItemsTheSame(@NonNull Word oldItem, @NonNull Word newItem) {
            return oldItem.getWord().equals(newItem.getWord());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Word oldItem, @NonNull Word newItem) {
            return oldItem.getWord().equals(newItem.getWord());
        }
    };

    public interface OnWordListItemClicked {
        void onItemClicked(Word word);
    }
}
