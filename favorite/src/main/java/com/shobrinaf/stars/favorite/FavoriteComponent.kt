package com.shobrinaf.stars.favorite

import android.content.Context
import com.shobrinaf.stars.di.ModuleDependencies
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [ModuleDependencies::class])
interface FavoriteComponent {

    fun inject(activity: FavoriteActivity)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(moduleDependencies: ModuleDependencies): Builder
        fun build(): FavoriteComponent
    }

}