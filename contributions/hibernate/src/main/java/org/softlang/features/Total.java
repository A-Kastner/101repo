package org.softlang.features;

import org.softlang.company.*;

/**
 * Total all salaries
 *
 */
public class Total {

	public static double total(Company company) {
		// total all salaries in all top departments
		return company.getDepts()
				.stream()
				.map(dept -> total(dept))
				.reduce(0.0, (t1,t2) -> t1+t2);
	}

	public static double total(Department dept) {
		// total all department's employees' salaries
		return dept.getEmployees()
				.stream()
				.map(employee -> total(employee))
				.reduce(0.0, (t1,t2) -> t1+t2)
				// total all salaries in all sub departments
				+ dept.getSubdepts()
				.stream()
				.map(subDept -> total(subDept))
				.reduce(0.0, (t1,t2) -> t1+t2);
	}

	public static double total(Employee employee) {
		return employee.getSalary();
	}
}