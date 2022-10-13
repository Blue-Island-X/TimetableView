package com.zhuangfei.timetable.model;

public class ScheduleColorSet {

    // 背景颜色
    private int backgroundColor;

    // 边框颜色
    private int borderColor;

    // 文字颜色
    private int textColor;

    /**
     * 获取背景颜色
     * @return int
     */
    public int getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * 获取边框颜色
     * @return int
     */
    public int getBorderColor() {
        return borderColor;
    }

    /**
     * 获取文字颜色
     * @return int
     */
    public int getTextColor() {
        return textColor;
    }

    /**
     * 设置背景颜色
     * @param backgroundColor int
     */
    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     * 设置边框颜色
     * @param borderColor int
     */
    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
    }

    /**
     * 设置边框颜色
     * @param textColor int
     */
    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }
}
