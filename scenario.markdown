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
 1. If you have one, add new placeholder IDs to placeholders `enum`. See example [id/O.java][O].
 1. If you need to describe layout declarative way, create an according `ui.xml` file. To mark the places where portlets will be placed (placeholders), it is required to use [Outlet][] widget. See example [layout/LayoutItem.ui.xml][LayoutItem.ux]. If you want to declare layout using standard OOP techninques, then you'll need to use [Outlet][] constructor.
 1. Create a layout class, inherit it from [Layout][]. Pass a new layout ID to parent constructor. Also, pass the IDs of all placeholders owned by this layout. In the `prepareOutlet()` overriden method return the appropriate `Outlet` using the received ID. Widget must be initiated directly inside of constructor. See example [layout/LayoutItem.java][LayoutItem].
 1. Register a layout in entry point using `Layouts.register()` method. See example [LayoutingDemo.java:47][LayoutingDemo.L47].

### 2b. Making a new layout with state

 1. Добавьте идентификатор нового лэйаута в `enum` лэйатуов. см. в примере [id/L.java][L].
 1. Если нужно, добавьте идентификаторы новых плейсходеров в `enum` плейсхолдеров. Туда же добавьте плейсхолдер для информирования о состоянии (я назвал его `STATUS`), если вы его ещё не завели. см. в примере [id/O.java][O].
 1. Если вы хотите описать лэйаут декларативно, создайте соответствующий `ui.xml`. В этом `ui.xml` для обозначения мест, куда будут вставляться портлеты (плейсхолдеров) потребуется использовать виджет [Outlet][]. Для места, куда будет вставляться сообщение о состоянии тоже подготовьте плэйсхолдер. см. [layout/LayoutEdit.ui.xml][LayoutEdit.ux] в примере. Можно описывать лэйаут и не декларативно, тогда придётся создавать плэйсхолдеры используя конструктор [Outlet][].
 1. Создайте класс лэйаута, отнаследовав его от [LayoutWithState][]. В родительский конструктор передайте новый идентификатор лэйаута и идентификаторы всех плейсходеров, принадлежащих этому лэйауту. Отдельно (последним параметром) передайте идентификатор плэйсхолдера, предназначенного для сообщения о состоянии. В переопределённом методе `prepareOutlet()` возвратите нужный `Outlet` по переданному идентификатору. В методе `prepare(State)` вы можете по переданному состоянию переключать видимость тех или иных виджетов внутри лэйаута и/или делать что-то ещё, если нужно. Виджет должен инициироваться прямо в конструкторе. см. [layout/LayoutEdit.java][LayoutEdit] в примере.
 1. Зарегистрируйте лэйаут в точке входа через метод `Layouts.register()`. см. [LayoutingDemo.java:47][LayoutingDemo.L47] в примере.

### 3. Making a page group (module)

 1. Добавьте идентификатор новой группы в `enum` для групп. см. в примере [id/G.java][G].
 1. Создайте модуль для группы, ничем не отличающийся от модуля mvp4g. см. в примере [page/user/UserModule.java][UserModule]. Не забудьте добавить его в `ChildModules` главной шины событий. см. в примере [page/main/MainEventBus.java][MainEventBus].
 1. Создайте шину событий для вашей группы и отнаследуйте её от [ChildEventBus][]. см. в примере [page/user/UserEventBus.java][UserEventBus].
 1. Создайте `HistoryConverter` и отнаследуйте его от [PortalsHistoryConverter][]. Передайте в родительский конструктор идентификатор группы. Метод `convertFromUrl` предназначен для того, чтобы по полученным `PortalUrl`/`Portal` (используйте метод `P.by()` из пункта 1.2.1) вызвать нужный метод в шине событий. см. в примере [page/user/history/UserHistoryConverter.java][UserHistoryConverter].
 1. Создайте своего наследника [LayoutBuilder][] для соответствующей шины событий. Метод `layout()` вставляет портлеты в плейсходеры и вовращает `true`, если всё прошло удачно. здесь также удобно использовать метод `P.by()`. см. в примере [page/user/layout/UserLayoutBuilder.java][UserLayoutBuilder].
 1. Зарегистрируйте билдер в точке входа через метод `LayoutBuilders.register()`. см. [LayoutingDemo.java:62][LayoutingDemo.L62] в примере.

