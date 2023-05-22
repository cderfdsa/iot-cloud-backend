package iot.cloud.backend.config;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author weichuang
 */
@Configuration
public class ConfigForSentinel {

    @PostConstruct
    public void initFlowRules() {
        List<FlowRule> flowRuleList = new ArrayList<>();
        //
        FlowRule flowRuleForValidateCode = new FlowRule();
        flowRuleForValidateCode.setResource("validateCode");
        flowRuleForValidateCode.setGrade(RuleConstant.FLOW_GRADE_QPS);
        flowRuleForValidateCode.setCount(10);
        //
        flowRuleList.add(flowRuleForValidateCode);
        //
        FlowRuleManager.loadRules(flowRuleList);
    }
}
