package directoryModel;
/**
 * 
 * @author Luis
 *This class will hold static methods that can be used by entity classes to validate
 *their inputs.
 */
public final class Utility {	
	
	/**
	 * Method to validate a string. checks that the string is not null
	 * nor it contains empty spaces. 
	 * @param strValue
	 * @return true if strValue is valid
	 */
	public static boolean isStringDataValid(String strValue){
		boolean isValid = true;
		boolean isStrEmpty = true;
		//check for an null variable being passed in
		if(strValue == null){
			return false;
		}
		//check to see strValue is not an empty string
		if(strValue.length() > 0 ){
			//check to make sure strValue is not full of empty spaces.
			for(int i = 0; i < strValue.length(); i++){
				if(!(strValue.charAt(i) == ' ')){
					isStrEmpty = false;
					break;
				}
			}
			if(isStrEmpty){
				isValid = false;
			}
		}else{
			isValid = false;
		}
		return isValid;
	}
	
	/**
	 * Method to extract the last Category
	 * @param cats
	 * @return
	 */
	public static String buildCategoryHierarchy(String [] cats){
		String output = "";
		for(int i = 0; i < cats.length; i++){
			if(cats[i] != null){
				output += cats[i] + "-";
			}
		}
		//delete the last hyphen in the string before returning
		output = output.substring(0,output.length() -1);
	 return output;
	}
	
	public static String getCategoryName(String [] cats){
		for(int i = cats.length-1; i >= 0; i--){
			if(cats[i] != null){
				return cats[i];
			}
		}
		return "";		
	}
		
}