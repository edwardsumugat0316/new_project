package udemy.andriod.newproject.userInfo

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import udemy.andriod.newproject.repository.service.roomdatabase.Bio
import udemy.andriod.newproject.repository.service.Repository
import udemy.andriod.newproject.repository.service.model.UsersJsonItem

class UserInfoViewModel (login: String ,private val repository: Repository ): ViewModel(){



    val userInfo: LiveData<UsersJsonItem?> = liveData(Dispatchers.IO) {
        load.postValue(true)
        emit(repository.getUser(login = login ))
    }

    val load = MutableLiveData<Boolean>()

    fun saveUser(user: UsersJsonItem?) {
        user?.let {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    repository.saveUser(it)
                }
            }
        }
    }

     fun getUserFromDb(login : String) : UsersJsonItem? {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
            repository.getUserFormDb(login)
            }
        }
        return repository.getUserFormDb(login)
    }

    fun getBioFromDb(id: Int): Bio? {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                repository.getBio(id)
            }
        }
        return repository.getBio(id)
    }

    fun saveBio(userBio: Bio) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.insertBio(userBio)
            }
        }
    }
}