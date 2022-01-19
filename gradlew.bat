/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import elmish.Component
import elmish.View
import elmish.a
import elmish.attributes
import elmish.code
import elmish.div
import elmish.empty
import elmish.h1
import elmish.ol
import elmish.pre
import elmish.small
import elmish.span
import elmish.tree.Tree
import elmish.tree.TreeView
import elmish.tree.viewSubTrees

import kotlin.browser.window


internal
sealed class ProblemNode {

    data class Error(val label: ProblemNode, val docLink: ProblemNode?) : ProblemNode()

    data class Warning(val label: ProblemNode, val docLink: ProblemNode?) : ProblemNode()

    data class Task(val path: String, val type: String) : ProblemNode()

    data class Bean(val type: String) : ProblemNode()

    data class Property(val kind: String, val name: String, val owner: String) : ProblemNode()

    data class BuildLogic(val location: String) : ProblemNode()

    data class BuildLogicClass(val type: String) : ProblemNode()

    data class Label(val text: String) : ProblemNode()

    data class Link(val href: String, val label: String) : ProblemNode()

    data class Message(val prettyText: PrettyText) : ProblemNode()

    data class Exception(val stackTrace: String) : ProblemNode()
}


internal
data class PrettyText(val fragments: List<Fragment>) {

    sealed class Fragment {

        data class Text(val text: String) : Fragment()

        data class Reference(val name: String) : Fragment()
    }
}


internal
typealias ProblemTreeModel = TreeView.Model<ProblemNode>


internal
typealias ProblemTreeIntent = TreeView.Intent<ProblemNode>


internal
val ProblemTreeModel.problemCount: Int
    get() = tree.children.size


internal
object ConfigurationCacheReportPage : Component<ConfigurationCacheReportPage.Model, ConfigurationCacheReportPage.Intent> {

    data class Model(
        val cacheAction: String,
        val documentationLink: String,
        val totalProblems: Int,
        val messageTree: ProblemTreeModel,
        val locationTree: ProblemTreeModel,
        val displayFilter: DisplayFilter = DisplayFilter.All,
        val tab: Tab = Tab.ByMessage
    )

    enum class DisplayFilter {
        All, Errors, Warnings
    }

