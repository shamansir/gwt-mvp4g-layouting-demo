package name.shamansir.mvplayout.lib.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Hyperlink;

import name.shamansir.mvplayout.lib.exception.PortalNotFoundException;
import name.shamansir.mvplayout.lib.ui.structure.Group;
import name.shamansir.mvplayout.lib.ui.structure.LayoutId;
import name.shamansir.mvplayout.lib.ui.structure.PortalId;
import name.shamansir.mvplayout.lib.utils.StringUtils;

public class Portal implements MakesLink {
    
    private static final Map<String, Portal> portals = 
                                        new HashMap<String, Portal>();

    // TODO: wrap this constants with some configuration class
    // means URL_PREFIX group EVENT_DELIM event [ PARAM_MARKER param1 [ PARAM_DELIM param2 [ PARAM_DELIM param3 ... ]  ] ]
    //       #          user  /           show    ?            1        /           no_info    /         read_only  
    public static final String URL_PREFIX = "#";
    public static final String EVENT_DELIM = "/";
    public static final String PARAM_MARKER = "?";
    public static final String PARAM_DELIM = "/";
    
    public final String id;
    public final LayoutId layout;
    public final Group group;
    public final String event;		
    
    public Portal(PortalId id, LayoutId layout) {
        this(id, layout, null, "");
    }
    
    public Portal(PortalId id, LayoutId layout, String event) {
        this(id, layout, null, event);
    }
    
    public Portal(PortalId id, LayoutId layout, Group group, String event) {
    	this.layout = layout;
    	this.group = group;
    	this.event = event;
    	this.id = id.id();
    	//portals.put(this.id, this);
    }
    
    // TODO: handle null group

    @Override
    public String makeLink() {
        if (group == null) return event;
    	return group.name().toLowerCase() + EVENT_DELIM + event;
    }
    
    @Override
    public String toString() {
        if (group == null) return "./" + event + " [" + layout + "]";
    	return group.name().toLowerCase() + "/" + event + " [" + layout + "]";
    }
    
    public String name() {
        if (group == null) return "root-" + event;
        return group + "-" + event;
    }
    
    public static Portal fromId(PortalId id) {
        return portals.get(id.id());
    }
        
    public static class PortalUrl implements MakesLink {
        
        private final Portal portal;
        private final List<String> params;
        
        public PortalUrl(Portal portal) {
            this.portal = portal;
            this.params = new ArrayList<String>();
        }
        
        public PortalUrl(PortalId portal) {
            this(portals.get(portal.id()));
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
            if (params.size() == 0) return portal.makeLink();
            else return portal.makeLink() + PARAM_MARKER + StringUtils.join(params, PARAM_DELIM);
        }

        public static PortalUrl fromEvent(Group group, String event,
                String param) throws PortalNotFoundException {
            for (Portal portal: Portal.portals.values()) {
                
            	if ((portal.group != null) &&
            	    portal.group.equals(group) &&
            		portal.event.equals(event)) {
            		final PortalUrl url = new PortalUrl(portal);
            		if (param != null) url.addParams(param.split(PARAM_DELIM));
            		return url;
            	}
            }
            throw new PortalNotFoundException(group + EVENT_DELIM + event + PARAM_MARKER + param);
        }
        
        @Override
        public String toString() {
        	return portal.toString() + " + " + params.toString();
        }        
        
    }
    
    public static interface UrlBuilder {
        //public String to(Portal portal);
        //public String to(PortalId portal);
        public MakesLink from(Portal portal, String... params);
        public MakesLink from(PortalId portal, String... params);
        public String build(Portal portal, String... params);
        public String build(PortalId portal, String... params);
        public Anchor link(Anchor assignTo, Portal portal, String... params);
        public Anchor link(Anchor assignTo, PortalId portal, String... params);
        public Hyperlink link(Hyperlink assignTo, Portal portal, String... params);
        public Hyperlink link(Hyperlink assignTo, PortalId portal, String... params);
        public String parameters(String... values);
    }
    
    // TODO: add PortalUrlWithNamedParamsBuilder
    
    public static class PortalUrlBuilder implements UrlBuilder {
        
        private static PortalUrlBuilder instance;
        
        private PortalUrlBuilder() { };
        
        /* @Override
        public String to(Portal portal) {
            return new PortalUrl(portal).makeLink();
        }
        
        @Override
        public String to(PortalId portal) {
            return to(portals.get(portal.id()));
        } */    

        public MakesLink from(Portal portal, String... params) {
            return new PortalUrl(portal).addParams(params);
        }
        
        public MakesLink from(PortalId portal, String... params) {
            return from(portals.get(portal.id()), params);
        }        
        
        public String build(Portal portal, String... params) {
            return from(portal, params).makeLink();
        }
        
        public String build(PortalId portal, String... params) {
            return from(portal, params).makeLink();
        }
        
        public String parameters(String... values) {
            return StringUtils.join(values, PARAM_DELIM);
        }
        
        public static PortalUrlBuilder get() {
            if (instance == null) return (instance = new PortalUrlBuilder());
            else return instance;
        }

        @Override
        public Anchor link(Anchor assignTo, Portal portal, String... params) {
            assignTo.setHref(URL_PREFIX + build(portal, params));
            return assignTo;
        }

        @Override
        public Anchor link(Anchor assignTo, PortalId portal, String... params) {
            return link(assignTo, portals.get(portal.id()), params);
        }        

        @Override
        public Hyperlink link(Hyperlink assignTo, Portal portal, String... params) {
            assignTo.setTargetHistoryToken(build(portal, params));
            return assignTo;
        }
        
        @Override
        public Hyperlink link(Hyperlink assignTo, PortalId portal, String... params) {
            return link(assignTo, portals.get(portal.id()), params);
        }        
        
    }

    public static void registerAll(PortalId[] portalIds) {
        for (PortalId id: portalIds) {
            portals.put(id.id(), id.portal());
        }
    }

    
}
