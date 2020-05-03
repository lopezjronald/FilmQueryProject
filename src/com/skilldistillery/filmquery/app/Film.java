package com.skilldistillery.filmquery.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Film {

	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	private List<Object> filmIdList = new ArrayList<>();
	private List<Object> filmLanguageList = new ArrayList<>();
	private List<Object> filmActorList = new ArrayList<>();

	public Film() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
	}

	public void showFilmInfoUsingKeyword(String keyword) {
		String user = "student";
		String password = "student";
		String sqlText;

		filmIdList.clear();
		filmLanguageList.clear();
		filmActorList.clear();

		sqlText = "SELECT film.id, film.title, film.release_year, film.rating, film.description, language.name , actor.first_name, actor.last_name "
				+ "FROM film " + "JOIN language ON film.language_id = language_id "
				+ "JOIN film_actor ON film_actor.film_id = film.id " + "JOIN actor ON actor.id = film_actor.actor_id "
				+ "WHERE title LIKE \"%" + keyword + "%\";";

		try (Connection connection = DriverManager.getConnection(URL, user, password);
				PreparedStatement stmt = connection.prepareStatement(sqlText);
				ResultSet rs = stmt.executeQuery();) {

			boolean isNotEmpty = rs.next();

			if (isNotEmpty) {
				System.out.println("----------------------");
				System.out.println("Film Information:");
				rs.previous();
			} else {
				System.out.println("There is no film with that keyword.");
			}

			while (rs.next()) {
				if (filmIdList.contains(rs.getString("film.id"))) {
					continue;
				}
				System.out.println("Film ID: " + rs.getString("film.id") + ", Film Title: " + rs.getString("film.title")
						+ ", Release Year: " + rs.getString("film.release_year") + "\nDescription: "
						+ rs.getString("film.description"));
				filmIdList.add(rs.getString("film.id"));
				
				
				System.out.println("----------------------");
				System.out.println("Languages:");
				
				while (rs.previous()) {
					if (filmLanguageList.contains(rs.getString("language.name"))) {
						continue;
					}
					System.out.println(rs.getString("language.name"));
					filmLanguageList.add(rs.getString("language.name"));
				}
				
				
				System.out.println("----------------------");
				System.out.println("Actors:");
				
				while (rs.next()) {
					if (filmActorList.contains(rs.getString("actor.first_name"))
							&& filmActorList.contains(rs.getString("actor.last_name"))) {
						continue;
					}
					System.out.println(rs.getString("actor.first_name") + " " + rs.getString("actor.last_name"));
					filmActorList.add(rs.getString("actor.first_name"));
					filmActorList.add(rs.getString("actor.last_name"));
				}
			}

//			if (isNotEmpty) {
//				System.out.println("----------------------");
//				System.out.println("Languages:");
//			}
//
//			while (rs.previous()) {
//				if (filmLanguageList.contains(rs.getString("language.name"))) {
//					continue;
//				}
//				System.out.println(rs.getString("language.name"));
//				filmLanguageList.add(rs.getString("language.name"));
//			}
//
//			if (isNotEmpty) {
//				System.out.println("----------------------");
//				System.out.println("Actors:");
//			}
//
//			while (rs.next()) {
//				if (filmActorList.contains(rs.getString("actor.first_name"))
//						&& filmActorList.contains(rs.getString("actor.last_name"))) {
//					continue;
//				}
//				System.out.println(rs.getString("actor.first_name") + " " + rs.getString("actor.last_name"));
//				filmActorList.add(rs.getString("actor.first_name"));
//				filmActorList.add(rs.getString("actor.last_name"));
//			}
//			System.out.println("----------------------");
		} catch (SQLException e) {
			System.err.println(e);
		}

	}

	public void showFilmInfoUsingID(String filmId) throws SQLException {

		String user = "student";
		String password = "student";
		String sqlText;
		filmIdList.clear();
		filmLanguageList.clear();
		filmActorList.clear();

		sqlText = "SELECT film.id, film.title, film.release_year, film.rating, film.description, language.name , actor.first_name, actor.last_name "
				+ "FROM film " + "JOIN language ON film.language_id = language_id "
				+ "JOIN film_actor ON film_actor.film_id = film.id " + "JOIN actor ON actor.id = film_actor.actor_id "
				+ "WHERE film.id = " + filmId + ";";

		try (Connection connection = DriverManager.getConnection(URL, user, password);
				PreparedStatement stmt = connection.prepareStatement(sqlText);
				ResultSet rs = stmt.executeQuery();) {

			boolean isNotEmpty = rs.next();

			if (isNotEmpty) {

			}

			if (isNotEmpty) {
				System.out.println("----------------------");
				System.out.println("Film Information:");
				rs.previous();
			} else {
				System.out.println("There is no film with that film ID.");
			}

			while (rs.next()) {
				while (true) {
					if (filmIdList.contains(rs.getString("film.id"))) {
						// do nothing
					} else {
						System.out.println("Film ID: " + rs.getString("film.id") + ", Film Title: "
								+ rs.getString("film.title") + ", Release Year: " + rs.getString("film.release_year")
								+ "\nDescription: " + rs.getString("film.description"));
						filmIdList.add(rs.getString("film.id"));
					}

					System.out.println("----------------------");
					System.out.println("Languages:");
					while (rs.next()) {
						if (filmLanguageList.contains(rs.getString("language.name"))) {
							//do nothing
						} else {
							System.out.println(rs.getString("language.name"));
							filmLanguageList.add(rs.getString("language.name"));
						}
					}

					System.out.println("----------------------");
					System.out.println("Actors:");
					while (rs.next()) {
						if (filmActorList.contains(rs.getString("actor.first_name"))
								&& filmActorList.contains(rs.getString("actor.last_name"))) {
							continue;
						} else {
							System.out.println(rs.getString("actor.first_name") + " " + rs.getString("actor.last_name"));
							filmActorList.add(rs.getString("actor.first_name"));
							filmActorList.add(rs.getString("actor.last_name"));
						}
					}

				}
			}

			System.out.println("----------------------");
		} catch (SQLException e) {
			System.err.println(e);
		}

	}
}
