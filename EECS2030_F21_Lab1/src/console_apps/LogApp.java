package console_apps;

import model.Log;

public class LogApp {

	public static void main(String[] args) {
		/* Create a new app update log with a version number */
		Log appUpdate = new Log("5.7.31");
		
		/* Retrieve the set version value. */
		String v = appUpdate.getVersion();
		System.out.println(v);
		
		/* Initially, no fixes have been added to the update log. */
		int n = appUpdate.getNumberOfFixes();
		System.out.println(n);
		
		/* The list of fixes (appearing within a pair of square brackets) is empty */
		String s1 = appUpdate.getFixes();
		System.out.println(s1);
		
		/* The string representation of an update log object includes:
		 * 	- its set version
		 * 	- the number of fixes so far
		 * 	- a comma-separated list of fixes, enclosed within a pair of square brackets
		 * Note: Always spell the word "fixes" even when the number of fixes is 0 or 1.
		 */
		String s2 = appUpdate.toString();
		System.out.println(s2);
	}

}
