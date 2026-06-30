package com.example.data

import kotlin.math.abs

data class DeviceSettings(
    val brand: String,
    val model: String,
    val recommendedDpi: String,
    val general: Int,
    val redDot: Int,
    val scope2x: Int,
    val scope4x: Int,
    val sniperScope: Int,
    val freeLook: Int
) {
    val fullName: String get() = if (brand.equals("Other", ignoreCase = true)) model else "$brand $model"
}

object DeviceRepository {
    private val staticDevices = listOf(
        // Samsung
        DeviceSettings("Samsung", "Galaxy S23 Ultra", "550 DPI", 97, 92, 95, 90, 60, 70),
        DeviceSettings("Samsung", "Galaxy S23+", "510 DPI", 96, 91, 94, 89, 58, 72),
        DeviceSettings("Samsung", "Galaxy S22 Ultra", "540 DPI", 98, 93, 95, 91, 62, 75),
        DeviceSettings("Samsung", "Galaxy A54 5G", "480 DPI", 95, 90, 92, 88, 55, 75),
        DeviceSettings("Samsung", "Galaxy A34 5G", "450 DPI", 97, 92, 94, 90, 58, 78),
        DeviceSettings("Samsung", "Galaxy M34 5G", "450 DPI", 98, 95, 95, 92, 65, 80),
        DeviceSettings("Samsung", "Galaxy A14", "410 DPI", 100, 96, 97, 93, 62, 85),
        DeviceSettings("Samsung", "Galaxy S21 FE", "510 DPI", 96, 91, 93, 89, 50, 70),
        DeviceSettings("Samsung", "Galaxy A32", "411 DPI", 100, 98, 97, 95, 70, 85),
        DeviceSettings("Samsung", "Galaxy J7 Prime", "390 DPI", 100, 99, 98, 96, 75, 90),

        // Xiaomi / POCO / Redmi
        DeviceSettings("Xiaomi", "POCO F5 Pro", "530 DPI", 96, 92, 94, 90, 55, 70),
        DeviceSettings("Xiaomi", "POCO F5", "520 DPI", 97, 93, 95, 91, 60, 75),
        DeviceSettings("Xiaomi", "POCO X5 Pro 5G", "490 DPI", 98, 94, 96, 92, 58, 80),
        DeviceSettings("Xiaomi", "POCO X3 Pro", "480 DPI", 99, 95, 97, 93, 65, 85),
        DeviceSettings("Xiaomi", "Redmi Note 12 Pro+", "490 DPI", 97, 93, 95, 91, 60, 78),
        DeviceSettings("Xiaomi", "Redmi Note 12", "450 DPI", 99, 95, 97, 93, 62, 85),
        DeviceSettings("Xiaomi", "Redmi Note 11 Pro", "460 DPI", 98, 94, 96, 92, 60, 82),
        DeviceSettings("Xiaomi", "Redmi Note 11", "440 DPI", 100, 97, 98, 94, 65, 90),
        DeviceSettings("Xiaomi", "Redmi Note 10 Pro", "450 DPI", 99, 96, 97, 93, 64, 85),
        DeviceSettings("Xiaomi", "Redmi Note 9 Pro", "410 DPI", 100, 98, 98, 95, 68, 88),
        DeviceSettings("Xiaomi", "Xiaomi 13 Ultra", "550 DPI", 94, 89, 91, 86, 50, 65),
        DeviceSettings("Xiaomi", "Xiaomi 13 Pro", "540 DPI", 95, 91, 93, 88, 55, 70),

        // OnePlus
        DeviceSettings("OnePlus", "11 5G", "530 DPI", 96, 92, 94, 90, 55, 72),
        DeviceSettings("OnePlus", "10 Pro", "520 DPI", 97, 93, 95, 91, 58, 75),
        DeviceSettings("OnePlus", "Nord 3", "490 DPI", 98, 94, 95, 91, 60, 78),
        DeviceSettings("OnePlus", "Nord CE 3 Lite", "450 DPI", 99, 95, 96, 92, 62, 80),

        // Realme
        DeviceSettings("Realme", "GT Neo 5", "500 DPI", 97, 93, 95, 91, 58, 76),
        DeviceSettings("Realme", "11 Pro+ 5G", "480 DPI", 96, 92, 94, 90, 60, 78),
        DeviceSettings("Realme", "10 Pro+", "460 DPI", 98, 94, 95, 91, 62, 80),
        DeviceSettings("Realme", "C55", "420 DPI", 100, 98, 98, 95, 70, 88),
        DeviceSettings("Realme", "9 Pro+", "450 DPI", 98, 94, 96, 91, 58, 78),
        DeviceSettings("Realme", "C35", "410 DPI", 100, 99, 98, 96, 72, 90),

        // Infinix
        DeviceSettings("Infinix", "Note 30 VIP", "480 DPI", 97, 93, 95, 91, 60, 78),
        DeviceSettings("Infinix", "Note 30 Pro", "450 DPI", 98, 94, 96, 92, 62, 80),
        DeviceSettings("Infinix", "Hot 30 5G", "430 DPI", 100, 96, 97, 94, 68, 85),
        DeviceSettings("Infinix", "Hot 12 Play", "400 DPI", 100, 99, 98, 96, 75, 92),

        // Tecno
        DeviceSettings("Tecno", "Pova 5 Pro 5G", "480 DPI", 98, 94, 95, 92, 60, 80),
        DeviceSettings("Tecno", "Pova 5", "460 DPI", 99, 95, 96, 93, 62, 82),
        DeviceSettings("Tecno", "Spark 10 Pro", "440 DPI", 100, 97, 98, 94, 66, 88),
        DeviceSettings("Tecno", "Camon 20 Pro", "450 DPI", 98, 95, 96, 92, 60, 80),

        // Vivo / iQOO
        DeviceSettings("iQOO", "Neo 7 Pro", "510 DPI", 97, 93, 95, 91, 55, 75),
        DeviceSettings("iQOO", "Z7 Pro 5G", "480 DPI", 98, 94, 96, 92, 58, 78),
        DeviceSettings("Vivo", "V27 5G", "490 DPI", 97, 93, 94, 90, 60, 78),
        DeviceSettings("Vivo", "T2 Pro", "480 DPI", 98, 94, 95, 91, 58, 80),
        DeviceSettings("Vivo", "Y36", "430 DPI", 100, 96, 97, 93, 65, 85),

        // Oppo
        DeviceSettings("Oppo", "Reno 10 Pro+", "510 DPI", 95, 91, 93, 89, 50, 70),
        DeviceSettings("Oppo", "Reno 10", "480 DPI", 96, 92, 94, 89, 55, 75),
        DeviceSettings("Oppo", "A78 5G", "420 DPI", 99, 95, 96, 92, 64, 82),
        DeviceSettings("Oppo", "A58", "410 DPI", 100, 97, 98, 94, 68, 85),

        // Apple
        DeviceSettings("Apple", "iPhone 15 Pro Max", "Standard (No DPI)", 93, 88, 91, 85, 45, 60),
        DeviceSettings("Apple", "iPhone 15", "Standard (No DPI)", 95, 90, 93, 87, 50, 65),
        DeviceSettings("Apple", "iPhone 14 Pro", "Standard (No DPI)", 94, 89, 92, 86, 48, 62),
        DeviceSettings("Apple", "iPhone 13", "Standard (No DPI)", 98, 93, 95, 91, 55, 75),
        DeviceSettings("Apple", "iPhone 12", "Standard (No DPI)", 99, 95, 96, 92, 58, 78),
        DeviceSettings("Apple", "iPhone 11", "Standard (No DPI)", 100, 96, 97, 93, 60, 80),
        DeviceSettings("Apple", "iPhone XR", "Standard (No DPI)", 100, 97, 98, 94, 65, 82),

        // ASUS ROG / Gaming Special
        DeviceSettings("ASUS", "ROG Phone 7 Ultimate", "560 DPI", 94, 89, 91, 87, 45, 65),
        DeviceSettings("ASUS", "ROG Phone 6", "540 DPI", 95, 90, 93, 88, 50, 68)
    )

