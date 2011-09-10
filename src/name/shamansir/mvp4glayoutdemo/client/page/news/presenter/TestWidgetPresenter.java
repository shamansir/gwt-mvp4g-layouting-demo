/**
 * 
 */
package name.shamansir.mvp4glayoutdemo.client.page.news.presenter;

import com.mvp4g.client.annotation.Presenter;

import name.shamansir.mvp4glayout.client.mvp.PortletPresenter;
import name.shamansir.mvp4glayout.client.ui.widget.Plug;
import name.shamansir.mvp4glayout.client.ui.widget.Portlet;

import name.shamansir.mvp4glayoutdemo.client.page.news.NewsEventBus;
import name.shamansir.mvp4glayoutdemo.client.widget.TestWidget;

/**
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 *
 */
@Presenter(view = TestWidgetPresenter.TestWidgetView.class)
public class TestWidgetPresenter extends PortletPresenter<TestWidgetPresenter.TestWidgetView, NewsEventBus> {
    
    // public visibility is important
    public static class TestWidgetView extends Portlet {

        @Override
        public void createView() {
            initWidget(Plug.of("test-widget", new TestWidget()));
        }
        
    }

}
