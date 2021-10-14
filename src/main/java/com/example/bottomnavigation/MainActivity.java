package com.example.bottomnavigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class MainActivity extends AppCompatActivity {
    //instalasi variabel
    MeowBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //memasukkan variabel
        bottomNavigation = findViewById(R.id.bottom_navigation);

        //menambahkan menu item
        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_notification));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_message));

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                // instalasi fragment
                Fragment fragment = null;
                // memeriksa kondisi
                switch (item.getId()) {
                    case 1:
                        // ketika memilih id ke-1
                        // maka tampil fragment notifikasi
                        fragment = new NotificationFragment();
                        break;
                    case 2:
                        // ketika memilih id ke-2
                        // maka tampil fragment home
                        fragment = new HomeFragment();
                        break;
                    case 3:
                        // ketika memilih id ke-3
                        // maka tampil fragment message
                        fragment = new MessageFragment();
                        break;
                }
                //load fragment
                loadFragment(fragment);

            }
        });

        // mengatur count notification
        bottomNavigation.setCount(1, "6");
        bottomNavigation.setCount(3, "10");
        // mengatur pemilihan menu home fragment
        bottomNavigation.show(2, true);

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                // tampilan toast
                Toast.makeText(getApplicationContext()
                        , "You Clicked " + item.getId()
                        ,Toast.LENGTH_SHORT).show();
            }
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                // tampilan Toast
                Toast.makeText(getApplicationContext()
                        , "You Reslected " + item.getId()
                        ,Toast.LENGTH_SHORT).show();


            }
        });
    }

    private void loadFragment(Fragment fragment) {
        // memindah fragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout,fragment)
                .commit();
    }
}