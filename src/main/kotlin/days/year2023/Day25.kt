package days.year2023

import days.Day
import org.jgrapht.alg.StoerWagnerMinimumCut
import org.jgrapht.graph.DefaultWeightedEdge
import org.jgrapht.graph.SimpleWeightedGraph


fun main() {
    println(Day25().solve())
}
class Day25: Day(25, 2023) {

    var components: MutableList<Component> = mutableListOf()
    var connections: MutableList<Connection> = mutableListOf()
    var size = 0

    fun solve(): Any {
        inputList.forEachIndexed{i,it->
                    var (name, connected) = it.split(": ");
                    val newComp = Component(name)
                    if(getComponent(name)==null)components.add(newComp)
                    val connectedList = connected.split(" ")
                    connectedList.forEach { n->
                        connections.add(Connection(name, n))
                        if(getComponent(n)==null) components.add(Component(n))
                    }
                }
        inputList.forEachIndexed{i,it->
                    var (name, connected) = it.split(": ");
                    val connectedList = connected.split(" ")
                    connectedList.forEach { connectedName ->
                        getComponent(name)!!.connected.addAll(connectedList.map(::getComponentSure))
                        getComponentSure(connectedName).connected.add(getComponent(name)!!)
                    }
                }
        size = components.size


        val graph = SimpleWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge::class.java)
        components.forEach { graph.addVertex(it.name) }
        connections.forEach { graph.addEdge(it.componentName1, it.componentName2) }
        val mincut = StoerWagnerMinimumCut(graph).minCut().size
        return (components.size - mincut) * mincut


//        for(first in connections){
//            for(second in connections){
//                println(second)
//                for(third in connections){
//                    if(first!=second&&second!=third&&first!=third){
//                        val copyConnections = connections.toMutableList()
//                        copyConnections.remove(first)
//                        copyConnections.remove(second)
//                        copyConnections.remove(third)
//                        val treesize = treeIsConnected(copyConnections)
//                        if(treesize!=size) {
//                            println(first)
//                            println(second)
//                            println(third)
//                            return treesize * (size-treesize)
//                        }
//                    }
//                }
//            }
//        }


        return 0;
    }

    fun treeIsConnected(connections: MutableList<Connection>): Int{
        val memory = mutableSetOf<String>()
        val que = mutableSetOf(connections[0].componentName1)
        while(que.isNotEmpty()){
            val name = que.first()
            que.remove(name)

            val c = connections.filter { it.componentName1 == name || it.componentName2 == name }

            que.addAll(c.map { it.componentName1 }.filter { it !in memory })
            que.addAll(c.map { it.componentName2 }.filter { it !in memory })

            memory.addAll(que)
        }
        return memory.size
    }

    fun getComponent(name: String): Component?{
        return components.find{it.name==name}
    }

    fun getComponentSure(name: String): Component{
        return components.find{it.name==name}!!
    }

    data class Component(val name: String){
        val connected: MutableSet<Component> = mutableSetOf()
        fun remove(name: String){
            connected.removeAll { it.name==name }
        }
    }

    data class Connection(val componentName1: String, val componentName2: String)


}