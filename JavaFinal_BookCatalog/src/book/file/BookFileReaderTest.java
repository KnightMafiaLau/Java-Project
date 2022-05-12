// 20220418
package book.file;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class BookFileReaderTest {
	
	// lists to store lines from different loaded book files
	List<String> catInTheHat;
	List<String> warAndPeace;
	List<String> siddhartha;

	@BeforeEach
	void setUp() throws Exception {
		
		////////////////////////////////////////////////
		///// DO NOT MODIFY THE TEST CODE BELOW! ///////
		
		//load and parse cat in the hat snippet book file
		this.catInTheHat = BookFileReader.parseFile("the_cat_in_the_hat_snippet.txt");
		
		//load and parse war and peace book file
		this.warAndPeace = BookFileReader.parseFile("war_and_peace.txt");
		
		//load and parse siddhartha book file
		this.siddhartha = BookFileReader.parseFile("siddhartha.txt");
    
		///// DO NOT MODIFY THE TEST CODE ABOVE! ///////
		////////////////////////////////////////////////
		
	}

	@Test
	void testParseFile() {

		////////////////////////////////////////////////
		///// DO NOT MODIFY THE TEST CODE BELOW! ///////
		
		//create array with first 10 expected lines of cat in the hat snippet
		String[] expectedLines1Array = {
			"Title: The Cat in the Hat",
			"Author: Dr. Seuss",
			"The sun did not shine.",
			"It was too wet to play.",
			"So we sat in the house",
			"All that cold, cold, wet day.",
			"I sat there with Sally.",
			"We sat there, we two.",
			"And I said, \"How I wish",
			"We had something to do!\""
		};
		List<String> expectedLines1 = new ArrayList<String>(Arrays.asList(expectedLines1Array));
		
		//compare to first 10 actual lines of cat in the hat snippet
		assertEquals(expectedLines1, this.catInTheHat.subList(0, 10), "The expected 10 lines do not match the actual first 10 'Cat in the Hat' book lines.");
		
		///// DO NOT MODIFY THE TEST CODE ABOVE! ///////
		////////////////////////////////////////////////
	
		
		// TODO insert 2 additional test cases

		String[] expectedLines2Array = {
				"Title: Siddhartha",
				"Author: Hermann Hesse",
				"Translator: Gunther Olesch, Anke Dreher, Amy Coulter, Stefan Langer and Semyon Chaichenets",
				"Release Date: April 6, 2008 [EBook #2500]",
				"Last Updated: February 6, 2019",
				"Language: English",
				"SIDDHARTHA",
				"An Indian Tale",
				"by Hermann Hesse",
				"FIRST PART"
				
		};
		List<String> expectedLines2 = new ArrayList<String>(Arrays.asList(expectedLines2Array));
		
		assertEquals(expectedLines2, this.siddhartha.subList(0, 10), "The expected 10 lines do not match the actual first 10 'siddhartha.");
	

		String[] expectedLines3Array = {
			"Title: War and Peace",
			"Author: Leo Tolstoy",
			"Translators: Louise and Aylmer Maude",
			"Posting Date: January 10, 2009 [EBook #2600]",
			"Last Updated: January 21, 2019",
			"Language: English",
			"WAR AND PEACE",
			"By Leo Tolstoy/Tolstoi",
			"CONTENTS",
			"BOOK ONE: 1805"
			
	};
	List<String> expectedLines3 = new ArrayList<String>(Arrays.asList(expectedLines3Array));
	assertEquals(expectedLines3, this.warAndPeace.subList(0, 10), "The expected 10 lines do not match the actual first 10 'Wae and Peace' book lines.");

}
        // Hint(s): Test sections of war and peace and siddhartha book files
	}

