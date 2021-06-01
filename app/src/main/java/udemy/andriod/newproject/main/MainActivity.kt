package udemy.andriod.newproject.main

import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.userslist_activity.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.viewmodel.ext.android.viewModel
import udemy.andriod.newproject.R
import udemy.andriod.newproject.base.BaseActivity
import udemy.andriod.newproject.repository.service.model.UsersJsonItem
import udemy.andriod.newproject.repository.service.roomdatabase.AppDatabase


const val TAG = "MainActivity"
class MainActivity : BaseActivity(){
    private var itemList = mutableListOf<UsersJsonItem>()

    private val viewModel:MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.userslist_activity)


        makeAPIRequest()


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if(query.isNotEmpty()) {
                }else{
                    Toast.makeText(this@MainActivity, "No match found", Toast.LENGTH_LONG).show()
                }
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {

                return false
            }
        })

    }

    private fun makeAPIRequest() {
        viewModel.userListLiveData.observe(this) {
            itemList.addAll(it)

            viewModel.saveUsers(itemList)

            lifecycleScope.launch {
                withContext(Dispatchers.Main) {
                    setUpRecyclerView()
                }
            }
        }
        viewModel.loadUsers(0)
    }

    private fun setUpRecyclerView() {
        rv_recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        rv_recyclerView.adapter = RecyclerAdapter(itemList, this)

        rv_recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!recyclerView.canScrollVertically(1)) {
                  val lastItemId=  itemList.last().id
                    viewModel.loadUsers(lastItemId)
                    Toast.makeText(this@MainActivity, "Please Wait", Toast.LENGTH_LONG).show()

                }
            }
        })

    }
}