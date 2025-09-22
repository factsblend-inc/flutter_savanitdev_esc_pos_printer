package com.savanitdev.printer.flutter_savanitdev_printer.utils;

public class StatusPrinter {

    // status printing detail
    public static final String STS_NORMAL = "The printer is normal";
    public static final String STS_COVEROPEN = "Cover open";
    public static final String STS_PAPEREMPTY = "Printer lack of paper";
    public static final String STS_PRESS_FEED = "Press the paper feed button";
    public static final String STS_PRINTER_ERR = "printer error";
    public static final String RETRY_FAILED3 = "Failed to connect after 3 retries";
    public static final String GET_ID_FAIL_DETAIL = "can't find id printer";

    public static final String PRINTER_DISCONNECT = "Printer disconnect";
    public static final String PRINT_FAIL = "PRINT_FAIL";

    // status connection
    public static final String CONNECTED = "CONNECTED";
    public static final String DISCONNECT = "DISCONNECT";
    public static final String DISCONNECT_FAIL = "DISCONNECT_FAIL";
    public static final String CONNECT_ERROR = "CONNECT_ERROR";
    public static final String RETRY_FAILED = "RETRY_FAILED";
    public static final String ERROR = "ERROR";
    public static final String GET_ID_FAIL = "GET_ID_FAIL";

    // Integer status codes for printCommand return values
    public static final int STATUS_SUCCESS = 0;                    // Print successful
    public static final int STATUS_COVER_OPEN = 8;                 // Cover open
    public static final int STATUS_PAPER_EMPTY = 16;               // Paper empty
    public static final int STATUS_PRESS_FEED = 32;                // Press feed button
    public static final int STATUS_PRINTER_ERROR = 64;             // Printer error
    public static final int STATUS_CUTTER_ERROR = 128;             // Cutter error
    public static final int STATUS_RECOVERABLE_ERROR = 256;        // Recoverable error
    public static final int STATUS_CONNECTION_ERROR = -1;          // Connection error
    public static final int STATUS_PRINT_FAILED = -2;              // Print operation failed
    public static final int STATUS_COMMUNICATION_ERROR = -3;       // Communication error
    public static final int STATUS_PRINTER_OFFLINE = -4;           // Printer offline
    public static final int STATUS_DRAWER_OPEN = -5;               // Drawer open
    public static final int STATUS_PAPER_FEED_ERROR = -6;          // Paper feed error
    public static final int STATUS_TIMEOUT = -99;                  // Timeout error
    public static final int STATUS_UNKNOWN_ERROR = -100;           // Unknown error
}
