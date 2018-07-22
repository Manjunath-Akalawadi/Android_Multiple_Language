package com.manju7.multiple_language;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    Button button1,button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        load();
        setContentView(R.layout.activity_main);



        button1=(Button) findViewById(R.id.button);
        button3=(Button) findViewById(R.id.button2);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ChangeLangauge();
            }
        });
    }

    private void ChangeLangauge(){

        String[] langlist = {"Español","हिंदी","Sweden","Chinese","English"};
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Choose Language");
        builder.setSingleChoiceItems(langlist, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(which==0){
                    setLang("es");
                    recreate();

                }

                if(which==1){
                    setLang("hi");
                    recreate();

                }

                if(which==2){
                    setLang("sv");
                    recreate();

                }

                if(which==3){
                    setLang("zh");
                    recreate();

                }

                if(which==4){
                    setLang("en");
                    recreate();

                }

                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void setLang(String lang) {

        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext()
                .getResources().getDisplayMetrics());

        SharedPreferences.Editor editor = getSharedPreferences("Settings",MODE_PRIVATE)
                  .edit();
        editor.putString("My_Lang",lang);
        editor.apply();
    }


    public  void  load(){

        SharedPreferences preferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = preferences.getString("My_Lang","");
        setLang(language);
    }
}

