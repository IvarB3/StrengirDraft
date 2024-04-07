package vinnsla;

public class Strengir {

    private String texti = "";

    public Strengir(){

    }


    /**
     * Vistar textann
     * @param texti
     */
    public void setTexti(String texti){
        this.texti=texti;
    }

    /**
     * Til að leita að sub-texta í texta
     *
     * @param leitarord er strengurinn sem skal leita að
     * @return -1 ef hann finnst ekki, annars indexinum á fyrsta staðnum þar sem orðið byrjar. 0 ef leitað er að tóma streng.
     * @throws NullPointerException ef parameterinn er null
     */
    public int leita(String leitarord) throws NullPointerException{
        return texti.indexOf(leitarord);
    }


    /**
     * Telur hversu oft orð kemur fyrir
     * @param ord
     * @return
     * @throws NullPointerException
     */
    public int fjoldiOrda(String ord) throws NullPointerException{
        if (ord.isEmpty())
            return 0;

        String withoutIt = texti.replace(ord, "");
        return (texti.length()-withoutIt.length())/ord.length();
    }



}
