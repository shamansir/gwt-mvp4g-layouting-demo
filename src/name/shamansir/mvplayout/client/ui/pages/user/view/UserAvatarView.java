package name.shamansir.mvplayout.client.ui.pages.user.view;

import name.shamansir.mvplayout.client.ui.Pluggable;
import name.shamansir.mvplayout.client.ui.pages.user.presenter.UserAvatarPresenter.Display;
import name.shamansir.mvplayout.client.ui.state.LayoutWithState.State;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public final class UserAvatarView extends Composite implements Display {
    
    @UiTemplate("UserAvatarView.ui.xml")	
	interface AvatarViewUiBinder extends UiBinder<Widget, UserAvatarView> { }	
	private static AvatarViewUiBinder uiBinder = GWT.create(AvatarViewUiBinder.class);
	
    public UserAvatarView() {  }	
	
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
    public Pluggable getViewFor(State state) {
        return null;
    }

}
