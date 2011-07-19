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
                eventBus.plugNewsList(O.A);
                eventBus.plugUserCard(O.B);
                eventBus.plugTestWidget(O.C);
            } return true;
            case NEWS_SHOW: {
                eventBus.plugNewsInfo(O.A);
                eventBus.plugUserCard(O.B);
                eventBus.plugTestWidget(O.C);              
            } return true;
            case NEWS_EDIT: {
                eventBus.plugNewsItemEditor(O.A);
                eventBus.plugSaveButton(O.B);
                eventBus.plugUserCard(O.C);
            }
        }
    
        return false;

	}

}
