package com.example.userservice;

import com.example.domain.User;

public interface UserUserService {
    
    User selectByNum(String memberId);

	User selectForFind(User dto);
	
	Integer write(User dto);
	
	Integer update(User dto);

	Integer updateImg(User dto);

	void changeToTempPw(String memberId, String tempPw);

	Integer checkId(String memberId);

}
