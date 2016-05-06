package com.chinanetcenter.war.itop.controller.jbpm;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import junit.framework.Assert;

import org.jbpm.api.ExecutionService;
import org.jbpm.api.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chinanetcenter.war.itop.controller.BaseController;
import com.leweiyou.jbpm.JBPM;

@Controller
@RequestMapping("/leave")
public class LeaveController extends BaseController{

	@Autowired
	private JBPM jbpm;
	
	@RequestMapping("/doLeave")
	public void doLeave(){
		//开始
		ExecutionService es = jbpm.getExecutionService();
		ProcessInstance pi = es.startProcessInstanceByKey("Helloword");
		Set<String> ans = pi.findActiveActivityNames();
		String pid = pi.getId();
		Assert.assertTrue(ans != null && ans.contains("填写申请"));
		
		//填写请假单
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("day", 10);
		map.put("reason", "请假");
		pi = es.signalExecutionById(pid, map);
		ans = pi.findActiveActivityNames();
		Assert.assertTrue(ans != null && ans.contains("经理审批"));
		
		//经理审批
		pi = es.signalExecutionById(pid,"同意");
		ans = pi.findActiveActivityNames();
		Assert.assertTrue(ans != null && ans.contains("老板审批"));
		
		//老板审批
		Map<String,Object> map1 = new HashMap<String,Object>();
		map1.put("reason", "老板不同意：项目太紧，过两天请假吧");
		pi = es.signalExecutionById(pid,"驳回",map1);
		ans = pi.findActiveActivityNames();
		Assert.assertTrue(ans != null && ans.contains("填写申请"));
		Assert.assertTrue(!pi.isEnded());
	}
}
