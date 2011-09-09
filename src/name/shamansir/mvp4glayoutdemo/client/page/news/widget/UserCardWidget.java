/**
 * 
 */
package name.shamansir.mvp4glayoutdemo.client.page.news.widget;

import com.google.gwt.user.client.ui.Label;

import name.shamansir.mvp4glayout.client.ui.widget.Plug;
import name.shamansir.mvp4glayoutdemo.shared.dao.User;

/**
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 *
 */
public class UserCardWidget extends Plug {

    public UserCardWidget() {
        super("user-info");
    }

    public void loadUser(User user) {
        clear();
        add(new Label("Name: " + user.name));
        add(new Label("Family name: " + user.familyName));
        add(new Label("Age: " + user.age));
        add(new Label("Avatar: " + user.avatar));
    }

}
