package com.acat.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.acat.dao.ComIntroDao;
import com.acat.pojo.ComIntro;
import com.acat.util.DbcpUtil;
import com.acat.util.JdbcUtil;

public class ComIntroDaoImpl implements ComIntroDao{
	private QueryRunner qr = new QueryRunner(DbcpUtil.getDataSource());
	@Override
	public void addComIntro(ComIntro ci) {
		try{
			qr.update("insert into zyw_com_intro(time,title,control) values(?,?,?)",ci.getTime(),ci.getTitle(),ci.getControl());
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateComIntro(ComIntro ci) {
		try{
			qr.update("update zyw_com_intro set time=?,title=?,control=? where comid=?",ci.getTime(),ci.getTitle(),ci.getControl(),ci.getComid());
		}catch(Exception e){
			throw new RuntimeException(e);
		}	
	}

	@Override
	public void deleteComIntro(int comid) {
		try{
			qr.update("delete from zyw_com_intro where comid=?",comid);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<ComIntro> findAll() {
//		try{
//			return qr.query("select * from zyw_com_intro", new BeanListHandler<ComIntro>(ComIntro.class));
//		}catch(Exception e){
//			throw new RuntimeException(e);
//		}
		
		try{
			return qr.query("select * from zyw_com_intro order by comid desc", new BeanListHandler<ComIntro>(ComIntro.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public ComIntro findComIntro(int comid) {
		try{
			return qr.query("select * from zyw_com_intro where comid = ?", new BeanHandler<ComIntro>(ComIntro.class),comid);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public ComIntro findMaxComIntro() {
		try{
			return qr.query("select * from zyw_com_intro where comid = (select max(comid) from zyw_com_intro)", new BeanHandler<ComIntro>(ComIntro.class));
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
			stmt = conn.prepareStatement("select count(*) from zyw_com_intro");
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
	public List<ComIntro> findPageRecords(int startIndex, int pagesize) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<ComIntro> lc = new ArrayList<ComIntro>();
		
		try{
			conn = JdbcUtil.getConnection();
			stmt = conn.prepareStatement("select * from zyw_com_intro order by comid desc limit ?,?");
			stmt.setInt(1, startIndex);
			stmt.setInt(2, pagesize);
			rs = stmt.executeQuery();
			while(rs.next()){
				ComIntro ci = new ComIntro();
				ci.setComid(rs.getInt("comid"));
				ci.setTime(rs.getString("time"));
				ci.setTitle(rs.getString("title"));
				ci.setControl(rs.getString("control"));
				lc.add(ci);
			}
			return lc;
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.liu(rs, stmt, conn);
		}
	}

	@Override
	public List<ComIntro> query(String title) {
		try{
			return qr.query("select * from zyw_com_intro where title like ?", new BeanListHandler<ComIntro>(ComIntro.class),title);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
