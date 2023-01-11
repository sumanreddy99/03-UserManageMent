package com.user.management.service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.management.binding.Login;
import com.user.management.binding.UnlockAccForm;
import com.user.management.binding.UserForm;
import com.user.management.email.EmailUtils;
import com.user.management.entity.City;
import com.user.management.entity.Country;
import com.user.management.entity.State;
import com.user.management.entity.User;
import com.user.management.repository.CityRepo;
import com.user.management.repository.CountryRepo;
import com.user.management.repository.StateRepo;
import com.user.management.repository.UserManagementRepository;

@Service
public class UserManagementServiceImpl implements UserManagementService {

	@Autowired
	private UserManagementRepository userMgtRepo;

	@Autowired
	private CountryRepo countryRepo;

	@Autowired
	private StateRepo stateRepo;

	@Autowired
	private CityRepo cityRepo;
	
	@Autowired
	private EmailUtils emailUtils;

	@Override
	public Map<Integer, String> getCountries() {

		List<Country> countries = countryRepo.findAll();

		Map<Integer, String> countryMap = new HashMap<>();

		countries.forEach(country -> countryMap.put(country.getCountryId(), country.getCountryName()));

		return countryMap;
	}

	@Override
	public Map<Integer, String> getStates(Integer countryId) {

		List<State> states = stateRepo.findByCountryId(countryId);

		Map<Integer, String> stateMap = new HashMap<Integer, String>();

		states.forEach(state -> stateMap.put(state.getStateId(), state.getStateName()));

		return stateMap;
	}

	@Override
	public Map<Integer, String> getCities(Integer stateId) {

		List<City> cities = cityRepo.findByStateId(stateId);

		Map<Integer, String> cityMap = new HashMap<Integer, String>();

		cities.forEach(city -> cityMap.put(city.getCityId(), city.getCityName()));

		return cityMap;

	}

	@Override
	public String registerUser(UserForm userForm) {

		// copy the data from form obj to entity obj

		User userEntity = new User();

		BeanUtils.copyProperties(userForm, userEntity);

		// generate and set the random password

		userEntity.setPassword(generatePwd());

		// set Acctive Status

		userEntity.setActiveStatus("LOCKED");

		userMgtRepo.save(userEntity);

		

		String to = userForm.getEmail();
		String subject = "Registration Form";
		String body = readEmailBody("REG_EMAIL_BODY.txt", userEntity);
		emailUtils.sendMail(to, subject, body);
		System.out.println(to +" "+subject+" "+body);

		return "Created";
	}

	@Override
	public String emailCheck(String email) {

		User uemail = userMgtRepo.findByEmail(email);
		if (uemail == null) {
			return "UNIQUE";
		}

		return "DUPLICATE";
	}

	@Override
	public String unlockAccount(UnlockAccForm accForm) {

		String email = accForm.getEmail();
		User user = userMgtRepo.findByEmail(email);

		if (user !=null  && user.getPassword().equals(accForm.getTempPassword())) {
			user.setPassword(accForm.getConfirmpassword());
			user.setActiveStatus("UNLOCKED");
			userMgtRepo.save(user);
			return "Account Unlocked";

		}

		return "Invalid Temporary Password";
	}

	@Override
	public String login(Login loginForm) {

		User user = userMgtRepo.findByEmailAndPassword(loginForm.getEmail(), loginForm.getPassword());

		if (user == null) {
			return "Invalid Credentials";
		}

		if (user.getActiveStatus().equals("LOCKED")) {
			return "Acccount Locked";
		}

		return "SUCCESS";
	}

	@Override
	public String forgotPwd(String email) {

		User user = userMgtRepo.findByEmail(email);

		if (user == null) {
			return "No Email Acoount";
		}

	
		String subject = "Recover Password";
		String body = readEmailBody("FORGOT_PWD_EMAIL_BODY.txt", user);
		emailUtils.sendMail(email, subject, body);

		return "Password sent to registered email";
	}

	private String generatePwd() {
		String text = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

		Random random = new Random();

		StringBuilder sb = new StringBuilder();

		for (int i = 1; i <= 6; i++) {
			int index = random.nextInt(text.length());
			sb.append(text.charAt(index));

		}
		System.out.println(sb.toString());
		return sb.toString();
	}

	// Read Data from File
	private String readEmailBody(String filename, User user) {

		StringBuffer sb = new StringBuffer();
		try (Stream<String> lines = Files.lines(Paths.get(filename))) {

			lines.forEach(line -> {
				line = line.replace("${FNAME}", user.getFirstName());
				line = line.replace("${LNAME}", user.getLastName());
				line = line.replace("${TEMP_PWD}", user.getPassword());
				line = line.replace("${EMAIL}", user.getEmail());
				line = line.replace("${PWD}", user.getPassword());
				sb.append(line);
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();

	}
}