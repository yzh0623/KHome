package com.kida.home.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

public class UserClient {

	private static String serverUri = "http://localhost:8080/KHome/user";

	public static void registerClient() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(serverUri + "/register");
		MultivaluedMap<String, String> formData = new MultivaluedHashMap<String, String>();
		formData.add("uname", "yuanzh");
		formData.add("pwd", "yzh0623");
		Response response = target.request().post(Entity.form(formData));
		response.close();
	}

	public static void main(String[] args) {
		UserClient uc = new UserClient();
		uc.registerClient();
	}

}
