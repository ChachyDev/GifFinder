import java.io.File
import java.util.*

/**
 * GIF Magic.
 *
 * According to https://en.wikipedia.org/wiki/GIF
 *
 * ╔══════╦═══════════════════╦═══════════════╦═════════╗
 * ║ Byte ║ Hexadecimal       ║ Text or Value ║ Meaning ║
 * ╠══════╬═══════════════════╬═══════════════╬═════════╣
 * ║ 0    ║ 47 49 46 38 39 61 ║ GIF89a        ║ Header  ║
 * ╚══════╩═══════════════════╩═══════════════╩═════════╝
 */

val GIF_MAGIC = intArrayOf(0x47, 0x49, 0x46, 0x38, 0x39, 0x61)

fun main(args: Array<String>) {
    if (args.isNotEmpty()) {
        args.map { File(it) }.forEach {
            if (it.isFormat(GIF_MAGIC)) {
                println("${it.name} is a gif!")
            }
        }
    } else {
        val scanner = Scanner(System.`in`)
        println("Please enter the path to the file(s)")
        val input = scanner.nextLine().split(", ")
        input.map { File(it) }.forEach {
            if (it.isFormat(GIF_MAGIC)) {
                println("${it.name} is a gif!")
            }
        }
    }
}

private fun File.isFormat(magic: IntArray): Boolean {
    return inputStream().use {
        for (element in magic) {
            if (it.read() != element) {
                return false
            }
        }
        true
    }
}