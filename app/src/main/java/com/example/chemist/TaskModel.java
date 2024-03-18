package com.example.chemist;

import java.util.Date;

public class TaskModel {
    String Task_Name, Schedule_ID, Assignee;
    String Due_Date, Start_Date;

    public TaskModel() {
    }

    public TaskModel(String task_Name, String schedule_ID, String assignee, String due_Date, String start_Date) {
        Task_Name = task_Name;
        Schedule_ID = schedule_ID;
        Assignee = assignee;
        Due_Date = due_Date;
        Start_Date = start_Date;
    }

    public String getTask_Name() {
        return Task_Name;
    }

    public void setTask_Name(String task_Name) {
        Task_Name = task_Name;
    }

    public String getSchedule_ID() {
        return Schedule_ID;
    }

    public void setSchedule_ID(String schedule_ID) {
        Schedule_ID = schedule_ID;
    }

    public String getAssignee() {
        return Assignee;
    }

    public void setAssignee(String assignee) {
        Assignee = assignee;
    }

    public String getDue_Date() {
        return Due_Date;
    }

    public void setDue_Date(String due_Date) {
        Due_Date = due_Date;
    }

    public String getStart_Date() {
        return Start_Date;
    }

    public void setStart_Date(String start_Date) {
        Start_Date = start_Date;
    }
}
