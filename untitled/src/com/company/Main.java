package com.company;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class Main {

    private static final String BRL_PREFIX = "R$ ";

    public static void main(String[] args) throws ParseException {
	// write your code here

        var batata = "9.5";

        System.out.print(fromBRL(batata));



    }

    public static double fromBRL(String brl) throws ParseException {


        //var value = brl.contains(BRL_PREFIX) ? brl : BRL_PREFIX.concat(brl);

        return NumberFormat
                .getCurrencyInstance(new Locale("pt", "BR"))
                .parse(brl)
                .doubleValue();
    }
}
