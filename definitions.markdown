---
layout: main-en
title: Terms
---

# Terms

Some of these terms are established ones, part of them are invented by me (possibly), due to the fact I discovered a lack of them. I will list the terms that are specific to the example, and I will skip the GWT/MVP/mvp4g-related ones. Also, the definitions in fact are only related to example, they are not globally-definite in any case.

* **Layout** — the predifined disposition of a blocks in a page, no binding to blocks contentbut to topograp.
* **Portal** — in fact, an analogue of "application page", it has a layout bound to it, and a portlets inside, positioned with a structure of this specific layout.
* **Portlet** — any standalone or composite widget that may be inserted whole or in part in a concrete place inside of layout.
* **Place[holder]** — an identidier of the place inside a layout, where you can put any visual object (widget, widgets group). In the example the letters `A`, `B`, `C`, `D`, `S` identify the places. You can use words if you want: `NAV`, `ASIDE`, `MAIN`, `TOOLBAR`, `STATUS` and so on.
* **Outlet** — a physical place inside of layout, where you can put any object that implements `Pluggable` interface; uniquely defined with `Place` identifier.
* **Plug, Pluggable** — any visual object (widget, a group of widgets, portlet, a state-panel of a portlet) that you may put in "outlet"; has a liberal string identifier.
* **State** — special mode of a page or a separate portlet, where it informs user about the status of data obtaining process (to easily change a structure within the page or easily substitute the blocks within the portlet); pages and widgets that support states have a default state of "loading data".
* **Group** — a group of pages, joined up with one child event bus; since pages that work with single type of entities are usually associated with one event bus, then "group" means an "entity type" in this case. The abscense of group in page properties means that it is bound to main event bus.
* **URL Builder** — a class that allows to construct or parse URL-s of the pages (including parameters) within the project on-the-fly; it is directly accessible in presenters and history converters as an `url` property.
* **Layout Builder** — a class that actually connects a "plugs" to "outlets" or, to paraphrase, plugs (inserts) "porlets" into the "portals".
* **State Director** — a class that helps to change a state from a portlet or portal presenter in a tick, provides commands like `state.noData()`, `state.gotData()`, `state.loading()`, `state.noMatches()` and other similar ones.

[[Contents]](./index.html)

