package com.nivestudio.pinjamambulan.home;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.nivestudio.pinjamambulan.MainActivity;
import com.nivestudio.pinjamambulan.R;

public class HomeActivity extends AppCompatActivity {
    private AlertDialog.Builder dialogExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("alhamdulillah","ketemu");
    }

    @Override
    public void onBackPressed() {
        dialogExit = new AlertDialog.Builder(HomeActivity.this);
        dialogExit.setTitle("Keluar Aplikasi");
        dialogExit.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finishAffinity();
            }
        });
        dialogExit.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        dialogExit.setMessage("Apakah Anda Ingin Menutup Aplikasi ?");
        dialogExit.show();
    }
}