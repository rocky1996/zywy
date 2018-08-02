package com.acat.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.acat.dao.ConRepDao;
import com.acat.pojo.CarRep;
import com.acat.pojo.ConRep;
import com.acat.util.DbcpUtil;
import com.acat.util.JdbcUtil;

public class ConRepDaoImpl implements ConRepDao{
	private QueryRunner qr = new QueryRunner(DbcpUtil.getDataSource());
	@Override
	public void addConRep(ConRep cr) {
		try{
			qr.update("insert into zyw_con_rep(time,title,control) values(?,?,?)",cr.getTime(),cr.getTitle(),cr.getControl());
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateConRep(ConRep cr) {
		try{
			qr.update("update zyw_con_rep set time=?,title=?,control=? where conid=?",cr.getTime(),cr.getTitle(),cr.getControl(),cr.getConid());
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deleteConRep(int conid) {
		try{
			qr.update("delete from zyw_con_rep where conid=?",conid);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<ConRep> findAll() {
		try{
			return qr.query("select * from zyw_con_rep order by conid desc", new BeanListHandler<ConRep>(ConRep.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public ConRep findConRep(int conid) {
		try{
			return qr.query("select * from zyw_con_rep where conid = ?", new BeanHandler<ConRep>(ConRep.class),conid);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public ConRep findMaxConRep() {
		try{
			return qr.query("select * from zyw_con_rep where conid = (select max(conid) from zyw_con_rep)", new BeanHandler<ConRep>(ConRep.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<ConRep> query(String title) {
		try{
			return qr.query("select * from zyw_con_rep where title like ?", new BeanListHandler<ConRep>(ConRep.class),title);
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
			stmt = conn.prepareStatement("select count(*) from zyw_con_rep");
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
	public List<ConRep> findPageRecords(int startIndex, int pagesize) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<ConRep> lc = new ArrayList<ConRep>();
		
		try{
			conn = JdbcUtil.getConnection();
			stmt = conn.prepareStatement("select * from zyw_con_rep order by conid desc limit ?,?");
			stmt.setInt(1, startIndex);
			stmt.setInt(2, pagesize);
			rs = stmt.executeQuery();
			while(rs.next()){
				ConRep cr = new ConRep();
				cr.setConid(rs.getInt("conid"));
				cr.setTime(rs.getString("time"));
				cr.setTitle(rs.getString("title"));
				cr.setControl(rs.getString("control"));
				lc.add(cr);
			}
			return lc;
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.liu(rs, stmt, conn);
		}
	}

}
