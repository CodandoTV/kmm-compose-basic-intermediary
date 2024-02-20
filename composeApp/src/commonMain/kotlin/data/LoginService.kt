package data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.Url

interface LoginService {
    suspend fun postLogin(loginRequest : LoginRequest) : Unit
}

class LoginServiceImpl(
    private val httpClient : HttpClient = KtorApiClient.httpClient
) : LoginService {


    override suspend fun postLogin(loginRequest: LoginRequest): Unit =
        httpClient.post(urlString = "loginKmpCompose") {
            setBody(loginRequest)
        }.body()
}