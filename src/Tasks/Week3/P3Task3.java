package Tasks.Week3;

import Tasks.TaskInstance;
import UI.MainWindow;
import UI.components.Form;
import UI.components.FormItem;
import UI.components.TextInput;

import javax.swing.*;

class TaxRateInstance {
    public char category;
    public double chargeableWithFixedAmount;
    public double maxChargeable;
    public double ratePercentage;
    public double cumulatedChargeable;

    public TaxRateInstance(char category, double chargeableWithFixedAmount, double maxChargeable, double ratePercentage, double cumulatedChargeable) {
        this.category = category;
        this.chargeableWithFixedAmount = chargeableWithFixedAmount;
        this.maxChargeable = maxChargeable;
        this.ratePercentage = ratePercentage;
        this.cumulatedChargeable = cumulatedChargeable;
    }
}

class TaxPayable {
    char category;
    double amount;
}

public class P3Task3 extends TaskInstance {
    private final TaxRateInstance[] TAX_RATES = new TaxRateInstance[]{
            new TaxRateInstance('A', 0, 5000, 0, 0),
            new TaxRateInstance('B', 5000, 20000, 1, 0),
            new TaxRateInstance('C', 20000, 35000, 3, 150),
            new TaxRateInstance('D', 35000, 50000, 6, 600),
            new TaxRateInstance('E', 50000, 70000, 11, 1500),
            new TaxRateInstance('F', 70000, 100000, 19, 3700),
            new TaxRateInstance('G', 100000, 400000, 25, 9400),
            new TaxRateInstance('H', 400000, 600000, 26, 84400),
            new TaxRateInstance('I', 600000, 2000000, 28, 136400),
            new TaxRateInstance('J', 2000000, Integer.MAX_VALUE, 30, 528400),
    };

    private TextInput yearlyIncomeInput;

    public P3Task3(MainWindow window) {
        super(window);

        this.name = "Task 3 - Tax Calculation";
        this.description = "Write a program that prompts user to enter their yearly income, and output the tax payable amount based on the tax rate table shown in the question statement.";
    }

    private TaxPayable calculateTaxPayable(double yearlyIncome) {
        TaxPayable taxPayable = new TaxPayable();

        for (TaxRateInstance taxRateInstance : TAX_RATES) {
            if (yearlyIncome <= taxRateInstance.maxChargeable) {
                taxPayable.category = taxRateInstance.category;
                taxPayable.amount = taxRateInstance.cumulatedChargeable +
                        (yearlyIncome - taxRateInstance.chargeableWithFixedAmount) * taxRateInstance.ratePercentage / 100;
                break;
            }
        }

        return taxPayable;
    }

    private void onSubmit() {
        try {
            double yearlyIncome = Double.parseDouble(yearlyIncomeInput.getText());
            TaxPayable taxPayable = calculateTaxPayable(yearlyIncome);

            JOptionPane.showMessageDialog(null, "You belongs to Category " + taxPayable.category + ", and your income tax payable is RM" + String.format("%.2f", taxPayable.amount));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number.", "Number Parsing Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    protected void prepareForm() {
        yearlyIncomeInput = new TextInput();

        FormItem[] formItems = new FormItem[]{
                new FormItem("Yearly income: ", yearlyIncomeInput),
        };

        Form inputForm = new Form(formItems, e -> onSubmit());
        mainWindow.insertForm(inputForm);
    }
}
