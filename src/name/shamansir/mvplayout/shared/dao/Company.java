package name.shamansir.mvplayout.shared.dao;

import java.util.Set;

import name.shamansir.mvplayout.shared.Card;

public class Company implements Card {
	
	public Company() { };	
	
	public Company(int cid) {

	}
	
	public String title;
	public Set<User> employee;

}
