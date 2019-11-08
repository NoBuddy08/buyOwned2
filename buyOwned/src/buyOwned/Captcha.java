package buyOwned;

public class Captcha {
	
	public static String createcaptcha()
	{
		String a="ABCDEFGHIJKLMNOPQRSTUVWXYZ"+"0123456789"+"abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb=new StringBuilder(5);
        for(int i=0;i<5;i++)
        {
            sb.append(a.charAt( (int) (a.length()*Math.random()) ) );
        }
        return sb.toString();
	}
	public static String createotp()
	{
		String a="0123456789";
        StringBuilder sb=new StringBuilder(5);
        for(int i=0;i<5;i++)
        {
            sb.append(a.charAt( (int) (a.length()*Math.random()) ) );
        }
        return sb.toString();
	}
}
