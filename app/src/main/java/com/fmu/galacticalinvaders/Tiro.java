package com.fmu.galacticalinvaders;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Tiro {

    Bitmap tiro;
    Context context;
    int shx, shy;

    public Tiro(Context context, int shx, int shy) {
        this.context = context;
        tiro = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.shot);
        this.shx = shx;
        this.shy = shy;
    }
    public Bitmap getTiro(){
        return tiro;
    }
    public int getTiroWidth() {
        return tiro.getWidth();
    }
    public int getTiroHeight() {
        return tiro.getHeight();
    }

}
