package name.shamansir.mvplayout.lib.ui.state;

import name.shamansir.mvplayout.lib.ui.structure.Place;

// TODO: refactor to plug(HasWidgets, State)
public interface PlugsStates {
	
    public void plugEmpty(Place where);    
    public void plugLoading(Place where);    
    public void plugNoMatches(Place where);

}
