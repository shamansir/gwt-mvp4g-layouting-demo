package name.shamansir.mvplayout.lib.ui;

import java.util.HashMap;
import java.util.Map;

import name.shamansir.mvplayout.lib.mvp.ChildEventBus;
import name.shamansir.mvplayout.lib.ui.structure.Group;

public class LayoutBuilders {
    
    public static abstract class LazyMaker {
        public abstract LayoutBuilder<?> create();
    }    
	
	private static final Map<Group, LayoutBuilder<?>> builders = new HashMap<Group, LayoutBuilder<?>>();
    private static final Map<Group, LazyMaker> makers = new HashMap<Group, LazyMaker>();
	
	@SuppressWarnings("unchecked")
	public static <E extends ChildEventBus> LayoutBuilder<E> get(Group group) {
		if (group == null) throw new IllegalArgumentException("Passed group is null");
		
		if (builders.containsKey(group)) return (LayoutBuilder<E>)builders.get(group);
        if (makers.containsKey(group)) {
            final LayoutBuilder<E> builder = (LayoutBuilder<E>)makers.get(group).create();      
            if (builder != null) builders.put(group, builder);
            else throw new IllegalStateException("Layout Builder for group " + group.name() + " was not created by Maker");
            return builder;
        } else throw new IllegalStateException("No Layout Builder Maker registered for group " + group.name());
        
	}
	
    public static void register(Group group, LazyMaker maker) {
        makers.put(group, maker);
    }	

}
