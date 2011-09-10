/**
 * 
 */
package name.shamansir.mvp4glayout.client.mvp;

import com.google.gwt.event.shared.HandlerRegistration;
import com.mvp4g.client.view.LazyView;

import name.shamansir.mvp4glayout.client.mvp.AMainView.PageResizeListener;
import name.shamansir.mvp4glayout.client.mvp.AMainView.PageScrollListener;
import name.shamansir.mvp4glayout.client.ui.Portal;
import name.shamansir.mvp4glayout.client.ui.widget.Layout;

/**
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 *
 */
public interface IsMainView extends LazyView {

    public Portal getCurPortal();

    public void clear();
    public void switchLayout(Layout to);
    public void beforePortalChange(Portal portal);        
    public void whenPortalChanged(Portal portal);
    
    public void showError(Throwable caught);

    public HandlerRegistration addPageResizeHandler(PageResizeListener handler);
    public HandlerRegistration addPageScrollHandler(PageScrollListener handler);

    public Layout getCurLayout();
    
    
}
