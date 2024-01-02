package dev.brampie.myandroidapplication.ui

import dev.brampie.myandroidapplication.fake.FakeApiAppRepository
import dev.brampie.myandroidapplication.network.location.ApiLocationState
import dev.brampie.myandroidapplication.ui.location.LocationViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LocationModelTest {
    private lateinit var viewModel: LocationViewModel

    @get:Rule
    val testDispatcherRule = TestDispatchersRule()

    @Before
    fun setup() {
        viewModel = LocationViewModel(FakeApiAppRepository())
    }

    @Test
    fun getLocations_methodCall_StateIsSuccessAfterCall() {
        // Arrange
        val expectedState = ApiLocationState.Success

        // Act
        viewModel.getApiLocations()

        // Assert
        Assert.assertEquals(expectedState, viewModel.locationApiState)
    }


}