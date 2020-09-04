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

package org.modelio.core.ui.nattable.parts.data.element.choice;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.element.single.IElementNatValue;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Defines an {@link INatValue} wrapping a single {@link MObject} instance.
 */
@objid ("d22aaeab-fddb-451d-9683-4126ad81bc0e")
public interface IElementChoiceNatValue extends IElementNatValue {
    @objid ("b41bdac9-d5ef-42a2-8b15-7aabbd2a64e1")
    @Override
    MObject getValue();

    /**
     * @return A list to choose the value from at edition.
     */
    @objid ("2ad65caa-e816-40e6-bcd2-ebcb71965799")
    List<? extends MObject> getValueChoices();

}
