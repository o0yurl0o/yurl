package com.common.logger.helper;

/**
 * Created by Yurl on 2017/11/15.
 */
public class TimerStamp {

    private long timeStamp;
    private long duration = 0L;

    public TimerStamp() {
        timeStamp = System.currentTimeMillis();
    }

    public TimerStamp(long timerStamp) {
        this.timeStamp = timerStamp;
    }

    public long getTime() {
        return timeStamp;
    }

    /**
     * 记录当前时间
     */
    public void record() {
        timeStamp = System.currentTimeMillis();
    }

    /**
     * 计算与记录时间的时间差
     * @return 时间间隔
     */
    public long calDuration() {
        duration = System.currentTimeMillis() - timeStamp;
        return duration;
    }

    /**
     * 计算与记录时间的时间差并累加时间间隔
     * @return 时间间隔
     */
    public long calAndAddDuration() {
        duration += System.currentTimeMillis() - timeStamp;
        return duration;
    }

    /**
     * 获得计算的时间间隔
     */
    public long getDuration() {
        return duration;
    }

}
