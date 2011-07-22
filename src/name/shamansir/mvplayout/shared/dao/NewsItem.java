package name.shamansir.mvplayout.shared.dao;

import java.util.Date;

import name.shamansir.mvplayout.shared.Card;

public class NewsItem extends Card {
    
    public NewsItem() { super(); };	
    
    public NewsItem(int nid) { super(nid); }
    
    public String title;
    public String text;
    public User author;
    public Date postTime;

}
