package iot.cloud.backend.tcp.bus;

import io.netty.channel.Channel;
import io.netty.util.AttributeKey;
import iot.cloud.backend.common.utils.Modbus4jUtils;
import iot.cloud.backend.service.dto.ReqDtoGetDeviceInfo;
import iot.cloud.backend.service.dto.ResDtoGetDeviceTypeAttributeModbus;
import iot.cloud.backend.service.modules.device.DeviceInfoService;
import iot.cloud.backend.service.result.ResResult;
import iot.cloud.backend.tcp.TcpServerUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import static iot.cloud.backend.common.utils.constant.ConstantForTCP.*;

/**
 * @author weichuang
 */
@Slf4j
@Component
public class BusForModbus {
    @Resource
    private DeviceInfoService deviceInfoService;

    @Resource
    private Executor modbusSendExecutor;

    @Scheduled(initialDelay = 30, timeUnit = TimeUnit.SECONDS, fixedRate = 10)
    public void handleSeconds10() {
        log.info("handleSeconds10");
        AttributeKey<Boolean> register = AttributeKey.valueOf(KEY_REGISTER);
        AttributeKey<Boolean> canBus = AttributeKey.valueOf(KEY_CAN_BUS);
        for (Channel channel : TcpServerUtils.clients) {
            if (channel.attr(register).get()) {
                modbusSendExecutor.execute(() -> {
                    //
                    AttributeKey<String> code = AttributeKey.valueOf(KEY_CODE);
                    String codeValue = channel.attr(code).get();
                    //
                    ResResult<List<ResDtoGetDeviceTypeAttributeModbus>> resResult =
                            deviceInfoService.getAttributeModbusByCodeAndTimeBus(new ReqDtoGetDeviceInfo(codeValue, 10, 's'));
                    List<ResDtoGetDeviceTypeAttributeModbus> list = resResult.getData();
                    //
                    long startTimeMillis = System.currentTimeMillis();
                    int whichDoingIndex = 0;
                    while (whichDoingIndex < list.size() && System.currentTimeMillis() - startTimeMillis <= 1000 * list.size()) {
                        if (channel.attr(canBus).get()) {
                            //
                            log.debug("whichDoingIndex = {}", whichDoingIndex);
                            log.debug("channel = {}", channel.id());
                            try {
                                ResDtoGetDeviceTypeAttributeModbus resDtoGetDeviceTypeAttributeModbus = list.get(whichDoingIndex);
                                byte[] data = Modbus4jUtils.readHoldingRegister(resDtoGetDeviceTypeAttributeModbus.getSlaveAddress(), resDtoGetDeviceTypeAttributeModbus.getRegisterAddress(), 1);
                                channel.writeAndFlush(data);
                                whichDoingIndex = whichDoingIndex + 1;
                                channel.attr(AttributeKey.valueOf(KEY_CAN_BUS)).set(false);
                                //
                                Thread.sleep(20);
                            } catch (Exception e) {
                                log.error(e.getMessage(), e);
                            }
                        } else {
                            //
                            log.debug("sleep whichDoingIndex = {}", whichDoingIndex);
                            try {
                                Thread.sleep(30);
                            } catch (InterruptedException e) {
                                log.error(e.getMessage(), e);
                            }
                        }
                    }
                    //
                    log.info("{} bus take up time {}ms with size = {}", channel.id(), System.currentTimeMillis() - startTimeMillis, list.size());
                });
            } else {
                log.warn("no register");
            }
        }
    }
}
