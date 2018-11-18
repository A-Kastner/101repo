package org.softlang.company;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="company")
public class Company {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="comp_id")
	private Set<Department> depts;

	
	public Set<Department> getDepts() {
		if (depts==null) 
			depts = new HashSet<Department>();
		return depts;
	}

	@SuppressWarnings("unused")
	private void setDepts(Set<Department> depts) {
		this.depts = depts;
}
}
