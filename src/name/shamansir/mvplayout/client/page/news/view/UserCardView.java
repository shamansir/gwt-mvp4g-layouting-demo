/**
 * 
 */
package name.shamansir.mvplayout.client.page.news.view;

import name.shamansir.mvplayout.client.page.news.presenter.UserCardPresenter.Display;
import name.shamansir.mvplayout.client.page.news.widget.UserCardWidget;
import name.shamansir.mvplayout.lib.ui.widget.Portlet;
import name.shamansir.mvplayout.shared.dao.User;

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

    /* (non-Javadoc)
     * @see name.shamansir.mvplayout.client.page.news.presenter.UserCardPresenter.Display#loadUser(name.shamansir.mvplayout.shared.dao.User)
     */
    @Override
    public void loadUser(User user) {
        widget.loadUser(user);
    }

}
