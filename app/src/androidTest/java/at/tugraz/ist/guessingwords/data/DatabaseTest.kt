package at.tugraz.ist.guessingwords.data

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import at.tugraz.ist.guessingwords.data.database.GWDatabase
import junit.framework.Assert.assertTrue
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DatabaseTest {

    private lateinit var db: GWDatabase

    private fun getContext() = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(
                getContext(),
                GWDatabase::class.java
            ).build()
    }

    @After
    fun teardown() {
        db.close()
    }

    @Test
    fun gettingAllWordsFromAnEmptyDatabaseReturnsEmptyList() {
        val allWords = db.wordDao().getAll()

        assertTrue(allWords.isEmpty())
    }

}