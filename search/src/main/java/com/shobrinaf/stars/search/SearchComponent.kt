package com.shobrinaf.stars.search

import android.content.Context
import com.shobrinaf.stars.di.ModuleDependencies
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [ModuleDependencies::class])
interface SearchComponent {

    fun inject(activity: SearchActivity)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(moduleDependencies: ModuleDependencies): Builder
        fun build(): SearchComponent
    }

}