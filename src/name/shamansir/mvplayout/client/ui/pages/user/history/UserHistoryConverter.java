package name.shamansir.mvplayout.client.ui.pages.user.history;

import com.mvp4g.client.annotation.History;

import name.shamansir.mvplayout.client.ui.Portal;
import name.shamansir.mvplayout.client.ui.Portal.Group;
import name.shamansir.mvplayout.client.ui.Portal.PortalUrl;
import name.shamansir.mvplayout.client.ui.pages.base.PortalsHistoryConverter;
import name.shamansir.mvplayout.client.ui.pages.user.UserEventBus;

@History
public class UserHistoryConverter extends PortalsHistoryConverter<UserEventBus> {

	protected UserHistoryConverter() { super(Group.USER); }

	@Override
	protected void convertFromUrl(PortalUrl url, Portal view,
			UserEventBus eventBus) {
		
		switch (view) {
			case USERS_LIST: eventBus.users(url.param(0));
		}
		
	}
	
	public String onUsers(String filter) {
	    return url.parameters(filter);
	}	

}
