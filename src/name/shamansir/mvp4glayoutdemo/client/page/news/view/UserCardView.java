/**
 * 
 */
package name.shamansir.mvp4glayoutdemo.client.page.news.view;

import name.shamansir.mvp4glayout.client.ui.widget.Portlet;
import name.shamansir.mvp4glayoutdemo.client.page.news.presenter.UserCardPresenter.Display;
import name.shamansir.mvp4glayoutdemo.client.page.news.widget.UserCardWidget;
import name.shamansir.mvp4glayoutdemo.shared.dao.User;

/**
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 *
 */
public class UserCardView extends Portlet implements Display {

    private final UserCardWidget widget = new UserCardWidget();    
    
    @Override
    public void createView() {
        initWidget(widget);
    }

    @Override
    public void loadUser(User user) {
        widget.loadUser(user);
    }
    
    @Override
    public void clear() {
        widget.clear();
    }    

}
