package types;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BullsAndCowsCode extends Code {
	
	public BullsAndCowsCode(List<? extends Colour> code) {
		super(code);
	}

	@Override
	public int[] howManyCorrect(Code other) {
		
		List<Colour> userTry = other.getCode();
		
		List<? extends Colour> secret = this.code;

		Map<Integer, Colour> rightPositionMap = new HashMap<Integer, Colour>();
		Map<Integer, Colour> wrongPositionMap = new HashMap<Integer, Colour>();
		
		for (int i = 0; i < other.getLength(); i++) {
			Colour currentColour = userTry.get(i);

			if (secret.contains(currentColour)) {
				
				if (currentColour == secret.get(i)) {
					rightPositionMap.put(i, currentColour);
					
					if (wrongPositionMap.containsKey(i))
						wrongPositionMap.remove(i);
				} 

				else {
					boolean foundWrongIndex = false;

					int j = 0;
					while(!foundWrongIndex && j < secret.size()) {
						if (!rightPositionMap.containsKey(j) && !wrongPositionMap.containsKey(j) && secret.get(j).equals(currentColour)) {
							foundWrongIndex = true;
							wrongPositionMap.put(j, currentColour);
						}
						j++;
					}
				}
			}
		}
		
		int[] result = {rightPositionMap.size(), wrongPositionMap.size()};
		return result;
	}
		
}
	

