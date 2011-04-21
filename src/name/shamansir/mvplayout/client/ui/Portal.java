package name.shamansir.mvplayout.client.ui;

import name.shamansir.mvplayout.client.ui.base.MakesLink;
import name.shamansir.mvplayout.client.ui.layout.base.Layouts.LayoutId;

public enum Portal implements MakesLink {
	
	USERS_LIST(LayoutId.LIST, Group.USER, "list"),
	USER_EDIT(LayoutId.EDIT, Group.USER, "edit"),
	USER_SHOW(LayoutId.ITEM, Group.USER, "show"),
	
	NEWS_LIST(LayoutId.LIST, Group.NEWS, "list"),
	NEWS_EDIT(LayoutId.EDIT, Group.NEWS, "edit"),
	NEWS_SHOW(LayoutId.ITEM, Group.NEWS, "show"),
	
	COMPANY_LIST(LayoutId.LIST, Group.COMPANY, "list"),
	COMPANY_EDIT(LayoutId.EDIT, Group.COMPANY, "edit"),
	COMPANY_SHOW(LayoutId.ITEM, Group.COMPANY, "show");
	
	public enum Group { USER, NEWS, COMPANY };
	
	public final LayoutId layout;
	public final Group group;
	public final String event;
	
	private Portal(LayoutId layout, Group group, String event) {
		this.layout = layout;
		this.group = group;
		this.event = event;
	}

	@Override
	public String makeLink() {
		return null;
	}

	@Override
	public String makeHistoryLink() {
		return null;
	}
	
	
}
