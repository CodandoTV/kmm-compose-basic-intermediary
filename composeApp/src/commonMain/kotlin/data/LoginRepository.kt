package data

interface LoginRepository {
    suspend fun postLogin(email: String, password: String): Unit
}

class LoginRepositoryImpl(
    private val service: LoginService = LoginServiceImpl()
) : LoginRepository {

    override suspend fun postLogin(email: String, password: String) =
        service.postLogin(
            LoginRequest(
                email = email,
                password = password
            )
        )
}