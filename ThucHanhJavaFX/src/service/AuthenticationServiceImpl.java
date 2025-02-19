package service;

import core.HRM;
import core.Human;

public class AuthenticationServiceImpl implements AuthenticationService {
	@Override
	public boolean login(Human user) {
		HRM hrm = new HRM();
		if (hrm.checkHR(user)) {
			return true;
		}
		return false;
	}
}
