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

package org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.generic;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IErrorReport;
import org.modelio.vcore.smkernel.mapi.modelshield.api.ModelError;
import org.modelio.vcore.smkernel.mapi.modelshield.spi.IChecker;

/**
 * The TypeChecker checker can control the type of the owner of an object
 * against a list of metaclasses.<br>
 * 
 * An abstract method that returns the object whose type to check has to be
 * provided:
 * 
 * MObject getCheckedObject()
 * 
 * @author phv
 */
@objid ("00960140-120f-1f6a-b3fb-001ec947cd2a")
public abstract class TypeChecker implements IChecker {
    @objid ("0096236e-120f-1f6a-b3fb-001ec947cd2a")
    private final String errorId;

    @objid ("e37c6958-d72d-11e1-bf21-002564c97630")
    private final List<MClass> allowedOwnerTypes = new ArrayList<>();

    @objid ("e37c695c-d72d-11e1-bf21-002564c97630")
    private final List<MClass> forbiddenOwnerTypes = new ArrayList<>();

    /**
     * @param obj @return
     */
    @objid ("00961afe-120f-1f6a-b3fb-001ec947cd2a")
    protected abstract MObject getCheckedObject(final MObject obj);

    @objid ("00961c34-120f-1f6a-b3fb-001ec947cd2a")
    @Override
    public void check(MObject object, final IErrorReport report) {
        if (object == null) {
            return;
        }
        
        MObject checkedObject = getCheckedObject(object);
        if (checkedObject == null) {
            // the rule cannot complain about type when there is no object
            // the responsibility of checking null object is not here
            return;
        }
        
        // The check is based on allowed metaclasses if at least one is defined
        // and on forbidden classes
        MClass checkedType = checkedObject.getMClass();
        
        // condition for allowed metaclasses
        boolean ok = (this.allowedOwnerTypes.isEmpty() || this.allowedOwnerTypes.contains(checkedType));
        
        // condition for forbidden metaclasses
        ok &= (this.forbiddenOwnerTypes.isEmpty() || !this.forbiddenOwnerTypes.contains(checkedType));
        
        if (!ok) {
            List<Object> objects = new ArrayList<>();
            objects.add(object);
            objects.add(checkedObject);
            report.addEntry(new ModelError(this.errorId, object, objects));
        }
        return;
    }

    @objid ("00962684-120f-1f6a-b3fb-001ec947cd2a")
    public TypeChecker(final String errorId) {
        this.errorId = errorId;
    }

    @objid ("009607c6-120f-1f6a-b3fb-001ec947cd2a")
    public void addRequiredType(final MClass smClass) {
        this.allowedOwnerTypes.add(smClass);
    }

    @objid ("0088a130-2a3b-1f6b-b3fb-001ec947cd2a")
    public void addForbiddenType(final MClass smClass) {
        this.forbiddenOwnerTypes.add(smClass);
    }

}
