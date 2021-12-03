package com.example.tugasfebymf4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private Button btnSimpanI;
    private Button btnbBacaI;
    private Button btnSimpanE;
    private Button btnBacaE;
    private EditText txtContent;
    private String STORETEXT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSimpanI=findViewById(R.id.btnSimpanI);
        btnbBacaI=findViewById(R.id.btnBacaE);
        btnSimpanE=findViewById(R.id.btnSimpanE);
        btnBacaE=findViewById(R.id.btnBacaE);
        txtContent= (EditText) findViewById(R.id.txtContent);

        btnSimpanI.setOnClickListener(new View.OnClickListener() {
            private String STORETEXT;

            @Override
            public void onClick(View v) {
                try {
                    OutputStreamWriter out = new OutputStreamWriter(openFileOutput(STORETEXT,0));
                    out.write(txtContent.getText().toString());
                    out.close();

                    Toast.makeText(getBaseContext(), "Berhasil di simpan di internal!", Toast.LENGTH_SHORT).show();

                }catch (Throwable t) {
                    Toast.makeText(getBaseContext(), "ERROR", Toast.LENGTH_SHORT).show();

                }

            }
        });

        btnbBacaI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    InputStream in = openFileInput(STORETEXT);
                    if (in != null){
                        InputStreamReader tmp = new InputStreamReader(in);
                        BufferedReader reader = new BufferedReader(tmp);

                        String str;
                        StringBuilder buf = new StringBuilder();

                        while ((str = reader.readLine()) != null) {
                            buf.append(str);
                        }
                        in.close();
                        txtContent.setText(buf.toString());
                    }
                }catch (java.io.IOException e) {
                }
                }
        });

        btnSimpanI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    File myFile = new File("/sdcard/lat7sd.txt");
                    myFile.createNewFile();


                    FileOutputStream fOut = new FileOutputStream(myFile);
                    OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
                    myOutWriter.append(txtContent.getText());
                    myOutWriter.close();
                    fOut.close();

                    Toast.makeText(getBaseContext(), "Berhasil disimpan diExternal", Toast.LENGTH_SHORT).show();

                }catch (Exception e){
                    Toast.makeText(getBaseContext(), "Error", Toast.LENGTH_SHORT).show();


                }
            }
        });

    }
}