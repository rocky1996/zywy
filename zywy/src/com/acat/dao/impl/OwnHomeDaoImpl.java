package com.acat.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.acat.dao.OwnHomeDao;
import com.acat.pojo.ComNew;
import com.acat.pojo.OwnHome;
import com.acat.util.DbcpUtil;
import com.acat.util.JdbcUtil;

public class OwnHomeDaoImpl implements OwnHomeDao{
	private QueryRunner qr = new QueryRunner(DbcpUtil.getDataSource());
	@Override
	public void addOwnHome(OwnHome oh) {
		try{
			qr.update("insert into zyw_own_home(time,title,control) values(?,?,?)",oh.getTime(),oh.getTitle(),oh.getControl());
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateOwnHome(OwnHome oh) {
		try{
			qr.update("update zyw_own_home set time=?,title=?,control=? where ownid=?",oh.getTime(),oh.getTitle(),oh.getControl(),oh.getOwnid());
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deleteOwnHome(int ownid) {
		try{
			qr.update("delete from zyw_own_home where ownid=?",ownid);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<OwnHome> findAll() {
		try{
			return qr.query("select * from zyw_own_home order by ownid desc", new BeanListHandler<OwnHome>(OwnHome.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public OwnHome findOwnHome(int ownid) {
		try{
			return qr.query("select * from zyw_own_home where ownid = ?", new BeanHandler<OwnHome>(OwnHome.class),ownid);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public OwnHome findMaxOwnHome() {
		try{
			return qr.query("select * from zyw_own_home where ownid = (select max(ownid) from zyw_own_home)", new BeanHandler<OwnHome>(OwnHome.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<OwnHome> query(String title) {
		try{
			return qr.query("select * from zyw_own_home where title like ?", new BeanListHandler<OwnHome>(OwnHome.class),title);
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
			stmt = conn.prepareStatement("select count(*) from zyw_own_home");
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
	public List<OwnHome> findPageRecords(int startIndex, int pagesize) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<OwnHome> lc = new ArrayList<OwnHome>();
		
		try{
			conn = JdbcUtil.getConnection();
			stmt = conn.prepareStatement("select * from zyw_own_home order by ownid desc limit ?,?");
			stmt.setInt(1, startIndex);
			stmt.setInt(2, pagesize);
			rs = stmt.executeQuery();
			while(rs.next()){
				OwnHome oh = new OwnHome();
				oh.setOwnid(rs.getInt("ownid"));
				oh.setTime(rs.getString("time"));
				oh.setTitle(rs.getString("title"));
				oh.setControl(rs.getString("control"));
				lc.add(oh);
			}
			return lc;
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.liu(rs, stmt, conn);
		}
	}

}
