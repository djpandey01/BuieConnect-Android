package me.arkanayan.buieconnect.adapters;

import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.arkanayan.buieconnect.R;
import me.arkanayan.buieconnect.activities.NoticesFragment.OnListFragmentInteractionListener;
import me.arkanayan.buieconnect.models.Notice;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Notice} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class NoticesAdapter extends RecyclerView.Adapter<NoticesAdapter.ViewHolder> {

    private final String TAG = this.getClass().getSimpleName();

    private final List<Notice> mValues;
    private final OnListFragmentInteractionListener mListener;

    public NoticesAdapter(List<Notice> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_notices, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTitleView.setText(mValues.get(position).getTitle());
        holder.mMessageView.setText(mValues.get(position).getMessage());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    Log.d(TAG, "onClick: position: " + holder.getAdapterPosition());
                    mListener.onNoticeSelected(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTitleView;
        public final TextView mMessageView;
        public Notice mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitleView = (TextView) view.findViewById(R.id.text_view_card_title);
            mMessageView = (TextView) view.findViewById(R.id.text_view_card_message);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mMessageView.getText() + "'";
        }
    }
}