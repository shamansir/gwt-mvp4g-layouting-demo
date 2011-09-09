/**
 * 
 */
package name.shamansir.mvp4glayout.client.mvp.state;

import name.shamansir.mvp4glayout.client.mvp.IsPortletView;
import name.shamansir.mvp4glayout.client.ui.HasMainView;
import name.shamansir.mvp4glayout.client.ui.state.HandlesStateChange;
import name.shamansir.mvp4glayout.client.ui.state.HasStatesPanels;

/**
 * <dl>
 * <dt>Project:</dt> <dd>gwt-mvp4g-layouting-demo</dd>
 * <dt>Package:</dt> <dd>name.shamansir.mvp4glayout.client.ui.state</dd>
 * </dl>
 *
 * <code>IsStatedPortletView</code>
 *
 * <p>Description</p>
 *
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 * @date Jun 16, 2011 8:26:14 PM 
 *
 */
public interface IsStatedPortletView extends IsPortletView, HasMainView,
                                             HasStatesPanels, HandlesStateChange {

}
