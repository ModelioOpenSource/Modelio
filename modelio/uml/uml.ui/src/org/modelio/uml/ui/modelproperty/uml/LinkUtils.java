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

package org.modelio.uml.ui.modelproperty.uml;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.element.choice.DefaultElementChoiceNatValue;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.InterfaceRealization;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.metamodel.uml.statik.NameSpace;

/**
 * Utilities for {@link LinkEnd} related property models.
 * 
 * @author cmarin
 */
@objid ("dfab3116-b36a-49e1-a8a0-5d54de9bde85")
class LinkUtils {
    @objid ("b70b519a-3d86-44d5-bed8-703ff8616307")
    private static List<AssociationEnd> getAllOwnedEnd(NameSpace sourceBase) {
        List<AssociationEnd> ret = new ArrayList<>();
        ret.addAll(((Classifier) sourceBase).getOwnedEnd());
        
        for (Generalization g : sourceBase.getParent()) {
            ret.addAll(getAllOwnedEnd(g.getSuperType()));
        }
        
        for (InterfaceRealization r : sourceBase.getRealized()) {
            ret.addAll(getAllOwnedEnd(r.getImplemented()));
        }
        return ret;
    }

    @objid ("55573c01-3691-4ecd-b91a-7438b74fc544")
    private static List<AssociationEnd> getAllTargetingEnd(NameSpace sourceBase) {
        List<AssociationEnd> ret = new ArrayList<>();
        ret.addAll(((Classifier) sourceBase).getTargetingEnd());
        
        for (Generalization g : sourceBase.getParent()) {
            ret.addAll(getAllTargetingEnd(g.getSuperType()));
        }
        
        for (InterfaceRealization r : sourceBase.getRealized()) {
            ret.addAll(getAllTargetingEnd(r.getImplemented()));
        }
        return ret;
    }

    @objid ("4f2b26f9-258a-44ff-9219-3b547e175db7")
    public static INatValue getBaseAssociationType(LinkEnd editedEnd) {
        List<ModelElement> availableEnds = new ArrayList<>();
        
        Instance source = editedEnd.getOwner();
        Instance target = editedEnd.getOpposite().getOwner();
        NameSpace sourceBase = source.getBase();
        NameSpace targetBase = target.getBase();
        if (sourceBase != null && sourceBase instanceof Classifier && targetBase != null
                && targetBase instanceof Classifier) {
            for (AssociationEnd end : getAllOwnedEnd(sourceBase)) {
                if (isSubTypeOf(targetBase, end.getOpposite().getOwner())) {
                    if (!availableEnds.contains(end)) {
                        availableEnds.add(end);
                    }
                }
            }
        
            for (AssociationEnd end : getAllTargetingEnd(sourceBase)) {
                if (isSubTypeOf(targetBase, end.getOwner())) {
                    if (!availableEnds.contains(end.getOpposite())) {
                        availableEnds.add(end.getOpposite());
                    }
                }
            }
        }
        return new DefaultElementChoiceNatValue(editedEnd.getModel(), true, Collections.singletonList(AssociationEnd.class),
                                availableEnds);
    }

    /**
     * Tells whether child is same or sub type of 'parent'.
     * @param child a namespace
     * @param parent a namespace potentially parent of child
     * @return whether child is same or sub type of 'parent'.
     */
    @objid ("6e90ea44-2e2d-4dd8-9ce9-399af4d496d2")
    private static boolean isSubTypeOf(NameSpace child, NameSpace parent) {
        if (child == null || parent == null) {
            return false;
        }
        if (child.equals(parent)) {
            return true;
        }
        
        for (Generalization g : child.getParent()) {
            if (isSubTypeOf(g.getSuperType(), parent)) {
                return true;
            }
        }
        
        if (parent instanceof Interface) {
            for (InterfaceRealization r : child.getRealized()) {
                if (isSubTypeOf(r.getImplemented(), parent)) {
                    return true;
                }
            }
        }
        return false;
    }

}
