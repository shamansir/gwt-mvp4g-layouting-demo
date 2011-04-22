package name.shamansir.mvplayout.client.ui.pages.main.view;

import name.shamansir.mvplayout.client.ui.pages.main.presenter.MainPresenter.IMainView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public final class MainView extends Composite implements IMainView {

	@UiTemplate("MainView.ui.xml")
	interface EurekaPageUiBinder extends UiBinder<Widget, MainView> { }	
	private static EurekaPageUiBinder uiBinder = GWT.create(EurekaPageUiBinder.class);
	
	@Override
	public void createView() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	/*
	@Override
	public void switchLayout(Layout to) {
		Log.debug(">>>>>>>>>>>>>>> NEW PAGE");
		
		unregisterPageScrollHandlers();
		
		layoutHolder.clear();
		if (currentLayout != null) {
			layoutHolder.removeStyleName(generateLayoutCSSClassName(currentLayout));
		}
		
		currentLayout = to;
		layoutHolder.add(currentLayout);
		layoutHolder.addStyleName(generateLayoutCSSClassName(currentLayout));
		
		for (Place place: currentLayout.hasPlaces()) {
			Widget target = (Widget)currentLayout.getPanel(place);
			target.addStyleName(generatePlaceCSSClassName(place));
		}
	}
	
	@Override
	public void beforePortalChange(Portal to) {
		if (currentLayout != null) {
			layoutHolder.removeStyleName(generatePortalCSSClassName(currentPortal));
		}
		
		currentPortal = to;
		layoutHolder.addStyleName(generatePortalCSSClassName(currentPortal));
	}
	
	@Override
	public Portal getCurrentPortal() { return currentPortal; }
	
	protected static String generatePortalCSSClassName(Portal portal) {
		return "a-" + portal.name().toLowerCase().replace('_', '-');
	}
	
	protected static String generateLayoutCSSClassName(Layout layout) {
		return "l-" + layout.getLayoutId().name().toLowerCase().replace('_', '-');
	}
	
	protected static String generatePlaceCSSClassName(Place place) {
		if (Place.STATUS.equals(place)) return "l-status b-empty";
		return "l-" + place.name().toLowerCase().replace('_', '-');
	}	

	@Override
	public void clear() { currentLayout.clear(); }	
	
	@Override
	public void changeWidget(Place where, Widget widget) {
		if (!currentLayout.hasPlace(where)) throw new IllegalArgumentException("No such place " + where + " in current layout " + currentLayout.getLayoutId());
		final HasWidgets placeholder = currentLayout.getPanel(where);
		placeholder.clear();
		placeholder.add(widget);
	}
	
	@Override
	public void project(HasWidgets where, Widget what) {
		where.clear();
		where.add(what);
	}	

	 */

}
