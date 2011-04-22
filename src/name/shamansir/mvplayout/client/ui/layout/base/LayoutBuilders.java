package name.shamansir.mvplayout.client.ui.layout.base;

import java.util.HashMap;
import java.util.Map;

import name.shamansir.mvplayout.client.ui.Portal.Group;
import name.shamansir.mvplayout.pages.base.ChildEventBus;
import name.shamansir.mvplayout.pages.company.layout.CompanyLayoutBuilder;
import name.shamansir.mvplayout.pages.news.layout.NewsLayoutBuilder;
import name.shamansir.mvplayout.pages.user.layout.UserLayoutBuilder;

public class LayoutBuilders {
	
	private static final Map<Group, LayoutBuilder<?>> builders = new HashMap<Group, LayoutBuilder<?>>(); 
	
	@SuppressWarnings("unchecked")
	public static <E extends ChildEventBus> LayoutBuilder<E> get(Group group) {
		if (group == null) throw new IllegalArgumentException("Passed group is null");
		
		if (builders.containsKey(group)) return (LayoutBuilder<E>) builders.get(group);
		LayoutBuilder<E> builder = null;
		
		switch (group) {
			case USER: builder = (LayoutBuilder<E>) new UserLayoutBuilder(); break;		
			case COMPANY: builder = (LayoutBuilder<E>) new CompanyLayoutBuilder(); break;
			case NEWS: builder = (LayoutBuilder<E>) new NewsLayoutBuilder(); break;
			default: builder = null;
		}
		
		if (builder != null) builders.put(group, builder);
		else throw new IllegalArgumentException("Builder for group " + group + " is not registered in LayoutBuilders");
		return builder;
	}	

}
