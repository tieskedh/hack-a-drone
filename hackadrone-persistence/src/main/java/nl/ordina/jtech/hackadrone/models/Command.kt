package nl.ordina.jtech.hackadrone.models

import kotlin.properties.Delegates
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Class representing a command model.

 * @author Nils Berlijn
 * *
 * @version 1.0
 * *
 * @since 1.0
 */
class Command (

        pitch: Int = 0,
        /**
         * The yaw number.
         */
        yaw: Int = 0,
        /**
         * The roll number.
         */
        roll: Int = 0,
        /**
         * The throttle number.
         */
        throttle: Int = 0,

        /**
         * The take off status.
         */
        var takeOff: Boolean = false,
        /**
         * The land status.
         */
        var land: Boolean = false
) {
    var pitch by SignedByteRange(pitch)
    var yaw by SignedByteRange(yaw)
    var roll by SignedByteRange(roll)
    var throttle by SignedByteRange(throttle)
}

class SignedByteRange(var value: Int):ReadWriteProperty<Any?, Int>{
    override fun getValue(thisRef: Any?, property: KProperty<*>) = value

    override fun setValue(thisRef: Any?, property: KProperty<*>, setVal: Int) {
        if (setVal < -128) {
            value = -128
        } else if (setVal > 127) {
            value= 127
        }
    }

}