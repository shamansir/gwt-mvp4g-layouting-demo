package name.shamansir.mvplayout.client.pages.user.view;

import name.shamansir.mvplayout.client.pages.user.presenter.UserDetailsPresenter.Display;
import name.shamansir.mvplayout.lib.ui.state.State;
import name.shamansir.mvplayout.lib.ui.widget.StatedPortlet;
import name.shamansir.mvplayout.shared.dao.User;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;

public final class UserDetailsView extends StatedPortlet implements Display {
    
    @UiTemplate("UserDetailsView.ui.xml")	
	interface DetailsViewUiBinder extends UiBinder<Widget, UserDetailsView> { }	
	private static DetailsViewUiBinder uiBinder = GWT.create(DetailsViewUiBinder.class);
	
    public UserDetailsView() { }	
	
	@Override
	public void createView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

    @Override
    public void showDetails(User user) {
        Window.alert("show details for " + user.getId());
    }

    @Override
    public void prepareFor(State to) {
        
    }

}
