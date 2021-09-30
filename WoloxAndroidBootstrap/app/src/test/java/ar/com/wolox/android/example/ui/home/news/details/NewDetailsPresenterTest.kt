package ar.com.wolox.android.example.ui.home.news.details

import ar.com.wolox.android.example.model.New
import ar.com.wolox.android.example.model.dtos.NewDetailsResponse
import ar.com.wolox.android.example.network.repository.NewRepository
import ar.com.wolox.android.example.ui.new
import ar.com.wolox.android.example.ui.newDetailsResponse
import ar.com.wolox.android.example.ui.user
import ar.com.wolox.android.example.utils.UserSession
import ar.com.wolox.wolmo.core.tests.CoroutineTestRule
import ar.com.wolox.wolmo.core.tests.WolmoPresenterTest
import ar.com.wolox.wolmo.networking.retrofit.handler.NetworkResponse
import com.nhaarman.mockitokotlin2.whenever
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.any
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.internal.util.reflection.FieldSetter
import retrofit2.Response

@ExperimentalCoroutinesApi
class NewDetailsPresenterTest : WolmoPresenterTest<NewDetailsView, NewDetailsPresenter>() {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule(runOnAllTests = true)

    @Mock
    lateinit var newRepository: NewRepository

    @Mock
    lateinit var userSession: UserSession

    override fun getPresenterInstance(): NewDetailsPresenter = NewDetailsPresenter(newRepository, userSession)

    @Test
    fun `given a user and a New when on init then call view setup`() = runBlocking {
        // given
        whenever(userSession.user).doReturn(user)
        // when
        presenter.onInit(new)
        // then
        verify(view, times(1)).setupNew(new)
        verify(view, times(1)).toggleLikedButton(any())
    }

    @Test
    fun `given a New when request then show New`() = runBlocking {

        // given
        val id = 1
        val response = Mockito.mock(Response::class.java) as Response<NewDetailsResponse>
        val mockedNew = Mockito.mock(New::class.java)
        FieldSetter.setField(presenter, presenter.javaClass.getDeclaredField("new"), mockedNew)
        whenever(mockedNew.id).doReturn(id)
        whenever(response.body()).doReturn(newDetailsResponse)
        whenever(newRepository.getSingleNew(id)).doReturn(NetworkResponse.Success(response))
        // when
        presenter.onRefresh().join()
        // then
        verify(mockedNew, times(1)).update(newDetailsResponse)
        verify(view, times(1)).setupNew(mockedNew)
        verify(view, times(1)).toggleLikedButton(any())
        verify(view, times(1)).dismissRefreshing()
    }

    @Test
    fun `given a New when request then show error`() = runBlocking {
        // given
        val id = 1
        val mockedNew = Mockito.mock(New::class.java)
        val response = Mockito.mock(Response::class.java) as Response<NewDetailsResponse>

        FieldSetter.setField(presenter, presenter.javaClass.getDeclaredField("new"), mockedNew)
        whenever(mockedNew.id).doReturn(id)
        whenever(newRepository.getSingleNew(id)).doReturn(NetworkResponse.Error(response))
        // when
        presenter.onRefresh().join()
        // then
        verify(view, times(1)).showError()
        verify(view, times(1)).dismissRefreshing()
    }

    @Test
    fun `given a New when request then show network error`() = runBlocking {
        // given
        val id = 1
        val mockedNew = Mockito.mock(New::class.java)
        FieldSetter.setField(presenter, presenter.javaClass.getDeclaredField("new"), mockedNew)
        whenever(mockedNew.id).doReturn(id)
        whenever(newRepository.getSingleNew(id)).doReturn(NetworkResponse.Failure(Throwable()))
        // when
        presenter.onRefresh().join()
        // then
        verify(view, times(1)).showNetworkError()
        verify(view, times(1)).dismissRefreshing()
    }

    @Test
    fun `when like image clicked then New should be updated`() = runBlocking {

        // given
        val id = 1
        val response = Mockito.mock(Response::class.java) as Response<Unit>
        val mockedNew = Mockito.mock(New::class.java)
        FieldSetter.setField(presenter, presenter.javaClass.getDeclaredField("new"), mockedNew)

        whenever(userSession.user).doReturn(user)
        whenever(mockedNew.id).doReturn(id)
        whenever(mockedNew.likes).doReturn(mutableListOf(user.id))
        whenever(newRepository.toggleLiked(id)).doReturn(NetworkResponse.Success(response))
        // when
        presenter.onLikedClicked().join()
        // then
        verify(view, times(1)).toggleEnabledLikedButton(false)
        verify(view, times(1)).toggleLikedButton(false)
        verify(view, times(1)).toggleEnabledLikedButton(true)
    }

    @Test
    fun `when like image clicked then show error`() = runBlocking {

        // given
        val id = 1
        val response = Mockito.mock(Response::class.java) as Response<Unit>
        val mockedNew = Mockito.mock(New::class.java)
        FieldSetter.setField(presenter, presenter.javaClass.getDeclaredField("new"), mockedNew)

        whenever(userSession.user).doReturn(user)
        whenever(mockedNew.id).doReturn(id)
        whenever(mockedNew.likes).doReturn(mutableListOf(user.id))
        whenever(newRepository.toggleLiked(id)).doReturn(NetworkResponse.Error(response))
        // when
        presenter.onLikedClicked().join()
        // then
        verify(view, times(1)).toggleEnabledLikedButton(false)
        verify(view, times(1)).showError()
        verify(view, times(1)).toggleEnabledLikedButton(true)
    }

    @Test
    fun `when like image clicked then show network error`() = runBlocking {

        // given
        val id = 1
        val mockedNew = Mockito.mock(New::class.java)
        FieldSetter.setField(presenter, presenter.javaClass.getDeclaredField("new"), mockedNew)

        whenever(userSession.user).doReturn(user)
        whenever(mockedNew.id).doReturn(id)
        whenever(mockedNew.likes).doReturn(mutableListOf(user.id))
        whenever(newRepository.toggleLiked(id)).doReturn(NetworkResponse.Failure(Throwable()))
        // when
        presenter.onLikedClicked().join()
        // then
        verify(view, times(1)).toggleEnabledLikedButton(false)
        verify(view, times(1)).showNetworkError()
        verify(view, times(1)).toggleEnabledLikedButton(true)
    }

    @Test
    fun `given image full screen when imageClicked then show`() = runBlocking {
        // given
        FieldSetter.setField(presenter, presenter.javaClass.getDeclaredField("imageFullscreen"), false)
        // when
        presenter.onCoverImageClicked()
        // then
        verify(view, times(1)).displayImageFullscreen()
    }

    @Test
    fun `given image full screen when imageClicked then dismiss`() = runBlocking {

        // given
        FieldSetter.setField(presenter, presenter.javaClass.getDeclaredField("imageFullscreen"), true)
        // when
        presenter.onCoverImageClicked()
        // then
        verify(view, times(1)).dismissImageFullscreen()
    }
}
