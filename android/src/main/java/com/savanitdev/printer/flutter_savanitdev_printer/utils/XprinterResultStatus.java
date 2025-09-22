package com.savanitdev.printer.flutter_savanitdev_printer.utils;

import android.os.Build;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import io.flutter.plugin.common.MethodChannel;

public class XprinterResultStatus {

    // Add a class-level map to track which results have been replied to
    private final Map<MethodChannel.Result, Boolean> resultReplied = new HashMap<>();
    private boolean lastResult = false;

    private synchronized boolean hasReplied(MethodChannel.Result result) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Boolean.TRUE.equals(resultReplied.getOrDefault(result, false));
        }
        return false;
    }

    private synchronized void markReplied(MethodChannel.Result result) {
        resultReplied.put(result, true);
    }

    /**
     * Set result with boolean value (for backward compatibility)
     */
    public void setResult(MethodChannel.Result result, boolean status) {
        if (!hasReplied(result)) {
            markReplied(result);
            result.success(status);
            lastResult = status;
        } else {
            Log.w("XprinterResultStatus", "Attempted to reply to an already replied result with success");
        }
    }

    /**
     * Set result with any object value
     */
    public void setResultMethod(MethodChannel.Result result, Object value) {
        if (!hasReplied(result)) {
            markReplied(result);
            result.success(value);
        } else {
            Log.w("XprinterResultStatus", "Attempted to reply to an already replied result with success");
        }
    }

    /**
     * Set result with integer status code (for printCommand)
     */
    public void setResultWithStatusCode(MethodChannel.Result result, int statusCode) {
        if (!hasReplied(result)) {
            markReplied(result);
            result.success(statusCode);
            lastResult = (statusCode == StatusPrinter.STATUS_SUCCESS);
        } else {
            Log.w("XprinterResultStatus", "Attempted to reply to an already replied result with status code");
        }
    }

    /**
     * Set error result with string message
     */
    public void setResultErrorMethod(MethodChannel.Result result, String value) {
        if (!hasReplied(result)) {
            markReplied(result);
            result.error(StatusPrinter.ERROR, "call error", value);
            lastResult = false;
        } else {
            Log.w("XprinterResultStatus", "Attempted to reply to an already replied result with error");
        }
    }

    /**
     * Set error result with status code (returns error as status code)
     */
    public void setResultErrorWithStatusCode(MethodChannel.Result result, int errorStatusCode) {
        if (!hasReplied(result)) {
            markReplied(result);
            result.success(errorStatusCode); // Return error as status code, not as error
            lastResult = false;
        } else {
            Log.w("XprinterResultStatus", "Attempted to reply to an already replied result with error status code");
        }
    }

    /**
     * Get the last result status
     */
    public boolean isResult() {
        return lastResult;
    }

    /**
     * Helper method to map printer status to status code
     */
    public int mapPrinterStatusToCode(int printerStatus) {
        switch (printerStatus) {
            case 0:
            case -3:
            case -4:
                return StatusPrinter.STATUS_SUCCESS;
            case 1:
                return StatusPrinter.STATUS_DRAWER_OPEN;
            case 2:
                return StatusPrinter.STATUS_PRINTER_OFFLINE;
            case 4:
                return StatusPrinter.STATUS_PAPER_FEED_ERROR;
            case 8:
                return StatusPrinter.STATUS_COVER_OPEN;
            case 16:
                return StatusPrinter.STATUS_PAPER_EMPTY;
            case 32:
                return StatusPrinter.STATUS_PRESS_FEED;
            case 64:
                return StatusPrinter.STATUS_PRINTER_ERROR;
            case 128:
                return StatusPrinter.STATUS_CUTTER_ERROR;
            case 256:
                return StatusPrinter.STATUS_RECOVERABLE_ERROR;
            case -65:
                return StatusPrinter.STATUS_CONNECTION_ERROR;
            default:
                if (printerStatus < 0) {
                    return StatusPrinter.STATUS_COMMUNICATION_ERROR;
                } else if (printerStatus > 0) {
                    // Unknown positive status - treat as success for compatibility
                    return StatusPrinter.STATUS_SUCCESS;
                } else {
                    return StatusPrinter.STATUS_SUCCESS;
                }
        }
    }

    /**
     * Set result based on printer status with automatic mapping
     */
    public void setResultFromPrinterStatus(MethodChannel.Result result, int printerStatus) {
        int statusCode = mapPrinterStatusToCode(printerStatus);
        setResultWithStatusCode(result, statusCode);
    }
}
