package name.shamansir.mvplayout.client.ui.pages.user.view;

import java.util.Set;

import name.shamansir.mvplayout.client.ui.Pluggable;
import name.shamansir.mvplayout.client.ui.pages.user.presenter.UserListPresenter.Display;
import name.shamansir.mvplayout.client.ui.state.LayoutWithState.State;
import name.shamansir.mvplayout.client.ui.widget.PortalView;
import name.shamansir.mvplayout.shared.dao.User;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Widget;

public final class UserListView extends PortalView implements Display {
    

    @UiTemplate("UserListView.ui.xml")	
	interface ListViewUiBinder extends UiBinder<Widget, UserListView> { }	
	private static ListViewUiBinder uiBinder = GWT.create(ListViewUiBinder.class);
	
    public UserListView() { }	
	
	@Override
	public void createView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void prepareFor(State to) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Pluggable getEmptyView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pluggable getLoadingView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pluggable getNoMatchesView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void showUsers(Set<User> users) {
		// TODO Auto-generated method stub
		
	}

}
