package com.ficrew.yourbutler.realestates.infrastructure.util;

/*
 * 신용도에 따른 이자율
 */
public class CreditUtils {
    public static final double RATE = 0.01;
    public static final double GTE_900 = 8.69 * RATE; // 900 이상
    public static final double GTE_800 = 9.33 * RATE; // 800 이상
    public static final double GTE_700 = 10.20 * RATE; // 700 이상
    public static final double GTE_600 = 11.21 * RATE; // 600 이상
    public static final double GTE_500 = 12.49 * RATE; // 500 이상
    public static final double GTE_400 = 11.43 * RATE; // 400 이상
    public static final double GTE_300 = 7.7 * RATE; // 300 이상
    public static final double LTE_300 = 9.68 * RATE; // 300 미만
    public static double getInterestRate(int creditScore) {
        if (creditScore >= 900) {
            return GTE_900;
        } else if (creditScore >= 800) {
            return GTE_800;
        } else if (creditScore >= 700) {
            return GTE_700;
        } else if (creditScore >= 600) {
            return GTE_600;
        } else if (creditScore >= 500) {
            return GTE_500;
        } else if (creditScore >= 400) {
            return GTE_400;
        } else if (creditScore >= 300) {
            return GTE_300;
        } else {
            return LTE_300;
        }
    }

}
