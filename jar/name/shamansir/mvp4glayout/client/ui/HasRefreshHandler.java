/**
 * 
 */
package name.shamansir.mvp4glayout.client.ui;


/**
 * <dl>
 * <dt>Project:</dt> <dd>gwt-mvp4g-layouting-demo</dd>
 * <dt>Package:</dt> <dd>name.shamansir.mvp4glayout.client.ui</dd>
 * </dl>
 *
 * <code>HasRefreshHandler</code>
 *
 * <p>Description</p>
 *
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 * @date Jun 16, 2011 9:07:21 PM 
 *
 */
public interface HasRefreshHandler {
    public RefreshHandler getRefreshHandler();
    public void setRefreshHandler(RefreshHandler refreshHandler);
}
