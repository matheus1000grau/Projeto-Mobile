package com.fmu.galacticalinvaders;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;


public class NaveEspacial {
    Context context;
    Bitmap naveEspacial;
    int ox, oy;
    Random random;

    public NaveEspacial(Context context) {
        this.context = context;
        naveEspacial = BitmapFactory.decodeResource(context.getResources(), R.drawable.rocket1);
        random = new Random();
        ox = random.nextInt(TiroEspaco.screenWidth);
        oy = TiroEspaco.screenHeight - naveEspacial.getHeight();
    }

    public Bitmap getOurSpaceship(){
        return naveEspacial;
    }

    int getOurSpaceshipWidth(){
        return naveEspacial.getWidth();
    }

}
