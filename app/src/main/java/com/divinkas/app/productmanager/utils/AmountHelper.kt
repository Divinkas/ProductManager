package com.divinkas.app.productmanager.utils

import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

object AmountHelper {
    const val FORMAT_WITH_DOT_AND_ZEROS = "#,###,###,##0.00"

    fun getFormattedAmount(amount: String, format: String = FORMAT_WITH_DOT_AND_ZEROS): String {
        val otherSymbols = DecimalFormatSymbols.getInstance(Locale.GERMAN)
        otherSymbols.groupingSeparator = ','
        otherSymbols.decimalSeparator = '.'
        val df = DecimalFormat(format, otherSymbols)

        val amountWithoutSpaceSymbols = amount.replace("\\s".toRegex(), "")
        val amountWithDot = hackForManyDots(amountWithoutSpaceSymbols.replace(',', '.'))
        val doubleValue = java.lang.Double.valueOf(amountWithDot)

        return df.format(BigDecimal(doubleValue)).toString().replace(',', ' ')
    }

    fun getBigDecimalValue(amount: String): BigDecimal {
        val amountWithoutSpaceSymbols = amount.replace("\\s".toRegex(), "")
        val amountWithDot = hackForManyDots(amountWithoutSpaceSymbols.replace(',', '.'))
        val doubleValue = java.lang.Double.valueOf(amountWithDot)
        return BigDecimal(doubleValue)
    }

    /**
     * This hack need for case that amount by some devices has wrong format (example: '40.000.00')
     * and this method removed all fist dots
     */
    private fun hackForManyDots(amount: String): String {
        if (amount.count { it == '.' } > 1)
            return hackForManyDots(amount.replaceFirst(".", ""))
        return amount
    }
}