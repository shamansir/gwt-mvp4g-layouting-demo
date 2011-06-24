package name.shamansir.mvplayout.client.ui.pages.user.layout;

import name.shamansir.mvplayout.client.ui.LayoutBuilder;
import name.shamansir.mvplayout.client.ui.Portal;
import name.shamansir.mvplayout.client.ui.Layouts.Place;
import name.shamansir.mvplayout.client.ui.pages.user.UserEventBus;
import name.shamansir.mvplayout.client.ui.state.LayoutWithState.State;
import name.shamansir.mvplayout.client.ui.widget.Layout;

public class UserLayoutBuilder extends LayoutBuilder<UserEventBus> {

	@Override
	protected boolean layout(Portal view, Layout layout, State state, UserEventBus eventBus) {
		
		switch (view) {
			case USERS_LIST: {
                eventBus.plugUsersList(Place.A);
				eventBus.plugUserAvatar(Place.B);
				eventBus.plugUserDetails(Place.C);
			} return true;
		}
		
		return false;
	}

}
