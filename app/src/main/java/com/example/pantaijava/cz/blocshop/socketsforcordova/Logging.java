package cz.blocshop.socketsforcordova;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Logging {
    public static void Error(String str, String str2, Throwable th) {
        Logger.getLogger(str).log(Level.SEVERE, str2, th);
    }
}
