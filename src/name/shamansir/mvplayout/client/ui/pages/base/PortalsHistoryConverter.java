package name.shamansir.mvplayout.client.ui.pages.base;

import name.shamansir.mvplayout.client.ui.LayoutBuilder;
import name.shamansir.mvplayout.client.ui.LayoutBuilders;
import name.shamansir.mvplayout.client.ui.Portal.Group;

import com.mvp4g.client.history.HistoryConverter;

public class PortalsHistoryConverter<E extends ChildEventBus> implements HistoryConverter<E> {

	protected final Group group;
	protected final LayoutBuilder<E> layoutBuilder;	
	
	protected PortalsHistoryConverter(Group group) {
		this.group = group;
		this.layoutBuilder = LayoutBuilders.get(group);		
	}
	
	@Override
	public final void convertFromToken(String historyName, String param, E eventBus) {
		
		/*
		// get current URL and portal
		final PortalUrl url = PortalUrl.fromEvent(group, event, param);
		final Portal view = url.getView();
		
		// prepare layout
		eventBus.newPortal(view, layoutBuilder.make(view, eventBus));
		
		// dispatch current URL
		if (!convertFromUrl(url, url.getView(), eventBus) && !PortalUrl.call(url, eventBus)) {
			eventBus.dispatch(event, param);
		} */
		
	}

	@Override
	public boolean isCrawlable() {
		return false;
	}

}
