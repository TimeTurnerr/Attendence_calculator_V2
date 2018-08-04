package com.example.desmond.attendencecalculator;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
public class MainActivity extends AppCompatActivity {
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    double bn,tc,ta;
    double ap;
    double tcc=0;
    double bnn=0;
    int TCA=0,TCC=0;
    long AP=0;
    private SharedPreferences shared;
    private Drawable drawable;
    TextView TA,TC;
    TextView editText3;
    EditText editText,editText2;

    public void clickFunction(View view) {
        bnn = shared.getInt("bunk", 0);
        tcc = shared.getInt("total", 0);
        String message = "";


        if(editText.getText().toString().isEmpty())
        {
            if(editText2.getText().toString().isEmpty())
            {
                message = "Please enter a number";
            }
            else
            {
                message = "Please enter a number";
            }

        }
        else {
            if (editText2.getText().toString().isEmpty()) {
                message = "Please enter a number";
            } else
            {
                bn = Double.parseDouble(editText.getText().toString());
                tc = Double.parseDouble(editText2.getText().toString());
                bnn = bnn + bn;
                tcc = tcc + tc;
                ta = tcc - bnn;
                ap = (ta / tcc) * 100;
                Toast.makeText(this, String.valueOf(ap), Toast.LENGTH_SHORT).show();
                editText3.setText(String.valueOf(Math.round(ap) + " %"));
                TCA = (int) ta;
                TCC = (int) tcc;
                AP = (long) ap;
                TA.setText("Total Classes Attended : "+String.valueOf(TCA));
                TC.setText("Total no. of Classes : "+ String.valueOf(TCC));
                if (ap <= 65.0)
                {
                    editText3.setBackgroundResource(R.drawable.circle2);

                } else if (ap > 65.0 && ap <= 70.0)
                {
                    editText3.setBackgroundResource(R.drawable.circle3);
                } else if (ap > 70.0)
                {
                    editText3.setBackgroundResource(R.drawable.circle4);
                }

            }
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }
    public void updateFunction(View view)
    {
        //update values
        SharedPreferences.Editor editor = shared.edit();
        editor.putInt("bunk", (int) (bnn));
        editor.putInt("total", (int) (tcc));

        editor.putInt("totalAttended", (int) (TCA));
        editor.putInt("totalClasses", (int) (TCC));
        editor.putLong("percent",(long) (AP));
        editor.apply();

    }

    public void ClearFunction(View view)
    {
        SharedPreferences.Editor editor = shared.edit();
        editor.clear();
        TA.setText("Total Classes Attended : "+0);
        TC.setText("Total no. of Classes : "+0);
        editText3.setText(0+ " %");
        editText3.setBackgroundResource(R.drawable.circle);
        editor.commit();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shared = PreferenceManager.getDefaultSharedPreferences(this);

        editText =  (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (TextView) findViewById(R.id.editText3);
        TA = (TextView)findViewById(R.id.TA);
        TC = (TextView)findViewById(R.id.TC);

        // Reading from SharedPreferences
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        int tca = shared.getInt("totalAttended",0);
        int tcc = shared.getInt("totalClasses",0);
        long ap = shared.getLong("percent",0);
        Log.v("test", String.valueOf(tca));

        TA.setText("Total Classes Attended : "+String.valueOf(tca));
        TC.setText("Total no. of Classes : "+ String.valueOf(tcc));
        editText3.setText(String.valueOf(Math.round(ap) + " %"));

        if (ap <= 65.0)
        {
            editText3.setBackgroundResource(R.drawable.circle2);

        } else if (ap > 65.0 && ap <= 70.0)
        {
            editText3.setBackgroundResource(R.drawable.circle3);
        } else if (ap > 70.0)
        {
            editText3.setBackgroundResource(R.drawable.circle4);
        }



        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}