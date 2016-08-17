package rules;

import org.jpl7.*;
import org.jpl7.Integer;

public class TaskSkills {
    private String taskName;
    private String[] skills;

    public TaskSkills(String taskName, String[] skills) {
        this.taskName = taskName;
        this.skills = skills;
    }

    public String getTaskName() {
        return taskName;
    }

    public String[] getSkills() {
        return skills;
    }

    public void assertFact() {
        new Query("assert", new Term[] {new Compound("task_skills",
                new Term[] {new Atom(taskName), org.jpl7.Util.stringArrayToList(skills)})}).hasSolution();
    }
}
