package com.acat.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.acat.dao.OrgStuDao;
import com.acat.pojo.ComIntro;
import com.acat.pojo.OrgStu;
import com.acat.util.DbcpUtil;
import com.acat.util.JdbcUtil;

public class OrgStuDaoImpl implements OrgStuDao{
	private QueryRunner qr = new QueryRunner(DbcpUtil.getDataSource());

	@Override
	public void addOrgStu(OrgStu os) {
		try{
			qr.update("insert into zyw_org_stu(time,title,control) values(?,?,?)",os.getTime(),os.getTitle(),os.getControl());
		}catch(Exception e){
			throw new RuntimeException(e);
		}		
	}

	@Override
	public void updateOrgStu(OrgStu os) {
		try{
			qr.update("update zyw_org_stu set time=?,title=?,control=? where orgid=?",os.getTime(),os.getTitle(),os.getControl(),os.getOrgid());
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deleteOrgStu(int orgid) {
		try{
			qr.update("delete from zyw_org_stu where orgid=?",orgid);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<OrgStu> findAll() {
		try{
			return qr.query("select * from zyw_org_stu order by orgid desc", new BeanListHandler<OrgStu>(OrgStu.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public OrgStu findOrgStu(int orgid) {
		try{
			return qr.query("select * from zyw_org_stu where orgid = ?", new BeanHandler<OrgStu>(OrgStu.class),orgid);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public OrgStu findMaxOrgStu() {
		try{
			return qr.query("select * from zyw_org_stu where orgid = (select max(orgid) from zyw_org_stu)", new BeanHandler<OrgStu>(OrgStu.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<OrgStu> query(String title) {
		try{
			return qr.query("select * from zyw_org_stu where title like ?", new BeanListHandler<OrgStu>(OrgStu.class),title);
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
			stmt = conn.prepareStatement("select count(*) from zyw_org_stu");
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
	public List<OrgStu> findPageRecords(int startIndex, int pagesize) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<OrgStu> lc = new ArrayList<OrgStu>();
		
		try{
			conn = JdbcUtil.getConnection();
			stmt = conn.prepareStatement("select * from zyw_org_stu order by orgid desc limit ?,?");
			stmt.setInt(1, startIndex);
			stmt.setInt(2, pagesize);
			rs = stmt.executeQuery();
			while(rs.next()){
				OrgStu os = new OrgStu();
				os.setOrgid(rs.getInt("orgid"));
				os.setTime(rs.getString("time"));
				os.setTitle(rs.getString("title"));
				os.setControl(rs.getString("control"));
				lc.add(os);
			}
			return lc;
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.liu(rs, stmt, conn);
		}
	}
	
}
