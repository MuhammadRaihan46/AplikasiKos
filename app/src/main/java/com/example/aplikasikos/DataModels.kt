package com.example.aplikasikos

/**
 * DataModels.kt
 * Tugas PBL Sprint - M5: Menerapkan Data Model Enkapsulasi
 * Aplikasi: Manajemen Kos (berdasarkan Class Diagram sebelumnya)
 *
 * Kelas ini menerapkan enkapsulasi dengan:
 * 1. Atribut private (menggunakan private set)
 * 2. Custom setter dengan logika validasi
 */

// =====================================================
// KELAS 1: PENGHUNI
// Enkapsulasi: private set + fungsi validasi format email
// =====================================================
class Penghuni(
    val idPenghuni: String,
    val nama: String,
    email: String
) {
    // Atribut email hanya bisa diubah lewat fungsi updateEmail()
    var email: String = email
        private set

    init {
        require(isEmailValid(email)) { "Format email tidak valid: $email" }
    }

    fun updateEmail(emailBaru: String) {
        if (!isEmailValid(emailBaru)) {
            throw IllegalArgumentException("Update gagal, format email tidak valid: $emailBaru")
        }
        email = emailBaru
    }

    private fun isEmailValid(value: String): Boolean {
        val emailRegex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
        return emailRegex.matches(value)
    }

    override fun toString(): String {
        return "Penghuni(id='$idPenghuni', nama='$nama', email='$email')"
    }
}

// =====================================================
// KELAS 2: KAMAR
// Enkapsulasi: custom setter dengan validasi stok tidak boleh minus
// =====================================================
class Kamar(
    val nomorKamar: String,
    val tipeKamar: String,
    val hargaSewa: Int,
    stokAwal: Int
) {
    // Custom setter: menolak nilai negatif setiap kali stokTersedia diubah
    var stokTersedia: Int = stokAwal
        set(value) {
            if (value < 0) {
                throw IllegalArgumentException(
                    "Stok kamar tidak boleh minus (nilai yang dimasukkan: $value)"
                )
            }
            field = value
        }

    fun tambahStok(jumlah: Int) {
        stokTersedia += jumlah
    }

    fun kurangiStok(jumlah: Int) {
        stokTersedia -= jumlah
    }

    override fun toString(): String {
        return "Kamar(nomor='$nomorKamar', tipe='$tipeKamar', harga=$hargaSewa, stok=$stokTersedia)"
    }
}

// =====================================================
// CONTOH PENGGUNAAN / PENGUJIAN
// =====================================================
fun main() {
    println("=== Pengujian Penghuni ===")
    val penghuni = Penghuni("P001", "Muhammad", "muhammad@example.com")
    println(penghuni)

    try {
        penghuni.updateEmail("email-tidak-valid")
    } catch (e: IllegalArgumentException) {
        println("Error tertangkap: ${e.message}")
    }

    println("\n=== Pengujian Kamar ===")
    val kamar = Kamar("A-01", "Standar", 750000, 3)
    println(kamar)

    kamar.kurangiStok(2)
    println("Setelah 2 kamar disewa: $kamar")

    try {
        kamar.kurangiStok(5) // akan gagal karena stok jadi minus
    } catch (e: IllegalArgumentException) {
        println("Error tertangkap: ${e.message}")
    }
}
