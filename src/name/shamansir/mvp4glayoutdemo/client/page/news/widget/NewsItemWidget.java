/**
 * 
 */
package name.shamansir.mvp4glayoutdemo.client.page.news.widget;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

import name.shamansir.mvp4glayoutdemo.shared.dao.NewsItem;

/**
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 *
 */
public class NewsItemWidget extends FlowPanel implements HasClickHandlers {

    
    private final NewsItem item;
    
    private Anchor showAnchor;
    private Anchor editAnchor;
    private FocusPanel clickable;
    
    public NewsItemWidget(NewsItem item) {
        super();
        this.item = item;
        setupComponent();
    }
    
    private void setupComponent() {
        final HorizontalPanel wrapper = new HorizontalPanel();
        clickable = new FocusPanel();
        
        clickable.add(new Label(item.title + 
                " (" + item.author.name + " " +  item.author.familyName + ")"));
        
        // TODO: show complete info
        
        wrapper.add(clickable);
        
        this.showAnchor = new Anchor("show");
        wrapper.add(showAnchor);
        this.editAnchor = new Anchor("edit");
        wrapper.add(editAnchor);            
        this.add(wrapper);                       
    }
    
    public NewsItem getItem() { return item; }
    
    public Anchor getShowAnchor() { return showAnchor; }        
    
    public Anchor getEditAnchor() { return editAnchor; }
    
    @Override
    public HandlerRegistration addClickHandler(ClickHandler handler) {
        return clickable.addClickHandler(handler);
    }
    
}
