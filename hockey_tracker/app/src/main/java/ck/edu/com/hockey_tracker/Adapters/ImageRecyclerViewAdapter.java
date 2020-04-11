package ck.edu.com.hockey_tracker.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import ck.edu.com.hockey_tracker.Fragments.ImageViewFragment.OnListFragmentInteractionListener;
import ck.edu.com.hockey_tracker.Fragments.dummy.DummyContent.DummyItem;
import ck.edu.com.hockey_tracker.R;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ImageRecyclerViewAdapter extends RecyclerView.Adapter<ImageRecyclerViewAdapter.ViewHolder> {

    ArrayList<String> arrayList;

    public ImageRecyclerViewAdapter(ArrayList<String> imageList) {
        this.arrayList = imageList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        String imagePath = String.valueOf(arrayList.get(position));
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);

        holder.imageView.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            imageView = (ImageView) view.findViewById(R.id.imageViewMatch);
        }
    }
}
