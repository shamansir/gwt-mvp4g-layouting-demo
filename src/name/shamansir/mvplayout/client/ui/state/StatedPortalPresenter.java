/**
 * 
 */
package name.shamansir.mvplayout.client.ui.state;

import name.shamansir.mvplayout.client.ui.LayoutBuilder;
import name.shamansir.mvplayout.client.ui.Portal;
import name.shamansir.mvplayout.client.ui.PortalPresenter;
import name.shamansir.mvplayout.client.ui.pages.base.ChildEventBus;

import com.google.gwt.user.client.ui.HasWidgets;
import com.mvp4g.client.view.LazyView;

/**
 * <dl>
 * <dt>Project:</dt> <dd>gwt-mvp4g-layouting-demo</dd>
 * <dt>Package:</dt> <dd>name.shamansir.mvplayout.client.ui</dd>
 * </dl>
 *
 * <code>StatedPortalPresenter</code>
 *
 * <p>Description</p>
 *
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 * @date Apr 28, 2011 10:16:17 PM 
 *
 */
public abstract class StatedPortalPresenter<V extends ViewWithStates & LazyView, 
                                   E extends ChildEventBus,
                                   L extends LayoutBuilder<E>> 
                                   extends PortalPresenter<V, E, L> {
    
    protected StateDirector state;    
    
    protected StatedPortalPresenter(Portal portal) {
        super(portal);
    }  
    
    @Override
    public void bindView() {
        state = new StateDirector(view, eventBus);
    }
    
    public void projectEmpty(HasWidgets where) {
        project(where, view.getEmptyView());
    }
    
    public void projectLoading(HasWidgets where) {
        
    }
    
    public void projectNoMatches(HasWidgets where) {
        
    }    

}
