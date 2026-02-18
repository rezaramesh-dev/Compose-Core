package com.onestackdev.core.common.base.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<S : UiState, E : UiEffect>(initialState: S) : ViewModel() {

    // ---------------- State ----------------
    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<S> = _state.asStateFlow()

    protected fun setState(reducer: S.() -> S) {
        _state.update { it.reducer() }
    }

    protected fun currentState(): S = _state.value

    // ---------------- Effect (One-time) ----------------
    private val _effect = MutableSharedFlow<E>()
    val effect: SharedFlow<E> = _effect.asSharedFlow()

    protected fun sendEffect(effect: E) {
        viewModelScope.launch {
            _effect.emit(effect)
        }
    }

    // ---------------- Coroutine Helper ----------------
    protected fun launch(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        onError: (Throwable) -> Unit = { handleError(it) },
        block: suspend CoroutineScope.() -> Unit,
    ) {
        viewModelScope.launch(dispatcher) {
            try {
                block()
            } catch (t: Throwable) {
                onError(t)
            }
        }
    }

    protected open fun handleError(t: Throwable) {
        t.printStackTrace()
    }
}

//USE

/*@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository
) : BaseViewModel<LoginState, LoginEffect>(initialState = LoginState()) {

    fun login(username: String, password: String) {

        // Coroutine-safe launch with centralized error handling
        launch {
            setState { copy(isLoading = true) }

            try {
                val result = repository.login(username, password)

                setState { copy(isLoading = false) }

                if (result.success) {
                    sendEffect(LoginEffect.NavigateHome)
                } else {
                    sendEffect(LoginEffect.ShowError("Invalid credentials"))
                }

            } catch (t: Throwable) {
                setState { copy(isLoading = false, error = t.message) }
                sendEffect(LoginEffect.ShowError(t.message ?: "Unknown error"))
            }
        }
    }
}*/