### 4a. Making a complete page without state support

 1. Добавьте описание доступа по URL к странице и укажите идентификатор соответствующего ей лэйаута в `enum` для страниц. см. в примере [id/P.java][P].
 1. Зарегистрируйте навигационное событие для вашей страницы в шине событий соответствующего модуля. см. в примере методы `news()`, `edit()` и `show()` в интерфейсе [page/news/NewsEventBus.java][NewsEventBus].
 1. В метод `convertFromUrl()` у `HistoryConverter` вашего модуля добавьте вызов этого события, если по истории был получен ваш портал. Создайте в конвертере метод `on...` для вашего события и возвратите отконвертированные параметры, если они есть — для удобства можно использовать проперти `url` конвертера. см. в примере [page/news/history/NewsHistoryConverter][NewsHistoryConverter].
 1. Создайте `Presenter` вашей страницы и отнаследуйте его от класса [PortalPresenter][]. Интерфейс `View` должен расширять интерфейс [IsPortalView][]. В конструктор нужно передать идентификатор портала, которому соответствует презентер. У презентера, как и у конвертера, также есть проперти `url`, вы можете использовать его для построения URL и передаче их во `view`. см. в примере [page/news/presenter/NewsEditPresenter.java][NewsEditPresenter].
 1. Создайте `View` вашей страницы, отнаследовав его от класса [Portal][]. Для каждой части страницы (виджета), которая будет вставлена в отдельный плейсходер, нужно использовать оборачивающий виджет [Plug][], независимо от того, используете вы `ui.xml` или нет. Корневым элементом для `view` должен быть виджет [Plugs][], который позволяет перечислить внутри нёго несколько виджетов `Plug`. см. в примере [page/news/view/NewsEditView.java][NewsEditView] и [page/news/view/NewsEditView.ui.xml][NewsEditView.ux], в случае `NewsEditView` в разные плейсхолдеры будут вставлены `infoPlug` (блок с информацией) и `savePlug` (кнопка "Save").
 1. На основе выбранного вами лэйаута добавьте сборку страницы в `LayoutBuilder` вашего модуля. Для сборки зарегистрируйте в шине событий модуля по одному новому событию `plug...` (принимающему в параметре `Place`) для каждого виджета, который будет вставляться в плейсхолдер. Эти события должны уходить в презентер, созданный в п.3 и вызывать в нём метод `plug(Place, view.get...)`, физически вставляя виджеты в плейсхолдеры. Для этого создайте в презентере методы, перехватывающие эти события и вызывающие `plug()` с нужной частью `view`. Затем, в `LayoutBuilder`-е, вызовите созданные события поочерёдно, передавая в параметре соответствующие идентификаторы плейсхолдеров. см. в примере [page/news/layout/NewsLayoutBuilder.java][NewsLayoutBuilder], [page/news/NewsEventBus.java][NewsEventBus] и [page/news/presenter/NewsEditPresenter.java][NewsEditPresenter], для `NEWS_EDIT` это методы `plugItemEditor` и `plugSaveButton`.

### 4b. Making a page with portlets with no state support

 1. Добавьте описание доступа по URL к странице и укажите идентификатор соответствующего ей лэйаута в `enum` для страниц. см. в примере [id/P.java][P].
 1. Зарегистрируйте навигационное событие для вашей страницы в шине событий соответствующего модуля. см. в примере методы `news()`, `edit()` и `show()` интерфейса [page/news/NewsEventBus.java][NewsEventBus].
 1. В метод `convertFromUrl()` у `HistoryConverter` вашего модуля добавьте вызов этого события, если по истории был получен ваш портал. Создайте в конвертере метод `on...` для вашего события и возвратите отконвертированные параметры, если они есть — для удобства можно использовать проперти `url` конвертера. см. в примере [page/news/history/NewsHistoryConverter][NewsHistoryConverter].
 1. Создайте для каждого портлета свой `Presenter` и отнаследуйте каждый от класса [PortletPresenter][]. Интерфейс `View` у них должен расширять интерфейс [IsPortletView][]. У презентера, как и у конвертера, также есть проперти `url`, вы можете использовать его для построения URL и передаче их во `view`. см. в примере [page/news/presenter/NewsInfoPresenter.java][NewsInfoPresenter], [page/news/presenter/NewsListPresenter.java][NewsListPresenter] и [page/news/presenter/UserCardPresenter.java][UserCardPresenter].
 1. `View` каждого из портлетов должен наследоваться от виджета [Portlet][]. Корневым элементом для этих `view` должен быть виджет [Plug][]. см. в примере [page/news/view/NewsListView.java][NewsListView], [page/news/view/NewsListView.ui.xml][NewsListView.ux], [page/news/view/NewsInfoView.java][NewsInfoView], [page/news/view/NewsInfoView.ui.xml][NewsInfoView.ux] и [page/news/view/UserCardView.java][UserCardView].
  1. На основе выбранного вами лэйаута добавьте сборку страницы в `LayoutBuilder` вашего модуля. Для сборки зарегистрируйте в шине событий модуля по одному новому событию `plug...` (принимающему в параметре `Place`) для каждого виджета, который будет вставляться в плейсхолдер. Эти события должны уходить в презентеры, созданные в п.3 и вызывать в них отнаследованный метод `plug(Place)`, физически вставляя виджеты в плейсхолдеры. Поскольку в данном случае `view` однозначен, переопределять в презентерах ничего не нужно. Затем, в `LayoutBuilder`-е, вызовите созданные события поочерёдно, передавая в параметре соответствующие идентификаторы плейсхолдеров. см. в примере [page/news/layout/NewsLayoutBuilder.java][NewsLayoutBuilder] и [page/news/NewsEventBus.java][NewsEventBus]. Для `NEWS_SHOW` это методы `plugNewsInfo`, `plugUserCard` и `plugTestWidget`. Для `NEWS_LIST` это методы `plugNewsList`, `plugUserCard` и `plugTestWidget`.

