package com.artica.telesalud.tph.dao;

import java.util.List;

import com.artica.telesalud.tph.model.evento.Lesionado;
import com.artica.telesalud.tph.model.evento.ResponsableAtencion;


public interface ResponsableAtencionDAO {
	
	public List<ResponsableAtencion> getAll();
	public ResponsableAtencion insert(ResponsableAtencion responsableAtencion);
	public ResponsableAtencion update(ResponsableAtencion responsableAtencion);
	public ResponsableAtencion newResponsableAtencion();
	public ResponsableAtencion delete(ResponsableAtencion responsableAtencion);
	public List<ResponsableAtencion> getAll(Lesionado lesionado);
	public ResponsableAtencion get(Integer responsableAtencionId);
}
