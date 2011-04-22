package name.shamansir.mvplayout.client.ui.pages.user.layout;

import java.util.Map;

import com.google.gwt.user.client.ui.HasWidgets;

import name.shamansir.mvplayout.client.ui.Layout;
import name.shamansir.mvplayout.client.ui.LayoutBuilder;
import name.shamansir.mvplayout.client.ui.Portal;
import name.shamansir.mvplayout.client.ui.LayoutWithState.State;
import name.shamansir.mvplayout.client.ui.Layouts.Place;
import name.shamansir.mvplayout.client.ui.pages.user.UserEventBus;

public class UserLayoutBuilder extends LayoutBuilder<UserEventBus> {

	@Override
	protected boolean layout(Portal view, Layout layout, State state,
			Map<Place, HasWidgets> panels, UserEventBus eventBus) {
		// TODO Auto-generated method stub
		
		/*
	@Override
	public boolean layout(Portal view, Layout layout, State state, 
			              Map<Place, HasWidgets> panels, JobEventBus eventBus) {
		switch (view) {
		    // Job Start View
			case JOB_START: {
					eventBus.projectAgentsList(panels.get(Place.MAIN));
					eventBus.projectAccordion(panels.get(Place.SECONDARY));
					eventBus.projectVacanciesClipboardWidget(panels.get(Place.ASIDE));
				}; return true;
				// Clipboard page
			case JOB_CLIPBOARD: {
				switch (state) {
					case HAS_DATA: {
						eventBus.projectClipboardToolbar(panels.get(Place.HEADER));
						eventBus.projectVacanciesClipboardVerticalList(panels.get(Place.NAV));
						eventBus.projectVacancyPanel(panels.get(Place.MAIN));							
					} return true;
					case LOADING_DATA: {
						eventBus.projectClipboardToolbar(panels.get(Place.HEADER));
						eventBus.projectClipboardLoading(panels.get(Place.STATUS));
					} return true;					
					case NO_DATA: {
						eventBus.projectClipboardToolbar(panels.get(Place.HEADER));
						eventBus.projectClipboardEmpty(panels.get(Place.STATUS));
					} return true;
				}
			}				
		}
		return false;
	}

		 */
		return false;
	}

}
