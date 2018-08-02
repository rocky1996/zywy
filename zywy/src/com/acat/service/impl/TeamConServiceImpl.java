package com.acat.service.impl;

import java.util.List;

import com.acat.dao.TeamConDao;
import com.acat.dao.impl.TeamConDaoImpl;
import com.acat.pojo.Page;
import com.acat.pojo.TeamCon;
import com.acat.service.TeamConService;
import com.acat.util.getNowTime;

public class TeamConServiceImpl implements TeamConService{
	private TeamConDao tcd = new TeamConDaoImpl();
	@Override
	public void addTeamCon(TeamCon tc) {
		tc.setTime(getNowTime.getTime());
		tcd.addTeamCon(tc);
	}

	@Override
	public void updateTeamCon(TeamCon tc) {
		tc.setTime(getNowTime.getTime());
		tcd.updateTeamCon(tc);
	}

	@Override
	public void deleteTeamCon(int teamid) {
		tcd.deleteTeamCon(teamid);	
	}

	@Override
	public List<TeamCon> findAll() {
		return tcd.findAll();
	}

	@Override
	public TeamCon findTeamCon(int teamid) {
		return tcd.findTeamCon(teamid);
	}

	@Override
	public TeamCon findMaxTeamCon() {
		return tcd.findMaxTeamCon();
	}

	@Override
	public List<TeamCon> query(String title) {
		return tcd.query(title);
	}

	@Override
	public Page findPageRecords(String pagenum) {
		int num = 1;//默认值ֵ
		
		if(pagenum != null && !"".equals(pagenum.trim())){//如果用户传进来的是null会这是空字符串，则说明第一次访问，
			num = Integer.parseInt(pagenum);
		}
		
		int totalrecords = tcd.getTotalRecords();
		Page page = new Page(num, totalrecords);
		List records = tcd.findPageRecords(page.getStartIndex(), page.getPagesize());
		page.setRecords(records);
		return page;
	}

}
