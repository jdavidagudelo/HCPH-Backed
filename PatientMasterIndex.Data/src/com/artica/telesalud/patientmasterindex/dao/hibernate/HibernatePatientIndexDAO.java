package com.artica.telesalud.patientmasterindex.dao.hibernate;


public class HibernatePatientIndexDAO{

/*	public HibernatePatientIndexDAO(String configFile) {
		super(configFile);
	}
	@Override
	public List<PatientIndex> findClinicHistoriesByPatient(
			String patientIdentifier, String domainUuid) {
		Transaction tx = null;
		Session session = getNewSession();
		
		try {
		    tx = session.beginTransaction();
		    String hql = "FROM PatientIndex p WHERE "+
				    "p.patient.patientId=:patientIdentifier AND p.domains.uuid=:domainUuid";
		    
		    
		    Query q = session.createQuery(hql);
		    q.setParameter("patientIdentifier", patientIdentifier);
		    q.setParameter("domainUuid", domainUuid);
		    @SuppressWarnings("unchecked")
			List<PatientIndex> result = q.list();
		     
		    tx.commit();
		    return result;
		} catch(Exception e) {
			if(tx!=null)
				tx.rollback();
		    e.printStackTrace();
		    throw new TelesaludException("Exception!",e,HibernatePatientIndexDAO.class);
		}
	}
	@Override
	public List<PatientIndex> findExternalClinicHistoriesByPatient(
			String patientIdentifier, String ehrUuid) {
		Transaction tx = null;
		Session session = getNewSession();
		
		try {
		    tx = session.beginTransaction();
		    String hql = "FROM PatientIndex p WHERE "+
				    "p.patient.patientId=:patientIdentifier AND p.ehr.uuid <> :ehrUuid";
		    
		    Query q = session.createQuery(hql); 
		    q.setParameter("patientIdentifier", patientIdentifier);
		    q.setParameter("ehrUuid", ehrUuid);
		    @SuppressWarnings("unchecked")
			List<PatientIndex> result = q.list();
		     
		    tx.commit();
		    return result;
		} catch(Exception e) {
			if(tx!=null)
				tx.rollback();
		    e.printStackTrace();
		    throw new TelesaludException("Exception!",e,HibernatePatientIndexDAO.class);
		}
	}*/

}
