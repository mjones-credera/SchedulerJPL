import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jpl7.Atom;
import org.jpl7.Query;
import org.jpl7.Term;
import org.jpl7.Variable;
import rules.Employee;
import rules.Shift;
import rules.Task;
import rules.TaskSkills;

public class Main {

    public static final int NUM_SOLUTIONS = 1;

    public static void main(String[] args) {
        setupFacts();

        // Load the Prolog file
        new Query(
                "consult",
                new Term[] {new Atom("shift_scheduler_clp.pl")}).hasSolution();

        // Prepare the main query
        Query q1 = new Query(
                "schedule",
                new Term[] {new Variable("Schedule")});

        if (!q1.hasSolution()) {
            System.out.println("No solutions found");
        }

        for (int i = 0; (i < NUM_SOLUTIONS && q1.hasMoreSolutions()); i++) {
            System.out.println("Solution #" + (i+1) + ":");
            List<ScheduleEntry> entries = new ArrayList<ScheduleEntry>();
            Map<String,Term> resultMap = q1.nextSolution();
            Term[] results = resultMap.get("Schedule").toTermArray();
            for (Term term : results) {
                Term[] termArgs = term.args();

                // term = assign(employee(EmployeeName),task(TaskName,shift(ShiftDay,ShiftPeriod)))
                String employee = termArgs[0].args()[0].toString();
                String taskName = termArgs[1].args()[0].toString();
                String shiftDay = termArgs[1].args()[1].args()[0].toString();
                String shiftPeriod = termArgs[1].args()[1].args()[1].toString();

                entries.add(new ScheduleEntry(employee, taskName, shiftDay, shiftPeriod));
            }
            // Output results
            for (ScheduleEntry entry : entries) {
                System.out.println(entry.toString());
            }
            System.out.println();
        }
    }

    public static void setupFacts() {
        new Employee("micah", 10, new String[] { "programming", "writing", "speaking", "nunchucks" },
                new Shift[] {new Shift("friday",1), new Shift("friday",2),
                             new Shift("saturday",1), new Shift("saturday",2)},
                new Task[] {new Task("web_design", new Shift("monday",1)),
                            new Task("web_design", new Shift("tuesday",1)),
                            new Task("web_design", new Shift("tuesday",2))}).assertFacts();
        new Employee("jonathan", 12, new String[] { "programming", "babysitting", "writing" },
                new Shift[] {},
                new Task[] {new Task("web_design", new Shift("monday",2))}).assertFacts();
        new Employee("blake", 10, new String[] { "programming", "speaking" },
                new Shift[] {},
                new Task[] {new Task("server_programming", new Shift("monday",1)),
                            new Task("server_programming", new Shift("monday",2))}).assertFacts();

        new TaskSkills("documentation", new String[] {"programming","writing"}).assertFact();
        new TaskSkills("web_design", new String[] {"programming"}).assertFact();
        new TaskSkills("server_programming", new String[] {"programming"}).assertFact();
        new TaskSkills("presentation", new String[] {"speaking"}).assertFact();

        new Task("documentation", new Shift("saturday",1)).assertFact();
        new Task("documentation", new Shift("monday",2)).assertFact();
        new Task("web_design", new Shift("monday",1)).assertFact();
        new Task("web_design", new Shift("monday",2)).assertFact();
        new Task("web_design", new Shift("tuesday",1)).assertFact();
        new Task("web_design", new Shift("tuesday",2)).assertFact();
        new Task("web_design", new Shift("wednesday",1)).assertFact();
        new Task("web_design", new Shift("wednesday",2)).assertFact();
        new Task("web_design", new Shift("thursday",1)).assertFact();
        new Task("web_design", new Shift("thursday",2)).assertFact();
        new Task("web_design", new Shift("saturday",1)).assertFact();
        new Task("web_design", new Shift("saturday",2)).assertFact();
        new Task("server_programming", new Shift("monday",1)).assertFact();
        new Task("server_programming", new Shift("monday",2)).assertFact();
        new Task("server_programming", new Shift("tuesday",1)).assertFact();
        new Task("server_programming", new Shift("tuesday",2)).assertFact();
        new Task("server_programming", new Shift("wednesday",1)).assertFact();
        new Task("server_programming", new Shift("wednesday",2)).assertFact();
        new Task("server_programming", new Shift("thursday",1)).assertFact();
        new Task("server_programming", new Shift("thursday",2)).assertFact();
        new Task("server_programming", new Shift("friday",1)).assertFact();
        new Task("server_programming", new Shift("friday",2)).assertFact();
        new Task("presentation", new Shift("friday",1)).assertFact();
    }

}
