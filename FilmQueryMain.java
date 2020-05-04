package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.Scanner;

public class FilmQueryMain {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		Film film = new Film();
		Scanner input = new Scanner(System.in);
        String menuChoice = "";
        
        while (true) {
            System.out.println("Please Choose from the Following Menu: ");
            System.out.println("1. Look up film by Film ID number");
            System.out.println("2. Look up film by keyword");
            System.out.println("3. Exit Application");
        	menuChoice = input.nextLine();
            
            if (menuChoice.equals("1")) {
            	System.out.println("Please enter the film ID number");
            	String filmId = input.nextLine();
            	film.showFilmInfoUsingID(filmId);
            } else if (menuChoice.equals("2")) {
            	System.out.print("Please enter your keyword: ");
            	String keyword = input.nextLine();
            	film.showFilmInfoUsingKeyword(keyword);
            } else if (menuChoice.equals("3")) {
            	System.out.println("Have a wonderful day. Good-bye.");
            	break;    	
            } else {
            	System.out.println("Sorry, the value you have entered is invalid");
            	continue;
            }
        } 		
	}

}
