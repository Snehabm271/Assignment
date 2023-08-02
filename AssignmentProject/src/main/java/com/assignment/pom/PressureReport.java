package com.assignment.pom;

import java.io.IOException;

import com.assignment.generic.BaseClass;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class PressureReport extends BaseClass{
	public void getPressure() {
		try {
			String date = getDateFromUser();
			String apiUrl = api_url;
			String apiResponse = makeAPIRequest(apiUrl);

			Gson gson = new Gson();
			JsonObject responseJson = gson.fromJson(apiResponse, JsonObject.class);
			JsonArray hourlyForecasts = responseJson.getAsJsonArray("list");

			for (JsonElement element : hourlyForecasts) {
				JsonObject forecast = element.getAsJsonObject();
				String forecastDate = forecast.get("dt_txt").getAsString();
				if (forecastDate.contains(date)) {
					JsonObject mainData = forecast.getAsJsonObject("main");
					double pressure = mainData.get("pressure").getAsDouble();
					System.out.println("Pressure on " + forecastDate + ": " + pressure + " hPa");
				}
			}
		} catch (IOException e) {
			System.out.println("Error: Failed to retrieve pressure data.");
		}
	}

}
