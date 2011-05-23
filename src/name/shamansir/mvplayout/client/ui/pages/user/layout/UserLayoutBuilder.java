package name.shamansir.mvplayout.client.ui.pages.user.layout;

import java.util.Map;

import com.google.gwt.user.client.ui.HasWidgets;

import name.shamansir.mvplayout.client.ui.Layout;
import name.shamansir.mvplayout.client.ui.LayoutBuilder;
import name.shamansir.mvplayout.client.ui.Portal;
import name.shamansir.mvplayout.client.ui.Layouts.Place;
import name.shamansir.mvplayout.client.ui.pages.user.UserEventBus;
import name.shamansir.mvplayout.client.ui.state.LayoutWithState.State;

public class UserLayoutBuilder extends LayoutBuilder<UserEventBus> {

	@Override
	protected boolean layout(Portal view, Layout layout, State state,
			Map<Place, HasWidgets> panels, UserEventBus eventBus) {
		
		switch (view) {
			case USERS_LIST: {
				eventBus.projectUserAvatar(Place.B);
				eventBus.projectUserDetails(Place.C);
				if (State.HAS_DATA.equals(state)) {
					eventBus.projectUsersList(Place.A);
				} else if (State.LOADING_DATA.equals(state)) {
					eventBus.projectUsersLoading(Place.STATUS);
				} else if (State.NO_DATA.equals(state)) {
					eventBus.projectUsersEmpty(Place.STATUS);
				} else if (State.NO_MATCHES.equals(state)) {
					eventBus.projectUsersNoMatches(Place.STATUS);
				} else return false;
				return true;
			}
		}
		
		return false;
	}

}
