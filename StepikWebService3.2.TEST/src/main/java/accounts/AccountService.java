package accounts;

import dbService.DBException;
import dbService.DBService;
import dbService.dataSets.UsersDataSet;

import java.util.HashMap;
import java.util.Map;

public class AccountService {
    private final Map<String, UserProfile> loginToProfile;
    private final Map<String, UserProfile> sessionIdToProfile;
    DBService dbService;

    public AccountService() {
        loginToProfile = new HashMap<>();
        sessionIdToProfile = new HashMap<>();
        dbService = new DBService();
    }

    public void addNewUser(UserProfile userProfile) {
        loginToProfile.put(userProfile.getLogin(), userProfile);
        try {
            dbService.addUser(userProfile.getLogin(), userProfile.getPassword());
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    public UserProfile getUserByLogin(String login) {
        UserProfile profile = loginToProfile.get(login);
        if (profile == null) {
            try {
                UsersDataSet dataSet = dbService.getUserByLogin(login);
                if (dataSet != null) {
                    String name = dataSet.getLogin();
                    String password = dataSet.getPassword();
                    profile = new UserProfile(name, password);
                }
            } catch (DBException e) {
             e.printStackTrace();
            }
        }
        return profile;
    }

    public UserProfile getUserBySessionId(String sessionId) {
        return sessionIdToProfile.get(sessionId);
    }

    public void addSession(String sessionId, UserProfile userProfile) {
        sessionIdToProfile.put(sessionId, userProfile);
    }

    public void deleteSession(String sessionId) {
        sessionIdToProfile.remove(sessionId);
    }
}
