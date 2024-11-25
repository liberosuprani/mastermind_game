package types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//All documentation is needed.

public class Code implements Cloneable {

	private List<? extends Colour> code;

	public Code(List<? extends Colour> code) {
		this.code = new ArrayList<Colour>();
		
		for (int i = 0; i < code.size(); i++)
			((List<Colour>)this.code).add(code.get(i));
	}
	
	public List<Colour> getCode() {
		return (List<Colour>)this.code;
	}

	public int getLength() {
		return this.code.size();
	}

	public int[] howManyCorrect(Code other) {
		int correctPosition = 0;
		
		List<Colour> userTry = other.getCode();
		
		List<? extends Colour> secret = this.code;
		List<Colour> clonedSecret = this.clone().getCode();
		
		Map<Integer, Colour> wrongPositionMap = new HashMap<Integer, Colour>();
		
		for (int i = 0; i < other.getLength(); i++) {
			Colour currentColour = userTry.get(i);
			
			if (clonedSecret.contains(currentColour)) {
				int firstAppearance = clonedSecret.indexOf(currentColour);
				
				if (userTry.get(i) == secret.get(i)) {
					correctPosition++;
					clonedSecret.remove(currentColour);
					
					if (wrongPositionMap.containsKey(firstAppearance))
						wrongPositionMap.remove(firstAppearance);
				}
				else if (!wrongPositionMap.containsKey(firstAppearance))
					wrongPositionMap.put(firstAppearance, currentColour);
			}
		}
		
		
		
		int[] result = {correctPosition, wrongPositionMap.size()};
		return result;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (Colour colour : this.code)
			sb.append(colour + ", ");
		
		sb.delete(sb.length()-2, sb.length());
		sb.append("]");
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
