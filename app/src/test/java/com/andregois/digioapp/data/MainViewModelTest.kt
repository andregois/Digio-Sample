package com.andregois.digioapp.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.andregois.digioapp.domain.Cash
import com.andregois.digioapp.domain.DigioResponse
import com.andregois.digioapp.domain.Product
import com.andregois.digioapp.domain.Spotlight
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations


@ExperimentalCoroutinesApi
class MainViewModelTest {


    @get:Rule
    val rule = InstantTaskExecutorRule()
    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `when view model fetchData get success then sets response`() {
        val result = DigioResponse(
            Cash("", "", ""),
            listOf(Product("", "", "")),
            listOf(Spotlight("", "", ""))
        )
        val repository = FakeRepository(result)
        viewModel = MainViewModel(repository)


        viewModel.fetchData()


        viewModel.response.observeOnce() {
            assertEquals(result, it.data)
        }
    }
}

fun <T> LiveData<T>.observeOnce(onChangeHandler: (T) -> Unit) {
    val observer = OneTimeObserver(handler = onChangeHandler)
    observe(observer, observer)
}

class FakeRepository(private val result: DigioResponse) : DigioRepository {
    override suspend fun getApiData(): DigioResponse {
        return result
    }
}
