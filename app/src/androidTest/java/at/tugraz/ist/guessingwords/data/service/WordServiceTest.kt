package at.tugraz.ist.guessingwords.data.service

import android.os.ConditionVariable
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import at.tugraz.ist.guessingwords.data.entity.Word
import org.junit.Before
import org.junit.Test
import org.junit.rules.Timeout
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)

class WordServiceTest {
    private lateinit var service : WordService
    private fun getContext() = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun setup(){
        service = WordService(getContext())
    }

    @Test(timeout = 3000)
    fun testCallbackIsCalledBackOnGetAllWords(){
        val cb = object:Callback<List<Word>>{
            val finished = ConditionVariable()
            override fun whenReady(data: List<Word>?) {
                finished.open()
            }
        }
        service.getAllWords(cb)
        cb.finished.block()
    }
}