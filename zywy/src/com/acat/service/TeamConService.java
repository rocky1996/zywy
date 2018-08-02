package com.acat.service;

import java.util.List;

import com.acat.pojo.Page;
import com.acat.pojo.TeamCon;

public interface TeamConService {
	void addTeamCon(TeamCon tc);
	void updateTeamCon(TeamCon tc);
	void deleteTeamCon(int teamid);
	List<TeamCon> findAll();
	TeamCon findTeamCon(int teamid);
	TeamCon findMaxTeamCon();
	List<TeamCon> query(String title);
	
	public Page findPageRecords(String pagenum);
}
