package com.vmezhevikin.blog.model;

public class JsonResponse {

	private String status;

	public JsonResponse(String status) {
		super();
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}