/**
 * 
 */
package name.shamansir.mvplayout.client.ui.widget;

import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import name.shamansir.mvplayout.client.ui.Pluggable;
import name.shamansir.mvplayout.client.ui.RefreshHandler;
import name.shamansir.mvplayout.client.ui.Layouts.Place;

/**
 * <dl>
 * <dt>Project:</dt> <dd>gwt-mvp4g-layouting-demo</dd>
 * <dt>Package:</dt> <dd>name.shamansir.mvplayout.client.ui.widget</dd>
 * </dl>
 *
 * <code>Plug</code>
 *
 * <p>Description</p>
 *
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 * @date Jun 16, 2011 8:57:10 PM 
 *
 */
public class Plug extends Composite implements Pluggable {
    
    private String alias;
    private Place place;
    private RefreshHandler refreshHandler;

    @UiConstructor
    public Plug(final String alias) {
        this.alias = alias;
    }
    
    @Override
    public String id() {
        return alias;
    }

    @Override
    public void refresh() {
        if (refreshHandler != null) refreshHandler.doRefresh();
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    @Override
    public Place getPlace() {
        return place;
    }

    @Override
    public void setPlace(Place place) {
        this.place = place;
    }

    @Override
    public RefreshHandler getRefreshHandler() {
        return refreshHandler;
    }

    @Override
    public void setRefreshHandler(RefreshHandler refreshHandler) {
        this.refreshHandler = refreshHandler;
    }

}
