/**
 * 
 */
package name.shamansir.mvp4glayoutdemo.client.page.news.view;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import name.shamansir.mvp4glayout.client.ui.Pluggable;
import name.shamansir.mvp4glayout.client.ui.widget.Plug;
import name.shamansir.mvp4glayout.client.ui.widget.Portal;

import name.shamansir.mvp4glayoutdemo.client.page.news.presenter.NewsEditPresenter.Display;
import name.shamansir.mvp4glayoutdemo.shared.dao.NewsItem;
import name.shamansir.mvp4glayoutdemo.shared.dao.User;

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
    
    @UiField TextBox title;
    @UiField TextArea text;
    @UiField Button save;
    
    private int currentItem = -1;
    private User currentAuthor;
    private Date currentPostTime;
    
    @Override
    public void createView() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void loadItem(NewsItem item) {
        this.currentItem = item.getId();
        this.currentAuthor = item.author;
        this.currentPostTime = item.postTime;
        title.setValue(item.title);
        text.setValue(item.text);
    }

    @Override
    public NewsItem collect() {
        final NewsItem result = new NewsItem(currentItem);
        result.title = title.getValue();
        result.text = text.getValue();
        result.author = currentAuthor != null ? currentAuthor : createDefaultAuthor();
        result.postTime = currentPostTime != null ? currentPostTime : new Date();
        return result;
    }

    private User createDefaultAuthor() {
        final User author = new User(-1);
        author.name = "Default";
        author.familyName = "Default";
        author.age = 84;
        author.avatar = "default.jpg";
        return author;
    }

    @Override
    public void itemSavedAs(int newId) {
        this.currentItem = newId;
        Window.alert("News item " + newId + " is saved");
    }

    @Override
    public void clear() {
        currentItem = -1;
        currentAuthor = null;
        currentPostTime = null;        
        title.setValue("");
        text.setValue("");
    }

    @Override
    public HasClickHandlers getSaveButton() {
        return save;
    }

    @Override
    public Pluggable getItemEditor() {
        return infoPlug;
    }

    @Override
    public Pluggable getSavePlug() {
        return savePlug;
    }

}
