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
package org.modelio.vcore.model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MVisitor;

/**
 * Service class that walks the model transitively, handles cycles and duplicates with a fluent API.
 * <p>
 * The class is to be considered as immutable, each withXxx() method return a new instance
 * to be used instead of the caller.
 * This allow pre-configuring a walker in a static variable then customize it on each run.
 * <p>
 * Contrary to java {@link java.util.Stream streams} a ModelWalker is reusable,
 * even more as long as {@link #from(MObject)} has not been called.
 * <p>
 * <h2> Example: </h2>
 * This build a model walker that get all interfaces implemented by a namespace.
 * <pre><code>
 * NameSpace ns = ...;
 * Collection&lt;NameSpace> implemented = new ModelWalker&lt;NameSpace>()
 * .withCompositeTransition(NameSpace::getRealized, InterfaceRealization::getImplemented)
 * .withCompositeTransition(NameSpace::getParent, Generalization::getSuperType)
 * .withFilter(Interface.class::isInstance)
 * .from(ns)
 * .getTraversed();
 * </code></pre>
 * @param <A> the type of walked elements
 * 
 * @author cma
 * @since 3.7.1
 * @deprecated Experimental, API under progress, may change without notice.
 */
@objid ("ffe828d8-ea59-40c9-bed5-d70290bedd9c")
@Deprecated
public class ModelWalker<A extends MObject> {
    @objid ("7c78f9de-3824-4bd5-a6d6-cb570281a0d0")
    private final boolean addSources;

    @objid ("ef2da4b5-9bfd-4810-86c0-ef529b56932f")
    private final List<A> from;

    @objid ("205b647b-61c9-4a86-9431-9f0030962c3a")
    private final Predicate<A> filter;

    @objid ("bbd30dbc-2ab6-405b-ad04-b310364766f3")
    private final Collection<Transition<A, A>> transitions;

    /**
     * Initialize a new model walker.
     */
    @objid ("b7218d80-efd0-4cc5-88e0-d82ea092efb0")
    public  ModelWalker() {
        this.from = Collections.emptyList();
        this.transitions= Collections.emptyList();
        this.filter = null ;
        this.addSources = false;
        
    }

    /**
     * Add a model element to walk from.
     * @param el a model element
     * @return another instance
     */
    @objid ("fb6ad8b9-7073-42f9-8d33-dec8b7919e30")
    public ModelWalker<A> from(A el) {
        ArrayList<A> nf = new ArrayList<>(this.from);
        nf.add(el);
        return new ModelWalker<>(nf, this.transitions, this.filter, this.addSources);
    }

    /**
     * Applies the transition transitively and return all found elements.
     * <p>
     * The elements are returned in breadth first order.
     * @return all found elements.
     */
    @objid ("9c0f5fc5-3500-41a5-a609-e8c92eb7a104")
    public Collection<A> getTraversed() {
        Set<A> ret = new HashSet<>();
        getTraversed(this.from, ret);
        if (this.addSources) {
            ret.addAll(this.from);
        }
        if (this.filter != null) {
            ret.removeIf(this.filter.negate());
        }
        return ret;
    }

    /**
     * Add a composite transition to walk.
     * <p>
     * The function is applied to each transition result and gives the real next element.
     * @param t1 a transition
     * @param t2 a function applied to each transition result.
     * @return the new walker to use.
     */
    @objid ("342a30f5-7fa8-442b-9daa-709a46169ca7")
    public <B> ModelWalker<A> withCompositeTransition(Transition<A, B> t1, Function<B, A> t2) {
        Transition<A, A> compositeTransition = a -> {
            Collection<B> t1Res = t1.walk(a);
            if (t1Res.isEmpty()) {
                return Collections.emptyList();
            }
            
            Collection<A> ret = new ArrayList<>(t1Res.size());
            for (B b : t1Res) {
                A c = t2.apply(b);
                if (c != null) {
                    ret.add(c);
                }
            }
            
            return ret;
        };
        return withTransition(compositeTransition) ;
    }

    /**
     * Add a composite transition to walk.
     * <p>
     * The second transition is applied to each transition result and gives the real next elements.
     * @param t1 a transition
     * @param t2 a second transition applied to each transition result.
     * @return the new walker to use.
     */
    @objid ("0aef2223-1dd5-46de-a04e-2a9bd3c18730")
    public <B> ModelWalker<A> withCompositeTransition(Transition<A, B> t1, Transition<B, A> t2) {
        Transition<A, A> compositeTransition = a -> {
            Collection<B> t1Res = t1.walk(a);
            if (t1Res.isEmpty()) {
                return Collections.emptyList();
            }
            
            Collection<A> ret = new ArrayList<>(t1Res.size() * 2);
            for (B b : t1Res) {
                Collection<A> c = t2.walk(b);
                if (c != null) {
                    ret.addAll(c);
                }
            }
            
            return ret;
        };
        return withTransition(compositeTransition) ;
    }

    /**
     * Set a filter on the returned result.
     * <p>
     * The filter does not stop iteration on transition.
     * @param aFilter a filter on results
     * @return a new instance
     */
    @objid ("cb6afa7c-287a-4db2-893c-597998406a37")
    public ModelWalker<A> withFilter(Predicate<A> aFilter) {
        return new ModelWalker<>(this.from, this.transitions, aFilter, this.addSources);
    }

    /**
     * Set whether initial model elements added with {@link #from(MObject)} will be included in results.
     * @param includesources true to include initial nodes
     * @return the new walker to use.
     */
    @objid ("a8e3a81e-2c36-4a4e-8c3c-55fe61a1a3dc")
    public ModelWalker<A> withSourcesIncluded(boolean includesources) {
        return new ModelWalker<>(this.from, this.transitions, this.filter, includesources);
    }

