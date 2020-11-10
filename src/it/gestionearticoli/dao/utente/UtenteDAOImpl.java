package it.gestionearticoli.dao.utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import it.gestionearticoli.dao.AbstractMySQLDAO;
import it.gestionearticoli.model.Utente;

public class UtenteDAOImpl extends AbstractMySQLDAO
		implements UtenteDAO {

	@Override
	public List<Utente> list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utente get(Long id) throws Exception {
		if (isNotActive()) {
			return null;
		}
		
		String query = "SELECT * FROM utente WHERE id_utente = ?";

		Utente utente = new Utente();

		try (PreparedStatement ps = connection.prepareStatement(query)) {
			
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				utente.setId(rs.getLong("id_utente"));
				utente.setNome(rs.getString("nome"));
				utente.setCognome(rs.getString("cognome"));
				utente.setCf(rs.getString("cf"));
				utente.setUsername(rs.getString("username"));
				utente.setPassword(rs.getString("password"));
				utente.setRuolo(Utente.Ruolo.valueOf(rs.getString("ruolo")));
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return utente;
	}

	@Override
	public int update(Utente input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Utente input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Utente input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public List<Utente> findByExample(Utente input) throws Exception {
		if (isNotActive()) {
			return null;
		}
		List<Utente> lista = new ArrayList<>();
	
		String query = "SELECT * FROM utente WHERE" +
						" (id_utente = ? OR ? = 0) AND"+
						" (nome = ? OR ? = \'\') AND"+
						" (cognome = ? OR ? = \'\') AND"+
						" (cf = ? OR ? = \'\') AND"+
						" (username = ? OR ? = \'\') AND"+
						" (password = ? OR ? = \'\')";

		try (PreparedStatement ps = connection.prepareStatement(query)) {
		
			ps.setLong(1, input.getId());
			ps.setLong(2, input.getId());
			
			ps.setString(3, input.getNome());
			ps.setString(4, input.getNome());
			
			ps.setString(5, input.getCognome());
			ps.setString(6, input.getCognome());
			
			ps.setString(7, input.getCf());
			ps.setString(8, input.getCf());
			
			ps.setString(9, input.getUsername());
			ps.setString(10, input.getUsername());
			
			ps.setString(11, input.getPassword());
			ps.setString(12, input.getPassword());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Utente utente = new Utente();
				utente.setId(rs.getLong("id_utente"));
				utente.setNome(rs.getString("nome"));
				utente.setCognome(rs.getString("cognome"));
				utente.setCf(rs.getString("cf"));
				utente.setUsername(rs.getString("username"));
				utente.setPassword(rs.getString("password"));
				utente.setRuolo(Utente.Ruolo.valueOf(rs.getString("ruolo")));
				lista.add(utente);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return lista;
	}

	@Override
	public List<Utente> search(Utente input)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
