package name.shamansir.mvplayout.client.ui.state;

import name.shamansir.mvplayout.client.ui.IsOutlet;

// TODO: refactor to project(HasWidgets, State)
public interface ProjectsStates {
	
    public void projectEmpty(IsOutlet where);    
    public void projectLoading(IsOutlet where);    
    public void projectNoMatches(IsOutlet where);

}
