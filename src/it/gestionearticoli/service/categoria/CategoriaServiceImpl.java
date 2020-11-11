package it.gestionearticoli.service.categoria;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import it.gestionearticoli.connection.MyConnection;
import it.gestionearticoli.dao.Constants;
import it.gestionearticoli.dao.categoria.CategoriaDAO;
import it.gestionearticoli.model.Categoria;

public class CategoriaServiceImpl extends CategoriaService {
	
//	private CategoriaDAO dao;
//	
//	@Override
//	public CategoriaDAO getDao() { return this.dao; }
//	
//	@Override
//	public void setDao(CategoriaDAO tDao) { this.dao = tDao; }
//
//	@Override
//	public List<Categoria> elenca() throws Exception {
//		List<Categoria> result = new ArrayList<>();
//		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {
//
//			// inietto la connection nel dao
//			dao.setConnection(connection);
//
//			// eseguo quello che realmente devo fare
//			result = dao.list();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw e;
//		}
//		return result;
//	}
//
//	@Override
//	public Categoria trova(Long id) throws Exception {
//		Categoria result = null;
//		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {
//
//			// inietto la connection nel dao
//			dao.setConnection(connection);
//
//			// eseguo quello che realmente devo fare
//			result = dao.get(id);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw e;
//		}
//		return result;
//	}
//	
//
//
//	@Override
//	public List<Categoria> trovaDa(Categoria input) throws Exception {
//		List<Categoria> result = null;
//		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {
//
//			// inietto la connection nel dao
//			dao.setConnection(connection);
//
//			// eseguo quello che realmente devo fare
//			result = dao.findByExample(input);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw e;
//		}
//		return result;
//	}
//
//	@Override
//	public int aggiorna(Categoria input) throws Exception {
//		int result = 0;
//		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {
//
//			// inietto la connection nel dao
//			dao.setConnection(connection);
//
//			// eseguo quello che realmente devo fare
//			result = dao.update(input);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw e;
//		}
//		return result;
//	}
//
//	@Override
//	public int inserisci(Categoria input) throws Exception {
//		int result = 0;
//		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {
//
//			// inietto la connection nel dao
//			dao.setConnection(connection);
//
//			// eseguo quello che realmente devo fare
//			result = dao.insert(input);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw e;
//		}
//		return result;
//	}
//
//	@Override
//	public int rimuovi(Categoria input) throws Exception {
//		int result = -1;
//		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {
//
//			// inietto la connection nel dao
//			dao.setConnection(connection);
//
//			// eseguo quello che realmente devo fare
//			result = dao.delete(input);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw e;
//		}
//		return result;
//	}
//
//	@Override
//	public List<Categoria> cerca(Categoria input) throws Exception {
//		List<Categoria> result = null;
//		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {
//
//			// inietto la connection nel dao
//			dao.setConnection(connection);
//
//			// eseguo quello che realmente devo fare
//			result = dao.search(input);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw e;
//		}
//		return result;
//	}
}
