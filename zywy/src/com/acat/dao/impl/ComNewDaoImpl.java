package com.acat.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.acat.dao.ComNewDao;
import com.acat.pojo.ComIntro;
import com.acat.pojo.ComNew;
import com.acat.util.DbcpUtil;
import com.acat.util.JdbcUtil;

public class ComNewDaoImpl implements ComNewDao{
	private QueryRunner qr = new QueryRunner(DbcpUtil.getDataSource());
	@Override
	public void addComNew(ComNew cn) {
		try{
			qr.update("insert into zyw_com_new(time,title,control) values(?,?,?)",cn.getTime(),cn.getTitle(),cn.getControl());
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateComNew(ComNew cn) {
		try{
			qr.update("update zyw_com_new set time=?,title=?,control=? where comid=?",cn.getTime(),cn.getTitle(),cn.getControl(),cn.getComid());
		}catch(Exception e){
			throw new RuntimeException(e);
		}	
	}

	@Override
	public void deleteComNew(int comid) {
		try{
			qr.update("delete from zyw_com_new where comid=?",comid);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<ComNew> findAll() {
		try{
			return qr.query("select * from zyw_com_new order by comid desc", new BeanListHandler<ComNew>(ComNew.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public ComNew findComNew(int comid) {
		try{
			return qr.query("select * from zyw_com_new where comid = ?", new BeanHandler<ComNew>(ComNew.class),comid);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public ComNew findMaxComNew() {
		try{
			return qr.query("select * from zyw_com_new where comid = (select max(comid) from zyw_com_new)", new BeanHandler<ComNew>(ComNew.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<ComNew> query(String title) {
		try{
			return qr.query("select * from zyw_com_new where title like ?", new BeanListHandler<ComNew>(ComNew.class),title);
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
			stmt = conn.prepareStatement("select count(*) from zyw_com_new");
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
	public List<ComNew> findPageRecords(int startIndex, int pagesize) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<ComNew> lc = new ArrayList<ComNew>();
		
		try{
			conn = JdbcUtil.getConnection();
			stmt = conn.prepareStatement("select * from zyw_com_new order by comid desc limit ?,?");
			stmt.setInt(1, startIndex);
			stmt.setInt(2, pagesize);
			rs = stmt.executeQuery();
			while(rs.next()){
				ComNew tn = new ComNew();
				tn.setComid(rs.getInt("comid"));
				tn.setTime(rs.getString("time"));
				tn.setTitle(rs.getString("title"));
				tn.setControl(rs.getString("control"));
				lc.add(tn);
			}
			return lc;
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.liu(rs, stmt, conn);
		}
	}

}
