/* 
 * Copyright 2013-2019 Modeliosoft
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
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

/**
 * Helper class to manipulate {@link ISelection} objects.
 * <p>
 * All the methods check the selection is a {@link IStructuredSelection}.
 * Selection objects not matching this criteria are treated as empty selections.
 * 
 * @author cmarin
 * @see ISelection
 * @see IStructuredSelection
 */
@objid ("6c42007e-a5cb-4c7f-9cc4-c7411b06587a")
public final class SelectionHelper {
    @objid ("63c8fb35-debb-40fa-8e33-de99ea153386")
    private SelectionHelper() {
        // no instance
    }

    /**
     * Tells whether the selection contains <b>at least</b> one element of the given type.
     * <p>
     * Returns false if the selection is empty.
     * 
     * @param selection a selection object
     * @param cls the required type
     * @return true if the selection contains at least one such element.
     */
    @objid ("10019deb-e7e4-4553-a92e-9edf11818d94")
    public static boolean contains(final ISelection selection, Class<?> cls) {
        if (selection instanceof IStructuredSelection) {
            for (Object element : ((IStructuredSelection) selection).toArray()) {
                if (adapt(element, cls) != null) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Tells whether the selection contains <b>only</b> elements of the given type.
     * <p>
     * Returns false if the selection is empty.
     * 
     * @param selection a selection object
     * @param cls the required type
     * @return true if the selection is not empty and contains only such elements.
     */
    @objid ("cb731922-65df-4d9d-a4a2-4cf52edad6b2")
    public static boolean containsOnly(final ISelection selection, Class<?> cls) {
        if (selection instanceof IStructuredSelection) {
            final IStructuredSelection structuredSelection = (IStructuredSelection) selection;
            
            // Forbid empty selection
            if (structuredSelection.size() == 0) {
                return false;
            }
        
            for (Object element : structuredSelection.toArray()) {
                if (adapt(element, cls) == null) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Count in the selection the elements matching the given type.
     * 
     * @param selection a selection object
     * @param cls the required type
     * @return true the matching elements count.
     */
    @objid ("e94c103e-231b-4bdd-b9bf-cfa0193177f9")
    public static int count(final ISelection selection, Class<?> cls) {
        int count = 0;
        if (selection instanceof IStructuredSelection) {
            for (Object element : ((IStructuredSelection) selection).toArray()) {
                if (adapt(element, cls) != null) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Get the first element in the selection that matches the given type
     * @param <T> the required type
     * 
     * @param selection the selection object
     * @param cls the required type class
     * @return the first matching element or null.
     */
    @objid ("360f2b5f-bf9c-407a-9964-ae8571a758a2")
    public static <T> T getFirst(final ISelection selection, Class<T> cls) {
        if (selection instanceof IStructuredSelection) {
            for (Object element : ((IStructuredSelection) selection).toArray()) {
                final T adapter = adapt(element, cls);
                if (adapter != null) {
                    return adapter;
                }
            }
        }
        return null;
    }

    /**
     * Get the selection size.
     * <p>
     * Returns 0 if the selection is not a {@link IStructuredSelection}.
     * 
     * @param selection a selection object.
     * @return the selection size.
     */
    @objid ("5d751412-8b15-4474-b409-7f5df21f41eb")
    public static int size(final ISelection selection) {
        if (selection instanceof IStructuredSelection) {
            return ((IStructuredSelection) selection).size();
        } else {
            return 0;
        }
    }

    /**
     * Convert the selection object to a list of the given type.
     * <p>
     * All selection elements not matching the type are filtered out.
     * @param <T> the wanted type
     * 
     * @param selection the selection object
     * @param cls the wanted type
     * @return the filtered selection content
     */
    @objid ("e5357575-e3de-433b-996c-5d1d57eb2e15")
    public static <T> List<T> toList(final ISelection selection, Class<T> cls) {
        List<T> selectedElements = new ArrayList<>();
        
        if (selection instanceof IStructuredSelection) {
            for (Object element : ((IStructuredSelection) selection).toArray()) {
                final T adapter = adapt(element, cls);
                if (adapter != null) {
                    selectedElements.add(adapter);
                }
        
            }
        }
        return selectedElements;
    }

    /**
     * Convert the selection object to a {@link Stream} of the given type.
     * <p>
     * All selection elements not matching the type are filtered out.
     * @param <T> the wanted type
     * 
     * @param selection the selection object
     * @param cls the wanted type
     * @return the filtered selection content
     */
    @objid ("6c2456cb-ed7e-432a-995d-8f88d45306df")
    public static <T> Stream<T> toStream(final ISelection selection, Class<T> cls) {
        if (selection instanceof IStructuredSelection) {
            Stream<?> stream = ((IStructuredSelection) selection).toList().stream();
            
            return stream
            .map(element -> adapt(element, cls))
            .filter(Objects::nonNull);
        }
        return Stream.empty();
    }

    @objid ("23402f6a-7760-49ca-ae6b-ca8e8db73e8c")
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

}
