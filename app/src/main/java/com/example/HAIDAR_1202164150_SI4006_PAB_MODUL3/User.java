package com.example.HAIDAR_1202164150_SI4006_PAB_MODUL3;

class User {

    private String nama;
    private String pekerjaan;
    private final int ava;

    public User(String nama, String pekerjaan, int ava) {
        this.nama = nama;
        this.pekerjaan = pekerjaan;
        this.ava = ava;
    }
    public String getNama() {
        return nama;
    }
    public String getPekerjaan() {
        return pekerjaan;
    }
    public int getAvatar() {
        return ava;
    }
}
