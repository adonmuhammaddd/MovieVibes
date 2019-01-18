package crispyid.co.id.movievibes;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MovieAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private Context mContext;
    private ArrayList<MovieActivity> mData = new ArrayList<>();

    public MovieAdapter (Context mContext) {
        this.mContext = mContext;
        mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MovieActivity getItem(int i) {
        return mData.get(i);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            view = mInflater.inflate(R.layout.activity_movie, null);
            holder.ivPoster = (ImageView)view.findViewById(R.id.iv_poster);
            holder.tvTitle = (TextView)view.findViewById(R.id.tv_title);
            holder.tvReleaseDate = (TextView)view.findViewById(R.id.tv_release_date);
            holder.tvVoteAverage = (TextView)view.findViewById(R.id.tv_vote_average);
            holder.tvOverview = (TextView)view.findViewById(R.id.tv_overview);
            view.setTag(holder);
        } else {
            holder = (ViewHolder)view.getTag();
        }

        Glide.with(mContext).load("http://image.tmdb.org/t/p/w185/"+mData.get(i).getPoster()).into(holder.ivPoster);
        holder.tvTitle.setText(mData.get(i).getTitle());
//        String getDate = mData.get(i).getReleaseDate();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            Date date = dateFormat.parse(getDate);
//            SimpleDateFormat newDateFormat = new SimpleDateFormat("EEEE, dd/MM/yyyy");
//            String dateRelease = newDateFormat.format(date);
//            holder.tvReleaseDate.setText(R.string.release + dateRelease);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        holder.tvVoteAverage.setText(R.string.rating + mData.get(i).getVoteAverage()+"%");
        holder.tvOverview.setText(mData.get(i).getOverview());

        return view;
    }

    public static class ViewHolder {
        ImageView ivPoster;
        TextView tvTitle, tvReleaseDate, tvVoteAverage, tvOverview;
    }

    public void setData(ArrayList<MovieActivity> item) {
        mData = item;
        notifyDataSetChanged();
    }

    public void addItem(final MovieActivity item) {
        mData.add(item);
        notifyDataSetChanged();
    }

    public void clearData() {
        mData.clear();
    }

    public int getViewType(int i) {
        return 0;
    }

    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getCount() {
        if (mData == null) return 0;
        return mData.size();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
}
