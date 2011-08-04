---
layout: main-ru
title: Сценарии
---

## Как этим пользоваться?

По сути, в пакете [`name.shamansir.mvplayout.lib`](https://github.com/shamansir/gwt-mvp4g-layouting-demo/tree/master/src/name/shamansir/mvplayout/lib) лежит код, который вполне можно использовать как библиотеку. Я пока не тороплюсь его заворачивать в `jar`, но потенциально он вполне надёжен для использования — сложно-составной пример работает стабильно, а ядро этой идеи (правда, устаревшая и невылизанная версия — актуальную встроить пока нет возможности) работает в [нашем проекте](http://experika.com).

Поясню, как пользоваться всем этим кодом, если вы решили разработать на его основе проект. Ниже представлены одновременно последовательные и отдельные сценарии — выполнив их по очереди (на некоторых переходя в цикл "повторять несколько раз пока необходимо") можно написать всё приложение целиком, но и после этого каждое из действий можно выполнять отдельно, по мере потребности изменений в приложении.

Словами описать можно отнюдь не всё, иногда удобнее взглянуть по ссылкам на примеры в приведённой очередности. Не проходите мимо этой возможности :).

### 1. Каркас приложения

 1. Продумать систему навигации вашего проекта, желательно чтобы она ложилась на схему `тип-объекта/действие`.
 1. Создать четыре `enum`-файла. Эти четыре `enum`-файла — главная и единственная конфигурация структуры страниц и лэйатуов в проекте (они не должны сразу же отражать действительность, но что-то потенциально реальное в них всё же должно быть):
   * Один (в примере: [id/P.java][P]) для перечисления _страниц_, описания _URL_-ов (группа — тип объекта, а событие — действие в упомянутой схеме `тип-объекта/действие`) и связывания их с _лэйаутами_. Для каждой страницы автоматически создаётся ID по методу `id()` (в примере в качестве ID везде берётся `name()` элемента из `enum`, но это может быть что угодно) и привязывается к созданному инстансу класса `Portal`: для этого `enum` должен имплементить интерфейс `PortalId`. Также вам понадобится быстрый способ получить значение `enum` из идентификатора `Portal`: в примере это метод `by(Portal page)`, для этого инстансы `Portal` хранятся внутри.
   * Второй (в примере: [id/G.java][G]) для перечисления _групп_. Должен имплементить интерфейс `Group`: возвращать уникальный ID в методе `id()`.
   * Третий (в примере: [id/L.java][L]) для перечисления _лэйаутов_. Должен имплементить интерфейс `LayoutId`: возвращать уникальный ID в методе `id()`.
   * Четвёртый (в примере: [id/O.java][O]) для перечисления _плэйсхолдеров_. Должен имплементить интерфейс `Place`: возвращать уникальный ID в методе `id()`.
 1. Создать `EntryPoint` вашего проекта и зарегистрировать в нём описанные порталы, причём выполнить это до запуска модуля mvp4g. В дальнейшем тут же нужно будет регистрировать новые лэйауты и лэйаут-билдеры, но в данный момент это необязательно. см. точку входа [LayoutingDemo.java][LayoutingDemo] в примере.
 1. Создать `Presenter` главной страницы и отнаследовать его от [AMainPresenter][]. Ничего больше особенного здесь делать не нужно. см. [page/main/presenter/MainPresenter.java][MainPresenter] в примере.
 1. Создать `View` главной страницы и отнаследовать его от [AMainView][] (см. [page/main/view/MainView.java][MainView] в примере). Методы, которые требуется определить:
   * Метод `getLayoutHolder()` должен возвращать панель, в которую будет "вставляться" лэйаут.
   * Метод `getPortalHolder()` может возвращать панель, оборачивающую предыдущую, а может ту же самую. Этот метод применяется только для назначения CSS-классов, классы портала и лэйаута будут назначены одному элементу в последнем случае.
   * Метод `getScrollable()` может возвращать область, которая будет скроллиться внутри страницы. Если такой области нет, метод может возвращать `null`, но тогда не будет работать подписка на скролл-события страницы по шине событий (`addPageScrollHandler`).
 1. Создать главную шину событий, имплеменитирующую [IsMainEventBus][]. В главной шине нужно переопределить все методы `IsMainEventBus` и перенаправить их в презентер главной страницы . Также нужно создать главный модуль, ничем не отличающийся от главного модуля mvp4g. см. [page/main/MainEventBus.java][MainEventBus] и [page/main/MainModule.java][MainModule] в примере.

