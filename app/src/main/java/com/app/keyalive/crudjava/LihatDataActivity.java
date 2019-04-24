package com.app.keyalive.crudjava;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.app.keyalive.crudjava.DataHelper.DataHelper;

public class LihatDataActivity extends AppCompatActivity {
//        implements View.OnClickListener

    DataHelper dbhelper = new DataHelper(this);
    protected Cursor cursor;

    private TextView TextView01;
    private TextView TextView02;
    private TextView TextView04;
    private TextView TextView03;
    private TextView TextView05;
    private TextView txtNo;
    private TextView txtNama;
    private TextView txtTglLair;
    private TextView txtJenkel;
    private TextView txtAlamat;
    private Button   button1;
    private TextView txtPendidikan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_data);

        TextView01 = findViewById(R.id.TextView01);
        TextView02 = findViewById(R.id.TextView02);
        TextView04 = findViewById(R.id.TextView04);
        TextView03 = findViewById(R.id.TextView03);
        TextView05 = findViewById(R.id.TextView05);
        txtNo = findViewById(R.id.txt_no);
        txtNama = findViewById(R.id.txt_nama);
        txtTglLair = findViewById(R.id.txt_tgl_lair);
        txtJenkel = findViewById(R.id.txt_jenkel);
        txtAlamat = findViewById(R.id.txt_alamat);
        txtPendidikan = findViewById(R.id.txt_pendidikan);

        button1 = findViewById(R.id.button1);

        SQLiteDatabase db = dbhelper.getReadableDatabase();

        cursor = db.rawQuery("SELECT * FROM biodata WHERE nama='" + getIntent().getStringExtra("nama") + "'", null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            txtNo.setText(cursor.getString(0).toString());
            txtNama.setText(cursor.getString(1).toString());
            txtJenkel.setText(cursor.getString(3).toString());
            txtTglLair.setText(cursor.getString(2).toString());
            txtAlamat.setText(cursor.getString(4).toString());
          txtPendidikan.setText(cursor.getString(5).toString());
        }

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                finish();
            }
        });
    }


//    @Override
//    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.button1:
//                finish();
//        }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }


    }
