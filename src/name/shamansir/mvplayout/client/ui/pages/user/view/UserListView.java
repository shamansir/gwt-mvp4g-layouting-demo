package name.shamansir.mvplayout.client.ui.pages.user.view;

import java.util.Set;

import name.shamansir.mvplayout.client.ui.Pluggable;
import name.shamansir.mvplayout.client.ui.pages.user.presenter.UserListPresenter.Display;
import name.shamansir.mvplayout.client.ui.state.LayoutWithState.State;
import name.shamansir.mvplayout.client.ui.widget.Plug;
import name.shamansir.mvplayout.shared.dao.User;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public final class UserListView extends Composite implements Display {
    
    @UiTemplate("UserListView.ui.xml")	
	interface ListViewUiBinder extends UiBinder<Widget, UserListView> { }	
	private static ListViewUiBinder uiBinder = GWT.create(ListViewUiBinder.class);
	
	@UiField Plug mainView;
	@UiField Plug ifEmpty;
	@UiField Plug whenLoading;
	@UiField Plug noMatches;
	
    public UserListView() {  }	
	
	@Override
	public void createView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

    @Override
    public void showUsers(Set<User> users) {
        
    }

    @Override
    public void prepareFor(State to) {
        
    }

    @Override
    public Pluggable getMainView() { return mainView; }

    @Override
    public Pluggable getEmptyView() { return ifEmpty; }

    @Override
    public Pluggable getLoadingView() { return whenLoading; }

    @Override
    public Pluggable getNoMatchesView() { return noMatches; }

}
