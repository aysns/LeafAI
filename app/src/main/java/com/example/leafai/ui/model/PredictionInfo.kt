
package com.example.leafai.ui.model

data class PredictionInfo(
    val title: String,
    val latinName: String,
    val confidence: String,
    val description: String,
    val suggestion: String
)

fun mapPrediction(rawText: String): PredictionInfo {

    val label = rawText.substringBefore(" (").trim()
    val confidence = rawText.substringAfter("(", "0%").substringBefore(")")

    return when (label) {

        "daun keriting" -> PredictionInfo(
            title = "Daun Keriting",
            latinName = "Pepper Leaf Curl Virus (PLCV)",
            confidence = confidence,
            description = "Daun keriting merupakan kondisi pada tanaman cabai yang ditandai dengan perubahan bentuk daun menjadi menggulung, mengerut, atau melengkung. Penyakit ini umumnya disebabkan oleh infeksi virus yang ditularkan oleh hama vektor seperti kutu kebul (Bemisia tabaci). Serangan yang berlanjut dapat menghambat pertumbuhan tanaman, mengurangi pembentukan bunga, serta menurunkan produktivitas panen.",
            suggestion =
                "• Pisahkan tanaman yang terinfeksi dari tanaman sehat.\n" +
                        "• Kendalikan kutu kebul sebagai vektor penyebaran virus.\n" +
                        "• Buang daun atau bagian tanaman yang rusak parah.\n" +
                        "• Bersihkan gulma di sekitar area tanam.\n" +
                        "• Lakukan pemeriksaan rutin untuk mencegah penyebaran penyakit."
        )

        "daun sehat" -> PredictionInfo(
            title = "Daun Sehat",
            latinName = "Healthy Leaf",
            confidence = confidence,
            description = "Daun berada dalam kondisi sehat dengan warna hijau yang merata, bentuk daun normal, serta tidak menunjukkan gejala penyakit seperti bercak, keriting, maupun perubahan warna. Kondisi ini menandakan bahwa tanaman memperoleh nutrisi dan lingkungan tumbuh yang baik.",
            suggestion =
                "• Pertahankan pola penyiraman yang teratur.\n" +
                        "• Pastikan tanaman mendapatkan sinar matahari yang cukup.\n" +
                        "• Berikan pupuk sesuai kebutuhan tanaman.\n" +
                        "• Jaga kebersihan area tanam dari gulma dan hama.\n" +
                        "• Lakukan pemeriksaan berkala untuk deteksi dini penyakit."
        )

        "bercak daun" -> PredictionInfo(
            title = "Bercak Daun",
            latinName = "Cercospora capsici",
            confidence = confidence,
            description = "Bercak daun merupakan penyakit yang umumnya disebabkan oleh infeksi jamur. Gejalanya ditandai dengan munculnya bercak berwarna cokelat hingga kehitaman pada permukaan daun. Jika tidak segera ditangani, bercak dapat meluas dan mengurangi kemampuan daun dalam melakukan fotosintesis.",
            suggestion =
                "• Pangkas dan musnahkan daun yang terinfeksi.\n" +
                        "• Kurangi kelembapan berlebih di sekitar tanaman.\n" +
                        "• Atur jarak tanam agar sirkulasi udara lebih baik.\n" +
                        "• Gunakan fungisida sesuai dosis yang dianjurkan.\n" +
                        "• Hindari penyiraman langsung pada permukaan daun."
        )

        "daun menguning" -> PredictionInfo(
            title = "Daun Menguning",
            latinName = "Yellow Leaf Curl Virus (YLCV)",
            confidence = confidence,
            description = "Daun menguning ditandai dengan perubahan warna daun dari hijau menjadi kuning yang sering disertai gejala daun mengecil, menebal, dan pertumbuhan tanaman yang terhambat. Penyakit ini umumnya berkaitan dengan infeksi virus yang ditularkan oleh kutu kebul dan dapat menyebabkan penurunan hasil panen secara signifikan.",
            suggestion =
                "• Periksa keberadaan kutu kebul pada tanaman.\n" +
                        "• Buang daun yang menunjukkan gejala parah.\n" +
                        "• Pastikan tanaman mendapatkan nutrisi yang cukup.\n" +
                        "• Perbaiki sistem drainase untuk menghindari genangan air.\n" +
                        "• Lakukan pengendalian hama secara rutin untuk mencegah penyebaran virus."
        )

        "bukan daun" -> PredictionInfo(
            title = "Bukan Daun",
            latinName = "-",
            confidence = confidence,
            description = "Objek yang terdeteksi bukan termasuk daun cabai.",
            suggestion = "Arahkan kamera ke daun cabai agar sistem dapat melakukan prediksi penyakit."
        )

        else -> PredictionInfo(
            title = "Menunggu Prediksi",
            latinName = "-",
            confidence = "0%",
            description = "Sistem belum mendeteksi daun cabai yang dapat dianalisis. Pastikan objek daun terlihat jelas dan berada di dalam area kamera agar proses identifikasi dapat dilakukan dengan lebih akurat.",
            suggestion =
                "• Arahkan kamera ke daun cabai.\n" +
                        "• Pastikan pencahayaan cukup terang.\n" +
                        "• Hindari gambar yang buram atau terlalu jauh.\n" +
                        "• Letakkan daun di tengah area kamera.\n" +
                        "• Tunggu hingga hasil prediksi muncul."
        )
    }
}