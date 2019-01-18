package crispyid.co.id.movievibes;

import org.json.JSONObject;

import java.text.DecimalFormat;

public class MovieActivity {

    private int id;
    private String title;
    private String poster;
    private String releaseDate;
    private String overview;
    private String voteCount;
    private String voteAverage;

    public MovieActivity(JSONObject object) {
        try {
            int id = object.getInt("id");
            String title = object.getString("title");
            String poster = object.getString("poster_path");
            String releaseDate = object.getString("release_date");
            String overview = object.getString("overview");
            String voteCount = object.getString("vote_count");
            Double voteAverage = object.getDouble("vote_double");

            double rating_result = voteAverage * 10;
            String rating = new DecimalFormat("##.##").format(rating_result);

            this.id = id;
            this.title = title;
            this.poster = poster;
            this.releaseDate = releaseDate;
            this.overview = overview;
            this.voteCount = voteCount;
            this.voteAverage = rating;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getTitle() {
        return title;
    }

    public String getPoster() {
        return poster;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getOverview() {
        return overview;
    }

    public String getVoteCount() {
        return voteCount;
    }

    public String getVoteAverage() {
        return voteAverage;
    }
}