package crispyid.co.id.movievibes;

import android.content.Context;
import android.content.AsyncTaskLoader;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MovieAsyncTaskLoader extends AsyncTaskLoader<ArrayList<MovieActivity>> {

    private String movie;
    private boolean mHasResult = false;
    private ArrayList<MovieActivity> mData;
    private String API_KEY = "7d7f7d358708ac715c17ba0844cf1830";

    MovieAsyncTaskLoader(final Context mContext, String movie) {
        super(mContext);
        onContentChanged();
        this.movie = movie;
    }

    @Override
    public void deliverResult(final ArrayList<MovieActivity> data) {
        mData = data;
        mHasResult = true;
        super.deliverResult(data);
    }

    @Override
    protected void onStartLoading() {
        if (takeContentChanged())
            forceLoad();
        else if (mHasResult)
            deliverResult(mData);
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        if(mHasResult) {
            mData = null;
            mHasResult = false;
        }
    }

    @Override
    public ArrayList<MovieActivity> loadInBackground() {
        SyncHttpClient client = new SyncHttpClient();

        final ArrayList<MovieActivity> movieItems = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/search/movie?api_key="+API_KEY+"&language=en-US&query="+movie;
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                setUseSynchronousMode(true);
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");
                    for (int i = 0; i < list.length(); i++){
                        JSONObject dataMovie = list.getJSONObject(i);
                        MovieActivity movieActivity = new MovieActivity(dataMovie);
                        movieItems.add(movieActivity);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] header, byte[] responseBody, Throwable error){}
        });
        return movieItems;
    }
}
