package uz.jurayev.academy.hemis_api;

import uz.jurayev.academy.rest.request.StudentRequest;

@lombok.Data
public class Data {

	private String code;
	private StudentRequest studentInfo;
	private Boolean success;
}
