package com.idata.filedemo.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by wangchang on 2019/4/29.
 */
public class StudyBendLine extends View {

    private float sumHeight;//总控件的高度
    private float sumWidth;//总空间的宽度
    private float maxTime;//最大的时间 用来划分单位的 最小就是20 X1.2是为了给上方和下方预留空间
    private Paint linePaint;//线的画笔
    private Paint shadowPaint;//阴影的画笔
    private Paint mPaint;//曲线画笔
    private Paint circlePaint;//圆点画笔
    private Paint circlePaint2;//圆点画笔
    private Paint scorePaint;
    private Paint textPaint;//文字的画笔
    private ArrayList<Integer> timeList;//读书时间
    private ArrayList<String> dataList;//底部的时间
    private float oneHeight; //每一个小段所要分成的高
    private float oneWidth;//每一个小段所要分成的宽
    private float buttomHeiht; //给底部一排日期预留出的时间
    private Path baseLinePath;//折线路径
    private float smoothness = 0.36f; //折线的弯曲率
    private Paint baseShadow;//折线下的阴影的画笔
    private ArrayList<PointF> xyList;//储存定好的坐标点的集合

    public StudyBendLine(Context context) {
        super(context);
        initPaint();
    }

    public StudyBendLine(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    /**
     * 初始化画笔
     *
     * @linpaint 线条画笔
     * @shadowPaint 阴影画笔
     */
    private void initPaint() {
        //画线的画笔
        linePaint = new Paint();
        linePaint.setColor(Color.parseColor("#17CAAA"));
        linePaint.setAntiAlias(true);
        linePaint.setTextSize(dp2px(getContext(), 12));
        linePaint.setStrokeWidth(dp2px(getContext(), 1));
        //画背景的画笔
        shadowPaint = new Paint();
        shadowPaint.setColor(Color.parseColor("#CBF2ED"));
        shadowPaint.setAntiAlias(true);
        //画最下方文字的画笔
        textPaint = new Paint();
        textPaint.setColor(Color.parseColor("#999999"));
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(dp2px(getContext(), 11));

        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setColor(Color.parseColor("#8BE4D4"));
        circlePaint.setStrokeWidth(dp2px(getContext(), 2));
        circlePaint.setStyle(Paint.Style.STROKE);

        circlePaint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint2.setColor(Color.WHITE);
        circlePaint2.setStyle(Paint.Style.FILL);

        baseShadow = new Paint();
        baseShadow.setAntiAlias(true);
        baseShadow.setColor((Color.WHITE & 0x40FFFFFF) | 0x10000000);
        baseShadow.setStyle(Paint.Style.FILL);

        buttomHeiht = dp2px(11);//线距离底部高度

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.parseColor("#17CAAA"));
        mPaint.setStrokeWidth(dp2px(getContext(), 2));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);


        scorePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        scorePaint.setStyle(Paint.Style.STROKE);
        scorePaint.setStrokeCap(Paint.Cap.ROUND);
        scorePaint.setColor(Color.parseColor("#DDDDDD"));
        scorePaint.setStrokeWidth(dp2px(0.5f));
        baseLinePath = new Path();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        sumHeight = getMeasuredHeight();
        sumWidth = getMeasuredWidth();

    }

    private void measure() {
        maxTime = 100;//最大分数为100
        for (int i = 0; i < 7; i++) {
            if (maxTime <= timeList.get(i)) {
                maxTime = timeList.get(i);
            }
        }
        if (maxTime < 20) {
            maxTime = 20;
        }
        String text = "分";
        Rect rect = new Rect();
        textPaint.getTextBounds(text, 0, text.length(), rect);
        oneHeight = ((sumHeight - buttomHeiht - 2 * rect.height()) / maxTime);
        oneWidth = sumWidth / 28;
    }

    /**
     * 更新阅读时间
     */

    public void updateTime(ArrayList<Integer> timeList, ArrayList<String> bottomList) {
        this.timeList = timeList;
        this.dataList = bottomList;
        if (this.timeList != null && this.timeList.size() > 0 && dataList != null && dataList.size() > 0) {
            invalidate();
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (timeList == null || dataList == null) return;
        maxTime = getMaxTime(timeList);
        measure();
        toGetXy();//获取x和y的坐标

        toDrawLine(canvas);
    }

    private void toGetXy() {
        int leftWidth = dp2px(13);//距离右边宽度
        xyList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            float x = oneWidth + i * 4 * oneWidth;
            float time = timeList.get(i);//每点时间
            float y = (sumHeight - (oneHeight * time));
            xyList.add(new PointF(x + leftWidth, y - buttomHeiht));
        }
    }

    /**
     * 画线
     */
    private void toDrawLine(Canvas canvas) {
        if (xyList == null || xyList.size() == 0) {
            return;
        }
        List<PointF> NewPoints = new ArrayList<>();
        NewPoints.addAll(xyList);
        float lX = 0;
        float lY = 0;
        baseLinePath.reset();
        baseLinePath.moveTo(NewPoints.get(0).x, NewPoints.get(0).y);
        for (int i = 1; i < NewPoints.size(); i++) {
            PointF p = NewPoints.get(i);
            PointF firstPointF = NewPoints.get(i - 1);
            float x1 = firstPointF.x + lX;
            float y1 = firstPointF.y + lY;

            PointF secondPointF = NewPoints.get(i + 1 < NewPoints.size() ? i + 1 : i);
            lX = (secondPointF.x - firstPointF.x) / 2 * smoothness;
            lY = (secondPointF.y - firstPointF.y) / 2 * smoothness;
            float x2 = p.x - lX;
            float y2 = p.y - lY;
            if (y1 == p.y) {
                y2 = y1;
            }
            baseLinePath.cubicTo(x1, y1, x2, y2, p.x, p.y);
        }
        canvas.drawPath(baseLinePath, mPaint);
        for (int i = 0; i < NewPoints.size(); i++) {
            float x = NewPoints.get(i).x;
            float y = NewPoints.get(i).y;
            int time = timeList.get(i);
            String mThumbText = String.valueOf(time);
            Rect rect = new Rect();
            linePaint.getTextBounds(mThumbText, 0, mThumbText.length(), rect);
            //canvas.drawText(mThumbText, x - rect.width() / 2, y - dp2px(6), linePaint);//画最上面字体
            canvas.drawCircle(x, y, dp2px(3), circlePaint);
            canvas.drawCircle(x, y, dp2px(2), circlePaint2);
            Rect rectf = new Rect();
            textPaint.getTextBounds(dataList.get(i), 0, dataList.get(i).length(), rectf);
//            canvas.drawText(dataList.get(i), x - rectf.width() / 2, sumHeight - dp2px(2), textPaint);//画最下方的字体
        }
        drawArea(canvas, NewPoints);
        drawBottomLine(canvas);
        drawLine(canvas);

    }

    /**
     * 分割线
     *
     * @param canvas
     */
    private void drawLine(Canvas canvas) {
        //
        String text = "0";
        Rect rect = new Rect();
        scorePaint.getTextBounds(text, 0, text.length(), rect);
        //0
        canvas.drawText(text, 0, sumHeight - buttomHeiht - rect.height() / 2, textPaint);
        //60
        text = "50%";
        float y = (sumHeight - (oneHeight * 50));
        canvas.drawText(text, 0, y - buttomHeiht - rect.height() / 2 - dp2px(4), textPaint);
        canvas.drawLine(0, y - buttomHeiht, sumWidth, y - buttomHeiht, scorePaint);
        //100
        text = "100%";
        float y2 = (sumHeight - (oneHeight * 100));
        canvas.drawText(text, 0, y2 - buttomHeiht - rect.height() / 2 - dp2px(4), textPaint);
        canvas.drawLine(0, y2 - buttomHeiht, sumWidth, y2 - buttomHeiht, scorePaint);
    }

    /**
     * 底部标线
     *
     * @param canvas
     */
    private void drawBottomLine(Canvas canvas) {
        Rect rect = new Rect();
        scorePaint.getTextBounds("0", 0, "0".length(), rect);
        canvas.drawLine(0, sumHeight - dp2px(15) - rect.height() / 2, sumWidth, sumHeight - dp2px(15) - rect.height() / 2, scorePaint);//画底部灰线
    }

    /**
     * 阴影层叠
     *
     * @param canvas
     * @param Points
     */

    private void drawArea(Canvas canvas, List<PointF> Points) {
        LinearGradient mShader = new LinearGradient(0, 0, 0, getMeasuredHeight(), new int[]{Color.parseColor("#BAEFE6"), Color.parseColor("#D7F5F0"), Color.parseColor("#F9FEFD")}, new float[]{0.5f, 0.65f, 0.85f}, Shader.TileMode.REPEAT);
        baseShadow.setShader(mShader);
        if (Points.size() > 0 && xyList != null && xyList.size() > 0) {
            baseLinePath.lineTo(xyList.get(Points.size() - 1).x, sumHeight - buttomHeiht);
            baseLinePath.lineTo(xyList.get(0).x, sumHeight - buttomHeiht);
            baseLinePath.close();
            canvas.drawPath(baseLinePath, baseShadow);
        }

    }

    public int dp2px(float dp) {
        final float scale = this.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    /**
     * 取出时间里面的最大的一个用来计算总长度
     *
     * @param timeList
     * @return
     */
    public float getMaxTime(List<Integer> timeList) {
        maxTime = 0;
        for (int i = 0; i < 7; i++) {
            if (maxTime < timeList.get(i)) {
                maxTime = timeList.get(i);
            }
        }
        if (maxTime <= 10) {
            maxTime = 10;
        }
        maxTime *= 1.2;
        return maxTime;
    }

    public int dp2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public float dp2px(Resources resources, float dp) {
        final float scale = resources.getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }

    public float sp2px(Resources resources, float sp) {
        final float scale = resources.getDisplayMetrics().scaledDensity;
        return sp * scale;
    }
}