### 2a. Создание лэйаута

 1. Добавьте идентификатор нового лэйаута в `enum` лэйатуов. см. в примере [id/L.java][L].
 1. Если нужно, добавьте идентификаторы новых плейсходеров в `enum` плейсхолдеров. см. в примере [id/O.java][O].
 1. Если вы хотите описать лэйаут декларативно, создайте соответствующий `ui.xml`. В этом `ui.xml` для обозначения мест, куда будут вставляться портлеты (плейсхолдеров) потребуется использовать виджет [Outlet][]. см. [layout/LayoutItem.ui.xml][LayoutItem.ux] в примере. Можно описывать лэйаут и не декларативно, тогда придётся создавать плэйсхолдеры используя конструктор [Outlet][]. Не забудьте, что вам нужен плейсходер для информировании о статусе.
 1. Создайте класс лэйаута, отнаследовав его от [Layout][]. В родительский конструктор передайте новый идентификатор лэйаута и идентификаторы всех плейсходеров, принадлежащих этому лэйауту. В переопределённом методе `prepareOutlet()` возвратите нужный `Outlet` по переданному идентификатору. Виджет должен инициироваться прямо в конструкторе. см. [layout/LayoutItem.java][LayoutItem] в примере.
 1. Зарегистрируйте лэйаут в точке входа через метод `Layouts.register()`. см. [LayoutingDemo.java:47][LayoutingDemo.L47] в примере.

### 2b. Создание лэйаута с состояниями

 1. Добавьте идентификатор нового лэйаута в `enum` лэйатуов. см. в примере [id/L.java][L].
 1. Если нужно, добавьте идентификаторы новых плейсходеров в `enum` плейсхолдеров. Туда же добавьте плейсхолдер для информирования о состоянии (я назвал его `STATUS`), если вы его ещё не завели. см. в примере [id/O.java][O].
 1. Если вы хотите описать лэйаут декларативно, создайте соответствующий `ui.xml`. В этом `ui.xml` для обозначения мест, куда будут вставляться портлеты (плейсхолдеров) потребуется использовать виджет [Outlet][]. Для места, куда будет вставляться сообщение о состоянии тоже подготовьте плэйсхолдер. см. [layout/LayoutEdit.ui.xml][LayoutEdit.ux] в примере. Можно описывать лэйаут и не декларативно, тогда придётся создавать плэйсхолдеры используя конструктор [Outlet][].
 1. Создайте класс лэйаута, отнаследовав его от [LayoutWithState][]. В родительский конструктор передайте новый идентификатор лэйаута и идентификаторы всех плейсходеров, принадлежащих этому лэйауту. Отдельно (последним параметром) передайте идентификатор плэйсхолдера, предназначенного для сообщения о состоянии. В переопределённом методе `prepareOutlet()` возвратите нужный `Outlet` по переданному идентификатору. В методе `prepare(State)` вы можете по переданному состоянию переключать видимость тех или иных виджетов внутри лэйаута и/или делать что-то ещё, если нужно. Виджет должен инициироваться прямо в конструкторе. см. [layout/LayoutEdit.java][LayoutEdit] в примере.
 1. Зарегистрируйте лэйаут в точке входа через метод `Layouts.register()`. см. [LayoutingDemo.java:47][LayoutingDemo.L47] в примере.

