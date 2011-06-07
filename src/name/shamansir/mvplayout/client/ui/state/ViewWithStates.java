/**
 * 
 */
package name.shamansir.mvplayout.client.ui.state;

import name.shamansir.mvplayout.client.ui.state.LayoutWithState.State;

import com.google.gwt.user.client.ui.Widget;

/**
 * <dl>
 * <dt>Project:</dt> <dd>gwt-mvp4g-layouting-demo</dd>
 * <dt>Package:</dt> <dd>name.shamansir.mvplayout.client.ui</dd>
 * </dl>
 *
 * <code>PluggableWithStates</code>
 *
 * <p>Description</p>
 *
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 * @date Apr 28, 2011 10:20:20 PM 
 *
 */
public interface ViewWithStates {
    
    public void prepareFor(State to);    
    
    public Widget getEmptyView();
    public Widget getLoadingView();
    public Widget getNoMatchesView();    

}
