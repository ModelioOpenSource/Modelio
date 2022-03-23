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
package org.modelio.metamodel.impl.expert.standard.links.impl.creation;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.expert.standard.links.ILinkExpert;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.ClassAssociation;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.NaryAssociation;
import org.modelio.metamodel.uml.statik.NaryAssociationEnd;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Creation expert for {@link ClassAssociation}.
 * <p>
 * ClassAssociation must go from an Association to a Classifier. The Class must not be one of the Association ends.
 */
@objid ("7e99a2e8-1eb2-11e2-8009-002564c97630")
public class ClassAssociationCreationExpert extends DefaultDelegatingLinkExpert {
    @objid ("7e99a2ed-1eb2-11e2-8009-002564c97630")
    @Override
    public boolean canSource(final MClass linkMetaclass, final MClass fromMetaclass) {
        return AssociationEnd.class.isAssignableFrom(fromMetaclass.getJavaInterface())
                                || NaryAssociation.class.isAssignableFrom(fromMetaclass.getJavaInterface());
    }

    @objid ("7e9c03f8-1eb2-11e2-8009-002564c97630")
    @Override
    public boolean canSource(final MObject fromElement, final MObject ownerElement) {
        if (fromElement instanceof AssociationEnd) {
            if (((AssociationEnd) fromElement).getAssociation().getLinkToClass() != null) {
                return false;
            }
            return true;
        } else if (fromElement instanceof NaryAssociation) {
            if (((NaryAssociation) fromElement).getLinkToClass() != null) {
                return false;
            }
            return true;
        }
        return false;
    }

    @objid ("7e9c0401-1eb2-11e2-8009-002564c97630")
    @Override
    public boolean canLink(final MClass linkMetaclass, final MObject fromElement, final MObject toElement) {
        List<Classifier> linkedElements = new ArrayList<>();
        // ClassAssociation must go from an Association to a Classifier.
        if (fromElement instanceof AssociationEnd) {
            final AssociationEnd fromEnd = (AssociationEnd) fromElement;
            if (fromEnd.getAssociation().getLinkToClass() != null) {
                return false;
            }
            linkedElements.add(fromEnd.getOwner());
            linkedElements.add(fromEnd.getOpposite().getOwner());
        } else if (fromElement instanceof NaryAssociation) {
            final NaryAssociation fromNary = (NaryAssociation) fromElement;
            if (fromNary.getLinkToClass() != null) {
                return false;
            }
            for (NaryAssociationEnd end : fromNary.getNaryEnd()) {
                linkedElements.add(end.getOwner());
            }
        }
        if (!(toElement instanceof Class)) {
            return false;
        } else {
            return !linkedElements.contains(toElement);
        }
        
    }

    @objid ("33994630-67c0-4176-bd91-c3c4229aa57d")
    public  ClassAssociationCreationExpert(ILinkExpert defaultExpert) {
        super(defaultExpert);
    }

}