### 3. Создание группы страниц (модуля)

 1. Добавьте идентификатор новой группы в `enum` для групп. см. в примере [id/G.java][G].
 1. Создайте модуль для группы, ничем не отличающийся от модуля mvp4g. см. в примере [page/user/UserModule.java][UserModule]. Не забудьте добавить его в `ChildModules` главной шины событий. см. в примере [page/main/MainEventBus.java][MainEventBus].
 1. Создайте шину событий для вашей группы и отнаследуйте её от [ChildEventBus][]. см. в примере [page/user/UserEventBus.java][UserEventBus].
 1. Создайте `HistoryConverter` и отнаследуйте его от [PortalsHistoryConverter][]. Передайте в родительский конструктор идентификатор группы. Метод `convertFromUrl` предназначен для того, чтобы по полученным `PortalUrl`/`Portal` (используйте метод `P.by()` из пункта 1.2.1) вызвать нужный метод в шине событий. см. в примере [page/user/history/UserHistoryConverter.java][UserHistoryConverter].
 1. Создайте своего наследника [LayoutBuilder][] для соответствующей шины событий. Метод `layout()` вставляет портлеты в плейсходеры и вовращает `true`, если всё прошло удачно. здесь также удобно использовать метод `P.by()`. см. в примере [page/user/layout/UserLayoutBuilder.java][UserLayoutBuilder].
  1. Зарегистрируйте билдер в точке входа через метод `LayoutBuilders.register()`. см. [LayoutingDemo.java:62][LayoutingDemo.L62] в примере.

### 4a. Создание цельной страницы без поддержки состояний

 1. Добавьте описание доступа по URL к странице и укажите идентификатор соответствующего ей лэйаута в `enum` для страниц. см. в примере [id/P.java][P].
 1. Зарегистрируйте навигационное событие для вашей страницы в шине событий соответствующего модуля. см. в примере методы `news()`, `edit()` и `show()` в интерфейсе [page/news/NewsEventBus.java][NewsEventBus].
 1. В метод `convertFromUrl()` у `HistoryConverter` вашего модуля добавьте вызов этого события, если по истории был получен ваш портал. Создайте в конвертере метод `on...` для вашего события и возвратите отконвертированные параметры, если они есть — для удобства можно использовать проперти `url` конвертера. см. в примере [page/news/history/NewsHistoryConverter][NewsHistoryConverter].
 1. Создайте `Presenter` вашей страницы и отнаследуйте его от класса [PortalPresenter][]. Интерфейс `View` должен расширять интерфейс [IsPortalView][]. В конструктор нужно передать идентификатор портала, которому соответствует презентер. У презентера, как и у конвертера, также есть проперти `url`, вы можете использовать его для построения URL и передаче их во `view`. см. в примере [page/news/presenter/NewsEditPresenter.java][NewsEditPresenter].
 1. Создайте `View` вашей страницы, отнаследовав его от класса [Portal][]. Для каждой части страницы (виджета), которая будет вставлена в отдельный плейсходер, нужно использовать оборачивающий виджет [Plug][], независимо от того, используете вы `ui.xml` или нет. Корневым элементом для `view` должен быть виджет [Plugs][], который позволяет перечислить внутри нёго несколько виджетов `Plug`. см. в примере [page/news/view/NewsEditView.java][NewsEditView] и [page/news/view/NewsEditView.ui.xml][NewsEditView.ux], в случае `NewsEditView` в разные плейсхолдеры будут вставлены `infoPlug` (блок с информацией) и `savePlug` (кнопка "Save").
 1. На основе выбранного вами лэйаута добавьте сборку страницы в `LayoutBuilder` вашего модуля. Для сборки зарегистрируйте в шине событий модуля по одному новому событию `plug...` (принимающему в параметре `Place`) для каждого виджета, который будет вставляться в плейсхолдер. Эти события должны уходить в презентер, созданный в п.3 и вызывать в нём метод `plug(Place, view.get...)`, физически вставляя виджеты в плейсхолдеры. Для этого создайте в презентере методы, перехватывающие эти события и вызывающие `plug()` с нужной частью `view`. Затем, в `LayoutBuilder`-е, вызовите созданные события поочерёдно, передавая в параметре соответствующие идентификаторы плейсхолдеров. см. в примере [page/news/layout/NewsLayoutBuilder.java][NewsLayoutBuilder], [page/news/NewsEventBus.java][NewsEventBus] и [page/news/presenter/NewsEditPresenter.java][NewsEditPresenter], для `NEWS_EDIT` это методы `plugItemEditor` и `plugSaveButton`.

