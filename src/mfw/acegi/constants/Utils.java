package mfw.acegi.constants;


public class Utils
{
	public static boolean isNull(String a)
	{
		if ((a == null) || (a.trim().length() == 0)) {
			return true;
			}
		return false;
		}

	
	public static boolean isNotNull(String a) {
		return !isNull(a);
		}

	
	public static boolean isNotNull(Integer[] a) {
		if ((a == null) || (a.length == 0)) {
			return false;
			}
		return true;
		}

	
	public static boolean isNotNull(Integer a) {
		if ((a == null) || (a.intValue() == 0)) {
			return false;
			}
		return true;
		}

	
	public static boolean isNotNull(Short a) {
		if ((a == null) || (a.intValue() == 0)) {
			return false;
			}
		return true;
		}
	
}

/*
 * Location: D:\Documents\certificate\WEB-INF\classes\ Qualified Name:
 * mfw.acegi.constants.Utils JD-Core Version: 0.6.2
 */