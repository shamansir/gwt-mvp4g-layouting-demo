package name.shamansir.mvplayout.client.page.news.presenter;

import name.shamansir.mvplayout.client.id.P;
import name.shamansir.mvplayout.client.page.news.NewsEventBus;
import name.shamansir.mvplayout.client.page.news.layout.NewsLayoutBuilder;
import name.shamansir.mvplayout.client.page.news.view.NewsEditView;
import name.shamansir.mvplayout.client.service.NewsServiceAsync;
import name.shamansir.mvplayout.lib.mvp.IsPortalView;
import name.shamansir.mvplayout.lib.mvp.PortalPresenter;
import name.shamansir.mvplayout.lib.ui.Pluggable;
import name.shamansir.mvplayout.lib.ui.structure.Place;
import name.shamansir.mvplayout.lib.utils.SafeCallback;
import name.shamansir.mvplayout.shared.dao.NewsItem;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.History;
import com.google.inject.Inject;
import com.mvp4g.client.annotation.Presenter;

@Presenter(view = NewsEditView.class)
public class NewsEditPresenter extends PortalPresenter<NewsEditPresenter.Display, NewsEventBus, NewsLayoutBuilder> {

    public interface Display extends IsPortalView {
        public void loadItem(NewsItem item);
        public NewsItem collect();
        public void itemSavedAs(int newId);
        
        public void clear();
        
        public HasClickHandlers getSaveButton();
        
        public Pluggable getItemEditor();
        public Pluggable getSavePlug();
    }
    
    @Inject NewsServiceAsync service;
    
    protected NewsEditPresenter() {
        super(P.NEWS_EDIT.portal);
    }
    
    @Override
    public void bindView() {
        view.getSaveButton().addClickHandler(new ClickHandler() {
            
            @Override
            public void onClick(ClickEvent event) {
                final NewsItem item = view.collect();
                service.saveNewsItem(item, new SafeCallback<Integer>(eventBus) {

                    @Override
                    public void onSuccess(Integer newId) {
                        History.newItem(url.build(P.NEWS_EDIT, String.valueOf(newId)));
                        view.itemSavedAs(newId);
                        eventBus.showUserCard(item.author);
                    }
                    
                });
            }
            
        });        
    }
    
    public void onEdit(int nid) {
        if (nid != -1) {
            service.getNewsItem(nid, new SafeCallback<NewsItem>(eventBus) {
    
                @Override
                public void onSuccess(NewsItem item) {
                    view.loadItem(item);
                    eventBus.showUserCard(item.author);
                }
            });
        } else {
            view.clear();
            eventBus.clearUserCard();
        }
    }
    
    public void plugItemEditor(Place where) {
        plug(where, view.getItemEditor());
    }
    
    public void plugSaveButton(Place where) {
        plug(where, view.getSavePlug());
    }
    
    
}
