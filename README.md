## videojs on Liferay sample
This sample shows how to display streaming on Liferay 7 / DXP from HTTP Streaming server (Nginx + nginx-rtmp-module) with videojs. videojs needed to be exposed globally, so 

## Required environment
* Liferay 7.0 GA4 / DXP SP4 or above
* Gradle 3.0 or above (to complie)
* Git
* Preferably, the latest version of [blade tool](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/installing-blade-cli) or you can simply drop the generated jar file under ```${liferay_home}/deploy``` folder.

## How to run this sample
1. Start the Liferay bundle.
2. Place this sample right under ```modules``` folder of your Liferay workspace, (for more details, please see [the official document](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/liferay-workspace)
3. Go to this sample root folder and run ```blade deploy``` or ```gradle assemble``` and place the generated jars into the ```${liferay_home}/deploy```
4. Confirm if it's properly deployed (you can see a log on a console reads like 
```bash
[Thread-60][BundleStartStopLogger:35] STARTED videojs-hook_1.0.0 [540]
[Thread-63][BundleStartStopLogger:35] STARTED com.liferay.videojs_1.0.0 [541]
```
5. Login as test@liferay.com as an administrator and place ```Videojs Portlet``` from the right pane.
6. Navigate to configuration of ```Videojs Portlet``` and click ```Save``` to save the default parameter.
7. Go back to the top page and refresh the page, and click ```Play``` button in the black rectangle area.

## How to set up your own streaming server quick
1. Clone the following repository ```git clone https://github.com/brocaar/nginx-rtmp-dockerfile```
2. Run ```docker build -t nginx_rtmp .``` to initialize the docker instance and then run ```docker run -p 1935:1935 -p 8080:80 --rm nginx_rtmp``` 
3. Download streaming client, [Open Broadcaster Software](https://obsproject.com/) and set up streaming.
4. Start up OBS and navigate to Streaming pane on left, then set URL ```rtmp://your-server-ip-is-here:1935/encoder/``` and Stream key as ```live``` for example.
5. On Liferay, navigate to configration of Videojs Portlet and set the URL as ```rtmp://your-server-ip-is-here:1935/encoder/live.m3u8```
6. The live stream should be displayed.

If you run this docker on the same machine, please make sure to change the port for Liferay. In case of tomcat Liferay bundle, go to ```${tomcat_home}/conf/Catalina/server.xml``` and change following part to appropriate port number from 8080 to 9090 for example.
```server.xml
    <Connector port="8080" protocol="HTTP/1.1"
               connectionTimeout="20000"
               redirectPort="8443" URIEncoding="UTF-8" />
```
## Bug report
Please create an issue on this repository.