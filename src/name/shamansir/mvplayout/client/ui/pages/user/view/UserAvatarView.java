package name.shamansir.mvplayout.client.ui.pages.user.view;

import name.shamansir.mvplayout.client.ui.pages.user.presenter.UserAvatarPresenter.Display;
import name.shamansir.mvplayout.client.ui.state.LayoutWithState.State;
import name.shamansir.mvplayout.client.ui.widget.Plug;
import name.shamansir.mvplayout.client.ui.widget.StatedPortlet;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public final class UserAvatarView extends StatedPortlet implements Display {
    
    @UiTemplate("UserAvatarView.ui.xml")	
	interface AvatarViewUiBinder extends UiBinder<Widget, UserAvatarView> { }	
	private static AvatarViewUiBinder uiBinder = GWT.create(AvatarViewUiBinder.class);
	
    public UserAvatarView() {  }
    
    @UiField Label avatarPath;
    
    @UiField Plug mainView;
    @UiField Plug ifEmpty;
    @UiField Plug whenLoading;
	
	@Override
	public void createView() {
		initWidget(uiBinder.createAndBindUi(this));
		
		register(mainView, State.HAS_DATA);
		register(ifEmpty, State.NO_DATA);
		register(whenLoading, State.LOADING_DATA);
	}

    @Override
    public void prepareFor(State to) {
        
    }

    @Override
    public void showAvatar(String avatar) {
        avatarPath.setText(avatar);
    }

}
