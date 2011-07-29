---
layout: main-ru
title: Styles
---

# Styles

CSS classes structure is created automatically, along with those you created:

    portal portal-<portal-id> group-<group-id>
        page layout-<layout-id>
            [state-<state-id>] // exists, if the whole page supports states
                place place-<place-id>
                    // state class exists, if the concrete widget supports states
                    [state-<state-id>] plug plug-<plug-id>
                place place-<place-id>
                    // state class exists, if the concrete widget supports states
                    [state-<state-id>] plug plug-<plug-id>
                place place-<place-id>
                . . .

For example, this structure at [user/list](http://gwt-mvp4g-layouting-demo.appspot.com/#user/list)  page in some state looks like this:

    portal portal-user-list group-user
        page layout-list
            place place-a
                state-loading plug plug-user-list-loading
            place place-b
                state-no-data plug plug-user-avatar-empty
            place place-c
                state-has-data plug plug-user-details

[[Содержание]](./index-ru.html)

