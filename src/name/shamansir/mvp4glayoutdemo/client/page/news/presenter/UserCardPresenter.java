/**
 * 
 */
package name.shamansir.mvp4glayoutdemo.client.page.news.presenter;

import com.mvp4g.client.annotation.Presenter;

import name.shamansir.mvp4glayout.client.mvp.IsPortletView;
import name.shamansir.mvp4glayout.client.mvp.PortletPresenter;

import name.shamansir.mvp4glayoutdemo.client.page.news.NewsEventBus;
import name.shamansir.mvp4glayoutdemo.client.page.news.view.UserCardView;
import name.shamansir.mvp4glayoutdemo.shared.dao.User;

/**
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 *
 */
@Presenter(view = UserCardView.class)
public class UserCardPresenter extends PortletPresenter<UserCardPresenter.Display, NewsEventBus> {

    public interface Display extends IsPortletView {
        public void loadUser(User user);
        public void clear();
    }
    
    public void onShowUserCard(User user) {
        view.loadUser(user);
    }
    
    public void onClearUserCard() {
        view.clear();
    }
    
}
