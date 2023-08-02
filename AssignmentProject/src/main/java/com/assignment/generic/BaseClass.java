package com.assignment.generic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.assignment.pom.PressureReport;
import com.assignment.pom.WeatherReport;
import com.assignment.pom.WindSpeedReport;

public class BaseClass {
	public static String api_url = "https://samples.openweathermap.org/data/2.5/forecast/hourly?q=London,us&appid=b6907d289e10d714a6e88b30761fae22";
	public void run() {
		int option;
		do {
			displayMenu();
			option = getUserOption();
			switch (option) {
			case 1:
			{
				WeatherReport w=new WeatherReport();
				w.getWeather();
				break;
			}
			case 2:
			{
				WindSpeedReport w1=new WindSpeedReport();
				w1.getWindSpeed();
				break;
			}
			case 3:
			{
				PressureReport p=new PressureReport();
				p.getPressure();
				break;
			}
			case 0:
				System.out.println("Exiting.......");
				break;
			default:
				System.out.println("Invalid option,Please try again!");
			}
		} while (option!=0);
	}
	public void displayMenu() {
		System.out.println("==== Weather App ====");
		System.out.println("1. Get weather");
		System.out.println("2. Get Wind Speed");
		System.out.println("3. Get Pressure");
		System.out.println("0. Exit");
	}
	public int getUserOption() {
		System.out.print("Enter your option: ");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			return Integer.parseInt(br.readLine());
		} catch (IOException e) {
			return -1; // Invalid input
		}
	}
	public String makeAPIRequest(String apiUrl) throws IOException {
		StringBuilder response = new StringBuilder();
		URL url = new URL(apiUrl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream())))
		{
			String line;
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
		}
		return response.toString();
	}

	public String getDateFromUser() throws IOException {
		System.out.print("Enter date (YYYY-MM-DD): ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		return br.readLine();
	}


}
