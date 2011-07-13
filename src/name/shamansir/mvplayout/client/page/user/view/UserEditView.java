package name.shamansir.mvplayout.client.page.user.view;

import name.shamansir.mvplayout.client.page.user.presenter.UserEditPresenter.Display;
import name.shamansir.mvplayout.lib.ui.Pluggable;
import name.shamansir.mvplayout.lib.ui.state.State;
import name.shamansir.mvplayout.lib.ui.widget.Plug;
import name.shamansir.mvplayout.lib.ui.widget.StatedPortlet;
import name.shamansir.mvplayout.shared.dao.User;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public final class UserEditView extends StatedPortlet implements Display {
    
    @UiTemplate("UserEditView.ui.xml")	
	interface EditViewUiBinder extends UiBinder<Widget, UserEditView> { }	
	private static EditViewUiBinder uiBinder = GWT.create(EditViewUiBinder.class);
	
	@UiField Plug ifEmpty;
	@UiField Plug whenLoading;
	
	@UiField Plug basicInfoPlug;
	@UiField Plug avatarPlug;
	@UiField Plug agePlug;
	@UiField Plug testPlug;
	
	@UiField TextBox name;
	@UiField TextBox familyName;
	@UiField TextBox age;
	@UiField TextBox avatar;
	@UiField Button save;
	
	private int currentUser = -1;
	
    public UserEditView() { 
    }	
	
	@Override
	public void createView() {
		initWidget(uiBinder.createAndBindUi(this));
		
        register(ifEmpty, State.NO_DATA);
        register(whenLoading, State.LOADING_DATA);		
	}

    @Override
    public void prepareFor(State to) {
        
    }

    @Override
    public void clear() {
        currentUser = -1;
        name.setValue("");
        familyName.setValue("");
        age.setValue("");
        avatar.setValue("");
    }

    @Override
    public User collect() {
        final User result = new User(currentUser);
        result.name =  name.getValue();
        result.familyName =  familyName.getValue();
        result.age =  Integer.valueOf(age.getValue());
        result.avatar =  avatar.getValue();
        return result;
    }

    @Override
    public void showUser(User user) {
        this.currentUser = user.getId();
        name.setValue(user.name);
        familyName.setValue(user.familyName);
        age.setValue(String.valueOf(user.age));
        avatar.setValue(String.valueOf(user.avatar));
    }

    @Override
    public HasClickHandlers getSaveButton() { return save; }
    
    @Override
    public Pluggable getInfoEditor() { return basicInfoPlug; }

    @Override
    public Pluggable getAgeEditor() { return agePlug; }

    @Override
    public Pluggable getAvatarEditor() { return avatarPlug; }

    @Override
    public Pluggable getTestWidget() { return testPlug; }

    @Override
    public void userSavedAs(int newId) {
        this.currentUser = newId;
        Window.alert("User " + newId + " is saved");
    }

}
