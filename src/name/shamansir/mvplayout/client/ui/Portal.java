package name.shamansir.mvplayout.client.ui;

import java.util.ArrayList;
import java.util.List;

import name.shamansir.mvplayout.client.exception.PortalNotFoundException;
import name.shamansir.mvplayout.client.ui.Layouts.LayoutId;
import name.shamansir.mvplayout.client.utils.StringUtils;

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
	    
	    public PortalUrl addParam(Object value) {
	        return addParam(value.toString());
	    }
	    
        public PortalUrl addParams(String... values) {
            for (String value: values) addParam(value);
            return this;
        }
        
        public PortalUrl addParams(Object... values) {
            for (Object value: values) addParam(value);
            return this;
        }
        
        public List<String> params() {
            return params;
        }
        
        public String param(int pos) {
            return (pos < params.size())
                   ? params.get(pos)
                   : null;
        }
	    
        public Portal view() {
            return portal;
        }

        @Override
        public String makeLink() {
            return portal.makeLink() + PARAM_MARKER + StringUtils.join(params, PARAM_DELIM);
        }

        public static PortalUrl fromEvent(Group group, String event,
                String param) throws PortalNotFoundException {
        	for (Portal portal: Portal.values()) {
        		if (portal.group.equals(group) &&
        			portal.event.equals(event)) return new PortalUrl(portal).addParams(param.split(PARAM_DELIM));
        	}
            throw new PortalNotFoundException(group + "/" + event + "/" + param);
        }
        
	}
	
	public static interface UrlBuilder {
	    public String build(Portal portal, String... params);
	    public String parameters(String... values);
	}
	
	public static class PortalUrlBuilder implements UrlBuilder {
	    
	    private static PortalUrlBuilder instance;
	    
	    private PortalUrlBuilder() { };
	    
	    public String build(Portal portal, String... params) {
	        return new PortalUrl(portal).addParams(params).makeLink();
	    }
	    
        public String parameters(String... values) {
            return StringUtils.join(values, PARAM_DELIM);
        }
        
        public static PortalUrlBuilder get() {
            if (instance == null) return (instance = new PortalUrlBuilder());
            else return instance;
        }
	    
	    
	}
	
}
