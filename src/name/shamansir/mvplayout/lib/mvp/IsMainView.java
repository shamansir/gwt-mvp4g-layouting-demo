/**
 * 
 */
package name.shamansir.mvplayout.lib.mvp;

import name.shamansir.mvplayout.lib.mvp.AMainView.PageResizeListener;
import name.shamansir.mvplayout.lib.mvp.AMainView.PageScrollListener;
import name.shamansir.mvplayout.lib.ui.Portal;
import name.shamansir.mvplayout.lib.ui.widget.Layout;

import com.google.gwt.event.shared.HandlerRegistration;
import com.mvp4g.client.view.LazyView;

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
