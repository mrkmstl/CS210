import java.io.*;
import java.security.*;
import java.lang.Math;

public class Main{
	public static String sha256(String input){
        try{
            MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
            byte[] salt = "CS210+".getBytes("UTF-8");
            mDigest.update(salt);
            byte[] data = mDigest.digest(input.getBytes("UTF-8"));
            StringBuffer sb = new StringBuffer();
            for (int i=0;i<data.length;i++){
                sb.append(Integer.toString((data[i]&0xff)+0x100,16).substring(1));
            }
            return sb.toString();
        }catch(Exception e){
            return(e.toString());
        }
    }
	/**
	 * The randNoun method pulls a random noun from words.txt file
	 */
    public static String randNoun(int noun)
    {
       /**
        * Creates Dictionary object
        */
       Dictionary x = new Dictionary();
       /**
        * Integers to define the range of lines where the nouns are located in words.txt
        */
 	   int min = 0;
 	   int max = 1499;
 	   int range = max - min + 1;
 	   /**
 	    * Picks a random number from the defined range
 	    */
 	   noun = (int)(Math.random() * range) + min;
 	   /**
 	    * Pulls a random noun from the words.txt file
 	    */
 	   String a = x.getWord(noun);
 	   /**
 	    * Cuts the return at the end of the word and returns a random noun
 	    */
 	   String a1 = a.trim();
 	   return a1;
 	}
    /**
	 * The randVerb method pulls a random berb from words.txt file and adds an adequate suffix
	 */
    public static String randVerb(int verb)
    {  
       	/**
        * Creates Dictionary object
        */
       Dictionary x = new Dictionary();
       /**
        * Integers to define the range of lines where the verbs are located in words.txt
        */
 	   int min = 1501;
 	   int max = 2498;
 	   int range = max - min + 1;
 	   /**
 	    * Picks a random number from the defined range
 	    */
 	   verb = (int)(Math.random() * range) + min;
 	   /**
 	    * Pulls a random verb from the words.txt file
 	    */
 	   String b = x.getWord(verb);
 	   String b1 = b.trim();
 	   /**
 	    * Checks the ending of the pulled verb adds a suffix "es" or "s" depending on the ending
 	    */
 	   if(((b1.charAt(b1.length()- 2) == 'c') && (b1.charAt(b1.length() - 1) == 'h')) || ((b1.charAt(b1.length()- 2) == 's') && (b1.charAt(b1.length() - 1) == 's')) || ((b1.charAt(b1.length()- 2) == 's') && (b1.charAt(b1.length() - 1) == 'h')) || (b1.charAt(b1.length() - 1) == 'o') || (b1.charAt(b1.length() - 1) == 'x') || ((b1.charAt(b1.length()- 2) == 'z') && (b1.charAt(b1.length() - 1) == 'z')))
 	   {
 		   b1 = b1 + "es";
 	   }
 	   else
 	   {
 		   b1 = b1 + "s";
 	   }
 	  /**
 	   * Cuts the return at the end of the word and returns a random verb
 	   */
 	   String b2 = b1.trim();
 	   return b2; 	   
    }
    /**
	 * The randAdj method pulls a random adjective from words.txt file
	 */
    public static String randAdj(int adj)
    {
    	/**
         * Creates Dictionary object
         */
       Dictionary x = new Dictionary();
    	
       /**
        * Integers to define the range of lines where the adjectives are located in words.txt
        */
       int min = 2500;
 	   int max = 3499;
 	   /**
 	    * Picks a random number from the defined range
 	    */
 	   int range = max - min + 1;
 	   adj = (int)(Math.random() * range) + min;
 	  /**
 	   * Pulls a random adjective from the words.txt file
 	   */
 	   String c = x.getWord(adj);
 	   /**
  	    * Cuts the return at the end of the word and returns a random adjective
  	    */
 	   String c1 = c.trim();
 	   return c1;
 	}
    /**
	 * The sentence method generates a random sentence in present simple tense using words from words.txt file
	 */
    public static String sentence(String d)
    {
    	/**
    	 * String that consists of article "The", random nouns, verb and an adjective
    	 */
    	d = "The" + " " + randNoun(0) + " " + randVerb(0) + " " + "the" + " " + randAdj(0) + " " + randNoun(0) + ".";
    	/**
    	 * Returns a randomly generated sentence
    	 */
    	return d;
    }
	public static void main(String[] args) {
		/**
		 * Two arrays of strings
		 */
		String[] sentences = new String[20000];
		String[] sentences1 = new String[20000];
		String lol = " ";
		/**
		 * For loop that fills in the arrays with random sentences and their hashes
		 */
		for(int i = 0; i<20000; i++)
		{
			lol = sentence("");
			sentences1[i] = lol;
			String lol1 = sha256(lol);
			sentences[i] = lol1;				
		}
		/**
		 * Nested loop, defines the position of the hash of the first sentence
		 */
		for(int i = 0; i<19999; i++)
		{
			/**
			 * Loop that defines the position of the hash of the second sentence
			 * to avoid comparing the same hashes twice we compare th first hash with hashes on higher positions in the array
			 */
			for(int k = i+1; k<20000; k++)
			{   
				/**
				 * This integer defines score, number of characters that match in the hash of the first and second sentence
				 */
				int num = 0;
				/**
				 * Loop that compares the characters on every position between the hash of the first and the second sentence
				 */
				for(int j = 0; j< 64; j++)
				{
					/**
					 * Compares the hashes
					 */
					if(sentences[i].charAt(j) == sentences[k].charAt(j))
					{
						/**
						 * For each match between the hashes it adds to the score
						 */
						num++;
					}
				}
				/**
				 * Checks if the score is higher or equal to a defined number
				 */
				if(num>= 18)
				{
					/**
					 * Prints out the score, both sentences and both hashes
					 */
					System.out.println(num + " " + sentences1[i] + " " + sentences[i] + " " + sentences1[k] + " " + sentences[k]);
					
				}
			}
	  	}
	}
}


class Dictionary
{
     
    private String input[]; 

    public Dictionary(){
        input = load("C://words.txt");  
    }
    
    public int getSize(){
        return input.length;
    }
    
    public String getWord(int n){
        return input[n];
    }
    
    private String[] load(String file) {
        File aFile = new File(file);     
        StringBuffer contents = new StringBuffer();
        BufferedReader input = null;
        try {
            input = new BufferedReader( new FileReader(aFile) );
            String line = null; 
            int i = 0;
            while (( line = input.readLine()) != null){
                contents.append(line);
                i++;
                contents.append(System.getProperty("line.separator"));
            }
        }catch (FileNotFoundException ex){
            System.out.println("Can't find the file - are you sure the file is in this location: "+file);
            ex.printStackTrace();
        }catch (IOException ex){
            System.out.println("Input output exception while processing file");
            ex.printStackTrace();
        }finally{
            try {
                if (input!= null) {
                    input.close();
                }
            }catch (IOException ex){
                System.out.println("Input output exception while processing file");
                ex.printStackTrace();
            }
        }
        String[] array = contents.toString().split("\n");
        for(String s: array){
            s.trim();
        }
        return array;
    }
    }
