
package com.example.android.quakereport;

import android.app.LoaderManager;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.content.AsyncTaskLoader;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.content.AsyncTaskLoader;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.AsyncTaskLoader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<ArrayList<Earthquake>> {
    //view for the empty state
    private TextView mEmptyStateTextView;
    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=4.5&limit=100";
    private QuakeAdapter adapter;
    //ID for the loader
    private static final int EARTHQUAKE_LOADER_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);
        // Get a reference to the LoaderManager, in order to interact with loaders.

        LoaderManager loaderManager = getLoaderManager();
        // Initialize the loader. Pass in the int ID constant defined above and pass in null for
        // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
        // because this activity implements the LoaderCallbacks interface).
        loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this);

        ListView earthquakeListView = (ListView)findViewById(R.id.list);
        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        earthquakeListView.setEmptyView(mEmptyStateTextView);

        // Create a new adapter that takes an empty list of earthquakes as input
        adapter = new QuakeAdapter(this, new ArrayList<Earthquake>());
        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current earthquake that was clicked on

                Earthquake currentEarthquake = adapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });
        EarthquakeAsyncTask task = new EarthquakeAsyncTask();
        task.execute(USGS_REQUEST_URL);


    }
    private void updateUi(List<Earthquake> earthquake) {
        ArrayList<Earthquake> earthquakes = QueryUtils.extractFeatureFromJson(USGS_REQUEST_URL);

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes

        final QuakeAdapter adapter = new QuakeAdapter(this, earthquakes);
        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Earthquake curr = adapter.getItem(position);
                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri earthquakeUri = Uri.parse(curr.getUrl());
                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);
                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });
    }

    @Override
    public Loader<ArrayList<Earthquake>> onCreateLoader(int id, Bundle args) {
        return new EarthquakeLoader(this, USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Earthquake>> loader, ArrayList<Earthquake> data) {
        // Set empty state text to display "No earthquakes found."
        mEmptyStateTextView.setText(R.string.no_earthquakes);
        // Clear the adapter of previous earthquake data
        adapter.clear();
        if(data!=null&&!data.isEmpty())
        {
            adapter.addAll(data);
        }

    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Earthquake>> loader) {
        adapter.clear();
    }

    private class EarthquakeAsyncTask extends AsyncTask<String,Void,ArrayList<Earthquake>> {
        @Override
        protected ArrayList<Earthquake> doInBackground(String... urls) {
            if(urls.length<1||urls[0]==null){return null;}
            ArrayList<Earthquake> result = QueryUtils.fetchEarthquakeData(urls[0]);
            return result;
        }

        @Override
        protected void onPostExecute(ArrayList<Earthquake> earthquakes) {
            // Clear the adapter of previous earthquake data
            adapter.clear();
            if(earthquakes!=null&&!earthquakes.isEmpty())
            {
                adapter.addAll(earthquakes);
            }
        }

    }
}
