---
layout: main-en
title: Demonstrates what?
---

# Demonstrates what?

This example demontrates:

- *Event buses*: Using main and child event buses in mvp4g: the main event bus manages loading _modules_ for users, groups and companies, and the event buses, corresponding to this modules, are responsible for _actions_ user can do with them: watch a list of elements, edit or view a separate element. This possiblity exists in mvp4g out-of-the-box.
- *Common and variable parts of the pages*: Using some inner part of the general page (or a whole page, if required) to place widgets into, according to layout that assigned to this concrete application page (it is the area between toolbar and footer in the example).
- *Layouts, pages and URLs*: Using the very single `enum` to bind pages to layout and also as the common point of URLs/navigation control. This `enum` is used everywhere the history management happens and/or it is required to construct or parse some URL. If required, you can extend it to work with named parameters. One `ui.xml` is intended to describe one layout.
- *Portals and portlets*: Filling a page with widgets, following to a chain: history &larr; layout builder &larr; event bus &larr; portlet (some widget) or portal (some page) presenter. So you can manage the whole page with one presenter (`PortalPresenter`) or using several distinct re-used presenters (`PortletPresenter`s) or you even can mix this mehtods (see [scenario](./scenario.html)).
- *States*: A self-contained widgets (portlets) and/or the whole pages (portals) may have following states: "no data", "loading data", "got data", "no matches", and this structure may even be nested.
- *CSS-friendly*: Every element corresponding to the logical block (page, portal, layout, placeholder, widget) has a minimum of two CSS-classes that allows to determine its identity unambiguously (see [style](./style.html))

[[Contents]](./index.html)

