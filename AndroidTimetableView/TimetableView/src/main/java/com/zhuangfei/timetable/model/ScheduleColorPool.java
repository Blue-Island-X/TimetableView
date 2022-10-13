package com.zhuangfei.timetable.model;

import android.content.Context;

import com.zhuangfei.timetable.utils.ColorUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 颜色池，管理课程项可挑选的颜色
 */

public class ScheduleColorPool {

    Context context;

    //课程不在本周时的背景色
    private ScheduleColorSet uselessColor;

    private Map<String, ScheduleColorSet> colorMap;

    //false：非本周课程使用uselessColor渲染
    //true：非本周课程使用colorMap渲染
    private boolean ignoreUselessColor = false;

    public ScheduleColorPool(Context context){
        this.context = context;
        colorMap = new HashMap<>();
    }

    /**
     * 获取颜色的映射Map
     * @return Map<String, Integer>
     */
    public Map<String, ScheduleColorSet> getColorMap() {
        if(colorMap == null) return new HashMap<>();
        return colorMap;
    }

    /**
     * 设置colorMap
     * @param colorMap Map<String, Integer>
     * @return ScheduleColorPool
     */
    public ScheduleColorPool setColorMap(Map<String, ScheduleColorSet> colorMap) {
        this.colorMap = colorMap;
        return this;
    }

    /**
     * 获取渲染时是否忽略非本周颜色
     * @return boolean
     */
    public boolean isIgnoreUselessColor() {
        return ignoreUselessColor;
    }

    /**
     * 设置渲染时是否忽略非本周颜色
     * false：非本周课程使用uselessColor渲染
     * true：非本周课程使用colorMap渲染
     * @return ScheduleColorPool
     */
    public ScheduleColorPool setIgnoreUselessColor(boolean ignoreUselessColor) {
        this.ignoreUselessColor = ignoreUselessColor;
        return this;
    }

    //使用集合维护颜色池
    private List<ScheduleColorSet> colorPool;

    /**
     * 获取非本周课程颜色
     * @return ScheduleColorSet
     */
    public ScheduleColorSet getUselessColor() {
        return uselessColor;
    }

    /**
     * 获取非本周课程颜色
     * @return int
     */
    public ScheduleColorSet getUselessColorWithAlpha(float alpha) {
        return ColorUtils.alphaColor(uselessColor, alpha);
    }

    /**
     * 设置非本周课程颜色
     * @param uselessColor 非本周课程的颜色
     * @return ScheduleColorPool
     */
    public ScheduleColorPool setUselessColor(ScheduleColorSet uselessColor) {
        this.uselessColor = uselessColor;
        return this;
    }

    /**
     * 得到颜色池的实例，即List集合
     * @return List<ScheduleColorSet>
     */
    public List<ScheduleColorSet> getPoolInstance() {
        if(colorPool == null) colorPool=new ArrayList<>();
        return colorPool;
    }

    /**
     * 从颜色池中取指定透明度的颜色
     * @param random
     * @param alpha
     * @return ScheduleColorSet
     */
    public ScheduleColorSet getColorAutoWithAlpha(int random, float alpha){
        if (random < 0) return getColorAuto(-random);
        return ColorUtils.alphaColor(getColor(random % size()), alpha);
    }

    /**
     * 根据索引获取颜色，索引越界默认返回 null
     * @param i 索引
     * @return ScheduleColorSet
     */
    public ScheduleColorSet getColor(int i) {
        if(i < 0 || i >= size()) return null;
        return colorPool.get(i);
    }

    /**
     * 使用模运算根据索引从颜色池中获取颜色,
     * 如果i<0，转换为正数,
     * 否则：重新计算索引j=i mod size
     * @param i 索引
     * @return ScheduleColorSet
     */
    public ScheduleColorSet getColorAuto(int i) {
        if(i < 0) return getColorAuto(-i);
        return getColor(i % size());
    }

    /**
     * 将指定集合中的颜色加入到颜色池中
     * @param ownColorPool 集合
     * @return ScheduleColorPool
     */
    public ScheduleColorPool addAll(Collection<? extends ScheduleColorSet> ownColorPool){
        getPoolInstance().addAll(ownColorPool);
        return this;
    }

    /**
     * 颜色池的大小
     * @return int
     */
    public int size(){
        if(getPoolInstance() == null) return 0;
        return getPoolInstance().size();
    }

    /**
     * 清空颜色池，清空默认颜色
     * @return ScheduleColorPool
     */
    public ScheduleColorPool clear(){
        getPoolInstance().clear();
        return this;
    }

    /**
     * 在颜色池中添加一些自定义的颜色
     * @param colorSets 多个颜色
     * @return ScheduleColorPool
     */
    public ScheduleColorPool add(ScheduleColorSet... colorSets) {
        if (colorSets != null) {
            colorPool.addAll(Arrays.asList(colorSets));
        }
        return this;
    }
}
