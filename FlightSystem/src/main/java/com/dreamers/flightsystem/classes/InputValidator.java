/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamers.flightsystem.classes;

/**
 *
 * @author Orbius
 */
public class InputValidator {

    // String Validation
    public boolean isNull(String text) {
        boolean empty = false;
        // We check is the text is empty or not.
        if (text.isEmpty()) {
            empty = true;
        }
        return empty;

    }
    
    public boolean containsNumber(String text){
        boolean contains = false;
        
        if(text != null && !text.isEmpty()){
            for(char c:text.toCharArray()){
                contains = Character.isDigit(c);
            }
        }
        
        return contains;
    }
    
    // Integer Validation
    public boolean containsCharacters(int number){
         boolean contains = false;
        try{
           contains = false;
        }catch(NumberFormatException ex){
            contains = true;
        }
        
        return contains;
        
    }
    
    // Phone Number format
    public boolean correctNumberFormat(int phone){
        boolean correct = true;
        String phone_string = "";
        
        // String number value so we can avoid complications
        phone_string = String.valueOf(phone);
        // Trim phone number
        phone_string.trim();
        
        // Get phone length and check if length is correct
        if(phone_string.length() > 9 || phone_string.length() < 0){
            correct = false;
        }
        
        return correct;
        
    }

}
