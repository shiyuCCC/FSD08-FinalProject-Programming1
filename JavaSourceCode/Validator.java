//Shiyu Cai 2339433
//Xuerun Wang 2340550
public class Validator {
	
	public static void main(String[] args) {		
	}
	
	public static boolean isAlphaNum(char input) {
		
		//check if the input is alphanumeric.
		
		return (input >= 'a'&& input <= 'z')||(input >= 'A' && input <= 'Z')||(input >= '0'&&input <= '9');
		
	}
	
	
	public static boolean isSpecialChar(char a, boolean b) {
		//check if the input is a reuired special character.
		return ((a == '-' || a =='.') || (a == '_' && b == true));
		
	}
	
	
	public static boolean isPrefixChar(char a) {
		//check if the input is an alphanumeric character or a special character.
		if ((isAlphaNum(a) == true) || (isSpecialChar(a, true) == true)) {
			return true;
		}else {
			return false;
		}
	}
	
	
	public static boolean isDomainChar(char a) {
		//check if the input is an alphanumeric character or a required special character.
		if ((isAlphaNum(a) == true) || (isSpecialChar(a, false) == true)) {
			return true;
		}else {
			return false;
		}
		
	}

	
	public static boolean singleAtSign(String a) {
		int count = 0;
		//for loop to check each char in the string and record the times of @
		for ( int i = 0;i < a.length();i++) {
			if (a.charAt(i) == '@') {
				count++;
            }
		}
		return (count == 1);
	}

	// fetchBeforeAt() and fetchAfterAt() are very similar. Both method using
    // split() method, the only difference is 
    // To get the beginning of an email address
    public static String fetchBeforeAt(String email) {
        return email.split("@")[0];
    }

    // To get the ending of an email address
    public static String fetchAfterAt(String email) {
        return email.split("@")[1];
    }

    public static boolean isPrefix(String prefix) {
        int length = prefix.length();

        if (length < 1) {
            return false; // check if prefix contains at least one character
        }

        if (!isAlphaNum(prefix.charAt(0))) {
            return false; // check if the first char is alphanumeric
        }

        for (int i = 0; i < length; ++i) {
            // if this char is not a valid prefix character, prefix is invalid
            if (!isPrefixChar(prefix.charAt(i))) {
                return false;
            }

            // if this char is a special char
            if (isSpecialChar(prefix.charAt(i), true)) {

                // if this special char is the last char of prefix, prefix is invalid
                if (i == length - 1) {
                    return false;
                }

                // if the next char after this special char is not an alphanumeric char, prefix
                // is invalid
                if (!isAlphaNum(prefix.charAt(i + 1))) {
                    return false;
                }
            }
        }

        // if the code executes to here, it means the prefex is valid
        return true;
    }


    public static Boolean isDomain(String domain) {
        // convert all characters to lower case
        domain = domain.toLowerCase();
        
        // split the domain by periods
    	String[] split = domain.split("\\.");
        int numSplit = split.length;

        // if the splitted array length is not 2
        // then number of periods is not 1
        // domain is invalid
        if (numSplit != 2) {
            return false;
        }

        String firstPortion = split[0];
        String secondPortion = split[1];
        int lengthFirst = firstPortion.length();
        int lengthSecond = secondPortion.length();
        // The first portion
        if (lengthFirst < 1) {
            return false;// check if the first portion contains at least one character
        }

        for (int i = 0; i < lengthFirst; ++i) {
            // if this char is not a valid domain character, domain is invalid
            if (!isDomainChar(firstPortion.charAt(i))) {
                return false;
            }

            // if this char is a special char
            if (isSpecialChar(firstPortion.charAt(i), true)) {

                // if this special char is the last char of domain, domain is invalid
                if (i == lengthFirst - 1) {
                    return false;
                }

                // if the next char after this special char is not an alphanumeric char, domain
                // is invalid
                if (!isAlphaNum(firstPortion.charAt(i + 1))) {
                    return false;
                }
            }
        }

        // The second portion
        if (lengthSecond < 2) {
            return false;// check if the second portion contains at least one character
        }

        // check if all chars in the second portion is from the alphabet
        for (int j = 0; j < lengthSecond; ++j) {
            char alphabet = secondPortion.charAt(j);
            if (alphabet < 'a' || alphabet > 'z') {
                return false;
            }
        }
        // if the code executes to here, it means the domain is valid
        return true;
    }
	

    public static Boolean isEmail(String email) {
        if (!singleAtSign(email)) {
            return false;
        }

        // split email address
        String prefix = fetchBeforeAt(email);
        String domain = fetchAfterAt(email);

        if (isPrefix(prefix) && isDomain(domain)) {
            return true;
        } else {
            return false;
        }
    }


	public static String isUsername(String username) {
        int length = username.length();
        Boolean ANFlag = false; // flag for alphanumeric

        // contains seven or less characters
        if (length > 7) {
            return "";
        }

        // must start with a period, or dash
        if (!isSpecialChar(username.charAt(0), false)) {
            return ""; // check if the first char is special letter
        }

        for (int i = 0; i < length; ++i) {

            char c = username.charAt(i);

            // check if char is not domain char or '!'
            if (!isDomainChar(c) || c == '!') {
                return "";
            }

            if (isAlphaNum(c)) {
                ANFlag = true;
            } else {
                // if this char is a special char
                if (isSpecialChar(c, false)) {

                    // if this special char is the last char of username, username is invalid
                    if (i == length - 1) {
                        return "";
                    }

                    // if the next char after this special char is not an alphanumeric char, username
                    // is invalid
                    if (!isAlphaNum(username.charAt(i + 1))) {
                        return "";
                    }
                }
            }

        }

        if (ANFlag == false) {
            return "";
        }

        return username.toLowerCase();
    }


    public static Boolean safePassword(String password) {
        int length = password.length();
        Boolean uppercaseFlag = false;
        Boolean lowercaseFlag = false;
        Boolean numberFlag = false;
        Boolean specialCharFlag = false;
        int countAN = 0; // count occurance of alphanumeric char

        // check if contains min 7 chars and max 15 chars
        if (length < 7 || length > 15) {
            return false;
        }

        for (int i = 0; i < length; ++i) {
            char ch = password.charAt(i);
            
            // count if two same chars appear consectively
            if (i < length-1 && ch == password.charAt(i+1)) {
                return false;
            }

            if (isAlphaNum(ch)) {
                countAN++;
            }

            if (Character.isUpperCase(ch)) {
                uppercaseFlag = true;
            } else if (Character.isLowerCase(ch)) {
                lowercaseFlag = true;
            } else if (Character.isDigit(ch)) {
                numberFlag = true;
            } else if (isSpecialChar(ch, true)) {
                specialCharFlag = true;
            }
        }

        // check if contains at least one alphanumeric char
        if (countAN < 1) {
            return false;
        }

        // check if Contain at least one uppercase letter, one lowercase letter,
        // one number, and one period, dash or underscore.
        if (uppercaseFlag && lowercaseFlag && numberFlag && specialCharFlag == false) {
            return false;
        }

        return true;
    }
    
}