package com.yuzhiyun.chemistry.model.entity.bmobEntity;

import cn.bmob.v3.BmobObject;

/**
 * Created by yuzhiyun on 2016-08-04.
 * 记录一次做题的开始时间、结束时间、时间跨度
 */
public class Record extends BmobObject {
    //    开始时间
    private long startTime;
    //    结束时间
    private long endTime;
    //    时间跨度
    private long timeSpan;
    //    用户
    private User user;
    public Record() {

    }
    public Record(long startTime, long endTime, long timeSpan) {
        setStartTime(startTime);
        setEndTime(endTime);
        setTimeSpan(timeSpan);
    }

    public Record(long startTime, long endTime, long timeSpan, User user) {
        this(startTime, endTime, timeSpan);
        setUser(user);
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public long getTimeSpan() {
        return timeSpan;
    }

    public void setTimeSpan(long timeSpan) {
        this.timeSpan = timeSpan;
    }
}