### 4b. Создание страницы с портлетами без поддержки состояний

 1. Добавьте описание доступа по URL к странице и укажите идентификатор соответствующего ей лэйаута в `enum` для страниц. см. в примере [id/P.java][P].
 1. Зарегистрируйте навигационное событие для вашей страницы в шине событий соответствующего модуля. см. в примере методы `news()`, `edit()` и `show()` интерфейса [page/news/NewsEventBus.java][NewsEventBus].
 1. В метод `convertFromUrl()` у `HistoryConverter` вашего модуля добавьте вызов этого события, если по истории был получен ваш портал. Создайте в конвертере метод `on...` для вашего события и возвратите отконвертированные параметры, если они есть — для удобства можно использовать проперти `url` конвертера. см. в примере [page/news/history/NewsHistoryConverter][NewsHistoryConverter].
 1. Создайте для каждого портлета свой `Presenter` и отнаследуйте каждый от класса [PortletPresenter][]. Интерфейс `View` у них должен расширять интерфейс [IsPortletView][]. У презентера, как и у конвертера, также есть проперти `url`, вы можете использовать его для построения URL и передаче их во `view`. см. в примере [page/news/presenter/NewsInfoPresenter.java][NewsInfoPresenter], [page/news/presenter/NewsListPresenter.java][NewsListPresenter] и [page/news/presenter/UserCardPresenter.java][UserCardPresenter].
 1. `View` каждого из портлетов должен наследоваться от виджета [Portlet][]. Корневым элементом для этих `view` должен быть виджет [Plug][]. см. в примере [page/news/view/NewsListView.java][NewsListView], [page/news/view/NewsListView.ui.xml][NewsListView.ux], [page/news/view/NewsInfoView.java][NewsInfoView], [page/news/view/NewsInfoView.ui.xml][NewsInfoView.ux] и [page/news/view/UserCardView.java][UserCardView].
  1. На основе выбранного вами лэйаута добавьте сборку страницы в `LayoutBuilder` вашего модуля. Для сборки зарегистрируйте в шине событий модуля по одному новому событию `plug...` (принимающему в параметре `Place`) для каждого виджета, который будет вставляться в плейсхолдер. Эти события должны уходить в презентеры, созданные в п.3 и вызывать в них отнаследованный метод `plug(Place)`, физически вставляя виджеты в плейсхолдеры. Поскольку в данном случае `view` однозначен, переопределять в презентерах ничего не нужно. Затем, в `LayoutBuilder`-е, вызовите созданные события поочерёдно, передавая в параметре соответствующие идентификаторы плейсхолдеров. см. в примере [page/news/layout/NewsLayoutBuilder.java][NewsLayoutBuilder] и [page/news/NewsEventBus.java][NewsEventBus]. Для `NEWS_SHOW` это методы `plugNewsInfo`, `plugUserCard` и `plugTestWidget`. Для `NEWS_LIST` это методы `plugNewsList`, `plugUserCard` и `plugTestWidget`.

