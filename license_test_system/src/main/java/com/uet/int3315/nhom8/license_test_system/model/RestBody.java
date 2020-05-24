package com.uet.int3315.nhom8.license_test_system.model;

import com.uet.int3315.nhom8.license_test_system.utils.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestBody {
	private int errorCode;
	private String message;
	private Object data;

	public static RestBody newInstance(int errorCode, String message, Object data) {
		return new RestBody(errorCode, message, data);
	}

	public static RestBody success(Object data) {
		return newInstance(ResponseCode.SUCCESS, "Success", data);
	}

	public static RestBody error(String msg, Object data) {
		return newInstance(ResponseCode.ERROR, msg, data);
	}

	public static RestBody error(String msg) {
		return newInstance(ResponseCode.ERROR, msg, null);
	}

}
