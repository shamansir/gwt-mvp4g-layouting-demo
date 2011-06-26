/**
 * 
 */
package name.shamansir.mvplayout.client.ui.state;

import name.shamansir.mvplayout.client.ui.Pluggable;
import name.shamansir.mvplayout.client.ui.state.LayoutWithState.State;

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
    
    public boolean hasViewFor(State state);
    public Pluggable getViewFor(State state);    

}
