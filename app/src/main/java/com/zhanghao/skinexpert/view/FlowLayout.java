package com.zhanghao.skinexpert.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 流式布局容器（自定义容器）
 * 1、继承ViewGroup
 * 2、必须重写onLayout方法，确认每个child如何正确放置位置
 * 3、先对容器进行测量（onMeasure）
 *
 * 如果要每个child获取外边距得加上外边的的代码,这里不讲
 * 1.onMeasure里加上外边的换行
 * 2.onLayout算上外边距再放
 * 只要添加指定的child就可以直接测出效果了
 */
public class FlowLayout extends ViewGroup {

    private static final String TAG="FlowLayout";

    public FlowLayout(Context context) {
        super(context);
        init();
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 对当前ViewGroup容器的高度和宽度进行测量
     * 1、match_parent或者定值可以直接测量
     * 2、wrap_content的情况根据实际情况重新测量
     * 在构造方法之后调用，在onLayout之前调用
     *
     * 传递的入参是组合的值
     * @param widthMeasureSpec 高低位拼接的值，宽度下mode+实际宽度大小
     * @param heightMeasureSpec 高度下mode+实际高度大小
     *
     * onMeasure会反复调用，进行多次测量
     * 难点：自适应的重新处理的逻辑 (这里要考虑换行的情况)
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //先将数据源集合清空
        mListAll.clear();//防止onMeasure反复调用，进来全部清空只留最后一次情况

        int widthMode=MeasureSpec.getMode(widthMeasureSpec);//提取宽度模式
        int widthSize=MeasureSpec.getSize(widthMeasureSpec);//提取宽度大小
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);//提取高度模式
        int heightSize=MeasureSpec.getSize(heightMeasureSpec);//提取高度大小

        //Mode分为三种
        //MeasureSpec.UNSPECIFIED//系统最外层dectorView使用的FrameLayout使用的
        //MeasureSpec.AT_MOST//给wrap_content使用的
        //MeasureSpec.EXACTLY//给match_parent或固定写死使用的模式，

        /*switch(widthMode){
            case MeasureSpec.UNSPECIFIED:
                Log.i(TAG,"widthMode-->"+"UNSPECIFIED");
                break;
            case MeasureSpec.AT_MOST:
                Log.i(TAG,"widthMode-->"+"AT_MOST");
                break;
            case MeasureSpec.EXACTLY:
                Log.i(TAG,"widthMode-->"+"EXACTLY");
                break;
            default:
                break;
        }
        switch(heightMode){
            case MeasureSpec.UNSPECIFIED:
                Log.i(TAG,"heightMode-->"+"UNSPECIFIED");
                break;
            case MeasureSpec.AT_MOST:
                Log.i(TAG,"heightMode-->"+"AT_MOST");
                break;
            case MeasureSpec.EXACTLY:
                Log.i(TAG,"heightMode-->"+"EXACTLY");
                break;
            default:
                break;
        }*/

        /**
         * 总结一下：在AT_MOST模式下，我们获取的高度或者宽度并不一定是自己实际想要的，该值需要重新进行计算
         */

        //流布局中，如果宽度自适应，取宽度最大的一行作为自适应的最终宽度
        //流布局中，如果高度自适应，每一行的child的高度累加作为整个容器自适应的高度（该行中最大child的高度）
        //流布局中，如何自动换行，不能比系统测出的widthSize大，如果大，考虑换行

        //定义变量
        int wrapWidth=0,wrapHeight=0;//每加入一个child后自适应获取的宽度高度
        int lineWidth=0,lineHeight=0;//记录每一行的总宽度高度

        List<View> childList=new ArrayList<View>();

        //获取该容器中所有存放child的个数
        int childCount = getChildCount();
        for (int i=0;i<childCount;i++){
            View child = getChildAt(i);
            //1、获取child以后，测试child的宽度高度
            //第一个参数：需要测量的child，后面2个参数由入参提供
            measureChild(child,widthMeasureSpec,heightMeasureSpec);

            int childWidth=child.getMeasuredWidth();//获取测量后的宽度
            int childHeight=child.getMeasuredHeight();//获取测量后的高度

            //判断换行的情况
            if (lineWidth+childWidth<=widthSize){//不需要换行（行的宽度加上child宽度没有超出系统给的宽度）
                //加入到集合
                childList.add(child);

                lineWidth += childWidth;
                lineHeight=Math.max(childHeight,lineHeight);//留下高度最高的child的高度
            }else {//换行的处理
                //将该行加到全局的数据源
                mListAll.add(childList);
                //重新构建新的childList
                childList=new ArrayList<View>();

                mRowMaxHeightList.add(lineHeight);//记录上一行最大高度

                wrapWidth=Math.max(lineWidth,wrapWidth);//和上述记录下来的每一行去比较
                wrapHeight+=lineHeight;

                //下一行的情况，记录了上一行的内容，计算出自适应的宽度和高度
                lineWidth=childWidth;
                lineHeight=childHeight;

                //将换行的第一个child加进集合
                childList.add(child);
            }

            //当添加到最后一个元素时，补上最后当前行的自适应宽度和高度
            if (i==childCount-1){//最后一个child的情况（记录的是当前行）
                //把当前这一行childList加入全局数据源
                mListAll.add(childList);

                mRowMaxHeightList.add(lineHeight);//记录当前行最大高度

                wrapWidth=Math.max(lineWidth,wrapWidth);
                wrapHeight+=lineHeight;
            }

        }

        //通过Mode再去确认使用wrapWidth和wrapHeight的情况
        //lastWidth、lastHeight（终极的宽度和高度）
        int lastWidth=widthMode==MeasureSpec.AT_MOST?wrapWidth:widthSize;
        int lastHeight=heightMode==MeasureSpec.AT_MOST?wrapHeight:heightSize;

        //通过系统提供的方法重新测量
        setMeasuredDimension(lastWidth,lastHeight);

    }

    //定义全局的数据源，要通过onMeasure方法整理这个数据源
    private List<List<View>> mListAll;
    private List<Integer> mRowMaxHeightList;//放置每行最大高度
    private void init(){
        mListAll=new ArrayList<List<View>>();
        mRowMaxHeightList=new ArrayList<Integer>();
    }

    /**
     * 放置位置的方法
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //设置对应的坐标位置（left、top、right、bottom）
        //放置每个child的逻辑处理
        int left=0,right=0,top=0,bottom=0;//每个child的坐标点都需要动态赋值

        for (int i=0;i<mListAll.size();i++){//指定行
            List<View> rowChild=mListAll.get(i);
            for (int j = 0; j < rowChild.size(); j++) {//指定行的每个child
                View child=rowChild.get(j);

                //调用child放置位置的方法
                right=left+child.getMeasuredWidth();
                bottom=top+child.getMeasuredHeight();
                child.layout(left,top,right,bottom);

                //立即修改left坐标，为下次left的使用做准备
                left=right;
            }
            //top的值累加上一行中最大高度，要做个集合，记录每一行的最大高度
            top+=mRowMaxHeightList.get(i);
            left=0;//left清零
        }

    }

}
