package com.company;

// exception required
class InvalidDomain extends Exception {
    private String email;
    InvalidDomain(String email) {
        this.email = email;
    }

    public String toString() {
        return "error in domain of: "+email;
    }
}

class InvalidAtTheRate extends Exception {
    private String email;
    InvalidAtTheRate(String email) {
        this.email = email;
    }

    public String toString() {
        return "error in @ usage in: "+email;
    }
}

class InvalidDot extends Exception {
    private String email;
    InvalidDot(String email) {
        this.email = email;
    }

    public String toString() {
        return "error in usage of dot in: "+email;
    }
}

class WeakPassword extends Exception {
    @Override
    public String toString() {
        return "The password must contain minimum 5 characters with atleast 1 symbol and 1 number";
    }
}

// User class is used to validate the entered username and password
public class User {
    private String password;
    private String email;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    static int countChar(String string, char identifier) {
        int count = 0;
        char[] chars = string.toCharArray();
        for (char c : chars) {
            if(c == identifier) ++count;
        }
        return count;
    }

    static void validateEmail(User u) throws InvalidDomain, InvalidAtTheRate, InvalidDot{
        // validating email
        String[] domains = {"com","in","net"};

        // checking '.' and '@' count
        if(countChar(u.email, '@') != 1) throw new InvalidAtTheRate(u.email);
        if(countChar(u.email, '.') != 1) throw new InvalidDot(u.email);

        String domain = u.email.substring(u.email.indexOf(".")+1);

        boolean isValidDomain = false;
        for (String s : domains) {
            if (s.equals(domain)) {
                isValidDomain = true;
                break;
            }
        }
        if(!isValidDomain) throw new InvalidDomain(u.email);


    }

    public static void validatePassword(User u) throws WeakPassword {
        // validating password
        // min length is 5 chars
        if(u.password.length() < 5) throw new WeakPassword();

        // searching for symbol atleast 1
        boolean isSymbolPresent = false;
        boolean isDigitPresent = false;
        char[] chars = u.password.toCharArray();
        for (char c : chars) {
            if(isDigitPresent && isSymbolPresent) break;
            if (!Character.isLetterOrDigit(c)) isSymbolPresent = true;
            if(!Character.isDigit(c)) isDigitPresent = true;
        }

        if(!isSymbolPresent || !isDigitPresent) throw new WeakPassword();
    }

}
