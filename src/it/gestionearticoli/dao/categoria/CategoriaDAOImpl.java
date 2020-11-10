package it.gestionearticoli.dao.categoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.gestionearticoli.dao.AbstractMySQLDAO;
import it.gestionearticoli.model.Articolo;
import it.gestionearticoli.model.Categoria;

public class CategoriaDAOImpl extends AbstractMySQLDAO implements CategoriaDAO {

	@Override
	public List<Categoria> list() throws Exception {
		if (isNotActive()) {
			return null;
		}

		ArrayList<Categoria> result = new ArrayList<Categoria>();
		Categoria cat = null;

		try (Statement ps = connection.createStatement()) {
			ResultSet rs = ps.executeQuery("select * from categoria");

			while (rs.next()) {
				cat = new Categoria();
				cat.setId(rs.getLong("id_cat"));
				cat.setNome(rs.getString("nome"));
				result.add(cat);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public Categoria get(Long id) throws Exception {
		if (isNotActive()) {
			return null;
		}
		
		String query = "SELECT * FROM categoria WHERE id_cat = ?";

		Categoria categoria = new Categoria();

		try (PreparedStatement ps = connection.prepareStatement(query)) {
			
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				categoria.setNome(rs.getString("nome"));
				categoria.setId(rs.getLong("id_cat"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return categoria;
	}

	@Override
	public int update(Categoria input) throws Exception {
		if (isNotActive() || input == null) {
			return -1;
		}
		
		String query = "UPDATE categoria SET nome = ? WHERE id_cat = ?";
		int result = 0;

		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setString(1, input.getNome());
			ps.setLong(2, input.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int insert(Categoria input) throws Exception {
		if (isNotActive() || input == null) {
			return -1;
		}
		
		String query = "INSERT INTO categoria (nome) VALUES (?);";
		int result = 0;

		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setString(1, input.getNome());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int delete(Categoria input) throws Exception {
		if (isNotActive() || input == null) {
			return -1;
		}
		
		String query = "DELETE FROM categoria WHERE id_cat = ?";
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
	public List<Categoria> findByExample(Categoria input) throws Exception {
		if (isNotActive()) {
			return null;
		}
		List<Categoria> lista = new ArrayList<>();
	
		String query = "SELECT * FROM categoria"+
						" WHERE " +
						"(id_cat = ? OR ? is null) AND "+
						"(nome = ? OR ? is null)";

		try (PreparedStatement ps = connection.prepareStatement(query)) {
		
			ps.setLong(1, input.getId());
			ps.setLong(2, input.getId());
			ps.setString(3, input.getNome());
			ps.setString(4, input.getNome());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Categoria categoria = new Categoria();
				categoria.setId(rs.getLong("id_cat"));
				categoria.setNome(rs.getString("nome"));
				
				lista.add(categoria);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return lista;
	}

	@Override
	public List<Categoria> search(Categoria input) throws Exception {
		if (isNotActive()) {
			return null;
		}
		List<Categoria> lista = new ArrayList<>();
	
		String query = "SELECT * FROM categoria"+
						" WHERE " +
						"(nome like ? OR ? is null)";

		try (PreparedStatement ps = connection.prepareStatement(query)) {
		
			ps.setString(1, "%"+input.getNome()+"%");
			ps.setString(2, input.getNome());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Categoria categoria = new Categoria();
				categoria.setId(rs.getLong("id_cat"));
				categoria.setNome(rs.getString("nome"));
				
				lista.add(categoria);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return lista;
	}

}
