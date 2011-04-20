package name.shamansir.mvplayout.client.ui;

import name.shamansir.mvplayout.client.ui.base.MakesLink;

public enum Portal implements MakesLink {
	
	USERS_LIST,
	USER_EDIT,
	USER_SHOW,
	
	NEWS_LIST,
	NEWS_EDIT,
	NEWS_SHOW,
	
	COMPANY_LIST,
	COMPANY_EDIT,
	COMPANY_SHOW;

	@Override
	public String makeLink() {
		return null;
	}

	@Override
	public String makeHistoryLink() {
		return null;
	}
	
	
}
