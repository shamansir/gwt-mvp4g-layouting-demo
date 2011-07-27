---
layout: main-ru
title: Стили
---

# Стили

Структура стилей на страницах создаётся автоматически, помимо указанных вами всегда существуют:

    portal portal-<portal-id> group-<group-id>
        page layout-<layout-id>
            [state-<state-id>] // присутствует, если вся страница поддерживает состояния
                place place-<place-id>
                    // state присутствует, если конкретный виджет поддерживает состояния
                    [state-<state-id>] plug plug-<plug-id>
                place place-<place-id>
                    // state присутствует, если конкретный виджет поддерживает состояния
                    [state-<state-id>] plug plug-<plug-id>
                place place-<place-id>
                . . .

Например, для страницы [user/list](http://gwt-mvp4g-layouting-demo.appspot.com/#user/list) эта структура в одном из состояний такова:

    portal portal-user-list group-user
        page layout-list
            place place-a
                state-loading plug plug-user-list-loading
            place place-b
                state-no-data plug plug-user-avatar-empty
            place place-c
                state-has-data plug plug-user-details

[[Содержание]](./index-ru.html)

