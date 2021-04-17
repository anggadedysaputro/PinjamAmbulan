package com.nivestudio.pinjamambulan.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.khoiron.actionsheets.ActionSheet;
import com.khoiron.actionsheets.callback.ActionSheetCallBack;
import com.nivestudio.pinjamambulan.MainActivity;
import com.nivestudio.pinjamambulan.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private AlertDialog.Builder dialogExit;
    private ImageButton menu;
    private TextView sayHello;
    private SearchView search;
    private ArrayList<String> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final Animation myanim = AnimationUtils.loadAnimation(this,R.anim.bounce);
        search = findViewById(R.id.search);
        sayHello = findViewById(R.id.sayHello);
        sayHello.setText("Hello, "+getIntent().getStringExtra("nama"));


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        data.add("Logout");

        EditText editText = (EditText) search.findViewById(androidx.appcompat.R.id.search_src_text);
        editText.setTextColor(Color.BLACK);

        menu = findViewById(R.id.btMenu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new ActionSheet(HomeActivity.this,data)
                        .setTitle("What do you want to do ?")
                        .setCancelTitle("Cancel")
                        .setColorTitle(getResources().getColor(R.color.black))
                        .setColorTitleCancel(getResources().getColor(R.color.black))
                        .setColorData(getResources().getColor(R.color.black))
                        .create(new ActionSheetCallBack() {
                            @Override
                            public void data(@NotNull String s, int i) {
                                switch (data.get(i)){
                                    case "Logout" :

                                        mGoogleSignInClient.revokeAccess().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                FirebaseAuth auth = FirebaseAuth.getInstance();
                                                auth.signOut();
                                                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                startActivity(intent);
                                                finish();
                                            }
                                        });
                                        break;
                                }
                            }
                        });
            }
        });
        menu.startAnimation(myanim);

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("alhamdulillah",newText);
                return false;
            }
        });
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