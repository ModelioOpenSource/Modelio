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
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.model.impl.copy.CopyMachine;
import org.modelio.gproject.model.impl.importer.core.IImportReport;
import org.modelio.metamodel.mmextensions.standard.facilities.CompositionInitializer;
import org.modelio.vcore.model.spi.mtools.IModelTool;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Implementation of {@link IModelTool}.
 */
@objid ("7e9c041b-1eb2-11e2-8009-002564c97630")
public class ModelTool implements IModelTool {
    @objid ("14bc773f-152b-412a-a074-1fb7378b9343")
    @Override
    public MObject cloneElement(MObject toClone) {
        return copyElement(toClone, toClone.getCompositionOwner());
    }

    @objid ("aa9b2f1b-b769-4d7c-ba3c-cb9d43bf1eb1")
    @Override
    public List<? extends MObject> cloneElements(List<? extends MObject> toClone) {
        return copyElements(toClone, toClone.get(0).getCompositionOwner());
    }

    @objid ("9257f8e2-f213-4ff3-a3ee-158b04d45899")
    @Override
    public MObject copyElement(MObject toCopy, MObject target) {
        List<MObject> elementsToCopy = new ArrayList<>();
        elementsToCopy.add(toCopy);
        
        List<? extends MObject> copiedElements = copyElements(elementsToCopy, target);
        if (copiedElements.size() > 0) {
            return copiedElements.get(0);
        }
        return null;
    }

    @objid ("026b25d0-9037-416b-91c6-254d3f3e0983")
    @Override
    public List<MObject> copyElements(List<? extends MObject> toCopy, MObject target) {
        CoreSession localSession = CoreSession.getSession(target);
        CoreSession refSession = null;
        
        List<SmObjectImpl> smObjectsToCopy = new ArrayList<>();
        for (MObject mObject : toCopy) {
            smObjectsToCopy.add((SmObjectImpl) mObject);
        
            if (refSession == null) {
                refSession = CoreSession.getSession(mObject);
            }
        }
        
        List<MObject> ret = new ArrayList<>();
        
        CopyMachine machine = new CopyMachine();
        final IImportReport report = machine.execute(localSession, (SmObjectImpl) target, refSession, smObjectsToCopy);
        
        // Fill the returned list in the appropriate order
        for (MObject ref : toCopy) {
            ret.add(report.getCreatedObject((SmObjectImpl) ref));
        }
        return ret;
    }

    @objid ("01f41c74-0000-00ef-0000-000000000000")
    private void moveTo(SmObjectImpl smObject, CompositionInitializer parentInitiliazer, SmObjectImpl oldParentHint) {
        SmDepVal compositionDepVal = smObject.getCompositionRelation();
        
        boolean ok;
        
        if (compositionDepVal.value != null) {
            if (compositionDepVal.dep.isMultiple()) {
                ok = smObject.eraseDepVal(compositionDepVal.dep, oldParentHint);
            } else {
                ok = smObject.appendDepVal (compositionDepVal.dep, null);
            }
        } else {
            ok = true;
        }
        
        if (ok) {
        
            if (!parentInitiliazer.execute (smObject, compositionDepVal.dep)) {
                throw new RuntimeException("Cannot move object" + smObject.getName());
            }
        }
    }

    @objid ("01f41c74-0000-00fe-0000-000000000000")
    @Override
    public void moveElements(List<? extends MObject> toMove, MObject newParent, MObject oldParentHint) {
        CompositionInitializer parentInitiliazer = new CompositionInitializer((SmObjectImpl) newParent);
        
        for (MObject elementToMove : toMove) {
            moveTo((SmObjectImpl) elementToMove, parentInitiliazer, (SmObjectImpl) oldParentHint);
        }
    }

    @objid ("a03dba89-24d0-11e2-ba1c-002564c97630")
    @Override
    public void moveElement(MObject toMove, MObject newParent, MObject oldParentHint) {
        CompositionInitializer parentInitiliazer = new CompositionInitializer((SmObjectImpl) newParent);
        moveTo((SmObjectImpl) toMove, parentInitiliazer, (SmObjectImpl) oldParentHint);
    }

    @objid ("514cfe7f-28b1-4637-bfd1-3cf154c66a04")
    @Override
    public List<List<? extends MObject>> copyElements(List<List<? extends MObject>> toCopy, List<MObject> target) {
        CoreSession localSession = null;
        CoreSession refSession = null;
        
        for (MObject mObject : target) {
            localSession = CoreSession.getSession(mObject);
            break;
        }
        
        List<List<SmObjectImpl>> listsToCopy = new ArrayList<>();
        for (List<? extends MObject> list : toCopy) {
            List<SmObjectImpl> smObjectsToCopy = new ArrayList<>();
        
            for (MObject mObject : list) {
                smObjectsToCopy.add((SmObjectImpl) mObject);
        
                if (refSession == null) {
                    refSession = CoreSession.getSession(mObject);
                }
            }
        
            listsToCopy.add(smObjectsToCopy);
        }
        
        List<SmObjectImpl> targetList = new ArrayList<>();
        for (MObject mObject : target) {
            targetList.add((SmObjectImpl) mObject);
        }
        
        List<List<? extends MObject>> ret = new ArrayList<>();
        
        CopyMachine machine = new CopyMachine();
        final IImportReport report = machine.execute(localSession, targetList, refSession, listsToCopy);
        
        // Fill the returned list in the appropriate order
        for (List<? extends MObject> list : toCopy) {
            List<MObject> copies = new ArrayList<>();
        
            for (MObject ref : list) {
                copies.add(report.getCreatedObject((SmObjectImpl) ref));
            }
        
            ret.add(copies);
        }
        return ret;
    }

}
