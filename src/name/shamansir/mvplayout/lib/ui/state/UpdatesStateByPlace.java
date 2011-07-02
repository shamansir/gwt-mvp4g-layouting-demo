/**
 * 
 */
package name.shamansir.mvplayout.lib.ui.state;

import name.shamansir.mvplayout.lib.ui.structure.Place;

/**
 * <dl>
 * <dt>Project:</dt> <dd>gwt-mvp4g-layouting-demo</dd>
 * <dt>Package:</dt> <dd>name.shamansir.mvplayout.lib.ui</dd>
 * </dl>
 *
 * <code>UpdatesStateByPlace</code>
 *
 * <p>Description</p>
 *
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 * @date Apr 28, 2011 10:32:24 PM 
 *
 */
public interface UpdatesStateByPlace {
    public void changeState(Place where, State to);    
}
