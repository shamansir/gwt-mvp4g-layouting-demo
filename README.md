Running demo is here: http://gwt-mvp4g-layouting-demo.appspot.com

Documentation is here: http://shamansir.github.com/gwt-mvp4g-layouting-demo/

# Eclipse

Download `eclipse-files.zip` from this repo to import Eclipse Project easily (anyway, it will possibly need some corrections)

Run configuration:

    -remoteUI "${gwt_remote_ui_server_port}:${unique_id}" -logLevel INFO -war
    <projects-dir>/gwt-mvp4g-layouting-demo/war -server com.google.appengine.tools.development.gwt.AppEngineLauncher 
    -codeServerPort 9997 -port 8888 name.shamansir.mvp4glayoutdemo.mvplayout