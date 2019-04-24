package com.app.keyalive.crudjava;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.app.keyalive.crudjava.DataHelper.DataHelper;

public class MainActivity extends AppCompatActivity {

    private Button btnCreate;
    private ListView listItem;

    String[]daftar;
    DataHelper dbcenter;
    Menu menu;
    protected Cursor cursor;
    public static MainActivity ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCreate = findViewById(R.id.btn_create);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
//        item yang nantinya di panggil pada activity lain nya
        ma=this;
        dbcenter=new DataHelper(this);

        RefreshList();
    }
//      untuk memperbarui data
    void RefreshList() {
//        memanggil data helper
        SQLiteDatabase db = dbcenter.getWritableDatabase();
        cursor = db.rawQuery("SELECT * FROM biodata", null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();

        for (int cc = 0; cc < cursor.getCount(); cc++) {
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(1).toString();
        }

        listItem = findViewById(R.id.lv_item);
        listItem.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
        listItem.setSelected(true);

        listItem.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView parent, View view, int arg2, long id) {
                final String selection = daftar[arg2];
                final CharSequence[] dialog = {"Lihat Biodata", "Update Biodata", "Hapus Biodata"};
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialog, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case (0):
                                Intent i = new Intent(getApplicationContext(), LihatDataActivity.class);
                                i.putExtra("nama", selection);
                                startActivity(i);
                                break;

                            case (1):
                                Intent in = new Intent(getApplicationContext(), UpdateData.class);
                                in.putExtra("nama", selection);
                                startActivity(in);
                                break;

                            case (2):
                                SQLiteDatabase db = dbcenter.getWritableDatabase();
                                db.execSQL("delete from biodata where nama= '" + selection + "'");
                                RefreshList();
                                break;
                        }

                    }
                });
                builder.create().show();
            }
        });((ArrayAdapter)listItem.getAdapter()).notifyDataSetInvalidated();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    }