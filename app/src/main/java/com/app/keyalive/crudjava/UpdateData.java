package com.app.keyalive.crudjava;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.keyalive.crudjava.DataHelper.DataHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class UpdateData extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbhelper;

    private TextView textView1;
    private EditText edtNomor;
    private TextView textView2;
    private EditText edtNama;
    private TextView textView3;
    private EditText edtTgllahir;
    private TextView textView4;
    private RadioGroup rbkelamin;
    private RadioButton rblk;
    private RadioButton rbpr;
    private TextView textView5;
    private EditText edtAlamat;
    private Button btnSimpan;
    TextView txtNo;
    TextView txtNama;
    TextView txtTglLair;
    TextView txtJenkel;
    TextView txtAlamat;
    private Button btnBack;
    private RadioGroup rbKelamin;
    private RadioButton RbLk;
    private RadioButton RbPr;
    private Spinner spinnerPendidikan;
    String[] items={"--Pilih Pendidikan--","SD", "SMP","SMA","D3","S1","S2","S3"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        dbhelper = new DataHelper(this);

        textView1 = findViewById(R.id.textView1);
        edtNomor = findViewById(R.id.edt_nomor);
        textView2 = findViewById(R.id.textView2);
        edtNama = findViewById(R.id.edt_nama);
        textView3 = findViewById(R.id.textView3);
        edtTgllahir = findViewById(R.id.edt_tgllahir);
        textView4 = findViewById(R.id.textView4);
        rbkelamin = findViewById(R.id.rb_kelamin);
        rblk = findViewById(R.id.Rb_lk);
        rbpr = findViewById(R.id.Rb_pr);
        textView5 = findViewById(R.id.textView5);
        edtAlamat = findViewById(R.id.edt_alamat);
        btnSimpan = findViewById(R.id.btn_simpan);
        btnBack = findViewById(R.id.btn_back);
        rbKelamin = findViewById(R.id.rb_kelamin);
        RbLk = findViewById(R.id.Rb_lk);
        RbPr = findViewById(R.id.Rb_pr);
       spinnerPendidikan = findViewById(R.id.spinner_pendidikan);


       ArrayAdapter<String> a=new ArrayAdapter<String>(UpdateData.this,android.R.layout.simple_spinner_item,items);
        a.setDropDownViewResource(android.R.layout.simple_spinner_item);spinnerPendidikan.setAdapter(a);

        edtTgllahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar newCalendar = Calendar.getInstance();

                DatePickerDialog datePickerDialog = new DatePickerDialog(UpdateData.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
                        edtTgllahir.setText(" " + dateFormatter.format(newDate.getTime()));
                    }

                }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.show();
            }
        });


        SQLiteDatabase db = dbhelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM biodata WHERE nama='" + getIntent().getStringExtra("nama") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);

            edtNomor.setText(cursor.getString(0).toString());
            edtNama.setText(cursor.getString(1).toString());
            edtTgllahir.setText(cursor.getString(2).toString());
            Log.d("UpdateData", "onCreate: " + cursor.getString(3).toString());
            if (cursor.getString(3).toString().equals("LAKI LAKI")) {

                rblk.setChecked(true);
            } else {
                rbpr.setChecked(true);
            }
            edtAlamat.setText(cursor.getString(4).toString());

          spinnerPendidikan.setSelection(5);
        }


        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                SQLiteDatabase db = dbhelper.getWritableDatabase();

                int gender = rbkelamin.getCheckedRadioButtonId();
                RadioButton jk = (RadioButton) findViewById(gender);
                String inputjk = String.valueOf(jk.getText().toString());

                db.execSQL("update biodata set nama='" + edtNama.getText().toString() +
                        "', tgl_lahir='" + edtTgllahir.getText().toString() +
                        "', jenkel='" + inputjk +
                        "', alamat='" + edtAlamat.getText().toString() +
                       "', pendidikan='" + spinnerPendidikan.getSelectedItem().toString() +
                        "' where nomor='" + edtNomor.getText().toString() + "'");

                Toast.makeText(getApplicationContext(), "Data telah di edit", Toast.LENGTH_SHORT).show();
                MainActivity.ma.RefreshList();
                finish();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                finish();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


}
