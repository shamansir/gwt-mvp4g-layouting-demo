package name.shamansir.mvplayout.client.ui.pages.main.view;

import name.shamansir.mvplayout.client.ui.Layout;
import name.shamansir.mvplayout.client.ui.Portal;
import name.shamansir.mvplayout.client.ui.Layouts.Place;
import name.shamansir.mvplayout.client.ui.pages.main.presenter.MainPresenter.IMainView;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public final class MainView extends Composite implements IMainView {

	@UiTemplate("MainView.ui.xml")
	interface EurekaPageUiBinder extends UiBinder<Widget, MainView> { }	
	private static EurekaPageUiBinder uiBinder = GWT.create(EurekaPageUiBinder.class);
	
	@UiField FlowPanel layoutHolder;
	
	private Layout currentLayout;
	private Portal currentPortal;
	
	@Override
	public void createView() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@Override
	public void switchLayout(Layout to) {
		Log.debug(">>>>>>>>>>>>>>> NEW PAGE with layout " + to);
		
		layoutHolder.clear();
		if (currentLayout != null) {
			layoutHolder.removeStyleName(generateLayoutCSSClassName(currentLayout));
		}
		
		currentLayout = to;
		layoutHolder.add(currentLayout);
		layoutHolder.addStyleName(generateLayoutCSSClassName(currentLayout));
		
		for (Place place: currentLayout.places()) {
			Widget target = (Widget)currentLayout.panel(place);
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
	public Portal getCurPortal() { return currentPortal; }
	
	protected static String generatePortalCSSClassName(Portal portal) {
		return "p-" + portal.name().toLowerCase().replace('_', '-');
	}
	
	protected static String generateLayoutCSSClassName(Layout layout) {
		return "l-" + layout.id().name().toLowerCase().replace('_', '-');
	}
	
	protected static String generatePlaceCSSClassName(Place place) {
		if (Place.STATUS.equals(place)) return "l-status b-empty";
		return "b-" + place.name().toLowerCase().replace('_', '-');
	}	

	@Override
	public void clear() { currentLayout.clear(); }

    @Override
    public void whenPortalChanged(Portal portal) { }	
	
	/* @Override
	public void changeWidget(Place where, Widget widget) {
		if (!currentLayout.has(where)) throw new IllegalArgumentException("No such place " + where + " in current layout " + currentLayout.getLayoutId());
		final HasWidgets placeholder = currentLayout.panel(where);
		placeholder.clear();
		placeholder.add(widget);
	} */
	
	/* @Override
	public void project(HasWidgets where, Widget what) {
		where.clear();
		where.add(what);
	} */

}
