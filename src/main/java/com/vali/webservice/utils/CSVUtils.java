package com.vali.webservice.utils;

/**
 * Utility class which cannot be extended or instantiated.
 * This class contains the properties used within this application.
 */
final public class CSVUtils {

    /**
     * property defined in the application.properties which holds the full path to the CSV file.
     */
    public static final String CURRENCIES_FULL_PATH = "currencies.full.path";

    /**
     * Error messages.
     */
    public static final String INVALID_CURRENCY_ERROR = "Unsupported currency provided.";
    public static final String INVALID_CONFIGURATION_ERROR = "Bad CSV path configuration or property name.";

    /**
     * precision required for comparing of 2 double values is set to below value.
     */
    public static final double DELTA = 0.0001;

    /**
     * private constructor so the class isn't instantiated.
     * for code coverage purposes no exception is throw in this constructor.
     */
    private CSVUtils() {}
}
