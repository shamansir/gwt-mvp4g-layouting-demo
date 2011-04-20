package name.shamansir.mvplayout.shared.dao;

import java.util.Date;

import name.shamansir.mvplayout.shared.Card;

public class News implements Card {
	
	public News() { };	
	
	public News(int nid) {

	}	
	
	public String title;
	public String text;
	public User author;
	public Date postTime;

}
