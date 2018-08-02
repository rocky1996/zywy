package com.acat.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.acat.dao.JoinZyDao;
import com.acat.pojo.JoinZy;
import com.acat.pojo.LocalDyn;
import com.acat.util.DbcpUtil;
import com.acat.util.JdbcUtil;

public class JoinZyDaoImpl implements JoinZyDao{
	private QueryRunner qr = new QueryRunner(DbcpUtil.getDataSource()); 
	@Override
	public void addJoinZy(JoinZy jz) {
		try{
			qr.update("insert into zyw_join_zy(time,title,control) values(?,?,?)",jz.getTime(),jz.getTitle(),jz.getControl());
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateJoinZy(JoinZy jz) {
		try{
			qr.update("update zyw_join_zy set time=?,title=?,control=? where localid=?",jz.getTime(),jz.getTitle(),jz.getControl(),jz.getJoinid());
		}catch(Exception e){
			throw new RuntimeException(e);
		}	
	}

	@Override
	public void deleteJoinZy(int joinid) {
		try{
			qr.update("delete from zyw_join_zy where joinid=?",joinid);
		}catch(Exception e){
			throw new RuntimeException(e);
		}	
	}

	@Override
	public List<JoinZy> findAll() {
		try{
			return qr.query("select * from zyw_join_zy order by joinid desc", new BeanListHandler<JoinZy>(JoinZy.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public JoinZy findJoinZy(int joinid) {
		try{
			return qr.query("select * from zyw_join_zy where joinid = ?", new BeanHandler<JoinZy>(JoinZy.class),joinid);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public JoinZy findMaxJoinZy() {
		try{
			return qr.query("select * from zyw_join_zy where joinid = (select max(joinid) from zyw_join_zy)", new BeanHandler<JoinZy>(JoinZy.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<JoinZy> query(String title) {
		try{
			return qr.query("select * from zyw_join_zy where title like ?", new BeanListHandler<JoinZy>(JoinZy.class),title);
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
			stmt = conn.prepareStatement("select count(*) from zyw_join_zy");
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
	public List<JoinZy> findPageRecords(int startIndex, int pagesize) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<JoinZy> lc = new ArrayList<JoinZy>();
		
		try{
			conn = JdbcUtil.getConnection();
			stmt = conn.prepareStatement("select * from zyw_join_zy order by joinid desc limit ?,?");
			stmt.setInt(1, startIndex);
			stmt.setInt(2, pagesize);
			rs = stmt.executeQuery();
			while(rs.next()){
				JoinZy jz = new JoinZy();
				jz.setJoinid(rs.getInt("joinid"));
				jz.setTime(rs.getString("time"));
				jz.setTitle(rs.getString("title"));
				jz.setControl(rs.getString("control"));
				lc.add(jz);
			}
			return lc;
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.liu(rs, stmt, conn);
		}
	}

}
