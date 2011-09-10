package name.shamansir.mvp4glayoutdemo.client.page.user.history;

import com.mvp4g.client.annotation.History;

import name.shamansir.mvp4glayout.client.mvp.PortalsHistoryConverter;
import name.shamansir.mvp4glayout.client.ui.Portal;
import name.shamansir.mvp4glayout.client.ui.Portal.PortalUrl;

import name.shamansir.mvp4glayoutdemo.client.id.G;
import name.shamansir.mvp4glayoutdemo.client.id.P;
import name.shamansir.mvp4glayoutdemo.client.page.user.UserEventBus;

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
