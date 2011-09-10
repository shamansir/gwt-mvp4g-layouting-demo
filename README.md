### Demo

Running demo is here: http://gwt-mvp4g-layouting-demo.appspot.com

### Documentation

Documentation is here: http://shamansir.github.com/gwt-mvp4g-layouting-demo/

### Library code

`mvp4glayout` Library code is here: https://github.com/shamansir/gwt-mvp4g-layouting (Downloads are also there). To use this library you must have a compatible [`mvp4g`](http://code.google.com/p/mvp4g) version in your project and, currently, [`gwt-log`](http://code.google.com/p/gwt-log).

### Eclipse

Download `eclipse-files.zip` from this repo to import in Eclipse as Existing Project easily (anyway, it will possibly need some corrections in paths)

Run configuration:

    -remoteUI "${gwt_remote_ui_server_port}:${unique_id}" -logLevel INFO -war
    <projects-dir>/gwt-mvp4g-layouting-demo/war -server com.google.appengine.tools.development.gwt.AppEngineLauncher 
    -codeServerPort 9997 -port 8888 name.shamansir.mvp4glayoutdemo.mvplayout
