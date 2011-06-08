package name.shamansir.mvplayout.client.ui.pages.user.layout;

import java.util.Map;

import name.shamansir.mvplayout.client.ui.IsOutlet;
import name.shamansir.mvplayout.client.ui.LayoutBuilder;
import name.shamansir.mvplayout.client.ui.Portal;
import name.shamansir.mvplayout.client.ui.Layouts.Place;
import name.shamansir.mvplayout.client.ui.pages.user.UserEventBus;
import name.shamansir.mvplayout.client.ui.state.LayoutWithState.State;
import name.shamansir.mvplayout.client.ui.widget.Layout;

public class UserLayoutBuilder extends LayoutBuilder<UserEventBus> {

	@Override
	protected boolean layout(Portal view, Layout layout, State state,
	        Map<Place, IsOutlet> outlets, UserEventBus eventBus) {
		
		switch (view) {
			case USERS_LIST: {
				eventBus.projectUserAvatar(outlets.get(Place.B));
				eventBus.projectUserDetails(outlets.get(Place.C));
				if (State.HAS_DATA.equals(state)) {
					eventBus.projectUsersList(outlets.get(Place.A));
				} else if (State.LOADING_DATA.equals(state)) {
					eventBus.projectUsersLoading(outlets.get(Place.STATUS));
				} else if (State.NO_DATA.equals(state)) {
					eventBus.projectUsersEmpty(outlets.get(Place.STATUS));
				} else if (State.NO_MATCHES.equals(state)) {
					eventBus.projectUsersNoMatches(outlets.get(Place.STATUS));
				} else return false;
				return true;
			}
		}
		
		return false;
	}

}
