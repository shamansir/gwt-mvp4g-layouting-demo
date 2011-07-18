/**
 * 
 */
package name.shamansir.mvplayout.client.page.news.presenter;

import com.mvp4g.client.annotation.Presenter;

import name.shamansir.mvplayout.client.page.news.NewsEventBus;
import name.shamansir.mvplayout.client.widget.TestWidget;
import name.shamansir.mvplayout.lib.mvp.PortletPresenter;
import name.shamansir.mvplayout.lib.ui.widget.Plug;
import name.shamansir.mvplayout.lib.ui.widget.Portlet;

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
            final Plug plug = new Plug("test-widget");
            plug.add(new TestWidget());
            initWidget(plug);
        }
        
    }

}
