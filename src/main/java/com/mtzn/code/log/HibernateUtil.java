package com.mtzn.code.log;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.tangshilong.po.Info;

public class HibernateUtil {
	public static final SessionFactory sessionFactory;
	static {
		try {
			Configuration cfg = new Configuration().configure();
			cfg.addClass(Info.class);
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties())
					.build();
			sessionFactory = cfg.buildSessionFactory(serviceRegistry);
		} catch (Throwable ex) {
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static final ThreadLocal<Session> session = new ThreadLocal<Session>();

	public static Session currentSession() throws HibernateException {
		Session s = session.get();
		if (s == null) {
			s = sessionFactory.openSession();
			session.set(s);
		}
		return s;
	}

	public static void closeSession() throws HibernateException {
		Session s = session.get();
		if (s != null)
			s.close();
		session.set(null);
	}

}
