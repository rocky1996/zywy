package com.acat.service.impl;

import java.util.List;

import com.acat.dao.JoinZyDao;
import com.acat.dao.impl.JoinZyDaoImpl;
import com.acat.pojo.JoinZy;
import com.acat.pojo.Page;
import com.acat.service.JoinZyService;
import com.acat.util.getNowTime;

public class JoinZyServiceImpl implements JoinZyService{
	private JoinZyDao jzd = new JoinZyDaoImpl();
	@Override
	public void addJoinZy(JoinZy jz) {
		jz.setTime(getNowTime.getTime());
		jzd.addJoinZy(jz);
	}

	@Override
	public void updateJoinZy(JoinZy jz) {
		jz.setTime(getNowTime.getTime());
		jzd.updateJoinZy(jz);
	}

	@Override
	public void deleteJoinZy(int joinid) {
		jzd.deleteJoinZy(joinid);	
	}

	@Override
	public List<JoinZy> findAll() {
		return jzd.findAll();
	}

	@Override
	public JoinZy findJoinZy(int joinid) {
		return jzd.findJoinZy(joinid);
	}

	@Override
	public Page findPageRecords(String pagenum) {
		int num = 1;//默认值ֵ
		
		if(pagenum != null && !"".equals(pagenum.trim())){//如果用户传进来的是null会这是空字符串，则说明第一次访问，
			num = Integer.parseInt(pagenum);
		}
		
		int totalrecords = jzd.getTotalRecords();
		Page page = new Page(num, totalrecords);
		List records =  jzd.findPageRecords(page.getStartIndex(), page.getPagesize());
		page.setRecords(records);
		return page;
	}

	@Override
	public List<JoinZy> query(String title) {
		return jzd.query(title);
	}

	@Override
	public JoinZy findMaxJoinZy() {
		return jzd.findMaxJoinZy();
	}

}
