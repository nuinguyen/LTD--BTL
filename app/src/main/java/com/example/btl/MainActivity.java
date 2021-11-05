package com.example.btl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.huawei.hms.ads.AdParam;
import com.huawei.hms.ads.BannerAdSize;
import com.huawei.hms.ads.HwAds;
import com.huawei.hms.ads.banner.BannerView;

public class MainActivity extends AppCompatActivity {
    private Button btnsignin;
    private Button btnadduser;
    private Button btnalluser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HwAds.init(this);
        // Lấy BannerView.
        BannerView bannerView = findViewById(R.id.hw_banner_view);
//        BannerView bannerView = new BannerView(this);
        // Đặt ID đơn vị quảng cáo và kích thước quảng cáo. "testw6vs28auh3" là ID đơn vị quảng cáo thử nghiệm chuyên dụng.
        bannerView.setAdId("testw6vs28auh3");
        bannerView.setBannerAdSize(BannerAdSize.BANNER_SIZE_360_57);
        // Đặt khoảng thời gian làm mới thành 30 giây.
        bannerView.setBannerRefresh (3);
        // Tạo một yêu cầu quảng cáo để tải một quảng cáo.
        bannerView.loadAd (new AdParam.Builder().build());

        vinitVi();
        btnadduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
            }
        });
        btnalluser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, AllActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
            }
        });
    }

    public void vinitVi(){
        btnadduser=findViewById(R.id.btn_adduser);
        btnalluser=findViewById(R.id.btn_alluser);
    }
}