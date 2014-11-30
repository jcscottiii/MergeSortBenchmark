package io.jamescscott.mergesortbenchmark;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

    // UI Components.
    // Start Benchmark Button.
    private static Button START_BENCHMARK_BUTTON;
    // Text View to display status of benchmark.
    private static TextView WELCOME_TEXT_VIEW;

    private class BenchmarkRunnerTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Disable button.
            START_BENCHMARK_BUTTON.setEnabled(false);
            // Tell the user the benchmark is currently running.
            WELCOME_TEXT_VIEW.setText(R.string.running);
        }

        protected Boolean doInBackground(Void... voids) {
            return MergeSortBenchmarkController.conductMergeSortBenchmark(getApplicationContext());
        }

        @Override
        protected void onPostExecute(Boolean success) {
            super.onPostExecute(success);
            if (success) {
                // Tell the user where to look for the data.
                WELCOME_TEXT_VIEW.setText("Benchmark complete.\nPlease check the\n\""
                        + getApplicationContext().getFilesDir().getAbsolutePath()
                        + "\"\nfolder for data.");
            } else {
                WELCOME_TEXT_VIEW.setText(R.string.failure);
            }
            // Enable the button so that it may be run again.
            START_BENCHMARK_BUTTON.setEnabled(true);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Acquire the objects for our UI components once the UI is built.
        START_BENCHMARK_BUTTON = (Button) findViewById(R.id.start_benchmark);
        WELCOME_TEXT_VIEW = (TextView) findViewById(R.id.welcome);
        // Set the listener to start the benchmark upon clicking the button.
        START_BENCHMARK_BUTTON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new BenchmarkRunnerTask().execute();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
