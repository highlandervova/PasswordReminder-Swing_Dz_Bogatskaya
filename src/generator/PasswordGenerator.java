package generator;

public class PasswordGenerator {
    public static String generatePassword(){
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            sb.append(RandomService.getRandomSymbol());
        }
       return sb.toString();
    }
}
