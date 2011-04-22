package name.shamansir.mvplayout.pages.base;

import name.shamansir.mvplayout.client.ui.Portal.Group;
import name.shamansir.mvplayout.client.ui.layout.base.LayoutBuilder;
import name.shamansir.mvplayout.client.ui.layout.base.LayoutBuilders;

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
			
	}

	@Override
	public boolean isCrawlable() {
		return false;
	}

}
