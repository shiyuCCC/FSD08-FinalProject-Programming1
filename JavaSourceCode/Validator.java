
public class Validator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		System.out.print(singleAtSign("A@b@c@d"));

	}
	
	public static boolean isAlphaNum(char input) {
		
		//check if the input is alphanumeric.
		
		return (input >= 'a'&& input<='z')||(input >= 'A' && input<='Z')||(input>='0'&&input<='9');
		
	}
	
	
	public static boolean isSpecialChar(char a, boolean b) {
		return ((a == '-' || a =='.') || (a == '_' && b == true));
		
	}
	
	
	public static boolean isPrefixChar(char a) {
		
		if ((isAlphaNum(a)== true) || (isSpecialChar(a, true)==true)) {
			return true;
		}else return false;
	}
	
	
	public static boolean isDomainChar(char a) {
		if ((isAlphaNum(a)== true) || (isSpecialChar(a, false)==true)) {
			return true;
		}else return false;
		
	}

	
	public static boolean singleAtSign(String a) {
		int count = 0;
		//for loop to check each char in the string and record the times of @
		for ( int i =0;i<a.length();i++) {
			if (a.charAt(i) == '@') {
				count++;} else;
		}
		return (count == 1);
	}

	// fetchBeforeAt() and fetchAfterAt() are very similar. Both method using
    // split() method, the only difference is the
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
        // split the domain by periods
        int numSplit = domain.split("\\.").length;

        // if the splitted array length is not 2
        // then number of periods is not 1
        // domain is invalid
        if (numSplit != 2) {
            return false;
        }

        String firstPortion = domain.split("\\.")[0];
        String secondPortion = domain.split("\\.")[1];
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
}
