package name.shamansir.mvplayout.shared.dao;

import name.shamansir.mvplayout.shared.Card;

public class User extends Card {

    public User() { super(); };	
    
    public User(int uid) { super(uid); }
    
    public String name;
    public String familyName;
    public int age;
    public String avatar;

}
