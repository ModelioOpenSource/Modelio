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

package org.modelio.diagram.styles.editingsupport;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.widgets.Event;

/**
 * Interface extracted from JFace {@link org.eclipse.jface.viewers.OwnerDrawLabelProvider} methods that are protected in the class.
 * <p>
 * Used by {@link StyleCellLabelProvider} to delegate owner draw to specialized label provider.
 * <p>
 * To be implemented by label providers put in {@link StyleCellLabelProvider} that want to do owner draw.
 * Such classes must then not extend {@link org.eclipse.jface.viewers.OwnerDrawLabelProvider}.
 * 
 * @author cma
 * @since 3.7
 */
@objid ("c9d5b9fc-1d8b-4eac-8fe1-0ebfb8b95025")
public interface IOwnerDrawLabelProvider {
    /**
     * Handle the measure event.
     * @see SWT#MeasureItem
     * @param event the measure event
     * @param element the model element
     */
    @objid ("79525e71-8bfc-4d33-8ebe-f5cc5eebd50e")
    void measure(Event event, Object element);

    /**
     * Handle the paint event.
     * @see SWT#PaintItem
     * @param event the paint event
     * @param element the model element
     */
    @objid ("2964f052-b788-481b-966e-75bee8221641")
    void paint(Event event, Object element);

}
