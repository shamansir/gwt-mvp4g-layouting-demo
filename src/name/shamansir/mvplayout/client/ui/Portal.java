package name.shamansir.mvplayout.client.ui;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.google.appengine.repackaged.com.google.common.base.StringUtil;

import name.shamansir.mvplayout.client.ui.Layouts.LayoutId;

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
	
	public static final String EVENT_DELIM = "/";
	public static final String PARAM_MARKER = "/";
	public static final String PARAM_DELIM = "/";
	
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
		return group.name() + EVENT_DELIM + event;
	}

	public static class PortalUrl implements MakesLink {
	    
	    private final Portal portal;
	    private final List<String> params;
	    
	    public PortalUrl(Portal portal) {
	        this.portal = portal;
	        this.params = new ArrayList<String>();
	    }
	    
	    public PortalUrl addParam(String value) {
	        this.params.add(value);
	        return this;
	    }

        @Override
        public String makeLink() {
            return portal.makeLink() + PARAM_MARKER + StringUtils.join(params, PARAM_DELIM);
        }
	    
	}
	
}
