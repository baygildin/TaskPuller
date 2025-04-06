package com.sbaygildin.taskpuller.feature

import com.sbaygildin.taskpuller.feature.timer.TimerViewModel
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TimerViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: TimerViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = TimerViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `startTimer sets isRunning true and remainingTime`() = runTest {
        viewModel.startTimer(1)

        assertTrue(viewModel.timerState.value.isRunning)
        assertEquals(60_000L, viewModel.timerState.value.remainingTime)
    }


    @Test
    fun `timer counts down each second`() = runTest {
        viewModel.startTimer(1)
        advanceTimeBy(1000L)
        runCurrent()
        assertEquals(59000L, viewModel.timerState.value.remainingTime)
    }

    @Test
    fun `stopTimer sets isRunning false`() = runTest {
        viewModel.startTimer(1)
        viewModel.stopTimer()

        assertFalse(viewModel.timerState.value.isRunning)
    }

    @Test
    fun `resetTimer resets timer state`() = runTest {
        viewModel.startTimer(1)
        viewModel.resetTimer()

        val state = viewModel.timerState.value
        assertFalse(state.isRunning)
        assertEquals(0L, state.remainingTime)
    }
}