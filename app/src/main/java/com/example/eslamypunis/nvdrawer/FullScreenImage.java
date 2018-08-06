package com.example.eslamypunis.nvdrawer;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.internal.NavigationMenu;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import io.github.yavski.fabspeeddial.FabSpeedDial;

public class FullScreenImage extends AppCompatActivity {
    ImageView fullscreenImage;
    FabSpeedDial fabSpeedDial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image);
        fullscreenImage=(ImageView)findViewById(R.id.fullscreenImage);
        fabSpeedDial=(FabSpeedDial)findViewById(R.id.fabdownload);
        fabSpeedDial.setMenuListener(new FabSpeedDial.MenuListener() {
            @Override
            public boolean onPrepareMenu(NavigationMenu navigationMenu) {
                return true;
            }

            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                if(menuItem.getItemId()==R.id.download)
                {
                    fullscreenImage.buildDrawingCache();
                    Bitmap bitmap=fullscreenImage.getDrawingCache();

                    MediaStore.Images.Media.insertImage(FullScreenImage.this.getContentResolver(), bitmap,"", "");

                    Toast.makeText(FullScreenImage.this, "Downloaded", Toast.LENGTH_SHORT).show();
                }
                return true;
            }

            @Override
            public void onMenuClosed() {

            }
        });
        Bundle getImage=getIntent().getExtras();
        String imageuri=getImage.getString("ImageUri");
        if(getImage!=null)
        {
            Picasso.with(this).load(imageuri).into(fullscreenImage);
        }



    }

}
