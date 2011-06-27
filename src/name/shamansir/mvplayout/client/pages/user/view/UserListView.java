package name.shamansir.mvplayout.client.pages.user.view;

import java.util.HashSet;
import java.util.Set;

import name.shamansir.mvplayout.client.pages.user.presenter.UserListPresenter.Display;
import name.shamansir.mvplayout.client.pages.user.presenter.UserListPresenter.UserRow;
import name.shamansir.mvplayout.lib.ui.state.State;
import name.shamansir.mvplayout.lib.ui.widget.Plug;
import name.shamansir.mvplayout.lib.ui.widget.StatedPortlet;
import name.shamansir.mvplayout.shared.dao.User;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public final class UserListView extends StatedPortlet implements Display {
    
    @UiTemplate("UserListView.ui.xml")	
	interface ListViewUiBinder extends UiBinder<Widget, UserListView> { }	
	private static ListViewUiBinder uiBinder = GWT.create(ListViewUiBinder.class);
	
	@UiField Plug mainView;
	@UiField Plug ifEmpty;
	@UiField Plug whenLoading;
	@UiField Plug noMatches;
	
	@UiField VerticalPanel usersHolder;	
	
	private final Set<UserRow> rows = new HashSet<UserRow>();
	
    public UserListView() { 
        rows.clear();
    }	
	
	@Override
	public void createView() {
		initWidget(uiBinder.createAndBindUi(this));
		
		register(ifEmpty, State.NO_DATA);
		register(whenLoading, State.LOADING_DATA);
		register(noMatches, State.NO_MATCHES);
		register(mainView, State.HAS_DATA);
	}

    @Override
    public void showUsers(Set<User> users) {
        rows.clear();
        usersHolder.clear();
        for (User user: users) {
            final UserPanel userPanel = new UserPanel(user);
            userPanel.add(new Label(user.name + " " + user.familyName));
            rows.add(userPanel);
            usersHolder.add(userPanel);
        }
    }

    @Override 
    public Set<UserRow> getRows() {
        return rows;
    }

    @Override
    public void prepareFor(State to) {
        
    }
    
    public class UserPanel extends FocusPanel implements UserRow {

        private final User user;
        
        public UserPanel(User user) {
            super();
            this.user = user;
        }
        
        @Override
        public User getUser() { return user; }
        
    }

}
