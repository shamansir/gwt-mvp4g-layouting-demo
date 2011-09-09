package name.shamansir.mvp4glayoutdemo.shared.dao;

import name.shamansir.mvp4glayoutdemo.shared.Card;

public class User extends Card {

    public User() { super(); };	
    
    public User(int uid) { super(uid); }
    
    public String name;
    public String familyName;
    public int age;
    public String avatar;

}
