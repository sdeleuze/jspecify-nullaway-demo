package org.example;

public class Main {

	public static void main(String[] args) {
		TokenExtractor extractor = new DefaultTokenExtractor();
		String token = extractor.extractToken("...");
		// Uncomment the if statement to fix the nullability issue
		// if (token != null) {
		System.out.println("The token has a length of " + token.length());
		// }
	}
}