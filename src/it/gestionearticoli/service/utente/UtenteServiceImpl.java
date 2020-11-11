package it.gestionearticoli.service.utente;

import java.sql.Connection;

import it.gestionearticoli.connection.MyConnection;
import it.gestionearticoli.dao.Constants;
import it.gestionearticoli.model.Utente;

public class UtenteServiceImpl extends UtenteService {

	@Override
	public Utente auth(String username, String password) throws Exception {
		Utente result = new Utente();
		result.setUsername(username);
		result.setPassword(password);
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			getDao().setConnection(connection);

			// eseguo quello che realmente devo fare
			result = getDao().findByExample(result).get(0);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	
//	private UtenteDAO dao;
//	
//	@Override
//	public UtenteDAO getDao() { return this.dao; }
//	
//	@Override
//	public void setDao(UtenteDAO tDao) { this.dao = tDao; }
//
//	@Override
//	public List<Utente> elenca() throws Exception {
//		List<Utente> result = new ArrayList<>();
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
//	public Utente trova(Long id) throws Exception {
//		Utente result = null;
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
//	@Override
//	public List<Utente> trovaDa(Utente input) throws Exception {
//		List<Utente> result = null;
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
//	public int aggiorna(Utente input) throws Exception {
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
//	public int inserisci(Utente input) throws Exception {
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
//	public int rimuovi(Utente input) throws Exception {
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
//	public List<Utente> cerca(Utente input) throws Exception {
//		List<Utente> result = null;
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
//
//	@Override
//	public Utente auth(String username, String password) throws Exception {
//		Utente result = new Utente();
//		result.setUsername(username);
//		result.setPassword(password);
//		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {
//
//			// inietto la connection nel dao
//			dao.setConnection(connection);
//
//			// eseguo quello che realmente devo fare
//			
//			result = dao.findByExample(result).get(0);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw e;
//		}
//		return result;
//	}
}
