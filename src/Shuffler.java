/**
 * This program shows an example of how to generate a list
 * of size n, containing the values 0 to (n - 1), with
 * each value occurring exactly once and in a random order.
 * 
 * @author mwallace
 * @version 1.0
 */
public final class Shuffler
{
  /**
   * Default constructor.
   */
  private Shuffler()
  {
    super();
  }
  
  
  /**
   * Returns a number between nMinValue and nMaxValue, inclusive.
   * 
   * @param nMinValue Minimum value of returned number
   * @param nMaxValue Maximum value of returned number
   * @return a number between nMinValue and nMaxValue, inclusive
   */
  private static int getRandom(final int nMinValue,
                               final int nMaxValue)
  {
    if (nMaxValue <= nMinValue)
    {
      return nMinValue;
    }
    
    double d = Math.random();
    double r = (double) (nMaxValue + 1 - nMinValue);
    
    int i = nMinValue + ((int) (d * r));
    
    return i;
  }
  
  
  /**
   * Returns a number between 0 and nMaxValue, inclusive.
   * 
   * @param nMaxValue Maximum value of returned number
   * @return a number between 0 and nMaxValue, inclusive
   */
  private static int getRandom(final int nMaxValue)
  {
    return getRandom(0, nMaxValue);
  }
  
  
  /**
   * For the value nSize, generate a list of integers (with
   * nSize elements), with the values between zero (inclusive)
   * and nSize (exclusive) sorted in a random order.
   * 
   * @param nSize the number of elements to shuffle
   * @return an array of integers (with nSize elements)
   */
  public static int[] randomize(final int nSize)
  {
    // Check the input
    if (nSize <= 0)
    {
      return null;
    }
    
    // Declare the array we return at the end
    int[] aiData = new int[nSize];
    
    // Declare an array to remember which numbers we've
    // already generated.  Initialize it with false.
    boolean[] aiMarkers = new boolean[nSize];
    for (int i = 0; i < nSize; ++i)
    {
      aiMarkers[i] = false;
    }
    
    // Generate nSize random numbers
    int nCurrMax = nSize - 1;
    for (int i = 0; i < nSize; ++i)
    {
      int nRandom = getRandom(nCurrMax);
      if (i == 0)
      {
        // This is the first random number, so we don't
        // need to check for previous random numbers
        aiData[0] = nRandom;
        aiMarkers[nRandom] = true;
      }
      else
      {
        // We've already generated at least one random
        // number, so count those to figure where nRandom
        // should land (i.e., if nRandom is 3, then it
        // is converted to the 3rd empty spot in our list
        boolean bFound = false;
        int k = 0;
        int m = 0;
        for (int j = 0; (!bFound) && (j < nSize); ++j)
        {
          // This spot is empty
          if (!aiMarkers[j])
          {
            // Check if this is the kth empty slot
            if (k == nRandom)
            {
              // It is, so store the value of j and set
              // our flag to true, so we stop processing
              m = j;
              bFound = true;
            }
            else
            {
              // We have not yet found the kth empty slot,
              // so increment our counter
              ++k;
            }
          }
        }
        
        // Update the two arrays with the correct values
        aiData[i] = m;
        aiMarkers[m] = true;
      }
      
      // Decrement the max value for the random number
      --nCurrMax;
    }
    
    // Set our boolean array to null
    aiMarkers = null;
    
    // Return the array
    return aiData;
  }
  
  
  /**
   * Tests the getRandom() method.
   */
  private static void testRandomize()
  {
    // Get the current time
    long lStart = System.currentTimeMillis();
    
    // Execute the call
    int[] data = randomize(30000);
    // Times (in ms): 10K=550, 20K=2200, 30K=5000, 50K=9800, 75K=21600
    
    // Get the amount of elapsed time, and print it out
    long lDiff = System.currentTimeMillis() - lStart;
    System.out.println("Required " + lDiff + " milliseconds to shuffle " +
        Integer.toString(data.length) + " elements");
    
    // Set our array to null
    data = null;
  }
  
  
  /**
   * Main entry point for the application.
   * 
   * @param args parameters passed by the user
   */
  public static void main(final String[] args)
  {
    for (int i = 0; i < 3; ++i)
    {
      testRandomize();
    }
  }
}
