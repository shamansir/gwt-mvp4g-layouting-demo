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

import name.shamansir.mvp4glayoutdemo.client.page.user.presenter.UserDetailsPresenter.Display;
import name.shamansir.mvp4glayoutdemo.shared.dao.User;

public final class UserDetailsView extends StatedPortlet implements Display {
    
    @UiTemplate("UserDetailsView.ui.xml")    
    interface DetailsViewUiBinder extends UiBinder<Widget, UserDetailsView> { }	
    private static DetailsViewUiBinder uiBinder = GWT.create(DetailsViewUiBinder.class);
    
    public UserDetailsView() { }    
    
    @UiField Label dataSummary;
    
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
    public void showDetails(User user) {
        dataSummary.setText("Age: "  + user.age);
    }

}
