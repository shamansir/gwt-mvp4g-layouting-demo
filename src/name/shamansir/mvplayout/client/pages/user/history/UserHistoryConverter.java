package name.shamansir.mvplayout.client.pages.user.history;

import com.mvp4g.client.annotation.History;

import name.shamansir.mvplayout.client.id.G;
import name.shamansir.mvplayout.client.id.P;
import name.shamansir.mvplayout.client.pages.user.UserEventBus;
import name.shamansir.mvplayout.lib.mvp.PortalsHistoryConverter;
import name.shamansir.mvplayout.lib.ui.Portal;
import name.shamansir.mvplayout.lib.ui.Portal.PortalUrl;

@History
public class UserHistoryConverter extends PortalsHistoryConverter<UserEventBus> {

	protected UserHistoryConverter() { super(G.USER); }

	@Override
	protected void convertFromUrl(PortalUrl url, Portal view,
			UserEventBus eventBus) {
		
		switch (P.by(view)) {
			case USERS_LIST: eventBus.users(url.param(0));
		}
		
	}
	
	public String onUsers(String filter) {
	    return url.parameters(filter);
	}	

}
