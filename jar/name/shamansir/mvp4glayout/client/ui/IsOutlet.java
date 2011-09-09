/**
 * 
 */
package name.shamansir.mvp4glayout.client.ui;

import com.google.gwt.user.client.ui.HasWidgets;

/**
 * <dl>
 * <dt>Project:</dt> <dd>gwt-mvp4g-layouting-demo</dd>
 * <dt>Package:</dt> <dd>name.shamansir.mvp4glayout.client.ui</dd>
 * </dl>
 *
 * <code>IsOutlet</code>
 *
 * <p>Description</p>
 *
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 * @date Jun 7, 2011 7:41:59 PM 
 *
 */
public interface IsOutlet extends HasWidgets {
    
    public void setVisible(boolean visible);
    public boolean isVisible();

}
