package org.example

/**
* Enum class que define un conjunto cerrado y finito de valores constantes.
*
* Una enum class en Kotlin:
* - Define valores predefinidos que no pueden ser modificados en runtime
* - Cada valor es una instancia única de la enum class (patrón Singleton)
* - Proporciona type-safety: solo se pueden usar los valores definidos
* - Incluye métodos útiles como values(), valueOf(), name, ordinal
*
* Ventajas sobre constantes String:
* - Previene errores de escritura ("alta" vs "ALTA")
* - Autocompletado en IDE
* - Refactoring seguro
* - Comparación eficiente por referencia
*/

enum class Prioridad {
    BAJA,
    MEDIA,
    ALTA
}