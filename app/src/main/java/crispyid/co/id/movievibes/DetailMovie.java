package crispyid.co.id.movievibes;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailMovie extends AppCompatActivity {

    private TextView tvTitle, tvReleaseDate, tvOverview, tvVoteCount, tvVoteAverage;
    private ImageView ivPoster;
    private Context mContext;
    private String changeReleaseDate;

    public static String EXTRA_TITLE = "EXTRA_TITLE";
    public static String EXTRA_POSTER = "EXTRA_POSTER";
    public static String EXTRA_RELEASE_DATE = "EXTRA_RELEASE_DATE";
    public static String EXTRA_OVERVIEW = "EXTRA_OVERVIEW";
    public static String EXTRA_VOTE_COUNT = "EXTRA_VOTE_COUNT";
    public static String EXTRA_VOTE_AVERAGE = "EXTRA_VOTE_AVERAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        tvTitle = (TextView)findViewById(R.id.tv_detail_title);
        String title = getIntent().getStringExtra(EXTRA_TITLE);
        tvReleaseDate = (TextView)findViewById(R.id.tv_detail_release_date);
        String releaseDate = getIntent().getStringExtra(EXTRA_RELEASE_DATE);
        tvOverview = (TextView)findViewById(R.id.tv_detail_overview);
        String overview = getIntent().getStringExtra(EXTRA_OVERVIEW);
        tvVoteCount = (TextView)findViewById(R.id.tv_detail_vote_count);
        String voteCount = getIntent().getStringExtra(EXTRA_VOTE_COUNT);
        tvVoteAverage = (TextView)findViewById(R.id.tv_detail_vote_average);
        String voteAverage = getIntent().getStringExtra(EXTRA_VOTE_AVERAGE);
        ivPoster = (ImageView)findViewById(R.id.iv_detail_poster);
        String poster = getIntent().getStringExtra(EXTRA_POSTER);

        tvTitle.setText(title);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dateFormat.parse(releaseDate);
            SimpleDateFormat newDateFormat = new SimpleDateFormat("EEEE, dd/MM/yyyy");
            String dateRelease = newDateFormat.format(date);
//            tvReleaseDate.setText("Rilis : " + dateRelease);
            final SpannableStringBuilder regulerDate = new SpannableStringBuilder(R.string.release + dateRelease);
            final StyleSpan boldDate = new StyleSpan(Typeface.BOLD);
            regulerDate.setSpan(boldDate, 0, 7, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            tvReleaseDate.setText(regulerDate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        tvOverview.setText(overview);
        if (TextUtils.isEmpty(overview)) {
            tvOverview.setText(R.string.no_overview + title + ".");
        }

//        tvVoteCount.setText("Total : " + voteCount + " vote");
//        tvVoteAverage.setText("Rating : " + voteAverage + " %");

        final SpannableStringBuilder regulerVoteCount = new SpannableStringBuilder(R.string.total + voteCount + " vote");
        final StyleSpan boldVoteCount = new StyleSpan(Typeface.BOLD);
        regulerVoteCount.setSpan(boldVoteCount, 0, 8, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        tvVoteCount.setText(regulerVoteCount);

        final SpannableStringBuilder regulerVoteAverage = new SpannableStringBuilder(R.string.rating + voteAverage + " %");
        final StyleSpan boldVoteAverage = new StyleSpan(Typeface.BOLD);
        regulerVoteAverage.setSpan(boldVoteAverage, 0, 8, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        tvVoteAverage.setText(regulerVoteAverage);

        Glide.with(DetailMovie.this).load("htttp://image.tmdb.org/t/p/w185" + poster).into(ivPoster);
    }
}
