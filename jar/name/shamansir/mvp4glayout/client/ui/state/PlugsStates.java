package name.shamansir.mvp4glayout.client.ui.state;

import name.shamansir.mvp4glayout.client.ui.structure.Place;

// TODO: refactor to plug(HasWidgets, State)
public interface PlugsStates {
    
    public void plugEmpty(Place where);    
    public void plugLoading(Place where);    
    public void plugNoMatches(Place where);

}
