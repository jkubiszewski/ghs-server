/**
 * 
 */
package de.champonthis.ghs.server.businesslogic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import de.champonthis.ghs.server.model.Game;

/**
 * The Class GameManager.
 */
@Component
public class GameManager implements SmartInitializingSingleton {

	@Autowired
	private Gson gson;

	private Connection connection = null;

	/*
	 * @see org.springframework.beans.factory.SmartInitializingSingleton#
	 * afterSingletonsInstantiated()
	 */
	@Override
	public void afterSingletonsInstantiated() {
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:ghs.sqlite");
			Statement statement = connection.createStatement();
			statement.executeUpdate(
					"CREATE TABLE IF NOT EXISTS games (id INTEGER PRIMARY KEY AUTOINCREMENT, game STRING)");
			statement.executeUpdate(
					"CREATE TABLE IF NOT EXISTS passwords (game_id INTEGER, password STRING, json_path STRING)");

			ResultSet passwordResultSet = passwords();

			if (passwordResultSet != null) {
				while (passwordResultSet.next()) {
					String password = passwordResultSet.getString("password");
					String jsonPath = passwordResultSet.getString("json_path");
					if (jsonPath == null) {
						jsonPath = "[ALL]";
					}
					int gameId = passwordResultSet.getInt("game_id");
					System.out.println("\nPASSWORD: " + password + " GRANTING " + jsonPath + " ON GAME: " + gameId + "\n");
				}
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Passwords.
	 *
	 * @return the result set
	 */
	public ResultSet passwords() {
		try {
			return connection.createStatement().executeQuery("SELECT game_id,password,json_path FROM passwords");
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	/**
	 * Count passwords.
	 *
	 * @return the int
	 */
	public int countPasswords() {
		try {
			Statement statement = connection.createStatement();
			ResultSet passwordCountResultSet = statement.executeQuery("SELECT count(*) FROM passwords;");

			if (passwordCountResultSet.next()) {
				return passwordCountResultSet.getInt("count(*)");
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

		return 0;
	}

	/**
	 * Creates the password.
	 *
	 * @param password the password
	 * @param gameId   the game id
	 */
	public void createPassword(String password, int gameId) {
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(
					"INSERT INTO passwords (game_id, password) VALUES(" + gameId + ",'" + password + "')");
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Gets the game id by password.
	 *
	 * @param password the password
	 * @return the game id by password
	 */
	public Integer getGameIdByPassword(String password) {
		try {
			Statement statement = connection.createStatement();
			ResultSet gameIdResultSet = statement
					.executeQuery("SELECT game_id FROM passwords WHERE password = '" + password + "';");
			if (gameIdResultSet.next()) {
				return gameIdResultSet.getInt("game_id");
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

		return null;
	}

	/**
	 * Gets the game.
	 *
	 * @param id the id
	 * @return the game
	 */
	public Game getGame(int id) {
		try {
			Statement statement = connection.createStatement();
			ResultSet gameResultSet = statement.executeQuery("SELECT game FROM games WHERE id = " + id + ";");

			if (gameResultSet.next()) {
				return gson.fromJson(gameResultSet.getString("game"), Game.class);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

		return null;
	}

	/**
	 * Creates the game.
	 *
	 * @param game the game
	 * @return the integer
	 */
	public Integer createGame(Game game) {
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO games (game) VALUES('" + gson.toJson(game) + "')");
			ResultSet gameResultSet = statement.executeQuery("SELECT id FROM games;");
			return gameResultSet.getInt("id");
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

		return null;
	}

	/**
	 * Sets the game.
	 *
	 * @param id   the id
	 * @param game the game
	 */
	public void setGame(int id, Game game) {
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("UPDATE games SET game= '" + gson.toJson(game) + "' WHERE id=" + id);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

}
