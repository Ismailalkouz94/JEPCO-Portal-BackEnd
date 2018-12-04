package com.lg.JepcoCsPortal.utils;

public class GlobalProvider {
    private static GlobalProvider instance;

    public static GlobalProvider getInstance() {

        if (instance == null)
            instance = new GlobalProvider();

        return instance;
    }

    public static String formatNumber(String mobNum)
    {

        if (!mobNum.startsWith("+962")) {
            if (mobNum.startsWith("962")) {
                mobNum = "+" + mobNum;
            } else {
                if (mobNum.startsWith("0")) {
                    mobNum = mobNum.replaceFirst("0", "+962");
                } else {
                    mobNum = "+962" + mobNum;
                }
            }
        }

        return mobNum;
    }

}