    /**
     * Add a transition to walk.
     * @param transition the transition to walk.
     * @return this instance
     */
    @objid ("dee974e0-9aeb-4a08-a410-1ebb8a68423e")
    public ModelWalker<A> withTransition(Transition<A, A> transition) {
        ArrayList<Transition<A,A>> nt = new ArrayList<>(this.transitions);
        nt.add(transition);
        return new ModelWalker<>(this.from, nt, this.filter, this.addSources);
    }

    /**
     * Add a {@link MVisitor} as transition.
     * <p>
     * The visitor is expected to return a Collection of A or <i>null</i>.
     * @param transitionVisitor a visitor that returns a collection.
     * @return the new walker to use.
     */
    @objid ("9f28d060-bf4e-4265-8bae-3e322a98dddb")
    @SuppressWarnings ("unchecked")
    public ModelWalker<A> withTransition(MVisitor transitionVisitor) {
        return withTransition( o -> (Collection<A>) o.accept(transitionVisitor));
    }

    /**
     * Immutable design pattern constructor.
     * @param from initial elements
     * @param transitions transitions
     * @param filter result filter
     */
    @objid ("74f76f93-7987-4f0b-9e4e-570d240a28c4")
    protected  ModelWalker(List<A> from, Collection<Transition<A, A>> transitions, Predicate<A> filter, boolean withSources) {
        super();
        this.from = from;
        this.transitions = transitions;
        this.filter = filter;
        this.addSources = withSources;
        
    }

    /**
     * Applies t to each element of <i>from</i> and return the result in a new collection.
     * <p>
     * Equivalent to <code>from.stream().map(t).collect(Collectors.toList())</code> without using streams.
     * @param from a source collection
     * @param t the function to apply to each element.
     * @return the result.
     * @deprecated use {@link #withCompositeTransition(Transition, Function)}
     */
    @objid ("f55bd0b9-942e-44fa-808e-cd882e09522b")
    @Deprecated
    private static <A, B> Collection<B> composeFunc(Collection<A> from, Function<A, B> t) {
        Collection<B> ret = new ArrayList<>(from.size());
        for (A a : from) {
            B next = t.apply(a);
            if (next != null) {
                ret.add(next);
            }
        }
        return ret;
    }

    /**
     * Applies the <i>t</i> transition to each element of <i>from</i> and return the result in a new collection.
     * <p>
     * Equivalent to:
     * <pre>
     * <code>from.stream().flatMap(a -> t.apply(a).stream()).collect(Collectors.toList())</code></pre> without using streams.
     * @param from a source collection
     * @param t the function to apply to each element.
     * @return the result.
     */
    @objid ("611d0d62-a695-49ca-b646-c1c56318909a")
    @Deprecated
    private static <A, B> Collection<B> composeTransition(Collection<A> from, Transition<A, B> t) {
        Collection<B> ret = new ArrayList<>(from.size() * 2);
        for (A a : from) {
            Collection<? extends B> next = t.walk(a);
            if (next != null) {
                ret.addAll(next);
            }
        }
        return ret;
    }

    /**
     * Get all elements found by walking the transition from the given objects transitively.
     * <p>
     * The elements are returned in breadth first order.
     * @param filter a filter that can stop the iteration.
     * @param roots the model objects to iterate.
     * @param traversed a set where all walked elements will be added.
     * @deprecated reimplemented in {@link #getTraversed(Collection, Set)}
     */
    @objid ("9e00a2ff-51ae-4100-aae5-b21e82863c1c")
    @Deprecated
    private void getTraversed0(final Collection<? extends A> roots, final Set<A> traversed) {
        // initialize a current roots list from the passed root elements
        Collection<A> currentRoots = new ArrayList<>(roots);
        Collection<A> directChildren = new ArrayList<>();
        
        // Loop until there is no root nodes
        while (!currentRoots.isEmpty()) {
            // Get direct childs of current roots into 'directChildren'
            for (A o : currentRoots) {
                for (Transition<A, A> transition : this.transitions) {
                    Collection<? extends A> nexts = transition.walk(o);
                    if (nexts != null) {
                        directChildren.addAll(nexts);
                    }
                }
            }
        
            // Clear the current roots list
            // in order to rebuild it for next iteration
            currentRoots.clear();
        
            // Add each new child to the result set and to the next roots list
            for (A child : directChildren) {
                if (! traversed.contains(child)) {
                    // Add the child to the set
                    traversed.add(child);
        
                    // Add the child to the next roots list
                    currentRoots.add(child);
                }
            }
        
            // Drop direct children list and create new one
            directChildren = new ArrayList<>();
        
        }
        
    }

    @objid ("a8a93216-2fe5-4fc0-91ab-daee74f1c65d")
    private void getTraversed(final Collection<? extends A> roots, final Set<A> traversed) {
        // initialize a current roots list from the passed root elements
        Deque<A> queue = new ArrayDeque<>(roots);
        
        // Loop until there is no root nodes
        while (!queue.isEmpty()) {
            A o = queue.poll();
            for (Transition<A, A> transition : this.transitions) {
                Collection<A> nexts = transition.walk(o);
                if (nexts != null) {
                    for (A n : nexts) {
                        if (traversed.add(n)) {
                            queue.add(n);
                        }
                    }
                }
            }
        }
        
    }

    /**
     * The function applied to an element to give the next ones.
     * @author cma
     * @since 3.7.1
     * 
     * @param <A> the type of walked elements
     */
    @objid ("08a76746-ae34-4a52-9665-2f1e28eb5da2")
    @FunctionalInterface
    public interface Transition<A, B> {
        @objid ("d461e2e4-ab59-4c49-afc2-2ec3f3a61f52")
        Collection<B> walk(A a);
}
    

}
