package com.fmu.galacticalinvaders;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class TiroEspaco  extends View {

    Context context;
    Bitmap background, lifeImage;
    Handler handler;
    long UPDATE_MILLIS = 30;
    static int screenWidth, screenHeight;
    int points = 0;
    int life = 3;
    Paint scorePaint;
    int TEXT_SIZE = 80;
    boolean paused = false;
    NaveEspacial naveEspacial;
    NaveInimigaEspaco naveInimigaEspaco;
    Random random;
    ArrayList<Tiro> inimigoTiros, nossoTiros;
    Explosao explosao;
    ArrayList<Explosao> explosaos;
    boolean inimigoTiroAcao = false;
    final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            invalidate();
        }
    };


    public TiroEspaco(Context context) {
        super(context);
        this.context = context;
        Display display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
        random = new Random();
        inimigoTiros = new ArrayList<>();
        nossoTiros = new ArrayList<>();
        explosaos = new ArrayList<>();
        naveEspacial = new NaveEspacial(context);
        naveInimigaEspaco = new NaveInimigaEspaco(context);
        handler = new Handler();
        background = BitmapFactory.decodeResource(context.getResources(), R.drawable.backgroundspace);
        lifeImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.life);
        scorePaint = new Paint();
        scorePaint.setColor(Color.RED);
        scorePaint.setTextSize(TEXT_SIZE);
        scorePaint.setTextAlign(Paint.Align.LEFT);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(background, 0, 0, null);
        canvas.drawText("Pt: " + points, 0, TEXT_SIZE, scorePaint);
        for(int i=life; i>=1; i--){
            canvas.drawBitmap(lifeImage, screenWidth - lifeImage.getWidth() * i, 0, null);
        }
        if(life == 0){
            paused = true;
            handler = null;
            Intent intent = new Intent(context, GamerOver.class);
            intent.putExtra("points", points);
            context.startActivity(intent);
            ((Activity) context).finish();
        }
        naveInimigaEspaco.ex += naveInimigaEspaco.inimigaVelocidade;
        if(naveInimigaEspaco.ex + naveInimigaEspaco.getNaveInimigaEspacoWidth() >= screenWidth){
            naveInimigaEspaco.inimigaVelocidade *= -1;
        }
        if(naveInimigaEspaco.ex <=0){
            naveInimigaEspaco.inimigaVelocidade *= -1;
        }
        if(inimigoTiroAcao == false){
            if(naveInimigaEspaco.ex >= 200 + random.nextInt(400)){
                Tiro inimigoTiro = new Tiro(context, naveInimigaEspaco.ex + naveInimigaEspaco.getNaveInimigaEspacoWidth() / 2, naveInimigaEspaco.ey );
                inimigoTiros.add(inimigoTiro);
                inimigoTiroAcao = true;
            }
            if(naveInimigaEspaco.ex >= 400 + random.nextInt(800)){
                Tiro inimigoTiro = new Tiro(context, naveInimigaEspaco.ex + naveInimigaEspaco.getNaveInimigaEspacoWidth() / 2, naveInimigaEspaco.ey );
                inimigoTiros.add(inimigoTiro);
                inimigoTiroAcao = true;
            }
            else{
                Tiro inimigoTiro = new Tiro(context, naveInimigaEspaco.ex + naveInimigaEspaco.getNaveInimigaEspacoWidth() / 2, naveInimigaEspaco.ey );
                inimigoTiros.add(inimigoTiro);
                inimigoTiroAcao = true;
            }
        }
        canvas.drawBitmap(naveInimigaEspaco.getNaveInimigaEspaco(), naveInimigaEspaco.ex, naveInimigaEspaco.ey, null);
        if(naveEspacial.ox > screenWidth - naveEspacial.getOurSpaceshipWidth()){
            naveEspacial.ox = screenWidth - naveEspacial.getOurSpaceshipWidth();
        }else if(naveEspacial.ox < 0){
            naveEspacial.ox = 0;
        }
        canvas.drawBitmap(naveEspacial.getOurSpaceship(), naveEspacial.ox, naveEspacial.oy, null);

        for(int i=0; i < inimigoTiros.size(); i++){
            inimigoTiros.get(i).shy += 15;
            canvas.drawBitmap(inimigoTiros.get(i).getTiro(), inimigoTiros.get(i).shx, inimigoTiros.get(i).shy, null);
            if((inimigoTiros.get(i).shx >= naveEspacial.ox)
                    && inimigoTiros.get(i).shx <= naveEspacial.ox + naveEspacial.getOurSpaceshipWidth()
                    && inimigoTiros.get(i).shy >= naveEspacial.oy
                    && inimigoTiros.get(i).shy <= screenHeight){
                life--;
                inimigoTiros.remove(i);
                explosao = new Explosao(context, naveEspacial.ox, naveEspacial.oy);
                explosaos.add(explosao);
            }else if(inimigoTiros.get(i).shy >= screenHeight){
                inimigoTiros.remove(i);
            }
            if(inimigoTiros.size() < 1){
                inimigoTiroAcao = false;
            }
        }

        for(int i=0; i < nossoTiros.size(); i++){
            nossoTiros.get(i).shy -= 15;
            canvas.drawBitmap(nossoTiros.get(i).getTiro(), nossoTiros.get(i).shx, nossoTiros.get(i).shy, null);
            if((nossoTiros.get(i).shx >= naveInimigaEspaco.ex)
                    && nossoTiros.get(i).shx <= naveInimigaEspaco.ex + naveInimigaEspaco.getNaveInimigaEspacoWidth()
                    && nossoTiros.get(i).shy <= naveInimigaEspaco.getNaveInimigaEspacoWidth()
                    && nossoTiros.get(i).shy >= naveInimigaEspaco.ey){
                points++;
                nossoTiros.remove(i);
                explosao = new Explosao(context, naveInimigaEspaco.ex, naveInimigaEspaco.ey);
                explosaos.add(explosao);
            }else if(nossoTiros.get(i).shy <=0){
                nossoTiros.remove(i);
            }
        }
        for(int i=0; i < explosaos.size(); i++){
            canvas.drawBitmap(explosaos.get(i).getExplosion(explosaos.get(i).explosaoFrame), explosaos.get(i).eX, explosaos.get(i).eY, null);
            explosaos.get(i).explosaoFrame++;
            if(explosaos.get(i).explosaoFrame > 8){
                explosaos.remove(i);
            }
        }

        if(!paused)
            handler.postDelayed(runnable, UPDATE_MILLIS);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int touchX = (int)event.getX();

        if(event.getAction() == MotionEvent.ACTION_UP){
            if(nossoTiros.size() < 1){
                Tiro nossoTiro = new Tiro(context, naveEspacial.ox + naveEspacial.getOurSpaceshipWidth() / 2, naveEspacial.oy);
                nossoTiros.add(nossoTiro);
            }
        }
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            naveEspacial.ox = touchX;
        }

        if(event.getAction() == MotionEvent.ACTION_MOVE){
            naveEspacial.ox = touchX;
        }

        return true;
    }

}