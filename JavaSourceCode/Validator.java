
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
	
}