### 4c. Создание цельной страницы с поддержкой состояний

 1. Добавьте описание доступа по URL к странице и укажите идентификатор соответствующего ей лэйаута в `enum` для страниц. см. в примере [id/P.java][P].
 1. Зарегистрируйте навигационное событие для вашей страницы в шине событий соответствующего модуля. см. в примере методы `users()`, `edit()` и `show()` интерфейса [page/user/UserEventBus.java][UserEventBus].
 1. В метод `convertFromUrl()` у `HistoryConverter` вашего модуля добавьте вызов этого события, если по истории был получен ваш портал. Создайте в конвертере метод `on...` для вашего события и возвратите отконвертированные параметры, если они есть — для удобства можно использовать проперти `url` конвертера. см. в примере [page/user/history/UserHistoryConverter][UserHistoryConverter].
 1. Создайте `Presenter` вашей страницы и отнаследуйте его от класса [StatedPortalPresenter][]. Интерфейс `View` должен расширять интерфейс [IsStatedPortalView][]. В конструктор нужно передать идентификатор портала, которому соответствует презентер. Для изменения состояния вида достаточно вызвать в необходимый момент подходящий метод у проперти `state` (например, `state.noData()`, `state.loading()`, `state.noMatches()`, `state.gotData()`...). По умолчанию виды с состояниями имеют состояние `LOADING`. У презентера, как и у конвертера, также есть проперти `url`, вы можете использовать его для построения URL и передаче их во `view`. см. в примере [page/user/presenter/UserEditPresenter.java][UserEditPresenter].
 1. Создайте `View` вашей страницы, отнаследовав его от класса [StatedPortal][]. Для каждой части страницы (виджета), которая будет вставлена в отдельный плейсходер, нужно использовать оборачивающий виджет [Plug][], независимо от того, используете вы `ui.xml` или нет. Корневым элементом для `view` должен быть виджет [Plugs][], который позволяет перечислить внутри нёго несколько виджетов `Plug`. Для каждого из необходимых состояний вида (можно регистрировать/создавать не все) также надо создать отдельный `Plug`. После этого, используя метод `register(State, Plug)`, их нужно связать с соответствующим им состоянием в методе `createView` см. в примере [page/user/view/UserEditView.java][UserEditView] и [page/user/view/UserEditView.ui.xml][UserEditView.ux], в случае `UserEditView` в разные плейсхолдеры будут вставлены `infoPlug` (блок с информацией), `avatarPlug`, `agePlug` и `testPlug` (просто фраза "Test Widget"); состояниям `NO_DATA` и `LOADING` соответствуют блоки `ifEmpty` и `whenLoading`, они регистрируются в методе `createView`
 1. На основе выбранного вами лэйаута добавьте сборку страницы в `LayoutBuilder` вашего модуля. В метод `layout` кроме всего прочего приходит состояние страницы `state`, его можно использовать для построения различных вариантов страницы, лэйаут уже подготовлен. Для сборки зарегистрируйте в шине событий модуля по одному новому событию `plug...` (принимающему в параметре `Place`) для каждого виджета и для каждого нужного вам состояния, которые будет вставляться в плейсхолдеры. Эти события должны уходить в презентер, созданный в п.3 и вызывать в нём метод `plug(Place, view.get...)`, физически вставляя виджеты в плейсхолдеры или уже определённые методы `plugEmpty`/`plugNoMatches`/`plugLoading` для состояний. Для этого создайте в презентере методы, перехватывающие события виджетов и вызывающие `plug()` с нужной частью `view`. Для событий "подключения" состояний ничего создавать не нужно. Затем, в `LayoutBuilder`-е, вызовите созданные события поочерёдно, передавая в параметре соответствующие идентификаторы плейсхолдеров (плейсхолдер состояния для виджета состояния). см. в примере [page/user/layout/UserLayoutBuilder.java][UserLayoutBuilder] ветка `USER_EDIT`, [page/user/UserEventBus.java][UserEventBus] и [page/user/presenter/UserEditPresenter.java][UserEditPresenter], для `USER_EDIT` это методы `plugInfoEditor`, `plugAgeEditor`, `plugAvatarEditor` и `plugTestWidget`.

