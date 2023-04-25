package com.example.playtipus.presentation.signin

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import com.example.playtipus.R
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException

class GoogleAuthUI (
    private val context: Context,
    private val oneTapClient: SignInClient
){
    private val auth = Firebase.auth

    suspend fun signIn(): IntentSender? {
        val result = try{
            oneTapClient.beginSignIn(
                buildSignInRequest()
            ).await()
        } catch (e: Exception){
            e.printStackTrace()
            if (e is CancellationException) throw e
            null
        }
        return result?.pendingIntent?.intentSender
    }

    suspend fun singInWithIntent(intent: Intent): SignInResult{
    val credential = oneTapClient.getSignInCredentialFromIntent(intent)
    val googleIdToken = credential.googleIdToken
        val gooleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
        return try{
            val user = auth.signInWithCredential(gooleCredentials).await().user
            SignInResult(
                data = user?.run{
                    UserData(
                        userId = uid,
                        username = displayName,
                        profilePictureUrl = photoUrl?.toString()
                    )
                },
                errorMessage = null
            )
        }catch(e: Exception){
            e.printStackTrace()
            if (e is CancellationException) throw e
            SignInResult(
                data = null,
                errorMessage = e.message)
        }

    }

    suspend fun signOut(){
        try {
            oneTapClient.signOut().await()
            auth.signOut()
        } catch (e: Exception){
            e.printStackTrace()
            if (e is CancellationException) throw e
        }
    }

    fun getSignedInUser(): UserData? = auth.currentUser?.run{
        UserData(
            userId = uid,
            username = displayName,
            profilePictureUrl = photoUrl?.toString()
        )
    }

    private fun buildSignInRequest(): BeginSignInRequest{
        return BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
            GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                .setServerClientId(context.getString(R.string.web_client_id))
                .setFilterByAuthorizedAccounts(false)
                .build()
            )
            .setAutoSelectEnabled(true)
            .build()

    }

}