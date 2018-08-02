package com.acat.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.acat.dao.MedNewDao;
import com.acat.pojo.ComCul;
import com.acat.pojo.MedNew;
import com.acat.util.DbcpUtil;
import com.acat.util.JdbcUtil;

public class MedNewDaoImpl implements MedNewDao{
	private QueryRunner qr = new QueryRunner(DbcpUtil.getDataSource());
	@Override
	public void addMedNew(MedNew mn) {
		try{
			qr.update("insert into zyw_med_new(time,title,control) values(?,?,?)",mn.getTime(),mn.getTitle(),mn.getControl());
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateMedNew(MedNew mn) {
		try{
			qr.update("update zyw_med_new set time=?,title=?,control=? where medid=?",mn.getTime(),mn.getTitle(),mn.getControl(),mn.getMedid());
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deleteMedNew(int medid) {
		try{
			qr.update("delete from zyw_med_new where medid=?",medid);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<MedNew> findAll() {
		try{
			return qr.query("select * from zyw_med_new order by medid desc", new BeanListHandler<MedNew>(MedNew.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public MedNew findMedNew(int medid) {
		try{
			return qr.query("select * from zyw_med_new where medid = ?", new BeanHandler<MedNew>(MedNew.class),medid);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public MedNew findMaxMedNew() {
		try{
			return qr.query("select * from zyw_med_new where medid = (select max(medid) from zyw_med_new)", new BeanHandler<MedNew>(MedNew.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<MedNew> query(String title) {
		try{
			return qr.query("select * from zyw_med_new where title like ?", new BeanListHandler<MedNew>(MedNew.class),title);
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
			stmt = conn.prepareStatement("select count(*) from zyw_med_new");
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
	public List<MedNew> findPageRecords(int startIndex, int pagesize) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<MedNew> lc = new ArrayList<MedNew>();
		
		try{
			conn = JdbcUtil.getConnection();
			stmt = conn.prepareStatement("select * from zyw_med_new order by medid desc limit ?,?");
			stmt.setInt(1, startIndex);
			stmt.setInt(2, pagesize);
			rs = stmt.executeQuery();
			while(rs.next()){
				MedNew mn = new MedNew();
				mn.setMedid(rs.getInt("medid"));
				mn.setTime(rs.getString("time"));
				mn.setTitle(rs.getString("title"));
				mn.setControl(rs.getString("control"));
				lc.add(mn);
			}
			return lc;
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.liu(rs, stmt, conn);
		}
	}

}
