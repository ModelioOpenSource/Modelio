/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package org.modelio.vbasic.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Topological sorter abstract class.
 * <p>
 * Sorts a <a href="http://en.wikipedia.org/wiki/Directed_acyclic_graph"
 * > Directed Acyclic Graph</a>, where vertices are accessible by {@link #getNodes()}
 * and edges with  {@link #getAdjacent(Object)}.
 * <p>
 * <b>Usage:</b>
 * <br>
 * Implement {@link #getNodes()} and {@link #getAdjacent(Object)} then call {@link #sort()}.
 * 
 * @author cma
 * @author credits to <a href="http://rosettacode.org/wiki/User:Margusmartsepp/Contributions/Java/Utils.java">Margusmartsepp‎ @ http://rosettacode.org</a>
 * @param <T> type of the nodes
 */
@objid ("e6937312-1f2d-4aad-bd25-9944a40952a4")
public abstract class TopologicalSorter<T> {
    /**
     * Get <a href="http://en.wikipedia.org/wiki/Directed_acyclic_graph"
     * > Directed Acyclic Graph</a>, vertices.
     * @return all nodes to be sorted
     */
    @objid ("d0df18a7-193a-43e3-a48b-20175a0e2c93")
    public abstract Collection<T> getNodes();

    /**
     * Get the adjacent nodes for a node.
     * @param node a node
     * @return all adjacent nodes
     */
    @objid ("45ed1e9c-b338-4a9b-824d-b230a8942b84")
    public abstract Collection<T> getAdjacent(T node);

    /**
     * Method removes self dependencies and adds missing leaf nodes.
     * @param <T> graph node type
     * @param g <a href="http://en.wikipedia.org/wiki/Directed_acyclic_graph"
     * > Directed Acyclic Graph</a>, where vertices are stored as
     * {@link java.util.HashMap HashMap} elements.
     * @deprecated not used, to be ported
     */
    @objid ("da7a8ca8-a6ba-4413-b658-15bbac606b97")
    @Deprecated
    private static <T> void tSortFix(java.util.Map<T, Collection<T>> g) {
        java.util.Collection<T> tmp;
        java.util.HashSet<T> P = new java.util.HashSet<>();
        P.addAll(g.keySet());
         
        for (T t : P)
            if (g.get(t) != null || !g.get(t).isEmpty()) {
                (tmp = g.get(t)).remove(t);
                for (T m : tmp)
                    if (!P.contains(m))
                        g.put(m, new ArrayList<T>(0));
            }
        
    }

    /**
     * <p>
     * <b>Topological sort</b> solves a problem of - finding a linear ordering
     * of the vertices of <i>V</i> such that for each edge <i>(i, j) ∈ E</i>,
     * vertex <i>i</i> is to the left of vertex <i>j</i>. (Skiena 2008, p. 481)
     * </p>
     * 
     * <p>
     * Method is derived from of <a
     * href="http://en.wikipedia.org/wiki/Topological_sort#Algorithms" > Kahn's
     * pseudo code</a> and traverses over vertices as they are returned by input
     * map. Leaf nodes can have null or empty values. This method assumes, that
     * input is valid DAG, so if cyclic dependency is detected, error is thrown.
     * tSortFix is a fix to remove self dependencies and add missing leaf nodes.
     * </p>
     * 
     * <pre>
     * // For input with elements:
     * { F1=[F2, F3, F4], F10=[F7, F4], F11=[F4], F2=[F3, F8, F4], F3=[F6],
     * F4=null, F5=[F6, F4], F6=[F7, F8, F4], F7=[F4], F8=[F4], F9=[F4]}
     * 
     * // Output based on {@link #getNodes()} type:
     * HashSet: [F4, F11, F8, F9, F7, F10, F6, F5, F3, F2, F1]
     * TreeSet: [F4, F11, F7, F8, F9, F10, F6, F3, F5, F2, F1] (or ordered collection)
     * </pre>
     * @return Linear ordering of input nodes.
     * @throws CyclicDependencyException Thrown when cyclic dependency is detected, error message also
     * contains elements in cycle.
     */
    @objid ("ef68abc5-ab67-4f60-a2ea-017b1ad1a9d0")
    public List<T> sort() throws CyclicDependencyException {
        /**
         * @param L
         *            Answer.
         * @param S
         *            Not visited leaf vertices.
         * @param V
         *            Visited vertices.
         * @param P
         *            Defined vertices.
         * @param n
         *            Current element.
         */
        java.util.ArrayList<T> L = new ArrayList<>(getNodes().size());
        @SuppressWarnings("unused")
        java.util.Queue<T> S = new java.util.ArrayDeque<T>();
        java.util.Set<T> V = new java.util.HashSet<>();
        Collection<T> P = getNodes();
        T n;
         
        // Find leaf nodes.
        for (T t : P) {
            Collection<T> adjacent = getAdjacent(t);
            if (adjacent == null || adjacent.isEmpty())
                S.add(t);
        }
         
        // Visit all leaf nodes. Build result from vertices, that are visited
        // for the first time. Add vertices to not visited leaf vertices S, if
        // it contains current element n an all of it's values are visited.
        while (!S.isEmpty()) {
            if (V.add(n = S.poll()))
                L.add(n);
            for (T t : P) {
                Collection<T> adjacent = getAdjacent(t);
                if (adjacent != null && !adjacent.isEmpty() && !V.contains(t)
                        && V.containsAll(adjacent))
                    S.add(t);
            }
        }
         
        // Return result.
        if (L.containsAll(P))
            return L;
         
        // Throw exception.
        CyclicDependencyException ex = new CyclicDependencyException();
        for (T t : P)
            if (!L.contains(t))  {
                ex.add(t);
            }
        
        throw ex;
        
    }

    /**
     * Indicates there is a cycle in the graph.
     */
    @objid ("d898a9dc-2780-418b-89c3-ddcd20f25d5a")
    public static class CyclicDependencyException extends Exception {
        @objid ("05bb6183-6155-478b-80e9-58ee80653764")
        private static final long serialVersionUID = 1L;

        @objid ("d9aace76-2112-4143-afff-4a2b1bee132b")
        private Collection<Object> cycle = new ArrayList<>();

        @objid ("8d4db54c-b1a9-430b-9101-93359a72bd62")
        <T> void add(T t) {
            this.cycle.add(t);
        }

        /**
         * Get the members of the cycle.
         * The returned collection is not sorted.
         * @param <T> the type of nodes
         * @return the cycle content
         */
        @objid ("9388b79e-4f13-4bea-a913-aafc46b62bd4")
        @SuppressWarnings("unchecked")
        public <T> Collection<T> getCycle() {
            return (Collection<T>) this.cycle;
        }

        @objid ("a6d54a50-6801-48d6-aa55-cf7ce9dffe91")
        @Override
        public String getMessage() {
            StringBuilder sb = new StringBuilder(
                    "Cyclic dependency detected :");
            boolean first = true;
            for (Object t : this.cycle) {
                if (first)
                    first = false;
                else
                    sb.append(", ");
            
                sb.append(t);
            }
            return sb.toString();
        }

    }

}
