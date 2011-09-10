package name.shamansir.mvp4glayoutdemo.client.page.user.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import name.shamansir.mvp4glayout.client.ui.state.State;
import name.shamansir.mvp4glayout.client.ui.widget.Plug;
import name.shamansir.mvp4glayout.client.ui.widget.StatedPortlet;

import name.shamansir.mvp4glayoutdemo.client.page.user.presenter.UserInfoPresenter.Display;
import name.shamansir.mvp4glayoutdemo.shared.dao.User;

public final class UserInfoView extends StatedPortlet implements Display {
    
    @UiTemplate("UserInfoView.ui.xml")    
    interface InfoViewUiBinder extends UiBinder<Widget, UserInfoView> { }	
    private static InfoViewUiBinder uiBinder = GWT.create(InfoViewUiBinder.class);
    
    @UiField Plug mainView;
    @UiField Plug ifEmpty;
    @UiField Plug whenLoading;
    
    @UiField Label name;
    @UiField Label familyName;
    
    public UserInfoView() { 
    }    
    
    @Override
    public void createView() {
    	initWidget(uiBinder.createAndBindUi(this));
    	
    	register(ifEmpty, State.NO_DATA);
    	register(whenLoading, State.LOADING_DATA);
    	register(mainView, State.HAS_DATA);
    }

    @Override
    public void showUser(User user) {
        name.setText(user.name);
        familyName.setText(user.familyName);
    }

    @Override
    public void prepareFor(State to) {
        
    }

}
