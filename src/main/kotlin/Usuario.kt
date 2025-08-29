package org.example

/**
 * Las data class se emplean como contenedores de datos simples, a menudo inmutables.
 * Las clases regulares son más adecuadas para escenarios complejos que requieren comportamiento y lógica más detallada
 *
 * @property id | Identificador único de la tarea (inmutable)
 * @property nombre | Título descriptivo de la tarea (inmutable)
 * @property email | Descripción detallada de la tarea (inmutable)
 */

data class Usuario(
    val id: Int,
    val nombre: String,
    val email: String
){
    init {
        // Validación del email
        require(email.isNotBlank()) { "El email no puede estar vacío" }
        require(isValidEmail(email)) { "El formato del email no es válido: $email" }
    }

    /**
     *  Valida si el email contiene "@" y ".com"
     *  @param email El string a validar
     *  @return true si contiene @ y .com, false en caso contrario
     */
    private fun isValidEmail(email: String): Boolean {
        return email.contains("@") && email.contains(".com")
    }

    override fun toString(): String {
        return "[$id] $nombre -> $email"
    }
}