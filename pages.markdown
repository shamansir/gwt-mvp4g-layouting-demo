---
layout: main-en
title: Pages
---

# Pages

Every page demonstrates its own technique:

 * `/` (main page) — construction of a page with a `SINGLE` layout from inside of the main presenter, in bounds of the main event bus + using `UrlBuilder` to build hyperlinks pointing to internal pages
 * `/404` ("page not found") — quick page construction in case of `@NotFoundHistory` event handled, from inside of the main presenter, in bounds of the main event bus.
 * `/user/list[?<filter>]` — a portal that is directed with several `PortletPresenter`s, where every portlet (users list (`A`), user avatar file path (`B`), user age (`C`)) has a state support: when loading a users list, portlet is in a "users are loading" state; when there is no users matching the filter, portlet will inform about there is no matches, not absence of users; avatar and an age portlets are in "no data" state, when there is no user was chosen.
 * `/user/show?<id>` — portal, directed with several `PortletPresenter`s, where every portlet (user information (`A`), user avatar file path (`B`), user age (`C`)) has a state support, herewith the same age and avatar portlets from users list page are used.
 * `/user/edit[?<id>]` — portal, directed with a single `PortalPresenter`, layout supports switching states from presenter: while loading a user portlets `A`, `B`, `C` are replaced with a state panel "loading user". When saving a user, new data is collected by presenter from `view` and sent to the server.
 * `news/list` — portal, directed with several `PortletPresenter`s, but layout and portlets do not support states; for one of the portlets `TestWidgetPresenter` is used, where `View` is created directly from inside the presenter, the `TestWidget` itself is accessible to all pages; also the interaction with neighbour presenters is demonstrated (`UserCardPresenter`).
 * `news/edit[?<id>]` — portal, directed with one `PortalPresenter`, layout do not supports states; the same `UserCardPresenter` as for `news/list` is used.
 * `news/show?id` — portal, directed with several `PortletPresenter`s, portlets do not support states; the same `UserCardPresenter` as for `news/list` is used, and also `TestWidgetPresenter`.
 * `company/list` — portal with a single portlet, directed with single `PortletPresenter`, that in its turn used by the external portlet-widget to display each company.
 * `company/show?<id>` — portal with a single portlet, directed with single `PortletPresenter`, while this portlet is the same portlet-widget that is used for companies list, no mediators or wrappers.

[[Contents]](./index.html)

