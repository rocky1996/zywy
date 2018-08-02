package com.acat.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.acat.dao.ComCulDao;
import com.acat.pojo.ComCul;
import com.acat.pojo.ComIntro;
import com.acat.util.DbcpUtil;
import com.acat.util.JdbcUtil;

public class ComCulDaoImpl implements ComCulDao{
	private QueryRunner qr = new QueryRunner(DbcpUtil.getDataSource());

	@Override
	public void addComCul(ComCul cc) {
		try{
			qr.update("insert into zyw_com_cul(time,title,control) values(?,?,?)",cc.getTime(),cc.getTitle(),cc.getControl());
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateComCul(ComCul cc) {
		try{
			qr.update("update zyw_com_cul set time=?,title=?,control=? where comid=?",cc.getTime(),cc.getTitle(),cc.getControl(),cc.getComid());
		}catch(Exception e){
			throw new RuntimeException(e);
		}	
	}

	@Override
	public void deleteComCul(int comid) {
		try{
			qr.update("delete from zyw_com_cul where comid=?",comid);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<ComCul> findAll() {
		try{
			return qr.query("select * from zyw_com_cul order by comid desc", new BeanListHandler<ComCul>(ComCul.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public ComCul findComCul(int comid) {
		try{
			return qr.query("select * from zyw_com_cul where comid = ?", new BeanHandler<ComCul>(ComCul.class),comid);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public ComCul findMaxComCul() {
		try{
			return qr.query("select * from zyw_com_cul where comid = (select max(comid) from zyw_com_cul)", new BeanHandler<ComCul>(ComCul.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<ComCul> query(String title) {
		try{
			return qr.query("select * from zyw_com_cul where title like ?", new BeanListHandler<ComCul>(ComCul.class),title);
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
			stmt = conn.prepareStatement("select count(*) from zyw_com_cul");
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
	public List<ComCul> findPageRecords(int startIndex, int pagesize) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<ComCul> lc = new ArrayList<ComCul>();
		
		try{
			conn = JdbcUtil.getConnection();
			stmt = conn.prepareStatement("select * from zyw_com_cul order by comid desc limit ?,?");
			stmt.setInt(1, startIndex);
			stmt.setInt(2, pagesize);
			rs = stmt.executeQuery();
			while(rs.next()){
				ComCul cc = new ComCul();
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
