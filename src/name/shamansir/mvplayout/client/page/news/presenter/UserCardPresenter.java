/**
 * 
 */
package name.shamansir.mvplayout.client.page.news.presenter;

import com.mvp4g.client.annotation.Presenter;

import name.shamansir.mvplayout.client.page.news.NewsEventBus;
import name.shamansir.mvplayout.client.page.news.view.UserCardView;
import name.shamansir.mvplayout.lib.mvp.IsPortletView;
import name.shamansir.mvplayout.lib.mvp.PortletPresenter;
import name.shamansir.mvplayout.shared.dao.User;

/**
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 *
 */
@Presenter(view = UserCardView.class)
public class UserCardPresenter extends PortletPresenter<UserCardPresenter.Display, NewsEventBus> {

    public interface Display extends IsPortletView {
        public void loadUser(User user);
    }
    
    public void onShowUserCard(User user) {
        view.loadUser(user);
    }
    
}
