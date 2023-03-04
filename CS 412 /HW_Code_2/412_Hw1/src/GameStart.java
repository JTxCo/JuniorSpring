public class GameStart {
    int randNum= (int)((Math.random() * (100)));
    public String guess(int numGuess) {
            if ( numGuess > randNum) {
                return ("too high");
            } else if (numGuess < randNum) {
                return "too low";
            }
            else {
                return "nailed it!";
            }
    }




    void fx( int x )
    {
        x = -999;
    }

    int x = 10;
    fx(x);
System.out.println(x);




}
