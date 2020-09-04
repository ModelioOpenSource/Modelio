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

package org.modelio.editors.richnote.gui.nattable;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.ISingleNatValue;
import org.modelio.metamodel.uml.infrastructure.Document;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

/**
 * Defines an {@link INatValue} wrapping a single {@link Document} instance.
 */
@objid ("c8e60e1a-6f65-455b-9c13-8f2f53ce1fc9")
public interface IDocumentNatValue extends ISingleNatValue {
    /**
     * @return the edited {@link Document}. Might be <code>null</code>.
     */
    @objid ("8652de76-8187-4259-92a7-8c0b06e804ae")
    @Override
    Document getValue();

    /**
     * @return the owner of the edited {@link Document}.
     */
    @objid ("86b78e49-4546-47fa-ad98-d834528ad711")
    ModelElement getOwner();

}
