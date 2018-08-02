package com.acat.dao;

import java.util.List;
import com.acat.pojo.TeamCon;

public interface TeamConDao {
	void addTeamCon(TeamCon tc);
	void updateTeamCon(TeamCon tc);
	void deleteTeamCon(int teamid);
	List<TeamCon> findAll();
	TeamCon findTeamCon(int teamid);
	TeamCon findMaxTeamCon();
	List<TeamCon> query(String title);
	
    int getTotalRecords();
	
	List<TeamCon> findPageRecords(int startIndex,int pagesize);
}
