package com.pjs.tablette;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class DrawView extends View {
    private Paint paint;
    private MainActivity contexte;
    
    public DrawView(MainActivity context) {
        super(context);
        this.paint = new Paint();
        this.contexte = context;
    }

    @Override
    public void onDraw(Canvas canvas) {
        paint.setColor(contexte.getCurrentColor());
        paint.setStrokeWidth(3);
        canvas.drawRect(0, 0, this.getWidth(), this.getHeight(), paint);
        
        /*
        paint.setColor(Color.YELLOW);
        paint.setStrokeWidth(0);
        canvas.drawRect(30+3, 30+3, 80-3, 80-3, paint );
        */

    }

}