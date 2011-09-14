---
layout: main-en
title: GWT + mvp4g Layouting Demo
---

# GWT + mvp4g Layouting Demo

[ [EN](./index.html) - [RU](./index-ru.html) ]

## Short summary

This library extends [mvp4g](http://code.google.com/p/mvp4g/) framework with support of portlets, layouts and data loading states Written by [shaman.sir](http://shamansir.github.com).

## Links

* [Documentation](http://shamansir.github.com/gwt-mvp4g-layouting-demo/#more-details) ("More details" section in this page)
* [Running demo](http://gwt-mvp4g-layouting-demo.appspot.com)
* [Take a `jar`](https://github.com/shamansir/gwt-mvp4g-layouting/archives/master)
* [Introduction in mvp4g blog](http://mvp4g.blogspot.com/2011/09/complex-layouting-library-over-mvp4g.html)

## Intro

![mvp4g logo](http://mvp4g.googlecode.com/svn/logo/mvp4g-logo-small.png)

[Mvp4g](http://code.google.com/p/mvp4g/) framework helps in writing applications which has not a lot of significant changes happening in the inner page section or just with a central part that is /instantly/ replaced with a new content. I can't say that it is a limitation of the framework — this situation may change in near future or may be not required for a lot of users — however, for the moment, this fact takes place.

For some moment, in [our project](http://experika.com) we have faced with critical mass of pages with evidently very similar widget placing positions. So we, together with [Vitaly Gashock](http://gashock.blogspot.com/) (who introduced this framework to me) and [Sergey Dyniovsky](http://lazio.com.ua/) (a markup master, who had really taken a GWT widgets into account), made a decision to generalize and structurize these things. There is how the layout set with the names of artists was born, the existing pages were distributed with these layouts, and we started to refactor (this action lead us to a few new bugs, of course, but instead we've got rid of kludges we hated so much). More of this, we were happy to remove some widgets with a very similar realization, in past we were required to make them because of their different behaviour between the pages. A  quintessence of this refactoring, completely separated from project logic and those freaky bugs inherited from the project itself, is described in this article and is  shown with this example.

## Downloads and Source Code

Source code of the library is located at github: [github.com/shamansir/gwt-mvp4g-layouting](https://github.com/shamansir/gwt-mvp4g-layouting). You can download it as a `jar` file there in *Downloads* section. Or just click [this link](https://github.com/downloads/shamansir/gwt-mvp4g-layouting/mvp4glayout-0.9-mvp4g1.3.jar). To use it in project you'll also need to have the compatible version of [mvp4g](http://code.google.com/p/mvp4g/downloads/detail?name=mvp4g-1.3.1.jar) and [gwt-log](http://code.google.com/p/gwt-log/downloads/detail?name=gwt-log-3.1.3.jar) in build path.

## What else?

This demo source code is also located at github: [github.com/shamansir/gwt-mvp4g-layouting-demo](https://github.com/shamansir/gwt-mvp4g-layouting-demo).

In the executable and clickable state (_in action_, you say) you can check it out at GAE: [gwt-mvp4g-layouting-demo.appspot.com](http://gwt-mvp4g-layouting-demo.appspot.com/). Is is a technical example, so there is an absence of design.

Documentation is located at github: [shamansir.github.com/gwt-mvp4g-layouting-demo](http://shamansir.github.com/gwt-mvp4g-layouting-demo/index.html)

## More details?

* [Demonstrates What?](./demonstrates.html)
* [Layouts](./layouts.html)
* [Definitions](./definitions.html)
* [Pages](./pages.html)
* [Scenario](./scenario.html)
* [Styles](./style.html)
* [Works how?](./works-how.html)

## Something more?

* [GWT + mvp4g lecture slides](http://shamansir.tumblr.com/post/5311666870/gwt-mvp4g-lecture-slides)

## State

* Current version: `0.9`
* Compatible with GWT: `2.3`
* Compatible with mvp4g: `1.3`

<!--
## Something more?

* Доклад о GWT + mvp4g. [Слайды](http://shamansir-ru.tumblr.com/post/5237785159/gwt-mvp4g-slides). Видео, [ч1](http://vimeo.com/shamansir/gwt-mvp4g-ru-p1), [ч2](http://vimeo.com/shamansir/gwt-mvp4g-ru-p2), [ч3](http://vimeo.com/shamansir/gwt-mvp4g-ru-p3).
* Русская группа GWT на Google Groups: [google-web-toolkit-ru](https://groups.google.com/forum/#!forum/google-web-toolkit-ru)
-->

