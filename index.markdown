---
layout: main-en
title: GWT + mvp4g Layouting Demo
---

# GWT + mvp4g Layouting Demo

[EN](./index.html) - [RU](./index-ru.html)

## Intro

![mvp4g logo](http://mvp4g.googlecode.com/svn/logo/mvp4g-logo-small.png)

[mvp4g](http://code.google.com/p/mvp4g/) framework helps in writing applications which has not a lot of significant changes happening in the inner page section or just with a central part that is /instantly/ replaced with a new content. I can't say that it is a limitation of the framework — this situation may change in near future — however, for the moment, this fact takes place.

For some moment, in [our project](http://experika.com) we have faced with critical mass of pages with evidently very similar widget placing positions. So we, together with [Vitaly Gashock](http://gashock.blogspot.com/) (who introduced this framework to me) and [Sergey Dyniovsky](http://lazio.com.ua/) (a markup master, who had really taken a GWT widgets into account), made a decision to generalize and structurize these things. There is how the layout set with the names of artists was born, the existing pages were distributed with these layouts, and we started to refactor (this action lead us to a few new bugs, of course, but instead we've got rid of kludges we hated so much). More of this, we were happy to remove some widgets with a very similar realization, in past we were required to make them because of their different behaviour between the pages. A  quintessence of this refactoring, completely separated from project logic and those freaky bugs inherited from the project itself, is described in this article and is  shown with this example.

## What else?

Source code is located at github: [github.com/shamansir/gwt-mvp4g-layouting-demo](https://github.com/shamansir/gwt-mvp4g-layouting-demo).

In the executable and clickable state (_in action_, you say) you can check it out at GAE: [gwt-mvp4g-layouting-demo.appspot.com](http://gwt-mvp4g-layouting-demo.appspot.com/). Is is a technical example, so there is an absence of design.

Documentation is located at github: [shamansir.github.com/gwt-mvp4g-layouting-demo](http://shamansir.github.com/gwt-mvp4g-layouting-demo/index.html)

## More details?

The code is separated in `client`, `shared`, `server` and `lib` packages: the first three packages contain the only example code and the latter is a code of a library itself, you can even place it in a separate `jar`.

* [Demonstrates What?](./demonstrates.html)
* [Layouts](./layouts.html)
* [Definitions](./definitions.html)
* [Pages](./pages.html)
* [Scenario](./scenario.html)
* [Styles](./style.html)
* [Works how?](./works-how.html)

<!--
## Something more?

* Доклад о GWT + mvp4g. [Слайды](http://shamansir-ru.tumblr.com/post/5237785159/gwt-mvp4g-slides). Видео, [ч1](http://vimeo.com/shamansir/gwt-mvp4g-ru-p1), [ч2](http://vimeo.com/shamansir/gwt-mvp4g-ru-p2), [ч3](http://vimeo.com/shamansir/gwt-mvp4g-ru-p3).
* Русская группа GWT на Google Groups: [google-web-toolkit-ru](https://groups.google.com/forum/#!forum/google-web-toolkit-ru)
-->

