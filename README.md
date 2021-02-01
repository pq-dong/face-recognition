# face-recognition 人脸识别系统服务端

### 项目背景：
无人机人脸识别及在大型群体性活动维稳应用

### 项目整体概述：
1、采用dji go 4使用无人机进行人脸照片拍摄，信息采集    
2、将图片信息通过app上传到服务端进行人脸识别与信息匹配  
3、服务端接受到图片信息调用人脸识别接口进行校验，并记录信息  
4、给app端返回校验结果，app端显示校验结果  

### 系统架构：
1、客户端采用安卓开发一个简单的app应用程序，满足照片上传需求  
2、服务端Spring Boot框架，云端部署  
3、调用腾讯云人脸搜索，人员库API进行识别  


### 排期：
[x] 2021/01/15完成服务端整体架构与接口调用，跑通识别demo



