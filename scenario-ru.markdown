---
layout: main-ru
title: Сценарии
---

## Как этим пользоваться?

По сути, в пакете [`name.shamansir.mvplayout.lib`](https://github.com/shamansir/gwt-mvp4g-layouting-demo/tree/master/src/name/shamansir/mvplayout/lib) лежит код, который вполне можно использовать как библиотеку. Я пока не тороплюсь его заворачивать в `jar`, но потенциально он вполне надёжен для использования — сложно-составной пример работает стабильно, а ядро этой идеи (правда, устаревшая, невылизанная версия — актуальную версию встроить пока нет возможности) работает в [нашем проекте](http://experika.com).

Поясню, как пользоваться всем этим кодом, если вы вдруг решили разработать на основе его проект. Ниже представлены одновременно последовательные и отдельные сценарии — выполнив их по очереди (переходя на некоторых в цикл "повторять несколько раз пока необходимо") можно написать приложение, но потом каждое из этих действий можно выполнять отдельно по мере необходимости изменений в приложении.

### Каркас приложения

 1. Продумать систему навигации вашего проекта, желательно чтобы она ложилась на схему `тип-объекта/действие`.
 1. Создать четыре `enum`-файла. Эти четыре `enum`-файла — главная и единственная конфигурация структуры страниц и лэйатуов в проекте (они не должны сразу же отражать действительность, но что-то потенциально реальное в них всё же должно быть):
   * Один (в примере: [id/P.java](https://github.com/shamansir/gwt-mvp4g-layouting-demo/tree/master/src/name/shamansir/mvplayout/client/id/P.java)) для перечисления _страниц_,описания _URL_-ов (группа — тип объекта, а событие — действие в упомянутой схеме `тип-объекта/действие`) и связывания их с _лэйаутами_. Для каждой страницы автоматически создаётся ID по методу `id()` (в примере в качестве ID везде берётся `name()` элемента из `enum`, но это может быть что угодно) и привязывается к созданному инстансу класса `Portal`: для этого `enum` должен имплементить интерфейс `PortalId`. Также вам понадобится быстрый способ получить значение `enum` из идентификатора `Portal`: в примере это метод `by(Portal page)`, для этого инстансы `Portal` хранятся внутри.
   * Второй (в примере: [id/G.java](https://github.com/shamansir/gwt-mvp4g-layouting-demo/tree/master/src/name/shamansir/mvplayout/client/id/G.java)) для перечисления _групп_. Должен имплементить интерфейс `Group`: возвращать уникальный ID в методе `id()`.
   * Третий (в примере: [id/L.java](https://github.com/shamansir/gwt-mvp4g-layouting-demo/tree/master/src/name/shamansir/mvplayout/client/id/L.java)) для перечисления _лэйаутов_. Должен имплементить интерфейс `LayoutId`: возвращать уникальный ID в методе `id()`.
   * Четвёртый (в примере: [id/O.java](https://github.com/shamansir/gwt-mvp4g-layouting-demo/tree/master/src/name/shamansir/mvplayout/client/id/O.java)) для перечисления _плэйсхолдеров_. Должен имплементить интерфейс `Place`: возвращать уникальный ID в методе `id()`.
 1. Создать `EntryPoint` вашего проекта и зарегистрировать в нём описанные порталы, причём выполнить это до запуска модуля mvp4g. В дальнейшем тут же нужно будет регистрировать новые лэйауты и лэйаут-билдеры, но в данный момент это необзяательно. см. точку входа [LayoutingDemo.java](https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/client/LayoutingDemo.java) в примере.
 1. Создать `Presenter` главной страницы и отнаследовать его от [AMainPresenter](https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/lib/mvp/AMainPresenter.java). Ничего больше особенного здесь делать не нужно. см. [page/main/presenter/MainPresenter.java](https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/client/page/main/presenter/MainPresenter.java) в примере.
 1. Создать `View` главной страницы и отнаследовать его от [AMainView](https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/lib/mvp/AMainView.java). см. [page/main/view/MainView.java](https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/client/page/main/view/MainView.java) в примере.
   * Метод `getLayoutHolder()` должен возвращать панель, в которую будет "вставляться" лэйаут.
   * Метод `getPortalHolder()` может возвращать панель, оборачиваю предыдущую, а может ту же самую. Этот метод применяется только для назначения CSS-классов, классы портала и лэйаута будут назначены одному элементу в последнем случае.
   * Метод `getScrollable()` может возвращать область, которая будет скроллиться внутри страницы. Если такой области нет, метод может возвращать `null`, но тогда не будет работать подписка на скролл-события страницы по шине событий (`addPageScrollHandler`).
 1. Создать главную шину событий, имплеменитрующую [IsMainEventBus](https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/lib/mvp/IsMainEventBus.java). В главной шине нужно переопределить все методы `IsMainEventBus` и перенаправить их в презентер главной страницы . Также нужно создать главный модуль, ничем не отличающийся от главного модуля mvp4g. см. [page/main/MainEventBus.java](https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/client/page/main/MainEventBus.java) и [page/main/MainModule.java](https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/client/page/main/MainModule.java) в примере.

### Создание лэйаута

 1. Добавьте идентификатор нового лэйаута в `enum` лэйатуов. см. в примере [id/L.java](https://github.com/shamansir/gwt-mvp4g-layouting-demo/tree/master/src/name/shamansir/mvplayout/client/id/L.java).
 1. Если нужно, добавьте идентификаторы новых плейсходеров в `enum` плейсхолдеров. см. в примере [id/O.java](https://github.com/shamansir/gwt-mvp4g-layouting-demo/tree/master/src/name/shamansir/mvplayout/client/id/O.java).
 1. Если вы хотите описать лэйаут декларативно, создайте соответствующий `ui.xml`. В этом `ui.xml` для обозначения мест, куда будут вставляться портлеты (плейсхолдеров) нужно использовать виджет [Outlet](https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/lib/ui/widget/Outlet.java). см. [layout/LayoutItem.ui.xml](https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/client/layout/LayoutItem.ui.xml) в примере. Можно описывать лэйаут и не декларативно, тогда придётся создавать плэйсхолдеры используя конструктор [Outlet](https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/lib/ui/widget/Outlet.java).
 1. Создайте класс лэйаута, отнаследовав его от [Layout](https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/lib/ui/widget/Layout.java). В родительский конструктор передайте новый идентификатор лэйаута и идентификаторы всех плейсходеров, принадлежащих этому лэйауту. В переопределённом методе `prepareOutlet()` возвратите нужный `Outlet` по переданному идентификатору. Виджет должен инициироваться прямо в конструкторе. см. [layout/LayoutItem.java](https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/client/layout/LayoutItem.java) в примере.
 1. Зарегистрируйте лэйаут в точке входа через метод `Layouts.register()`. см. [LayoutingDemo.java:47](https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/client/LayoutingDemo.java#L47) в примере.

### Создание лэйаута с состояниями

 1. Добавьте идентификатор нового лэйаута в `enum` лэйатуов. см. в примере [id/L.java](https://github.com/shamansir/gwt-mvp4g-layouting-demo/tree/master/src/name/shamansir/mvplayout/client/id/L.java).
 1. Если нужно, добавьте идентификаторы новых плейсходеров в `enum` плейсхолдеров. Туда же добавьте плейсхолдер для информирования о состоянии (я назвал его `STATUS`), если вы его ещё не завели. см. в примере [id/O.java](https://github.com/shamansir/gwt-mvp4g-layouting-demo/tree/master/src/name/shamansir/mvplayout/client/id/O.java).
 1. Если вы хотите описать лэйаут декларативно, создайте соответствующий `ui.xml`. В этом `ui.xml` для обозначения мест, куда будут вставляться портлеты (плейсхолдеров) нужно использовать виджет [Outlet](https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/lib/ui/widget/Outlet.java). Для места, куда будет вставляться сообщение о состоянии тоже подготовьте плэйсхолдер. см. [layout/LayoutEdit.ui.xml](https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/client/layout/LayoutEdit.ui.xml) в примере. Можно описывать лэйаут и не декларативно, тогда придётся создавать плэйсхолдеры используя конструктор [Outlet](https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/lib/ui/widget/Outlet.java).
 1. Создайте класс лэйаута, отнаследовав его от [LayoutWithState](https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/lib/ui/state/LayoutWithState.java). В родительский конструктор передайте новый идентификатор лэйаута и идентификаторы всех плейсходеров, принадлежащих этому лэйауту. Отдельно (последним параметром) передайте идентификатор плэйсхолдера, предназначенного для сообщения о состоянии. В переопределённом методе `prepareOutlet()` возвратите нужный `Outlet` по переданному идентификатору. В методе `prepare(State)` вы можете по переданному состоянию переключать видимость тех или иных виджетов внутри лэйаута и/или делать что-то ещё, если нужно. Виджет должен инициироваться прямо в конструкторе. см. [layout/LayoutEdit.java](https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/client/layout/LayoutEdit.java) в примере.
 1. Зарегистрируйте лэйаут в точке входа через метод `Layouts.register()`. см. [LayoutingDemo.java:47](https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/client/LayoutingDemo.java#L47) в примере.

### Создание группы страниц

 1. Добавьте идентификатор новой группы в `enum` групп. см. в примере [id/G.java](https://github.com/shamansir/gwt-mvp4g-layouting-demo/tree/master/src/name/shamansir/mvplayout/client/id/G.java).

### Создание цельной страницы без поддержки состояний

### Создание страницы с портлетами без поддержки состояний

### Создание цельной страницы с поддержкой состояний

### Создание страницы из портлетов, в которой некоторые или все портлеты поддерживают состояния

