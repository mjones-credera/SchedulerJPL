package rules;

import org.jpl7.*;
import org.jpl7.Integer;

// Generates assertions for employee, employee_max_shifts, employee_skill, employee_unavailable, and employee_assigned facts
public class Employee {
    private String name;
    private int maxShifts;
    private String[] skills;
    private Shift[] unavailableShifts;
    private Task[] assignedTasks;

    public Employee(String name, int maxShifts, String[] skills, Shift[] unavailableShifts, Task[] assignedTasks) {
        this.name = name;
        this.maxShifts = maxShifts;
        this.skills = skills;
        this.unavailableShifts = unavailableShifts;
        this.assignedTasks = assignedTasks;
    }

    public String getName() {
        return name;
    }

    public int getMaxShifts() {
        return maxShifts;
    }

    public String[] getSkills() {
        return skills;
    }

    public Shift[] getUnavailableShifts() {
        return unavailableShifts;
    }

    public Task[] getAssignedTasks() {
        return assignedTasks;
    }

    public void assertFacts() {
        // employee
        new Query(
                "assert",
                new Term[] {new Compound("employee", new Term[] {new Atom(name)})}).hasSolution();
        // employee_max_shifts
        new Query(
                "assert",
                new Term[] {new Compound("employee_max_shifts", new Term[] {new Atom(name), new Integer(maxShifts)})}).hasSolution();
        // employee_skill
        for (String skill : skills) {
            new Query(
                    "assert",
                    new Term[] {new Compound("employee_skill", new Term[] {new Atom(name), new Atom(skill)})}).hasSolution();
        }
        // employee_unavailable
        for (Shift shift: unavailableShifts) {
            new Query(
                    "assert",
                    new Term[] {new Compound("employee_unavailable", new Term[] {new Atom(name), shift.getCompound()})}).hasSolution();
        }
        // employee_assigned
        for (Task task: assignedTasks) {
            new Query(
                    "assert",
                    new Term[] {new Compound("employee_assigned", new Term[] {task.getCompound()})}).hasSolution();
        }
    }
}
