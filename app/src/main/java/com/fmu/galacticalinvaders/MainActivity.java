package com.fmu.galacticalinvaders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import kotlin.reflect.KFunction;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new TiroEspaco(this));
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
    @Override
    protected void onPause(){
        super.onPause();
    }
}