package it.gestionearticoli.dao.articolo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.gestionearticoli.dao.AbstractMySQLDAO;
import it.gestionearticoli.model.Articolo;
import it.gestionearticoli.model.Categoria;
import it.gestionearticoli.model.Utente;
import it.gestionearticoli.service.ServiceFactory;

public class ArticoloDAOImpl extends AbstractMySQLDAO implements ArticoloDAO {

	@Override
	public List<Articolo> list() throws Exception {
		if (isNotActive()) {
			return null;
		}
		
		String query = "select * from (articolo a join categoria c on a.categoria_fk = c.id_cat)";

		ArrayList<Articolo> result = new ArrayList<Articolo>();
		Articolo articoloTemp = null;

		try (Statement ps = connection.createStatement()) {
			ResultSet rs = ps.executeQuery(query);

			while (rs.next()) {
				articoloTemp = new Articolo();
				articoloTemp.setCodice(rs.getString("CODICE"));
				articoloTemp.setDescrizione(rs.getString("DESCRIZIONE"));
				articoloTemp.setPrezzo(rs.getInt("PREZZO"));
				articoloTemp.setId(rs.getLong("ID"));
				Categoria categoria = new Categoria();
				categoria.setId(rs.getLong("id_cat"));
				categoria.setNome(rs.getString("nome"));
				articoloTemp.setCategoria(categoria);
				result.add(articoloTemp);		
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public Articolo get(Long id) throws Exception {
		if (isNotActive()) {
			return null;
		}
		
		String query = "SELECT * FROM (articolo a join categoria c on a.categoria_fk = c.id_cat) WHERE id = ?";

		Articolo articolo = new Articolo();

		try (PreparedStatement ps = connection.prepareStatement(query)) {
			
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				articolo.setCodice(rs.getString("codice"));
				articolo.setDescrizione(rs.getString("descrizione"));
				articolo.setPrezzo(rs.getInt("prezzo"));
				articolo.setId(rs.getLong("id"));
				Categoria categoria = new Categoria();
				categoria.setId(rs.getLong("id_cat"));
				categoria.setNome(rs.getString("nome"));
				articolo.setCategoria(categoria);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return articolo;
	}

	@Override
	public int update(Articolo input) throws Exception {
		if (isNotActive() || input == null) {
			return -1;
		}
		
		String query = "UPDATE articolo SET codice = ?, descrizione = ?, prezzo = ?, categoria_fk = ? WHERE id = ?";
		int result = 0;

		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setString(1, input.getCodice());
			ps.setString(2, input.getDescrizione());
			ps.setInt(3, input.getPrezzo());
			ps.setLong(4, input.getCategoria().getId());
			ps.setLong(5, input.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int insert(Articolo input) throws Exception {
		if (isNotActive() || input == null) {
			return -1;
		}
		
		String query = "INSERT INTO articolo (codice, descrizione, prezzo, categoria_fk) VALUES (?, ?, ?, ?);";
		int result = 0;

		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setString(1, input.getCodice());
			ps.setString(2, input.getDescrizione());
			ps.setInt(3, input.getPrezzo());
			ps.setLong(4, input.getCategoria().getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int delete(Articolo input) throws Exception {
		if (isNotActive() || input == null) {
			return -1;
		}
		
		String query = "DELETE FROM articolo WHERE id = ?";
		int result = 0;

		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setLong(1, input.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;

	}

	@Override
	public List<Articolo> findByExample(Articolo input) throws Exception {
		if (isNotActive()) {
			return null;
		}
		List<Articolo> lista = new ArrayList<>();
	
		String query = "SELECT * FROM (articolo a JOIN categoria c on a.categoria_fk = c.id_cat) "+
						"WHERE" +
						" (id = ? OR ? = -1) AND"+
						" (codice = ? OR ? is null) AND"+
						" (descrizione = ? OR ? is null) AND"+
						" (prezzo = ? OR ? = -1) AND"+
						" (categoria_fk = ? OR ? = -1)";

		try (PreparedStatement ps = connection.prepareStatement(query)) {
		
			Long id = (input.getId()==null) ? -1 : input.getId();
			ps.setLong(1, id);
			ps.setLong(2, id);
			
			ps.setString(3, input.getCodice());
			ps.setString(4, input.getCodice());
			
			ps.setString(5, input.getDescrizione());
			ps.setString(6, input.getDescrizione());
			
			Integer prezzo = (input.getPrezzo()==null) ? -1 : input.getPrezzo();
			ps.setInt(7, prezzo);
			ps.setInt(8, prezzo);
			
			Long cat = (input.getCategoria()==null) ? -1 : input.getCategoria().getId();
			ps.setLong(9, input.getCategoria().getId());
			ps.setLong(10, input.getCategoria().getId());
			
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Articolo articolo = new Articolo();
				articolo.setId(rs.getLong("id"));
				articolo.setCodice(rs.getString("codice"));
				articolo.setDescrizione(rs.getString("descrizione"));
				articolo.setPrezzo(rs.getInt("prezzo"));
				Categoria categoria = new Categoria();
				categoria.setId(rs.getLong("id_cat"));
				categoria.setNome(rs.getString("nome"));
				articolo.setCategoria(categoria);
				
				lista.add(articolo);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return lista;
	}
	
	public List<Articolo> search(Articolo input) throws Exception {
		if (isNotActive()) {
			return null;
		}
		List<Articolo> lista = new ArrayList<>();
	
		String query = "SELECT * FROM (articolo a JOIN categoria c on a.categoria_fk = c.id_cat) "+
						"WHERE" +
						" (codice like ? OR ? is null) AND"+
						" (descrizione like ? OR ? is null) AND"+
						" (prezzo = ? OR ? = -1) AND"+
						" (categoria_fk = ? OR ? = -1)";

		try (PreparedStatement ps = connection.prepareStatement(query)) {
		
			ps.setString(1, "%"+input.getCodice()+"%");
			ps.setString(2, input.getCodice());
			
			ps.setString(3, "%"+input.getDescrizione()+"%");
			ps.setString(4, input.getDescrizione());
			
			Integer prezzo = (input.getPrezzo()==null) ? -1 : input.getPrezzo();
			ps.setInt(5, prezzo);
			ps.setInt(6, prezzo);
			
			Long cat = (input.getCategoria()==null) ? -1 : input.getCategoria().getId();
			ps.setLong(7, cat);
			ps.setLong(8, cat);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Articolo articolo = new Articolo();
				articolo.setId(rs.getLong("id"));
				articolo.setCodice(rs.getString("codice"));
				articolo.setDescrizione(rs.getString("descrizione"));
				articolo.setPrezzo(rs.getInt("prezzo"));
				Categoria categoria = new Categoria();
				categoria.setId(rs.getLong("id_cat"));
				categoria.setNome(rs.getString("nome"));
				articolo.setCategoria(categoria);
				
				lista.add(articolo);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return lista;
	}

}
