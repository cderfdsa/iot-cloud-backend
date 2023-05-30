package iot.cloud.backend.tcp;

import com.alibaba.fastjson2.JSONObject;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;
import iot.cloud.backend.common.utils.HexUtils;
import iot.cloud.backend.common.utils.Modbus4jUtils;
import iot.cloud.backend.common.utils.StrUtils;
import iot.cloud.backend.service.modules.device.DeviceInfoService;
import iot.cloud.backend.service.utils.SpringApplicationUtils;
import iot.cloud.backend.tcp.mqtt.TcpForMqttClient;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.nio.charset.StandardCharsets;

import static iot.cloud.backend.common.utils.constant.ConstantForTCP.*;
import static iot.cloud.backend.tcp.TcpServerUtils.clients;

/**
 * @author weichuang
 */
@Slf4j
public class SocketHandler extends ChannelInboundHandlerAdapter {
    //


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //
        log.info("channelRead ID,{}", ctx.channel().id().asShortText());
        //
        AttributeKey<Boolean> register = AttributeKey.valueOf(KEY_REGISTER);
        if (msg instanceof byte[] && !ctx.channel().attr(register).get()) {
            //
            byte[] msgByteArr = (byte[]) msg;
            log.info("channelRead msg {}", HexUtils.encodeHexStr(msgByteArr, false));
            String msgStr = StrUtils.str(msgByteArr, StandardCharsets.UTF_8);
            log.info("channelRead msgStr {}", msgStr);
            //
            String[] strArr = msgStr.split("&");
            if (strArr.length == 2) {
                //
                String code = strArr[0];
                String pwd = strArr[1];
                DeviceInfoService deviceInfoService = SpringApplicationUtils.getBean(DeviceInfoService.class);
                boolean auth = deviceInfoService.auth(code, pwd);
                if (auth) {
                    ctx.channel().attr(AttributeKey.valueOf(KEY_CODE)).set(code);
                    ctx.channel().attr(AttributeKey.valueOf(KEY_REGISTER)).set(true);
                    ctx.channel().attr(AttributeKey.valueOf(KEY_CAN_BUS)).set(true);
                    // connect and subscribe to mqtt broker
                    try {
                        TcpForMqttClient tcpForMqttClient = new TcpForMqttClient(ctx.channel(), code, pwd);
                        ctx.channel().attr(AttributeKey.valueOf(KEY_MQTT_CLIENT)).set(tcpForMqttClient);
                    } catch (MqttException e) {
                        log.error("connect and subscribe to mqtt broker");
                        log.error(e.getMessage(), e);
                    }
                } else {
                    ctx.channel().close();
                    log.warn("auth fail = {},{}", code, pwd);
                }
            } else {
                ctx.channel().close();
                log.warn("strArr = {}", strArr);
            }
        } else if (msg instanceof byte[] && ctx.channel().attr(register).get()) {
            //
            byte[] msgByteArr = (byte[]) msg;
            log.info("channelRead msg {}", HexUtils.encodeHexStr(msgByteArr));
            ctx.channel().attr(AttributeKey.valueOf(KEY_CAN_BUS)).set(true);
            //
            AttributeKey<String> attrCode = AttributeKey.valueOf(KEY_ATTR_CODE);
            String code = ctx.channel().attr(attrCode).get();
            if (msgByteArr.length <= 4) {
                log.debug("channelRead msgByteArr = {} >> heart ping", msgByteArr);
                return;
            }
            long value = Modbus4jUtils.readHoldingRegisterResLong(msgByteArr);
            log.info("channelRead value = {}", value);
            // publish to mqtt broker
            AttributeKey<TcpForMqttClient> tcpForMqttClientAttributeKey = AttributeKey.valueOf(KEY_MQTT_CLIENT);
            TcpForMqttClient tcpForMqttClient = ctx.channel().attr(tcpForMqttClientAttributeKey).get();
            if (tcpForMqttClient != null) {
                tcpForMqttClient.publishForDeviceAttribute(JSONObject.of(code, value));
            } else {
                log.warn("publish to mqtt broker fail , because tcpForMqttClient is null. ");
            }
        } else {
            log.error("channelRead error");
        }
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //
        log.info("handlerAdded ID,{}", ctx.channel().id().asShortText());
        ctx.channel().attr(AttributeKey.valueOf(KEY_REGISTER)).set(false);
        ctx.channel().attr(AttributeKey.valueOf(KEY_CAN_BUS)).set(false);
        ctx.channel().attr(AttributeKey.valueOf(KEY_ATTR_CODE)).set("");
        ctx.channel().attr(AttributeKey.valueOf(KEY_MQTT_CLIENT)).set(null);
        clients.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        //
        log.info("handlerRemoved ID,{}", ctx.channel().id().asShortText());
        clients.remove(ctx.channel());
        // disconnect to mqtt broker
        AttributeKey<TcpForMqttClient> tcpForMqttClientAttributeKey = AttributeKey.valueOf(KEY_MQTT_CLIENT);
        TcpForMqttClient tcpForMqttClient = ctx.channel().attr(tcpForMqttClientAttributeKey).get();
        if (tcpForMqttClient != null) {
            tcpForMqttClient.getMqttClient().disconnect();
            tcpForMqttClient.getMqttClient().close();
        } else {
            log.warn("disconnect to mqtt broker fail , because tcpForMqttClient is null. ");
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //
        log.info("exceptionCaught ID,{}", ctx.channel().id().asShortText());
        log.error(cause.toString(), cause);
        ctx.channel().close();
        clients.remove(ctx.channel());
        // TODO disconnect to mqtt broker
    }
}
