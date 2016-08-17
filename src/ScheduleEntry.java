public class ScheduleEntry {

    private String employee;
    private String taskName;
    private String shiftDay;
    private String shiftPeriod;

    public ScheduleEntry(String employee, String taskName, String shiftDay, String shiftPeriod) {
        this.employee = employee;
        this.taskName = taskName;
        this.shiftDay = shiftDay;
        this.shiftPeriod = shiftPeriod;
    }

    public String getEmployee() {
        return employee;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getShiftDay() {
        return shiftDay;
    }

    public String getShiftPeriod() {
        return shiftPeriod;
    }

    @Override
    public String toString() {
        return "Name: " + employee + ", " +
               "Task: " + taskName + ", " +
               "Day: " + shiftDay + ", " +
               "Shift: " + shiftPeriod + ", ";
    }
}
