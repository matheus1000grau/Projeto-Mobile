package com.fmu.galacticalinvaders;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

public class NaveInimigaEspaco {
    Context context;
    Bitmap naveInimiga;
    int ex, ey;
    int inimigaVelocidade;
    Random random;

    public NaveInimigaEspaco(Context context) {
        this.context = context;
        naveInimiga = BitmapFactory.decodeResource(context.getResources(), R.drawable.rocket2);
        random = new Random();
        ex = 200 + random.nextInt(400);
        ey = 0;
        inimigaVelocidade = 14 + random.nextInt(10);
    }

    public Bitmap getNaveInimigaEspaco(){
        return naveInimiga;
    }

    int getNaveInimigaEspacoWidth(){
        return naveInimiga.getWidth();
    }

    int getNaveInimigaEspacoHeight(){
        return naveInimiga.getHeight();
    }
}