package com.example.okello_ellywellnesstracker.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.mindrot.jbcrypt.BCrypt

class UserRepository(private val userDao: UserDao) {

    suspend fun signUp(username: String, password: String): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            val existing = userDao.getUserByUsername(username)
            if (existing != null) {
                return@withContext Result.failure(Exception("Username already exists"))
            }
            val hashed = BCrypt.hashpw(password, BCrypt.gensalt())
            val user = User(username = username, passwordHash = hashed)
            userDao.insertUser(user)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun login(username: String, password: String): Result<String> = withContext(Dispatchers.IO) {
        try {
            val user = userDao.getUserByUsername(username)
            if (user == null || !BCrypt.checkpw(password, user.passwordHash)) {
                // Same generic error message for both user not found and wrong password
                return@withContext Result.failure(Exception("Invalid username or password"))
            }
            Result.success(user.username)
        } catch (e: Exception) {
            Result.failure(Exception("Invalid username or password"))
        }
    }
}
