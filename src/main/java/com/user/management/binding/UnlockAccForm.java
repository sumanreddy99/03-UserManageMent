package com.user.management.binding;

import lombok.Data;

@Data
public class UnlockAccForm {
	
	
	private String email;	
	private String tempPassword;
	private String newPassword;
	private String confirmpassword;

}
