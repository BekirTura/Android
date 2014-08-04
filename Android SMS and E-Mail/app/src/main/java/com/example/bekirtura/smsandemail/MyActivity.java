package com.example.bekirtura.smsandemail;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MyActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Button sms=(Button)findViewById(R.id.button);
        Button e_mail=(Button)findViewById(R.id.button2);
        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String smsNumber="number*******";
                String smsText="**** naber ";
                Uri uri= Uri.parse("smsto:"+smsNumber);
                Intent intent=new Intent(Intent.ACTION_SENDTO, uri );
                intent.putExtra("sms_body", smsText);
                startActivity(intent);

            }
        });
        e_mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent= new Intent(Intent.ACTION_SEND);
               intent.setType("text/html");
               intent.putExtra(Intent.EXTRA_EMAIL,new String[]{"email*************@hotmail.com"});
               intent.putExtra(Intent.EXTRA_SUBJECT, "Android");
               intent.putExtra(Intent.EXTRA_TEXT,"naber nasılsın ?");
               startActivity(Intent.createChooser(intent,"Send mail"));
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
