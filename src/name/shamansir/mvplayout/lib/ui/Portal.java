package name.shamansir.mvplayout.lib.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import name.shamansir.mvplayout.lib.exception.PortalNotFoundException;
import name.shamansir.mvplayout.lib.ui.structure.Group;
import name.shamansir.mvplayout.lib.ui.structure.LayoutId;
import name.shamansir.mvplayout.lib.ui.structure.PortalId;
import name.shamansir.mvplayout.lib.utils.StringUtils;

public class Portal implements MakesLink {
    
    private static final Map<String, Portal> instances = 
                                        new HashMap<String, Portal>();
	
	public static final String EVENT_DELIM = "/";
	public static final String PARAM_MARKER = "/";
	public static final String PARAM_DELIM = "/";
	
	public final String id;
	public final LayoutId layout;
	public final Group group;
	public final String event;
	
	public Portal(PortalId id, LayoutId layout, Group group, String event) {
		this.layout = layout;
		this.group = group;
		this.event = event;
		this.id = id.id();
		instances.put(this.id, this);
	}	

	@Override
	public String makeLink() {
		return group.name().toLowerCase() + EVENT_DELIM + event;
	}
	
	@Override
	public String toString() {
		return group.name().toLowerCase() + "/" + event + " [" + layout + "]";
	}
	
    public String name() {
        return group + "-" + event;
    }	
	
	public static class PortalUrl implements MakesLink {
	    
	    private final Portal portal;
	    private final List<String> params;
	    
	    public PortalUrl(Portal portal) {
	        this.portal = portal;
	        this.params = new ArrayList<String>();
	    }
	    
	    public PortalUrl addParam(String value) {
	    	if (value == null) return this;
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
        	for (Portal portal: Portal.instances.values()) {
        		if (portal.group.equals(group) &&
        			portal.event.equals(event)) {
        			final PortalUrl url = new PortalUrl(portal);
        			if (param != null) url.addParams(param.split(PARAM_DELIM));
        			return url;
        		}
        	}
            throw new PortalNotFoundException(group + "/" + event + "/" + param);
        }
        
    	@Override
    	public String toString() {
    		return portal.toString() + " + " + params.toString();
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
