---
layout: main-ru
title: Scenario
---

## How I may use it?

In fact, in [`name.shamansir.mvplayout.lib`](https://github.com/shamansir/gwt-mvp4g-layouting-demo/tree/master/src/name/shamansir/mvplayout/lib) package there is a code you may use as a library with ease. I'm not wrapping it with `jar` package, but potentially it is quite reliable for usage — a stable-working complex example illustrates it, and the core idea (not actual and totally-fixed one, however) version works in [our project](http://experika.com)

Let me explain how concretely you may use this code if you've taken a decision to build a project based on it. You can see below a number of scenarios: they can be used simultaneously as a conseсuitive ones or a separate ones. Executing them one by one (however, you must repeat some actions of those until-it-is-necessary), you can write a complete application, but you can execute each action separately as application changes need.

It is difficult to describe everything with words, sometimes it is easier to look at examples by following the links in the order of scenario goes. Don't hesitate to use this opportunity :).

(My method is to use `enum` instances to configurate project navigation/pages structure, it was applied to implement this framework fast; but later, I suppose, it will be better to change this principle to annotations or `xml`-files, if you will not find it easy in use)

### 1. Application skeleton

 1. Think over the navigation system of your project, it is recommended to make it match the `object-type/action` scheme.
 1. Create four `enum`-files. These `enum`-files are the main and the sole page and layouts structure configuration of your project (you don't need to worry and keep these `enum`s reflecting actual reality, but for sure you need to store there something potentially correct):
   * First one (see example: [id/P.java][P]) to enumerate _pages_, to describe _URL_-s (group — an object type, and an event — is an action in a `object-type/action` scheme) and to link them to _layouts_. Each page gets an ID using `id()` method automatically (`enum` element's `name()` method serves this purpose in the example, but in fact you may use anything to do this) and gets linked to the prepared `Portal` class instance: to use this trick, your `enum` must implement `PortalId` interface. Also you'll need an easy way to get proper `enum` value from unknown `Portal` instance: in example it's done with method `by(Portal page)`, that's the reason why `Portal` instances are kept inside `enum`.
   * Second one (see example: [id/G.java][G]) to enumerate _groups_. Must implement `Group` interface: to return unique ID with `id()` method.
   * Third one (see example: [id/L.java][L]) to enumerate _layouts_. Must implement `LayoutId` interface: to return unique ID with `id()` method.
   * Fourth one (see example: [id/L.java][L]) to enumerate _placeholders_. Must implement `Place` interface: to return unique ID with `id()` method.
 1. Create `EntryPoint` for your project and register there your enumerated portals; more of that — you need to do this _before_ the start of mvp4g module. In future, you'll also need to register new layouts and layout-builders here, but it is not required for this moment. See entry point [LayoutingDemo.java][LayoutingDemo] in example.
 1. Create main page `Presenter` and extend it from [AMainPresenter][] class. Nothing more special to do here. See example: [page/main/presenter/MainPresenter.java][MainPresenter].
 1. Create main page `View` end extend it from [AMainView][] (see example [page/main/view/MainView.java][MainView]). Methods required to define:
   * `getLayoutHolder()` method must return the panel to "hold" a complete layout (it will be "injected" just there).
   * `getPortalHolder()` method may return the panel that wraps the previous one, or even the same panel. This method is only used to assign CSS-classes, so portal and layout classes will be assigned to the same element in latter case.
   * `getScrollable()` method may return the area to be scrolled inside of a page. If such area is not required, the method may return a `null` value, but this behaviour will turn off subscribing page scroll-events using event bus (`addPageScrollHandler`).
 1. Create the main event bus that implements [IsMainEventBus][]. In the main event bus you need to override all the methods from `IsMainEventBus` interface and redirect them to the main page presenter (it will handle them automatically using the abstract class). Also you need to create a module that has no differences from standard mvp4g module. See example: [page/main/MainEventBus.java][MainEventBus] и [page/main/MainModule.java][MainModule].

### 2a. Making a new layout

 1. Add a new layout ID to layouts `enum`. See example [id/L.java][L].
 1. If you have some, add new placeholder IDs to placeholders `enum`. See example [id/O.java][O].
 1. If you need to describe layout using declarative way, create an according `ui.xml` file. To mark the places where portlets will be placed (placeholders), it is required to use [Outlet][] widget. See example [layout/LayoutItem.ui.xml][LayoutItem.ux]. If you want to declare layout using standard OOP techninques, then you'll need to use [Outlet][] constructor.
 1. Create a layout class, inherit it from [Layout][]. Pass a new layout ID to parent constructor. Also, pass the IDs of all placeholders owned by this layout. In the `prepareOutlet()` overriden method return the appropriate `Outlet` using the received ID. Widget must be initialized right inside the constructor. See example [layout/LayoutItem.java][LayoutItem].
 1. Register a layout in entry point using `Layouts.register()` method. See example [LayoutingDemo.java:47][LayoutingDemo.L47].

### 2b. Making a new layout with state

 1. Add a new layout ID to layouts `enum`. See example [id/L.java][L].
 1. If you have some, add new placeholder IDs to placeholders `enum`. Add there a placeholder to inform about state (I've called it `STATUS`), also if you still have no such placeholder. See example [id/O.java][O].
 1. If you need to describe layout using declarative way, create an according `ui.xml` file. To mark the places where portlets will be placed (placeholders), it is required to use [Outlet][] widget. See example [layout/LayoutEdit.ui.xml][LayoutEdit.ux]. If you want to declare layout using standard OOP techninques, then you'll need to use [Outlet][] constructor. Remember that you'll need a placeholder to inform about layout status.
  1. Create a layout class, inherit it from [LayoutWithState][]. Pass a new layout ID to parent constructor. Also, pass the IDs of all placeholders owned by this layout. Pass a status placeholder ID as the last parameter. In the `prepareOutlet()` overriden method return the appropriate `Outlet` using the received ID. In `prepare(State)` method you may switch visibility of the inner widgets and/or perform something else using the passed state. Layout Widget must be initialized right inside the constructor. See example [layout/LayoutEdit.java][LayoutEdit].
 1. Register a layout in entry point using `Layouts.register()` method. See example [LayoutingDemo.java:47][LayoutingDemo.L47].

### 3. Making a page group (module)

 1. Add new group ID to groups `enum`. See example [id/G.java][G].
 1. Crate a group module, no difference from mvp4g module structure. See example [page/user/UserModule.java][UserModule]. Don't forget to add it to `ChildModules` annotation of main event bus. See example [page/main/MainEventBus.java][MainEventBus].
 1. Create an event bus for your group and extend it from [ChildEventBus][]. See example [page/user/UserEventBus.java][UserEventBus].
 1. Create a `HistoryConverter` and extend it from [PortalsHistoryConverter][]. Pass a new group ID to parent constructor. Method `convertFromUrl` is intended to call the required method from event bus depending on the received `PortalUrl`/`Portal` (use `P.by()` from 1.2.1). See example [page/user/history/UserHistoryConverter.java][UserHistoryConverter].
 1. Create your own [LayoutBuilder][] child to work with new event bus. `layout()` menthod inserts portlets into placeholders and returns `true`, if everything gone well. It also useful to use `P.by()` method here. See example [page/user/layout/UserLayoutBuilder.java][UserLayoutBuilder].
 1. Register that builder in entry point using `LayoutBuilders.register()` method. See example [LayoutingDemo.java:62][LayoutingDemo.L62].

### 4a. Making a complete page without state support

 1. Add a description of URL to access the page and specify the ID of layout, corresponding to the page, both in the element of pages `enum`. See example [id/P.java][P].
 1. Register a navigation event for your page in the event bus of the appropriate module. See methods `news()`, `edit()` or `show()` of [page/news/NewsEventBus.java][NewsEventBus] interface.
 1. Add a call of this event to the `convertFromUrl()` method of your module's `HistoryConverter`, in case if this portal was received. Create an `on...` handler method in converter for your event and return the parameters serialized to string, if there any exist — for your convenience you can use `url` property of the converter to construct and/or parse parameters. See example [page/news/history/NewsHistoryConverter][NewsHistoryConverter].
 1. Create a `Presenter` for your page and inherit it from [PortalPresenter][] class. `View` interface must extend [IsPortalView][] interface. You need to pass an ID of the current portal to the parent constructor. Presenters, like converters, has an `url` property, you may use to construct URLs and pass them into `view`. See example [page/news/presenter/NewsEditPresenter.java][NewsEditPresenter].
 1. Create a `View` for your page, inherit it from [Portal][] class. For every part of a page that planned to be inserted into a separate placeholder (a widget) you need to use a wrapping [Plug][] widget, regardless of whether you use `ui.xml` or not. The root of such `view` must be a [Plugs][] widget that allows to enumerate any number of `Plug` widgets inside it. See example [page/news/view/NewsEditView.java][NewsEditView] and [page/news/view/NewsEditView.ui.xml][NewsEditView.ux], in case of `NewsEditView`, the `infoPlug` (information block) and `savePlug` ("Save" button) widgets will be placed in different placeholders.
 1. On the basis of the layout you have chosen, add a page construction code to `LayoutBuilder` of your module. To ease the construction, register in the module's event bus a new `plug...` event (taking `Place` as a parameter) for every widget that will be placed into placeholder. All these events must go to presenter you've created at p.3 and call its `plug(Place, view.get...)` method that physically puts widgets to placeholders. To make this work, creare a methods handling these events in this presenter, that will call a `plug()` method for the appropriate part of `view`. Then, in `LayoutBuilder`, call the events you've created one by one, passing the required placeholders IDs in parameter. This way you manage a page structure independently from its content. See example [page/news/layout/NewsLayoutBuilder.java][NewsLayoutBuilder], [page/news/NewsEventBus.java][NewsEventBus] and [page/news/presenter/NewsEditPresenter.java][NewsEditPresenter], for `NEWS_EDIT` such methods are `plugItemEditor` и `plugSaveButton`.

### 4b. Making a page with portlets with no state support

 1. Add a description of URL to access the page and specify the ID of layout, corresponding to the page, both in the element of pages `enum`. See example [id/P.java][P].
 1. Register a navigation event for your page in the event bus of the appropriate module. See methods `news()`, `edit()` or `show()` of [page/news/NewsEventBus.java][NewsEventBus] interface.
 1. Add a call of this event to the `convertFromUrl()` method of your module's `HistoryConverter`, in case if this portal was received. Create an `on...` handler method in converter for your event and return the parameters serialized to string, if there any exist — for your convenience you can use `url` property of the converter to construct and/or parse parameters. See example [page/news/history/NewsHistoryConverter][NewsHistoryConverter].
 1. Create a `Presenter` for every portlet and inherit each one of them from [PortletPresenter][] class. Their `View` interface must extend [IsPortletView][] interface. Presenters, like converters, has an `url` property, you may use it to construct URLs and pass them into `view`. See example [[page/news/presenter/NewsInfoPresenter.java][NewsInfoPresenter], [page/news/presenter/NewsListPresenter.java][NewsListPresenter] and [page/news/presenter/UserCardPresenter.java][UserCardPresenter].
 1. `View` for each of this portlets must be inherited from [Portlet][] widget. The roor of these `view`s must be a [Plug][] widget. See example [page/news/view/NewsListView.java][NewsListView], [page/news/view/NewsListView.ui.xml][NewsListView.ux], [page/news/view/NewsInfoView.java][NewsInfoView], [page/news/view/NewsInfoView.ui.xml][NewsInfoView.ux] and [page/news/view/UserCardView.java][UserCardView].
  1. On the basis of the layout you have chosen, add a page construction code to `LayoutBuilder` of your module. To ease the construction, register in the module's event bus a new `plug...` event (taking `Place` as a parameter) for every widget that will be placed into placeholder. All these events must go to the appropriate presenters you've created at p.3 and call their inherited `plug(Place)` method that physically puts widgets to placeholders. In this case the `view` is unambiguous, you don't need to override something manually in presenters. Then, in `LayoutBuilder`, call the events you've created one by one, passing the required placeholders IDs in parameter. This way you manage a page structure independently from its content. See example [page/news/layout/NewsLayoutBuilder.java][NewsLayoutBuilder] and [page/news/NewsEventBus.java][NewsEventBus]. For `NEWS_SHOW` such methods are `plugNewsInfo`, `plugUserCard` and `plugTestWidget`. For `NEWS_LIST` such methods are `plugNewsList`, `plugUserCard` and `plugTestWidget`.

### 4c. Making a complete page with state support

 1. Add a description of URL to access the page and specify the ID of layout, corresponding to the page, both in the element of pages `enum`. See example [id/P.java][P].
 1. Register a navigation event for your page in the event bus of the appropriate module. See methods `users()`, `edit()` or `show()` of [page/user/UserEventBus.java][UserEventBus] interface.
 1. Add a call of this event to the `convertFromUrl()` method of your module's `HistoryConverter`, in case if this portal was received from history. Create an `on...` handler method in converter for your event and return the parameters serialized to string, if there any exist — for your convenience you can use `url` property of the converter to construct and/or parse parameters. See example [page/user/history/UserHistoryConverter][UserHistoryConverter].
 1. Create a `Presenter` for your page and inherit it from [StatedPortalPresenter][] class. `View` interface must extend [IsStatedPortalView][] interface. You need to pass an ID of the current portal to the parent constructor. To change view state, you just need to call a method of `state` property in the given moment (i.e. `state.noData()`, `state.loading()`, `state.noMatches()`, `state.gotData()`...). By default, all view supporting states are in a `LOADING` state. Presenters, like converters, has an `url` property, you may use to construct URLs and pass them into `view`. See example [page/user/presenter/UserEditPresenter.java][UserEditPresenter].
 1. Create a `View` for your page, inherit from [StatedPortal][] class. For every part of a page that planned to be inserted into a separate placeholder (a widget) you need to use a wrapping [Plug][] widget, regardless of whether you use `ui.xml` or not. The root of such `view` must be a [Plugs][] widget that allows to enumerate any number of `Plug` widgets inside it. For each of supported states of the view, (you are not required to create/register all of the possible states) you also need to create a separate `Plug`. After this, in `createView` method, using `register(State, Plug)` method, you need to bind them to the corresponding states. See example [page/user/view/UserEditView.java][UserEditView] and [page/user/view/UserEditView.ui.xml][UserEditView.ux], in case of `UserEditView`, the `infoPlug` (information block), `avatarPlug`, `agePlug` and `testPlug` (just a "Test Widget" phrase) widgets will be placed in different placeholders; `ifEmpty` and `whenLoading` blocks are correspond to `NO_DATA` and `LOADING` states, they are registered in `createView` method.
 1. On the basis of the layout you have chosen, add a page construction code to `LayoutBuilder` of your module. The `state` of the page comes to `layout` method as parameter, among with others, you may use it to build a different variants of the page, layout is ready for it. To ease the construction, register in the module's event bus a new `plug...` event (taking `Place` as a parameter) for every widget and for every supported state that will be placed into placeholder. All these events must go to presenter you've created at p.3 and call its `plug(Place, view.get...)` method that physically puts widgets to placeholders or one the `plugEmpty`/`plugNoMatches`/`plugLoading` methods for the states, the latter ones are already defined. To make this work, creare a methods handling these events in this presenter, that will call a `plug()` method for the appropriate part of `view`. Once again, for the event that plug states, you don't need to create anything in presenters. Then, in `LayoutBuilder`, call the events you've created one by one, passing the required placeholders IDs in parameter (`STATUS` placeholder for any state-widget). This way you manage a page structure and state independently from its content. See example [page/user/layout/UserLayoutBuilder.java][UserLayoutBuilder] ветка `USER_EDIT`, [page/user/UserEventBus.java][UserEventBus] и [page/user/presenter/UserEditPresenter.java][UserEditPresenter], for `USER_EDIT` such methods are `plugInfoEditor`, `plugAgeEditor`, `plugAvatarEditor` are `plugTestWidget`.

### 4d. Making a complete page containing portlets, where all or some of them have state support

 1. Добавьте описание доступа по URL к странице и укажите идентификатор соответствующего ей лэйаута в `enum` для страниц. см. в примере [id/P.java][P].
 1. Зарегистрируйте навигационное событие для вашей страницы в шине событий соответствующего модуля. см. в примере методы `users()`, `edit()` и `show()` интерфейса [page/user/UserEventBus.java][UserEventBus].
 1. В метод `convertFromUrl()` у `HistoryConverter` вашего модуля добавьте вызов этого события, если по истории был получен ваш портал. Создайте в конвертере метод `on...` для вашего события и возвратите отконвертированные параметры, если они есть — для удобства можно использовать проперти `url` конвертера. см. в примере [page/user/history/UserHistoryConverter][UserHistoryConverter].
 1. Создайте для каждого портлета свой `Presenter` и отнаследуйте каждый от класса [StatedPortletPresenter][] (или от [PortletPresenter][], если у виджета не должно быть состояний). Интерфейс `View` у них должен расширять интерфейс [IsStatedPortletView][] (или [IsPortletView][], если у виджета нет состояний). Для изменения состояния вида портлетов  достаточно вызвать в необходимый момент подходящий метод у проперти `state` (например, `state.noData()`, `state.loading()`, `state.noMatches()`, `state.gotData()`...). По умолчанию виды с состояниями имеют состояние `LOADING`. У презентеров, как и у конвертера, также есть проперти `url`, вы можете использовать его для построения URL и передаче их во `view`. см. в примере [page/user/presenter/UserAvatarPresenter.java][UserAvatarPresenter], [page/user/presenter/UserDetailsPresenter.java][UserDetailsPresenter], [page/user/presenter/UserInfoPresenter.java][UserInfoPresenter] и [page/user/UserListPresenter][UserListPresenter].
 1. `View` каждого из портлетов должен наследоваться от виджета [StatedPortlet][] (или [Portlet][], если виджет не имеет состояний). Корневым элементом для `view` с состоянием должен быть виджет [Plugs][], внутри которого должны находиться виджеты [Plug][] с основным и побочными необходимыми вам состояниями. Для того, чтобы связать виджеты состояний с соответствующими [Plug][], используйте метод `register(Plug, State)` в реализации `createView()`. Для `view` без состояний, корневым элементом должен быть [Plug][], здесь всё проще — он и будет главным видом. см. в примере виды с состояниями: [page/user/view/UserAvatarView.java][UserAvatarView], [page/user/view/UserAvatarView.ui.xml][UserAvatarView.ux], [page/user/view/UserDetailsView.java][UserDetailsView], [page/user/view/UserDetailsView.ui.xml][UserDetailsView.ux], [page/user/view/UserInfoView.java][UserInfoView], [page/user/view/UserInfoView.ui.xml][UserInfoView.ux], виды без состояний: [page/news/view/NewsInfoView.java][NewsInfoView], [page/news/view/NewsInfoView.ui.xml][NewsInfoView.ux]
  1. На основе выбранного вами лэйаута добавьте сборку страницы в `LayoutBuilder` вашего модуля. Для сборки зарегистрируйте в шине событий модуля по одному новому событию `plug...` (принимающему в параметре `Place`) для каждого виджета, который будет вставляться в плейсхолдер. Эти события должны уходить в презентеры, созданные в п.3 и вызывать в них отнаследованный метод `plug(Place)`, физически вставляя виджеты в плейсхолдеры. Поскольку в данном случае `view` однозначен, переопределять в презентерах ничего не нужно. Затем, в `LayoutBuilder`-е, вызовите созданные события поочерёдно, передавая в параметре соответствующие идентификаторы плейсхолдеров. см. в примере [page/user/layout/UserLayoutBuilder.java][UserLayoutBuilder] и [page/user/UserEventBus.java][UserEventBus]. Для `USER_SHOW` это методы `plugUserInfo`, `plugUserAvatar` и `plugUserDetails`. Для `USERS_LIST` это методы `plugUserInfo`, `plugUserAvatar` и `plugUsersList`.

[[Contents]](./index-ru.html)




[P]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/tree/master/src/name/shamansir/mvplayout/client/id/P.java#files
[G]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/tree/master/src/name/shamansir/mvplayout/client/id/G.java#files
[L]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/tree/master/src/name/shamansir/mvplayout/client/id/L.java#files
[O]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/tree/master/src/name/shamansir/mvplayout/client/id/O.java#files

[AMainPresenter]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/lib/mvp/AMainPresenter.java#files
[AMainView]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/lib/mvp/AMainView.java#files). см. [page/main/view/MainView.java](https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/client/page/main/view/MainView.java#files
[IsMainEventBus]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/lib/mvp/IsMainEventBus.java#files
[ChildEventBus]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/tree/master/src/name/shamansir/mvplayout/lib/mvp/ChildEventBus.java#files
[PortalsHistoryConverter]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/lib/mvp/PortalsHistoryConverter.java#files
[LayoutBuilder]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/lib/ui/LayoutBuilder.java#files

[PortalPresenter]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/lib/mvp/PortalPresenter.java#files
[IsPortalView]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/lib/mvp/IsPortalView.java#files
[Portal]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/lib/ui/widget/Portal.java#files
[PortletPresenter]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/lib/mvp/PortletPresenter.java#files
[IsPortletView]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/lib/mvp/IsPortletView.java#files
[Portlet]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/lib/ui/widget/Portlet.java#files
[StatedPortalPresenter]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/lib/mvp/state/StatedPortalPresenter.java#files
[StatedPortletPresenter]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/lib/mvp/state/StatedPortletPresenter.java#files
[IsStatedPortalView]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/lib/mvp/state/IsStatedPortalView.java#files
[StatedPortal]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/lib/ui/widget/StatedPortal.java#files
[IsStatedPortletView]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/lib/mvp/state/IsStatedPortletView.java#files
[StatedPortlet]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/lib/ui/widget/StatedPortlet.java#files
[Plug]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/lib/ui/widget/Plug.java#files
[Plugs]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/lib/ui/widget/Plugs.java#files

[LayoutingDemo]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/client/LayoutingDemo.java#files
[LayoutingDemo.L47]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/client/LayoutingDemo.java#L47
[LayoutingDemo.L62]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/client/LayoutingDemo.java#L62

[MainEventBus]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/client/page/main/MainEventBus.java#files
[UserEventBus]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/tree/master/src/name/shamansir/mvplayout/client/page/user/UserEventBus.java#files
[NewsEventBus]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/client/page/news/NewsEventBus.java#files

[UserHistoryConverter]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/client/page/user/history/UserHistoryConverter.java#files
[UserLayoutBuilder]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/client/page/user/layout/UserLayoutBuilder.java#files
[NewsHistoryConverter]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/client/page/news/history/NewsHistoryConverter.java#files
[NewsLayoutBuilder]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/client/page/news/layout/NewsLayoutBuilder.java#files

[MainModule]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/client/page/main/MainModule.java#files
[UserModule]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/tree/master/src/name/shamansir/mvplayout/client/page/user/UserModule.java#files

[MainPresenter]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/client/page/main/presenter/MainPresenter.java#files
[MainView]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/client/page/main/view/MainView.java#files

[Layout]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/lib/ui/widget/Layout.java#files
[LayoutWithState]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/lib/ui/state/LayoutWithState.java#files
[Outlet]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/lib/ui/widget/Outlet.java#files

[LayoutItem]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/client/layout/LayoutItem.java#files
[LayoutEdit]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/client/layout/LayoutEdit.java#files
[LayoutItem.ux]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/client/layout/LayoutItem.ui.xml
[LayoutEdit.ux]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/client/layout/LayoutEdit.ui.xml

[NewsEditPresenter]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/client/page/news/presenter/NewsEditPresenter.java#files
[NewsEditView]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/client/page/news/view/NewsEditView.java#files
[NewsEditView.ux]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/client/page/news/view/NewsEditView.ui.xml
[NewsInfoPresenter]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/client/page/news/presenter/NewsInfoPresenter.java#files
[NewsInfoView]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/client/page/news/view/NewsInfoView.java#files
[NewsInfoView.ux]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/client/page/news/view/NewsInfoView.ui.xml#files
[NewsListPresenter]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/client/page/news/presenter/NewsListPresenter.java#files
[NewsListView]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/client/page/news/view/NewsListView.java#files
[NewsListView.ux]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/client/page/news/view/NewsListView.ui.xml#files
[UserCardPresenter]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/client/page/news/presenter/UserCardPresenter.java#files
[UserCardView]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/client/page/news/view/UserCardView.java#files

[UserEditPresenter]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/client/page/user/presenter/UserEditPresenter.java#files
[UserEditView]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/client/page/user/view/UserEditView.java#files
[UserEditView.ux]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/client/page/user/view/UserEditView.ui.xml#files
[UserAvatarPresenter]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/client/page/user/presenter/UserAvatarPresenter.java#files
[UserDetailsPresenter]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/client/page/user/presenter/UserDetailsPresenter.java#files
[UserInfoPresenter]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/client/page/user/presenter/UserInfoPresenter.java#files
[UserListPresenter]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/client/page/user/presenter/UserListPresenter.java#files
[UserAvatarView]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/client/page/user/view/UserAvatarView.java#files
[UserAvatarView.ux]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/client/page/user/view/UserAvatarView.ui.xml#files
[UserDetailsView]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/client/page/user/view/UserDetailsView.java#files
[UserDetailsView.ux]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/client/page/user/view/UserDetailsView.ui.xml#files
[UserInfoView]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/client/page/user/view/UserInfoView.java#files
[UserInfoView.ux]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/client/page/user/view/UserInfoView.ui.xml#files

