package com.example.pepets_spa.viewmodel

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    // Usuario actual en sesión
    private val _currentUser = MutableStateFlow<Usuario?>(null)
    val currentUser: StateFlow<Usuario?> = _currentUser

    // Función para login
    fun login(email: String, password: String): Boolean {
        val user = userRepository.getUserByEmail(email)
        return if (user != null && user.password == password) {
            _currentUser.value = user
            true
        } else false
    }

    // Función para registro
    fun register(user: Usuario) {
        userRepository.saveUser(user)
        _currentUser.value = user
    }
}
