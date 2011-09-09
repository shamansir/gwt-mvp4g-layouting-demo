/**
 * 
 */
package name.shamansir.mvp4glayout.client.ui.state;


/**
 * <dl>
 * <dt>Project:</dt> <dd>gwt-mvp4g-layouting-demo</dd>
 * <dt>Package:</dt> <dd>name.shamansir.mvp4glayout.client.ui</dd>
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
public interface HandlesStateChange {
    
    public void prepareFor(State to);    

}
