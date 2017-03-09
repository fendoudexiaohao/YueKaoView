package songzhihao.bwei.com.yuekaoview;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * 作者： 宋智豪
 * * 时间： 2017/3/9 19:57
 * * 描述： 自定义圆环加点击跳转和提示
 */

public class MyView extends View{

    private TypedArray ty;
    private String text;
    private float textsize;
    private int ny;
    private int wy;

    public MyView(Context context) {
        this(context,null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs,R.style.AppTheme);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //读取定义的属性
        ty = context.obtainStyledAttributes(attrs, R.styleable.MyView);
        text = ty.getString(R.styleable.MyView_text);
        textsize = ty.getDimension(R.styleable.MyView_textsize, 1);
        ny = ty.getColor(R.styleable.MyView_ny, Color.BLUE);
        wy = ty.getColor(R.styleable.MyView_wy, Color.DKGRAY);
        //用完务必回收
        ty.recycle();
    }
    //画布的方法

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画内圆
        Paint opaint = new Paint();
        opaint.setAntiAlias(true);
        opaint.setStrokeWidth(1);
        opaint.setStyle(Paint.Style.FILL);
        opaint.setColor(ny);
        canvas.drawCircle(getWidth()/2,getHeight()/2,70,opaint);

        //第二个圆
        Paint tpaint = new Paint();
        tpaint.setColor(wy);
        tpaint.setStyle(Paint.Style.STROKE);
        tpaint.setStrokeWidth(60);
        tpaint.setAntiAlias(true);
        canvas.drawCircle(getWidth()/2,getHeight()/2,100,tpaint);

        Paint thpaint = new Paint();
        thpaint.setAntiAlias(true);
        thpaint.setStrokeWidth(1);
        thpaint.setStyle(Paint.Style.STROKE);
        thpaint.setColor(Color.BLACK);
        float yh = thpaint.measureText("圆环");
        canvas.drawText("圆环",(getWidth()-yh)/2,getHeight()/2,thpaint);
    }
    //触摸方法

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x;
        int y;
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //得到点击的xy的坐标
                x = (int) event.getX();
                y = (int) event.getY();
                int x1 = (x - getWidth() / 2) * (x - getWidth() / 2);
                int y1 = (y - getHeight() / 2) * (y - getHeight() / 2);
                if (x1+y1<70*70){
                    Toast.makeText(getContext(),"点击了小圆内",Toast.LENGTH_SHORT).show();
                }else if(x1+y1<130*130&&x1+y1>70*70){
                    Toast.makeText(getContext(),"点击了圆环内",Toast.LENGTH_SHORT).show();
                    //跳转
                    Intent intent = new Intent(getContext(),HomeMainActivity.class);
                    getContext().startActivity(intent);
                }else{
                    Toast.makeText(getContext(),"点击了圆环外",Toast.LENGTH_SHORT).show();
                }
        }
        return true;
    }
}
