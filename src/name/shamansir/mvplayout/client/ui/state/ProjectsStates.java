package name.shamansir.mvplayout.client.ui.state;

import com.google.gwt.user.client.ui.HasWidgets;

// TODO: refactor to some generic base class for Stated*Presenter
public interface ProjectsStates {
	
    public void projectEmpty(HasWidgets where);    
    public void projectLoading(HasWidgets where);    
    public void projectNoMatches(HasWidgets where);

}
