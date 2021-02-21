# face-recognition 人脸识别系统服务端

### 项目背景：
无人机人脸识别及在大型群体性活动维稳应用

### Demo地址，首次加载会比较慢，特别是人员列表的加载比较慢
http://face.pqdongo.com

### 项目整体概述：
1、采用dji go 4使用无人机进行人脸照片拍摄，信息采集    
2、将图片信息通过app上传到服务端进行人脸识别与信息匹配  
3、服务端接受到图片信息调用人脸识别接口进行校验，并记录信息  
4、给app端返回校验结果，app端显示校验结果  

### 系统架构：
1、客户端采用安卓开发一个简单的app应用程序，满足照片上传需求  
2、服务端Spring Boot框架，云端部署  
3、调用腾讯云人脸搜索，人员库API进行识别  

### 参考文档：
1、https://cloud.tencent.com/document/product/867/45035  
2、https://cloud.tencent.com/document/sdk/Java  


### 1.0排期：
- [x] 2021/01/15完成服务端整体架构与接口调用，跑通识别demo
- [ ] 2021/02/13安卓端开发
- [ ] 2021/02/15调通安卓端与服务端
- [x] 2021/02/16服务端管理页面
- [x] 2021/02/17联调整个服务，部署

### DDL
```sql
create table person
(
    id int auto_increment
 primary key,
 person_id varchar(50) not null default '' comment '人员id',
 face_base longtext null comment '人员头像base64',
 index person_index (person_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

```

### 部署开发

开发：通过idea直接引入项目即可，注意修改application.yml中的一些配置，切换到自己的数据库和腾讯云秘钥

云端部署：
#### 后端，采用host模式
docker run -d --name=facebackend -p 10011:10011 --network=host -v /home/face/log:/access_log imagename

#### 前端，采用host模式
docker run -d --name=facefront -p 10012:10012 --network=host imagename



