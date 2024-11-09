package types;

import java.util.ArrayList;
import java.util.List;

//All documentation is needed.

public class Code implements Cloneable{

	private List<? extends Colour> code;

	public Code(List<? extends Colour> code) {
		this.code = code;
	}

	public List<Colour> getCode() {
		List<Colour> clonedCode = new ArrayList<Colour>(this.code.size());
		for (Colour c : this.code) {
			clonedCode.add(c);
		}
		return clonedCode;
	}

	public int getLength() {
		return this.code.size();
	}

	//TODO
	public int[] howManyCorrect(Code other) {
		return null; 
	}

	//TODO
	@Override
	public String toString() {
		return "String representation";
	}

	@Override
	public Code clone() {
		return new Code(this.getCode());
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
