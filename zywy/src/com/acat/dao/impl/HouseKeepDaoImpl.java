package com.acat.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.acat.dao.HouseKeepDao;
import com.acat.pojo.ComNew;
import com.acat.pojo.HouseKeep;
import com.acat.util.DbcpUtil;
import com.acat.util.JdbcUtil;

public class HouseKeepDaoImpl implements HouseKeepDao{
	private QueryRunner qr = new QueryRunner(DbcpUtil.getDataSource());
	@Override
	public void addHouseKeep(HouseKeep hk) {
		try{
			qr.update("insert into zyw_house_keep(time,title,control) values(?,?,?)",hk.getTime(),hk.getTitle(),hk.getControl());
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateHouseKeep(HouseKeep hk) {
		try{
			qr.update("update zyw_house_keep set time=?,title=?,control=? where houseid=?",hk.getTime(),hk.getTitle(),hk.getControl(),hk.getHouseid());
		}catch(Exception e){
			throw new RuntimeException(e);
		}	
	}

	@Override
	public void deleteHouseKeep(int houseid) {
		try{
			qr.update("delete from zyw_house_keep where houseid=?",houseid);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<HouseKeep> findAll() {
		try{
			return qr.query("select * from zyw_house_keep order by houseid desc", new BeanListHandler<HouseKeep>(HouseKeep.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public HouseKeep findHouseKeep(int houseid) {
		try{
			return qr.query("select * from zyw_house_keep where houseid = ?", new BeanHandler<HouseKeep>(HouseKeep.class),houseid);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public HouseKeep findMaxHouseKeep() {
		try{
			return qr.query("select * from zyw_house_keep where houseid = (select max(houseid) from zyw_house_keep)", new BeanHandler<HouseKeep>(HouseKeep.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<HouseKeep> query(String title) {
		try{
			return qr.query("select * from zyw_house_keep where title like ?", new BeanListHandler<HouseKeep>(HouseKeep.class),title);
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
			stmt = conn.prepareStatement("select count(*) from zyw_house_keep");
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
	public List<HouseKeep> findPageRecords(int startIndex, int pagesize) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<HouseKeep> lc = new ArrayList<HouseKeep>();
		
		try{
			conn = JdbcUtil.getConnection();
			stmt = conn.prepareStatement("select * from zyw_house_keep order by houseid desc limit ?,?");
			stmt.setInt(1, startIndex);
			stmt.setInt(2, pagesize);
			rs = stmt.executeQuery();
			while(rs.next()){
				HouseKeep hk = new HouseKeep();
				hk.setHouseid(rs.getInt("houseid"));
				hk.setTime(rs.getString("time"));
				hk.setTitle(rs.getString("title"));
				hk.setControl(rs.getString("control"));
				lc.add(hk);
			}
			return lc;
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.liu(rs, stmt, conn);
		}
	}

}
