package lib;

public class TaxFunction {

	/**
	 * Calculates the amount of income tax that an employee has to pay annually.
	 *
	 * Tax is calculated as 5% of the annual net income (monthly salary and other
	 * monthly income multiplied by the number of months worked minus the
	 * deductible) minus the tax-free income.
	 *
	 * If the employee is unmarried and has no children, then the tax-free income is
	 * Rp 54,000,000.
	 * If the employee is married, the tax-free income is increased by Rp 4,500,000.
	 * If the employee has children, the tax-free income is increased by Rp
	 * 4,500,000 per child up to the third child.
	 *
	 */

	public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthsWorked, int deductible,
			boolean isMarried, int numberOfChildren) {

		if (numberOfMonthsWorked > 12) {
			throw new IllegalArgumentException("Number of months worked cannot exceed 12.");
		}

		numberOfChildren = Math.min(numberOfChildren, 3);

		int taxFreeIncome = 54000000;
		if (isMarried) {
			taxFreeIncome += 4500000;
		}
		taxFreeIncome += numberOfChildren * 4500000;

		int taxableIncome = (monthlySalary + otherMonthlyIncome) * numberOfMonthsWorked - deductible - taxFreeIncome;
		int tax = (int) Math.round(0.05 * taxableIncome);

		return Math.max(tax, 0);
	}
}