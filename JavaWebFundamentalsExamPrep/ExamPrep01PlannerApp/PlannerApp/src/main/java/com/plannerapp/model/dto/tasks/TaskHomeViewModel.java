package com.plannerapp.model.dto.tasks;

import java.util.ArrayList;
import java.util.List;

public class TaskHomeViewModel {

    private List<TaskDTO> myTasks;

    private List<TaskDTO> assignedTasks;

    private int availableTasksCount;

    public TaskHomeViewModel(){
        this(new ArrayList<>(),new ArrayList<>());
    }

    public TaskHomeViewModel(List<TaskDTO> myTasks, List<TaskDTO> assignedTasks) {
       this.myTasks = myTasks;
       this.assignedTasks = assignedTasks;
       this.availableTasksCount = setAvailableTasksCount();

    }

    public List<TaskDTO> getMyTasks() {
        return myTasks;
    }



    public List<TaskDTO> getAssignedTasks() {
        return assignedTasks;
    }

    public int setAvailableTasksCount(){
        return this.assignedTasks.size();

    }

    public int getAvailableTasksCount() {
        return availableTasksCount;
    }




}
