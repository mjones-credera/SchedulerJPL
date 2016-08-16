package rules;

import org.jpl7.Atom;
import org.jpl7.Compound;
import org.jpl7.Term;
import org.jpl7.Integer;

public class Shift {
    private String day;
    private int period;

    public Shift(String day, int period) {
        this.day = day;
        this.period = period;
    }

    public String getDay() {
        return day;
    }

    public int getPeriod() {
        return period;
    }

    public Compound getCompound() {
        return new Compound("shift", new Term[] {new Atom(day), new Integer(period)});
    }
}