### 4d. Создание страницы из портлетов, в которой некоторые или все портлеты поддерживают состояния

 1. Добавьте описание доступа по URL к странице и укажите идентификатор соответствующего ей лэйаута в `enum` для страниц. см. в примере [id/P.java][P].
 1. Зарегистрируйте навигационное событие для вашей страницы в шине событий соответствующего модуля. см. в примере методы `users()`, `edit()` и `show()` интерфейса [page/user/UserEventBus.java][UserEventBus].
 1. В метод `convertFromUrl()` у `HistoryConverter` вашего модуля добавьте вызов этого события, если по истории был получен ваш портал. Создайте в конвертере метод `on...` для вашего события и возвратите отконвертированные параметры, если они есть — для удобства можно использовать проперти `url` конвертера. см. в примере [page/user/history/UserHistoryConverter][UserHistoryConverter].
 1. Создайте для каждого портлета свой `Presenter` и отнаследуйте каждый от класса [StatedPortletPresenter][] (или от [PortletPresenter][], если у виджета не должно быть состояний). Интерфейс `View` у них должен расширять интерфейс [IsStatedPortletView][] (или [IsPortletView][], если у виджета нет состояний). Для изменения состояния вида портлетов  достаточно вызвать в необходимый момент подходящий метод у проперти `state` (например, `state.noData()`, `state.loading()`, `state.noMatches()`, `state.gotData()`...). По умолчанию виды с состояниями имеют состояние `LOADING`. У презентеров, как и у конвертера, также есть проперти `url`, вы можете использовать его для построения URL и передаче их во `view`. см. в примере [page/user/presenter/UserAvatarPresenter.java][UserAvatarPresenter], [page/user/presenter/UserDetailsPresenter.java][UserDetailsPresenter], [page/user/presenter/UserInfoPresenter.java][UserInfoPresenter] и [page/user/UserListPresenter][UserListPresenter].
 1. `View` каждого из портлетов должен наследоваться от виджета [StatedPortlet][] (или [Portlet][], если виджет не имеет состояний). Корневым элементом для `view` с состоянием должен быть виджет [Plugs][], внутри которого должны находиться виджеты [Plug][] с основным и побочными необходимыми вам состояниями. Для того, чтобы связать виджеты состояний с соответствующими [Plug][], используйте метод `register(Plug, State)` в реализации `createView()`. Для `view` без состояний, корневым элементом должен быть [Plug][], здесь всё проще — он и будет главным видом. см. в примере виды с состояниями: [page/user/view/UserAvatarView.java][UserAvatarView], [page/user/view/UserAvatarView.ui.xml][UserAvatarView.ux], [page/user/view/UserDetailsView.java][UserDetailsView], [page/user/view/UserDetailsView.ui.xml][UserDetailsView.ux], [page/user/view/UserInfoView.java][UserInfoView], [page/user/view/UserInfoView.ui.xml][UserInfoView.ux], виды без состояний: [page/news/view/NewsInfoView.java][NewsInfoView], [page/news/view/NewsInfoView.ui.xml][NewsInfoView.ux]
  1. На основе выбранного вами лэйаута добавьте сборку страницы в `LayoutBuilder` вашего модуля. Для сборки зарегистрируйте в шине событий модуля по одному новому событию `plug...` (принимающему в параметре `Place`) для каждого виджета, который будет вставляться в плейсхолдер. Эти события должны уходить в презентеры, созданные в п.3 и вызывать в них отнаследованный метод `plug(Place)`, физически вставляя виджеты в плейсхолдеры. Поскольку в данном случае `view` однозначен, переопределять в презентерах ничего не нужно. Затем, в `LayoutBuilder`-е, вызовите созданные события поочерёдно, передавая в параметре соответствующие идентификаторы плейсхолдеров. см. в примере [page/user/layout/UserLayoutBuilder.java][UserLayoutBuilder] и [page/user/UserEventBus.java][UserEventBus]. Для `USER_SHOW` это методы `plugUserInfo`, `plugUserAvatar` и `plugUserDetails`. Для `USERS_LIST` это методы `plugUserInfo`, `plugUserAvatar` и `plugUsersList`.

[[Содержание]](./index-ru.html)




[P]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/tree/master/src/name/shamansir/mvplayout/client/id/P.java#files
[G]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/tree/master/src/name/shamansir/mvplayout/client/id/G.java#files
[L]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/tree/master/src/name/shamansir/mvplayout/client/id/L.java#files
[O]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/tree/master/src/name/shamansir/mvplayout/client/id/O.java#files

[AMainPresenter]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/lib/mvp/AMainPresenter.java#files
[AMainView]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/lib/mvp/AMainView.java#files
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

