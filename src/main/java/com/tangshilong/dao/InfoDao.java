package com.tangshilong.dao;

import com.mtzn.code.log.HibernateUtil;
import com.tangshilong.po.Info;
import com.tangshilong.spider.PdfDownSprider;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class InfoDao {
	@SuppressWarnings({"unchecked"})
	public static List<Info> getListByI(int i) throws Exception {
		try {
			Session session = HibernateUtil.currentSession();
			SQLQuery query = session
					.createSQLQuery("select * from info where down = ? and id < 115000 and MOD(id,?) = ? limit 10");
			return (List<Info>) query.addEntity(Info.class).setInteger(0, 0).setInteger(1, PdfDownSprider.MAX_THREAD)
					.setInteger(2, i).list();
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public static void changeStatus(int id) throws Exception {
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.currentSession();
			transaction = session.beginTransaction();
			session.createSQLQuery("update info set down=? where id =?").setInteger(0, 1).setInteger(1, id)
					.executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public static void saveInfo(Info info) throws Exception {
		try {
			Session session = HibernateUtil.currentSession();
			Transaction transaction = session.beginTransaction();
			SQLQuery sqlQuery = session
					.createSQLQuery("insert into info (name,url,source,writer,level,date) values (?,?,?,?,?,?)");
			sqlQuery.setString(0, info.getName());
			sqlQuery.setString(1, info.getUrl());
			sqlQuery.setString(2, info.getSource());
			sqlQuery.setString(3, info.getWriter());
			sqlQuery.setString(4, info.getLevel());
			sqlQuery.setString(5, info.getDate());
			sqlQuery.executeUpdate();
			transaction.commit();
		} finally {
			HibernateUtil.closeSession();
		}
	}
}
