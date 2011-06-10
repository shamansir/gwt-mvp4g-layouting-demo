package name.shamansir.mvplayout.client.ui.state;

import name.shamansir.mvplayout.client.ui.Layouts.Place;

// TODO: refactor to plug(HasWidgets, State)
public interface PlugsStates {
	
    public void plugEmpty(Place where);    
    public void plugLoading(Place where);    
    public void plugNoMatches(Place where);

}
