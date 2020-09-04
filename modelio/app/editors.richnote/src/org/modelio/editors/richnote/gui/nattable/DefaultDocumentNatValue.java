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

import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.metamodel.uml.infrastructure.Document;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

/**
 * Default implementation of {@link IDocumentNatValue}.
 */
@objid ("f5aa24a6-796e-49c9-98c5-a1b4a175a764")
public class DefaultDocumentNatValue extends DefaultElementNatValue implements IDocumentNatValue {
    @objid ("556ca076-ce7d-41ee-94da-92a71dd32ec8")
    private ModelElement owner;

    /**
     * Creates a new instance.
     * 
     * @param owner the owner of the {@link Document}.
     * @param value the wrapped value.
     * @param acceptNullValue whether or not <code>null</code> is a valid value for this field.
     */
    @objid ("0cc9d55f-9032-4818-9b29-a8431889f774")
    public DefaultDocumentNatValue(ModelElement owner, Document value, boolean acceptNullValue) {
        super(value, acceptNullValue, Collections.singletonList(Document.class));
        this.owner = owner;
    }

    /**
     * Copy constructor, creating a new instance with the same configuration as the other.
     * 
     * @param anotherInstance the instance to copy.
     */
    @objid ("24e93b21-d8c3-4a9f-8689-ec795eadc4e1")
    public DefaultDocumentNatValue(DefaultDocumentNatValue anotherInstance) {
        super(anotherInstance);
        this.owner = anotherInstance.owner;
    }

    @objid ("3a13cb2f-bf0e-413d-b7ff-f9250a56e881")
    @Override
    public Document getValue() {
        return (Document) super.getValue();
    }

    @objid ("8431fd46-c9f6-482e-8bd6-9f68c001732b")
    @Override
    public ModelElement getOwner() {
        return this.owner;
    }

}
