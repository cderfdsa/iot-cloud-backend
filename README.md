# iot-cloud-backend
物联网云平台-后端

### 技术选型

- JDK 17
- SpringBoot 3
- MySQL 8
- Kafka
- InfluxDB
- Redis
- smqttx
- modbus4j
- mybatis+tk.mybatis

### 数据库设计草图

- 用户表 user_info
- 设备表 device_info
- 设备类型表 device_type
- 设备类型属性表 device_type_attributes
- 设备类型属性Modubus表 device_type_attributes_modbus

### 代码结构设计草图

- application 主模块
- dao 数据库操作模块
- service 业务操作模块
- server 通讯服务模块
  - server-mqtt 
  - server-tcp
  - server-http
- webapi webapi服务模块
- common
  - common-utils 工具模块
  - common-base 基础抽象模块
  - common-config 配置模块
- alarm
  - alarm-rule 报警规则引擎
  - alarm-notify 报警通知模块

### 协议设计草图
/device/${device.code}/attributes/d
/device/${device.code}/attributes/u
/account/${user.account}/${device.code}/attributes/d
/account/${user.account}/${device.code}/attributes/u
/account/${user.account}/online/d
/account/${user.account}/alarm/d