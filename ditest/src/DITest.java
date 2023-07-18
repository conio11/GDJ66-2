public class DITest {
	public static void main (String[] args) {
		루피 a = new 루피(); // bean @Component() -> 객체를 미리 갖고 있다가 주입(DI)
		
		나미 n = new 나미();
		n.p = a; // DI @Autowired
		n.호출하다(); 		
	}
}