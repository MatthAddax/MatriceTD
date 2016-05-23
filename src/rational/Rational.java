/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rational;

/**
 *
 * @author Matthieu
 */
public class Rational{
    private int numerator, denominator;
    
    public Rational(int numerator) throws DenominatorException{
        this(numerator, 1);
    }
    public Rational(int numerator, int denominator) throws DenominatorException{
        this.numerator = numerator;
        setDenominator(denominator);
        simplify();
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }
    
    private void setDenominator(int denominator) throws DenominatorException{
        if(denominator == 0){
            throw new DenominatorException();
        }
    }

   
    public Rational add(Rational rational) throws DenominatorException{
        int additionNumerator = (numerator * rational.getDenominator()) + (rational.getNumerator() * denominator);
        int additionDenominator = denominator * rational.getDenominator();
        return new Rational(additionNumerator, additionDenominator);
    }
    
    public Rational subtract(Rational rational) throws DenominatorException{
        
        int subtractionNumerator = (numerator * rational.getDenominator()) - (rational.getNumerator() * denominator);
        int subtractionDenominator = denominator * rational.getDenominator();
        return new Rational(subtractionNumerator, subtractionDenominator);
    }
    
    public Rational multiplication(Rational rational) throws DenominatorException{
        return new Rational(numerator * rational.getNumerator(), denominator * rational.getDenominator());
    }
    
    public Rational division(Rational rational) throws DenominatorException{
        Rational inverted = new Rational(denominator, numerator);
        return this.multiplication(inverted);
    }
    
    public void opose(){
        numerator*=-1;
        simplify();
    }
    
    public void invert(){
        int t = numerator;
        numerator = denominator;
        denominator = numerator;
        simplify();
    }
    
    public void simplify() 
    {
        if(numerator == 0)
        {
            denominator = 1;
        }else{
            if (denominator < 0)
            {
                numerator = - numerator;
                denominator = - denominator;
            }
            int numeratorAbsoluteValue = Math.abs(numerator);
            int temporaryDenominator = denominator;
            while(numeratorAbsoluteValue != temporaryDenominator)
            {
                if(numeratorAbsoluteValue < temporaryDenominator)
                {
                    int temp = numeratorAbsoluteValue;
                    numeratorAbsoluteValue = temporaryDenominator;
                    temporaryDenominator = temp;
                }
                numeratorAbsoluteValue = numeratorAbsoluteValue - temporaryDenominator;
            }
            if(numeratorAbsoluteValue == temporaryDenominator)
            {
                this.numerator = this.numerator / numeratorAbsoluteValue;
                this.denominator = this.denominator / numeratorAbsoluteValue;
            }
        }

            
    }
    
    
    @Override
    public String toString() {
        return numerator + ((denominator==1)?"\\" + denominator:"");
    }
    
    @Override
    public boolean equals(Object other){
        if(other instanceof Rational){
            Rational otherRational = (Rational)other;
            return (otherRational.getDenominator() == denominator 
                    && otherRational.getNumerator() == numerator);
        }
        else{
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.numerator;
        hash = 13 * hash + this.denominator;
        return hash;
    }
}
