package subsystem.interbank;

import common.exception.UnrecognizedException;
import utils.ApplicationProgrammingInterface;

public class InterbankBoundary {
      //data coupling do su dung het du lieu truyen vao
	String query(String url, String data) { // 
		String response = null;
		try {
			response = ApplicationProgrammingInterface.post(url, data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new UnrecognizedException();
		}
		return response;
	}

}
