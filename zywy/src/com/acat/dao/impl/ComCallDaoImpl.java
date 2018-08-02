package com.acat.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.acat.dao.ComCallDao;
import com.acat.pojo.ComCall;
import com.acat.pojo.ComNew;
import com.acat.util.DbcpUtil;
import com.acat.util.JdbcUtil;

public class ComCallDaoImpl implements ComCallDao{
	private QueryRunner qr = new QueryRunner(DbcpUtil.getDataSource());
	@Override
	public void addComCall(ComCall cc) {
		try{
			qr.update("insert into zyw_com_call(time,title,control) values(?,?,?)",cc.getTime(),cc.getTitle(),cc.getControl());
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateComCall(ComCall cc) {
		try{
			qr.update("update zyw_com_call set time=?,title=?,control=? where comid=?",cc.getTime(),cc.getTitle(),cc.getControl(),cc.getComid());
		}catch(Exception e){
			throw new RuntimeException(e);
		}	
	}

	@Override
	public void deleteComCall(int comid) {
		try{
			qr.update("delete from zyw_com_call where comid=?",comid);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<ComCall> findAll() {
		try{
			return qr.query("select * from zyw_com_call order by comid desc", new BeanListHandler<ComCall>(ComCall.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public ComCall findComCall(int comid) {
		try{
			return qr.query("select * from zyw_com_call where comid = ?", new BeanHandler<ComCall>(ComCall.class),comid);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public ComCall findMaxComCall() {
		try{
			return qr.query("select * from zyw_com_call where comid = (select max(comid) from zyw_com_call)", new BeanHandler<ComCall>(ComCall.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<ComCall> query(String title) {
		try{
			return qr.query("select * from zyw_com_call where title like ?", new BeanListHandler<ComCall>(ComCall.class),title);
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
			stmt = conn.prepareStatement("select count(*) from zyw_com_call");
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
	public List<ComCall> findPageRecords(int startIndex, int pagesize) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<ComCall> lc = new ArrayList<ComCall>();
		
		try{
			conn = JdbcUtil.getConnection();
			stmt = conn.prepareStatement("select * from zyw_com_call order by comid desc limit ?,?");
			stmt.setInt(1, startIndex);
			stmt.setInt(2, pagesize);
			rs = stmt.executeQuery();
			while(rs.next()){
				ComCall cc = new ComCall();
				cc.setComid(rs.getInt("comid"));
				cc.setTime(rs.getString("time"));
				cc.setTitle(rs.getString("title"));
				cc.setControl(rs.getString("control"));
				lc.add(cc);
			}
			return lc;
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.liu(rs, stmt, conn);
		}
	}

}
