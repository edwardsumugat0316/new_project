package udemy.andriod.newproject.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import udemy.andriod.newproject.repository.service.Repository
import udemy.andriod.newproject.repository.service.model.UsersJsonItem
import udemy.andriod.newproject.repository.service.roomdatabase.APIRequestUser

class MainActivityViewModel(private val repository: Repository): ViewModel() {
//    val userListLiveData : LiveData<List<UsersJsonItem>> = liveData(Dispatchers.IO) {
//        isLoading.postValue(true)
//        emit(repository.getUsers(0))
//    }

    val userListLiveData: MutableLiveData<List<UsersJsonItem>> = MutableLiveData()

    val searchLiveData: MutableLiveData<List<UsersJsonItem>> = MutableLiveData()

//    val isLoading = MutableLiveData<Boolean>()


    fun saveUsers(users: List<UsersJsonItem>) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.insertUsers(users)
            }
        }
    }

    fun loadUsers(since: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val userList = repository.getUsers(since)

                withContext(Dispatchers.Main) {
                    userListLiveData.value = userList
                }
            }
        }
    }
    fun onSearch(search: String){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val searchUsers = repository.searchUserByName(search)

                withContext(Dispatchers.Main){
                    searchLiveData.value = searchUsers
                }
            }

        }
    }

}