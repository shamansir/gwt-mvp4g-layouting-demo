/**
 * 
 */
package name.shamansir.mvplayout.client.page.news.view;

import name.shamansir.mvplayout.client.page.news.presenter.NewsInfoPresenter.Display;
import name.shamansir.mvplayout.lib.ui.widget.Plug;
import name.shamansir.mvplayout.lib.ui.widget.Portlet;
import name.shamansir.mvplayout.shared.dao.NewsItem;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

/**
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 *
 */
public class NewsInfoView extends Portlet implements Display {

    private final FlowPanel panel = new FlowPanel();    
    
    @Override
    public void createView() {
        initWidget(Plug.of("news-info", panel));
    }


    @Override
    public void showItem(NewsItem item) {
        panel.clear();
        panel.add(new Label("Title: " + item.title));
        panel.add(new Label("Text: " + item.text));
        panel.add(new Label("Posted at: " + item.postTime));
    }

}
