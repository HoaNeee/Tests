package com.hoaphph29102.pnlib_ass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.hoaphph29102.pnlib_ass.Fragment.AddUserFragment;
import com.hoaphph29102.pnlib_ass.Fragment.DoanhThuFragment;
import com.hoaphph29102.pnlib_ass.Fragment.DoiMkFragment;
import com.hoaphph29102.pnlib_ass.Fragment.LoaiSachFragment;
import com.hoaphph29102.pnlib_ass.Fragment.MemberFragment;
import com.hoaphph29102.pnlib_ass.Fragment.PhieuFragment;
import com.hoaphph29102.pnlib_ass.Fragment.SachFragment;
import com.hoaphph29102.pnlib_ass.Fragment.TopFragment;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawer_main;
    Toolbar toolbar_main;
    NavigationView nav_main;
    AddUserFragment addUserFragment;
    LoaiSachFragment loaiSachFragment;
    MemberFragment memberFragment;
    PhieuFragment phieuFragment;
    SachFragment sachFragment;
    DoanhThuFragment doanhThuFragment;
    DoiMkFragment doiMkFragment;
    TopFragment topFragment;
    FragmentManager fm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawer_main = findViewById(R.id.drawer_main);
        toolbar_main = findViewById(R.id.toobar_main);
        nav_main = findViewById(R.id.nav_main);

        setSupportActionBar(toolbar_main);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer_main,toolbar_main,R.string.open,R.string.close);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

        drawer_main.addDrawerListener(toggle);

        fm = getSupportFragmentManager();
        addUserFragment = new AddUserFragment();
        loaiSachFragment = new LoaiSachFragment();
        phieuFragment = new PhieuFragment();
        memberFragment = new MemberFragment();
        sachFragment = new SachFragment();
        doanhThuFragment = new DoanhThuFragment();
        doiMkFragment = new DoiMkFragment();
        topFragment = new TopFragment();

        fm.beginTransaction().add(R.id.frag_container,phieuFragment).commit();

        nav_main.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_phieu){
                    fm.beginTransaction().replace(R.id.frag_container,phieuFragment).commit();
                }
                else if (id == R.id.nav_loai_sach){
                    fm.beginTransaction().replace(R.id.frag_container,loaiSachFragment).commit();
                }
                else if (id == R.id.nav_sach){
                    fm.beginTransaction().replace(R.id.frag_container,sachFragment).commit();
                }
                else if (id == R.id.nav_thanh_vien){
                    fm.beginTransaction().replace(R.id.frag_container,memberFragment).commit();
                }
                else if (id == R.id.nav_top_10){
                    fm.beginTransaction().replace(R.id.frag_container,topFragment).commit();
                }
                else if (id == R.id.nav_doanh_thu){
                    fm.beginTransaction().replace(R.id.frag_container,doanhThuFragment).commit();
                }
                else if (id == R.id.nav_add_user){
                    fm.beginTransaction().replace(R.id.frag_container,addUserFragment).commit();
                }

                else if (id == R.id.nav_doi_mat_khau){
                    fm.beginTransaction().replace(R.id.frag_container,doiMkFragment).commit();
                }
                else if (id == R.id.nav_log_out){
                    startActivity(new Intent(MainActivity.this,Login_activity.class));
                }
                else {

                }
                drawer_main.close();
                return true;
            }
        });

    }
}