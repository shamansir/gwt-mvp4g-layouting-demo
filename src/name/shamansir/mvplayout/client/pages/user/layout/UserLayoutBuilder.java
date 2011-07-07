package name.shamansir.mvplayout.client.pages.user.layout;

import name.shamansir.mvplayout.client.id.O;
import name.shamansir.mvplayout.client.id.P;
import name.shamansir.mvplayout.client.pages.user.UserEventBus;
import name.shamansir.mvplayout.lib.ui.LayoutBuilder;
import name.shamansir.mvplayout.lib.ui.Portal;
import name.shamansir.mvplayout.lib.ui.state.State;
import name.shamansir.mvplayout.lib.ui.widget.Layout;

public class UserLayoutBuilder extends LayoutBuilder<UserEventBus> {

	@Override
	protected boolean layout(Portal view, Layout layout, State state, UserEventBus eventBus) {
		
		switch (P.by(view)) {
			case USERS_LIST: {
                eventBus.plugUsersList(O.A);
				eventBus.plugUserAvatar(O.B);
				eventBus.plugUserDetails(O.C);
			} return true;
			case USER_SHOW: {
                eventBus.plugUserInfo(O.A);
                eventBus.plugUserAvatar(O.B);
                eventBus.plugUserDetails(O.C);			    
			}    
		}
		
		return false;
	}

}
