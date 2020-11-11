package it.gestionearticoli.service.articolo;

public class ArticoloServiceImpl extends ArticoloService {
	
//	private ArticoloDAO dao;
//	
//	@Override
//	public ArticoloDAO getDao() { return this.dao; }
//	
//	@Override
//	public void setDao(ArticoloDAO tDao) { this.dao = tDao; }
//
//	@Override
//	public List<Articolo> elenca() throws Exception {
//		List<Articolo> result = new ArrayList<>();
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
//	public Articolo trova(Long id) throws Exception {
//		Articolo result = null;
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
//	public List<Articolo> trovaDa(Articolo input) throws Exception {
//		List<Articolo> result = null;
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
//	public int aggiorna(Articolo input) throws Exception {
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
//	public int inserisci(Articolo input) throws Exception {
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
//	public int rimuovi(Articolo input) throws Exception {
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
//	public List<Articolo> cerca(Articolo input) throws Exception {
//		List<Articolo> result = null;
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
