package Tasks.Week5;

import Tasks.TaskInstance;
import UI.MainWindow;
import UI.components.Form;
import UI.components.FormItem;

import javax.swing.*;

public class P5Task6 extends TaskInstance {
    public P5Task6(MainWindow window) {
        super(window);

        this.name = "Task 6 - Financial Application: Find the Sales Amount";
        this.description = "<p>\n" +
                "  You have just started a sales job in a department store. Your pay consists of a base salary and a commission.\n" +
                "  The base salary is <strong>$5,000</strong>. The scheme shown below is used to determine the commission rate.\n" +
                "</p>\n" +
                "<p></p>\n" +
                "<table border=\"1\" cellpadding=\"8\" cellspacing=\"0\">\n" +
                "  <thead>\n" +
                "    <tr>\n" +
                "      <th>Sales Amount</th>\n" +
                "      <th>Commission Rate</th>\n" +
                "    </tr>\n" +
                "  </thead>\n" +
                "  <tbody>\n" +
                "    <tr>\n" +
                "      <td>$0.01 - $5,000</td>\n" +
                "      <td>6 percent</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td>$5,000.01 - $10,000</td>\n" +
                "      <td>8 percent</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td>$10,000.01 and above</td>\n" +
                "      <td>10 percent</td>\n" +
                "    </tr>\n" +
                "  </tbody>\n" +
                "</table>\n" +
                "<p></p>\n" +
                "<p>\n" +
                "  Note that this is a graduated rate. The rate for the first $5,000 is at 6%, the next $5,000 is at 8%, and the rest is at 10%.\n" +
                "  If your sales amounts to $25,000, the commission is:\n" +
                "</p>\n" +
                "\n" +
                "<p>\n" +
                "  <code>$5,000 * 6% + $5,000 * 8% + $15,000 * 10% = $2,200</code>\n" +
                "</p>\n" +
                "\n" +
                "<p>\n" +
                "  Your goal is to earn $30,000 a year. Write a program to find the minimum sales you have to generate in order to make $30,000.\n" +
                "</p>\n";
    }

    private static double calculateCommission(double sales) {
        double commission;

        if (sales <= 5000) {
            commission = sales * 0.06;
        } else if (sales <= 10000) {
            commission = (5000 * 0.06) + ((sales - 5000) * 0.08);
        } else {
            commission = (5000 * 0.06) + (5000 * 0.08) + ((sales - 10000) * 0.10);
        }

        return commission;
    }

    private void displayMinimumSales() {
        double baseSalary = 5000.0;
        double targetIncome = 30000.0;
        double requiredCommission = targetIncome - baseSalary;

        double sales = 0.0;
        double commission = 0.0;

        while (commission < requiredCommission) {
            sales += 0.01;
            commission = calculateCommission(sales);
        }

        JOptionPane.showMessageDialog(null, "Minimum sales amount needed to earn $30,000: $" + String.format("%.2f", sales));
    }

    @Override
    protected void prepareForm() {
        JButton calculateButton = new JButton("Calculate Minimum Sales");
        calculateButton.addActionListener(e -> displayMinimumSales());

        FormItem[] formItems = new FormItem[]{
                new FormItem("Click to calculate:", calculateButton)
        };

        Form inputForm = new Form(formItems);
        this.mainWindow.insertForm(inputForm);
    }
}
