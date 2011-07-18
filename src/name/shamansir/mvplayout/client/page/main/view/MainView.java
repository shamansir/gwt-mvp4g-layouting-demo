package name.shamansir.mvplayout.client.page.main.view;

import name.shamansir.mvplayout.client.id.P;
import name.shamansir.mvplayout.client.page.main.presenter.MainPresenter.IMainView;
import name.shamansir.mvplayout.lib.mvp.AMainView;
import name.shamansir.mvplayout.lib.ui.Pluggable;
import name.shamansir.mvplayout.lib.ui.Portal;
import name.shamansir.mvplayout.lib.ui.Portal.UrlBuilder;
import name.shamansir.mvplayout.lib.ui.widget.Plug;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasScrolling;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public final class MainView extends AMainView implements IMainView {

	@UiTemplate("MainView.ui.xml")
	interface EurekaPageUiBinder extends UiBinder<Widget, MainView> { }	
	private static EurekaPageUiBinder uiBinder = GWT.create(EurekaPageUiBinder.class);
	
	@UiField ScrollPanel scrollable;	
	@UiField FlowPanel layoutHolder;
	
	@Override
	public void makeView() {
		initWidget(uiBinder.createAndBindUi(this));				
	}
	
    @Override
    public void whenPortalChanged(Portal portal) { }
    
    @Override
    public void showError(Throwable caught) {
    	Window.alert("Error: " + caught.getMessage());
    }

    @Override
    protected HasScrolling getScrollable() { return scrollable; }

    @Override
    protected Panel getLayoutHolder() { return layoutHolder; }

    @Override
    protected Panel getPortalHolder() { return scrollable; }
    // parent will change only CSS classes

    @Override
    public Pluggable get404View() {
        final Plug plug = new Plug("404-view");
        plug.add(new Label("404"));
        return plug;
    }

    @Override
    public Pluggable getLinksView(UrlBuilder url) {
        final Plug plug = new Plug("links-view");
        final VerticalPanel vert = new VerticalPanel();
        vert.add(url.link(new Anchor("Users"), P.USERS_LIST));
        vert.add(url.link(new Anchor("News"), P.NEWS_LIST));
        vert.add(url.link(new Anchor("Companies"), P.COMPANY_LIST));
        plug.add(vert);
        return plug;
    }
    
}
