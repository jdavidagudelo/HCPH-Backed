package com.artica.telesalud.patientmasterindex.dao;

import com.artica.telesalud.common.data.DAOFactory;

public interface PatientMasterIndexDAOFactory extends DAOFactory{



		public EhrDAO getEhrDAO();
}
