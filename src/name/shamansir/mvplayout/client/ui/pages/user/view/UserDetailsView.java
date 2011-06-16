package name.shamansir.mvplayout.client.ui.pages.user.view;

import name.shamansir.mvplayout.client.ui.Pluggable;
import name.shamansir.mvplayout.client.ui.pages.user.presenter.UserDetailsPresenter.Display;
import name.shamansir.mvplayout.client.ui.state.LayoutWithState.State;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public final class UserDetailsView extends Composite implements Display {
    
    @UiTemplate("UserDetailsView.ui.xml")	
	interface DetailsViewUiBinder extends UiBinder<Widget, UserDetailsView> { }	
	private static DetailsViewUiBinder uiBinder = GWT.create(DetailsViewUiBinder.class);
	
    public UserDetailsView() { }	
	
	@Override
	public void createView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

    @Override
    public void prepareFor(State to) {
        
    }

    @Override
    public Pluggable getMainView() {
        return null;
    }

    @Override
    public Pluggable getEmptyView() {
        return null;
    }

    @Override
    public Pluggable getLoadingView() {
        return null;
    }

    @Override
    public Pluggable getNoMatchesView() {
        return null;
    }


}
