package udemy.andriod.newproject.main

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.user_layout.*
import kotlinx.android.synthetic.main.userslist_activity.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.viewmodel.ext.android.viewModel
import udemy.andriod.newproject.R
import udemy.andriod.newproject.base.BaseActivity
import udemy.andriod.newproject.repository.service.model.UsersJsonItem


const val TAG = "MainActivity"
class MainActivity : BaseActivity(){
    private var itemList = mutableListOf<UsersJsonItem>()
    var isLoading  = false
    private val viewModel:MainActivityViewModel by viewModel()
    private val myAdapter:RecyclerAdapter by lazy { RecyclerAdapter(itemList,this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.userslist_activity)


        makeAPIRequest()
        setUpRecyclerView()
        onSearch()
    }

    private fun makeAPIRequest() {
        viewModel.userListLiveData.observe(this) {
            itemList.addAll(it)
            isLoading = false
            viewModel.saveUsers(itemList)

            lifecycleScope.launch {
                withContext(Dispatchers.Main) {
                    myAdapter.setItem(itemList)
                }
            }
        }
        viewModel.loadUsers(0)
    }

    private fun setUpRecyclerView() {
        rv_recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        rv_recyclerView.adapter = myAdapter

        rv_recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!recyclerView.canScrollVertically(1)) {
                    val lastItemId=  itemList.last().id
                    if(!isLoading){
                        viewModel.loadUsers(lastItemId)
                        isLoading = true
                        Log.d(TAG, "LALA")
                    }
                    Toast.makeText(this@MainActivity, "Please Wait", Toast.LENGTH_LONG).show()
                }
            }
        })

    }

    private fun onSearch(){
        search_view.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.onSearch(newText)
                return true
            }
        })
    }

}

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.main_menu,menu)
//        val search = menu?.findItem(R.id.search_menu)
//
//        if (search != null){
//            val searchView = search.actionView as SearchView
//            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
//                override fun onQueryTextSubmit(query: String?): Boolean {
//                    return true
//                }
//
//                override fun onQueryTextChange(newText: String): Boolean {
//                    viewModel.onSearch(newText)
//                   return true
//                }
//            } )
//        }
//        return true
//    }
