package com.acat.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.acat.dao.LocalDynDao;
import com.acat.pojo.ComIntro;
import com.acat.pojo.LocalDyn;
import com.acat.util.DbcpUtil;
import com.acat.util.JdbcUtil;

public class LocalDynDaoImpl implements LocalDynDao{
	private QueryRunner qr = new QueryRunner(DbcpUtil.getDataSource()); 
	@Override
	public void addLocalDyn(LocalDyn ld) {
		try{
			qr.update("insert into zyw_local_dyn(time,title,control) values(?,?,?)",ld.getTime(),ld.getTitle(),ld.getControl());
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateLocalDyn(LocalDyn ld) {
		try{
			qr.update("update zyw_local_dyn set time=?,title=?,control=? where localid=?",ld.getTime(),ld.getTitle(),ld.getControl(),ld.getLocalid());
		}catch(Exception e){
			throw new RuntimeException(e);
		}	
	}

	@Override
	public void deleteLocalDyn(int localid) {
		try{
			qr.update("delete from zyw_local_dyn where localid=?",localid);
		}catch(Exception e){
			throw new RuntimeException(e);
		}		
	}

	@Override
	public List<LocalDyn> findAll() {
		try{
			return qr.query("select * from zyw_local_dyn order by localid desc", new BeanListHandler<LocalDyn>(LocalDyn.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public LocalDyn findLocalDyn(int localid) {
		try{
			return qr.query("select * from zyw_local_dyn where localid = ?", new BeanHandler<LocalDyn>(LocalDyn.class),localid);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public LocalDyn findMaxLocalDyn() {
		try{
			return qr.query("select * from zyw_local_dyn where localid = (select max(localid) from zyw_local_dyn)", new BeanHandler<LocalDyn>(LocalDyn.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<LocalDyn> query(String title) {
		try{
			return qr.query("select * from zyw_local_dyn where title like ?", new BeanListHandler<LocalDyn>(LocalDyn.class),title);
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
			stmt = conn.prepareStatement("select count(*) from zyw_local_dyn");
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
	public List<LocalDyn> findPageRecords(int startIndex, int pagesize) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<LocalDyn> lc = new ArrayList<LocalDyn>();
		
		try{
			conn = JdbcUtil.getConnection();
			stmt = conn.prepareStatement("select * from zyw_local_dyn order by localid desc limit ?,?");
			stmt.setInt(1, startIndex);
			stmt.setInt(2, pagesize);
			rs = stmt.executeQuery();
			while(rs.next()){
				LocalDyn ld = new LocalDyn();
				ld.setLocalid(rs.getInt("localid"));
				ld.setTime(rs.getString("time"));
				ld.setTitle(rs.getString("title"));
				ld.setControl(rs.getString("control"));
				lc.add(ld);
			}
			return lc;
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.liu(rs, stmt, conn);
		}
	}

}
