package com.acat.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.acat.dao.TeamConDao;
import com.acat.pojo.OrgStu;
import com.acat.pojo.TeamCon;
import com.acat.util.DbcpUtil;
import com.acat.util.JdbcUtil;

public class TeamConDaoImpl implements TeamConDao{
	private QueryRunner qr = new QueryRunner(DbcpUtil.getDataSource());
	@Override
	public void addTeamCon(TeamCon tc) {
		try{
			qr.update("insert into zyw_team_con(time,title,control) values(?,?,?)",tc.getTime(),tc.getTitle(),tc.getControl());
		}catch(Exception e){
			throw new RuntimeException(e);
		}	
	}

	@Override
	public void updateTeamCon(TeamCon tc) {
		try{
			qr.update("update zyw_team_con set time=?,title=?,control=? where teamid=?",tc.getTime(),tc.getTitle(),tc.getControl(),tc.getTeamid());
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deleteTeamCon(int teamid) {
		try{
			qr.update("delete from zyw_team_con where teamid=?",teamid);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<TeamCon> findAll() {
		try{
			return qr.query("select * from zyw_team_con order by teamid desc", new BeanListHandler<TeamCon>(TeamCon.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public TeamCon findTeamCon(int teamid) {
		try{
			return qr.query("select * from zyw_team_con where teamid = ?", new BeanHandler<TeamCon>(TeamCon.class),teamid);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public TeamCon findMaxTeamCon() {
		try{
			return qr.query("select * from zyw_team_con where teamid = (select max(teamid) from zyw_team_con)", new BeanHandler<TeamCon>(TeamCon.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<TeamCon> query(String title) {
		try{
			return qr.query("select * from zyw_team_con where title like ?", new BeanListHandler<TeamCon>(TeamCon.class),title);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public int getTotalRecords() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try{
			conn = JdbcUtil.getConnection();
			stmt = conn.prepareStatement("select count(*) from zyw_team_con");
			rs = stmt.executeQuery();
			if(rs.next()){
				return rs.getInt(1);
			}else{
				return 0;
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.liu(rs, stmt, conn);
		}
	}

	@Override
	public List<TeamCon> findPageRecords(int startIndex, int pagesize) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<TeamCon> lc = new ArrayList<TeamCon>();
		
		try{
			conn = JdbcUtil.getConnection();
			stmt = conn.prepareStatement("select * from zyw_team_con order by teamid desc limit ?,?");
			stmt.setInt(1, startIndex);
			stmt.setInt(2, pagesize);
			rs = stmt.executeQuery();
			while(rs.next()){
				TeamCon tc = new TeamCon();
				tc.setTeamid(rs.getInt("teamid"));
				tc.setTime(rs.getString("time"));
				tc.setTitle(rs.getString("title"));
				tc.setControl(rs.getString("control"));
				lc.add(tc);
			}
			return lc;
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.liu(rs, stmt, conn);
		}
	}

}
