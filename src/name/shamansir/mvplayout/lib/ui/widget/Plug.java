/**
 * 
 */
package name.shamansir.mvplayout.lib.ui.widget;

import name.shamansir.mvplayout.lib.ui.CanBePlaced;
import name.shamansir.mvplayout.lib.ui.Pluggable;
import name.shamansir.mvplayout.lib.ui.PlugsContainer;
import name.shamansir.mvplayout.lib.ui.RefreshHandler;
import name.shamansir.mvplayout.lib.ui.state.State;
import name.shamansir.mvplayout.lib.ui.structure.Place;
import name.shamansir.mvplayout.lib.utils.StringUtils;

import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * <dl>
 * <dt>Project:</dt> <dd>gwt-mvp4g-layouting-demo</dd>
 * <dt>Package:</dt> <dd>name.shamansir.mvplayout.lib.ui.widget</dd>
 * </dl>
 *
 * <code>Plug</code>
 *
 * <p>Description</p>
 * 
 * TODO: CustomPlug<FlowPanel>, replace all Plug restrictions with Pluggable
 *
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 * @date Jun 16, 2011 8:57:10 PM 
 *
 */
public class Plug extends FlowPanel implements Pluggable {
    
    private String alias;
    private Place place;
    private RefreshHandler refreshHandler;
    private PlugsContainer parent;
    private State state; // may be null

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
    public Place getPlace() {
        return place;
    }

    @Override
    public void setPlace(Place place) {
        this.place = place;
        if ((parent != null)
            && (parent instanceof CanBePlaced)) {
            ((CanBePlaced)parent).setPlace(place);
        }
    }

    @Override
    public RefreshHandler getRefreshHandler() {
        return refreshHandler;
    }

    @Override
    public void setRefreshHandler(RefreshHandler refreshHandler) {
        this.refreshHandler = refreshHandler;
    }

    @Override
    public PlugsContainer getContainer() {
    	return parent;
    }
    
    public void setContainer(PlugsContainer parent) {
    	this.parent = parent;
    }	

    public final void changeState(State state) {
        if (this.state != null) removeStyleName(generateStateCSSClassName(this.state));
        this.state = state;
        addStyleName(generateStateCSSClassName(this.state));
    }   
    
    protected static String generateStateCSSClassName(State state) {
        return "state-" + StringUtils.toCSS(state.name());
    }
    
    @Override
    public State getState() { // may be null
        return this.state;
    }
    
    public static Plug of(String alias, Widget widget) {
        final Plug plug = new Plug(alias);
        plug.add(widget);
        return plug;
    }
    
}
