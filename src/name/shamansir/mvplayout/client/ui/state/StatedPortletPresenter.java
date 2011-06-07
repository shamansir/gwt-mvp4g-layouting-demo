/**
 * 
 */
package name.shamansir.mvplayout.client.ui.state;

import name.shamansir.mvplayout.client.ui.PortletPresenter;
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
public abstract class StatedPortletPresenter<V extends PluggableWithStates & LazyView, 
                                   E extends ChildEventBus> 
                                   extends PortletPresenter<V, E>
								   implements ProjectsStates {
    
    protected StatedPortletPresenter() {

    }  
    
    @Override
    public void projectEmpty(HasWidgets where) {
        //project(where, view.getEmptyView());
    }

    @Override    
    public void projectLoading(HasWidgets where) {
    	//project(where, view.getLoadingView());
    }
    
    @Override    
    public void projectNoMatches(HasWidgets where) {
    	//project(where, view.getNoMatchesView());
    }    

}
