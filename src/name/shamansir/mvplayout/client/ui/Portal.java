package name.shamansir.mvplayout.client.ui;

import name.shamansir.mvplayout.client.ui.base.MakesLink;
import name.shamansir.mvplayout.client.ui.layout.base.Layouts.LayoutId;

public enum Portal implements MakesLink {
	
	USERS_LIST(LayoutId.LIST),
	USER_EDIT(LayoutId.EDIT),
	USER_SHOW(LayoutId.ITEM),
	
	NEWS_LIST(LayoutId.LIST),
	NEWS_EDIT(LayoutId.EDIT),
	NEWS_SHOW(LayoutId.ITEM),
	
	COMPANY_LIST(LayoutId.LIST),
	COMPANY_EDIT(LayoutId.EDIT),
	COMPANY_SHOW(LayoutId.ITEM);
	
	public final LayoutId layout;
	
	private Portal(LayoutId layout) {
		this.layout = layout;
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
