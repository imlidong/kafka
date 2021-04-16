# demo for docker kafka zk and SpringBoot
 
# windows下docker安装zk和kafka环境

## 1. docker安装zookeeper

拉取zookeeper的镜像

`docker pull wurstmeister/zookeeper`

创建并启动zookeeper容器

`docker run -d --name zookeeper -p 2181:2181 wurstmeister/zookeeper`

zookeeper容器顺利创建并启动



##  2. docker安装kafka

拉取kafka镜像

`docker pull wurstmeister/kafka`

创建并启动kafka容器

`docker run -d --name kafka -p 9092:9092 --link zookeeper wurstmeister/kafka `

> 注意这里创建失败了，因为并没有填写宿主机的ip地址，导致启动失败



再次尝试启动

`` docker run -d --name kafka1 -p 9092:9092 --link zookeeper \`` 

 ``-e KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181 \``

 ``-e KAFKA_ADVERTISED_HOST_NAME=localhost \ ``

  ``-e KAFKA_ADVERTISED_PORT=9092 \  ``

 ``wurstmeister/kafka ``

> 这一次成功启动



查看一下已经启动的容器

![c23bQg.png](https://z3.ax1x.com/2021/04/15/c23bQg.png)

也可以使用docker客户端查dash查看已经启动的容器

![c23sJK.png](https://z3.ax1x.com/2021/04/15/c23sJK.png)



## 3. docker-compose 启动zk和kafka

当容器特别多的时候，其实每次启动一个个的容器非常的繁琐，docker-compose可以很好的以组合方式启动，只需要编写简单的yml文件

```yml
version: '2'
services:
  zookeeper:
    image: wurstmeister/zookeeper
    ports:
      - "2181:2181"
  kafka:
    image: wurstmeister/kafka
    ports:
      - "9092:9092"
    environment:
      KAFKA_ADVERTISED_HOST_NAME: localhost
      KAFKA_CREATE_TOPICS: "demo"
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
    volumes:
      - /var/run/docker.sock:/var/run/docker.sock
```

然后使用指令：

`docker-compose.exe -f xxxx.yml up -d`即可启动两个服务！



## 4. SpringBoot整合kafka的demo



