package name.shamansir.mvplayout.client.ui.pages.user.view;

import name.shamansir.mvplayout.client.ui.pages.user.presenter.UserDetailsPresenter.Display;
import name.shamansir.mvplayout.client.ui.state.LayoutWithState.State;
import name.shamansir.mvplayout.client.ui.widget.PortletView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Widget;

public final class UserDetailsView extends PortletView implements Display {
    
    public static final String PORTLET_ID = "user-details";

    @UiTemplate("UserDetailsView.ui.xml")	
	interface DetailsViewUiBinder extends UiBinder<Widget, UserDetailsView> { }	
	private static DetailsViewUiBinder uiBinder = GWT.create(DetailsViewUiBinder.class);
	
    public UserDetailsView() { 
        super(PORTLET_ID);
    }	
	
	@Override
	public void createView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

    @Override
    public void prepareFor(State to) {
        // TODO Auto-generated method stub
        
    }


}
