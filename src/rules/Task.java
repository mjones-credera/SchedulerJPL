package rules;

import org.jpl7.*;
import org.jpl7.Integer;

public class Task {
    private String taskName;
    private Shift shift;

    public Task(String taskName, Shift shift) {
        this.taskName = taskName;
        this.shift = shift;
    }

    public String getTaskName() {
        return taskName;
    }

    public Shift getShift() {
        return shift;
    }

    public Compound getCompound() {
        return new Compound("task", new Term[] {new Atom(taskName), shift.getCompound()});
    }

    public void assertFact() {
        new Query("assert", new Term[] {getCompound()}).hasSolution();
    }
}
