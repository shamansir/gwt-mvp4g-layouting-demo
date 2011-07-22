package name.shamansir.mvplayout.client.page.main.presenter;

import name.shamansir.mvplayout.client.id.O;
import name.shamansir.mvplayout.client.id.P;
import name.shamansir.mvplayout.client.page.main.MainEventBus;
import name.shamansir.mvplayout.client.page.main.view.MainView;
import name.shamansir.mvplayout.lib.mvp.AMainPresenter;
import name.shamansir.mvplayout.lib.mvp.IsMainView;
import name.shamansir.mvplayout.lib.ui.LayoutBuilder.CanLayoutMainView;
import name.shamansir.mvplayout.lib.ui.Pluggable;
import name.shamansir.mvplayout.lib.ui.Portal;
import name.shamansir.mvplayout.lib.ui.Portal.UrlBuilder;
import name.shamansir.mvplayout.lib.ui.state.State;
import name.shamansir.mvplayout.lib.ui.widget.Layout;

import com.google.gwt.user.client.History;
import com.mvp4g.client.annotation.Presenter;

@Presenter(view = MainView.class)
public class MainPresenter extends AMainPresenter<MainPresenter.IMainView, MainEventBus> {
    
    public interface IMainView extends IsMainView {
        public Pluggable get404View();
        public Pluggable getLinksView(UrlBuilder url);
    }
    
    private CanLayoutMainView page404;
    private CanLayoutMainView pageWithLinks;
    
    public void createPresenter() {	    
        this.page404 = new CanLayoutMainView(P.VIEW_404, eventBus) {
            @Override
            protected boolean doLayout(Portal view, Layout layout, State state) {
                layout.plug(O.A, MainPresenter.this.view.get404View());
                return true;
            }
        };
        
        this.pageWithLinks = new CanLayoutMainView(P.LINKS, eventBus) {
            @Override
            protected boolean doLayout(Portal view, Layout layout, State state) {
                layout.plug(O.A, MainPresenter.this.view.getLinksView(url));
                return true;
            }
        };
        
    }
    
    public void onStart() {        
        if (History.getToken().isEmpty()) {
            //History.newItem(url.build(P.USERS_LIST), false); // if you want just open a child page at start
            //OR: eventBus.users(null); // FIXME: do not calls history converter when not in dev-mode
            pageWithLinks.run();
        }
        
    }
        
    public void onShow404() {
        page404.run();
    }
    
}
