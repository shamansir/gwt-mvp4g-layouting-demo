/**
 * 
 */
package name.shamansir.mvplayout.lib.ui.state;

import name.shamansir.mvplayout.lib.ui.Pluggable;

/**
 * <dl>
 * <dt>Project:</dt> <dd>gwt-mvp4g-layouting-demo</dd>
 * <dt>Package:</dt> <dd>name.shamansir.mvplayout.lib.ui.state</dd>
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
