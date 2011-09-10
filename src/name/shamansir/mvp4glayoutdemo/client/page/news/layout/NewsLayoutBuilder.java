package name.shamansir.mvp4glayoutdemo.client.page.news.layout;

import name.shamansir.mvp4glayout.client.ui.LayoutBuilder;
import name.shamansir.mvp4glayout.client.ui.Portal;
import name.shamansir.mvp4glayout.client.ui.state.State;
import name.shamansir.mvp4glayout.client.ui.widget.Layout;

import name.shamansir.mvp4glayoutdemo.client.id.O;
import name.shamansir.mvp4glayoutdemo.client.id.P;
import name.shamansir.mvp4glayoutdemo.client.page.news.NewsEventBus;

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
            } return true;
        }
    
        return false;

    }

}
