package groddler.extensions

import groddler.api.Project


/**
 * A simulated [Project] extension.
 */
class KotlinExtension {
    var isAwesome = true
}


/**
 * Simulates a generated accessor to configure a [Project] extension.
 */
fun Project.kotlin(configure: KotlinExtension.() -> Unit) = Unit
