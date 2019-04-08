package com.example.HAIDAR_1202164150_SI4006_PAB_MODUL3;

import android.content.Context;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private ArrayList<User> daftarUser;
    private Context objectContext;

    public UserAdapter(ArrayList<User> daftarUser, Context objectContext) {
        this.objectContext = objectContext;
        this.daftarUser = daftarUser;
    }
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(objectContext).
                inflate(R.layout.activity_list_user, viewGroup, false));
    }
    @Override
    public void onBindViewHolder(UserAdapter.ViewHolder viewHolder, int list) {
        User currentUser = daftarUser.get(list);
        viewHolder.bindTo(currentUser);
    }
    @Override
    public int getItemCount() {
        return daftarUser.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView nama, pekerjaan;
        private ImageView gambar_user;
        private int kode_ava;

        public ViewHolder(View itemView) {
            super(itemView);

            nama = itemView.findViewById(R.id.texview_user);
            pekerjaan = itemView.findViewById(R.id.textview_pekerjaan);
            gambar_user = itemView.findViewById(R.id.avatar);

            itemView.setOnClickListener(this);
        }
        void bindTo(User currentUser){
            nama.setText(currentUser.getNama());
            pekerjaan.setText(currentUser.getPekerjaan());

            kode_ava = currentUser.getAvatar();
            switch (currentUser.getAvatar()){
                case 1 :
                    gambar_user.setImageResource(R.drawable.cowok);
                    break;
                case 2 :
                default:
                    gambar_user.setImageResource(R.drawable.cewek);
                    break;
            }
        }
        @Override
        public void onClick(View v) {
            Intent toDetailActivity = new Intent(v.getContext(), UserDetail.class);
            toDetailActivity.putExtra("nama",nama.getText().toString());
            toDetailActivity.putExtra("jeniskelamin",kode_ava);
            toDetailActivity.putExtra("pekerjaan",pekerjaan.getText().toString());
            v.getContext().startActivity(toDetailActivity);
        }
    }
}