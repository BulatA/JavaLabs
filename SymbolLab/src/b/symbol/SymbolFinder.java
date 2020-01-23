package b.symbol;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.stream.Collectors;

public class SymbolFinder {
	
	static Character[] Merge(Character[] A, Character[] B) {
		int aLenght = A.length;
		int bLenght = B.length;
		Character[] C = new Character[aLenght + bLenght];
		System.arraycopy(A, 0, C, 0, aLenght);
		System.arraycopy(B, 0, C, aLenght, bLenght);
		return C;
	}
	
	public static void main(String[] args) {
		try {
			FileInputStream fis = new FileInputStream("voyna-i-mir-tom-1.txt"); 
			BufferedReader br = new BufferedReader(new InputStreamReader(fis, "Cp1251")); 
			String str;
			Character chars[] = {};
			//read a line
			while ((str = br.readLine()) != null) { 
				//With regular expression filter symbols and make it to lower case
				str = str.replaceAll("[^à-ÿÀ-ßa-zA-Z\\s]", "").toLowerCase(); 
				//Turn string to array of Character, delete the white spaces
				Character[] charObjectArray = str.chars().filter(c ->c != (int)' ').mapToObj(c ->(char) c).toArray(Character[]::new); 
				//merge the arrays of lines
				chars = Merge(chars, charObjectArray); 
			}
			for (int l = 0; l < (chars.length/2000); l++) {
				System.out.println("Part of text ¹"+l);
				Character x[];
				if((double)chars.length/(2000 * (l+1)) >= 1.0003) {
					x = majority(chars, 2000 * l, 2000 * (l + 1), 10); 
				}else {
					x = majority(chars, 2000 * l, chars.length-1, 10); 
				}
					
				
				for (int i = 0; i < 10 && i < x.length; i++)
					System.out.print(x[i].charValue() + " ");
				System.out.println();
			}
			br.close();
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Find the most common characters in array of 2000 elements. There will be 10 characters in total
	private static Character[] majority(Character[] array, int B, int N, int k) {
		
		Map<Character, Integer> candidates = new HashMap<Character, Integer>();
		for (int i = B; i < N; i++) {

			if (candidates.containsKey(array[i])) {
				//System.out.println("contain: "+candidates.size());
				candidates.put(array[i], candidates.get(array[i]).intValue() + 1);
			}
				
			else 
				if (candidates.size() < k) {
					candidates.put(array[i], 1);
					//System.out.println("not contain: "+candidates.size());
				}
					
				else 
				{
					Character x[] = (Character[]) candidates.keySet().toArray(new Character[candidates.size()]);
					for (Character l : x) {
						candidates.put(l, candidates.get(l).intValue() - 1);
						//System.out.println(l + ":" +candidates.get(l));
						if (candidates.get(l).intValue() == 0) {
							candidates.remove(l); 
							candidates.put(array[i], 1);
							//System.out.println("putting:"+array[i]);
							break;
							//candidates.put(array[i], 1);
						}
					//System.out.println("remain: "+candidates.size());	
					}
					//System.out.println("-------");
							
			}
			candidates = candidates.entrySet().stream()
					.sorted(Map.Entry.comparingByValue())
					.collect(Collectors.toMap(
					          Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		}
		
		return (Character[]) candidates.keySet().toArray(new Character[candidates.size()]); 
	}

	public static Character[] getPopulate(Character[] array, int B, int N, int k) {
		return null;
	}

}
