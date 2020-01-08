package calcserver;


public class Calculator {
	
	public String Calc(String example) throws Exception{
		String[] operation = null;
		double a,b = 0;
		try {
			operation = example.split(" ");
			a = Double.parseDouble(operation[0]);
			b = Double.parseDouble(operation[1]);
			return Solve (a, b, operation[2]);
		}catch(Exception e) {
			return "Wrong format!";
		}finally {
				
		}
		
	}
	public String Solve(double a, double b, String op){
		switch(op) {
		case "add" : return "Result "+ (a + b);
		case "substract" : return "Result "+ (a - b);
		case "multiply" : return "Result "+ (a * b);
		case "division" : return "Result "+ (a / b); 
		default : return "Unexist operator";
		}
	}
}
