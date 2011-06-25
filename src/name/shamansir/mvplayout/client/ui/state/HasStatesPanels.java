/**
 * 
 */
package name.shamansir.mvplayout.client.ui.state;

import name.shamansir.mvplayout.client.ui.Pluggable;

/**
 * <dl>
 * <dt>Project:</dt> <dd>gwt-mvp4g-layouting-demo</dd>
 * <dt>Package:</dt> <dd>name.shamansir.mvplayout.client.ui.state</dd>
 * </dl>
 *
 * <code>HasStatesPanels</code>
 *
 * <p>Description</p>
 *
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 * @date Jun 8, 2011 11:21:56 PM 
 *
 */
public interface HasStatesPanels extends HandlesStateChange {
    
    // TODO: change to getViewFor(State)
    public Pluggable getEmptyView();
    public Pluggable getLoadingView();
    public Pluggable getNoMatchesView();    

}
