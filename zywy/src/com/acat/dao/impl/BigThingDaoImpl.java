package com.acat.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.acat.dao.BigThingDao;
import com.acat.pojo.BigThing;
import com.acat.pojo.ComCul;
import com.acat.util.DbcpUtil;
import com.acat.util.JdbcUtil;

public class BigThingDaoImpl implements BigThingDao{
	private QueryRunner qr = new QueryRunner(DbcpUtil.getDataSource());
	@Override
	public void addBigThing(BigThing bt) {
		try{
			qr.update("insert into zyw_big_thing(time,title,control) values(?,?,?)",bt.getTime(),bt.getTitle(),bt.getControl());
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateBigThing(BigThing bt) {
		try{
			qr.update("update zyw_big_thing set time=?,title=?,control=? where bigid=?",bt.getTime(),bt.getTitle(),bt.getControl(),bt.getBigid());
		}catch(Exception e){
			throw new RuntimeException(e);
		}	
	}

	@Override
	public void deleteBigThing(int bigid) {
		try{
			qr.update("delete from zyw_big_thing where bigid=?",bigid);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<BigThing> findAll() {
		try{
			return qr.query("select * from zyw_big_thing order by bigid desc", new BeanListHandler<BigThing>(BigThing.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public BigThing findBigThing(int bigid) {
		try{
			return qr.query("select * from zyw_big_thing where bigid = ?", new BeanHandler<BigThing>(BigThing.class),bigid);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public BigThing findMaxBigThing() {
		try{
			return qr.query("select * from zyw_big_thing where bigid = (select max(bigid) from zyw_big_thing)", new BeanHandler<BigThing>(BigThing.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<BigThing> query(String title) {
		try{
			return qr.query("select * from zyw_big_thing where title like ?", new BeanListHandler<BigThing>(BigThing.class),title);
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
			stmt = conn.prepareStatement("select count(*) from zyw_big_thing");
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
	public List<BigThing> findPageRecords(int startIndex, int pagesize) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<BigThing> lc = new ArrayList<BigThing>();
		
		try{
			conn = JdbcUtil.getConnection();
			stmt = conn.prepareStatement("select * from zyw_big_thing order by bigid desc limit ?,?");
			stmt.setInt(1, startIndex);
			stmt.setInt(2, pagesize);
			rs = stmt.executeQuery();
			while(rs.next()){
				BigThing bt = new BigThing();
				bt.setBigid(rs.getInt("bigid"));
				bt.setTime(rs.getString("time"));
				bt.setTitle(rs.getString("title"));
				bt.setControl(rs.getString("control"));
				lc.add(bt);
			}
			return lc;
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.liu(rs, stmt, conn);
		}
	}

}
