package crispyid.co.id.movievibes;

import android.content.Intent;
import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<MovieActivity>> {

    Button btnSearch;
    ListView listView;
    EditText edtSearch;
    MovieAdapter adapter;

    static final String EXTRAS_MOVIE = "extras_movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSearch = (Button)findViewById(R.id.btn_search);
        listView = (ListView)findViewById(R.id.lv_movies);
        edtSearch = (EditText)findViewById(R.id.edt_search);

        adapter = new MovieAdapter(this);
        adapter.notifyDataSetChanged();

        btnSearch.setOnClickListener(myListener);
        listView.setAdapter(adapter);

        String dataMovie = edtSearch.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRAS_MOVIE, dataMovie);

        getLoaderManager().restartLoader(0, bundle, this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MovieActivity item = (MovieActivity) parent.getItemAtPosition(position);

                Log.d("Movie", "onItemClick");

                Intent intent = new Intent(MainActivity.this, DetailMovie.class);

                intent.putExtra(DetailMovie.EXTRA_TITLE, item.getTitle());
                intent.putExtra(DetailMovie.EXTRA_OVERVIEW, item.getOverview());
                intent.putExtra(DetailMovie.EXTRA_RELEASE_DATE, item.getReleaseDate());
                intent.putExtra(DetailMovie.EXTRA_POSTER, item.getPoster());
                intent.putExtra(DetailMovie.EXTRA_VOTE_COUNT, item.getVoteCount());
                intent.putExtra(DetailMovie.EXTRA_VOTE_AVERAGE, item.getVoteAverage());
                startActivity(intent);
            }
        });
    }

    @Override
    public Loader<ArrayList<MovieActivity>> onCreateLoader(int id, Bundle args) {
        String movie = "";
        if (args != null) {
            movie = args.getString(EXTRAS_MOVIE);
        }
        return new MovieAsyncTaskLoader(this, movie);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<MovieActivity>> loader, ArrayList<MovieActivity> data) {
        adapter.setData(data);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<MovieActivity>> loader) {
        adapter.setData(null);
    }

    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String dataMovie = edtSearch.getText().toString();
            if (TextUtils.isEmpty(dataMovie)) return;

            Bundle bundle = new Bundle();
            bundle.putString(EXTRAS_MOVIE, dataMovie);
            getLoaderManager().restartLoader(0, bundle, MainActivity.this);
        }
    };
}
