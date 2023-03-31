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
package org.modelio.platform.model.ui.swt.textelement;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("bb300b87-219e-4b18-b254-bfff84f01feb")
public interface ITextElementSelectionListener {
    /**
     * Called when the selected element changes.
     * @param oldElement previously selected element. May be null.
     * @param newElement newly selected element. May be null.
     */
    @objid ("8d4be666-c068-11e1-8c0a-002564c97630")
    void selectedElementChanged(final MObject oldElement, final MObject newElement);
}

