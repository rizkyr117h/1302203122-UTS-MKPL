package lib;

import java.util.Date;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.time.ZoneId;

public class Employee extends Person {

	private enum Gender {
		LAKI_LAKI,
		PEREMPUAN
	}

	private Date dateJoined;
	private boolean isForeigner;

	private int monthlySalary;
	private int otherMonthlyIncome;
	private int annualDeductible;

	// Data Family
	private Person spouse;
	private List<String> childNames;
	private List<String> childIdNumbers;

	public Employee(String name, String idNumber, String ad, String address,
			Date dateJoined, boolean isForeigner, Gender gender) {
		setName(name);
		setId(idNumber);
		setAdress(address);
		setGender(null);
		spouse = new Person();
		spouse.setName(name);
		spouse.setId(idNumber);
		spouse.setAdress(address);
		this.dateJoined = dateJoined;
		this.isForeigner = isForeigner;
		childNames = new LinkedList<String>();
		childIdNumbers = new LinkedList<String>();

	}

	/**
	 * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade kepegawaiannya
	 * (grade 1: 3.000.000 per bulan, grade 2: 5.000.000 per bulan, grade 3:
	 * 7.000.000 per bulan)
	 * Jika pegawai adalah warga negara asing gaji bulanan diperbesar sebanyak 50%
	 */

	public void setMonthlySalary(int grade) {
		if (grade == 1) {
			monthlySalary = 3000000;
			if (isForeigner) {
				monthlySalary = (int) (3000000 * 1.5);
			}
		} else if (grade == 2) {
			monthlySalary = 5000000;
			if (isForeigner) {
				monthlySalary = (int) (3000000 * 1.5);
			}
		} else if (grade == 3) {
			monthlySalary = 7000000;
			if (isForeigner) {
				monthlySalary = (int) (3000000 * 1.5);
			}
		}
	}

	public void setAnnualDeductible(int deductible) {
		this.annualDeductible = deductible;
	}

	public void setAdditionalIncome(int income) {
		this.otherMonthlyIncome = income;
	}

	public void addChild(String childName, String childIdNumber) {
		childNames.add(childName);
		childIdNumbers.add(childIdNumber);
	}

	public int getAnnualIncomeTax() {
		// Menghitung berapa lama pegawai bekerja dalam setahun ini, jika pegawai sudah
		// bekerja dari tahun sebelumnya maka otomatis dianggap 12 bulan.
		LocalDate currentDate = LocalDate.now(); // tanggal sekarang
		LocalDate joinDate = dateJoined.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); //
		int monthWorkingInYear;

		if (currentDate.getYear() == joinDate.getYear()) {
			monthWorkingInYear = currentDate.getMonthValue() - joinDate.getMonthValue() + 1;
		} else {
			monthWorkingInYear = 12;
		}

		return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthWorkingInYear, annualDeductible,
				spouse.id() == null || spouse.id().equals(""), childIdNumbers.size());
	}
}
