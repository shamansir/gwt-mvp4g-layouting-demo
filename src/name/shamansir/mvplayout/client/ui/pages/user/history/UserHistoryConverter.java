package name.shamansir.mvplayout.client.ui.pages.user.history;

import name.shamansir.mvplayout.client.ui.Portal;
import name.shamansir.mvplayout.client.ui.Portal.Group;
import name.shamansir.mvplayout.client.ui.Portal.PortalUrl;
import name.shamansir.mvplayout.client.ui.pages.base.PortalsHistoryConverter;
import name.shamansir.mvplayout.client.ui.pages.user.UserEventBus;

public class UserHistoryConverter extends PortalsHistoryConverter<UserEventBus> {

	protected UserHistoryConverter() { super(Group.USER); }

	@Override
	protected void convertFromUrl(PortalUrl url, Portal view,
			UserEventBus eventBus) {
		
		switch (view) {
			case USERS_LIST: eventBus.users();
		}
		
	}
	
	

}
