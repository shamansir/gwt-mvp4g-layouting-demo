/**
 * 
 */
package name.shamansir.mvplayout.client.page.news.view;

import name.shamansir.mvplayout.client.page.news.presenter.NewsEditPresenter.Display;
import name.shamansir.mvplayout.lib.ui.Pluggable;
import name.shamansir.mvplayout.lib.ui.widget.Plug;
import name.shamansir.mvplayout.lib.ui.widget.Portal;
import name.shamansir.mvplayout.shared.dao.NewsItem;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 *
 */
public class NewsEditView extends Portal implements Display {
    
    @UiTemplate("NewsEditView.ui.xml")  
    interface EditViewUiBinder extends UiBinder<Widget, NewsEditView> { }   
    private static EditViewUiBinder uiBinder = GWT.create(EditViewUiBinder.class);

    @UiField Plug infoPlug;
    @UiField Plug savePlug;
    
    @Override
    public void createView() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void loadItem(NewsItem item) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public NewsItem collect() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void itemSavedAs(int newId) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public HasClickHandlers getSaveButton() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Pluggable getItemEditor() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Pluggable getSavePlug() {
        // TODO Auto-generated method stub
        return null;
    }

}
