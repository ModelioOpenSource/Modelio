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

package org.modelio.gproject.model.impl.mtools;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.vcore.model.spi.mtools.IAuthTool;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MStatus;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmMetamodel;

/**
 * Implementation of {@link IAuthTool}.
 * <p>
 * Helper class to test whether some model operations are allowed.
 */
@objid ("5f252c1a-272e-11e2-a9d1-002564c97630")
public class AuthTool implements IAuthTool {
    @objid ("620e36d0-272e-11e2-a9d1-002564c97630")
    @Override
    public boolean canAdd(final MObject parentElement, final String metaclass) {
        if (parentElement == null) {
            return false;
        }
        
        final MStatus parentStatus = parentElement.getStatus();
        if (parentStatus.isModifiable()) {
            return true;
        }
        
        if (!parentStatus.isUserWrite()) {
            return false;
        }
        
        // Parent is CMS locked here.
        // Both parent and child must be CMS node.
        SmMetamodel mm = CoreSession.getSession(parentElement).getMetamodel();
        
        SmClass mClass = mm.getMClass(metaclass);
        if (mClass == null) {
            // don't know what we add
            return false;
        }
        return canAdd(parentElement, mClass);
    }

    @objid ("c32d4730-4079-499e-98dd-9d0b420072ae")
    @Override
    public boolean canAdd(final MObject parentElement, final MClass metaclass) {
        if (parentElement == null) {
            return false;
        }
        
        final MStatus parentStatus = parentElement.getStatus();
        if (parentStatus.isModifiable()) {
            return true;
        }
        
        if (!parentStatus.isUserWrite()) {
            return false;
        }
        
        // Parent is CMS locked here.
        // Both parent and child must be CMS node.
        return (parentElement.getMClass().isCmsNode() && metaclass.isCmsNode());
    }

    @objid ("f1865eb6-2984-11e2-8460-002564c97630")
    @Override
    public boolean canAddTo(MObject child, MObject parent) {
        MStatus cs = child.getStatus();
        if (!cs.isModifiable()) {
            return false;
        }
        
        if (parent == null) {
            return false;
        }
        
        MStatus ps = parent.getStatus();
        return ps.isModifiable() || (child.getMClass().isCmsNode() && !cs.isCmsManaged() && !cs.isRamc());
    }

    @objid ("620e36e0-272e-11e2-a9d1-002564c97630")
    @Override
    public boolean canCreateLink(Class<? extends MObject> toCreate, MObject srcElement, MObject targetEl) {
        if (!srcElement.getStatus().isModifiable()) {
            return false;
        }
        
        if (InformationFlow.class.isAssignableFrom(toCreate)) {
            NameSpace ns = getCommonNameSpace(srcElement, targetEl);
            if (ns == null || !ns.getStatus().isModifiable()) {
                return false;
            }
        }
        return true;
    }

    @objid ("620e36ea-272e-11e2-a9d1-002564c97630")
    @Override
    public boolean canCreateLinkFrom(Class<? extends MObject> toCreate, MObject srcElement) {
        // The source must be modifiable
        if (!srcElement.getStatus().isModifiable()) {
            return false;
        }
        
        if (InformationFlow.class.isAssignableFrom(toCreate)) {
            // For information flow, at least one parent NameSpace must be modifiable.
            MObject parent = srcElement.getCompositionOwner();
        
            while (parent != null && !(parent instanceof NameSpace && parent.getStatus().isModifiable())) {
                parent = parent.getCompositionOwner();
            }
        
            return parent != null && parent.getStatus().isModifiable();
        }
        return true;
    }

    @objid ("620e36d9-272e-11e2-a9d1-002564c97630")
    @Override
    public boolean canModify(final MObject el) {
        return (el != null && !el.isShell() && !el.isDeleted() && el.getStatus().isModifiable());
    }

    @objid ("f188c004-2984-11e2-8460-002564c97630")
    @Override
    public boolean canRemoveFrom(MObject child, MObject parent) {
        // child must be modifiable, exception: allow deleting shell object
        MStatus cs = child.getStatus();
        if (!cs.isModifiable() && !cs.isShell()) {
            return false;
        }
        
        if (parent == null) {
            return false;
        }
        
        MStatus ps = parent.getStatus();
        return ps.isModifiable() || (child.getMClass().isCmsNode() && !cs.isCmsManaged() && !cs.isRamc());
    }

    @objid ("6210982c-272e-11e2-a9d1-002564c97630")
    private NameSpace getCommonNameSpace(final MObject aSource, final MObject aTarget) {
        final ArrayList<MObject> l1 = new ArrayList<>(20);
        final ArrayList<MObject> l2 = new ArrayList<>(20);
        
        MObject el = aSource;
        while (el != null) {
            l1.add(el);
            el = el.getCompositionOwner();
        }
        
        el = aTarget;
        while (el != null) {
            l2.add(el);
            el = el.getCompositionOwner();
        }
        
        Collections.reverse(l1);
        Collections.reverse(l2);
        
        MObject ret = null;
        int i = 0;
        final int max = Math.min(l1.size(), l2.size());
        do {
            el = l1.get(i);
            if (el instanceof NameSpace) {
                if (el.equals(l2.get(i))) {
                    ret = el;
                } else if (ret != null) {
                    return (NameSpace) ret;
                } else {
                    // no common namespace, return the first namespace found
                    return (NameSpace) el;
                }
            } else if (ret != null) {
                return (NameSpace) ret;
            }
            i++;
        
        } while (i < max);
        
        // Reaching this point means aSource == aTarget
        if (ret != null) {
            return (NameSpace) ret;
        }
        return null;
    }

}
