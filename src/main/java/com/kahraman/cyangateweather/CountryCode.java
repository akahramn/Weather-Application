package com.kahraman.cyangateweather;

/**
 * The CountryCode class represent the ISO country codes are
 * internationally recognized codes that designate every country
 * and most of the dependent areas a two-letter combination or a
 * three-letter combination
 *
 * @author Abdullah Kahraman
 */
public enum CountryCode {
    TR("TURKEY");

    private String value;

    CountryCode(String value) {
        this.value = value;
    }

    /**
     *Returns the countries original name from the ISO country codes.
      * @return Countries original name.
     */
    public String getValue() {
        return value;
    }
}
