import java.util.*;
public class Jni
{
	static 
	{
		System.loadLibrary("clean"); 
	}
 
	public static native void Clean();
	
}

