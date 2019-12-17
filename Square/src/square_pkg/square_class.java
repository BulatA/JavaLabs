package square_pkg;

public class square_class {
	public String SwitchSolution(float a, float b, float c) {
		System.out.println(String.format("%f*x^2+%f*x+%f", a,b,c));
		if(a == 0) {
			if(b == 0) {
				return (c == 0) ? "Solution is 0" : "Wrong solution!";
			}
			return (b != 0) ? SolveSimple(b,c) : "Divide by zero! Solution doesn't has roots";
		}
		if(b == 0 & a > 0 & c >0) {
			return "Solution doesn't has roots";
		}
		
		float D = GetDiscriminant(a, b, c);
		if (D > 0) {
			
			return Solve(a,b,c,D);
			
		}else if(D == 0) {
			
			return Solve(a,b,c);
			
		}else return "Negative dicriminant! Solution doesn't has roots";
	}
	
	private float GetDiscriminant(float a, float b, float c) {
		return (float)Math.pow(b, 2) - 4*a*c;
	}
	
	public String Solve(float a, float b, float c, float D) {
		float x1 = (-b + (float)Math.sqrt(D))/(2*a);
		
		float x2 = (-b - (float)Math.sqrt(D))/(2*a);
		
		float result1 = a * (float)Math.pow(x1, 2) + b * x1 + c;
		
		float result2 = a * (float)Math.pow(x2, 2) + b * x2 + c;
		
		if(result1 == 0 & result2 == 0) {
			System.out.println("It's work");
		}else {
			System.out.println(result1);
			System.out.println(result2);
		}
		return String.format("%f*%f^2+%f*%f+%f=0", a,x1,b,x1,c) + " \nor\n " +String.format("%f*%f^2+%f*%f+%f=0", a,x2,b,x2,c);
	}
	
	public String Solve(float a, float b, float c) {
		float x1 = -b/(2*a);
		float result1 = a * (float)Math.pow(x1, 2) + b * x1 + c;
		if(result1 == 0) {
			System.out.println("SolveOneRoot It's work");
		}else {
			System.out.println("SolveOneRoot" + result1);
		}
		return String.format("%f*%f^2+%f*%f+%f=0", a,x1,b,x1,c);
	}
	
	public String SolveSimple(float b, float c) {
float x1 = -c/b;
		
		float result = b*x1+c;
		
		if(result == 0) {
			System.out.println("SimpleSolve It's work");
		}
		else {
			System.out.println("SimpleSolve " + result);
		}
		return String.format("%f*%f+%f=0", b,x1,c);
	}	
}

