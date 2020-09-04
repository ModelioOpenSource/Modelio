/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.core.ui.swt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.IStructuredSelection;

/**
 * Helper class to manipulate Eclipse 4 and {@link org.modelio.ui.panel.IPanelProvider} inputs objects.
 * <p>
 * All the methods support as input :<ul>
 * <li> a {@link IStructuredSelection}.
 * <li> a {@link Collection}
 * <li> a single object
 * </ul>
 * The input element(s) may implement {@link IAdaptable} to adapt themselves to the requested type.
 * 
 * @author cmarin
 * @since Valkyrie 3.8
 * @see IStructuredSelection
 * @see IAdaptable
 */
@objid ("57459c0c-3282-4f0f-9a06-a99c1accbabc")
public final class InputHelper {
    /**
     * Tells whether the input contains <b>at least</b> one element of the given type.
     * <p>
     * Returns false if the input is empty.
     * @param input an object
     * @param cls the required type
     * @return true if the input contains at least one such element.
     */
    @objid ("76e9edc6-3235-4b12-8dbf-9bc7a2d48e4f")
    public static boolean contains(final Object input, Class<?> cls) {
        Collection<?> collection = toCollection(input);
        
        for (Object element : collection) {
            if (adapt(element, cls) != null) {
                return true;
            }
        }
        return false;
    }

    /**
     * Tells whether the input contains <b>only</b> elements of the given type.
     * <p>
     * Returns false if the input is empty.
     * @param input a input object
     * @param cls the required type
     * @return true if the input is not empty and contains only such elements.
     */
    @objid ("96f76f1d-ba0e-4a4b-a74c-78e863972507")
    public static boolean containsOnly(final Object input, Class<?> cls) {
        Collection<?> collection = toCollection(input);
        
        // Forbid empty input
        if (collection.isEmpty()) {
            return false;
        }
        
        for (Object element : collection) {
            if (adapt(element, cls) == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Count in the input the elements matching the given type.
     * @param input a input object
     * @param cls the required type
     * @return true the matching elements count.
     */
    @objid ("2fa49db2-9d76-45f3-8983-dbe8f815a505")
    public static int count(final Object input, Class<?> cls) {
        int count = 0;
        Collection<?> collection = toCollection(input);
        
        for (Object element : collection) {
            if (adapt(element, cls) != null) {
                count++;
            }
        }
        return count;
    }

    /**
     * Get the first element in the input that matches the given type.
     * @param <T> the required type
     * @param input the input object
     * @param cls the required type class
     * @return the first matching element or null.
     */
    @objid ("6b248e56-67cb-4bb6-9911-409d3805e1ae")
    public static <T> T getFirst(final Object input, Class<T> cls) {
        Collection<?> collection = toCollection(input);
        
        for (Object element : collection) {
            final T adapter = adapt(element, cls);
            if (adapter != null) {
                return adapter;
            }
        }
        return null;
    }

    /**
     * Get the input size.
     * @param input a input object.
     * @return the input size.
     */
    @objid ("7f1252a3-d1d5-4474-9016-f5e409b1bb04")
    public static int size(final Object input) {
        Collection<?> collection = toCollection(input);
        return collection.size();
    }

    /**
     * Convert the input object to a list of the given type.
     * <p>
     * All input elements not matching the type are filtered out.
     * @param <T> the wanted type
     * @param input the input object
     * @param cls the wanted type
     * @return the filtered input content
     */
    @objid ("f8acac16-cc12-47d0-81bf-d899f6664f38")
    public static <T> List<T> toList(final Object input, Class<T> cls) {
        Collection<?> collection = toCollection(input);
        List<T> selectedElements = new ArrayList<>(collection.size());
        for (Object element : collection) {
            final T adapter = adapt(element, cls);
            if (adapter != null) {
                selectedElements.add(adapter);
            }
        
        }
        return selectedElements;
    }

    /**
     * Convert the input object to a {@link Stream} of the given type.
     * <p>
     * All input elements not matching the type are filtered out.
     * @param <T> the wanted type
     * @param input the input object
     * @param cls the wanted type
     * @return the filtered input content
     */
    @objid ("0bb77309-2d03-43aa-b832-22b66aadf395")
    public static <T> Stream<T> toStream(final Object input, Class<T> cls) {
        Collection<?> collection = toCollection(input);
        if (collection.isEmpty()) {
            return Stream.empty();
        }
        
        Stream<?> stream = collection.stream();
        return stream
                                .map(element -> adapt(element, cls))
                                .filter(Objects::nonNull);
    }

    @objid ("15444df8-e8d1-4764-8281-8dab32fbf397")
    private InputHelper() {
        // no instance
    }

    @objid ("8f930cee-114f-488f-9a31-5bbfe5a2e8be")
    @SuppressWarnings("unchecked")
    private static <T> T adapt(Object o, Class<T> cls) {
        if (cls.isInstance(o)) {
            return (T) o;
        } else if (o instanceof IAdaptable) {
            final T adapter = ((IAdaptable) o).getAdapter(cls);
            if (adapter != null) {
                return adapter;
            }
        }
        return null;
    }

    @objid ("b00ae681-4287-4dad-937b-1edb221ef0a3")
    private static Collection<?> toCollection(Object input) {
        if (input == null) {
            return Collections.emptyList();
        }
        
        if (input instanceof IStructuredSelection) {
            return ((IStructuredSelection) input).toList();
        }
        if (input instanceof Collection) {
            return (Collection<?>) input;
        }
        return Collections.singletonList(input);
    }

}
