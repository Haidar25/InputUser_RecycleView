package com.example.HAIDAR_1202164150_SI4006_PAB_MODUL3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class UserDetail extends AppCompatActivity {

    private TextView detail_namauser, detail_pekerjaanuser;
    private ImageView detail_gambaruser;
    private int kode_ava;
    private String objectnama,objectpekerjaan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        detail_namauser = findViewById(R.id.textview_namauser);
        detail_pekerjaanuser = findViewById(R.id.textview_pekerjaanuser);
        detail_gambaruser = findViewById(R.id.ava_user);

        objectnama = getIntent().getStringExtra("nama");
        objectpekerjaan = getIntent().getStringExtra("pekerjaan");
        kode_ava = getIntent().getIntExtra("jeniskelamin",2);

        detail_namauser.setText(objectnama);
        detail_pekerjaanuser.setText(objectpekerjaan);
        switch (kode_ava){
            case 1 :
                detail_gambaruser.setImageResource(R.drawable.cowok);
                break;
            case 2 :
            default:
                detail_gambaruser.setImageResource(R.drawable.cewek);
                break;
        }
    }
}