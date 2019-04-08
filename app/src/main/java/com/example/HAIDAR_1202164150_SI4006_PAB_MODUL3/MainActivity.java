package com.example.HAIDAR_1202164150_SI4006_PAB_MODUL3;

import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    Dialog dialog;
    private RecyclerView objectRecyclerView;
    private ArrayList<User> daftarUser;
    private UserAdapter userAdapter;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        objectRecyclerView = findViewById(R.id.RecyclerView);

        int gridColumnCount = getResources().getInteger(R.integer.grid_column_count);
        objectRecyclerView.setLayoutManager(new GridLayoutManager(this, gridColumnCount));

        daftarUser = new ArrayList<>();
        if (savedInstanceState != null) {
            daftarUser.clear();
            for (int i = 0; i < savedInstanceState.getStringArrayList("nama").size(); i++) {
                daftarUser.add(new User(savedInstanceState.getStringArrayList("nama").get(i),
                        savedInstanceState.getStringArrayList("pekerjaan").get(i),
                        savedInstanceState.getIntegerArrayList("jenis kelamin").get(i)));
            }
        } else {
            init();
        }
        userAdapter = new UserAdapter(daftarUser, this);
        objectRecyclerView.setAdapter(userAdapter);

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN | ItemTouchHelper.UP,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();
                Collections.swap(daftarUser, from, to);
                userAdapter.notifyItemMoved(from, to);

                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                daftarUser.remove(viewHolder.getAdapterPosition());
                userAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });
        helper.attachToRecyclerView(objectRecyclerView);
    }
    void init(){ //default user
        daftarUser.clear();
        daftarUser.add(new User("Haidar","CEO Haitech",1));
        daftarUser.add(new User("Alvinanda","Backend Developer",2));
        daftarUser.add(new User("Sulistyo","Front End Developer",1));

    }
    void tambah(View view){
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_tambah_user);
        final TextView mNama,mPekerjaan;
        final Spinner mGender;
        mNama = dialog.findViewById(R.id.txtview_namauser);
        mPekerjaan = dialog.findViewById(R.id.txtview_pekerjaan);

        TextView tambah=dialog.findViewById(R.id.btn_tambahkan);
        TextView batal = dialog.findViewById(R.id.btn_batalkan);

        mGender = dialog.findViewById(R.id.Spinner_Jeniskelamin);
        String[]list={"Laki-Laki","Perempuan"};

        ArrayAdapter<String>adapterX = new ArrayAdapter(dialog.getContext(),android.R.layout.simple_spinner_item,list);
        mGender.setAdapter(adapterX);

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daftarUser.add(new User(mNama.getText().toString(),mPekerjaan.getText().toString(),mGender.getSelectedItemPosition()+1));
                userAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        ArrayList<String>tempListNama = new ArrayList<>();
        ArrayList<String>tempListPekerjaan = new ArrayList<>();
        ArrayList<Integer>tempListGender = new ArrayList<>();
        for (int i = 0; i <daftarUser.size() ; i++) {
            tempListNama.add(daftarUser.get(i).getNama());
            tempListPekerjaan.add(daftarUser.get(i).getPekerjaan());
            tempListGender.add(daftarUser.get(i).getAvatar());
        }
        outState.putStringArrayList("nama",tempListNama);
        outState.putStringArrayList("pekerjaan",tempListPekerjaan);
        outState.putIntegerArrayList("Jenis Kelamin",tempListGender);
        super.onSaveInstanceState(outState);
    }
}