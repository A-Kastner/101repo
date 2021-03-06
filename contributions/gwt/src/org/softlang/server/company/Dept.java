package org.softlang.server.company;

import java.util.LinkedList;
import java.util.List;

/**
 * A department has a name, a manager and a list of subunits
 * 
 */
public class Dept {

	private String name;
	private Employee manager;
	private List<Subunit> subunits;

	public Dept() {
		name = "";
		subunits = new LinkedList<Subunit>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public List<Subunit> getSubunits() {
		return subunits;
	}

	public double total() {
		double total = 0;
		total += getManager().total();
		for (Subunit s : getSubunits())
			total += s.total();
		return total;
	}

	public void cut() {
		getManager().cut();
		for (Subunit s : getSubunits())
			s.cut();
	}
}