### 4c. Making a complete page with state support

 1. Добавьте описание доступа по URL к странице и укажите идентификатор соответствующего ей лэйаута в `enum` для страниц. см. в примере [id/P.java][P].
 1. Зарегистрируйте навигационное событие для вашей страницы в шине событий соответствующего модуля. см. в примере методы `users()`, `edit()` и `show()` интерфейса [page/user/UserEventBus.java][UserEventBus].
 1. В метод `convertFromUrl()` у `HistoryConverter` вашего модуля добавьте вызов этого события, если по истории был получен ваш портал. Создайте в конвертере метод `on...` для вашего события и возвратите отконвертированные параметры, если они есть — для удобства можно использовать проперти `url` конвертера. см. в примере [page/user/history/UserHistoryConverter][UserHistoryConverter].
 1. Создайте `Presenter` вашей страницы и отнаследуйте его от класса [StatedPortalPresenter][]. Интерфейс `View` должен расширять интерфейс [IsStatedPortalView][]. В конструктор нужно передать идентификатор портала, которому соответствует презентер. Для изменения состояния вида достаточно вызвать в необходимый момент подходящий метод у проперти `state` (например, `state.noData()`, `state.loading()`, `state.noMatches()`, `state.gotData()`...). По умолчанию виды с состояниями имеют состояние `LOADING`. У презентера, как и у конвертера, также есть проперти `url`, вы можете использовать его для построения URL и передаче их во `view`. см. в примере [page/user/presenter/UserEditPresenter.java][UserEditPresenter].
 1. Создайте `View` вашей страницы, отнаследовав его от класса [StatedPortal][]. Для каждой части страницы (виджета), которая будет вставлена в отдельный плейсходер, нужно использовать оборачивающий виджет [Plug][], независимо от того, используете вы `ui.xml` или нет. Корневым элементом для `view` должен быть виджет [Plugs][], который позволяет перечислить внутри нёго несколько виджетов `Plug`. Для каждого из необходимых состояний вида (можно регистрировать/создавать не все) также надо создать отдельный `Plug`. После этого, используя метод `register(State, Plug)`, их нужно связать с соответствующим им состоянием в методе `createView` см. в примере [page/user/view/UserEditView.java][UserEditView] и [page/user/view/UserEditView.ui.xml][UserEditView.ux], в случае `UserEditView` в разные плейсхолдеры будут вставлены `infoPlug` (блок с информацией), `avatarPlug`, `agePlug` и `testPlug` (просто фраза "Test Widget"); состояниям `NO_DATA` и `LOADING` соответствуют блоки `ifEmpty` и `whenLoading`, они регистрируются в методе `createView`
 1. На основе выбранного вами лэйаута добавьте сборку страницы в `LayoutBuilder` вашего модуля. В метод `layout` кроме всего прочего приходит состояние страницы `state`, его можно использовать для построения различных вариантов страницы, лэйаут уже подготовлен. Для сборки зарегистрируйте в шине событий модуля по одному новому событию `plug...` (принимающему в параметре `Place`) для каждого виджета и для каждого нужного вам состояния, которые будет вставляться в плейсхолдеры. Эти события должны уходить в презентер, созданный в п.3 и вызывать в нём метод `plug(Place, view.get...)`, физически вставляя виджеты в плейсхолдеры или уже определённые методы `plugEmpty`/`plugNoMatches`/`plugLoading` для состояний. Для этого создайте в презентере методы, перехватывающие события виджетов и вызывающие `plug()` с нужной частью `view`. Для событий "подключения" состояний ничего создавать не нужно. Затем, в `LayoutBuilder`-е, вызовите созданные события поочерёдно, передавая в параметре соответствующие идентификаторы плейсхолдеров (плейсхолдер состояния для виджета состояния). см. в примере [page/user/layout/UserLayoutBuilder.java][UserLayoutBuilder] ветка `USER_EDIT`, [page/user/UserEventBus.java][UserEventBus] и [page/user/presenter/UserEditPresenter.java][UserEditPresenter], для `USER_EDIT` это методы `plugInfoEditor`, `plugAgeEditor`, `plugAvatarEditor` и `plugTestWidget`.

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

