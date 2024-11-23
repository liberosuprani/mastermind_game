package types;

import java.util.ArrayList;
import java.util.List;

//All documentation is needed.

public class Code implements Cloneable {

	private List<? extends Colour> code;

	public Code(List<? extends Colour> code) {
		this.code = code;
	}

	public List<Colour> getCode() {
		return (List<Colour>)this.code;
	}

	public int getLength() {
		return this.code.size();
	}

	public int[] howManyCorrect(Code other) {
		int correctPosition = 0, wrongPosition = 0;
		List<Colour> userTry = other.getCode(); 
		
		for (int i = 0; i < other.getLength(); i++) {
			Colour currentColour = userTry.get(i);
			
			if (this.code.contains(currentColour)) {
				if (this.code.get(i) == userTry.get(i)) 
					correctPosition++;
				else 
					wrongPosition++;
			}
		}
		
		int[] result = {correctPosition, wrongPosition};
		return result;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Colour colour : this.code)
			sb.append(colour);
		
		return sb.toString();
	}

	@Override
	public Code clone() {
		List<Colour> clonedCode = new ArrayList<Colour>();
		for (Colour colour : this.code) 
			clonedCode.add(colour);
		
		return new Code(clonedCode);
	}

	@Override
	public boolean equals(Object obj) {
		if ( !(obj instanceof Code) ) 
			return false;
		else {
			Code castObj = (Code) obj;
			List<Colour> objCode = castObj.getCode(); 
	
			if (this.code.size() != objCode.size())
				return false;
	
			for (int i = 0; i < objCode.size(); i++) {
				if (objCode.get(i) != this.code.get(i))
					return false;
			}	
		}
		return true;
	}
}
