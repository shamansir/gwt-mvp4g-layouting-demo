/**
 * 
 */
package name.shamansir.mvplayout.client.ui;

import com.google.gwt.user.client.ui.IsWidget;

/**
 * <dl>
 * <dt>Project:</dt> <dd>gwt-mvp4g-layouting-demo</dd>
 * <dt>Package:</dt> <dd>name.shamansir.mvplayout.client.ui</dd>
 * </dl>
 *
 * <code>Pluggable</code>
 *
 * <p>Description</p>
 *
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 * @date Jun 7, 2011 7:42:44 PM 
 *
 */
public interface Pluggable extends IsWidget {
    
    public String id();
    public void refresh();

}
