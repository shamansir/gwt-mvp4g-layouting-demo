package name.shamansir.mvplayout.client.page.user.history;

import name.shamansir.mvplayout.client.id.G;
import name.shamansir.mvplayout.client.id.P;
import name.shamansir.mvplayout.client.page.user.UserEventBus;
import name.shamansir.mvplayout.lib.mvp.PortalsHistoryConverter;
import name.shamansir.mvplayout.lib.ui.Portal;
import name.shamansir.mvplayout.lib.ui.Portal.PortalUrl;

import com.mvp4g.client.annotation.History;

@History
public class UserHistoryConverter extends PortalsHistoryConverter<UserEventBus> {

	protected UserHistoryConverter() { super(G.USER); }

	@Override
	protected void convertFromUrl(PortalUrl url, Portal view,
			UserEventBus eventBus) {
		
		switch (P.by(view)) {
			case USERS_LIST: eventBus.users(url.param(0)); break;
			case USER_SHOW: eventBus.show(
			        (url.param(0) != null) && !url.param(0).isEmpty()
                    ? Integer.parseInt(url.param(0)) : -1); break;
			case USER_EDIT: eventBus.edit(
			        (url.param(0) != null) && !url.param(0).isEmpty()
			        ? Integer.parseInt(url.param(0)) : -1); break;
		}
		
	}
	
	public String onUsers(String filter) {
	    return url.parameters(filter);
	}
	
	public String onShow(int uid) {
        return (uid != -1) 
                ? url.parameters(String.valueOf(uid)) : "";
	}
	
    public String onEdit(int uid) {        
        return (uid != -1) 
                ? url.parameters(String.valueOf(uid)) : "";
    }	

}
