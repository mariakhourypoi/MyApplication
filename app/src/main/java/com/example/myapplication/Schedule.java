package com.example.myapplication;


import java.io.Serializable;

public class Schedule implements Serializable {
        private String day;
        private Time timeScheduleStart;
        private Time TimeScheduleEnd;

        public Schedule(String day , Time tms, Time tme) {
            this.day = day;
            this.timeScheduleStart=tms;
            this.TimeScheduleEnd=tme;
        }

    public Schedule() {
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Time getTimeScheduleStart() {
        return timeScheduleStart;
    }

    public void setTimeScheduleStart(Time timeScheduleStart) {
        this.timeScheduleStart = timeScheduleStart;
    }

    public Time getTimeScheduleEnd() {
        return TimeScheduleEnd;
    }

    public void setTimeScheduleEnd(Time timeScheduleEnd) {
        TimeScheduleEnd = timeScheduleEnd;
    }
}
