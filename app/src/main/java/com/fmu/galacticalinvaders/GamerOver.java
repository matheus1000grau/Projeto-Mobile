package com.fmu.galacticalinvaders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GamerOver extends AppCompatActivity {

    TextView tvPoints;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over);
        int points = getIntent().getExtras().getInt("points");
        tvPoints = findViewById(R.id.tvPoinsts);
        tvPoints.setText("" + points);
    }

    public void restart(View view){
        Intent intent = new Intent(GamerOver.this, TelaInicial.class);
        startActivity(intent);
        finish();
    }

    public void sair(View view){
        finish();
    }
}
