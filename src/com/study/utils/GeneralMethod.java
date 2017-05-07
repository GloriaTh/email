package com.study.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/*
 * gson返回json
 */
public class GeneralMethod {
	public static void BackMessage(Object obj, HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=utf-8");

			PrintWriter out = response.getWriter();
			Gson gson = new Gson();
			String json = gson.toJson(obj);
			out.write(json);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
