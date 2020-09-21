package com.bwrsks.videocrudsqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainUpdel extends AppCompatActivity {
    private DatabaseHandler db;
    private String Sid, Snama, Skelas;
    private EditText Enama, Ekelas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updel);

        db = new DatabaseHandler(this);

        final Intent i = this.getIntent();
        Sid = i.getStringExtra("Iid");
        Snama = i.getStringExtra("Inama");
        Skelas = i.getStringExtra("Ikelas");

        Enama = (EditText) findViewById(R.id.updel_nama);
        Ekelas = (EditText) findViewById(R.id.updel_kelas);

        Enama.setText(Snama);
        Ekelas.setText(Skelas);

        Button btnUpdate = (Button) findViewById(R.id.btn_update);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snama = String.valueOf(Enama.getText());
                Skelas = String.valueOf(Ekelas.getText());

                if (Snama.equals("")) {
                    Enama.requestFocus();
                    Toast.makeText(MainUpdel.this, "silahkan isi nama", Toast.LENGTH_SHORT).show();
                } else if (Skelas.equals("")) {
                    Ekelas.requestFocus();
                    Toast.makeText(MainUpdel.this, "silahkan isi kelas", Toast.LENGTH_SHORT).show();
                } else {
                    db.UpdateMahasiswa(new ModalMahasiswa(Sid, Snama, Skelas));
                    Toast.makeText(MainUpdel.this, "data telah diupdate", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

       Button btnDelete = (Button) findViewById(R.id.btn_delete);
       btnDelete.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               db.DeleteMahasiswa(new ModalMahasiswa(Sid, Snama, Skelas));
               Toast.makeText(MainUpdel.this, "data telah dihapus", Toast.LENGTH_SHORT).show();
               finish();
           }
       });
    }
}