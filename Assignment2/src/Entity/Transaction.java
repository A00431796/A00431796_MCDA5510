package Entity;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.mysql.cj.util.StringUtils;


public class Transaction { 
	Pattern regex = Pattern.compile("[$&+,:;=?@#|]");
	Pattern regex2 = Pattern.compile("^5[1-5][0-9]{5,}|222[1-9][0-9]{3,}|22[3-9][0-9]{4,}|2[3-6][0-9]{5,}|27[01][0-9]{4,}|2720[0-9]{3,}$]");
	public Integer id;
	public String nameOnCard;
	
	public String cardNumber;
	public String CardType;
	
	public float unitprice;
	
	public int quantity;
	
	public float TotalPrice;
	
	public String ExpDate;
	
	public String CreatedOn;
	
	public String  createdBy ;
	
	
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	public String getNameOnCard() {
		return nameOnCard;
	}
	public void setNameOnCard(String nameOnCard) {
		
    	
		this.nameOnCard = nameOnCard;
    	
	}	
	
	public String toString(){
		
		String results = new String();
		
		results = "[NameOnCard: " + nameOnCard +",CardNumber: " + cardNumber+"]";
		return results;

	} 
	
	public String Validation(Transaction trans)
	{    
		Matcher matcher ;
		String digit2 = trans.cardNumber.substring(0,2);
		
		String errorMessage = new String();
		
				
		if( regex.matcher(trans.nameOnCard).find())
		{ 
			errorMessage="Name on card contains Special Charcters";
			
		}
		if( regex.matcher(trans.CardType).find() && (trans.CardType.equalsIgnoreCase("Visa")|| trans.CardType.equalsIgnoreCase("MasterCard")||trans.CardType.equalsIgnoreCase("American Express")))
		{
			errorMessage="Card Type should be either Visa,MasterCard or American Exress and Should not contain Special Charcters";
		}
		
		if(StringUtils.isStrictlyNumeric((trans.cardNumber)))
		{ 
		if(trans.CardType.equalsIgnoreCase("MasterCard") && (trans.cardNumber.length()!=16 || digit2.compareTo("51")>=0 || digit2.compareTo("55")<=0))
		{
			errorMessage="Master card should be length of 16 and  prefix of the card should be from 51-54";
		}
		if(trans.CardType.equalsIgnoreCase("Visa") && (trans.cardNumber.length()!=16 || !trans.cardNumber.startsWith("4")))
		{ 
			errorMessage="Visa should be legth of 16 and Should prefix starts from 4";
		
		}
		if(trans.CardType.equalsIgnoreCase("American Express") && (trans.cardNumber.length()!=15 ||(!trans.cardNumber.startsWith("34")|| !trans.cardNumber.startsWith("37"))))
		{ 
			errorMessage="American Express should be legnth of 15 and prefix starts from 34 or 37  ";
		}
		}
		else
		{
			errorMessage="Card Number should be numeric";
		}
		
		return errorMessage;
		
	} 
	
	
	
} 
