### 一,MeasureSpec测量规则，测量模式分为三种：
  * EXACTLY -> 确认值，如：100dp，match_parent
  * AT_MOST -> 在范围之内的值，如：wrap_content
  * UNSPECIFIED -> 不确定的值，如：ListView，RecyclerView,ScrollView等
``` 
    private fun getMeasureSize(measureSpec: Int): Int {
        val mode = MeasureSpec.getMode(measureSpec)
        val size = MeasureSpec.getSize(measureSpec)
        return when (mode) {
            MeasureSpec.EXACTLY -> size
            MeasureSpec.AT_MOST -> size
            MeasureSpec.UNSPECIFIED -> 100 // 设置默认值
            else -> 100 // 设置默认值
        }
    }
    // 在onMeasure方法中调用setMeasuredDimension()实现宽高设置
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = getMeasureSize(widthMeasureSpec)
        val height = getMeasureSize(heightMeasureSpec)
        setMeasuredDimension(width, height)
    }
```

### 二,自定义布局属性
 * 在attr.xml文件中添加属性
``` 
    <declare-styleable name="SimpleSquareWidget">
        <!--设置默认的大小-->
        <attr name="defaultSize" format="dimension" />
    </declare-styleable>
``` 

 * 在布局xml中的使用,需要在头部添加域名：xmlns:app="http://schemas.android.com/apk/res-auto"
 ```
    <com.app.induction.widget.SimpleSquareWidget
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:defaultSize="100dp"/>
```

* 定义的属性在自定义控件中的获取
``` 
class SimpleSquareWidget(context: Context, attr: AttributeSet? = null, defStyleAttr: Int = 0) :
    View(context, attr, defStyleAttr) {

    private var mDefaultSize: Int = 10

    init {
        attr?.let {
            val typeArray = context.obtainStyledAttributes(attr, R.styleable.SimpleSquareWidget)
            mDefaultSize =
                typeArray.getDimensionPixelSize(R.styleable.SimpleSquareWidget_defaultSize, 10)
            typeArray.recycle()
        }
    }
}
``` 