/**
 * 
 */
package name.shamansir.mvplayout.client.ui;

import name.shamansir.mvplayout.client.ui.Layouts.Place;

/**
 * <dl>
 * <dt>Project:</dt> <dd>gwt-mvp4g-layouting-demo</dd>
 * <dt>Package:</dt> <dd>name.shamansir.mvplayout.client.ui</dd>
 * </dl>
 *
 * <code>CanBePlaced</code>
 *
 * <p>Description</p>
 *
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 * @date Jun 9, 2011 7:14:44 PM 
 *
 */
public interface CanBePlaced {
    
    public Place getPlace();
    public void setPlace(Place place);    

}
