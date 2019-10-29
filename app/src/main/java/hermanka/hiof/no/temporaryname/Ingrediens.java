package hermanka.hiof.no.temporaryname;

import java.util.ArrayList;

public class Ingrediens {

    protected static ArrayList<Ingrediens> ingredienser = new ArrayList<>();
    private String bildeURL;
    private String ingrediensnavn;

    public Ingrediens(String bildeURL, String ingrediensnavn) {
        this.bildeURL = bildeURL;
        this.ingrediensnavn = ingrediensnavn;
        ingredienser.add(this);
    }

    public static ArrayList<Ingrediens> getIngredienser() {
        return ingredienser;
    }

    public static void setIngredienser(ArrayList<Ingrediens> ingredienser) {
        Ingrediens.ingredienser = ingredienser;
    }

    public static void main(String[] args) {
        //Ingrediens ing = new Ingrediens("url", "navn");
        //System.out.println(ing);
        //Ingrediens ing2 = new Ingrediens("url2", "navn2");
        //System.out.println(ing2);
    }

    public String getBildeURL() {
        return this.bildeURL;
    }

    public void setBildeURL(String bildeURL) {
        this.bildeURL = bildeURL;
    }

    public String getIngrediensnavn() {
        return this.ingrediensnavn;
    }

    public void setIngrediensnavn(String ingrediensnavn) {
        this.ingrediensnavn = ingrediensnavn;
    }

    public String toString() {
        return "Ingrediensnavn: " + this.ingrediensnavn + ", BildeURL: " + this.bildeURL;
    }
}
