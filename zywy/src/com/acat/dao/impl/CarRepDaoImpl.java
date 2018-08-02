package com.acat.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.acat.dao.CarRepDao;
import com.acat.pojo.CarRep;
import com.acat.pojo.ComCul;
import com.acat.util.DbcpUtil;
import com.acat.util.JdbcUtil;

public class CarRepDaoImpl implements CarRepDao{
	private QueryRunner qr = new QueryRunner(DbcpUtil.getDataSource());
	@Override
	public void addCarRep(CarRep cr) {
		try{
			qr.update("insert into zyw_car_rep(time,title,control) values(?,?,?)",cr.getTime(),cr.getTitle(),cr.getControl());
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateCarRep(CarRep cr) {
		try{
			qr.update("update zyw_car_rep set time=?,title=?,control=? where carid=?",cr.getTime(),cr.getTitle(),cr.getControl(),cr.getCarid());
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deleteCarRep(int carid) {
		try{
			qr.update("delete from zyw_car_rep where carid=?",carid);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<CarRep> findAll() {
		try{
			return qr.query("select * from zyw_car_rep order by carid desc", new BeanListHandler<CarRep>(CarRep.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public CarRep findCarRep(int carid) {
		try{
			return qr.query("select * from zyw_car_rep where carid = ?", new BeanHandler<CarRep>(CarRep.class),carid);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public CarRep findMaxCarRep() {
		try{
			return qr.query("select * from zyw_car_rep where carid = (select max(carid) from zyw_car_rep)", new BeanHandler<CarRep>(CarRep.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<CarRep> query(String title) {
		try{
			return qr.query("select * from zyw_car_rep where title like ?", new BeanListHandler<CarRep>(CarRep.class),title);
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
			stmt = conn.prepareStatement("select count(*) from zyw_car_rep");
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
	public List<CarRep> findPageRecords(int startIndex, int pagesize) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<CarRep> lc = new ArrayList<CarRep>();
		
		try{
			conn = JdbcUtil.getConnection();
			stmt = conn.prepareStatement("select * from zyw_car_rep order by carid desc limit ?,?");
			stmt.setInt(1, startIndex);
			stmt.setInt(2, pagesize);
			rs = stmt.executeQuery();
			while(rs.next()){
				CarRep cr = new CarRep();
				cr.setCarid(rs.getInt("carid"));
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
