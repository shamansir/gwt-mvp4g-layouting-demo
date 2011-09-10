package name.shamansir.mvp4glayoutdemo.client.page.user.layout;

import name.shamansir.mvp4glayout.client.ui.LayoutBuilder;
import name.shamansir.mvp4glayout.client.ui.Portal;
import name.shamansir.mvp4glayout.client.ui.state.State;
import name.shamansir.mvp4glayout.client.ui.widget.Layout;

import name.shamansir.mvp4glayoutdemo.client.id.O;
import name.shamansir.mvp4glayoutdemo.client.id.P;
import name.shamansir.mvp4glayoutdemo.client.page.user.UserEventBus;

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
    	    } return true;
    	    case USER_EDIT: {
    	        switch (state) {
                    case HAS_DATA: {
                        eventBus.plugUserInfoEditor(O.A);
                        eventBus.plugUserAgeEditor(O.B);
                        eventBus.plugUserAvatarEditor(O.C);
                        eventBus.plugTestWidget(O.D);
                    } return true;
                    case LOADING_DATA: {
                        eventBus.plugLoadingUserInEditor(O.STATUS);
                        eventBus.plugTestWidget(O.D);
                    } return true;
                    case NO_DATA: {
                        eventBus.plugNoUserInEditor(O.STATUS);
                        eventBus.plugTestWidget(O.D);
                    } return true;        
                }
            }
        }

        return false;
    }

}
