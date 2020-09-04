/* 
 * Copyright 2013-2019 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package org.modelio.api.ui.swt;

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
 * @since 3.7.1
 * @see ISelection
 * @see IStructuredSelection
 */
@objid ("acea6a4e-ea5d-46fc-9690-d8500c0a2116")
public final class SelectionHelper {
    @objid ("5ffe3602-d87b-4cc7-851b-e5b604e1594a")
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
    @objid ("2ccc0951-d847-4c24-9a32-4063deaf9963")
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
    @objid ("46a503ab-b4ea-4758-946c-7bbc27200bf6")
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
    @objid ("6b2818a5-293f-4845-a2d2-c106cf877bff")
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
     * Convert the selection object to a list of the given type.
     * <p>
     * All selection elements not matching the type are filtered out.
     * @param <T> the wanted type
     * 
     * @param selection the selection object
     * @param cls the wanted type
     * @return the filtered selection content
     */
    @objid ("ef2028d5-faeb-47a4-897c-6507a2e60b4d")
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
    @objid ("6d0b96e1-f2d1-4fc4-b6a7-523b92d0f19d")
    public static <T> Stream<T> toStream(final ISelection selection, Class<T> cls) {
        if (selection instanceof IStructuredSelection) {
            Stream<?> stream = ((IStructuredSelection) selection).toList().stream();
        
            return stream
                    .map(element -> adapt(element, cls))
                    .filter(Objects::nonNull);
        }
        return Stream.empty();
    }

    /**
     * Get the first element in the selection that matches the given type
     * @param <T> the required type
     * 
     * @param selection the selection object
     * @param cls the required type class
     * @return the first matching element or null.
     */
    @objid ("4e5c5d4b-34d6-49cf-baef-bd42bf13c86c")
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

    @objid ("8051bc26-13ba-4250-bc78-5fe31111649f")
    @SuppressWarnings ("unchecked")
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
