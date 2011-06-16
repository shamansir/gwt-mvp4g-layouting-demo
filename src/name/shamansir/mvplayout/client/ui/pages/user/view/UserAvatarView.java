package name.shamansir.mvplayout.client.ui.pages.user.view;

import name.shamansir.mvplayout.client.ui.pages.user.presenter.UserAvatarPresenter.Display;
import name.shamansir.mvplayout.client.ui.state.LayoutWithState.State;
import name.shamansir.mvplayout.client.ui.widget.PortletView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Widget;

public final class UserAvatarView extends PortletView implements Display {
    
    public static final String PORTLET_ID = "user-avatar";

    @UiTemplate("UserAvatarView.ui.xml")	
	interface AvatarViewUiBinder extends UiBinder<Widget, UserAvatarView> { }	
	private static AvatarViewUiBinder uiBinder = GWT.create(AvatarViewUiBinder.class);
	
    public UserAvatarView() { 
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
