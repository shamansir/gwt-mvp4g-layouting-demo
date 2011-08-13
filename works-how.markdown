---
layout: main-en
title: Works how?
---

# Works how?

The whole source code is separeated into packages `client`, `shared`, `server` and `lib`: the first three ones are contain only the example-related code and the latter one contains the library code itself.

All the main events used in this code are happen due to passing signals with forwarding technique from child event buses ([ChildEventBus][]) to the main event bus ([IsMainEventBus][]). So, you can see such events are enumerated in [IsMainEventBus][] interface. From the main event bus they are passed to the main page presenter ([AMainPresenter][]) and are appropriately handled there.

The new event comes in as an event string and a parameters string in mvp4g-`HistoryConverter`. Basing on this two strings and a list of registered pages, that describe the relations between pages, events and layouts, we can concretely recognize a current page, current URL and the corresponding layout ID. It happens in `convertFromToken` method of [PortalsHistoryConverter][] class. (That's why all the events in your application must pass through history mechanism, it may be reached by tuning up event buses).

[PortalsHistoryConverter][] implementation, before calling a navigation event, prepares [CanBuildLayout][] instance, which may build a layout in lazy-way (on demand) and to fill it with widgets, using current module's [LayoutBuilder][]. This instance is passed to `newPortal(...)` event, that in the end goes directly to a main page presenter. The presenter makes a decision on  the need of building a new layout (if layout was changed or a state was changed) and, if it is really necessary, it asks for a factual layout building (creating a new empty instance (or cleaning up the cached one) of layout) and filling it with widgets depending on current state (if set) with the help of [LayoutBuilder][].

This is achieved by means that [LayoutBuilder][] passes a current portal to the child implementation of an abstract `layout` method, along with a page state (`null`, if page is stateless) and layout ID. Using this data the child implementation can unambiguously decide which widgets and in which state it is required to fill up layout placeholders, with the help `plug` event of any child module's event bus. A `plug` event is passed to the main page presenter and it actually connects the plugs to outlets of current layout.

Abstract `covertFromUrl` method is called by [PortalsHistoryConverter][] class after that, and the parsed URL and portal ID are passed there. Using this ID of the portal the child converter may call the corresponding navigation event.

Thus, the *layout of the page is built before calling the navigation event*. After the fact of navigation event was called, user can ask to change a state using `updateState(Place, State)` event to update the whole page state (when `place` == `null`) or a part of it (`place` is not `null`). This event is also handled by main presenter, that updates the layout using `LayoutBuilder` if needed, or just replaces the content of a single "outlet".

In fact, it is the complete mechanism, the rest are subtleties, I hope, you'll understand from the code.

P.S. For the moment no annotations or Dependency Injection used in the code. May be it'd be nice to.

[[Contents]](./index-ru.html)

[PortalsHistoryConverter]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/lib/mvp/PortalsHistoryConverter.java#files
[ChildEventBus]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/lib/mvp/ChildEventBus.java#files
[IsMainEventBus]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/lib/mvp/IsMainEventBus.java#files
[AMainPresenter]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/lib/mvp/AMainPresenter.java#files
[CanBuildLayout]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/lib/ui/LayoutBuilder.java#L37
[LayoutBuilder]: https://github.com/shamansir/gwt-mvp4g-layouting-demo/blob/master/src/name/shamansir/mvplayout/lib/ui/LayoutBuilder.java#files

