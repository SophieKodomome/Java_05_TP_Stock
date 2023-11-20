package myUtil;

import java.text.NumberFormat;
import javax.swing.text.NumberFormatter;

public class NumberFormateur {
    private NumberFormatter formatter;

    public NumberFormateur() {
        NumberFormat format = NumberFormat.getInstance();
        formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        // formatter.setCommitsOnValidEdit(true);
    }

    public NumberFormatter getFormatter() {
        return formatter;
    }
}
