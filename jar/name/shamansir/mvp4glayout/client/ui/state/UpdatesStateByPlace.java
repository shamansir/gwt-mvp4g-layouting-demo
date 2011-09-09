/**
 * 
 */
package name.shamansir.mvp4glayout.client.ui.state;

import name.shamansir.mvp4glayout.client.ui.structure.Place;

/**
 * <dl>
 * <dt>Project:</dt> <dd>gwt-mvp4g-layouting-demo</dd>
 * <dt>Package:</dt> <dd>name.shamansir.mvp4glayout.client.ui</dd>
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
