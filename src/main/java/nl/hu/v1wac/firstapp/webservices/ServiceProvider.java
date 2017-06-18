package nl.hu.v1wac.firstapp.webservices;

import nl.hu.v1wac.firstapp.model.WorldService;

public class ServiceProvider {
	private static WorldService worldService = new WorldService();

	public static WorldService getWorldService() {
		return worldService;
	}
}