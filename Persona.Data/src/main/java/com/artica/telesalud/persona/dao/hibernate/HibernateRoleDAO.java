package com.artica.telesalud.persona.dao.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.artica.telesalud.common.data.DataConstraintViolation;
import com.artica.telesalud.common.data.HibernateDAO;
import com.artica.telesalud.common.exception.TelesaludException;
import com.artica.telesalud.persona.dao.RoleDAO;
import com.artica.telesalud.persona.model.impl.PermissionDTO;
import com.artica.telesalud.persona.model.impl.PrivilegeDTO;
import com.artica.telesalud.persona.model.impl.ResourceDTO;
import com.artica.telesalud.persona.model.impl.RoleDTO;
import com.artica.telesalud.persona.model.impl.UserDTO;

public class HibernateRoleDAO extends HibernateDAO implements RoleDAO,
Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public List<RoleDTO> getAll() {
		Session session = null;
		Transaction tx = null;
		try {
			session = getNewSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(RoleDTO.class);

			@SuppressWarnings("unchecked")
			List<RoleDTO> roles = criteria.list();
			tx.commit();
			return roles;
		} catch (Exception e) {
			if(tx != null && session.isOpen())
			{
				tx.rollback();
			}
			e.printStackTrace();
			throw new TelesaludException("Exception!", e,
					HibernateUserDAO.class);
		} finally {
			if (session != null)
				session.close();
		}
	}

	
	public RoleDTO insert(RoleDTO role) {
		Transaction tx = null;
		Session session = null;
		
		try {
			session = getNewSession();
			tx = session.beginTransaction();
			session.save(role);
			tx.commit();

		} catch (Exception e) {
			if (tx != null && session.isOpen()) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new TelesaludException("Exception!", e,
					HibernateUserDAO.class);
		} finally {
			if (session != null)
				session.close();
		}
		return role;
	}

	
	public RoleDTO update(RoleDTO role) {
		Transaction tx = null;
		Session session = null;
		try {
			session = getNewSession();
			tx = session.beginTransaction();
			session.update(role);
			tx.commit();

		} catch (Exception e) {
			if (tx != null && session.isOpen()) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new TelesaludException("Exception!", e,
					HibernateUserDAO.class);
		} finally {
			if (session != null)
				session.close();
		}
		return role;
	}

	
	public RoleDTO newBlankRole() {
		return new RoleDTO();
	}

	
	public RoleDTO delete(RoleDTO role) {
		Transaction tx = null;
		Session session = null;
		try {
			session = getNewSession();
			tx = session.beginTransaction();
			session.delete(role);
			tx.commit();

		} catch (Exception e) {
			if (tx != null && session.isOpen()) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new TelesaludException("Exception!", e,
					HibernateUserDAO.class);
		} finally {
			if (session != null)
				session.close();
		}
		return role;
	}

	
	public RoleDTO findByName(String name) {
		Session session = null;
		Transaction tx = null;
		RoleDTO role = null;
		try {
			session = getNewSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(UserDTO.class).add(
					Restrictions.eq("name", name));
			role = (RoleDTO) criteria.uniqueResult();
			tx.commit();
		} catch (Exception e) {
			if(tx != null && session.isOpen())
			{
				tx.rollback();
			}
			e.printStackTrace();
			throw new TelesaludException("Exception!", e,
					HibernateUserDAO.class);
		} finally {
			if (session != null)
				session.close();
		}
		return role;
	}

	
	public List<RoleDTO> getChildren(RoleDTO role) {
		Session session = null;
		Transaction tx = null;
		try {
			session = getNewSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(UserDTO.class).add(
					Restrictions.eq("parentId", role.getRoleId()));
			@SuppressWarnings("unchecked")
			List<RoleDTO> list = criteria.list();
			tx.commit();
			return list;
		} catch (Exception e) {
			if(tx != null && session.isOpen())
			{
				tx.rollback();
			}
			e.printStackTrace();
			throw new TelesaludException("Exception!", e,
					HibernateUserDAO.class);
		} finally {
			if (session != null)
				session.close();
		}

	}

	
	public List<DataConstraintViolation> validate(RoleDTO role) {
		return new ArrayList<DataConstraintViolation>(0);
	}



	@SuppressWarnings("unchecked")
	
	public List<PrivilegeDTO> getPrivilegesFor(RoleDTO role) {
		
		Session session = null;
		Transaction tx = null;
		try {
			session = getNewSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(PermissionDTO.class).add(
					Restrictions.eq("role", role.getRoleId()));
			List<PrivilegeDTO> result = new ArrayList<PrivilegeDTO>();
			for(PermissionDTO permission : (List<PermissionDTO>)criteria.list())
			{
				PrivilegeDTO privilege = new PrivilegeDTO();
				privilege.setOperation(permission.getOperation());
				privilege.setResource(permission.getResource()); 
				result.add(privilege);
			}
			tx.commit();
			return result;
		} catch (Exception e) {
			if(tx != null && session.isOpen())
			{
				tx.rollback();
			}
			e.printStackTrace();
			throw new TelesaludException("Exception!", e,
					HibernateUserDAO.class);
		} finally {
			if (session != null)
				session.close();
		}

	}

	
	public RoleDTO findById(int id) {
		Session session = null;
		RoleDTO role = null;
		Transaction tx = null;
		try {
			session = getNewSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(RoleDTO.class).add(
					Restrictions.eq("roleId", id));
			role = (RoleDTO) criteria.uniqueResult();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(tx != null && session.isOpen())
			{
				tx.rollback();
			}
			throw new TelesaludException("Exception!", e,
					HibernateUserDAO.class);
		} finally {
			if (session != null)
				session.close();
		}
		return role;
	}

	@SuppressWarnings("unchecked")
	
	public List<ResourceDTO> getPartAccesibleResources(RoleDTO role) {
		
		Session session = null;
		Transaction tx = null; 
		try {
			session = getNewSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(PermissionDTO.class).add(
					Restrictions.eq("role", role.getRoleId()));
			List<ResourceDTO> resources = new ArrayList<ResourceDTO>();
			for(PermissionDTO permission : (List<PermissionDTO>)criteria.list())
			{
				Criteria criteria1 = session.createCriteria(ResourceDTO.class).add(
						Restrictions.eq("resourceId", permission.getResource()));
				resources.addAll(criteria1.list());
			}
			tx.commit();
			return resources;
		} catch (Exception e) {
			if(tx != null && session.isOpen())
			{
				tx.rollback();
			}
			e.printStackTrace();
			throw new TelesaludException("Exception!", e,
					HibernateUserDAO.class);
		} finally {
			if (session != null)
				session.close();
		}
	}

	
	public RoleDTO getParent(RoleDTO role) {
		if( role.getParentId() == null )
			return null;
		
		return findById(role.getParentId());
	}

	
	public void addPrivilegeTo(RoleDTO role, PrivilegeDTO privilege) {
		Session session = null;
		Transaction tx = null;
		try {
			session = getNewSession();
			
			PermissionDTO permission = new PermissionDTO();
			permission.setRole(role.getRoleId());
			permission.setOperation(privilege.getOperation());
			permission.setResource(privilege.getResource());
			tx = session.beginTransaction();
			session.saveOrUpdate(permission);
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			throw new TelesaludException("Exception!",e,HibernateRoleDAO.class);
		}finally {
			if (session != null)
				session.close();
		}
		
	}

	
	public void removePrivilegeFrom(RoleDTO role, PrivilegeDTO privilege) {
		Session session = null;
		Transaction tx = null;
		try {
			session = getNewSession();
			
			PermissionDTO permission = new PermissionDTO();
			permission.setRole(role.getRoleId());
			permission.setOperation(privilege.getOperation());
			permission.setResource(privilege.getResource());
			tx = session.beginTransaction();
			session.delete(permission);
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			throw new TelesaludException("Exception!",e,HibernateRoleDAO.class);
		}finally {
			if (session != null)
				session.close();
		}		
	}

	@SuppressWarnings("unchecked")
	
	public void removeAllPrivilegesFor(RoleDTO role) {
		Session session = null;
		Transaction tx = null;
		try {
			session = getNewSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(PermissionDTO.class).add(
					Restrictions.eq("role", role.getRoleId()));
			List<PermissionDTO> permissions =(List<PermissionDTO>)criteria.list(); 
			for(PermissionDTO permission : permissions)
			{
				session.delete(permission);
			}
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			throw new TelesaludException("Exception!",e,HibernateRoleDAO.class);
		}finally {
			if (session != null)
				session.close();
		}	
	}
}
