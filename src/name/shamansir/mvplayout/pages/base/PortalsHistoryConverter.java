package name.shamansir.mvplayout.pages.base;

import com.mvp4g.client.history.HistoryConverter;

public class PortalsHistoryConverter<E extends ChildEventBus> implements HistoryConverter<E> {

	@Override
	public final void convertFromToken(String historyName, String param, E eventBus) {
			
	}

	@Override
	public boolean isCrawlable() {
		return false;
	}

}
