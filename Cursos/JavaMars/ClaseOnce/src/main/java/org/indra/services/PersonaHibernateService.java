package org.indra.services;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.*;
import org.hibernate.boot.*;
import org.hibernate.boot.registry.*;
import org.indra.models.Persona;

public class PersonaHibernateService implements IPersonaService {
	
	private SessionFactory factory;
	public PersonaHibernateService(SessionFactory factory) {
		this.factory = factory;
	
	}
	
	@Override
	public void add(Persona persona) {
		try {
			Session session = this.factory.openSession();
			Transaction transaction = session.beginTransaction();

			session.save(persona);
			
			transaction.commit();
	
			session.close();
		}catch(Exception exception) {
			exception.printStackTrace();
		}		
	}

	@Override
	public List<Persona> getAll() {
		// TODO Auto-generated method stub
		//return factory.openSession().createCriteria(Persona.class).list();
		//return factory.openSession().createQuery("FROM Persona").getResultList();
		Session session = factory.openSession();
		CriteriaBuilder builder = factory.openSession().getCriteriaBuilder();
		CriteriaQuery<Persona> qry = builder.createQuery(Persona.class);
		qry.from(Persona.class);
		return session.createQuery(qry).getResultList();
	}

	@Override
	public void delete(int id) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		Persona p = session.get(Persona.class, id);
		session.delete(p);
		t.commit();
		session.close();
	}

}
