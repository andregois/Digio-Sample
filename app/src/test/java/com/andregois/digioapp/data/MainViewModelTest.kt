package com.andregois.digioapp.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.andregois.digioapp.domain.Cash
import com.andregois.digioapp.domain.DigioResponse
import com.andregois.digioapp.domain.Product
import com.andregois.digioapp.domain.Spotlight
import com.andregois.digioapp.retrofit.DigioApi
import com.andregois.digioapp.utils.ApiResult
import com.andregois.digioapp.utils.Status
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import net.bytebuddy.implementation.bytecode.Throw
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations
import java.io.IOException
import java.lang.Exception


@ExperimentalCoroutinesApi
class MainViewModelTest {


    @get:Rule
    val rule = InstantTaskExecutorRule()
    private val testDispatcher = TestCoroutineDispatcher()

    lateinit var result: DigioResponse


    lateinit var digioDataSource: DigioDataSource
    lateinit var digioRepository: DigioRepository

    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() = runBlocking {
//        digioApi = mock()
        result = DigioResponse(
            Cash("https://s3-sa-east-1.amazonaws.com/digio-exame/cash_banner.png", "Dinheiro na conta sem complicação. Transfira parte do limite do seu cartão para sua conta.", "digio Cash"),
            listOf(Product("Com o e-Gift Card Level Up você adquire créditos para jogar diversas opções de games, de MMORPGs a jogos de Tiro e Mobas! Para ver a lista de jogos e a quantia de créditos que pode resgatar em cada um deles, acesse: http://levelupgames.uol.com.br/levelup/seuegiftcard.lhtml",  "https://s3-sa-east-1.amazonaws.com/digio-exame/level_up_icon.png", "Level up")),
            listOf(Spotlight("https://s3-sa-east-1.amazonaws.com/digio-exame/recharge_banner.png", "Agora ficou mais fácil colocar créditos no seu celular! A digio Store traz a facilidade de fazer recargas... direto pelo seu aplicativo, com toda segurança e praticidade que você procura.", "Recarga"))
        )

        digioDataSource = mock()


        digioRepository = DigioRepositoryImpl(digioDataSource)
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `when view model fetchData get success then sets response`() = runBlocking {
        whenever(digioDataSource.getDigioData()).thenReturn(result)
        viewModel = MainViewModel(digioRepository)

        viewModel.fetchData()

        viewModel.response.observeOnce() {
            assertEquals(result, it.data)
        }
    }


    @Test
    fun `when view model fetchData get error then sets response`() = runBlocking {
        whenever(digioDataSource.getDigioData()).thenThrow(ArrayIndexOutOfBoundsException(""))
        viewModel = MainViewModel(digioRepository)

        viewModel.fetchData()

        viewModel.response.observeOnce() {
            assertEquals(null, it.data)
            assertEquals(Status.ERROR,it.status)
        }
    }

}

fun <T> LiveData<T>.observeOnce(onChangeHandler: (T) -> Unit) {
    val observer = OneTimeObserver(handler = onChangeHandler)
    observe(observer, observer)
}
