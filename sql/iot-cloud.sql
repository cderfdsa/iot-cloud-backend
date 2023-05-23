-- `iot-cloud`.device_info definition

CREATE TABLE `device_info`
(
    `id`                 bigint      NOT NULL AUTO_INCREMENT COMMENT '自增ID',
    `rel_device_type_id` bigint      NOT NULL COMMENT '设备类型表',
    `rel_user_info_id`   bigint      NOT NULL COMMENT '用户信息表',
    `name`               varchar(20) NOT NULL COMMENT '设备名称',
    `code`               varchar(20) NOT NULL COMMENT '设备标识符',
    `pwd`                varchar(20) NOT NULL COMMENT '设备连接密码',
    `online_status`      int         NOT NULL DEFAULT '0' COMMENT '在线状态 0离线 1在线',
    `alarm_status`       int         NOT NULL DEFAULT '0' COMMENT '告警状态 1正常 2普通告警 3重要告警 3紧急告警',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 20
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='设备信息表';


-- `iot-cloud`.device_type definition

CREATE TABLE `device_type`
(
    `id`                 bigint                                                       NOT NULL AUTO_INCREMENT COMMENT '自增ID',
    `name`               varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
    `type`               int                                                          NOT NULL DEFAULT '1' COMMENT '类型 1直连设备 2网关 3网关子设备',
    `communication_type` int                                                          NOT NULL DEFAULT '1' COMMENT '通信方式 101WIFI 102以太网 103蜂窝网络2G 104蜂窝网络3G 105蜂窝网络4G 106蜂窝网络5G 107NB-IOT 199其他 201WIFI 202以太网 203蜂窝网络2G 204蜂窝网络3G 205蜂窝网络4G 206蜂窝网络5G 207NB-IOT 299其他 301WIFI 302以太网 303RS485 304RS232 305RS422 306LoRa 307Zigbee 308蓝牙/BLE 399其他',
    `protocol_type`      int                                                          NOT NULL DEFAULT '1' COMMENT '协议类型 1MQTT 2TCP 3HTTP',
    `protocol_format`    int                                                          NOT NULL DEFAULT '1' COMMENT '协议格式 101JSON 201ModbusRTU 202二进制 203普通文本 301JSON 302普通文本',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 8
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='设备类型表';


-- `iot-cloud`.device_type_attribute definition

CREATE TABLE `device_type_attribute`
(
    `id`                 bigint      NOT NULL AUTO_INCREMENT COMMENT '自增ID',
    `rel_device_type_id` bigint      NOT NULL COMMENT '设备类型表',
    `name`               varchar(20) NOT NULL COMMENT '属性名称',
    `code`               varchar(20) NOT NULL COMMENT '属性标识符',
    `type`               int         NOT NULL DEFAULT '1' COMMENT '类型 1设备上报 2云端下发 3设备云端共享 4云端私有',
    `data_type`          int         NOT NULL DEFAULT '1' COMMENT '数据类型 1数值 2开关 3文本',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='设备类型属性表';


-- `iot-cloud`.device_type_attribute_modbus definition

CREATE TABLE `device_type_attribute_modbus`
(
    `id`                           bigint NOT NULL AUTO_INCREMENT COMMENT '自增ID',
    `rel_device_type_id`           bigint NOT NULL COMMENT '设备类型表',
    `rel_device_type_attribute_id` bigint NOT NULL COMMENT '设备类型属性表',
    `slave_address`                int    NOT NULL DEFAULT '1' COMMENT '从机地址',
    `register_address`             int    NOT NULL DEFAULT '1' COMMENT '寄存器地址',
    `read_write_type`              int    NOT NULL DEFAULT '1' COMMENT '读写类型 1只读 2读写',
    `data_type`                    int    NOT NULL DEFAULT '1' COMMENT '数据类型 0 位 1 16位无符号整数 2 16位有符号整数 3 32位有符号整数 4 32位无符号整数',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 9
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='设备类型属性Modbus表';


-- `iot-cloud`.user_info definition

CREATE TABLE `user_info`
(
    `id`      bigint                                                       NOT NULL AUTO_INCREMENT COMMENT '自增ID',
    `email`   varchar(100)                                                 NOT NULL COMMENT '邮箱',
    `account` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '账号',
    `secret`  varchar(10)                                                  NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `user_info_email_IDX` (`email`) USING BTREE,
    UNIQUE KEY `user_info_account_uindex` (`account`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 7
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='用户信息表';