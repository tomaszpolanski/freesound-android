/*
 * Copyright 2017 Futurice GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.futurice.freesound.feature.home

import android.support.annotation.VisibleForTesting
import com.futurice.freesound.feature.common.streams.Fetch
import com.futurice.freesound.feature.common.streams.asFetchStream
import com.futurice.freesound.feature.user.UserRepository
import com.futurice.freesound.network.api.model.User
import io.reactivex.Observable

class HomeUserInteractor(private val userRepository: UserRepository) {

    companion object {
        @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
        val HOME_USERNAME = "SpiceProgram"
    }

    /**
     * Emits the any current cached home user, triggers a fetch from the API and then streams
     * further updates.
     */
    fun homeUserStream(): Observable<Fetch<User>> {
        return userRepository.user(HOME_USERNAME)
                .toObservable()
                .asFetchStream(userRepository.awaitUserStream(HOME_USERNAME))
    }

}
