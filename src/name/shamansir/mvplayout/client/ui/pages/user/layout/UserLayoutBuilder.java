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
				eventBus.plugUserAvatar(Place.B);
				eventBus.plugUserDetails(Place.C);
				if (State.HAS_DATA.equals(state)) {
					eventBus.plugUsersList(Place.A);
				} else if (State.LOADING_DATA.equals(state)) {
					eventBus.plugUsersLoading(Place.STATUS);
				} else if (State.NO_DATA.equals(state)) {
					eventBus.plugUsersEmpty(Place.STATUS);
				} else if (State.NO_MATCHES.equals(state)) {
					eventBus.plugUsersNoMatches(Place.STATUS);
				} else return false;
				return true;
			}
		}
		
		return false;
	}

}
