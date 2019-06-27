package com.example.alumno.clase5;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ShareActionProvider;

public class OtroActivity extends AppCompatActivity {
    private ShareActionProvider shareActionProvider;
    String url = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otro);

        Intent i = getIntent();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(i.getStringExtra("title"));

        //MyListener listener = new MyListener(this);
        /*Button btn = (Button) super.findViewById(R.id.btnBack);
        btn.setOnClickListener(listener);*/

        WebView webView = (WebView)super.findViewById(R.id.wView);
        WebSettings webSettings = webView.getSettings();

        webSettings.setJavaScriptEnabled(true);

        url = i.getStringExtra("url");
        Log.d("URLLLLLLLLLLLL", url);
        webView.loadUrl(url);

        FloatingActionButton comp = (FloatingActionButton) findViewById(R.id.shareLink);
        comp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent myShareIntent = new Intent(Intent.ACTION_SEND);
                myShareIntent.setType("text/plain");
                myShareIntent.putExtra(Intent.EXTRA_TEXT,url);
                shareActionProvider.setShareIntent(myShareIntent);*/

                Intent share = new Intent(android.content.Intent.ACTION_SEND);
                share.setType("text/plain");
                share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                share.putExtra(Intent.EXTRA_TEXT, url);

                startActivity(Intent.createChooser(share, "Compartir Noticia"));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
