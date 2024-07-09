package demo

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

@Suppress("Unused")
interface ISerializableUtil {

    fun Any.dump(): ByteArray {
        return ByteArrayOutputStream().use { bo ->
            ObjectOutputStream(bo).use { oo ->
                oo.writeObject(this)
            }
            bo.toByteArray()
        }
    }

    fun ByteArray.load(): Any {
        return ByteArrayInputStream(this).use { bi ->
            ObjectInputStream(bi).use { oi ->
                oi.readObject()
            }
        }
    }

}

