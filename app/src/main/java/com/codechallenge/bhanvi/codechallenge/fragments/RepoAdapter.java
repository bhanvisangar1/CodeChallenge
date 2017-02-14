package com.codechallenge.bhanvi.codechallenge.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codechallenge.bhanvi.codechallenge.R;
import com.codechallenge.bhanvi.codechallenge.model.RepoInfo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by bhanvisangar on 2/11/17.
 */

public class RepoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int HEADER_VIEW = 0;
    private final int ITEM_VIEW = 1;

    ArrayList<RepoInfo> repoList = new ArrayList<>();
    BehaviorSubject<String> urlToOpen = BehaviorSubject.create();

    // class constructor
    public RepoAdapter(ArrayList repoList, BehaviorSubject<String> urlToOpen) {
        this.repoList = repoList;
        this.urlToOpen = urlToOpen;
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView idTextView, nameTextView, languageTextView, lastUpdatedTextView, ownerLoginTextView;
        ImageView avatarImageView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            idTextView = (TextView) itemView.findViewById(R.id.id_textview);
            nameTextView = (TextView) itemView.findViewById(R.id.name_textview);
            languageTextView = (TextView) itemView.findViewById(R.id.language);
            lastUpdatedTextView = (TextView) itemView.findViewById(R.id.updated);
            ownerLoginTextView = (TextView) itemView.findViewById(R.id.owner_login);
            avatarImageView = (ImageView) itemView.findViewById(R.id.avatar_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = this.getAdapterPosition() - 1;
            String url = repoList.get(position).cloneUrl;
            urlToOpen.onNext(url);

        }
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView urlTextView;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            titleTextView = (TextView) itemView.findViewById(R.id.title_textview);
            urlTextView = (TextView) itemView.findViewById(R.id.url_textview);
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_VIEW) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
            return new ItemViewHolder(view);

        } else if (viewType == HEADER_VIEW) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_row, parent, false);
            return new HeaderViewHolder(view);
        }
        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            position = position - 1;
            itemViewHolder.idTextView.setText(repoList.get(position).id);
            itemViewHolder.nameTextView.setText(repoList.get(position).name);
            itemViewHolder.ownerLoginTextView.setText(repoList.get(position).owner.login);
            itemViewHolder.lastUpdatedTextView.setText(repoList.get(position).updatedAt);
            itemViewHolder.languageTextView.setText(repoList.get(position).language);
            Picasso.with(itemViewHolder.avatarImageView.getContext()).load(repoList.get(position).owner.avatarUrl).into(itemViewHolder.avatarImageView);

        } else if (holder instanceof HeaderViewHolder) {
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
            headerViewHolder.titleTextView.setText(R.string.header_title);
            headerViewHolder.urlTextView.setText(R.string.url_info);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return HEADER_VIEW;
        else return ITEM_VIEW;
    }

    @Override
    public int getItemCount() {
        return repoList.size() + 1;
    }
}
