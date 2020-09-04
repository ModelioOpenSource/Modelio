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

package org.modelio.vcore.session.impl.permission;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.session.impl.handles.IAccessHandle;
import org.modelio.vcore.smkernel.AccessDeniedException;
import org.modelio.vcore.smkernel.IPStatus;
import org.modelio.vcore.smkernel.IRStatus;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MStatus;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

/**
 * Default access handle.
 * <p>
 * Check rights on the object.
 */
@objid ("000c66f6-702c-1f21-85a5-001ec947cd2a")
public class DefaultAccessHandle implements IAccessHandle {
    /**
     * Same as SmStatus.MASK_MODIFIABLE_FORBIDDEN but allow CMSREADONLY
     */
    @objid ("c079aeb6-4081-11e2-87cb-001ec947ccaf")
    private static final long MASK_FORBIDDEN_ADD_TO_CMS = IRStatus.RAMC|IRStatus.SHELL|IRStatus.DELETED;

    @objid ("000cf42c-702c-1f21-85a5-001ec947cd2a")
    private static AccessDeniedException createReadOnlyObjectException(final SmObjectImpl smObject) {
        // Build an error message like "Package a::b::c is not modifiable."
        StringBuilder message = new StringBuilder(200);
        message.append(smObject.getName());
        
        SmObjectImpl owner = smObject.getCompositionOwner();
        while (owner != null) {
            message.insert(0, "/");
            message.insert(0, owner.getName());
            owner = owner.getCompositionOwner();
        }
        message.insert(0, "'/");
        
        message.append("' ");
        message.append(smObject.getMClass().getName());
        message.append(" is not modifiable:");
        
        MStatus status = smObject.getStatus();
        if (status.isCmsReadOnly()) {
            message.append(" it is locked by the CMS,");
        }
        if (status.isRamc()) {
            message.append(" it is a model library element,");
        }
        if (status.isDeleted()) {
            message.append(" it is a deleted element,");
        }
        if (!status.isDomainWrite()) {
            message.append(" it is not editable,");
        }
        if (!status.isObjectWrite()) {
            message.append(" it is read only,");
        }
        if (status.isShell()) {
            message.append(" it is an unresolved reference,");
        }
        if (!status.isUserWrite()) {
            message.append(" you don't have the needed rights,");
        }
        message.deleteCharAt(message.length()-1);
        message.append(".");
        return new AccessDeniedException(message.toString(), smObject);
    }

    @objid ("dc142726-8fb5-11e1-81e9-001ec947ccaf")
    private static boolean isModifiable(final SmObjectImpl obj) {
        if (isObjModifiable(obj)) {
            return true;
        } else {
            return obj.getClassOf().hasDirectiveInGraph(SmDirective.NOREADONLY);
        }
    }

    @objid ("000caf9e-702c-1f21-85a5-001ec947cd2a")
    private static boolean isObjModifiable(final SmObjectImpl obj) {
        return obj.hasStatus(IRStatus.RMASK_MODIFIABLE_REQUIRED , IPStatus.PMASK_MODIFIABLE_REQUIRED, IRStatus.RMASK_MODIFIABLE_FORBIDDEN,0L);
    }

    @objid ("000d2b36-702c-1f21-85a5-001ec947cd2a")
    @Override
    public void checkAccess(final SmObjectImpl obj) throws AccessDeniedException {
        if (!isModifiable(obj)) {
            throw createReadOnlyObjectException(obj);
        }
    }

    @objid ("000c7bbe-702c-1f21-85a5-001ec947cd2a")
    @Override
    public void checkAccessFor(final SmObjectImpl obj, final SmAttribute att, final SmObjectImpl val) throws AccessDeniedException {
        final boolean modifiable = isModifiable(obj);
        
        if (!modifiable) {
            if (!obj.getClassOf().hasDirectiveInGraph(SmDirective.NOREADONLY)) {
                if (att == obj.getClassOf().statusAtt() || att.hasDirective(SmDirective.NOREADONLY)) {
                    return ;
                } else {
                    throw createReadOnlyObjectException(obj);
                }
            }
        }
    }

    @objid ("e01c01ae-9a12-4558-8b8c-3a18e35042c7")
    @Override
    public void checkAccessFor(final SmObjectImpl obj, final SmDependency dep, final SmObjectImpl val) throws AccessDeniedException {
        checkAccess(obj, dep, val);
        
        SmDependency symetric = dep.getSymetric();
        if (symetric != null && val != null) {
            checkAccess(val, symetric, obj);
        }
    }

    @objid ("24f41c7b-382d-4b30-bad3-2f0beabe297f")
    private void checkAccess(final SmObjectImpl obj, final SmDependency dep, final SmObjectImpl val) throws AccessDeniedException {
        // First quick check that may avoid loading the object
        if (! dep.doModifyObject()) {
            return;
        }                
        
        // Note : this may trigger object (and its CMS node) loading
        final boolean modifiable = isModifiable(obj);
        
        if (!modifiable) {
            if (!obj.getClassOf().hasDirectiveInGraph(SmDirective.NOREADONLY)) {
                
                if (dep.hasDirective(SmDirective.NOREADONLY)) {
                    return;
                } 
                
                // Allow add a non managed CMS node to a locked CMS node
                if ((dep.isComposition() || dep.isSharedComposition()) &&
                        val != null &&
                        val.getMClass().isCmsNode() &&
                        !val.getStatus().isCmsManaged() &&
                        obj.hasStatus(IRStatus.RMASK_MODIFIABLE_REQUIRED, IPStatus.PMASK_MODIFIABLE_REQUIRED, MASK_FORBIDDEN_ADD_TO_CMS, 0)) {
                    return ;
                } 
                
                // Allow deleting shell objects
                if (obj.isShell()) {
                    if (dep.isCompositionOpposite()) {
                        return;
                    }
                }
                
                throw createReadOnlyObjectException(obj);
            }
        }
    }

}
