package name.shamansir.mvplayout.client.page.news.layout;

import name.shamansir.mvplayout.client.id.O;
import name.shamansir.mvplayout.client.id.P;
import name.shamansir.mvplayout.client.page.news.NewsEventBus;
import name.shamansir.mvplayout.lib.ui.LayoutBuilder;
import name.shamansir.mvplayout.lib.ui.Portal;
import name.shamansir.mvplayout.lib.ui.state.State;
import name.shamansir.mvplayout.lib.ui.widget.Layout;

public class NewsLayoutBuilder extends LayoutBuilder<NewsEventBus> {

	@Override
	protected boolean layout(Portal view, Layout layout, State state,
	                         NewsEventBus eventBus) {
        switch (P.by(view)) {
            case NEWS_LIST: {
                /*eventBus.plugUsersList(O.A);
                eventBus.plugUserAvatar(O.B);
                eventBus.plugUserDetails(O.C); */
            } return true;
            case NEWS_SHOW: {
                /* eventBus.plugUserInfo(O.A);
                eventBus.plugUserAvatar(O.B);
                eventBus.plugUserDetails(O.C); */              
            } return true;
            case NEWS_EDIT: {
                switch (state) {
                    case HAS_DATA: {
                        /*eventBus.plugUserInfoEditor(O.A);
                        eventBus.plugUserAgeEditor(O.B);
                        eventBus.plugUserAvatarEditor(O.C);
                        eventBus.plugTestWidget(O.D); */
                    } return true;
                    case LOADING_DATA: {
                        /* eventBus.plugLoadingUserInEditor(O.STATUS);
                        eventBus.plugTestWidget(O.D); */
                    } return true;
                    case NO_DATA: {
                        /* eventBus.plugNoUserInEditor(O.STATUS);
                        eventBus.plugTestWidget(O.D); */
                    } return true;      
                }
            }
        }
    
        return false;

	}

}
