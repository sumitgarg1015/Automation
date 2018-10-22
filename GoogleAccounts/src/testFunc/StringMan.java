package testFunc;



import org.testng.annotations.Test;

public class StringMan {

	private static StringMan st;
	public StringMan() throws Exception{

	}
	

	public static void main() throws Exception{
		st = new StringMan();
		st.start_Testing();
	}
	
	@Test
	public void start_Testing() throws Exception{
		
		for(int i=1;i<3;i++){
			if(i%2==0)
				executeKeywords();
		}
		
	}
	
	@Test (dependsOnMethods={"start_Testing"})
	public static void executeKeywords() throws Exception{
		
		System.out.println("This is Test execution");
		
	}

}