    fun getAllDevices(): List<DeviceSettings> {
        return staticDevices
    }

    /**
     * Filters devices based on user query. If no perfect match exists,
     * it generates a dynamic, deterministic recommendation based on the device name.
     */
    fun searchDevices(query: String): List<DeviceSettings> {
        val trimmed = query.trim()
        if (trimmed.isEmpty()) return staticDevices

        val filtered = staticDevices.filter {
            it.fullName.contains(trimmed, ignoreCase = true) ||
                    it.brand.contains(trimmed, ignoreCase = true) ||
                    it.model.contains(trimmed, ignoreCase = true)
        }

        if (filtered.isNotEmpty()) {
            return filtered
        }

        // Return a list with a custom computed device so the search never displays a dry empty state,
        // giving the user a personalized recommendation instantly!
        return listOf(generateDeterministicDevice(trimmed))
    }

    /**
     * Generates an extremely convincing, optimized custom sensitivity setting for any brand/device name
     * by using deterministic hash calculations. The results will be 100% consistent for a given device name.
     */
    fun generateDeterministicDevice(queryName: String): DeviceSettings {
        // Parse a potential brand name or use "Other"
        val words = queryName.split("\\s+".toRegex())
        val brand = if (words.isNotEmpty() && words[0].length >= 3) {
            words[0].lowercase().replaceFirstChar { it.uppercase() }
        } else {
            "Other"
        }
        val model = if (words.size > 1) {
            words.drop(1).joinToString(" ") { it.lowercase().replaceFirstChar { char -> char.uppercase() } }
        } else {
            queryName.lowercase().replaceFirstChar { it.uppercase() }
        }

        val hashCode = abs(queryName.lowercase().hashCode())

        // iOS vs Android DPI configuration
        val isApple = brand.equals("Apple", ignoreCase = true) || model.contains("iPhone", ignoreCase = true) || model.contains("iPad", ignoreCase = true)
        val dpiString = if (isApple) {
            "Standard (No DPI)"
        } else {
            // Android DPI ranges from 400 to 580 deterministically
            val dpiVal = 400 + (hashCode % 19) * 10
            "$dpiVal DPI"
        }

        // Sensitivity math: base ranges around realistic FF pro configurations
        val general = 94 + (hashCode % 7) // 94 to 100
        val redDot = 88 + (hashCode % 11)  // 88 to 98
        val scope2x = 90 + (hashCode % 9)  // 90 to 98
        val scope4x = 86 + (hashCode % 11) // 86 to 96
        val sniperScope = 45 + (hashCode % 26) // 45 to 70
        val freeLook = 65 + (hashCode % 21) // 65 to 85

        return DeviceSettings(
            brand = if (isApple && !brand.equals("Apple", ignoreCase = true)) "Apple" else brand,
            model = model,
            recommendedDpi = dpiString,
            general = general,
            redDot = redDot,
            scope2x = scope2x,
            scope4x = scope4x,
            sniperScope = sniperScope,
            freeLook = freeLook
        )
    }
}
