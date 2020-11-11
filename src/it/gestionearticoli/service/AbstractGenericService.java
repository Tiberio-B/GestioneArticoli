package it.gestionearticoli.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import it.gestionearticoli.connection.MyConnection;
import it.gestionearticoli.dao.Constants;
import it.gestionearticoli.dao.IBaseDAO;

public abstract class AbstractGenericService<T> implements GenericService<T> {
	
	private IBaseDAO<T> dao;
	
	@Override
	public IBaseDAO<T> getDao() { return this.dao; }
	
	@Override
	public void setDao(IBaseDAO<T> tDao) { this.dao = tDao; }

	@Override
	public List<T> elenca() throws Exception {
		List<T> result = new ArrayList<>();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			getDao().setConnection(connection);

			// eseguo quello che realmente devo fare
			result = getDao().list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public T trova(Long id) throws Exception {
		T result = null;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			getDao().setConnection(connection);

			// eseguo quello che realmente devo fare
			result = getDao().get(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public List<T> trovaDa(T input) throws Exception {
		List<T> result = null;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			getDao().setConnection(connection);

			// eseguo quello che realmente devo fare
			result = getDao().findByExample(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int aggiorna(T input) throws Exception {
		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			getDao().setConnection(connection);

			// eseguo quello che realmente devo fare
			result = getDao().update(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int inserisci(T input) throws Exception {
		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			getDao().setConnection(connection);

			// eseguo quello che realmente devo fare
			result = getDao().insert(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int rimuovi(T input) throws Exception {
		int result = -1;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			getDao().setConnection(connection);

			// eseguo quello che realmente devo fare
			result = getDao().delete(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public List<T> cerca(T input) throws Exception {
		List<T> result = null;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			getDao().setConnection(connection);

			// eseguo quello che realmente devo fare
			result = getDao().search(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}
}
