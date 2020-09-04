/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.vcore.smkernel.meta;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <p>
 * Contains metamodel-only resolutions.
 * </p>
 * <p>
 * For now, it doesn't know any "link" metaclass.
 * </p>
 */
@objid ("65252525-f3f8-449c-938e-414cffd68d21")
public class DefaultMetaExpert implements MExpert {
    @objid ("fef58d57-985b-42ac-96a4-3cf32a1c61da")
    @Override
    public boolean canCompose(final MObject owner, final MObject composed, final String dep) {
        return canCompose(owner.getMClass(), composed.getMClass(), dep);
    }

    @objid ("6d983942-104a-4e1a-822e-caadd0f692bf")
    @Override
    public boolean canCompose(final MObject owner, final MClass composed, final String dep) {
        return canCompose(owner.getMClass(), composed, dep);
    }

    @objid ("ab386595-813a-458a-bef3-7e70c79cf25f")
    @Override
    public boolean canCompose(final MClass owner, final MClass composed, String dep) {
        SmClass ownerSmClass = (SmClass) owner;
        SmClass composedSmClass = (SmClass) composed;
        SmDependency smDep = ownerSmClass.getDependencyDef(dep);
        if (smDep == null) {
            smDep = getDefaultCompositionDep(ownerSmClass, composedSmClass);
        }
        
        if (smDep != null) {
            return (smDep.isComposition() || smDep.isSharedComposition()) && ((SmClass) smDep.getTarget()).hasBase(composed);
        } else {
            return false;
        }
    }

    @objid ("39b4da69-61e7-43a4-8c6b-b831cc8d1137")
    @Override
    public boolean canDep(MObject source, MClass target, String dep) {
        return canDep(source.getMClass(), target, dep);
    }

    @objid ("df2cfa63-7c7f-47c4-b467-858c8cbb2c52")
    @Override
    public boolean canDep(MClass source, MClass target, String dep) {
        MDependency mDep = source.getDependency(dep);
        return (mDep != null && ((SmClass) mDep.getTarget()).hasBase(target));
    }

    @objid ("544a8ad7-02b9-4cec-a2b0-b2c3f206e3d7")
    @Override
    public boolean canDep(MObject source, MObject target, String dep) {
        return canDep(source.getMClass(), target.getMClass(), dep);
    }

    @objid ("7d9d1652-09d1-49fd-b291-709ba578917d")
    @Override
    public boolean canLink(MClass linkMetaclass, MObject from, MObject to) {
        return false;
    }

    @objid ("412be1e9-cab7-4e51-807b-7fd0f76b89c2")
    @Override
    public boolean canLink(MClass linkMetaclass, MClass from, MClass to) {
        return false;
    }

    @objid ("b85b0db8-12c9-43f8-ba58-25c1e8718c44")
    @Override
    public boolean canSource(MObject linkElement, MObject from) {
        return false;
    }

    @objid ("76aec563-948e-43c9-a257-d83453017c86")
    @Override
    public boolean canSource(MClass linkMetaclass, final MClass fromMetaclass) {
        return false;
    }

    @objid ("437e5dc8-7431-42f6-b7a2-5235b9c36d09")
    @Override
    public boolean canTarget(MClass linkMetaclass, MClass toMetaclass) {
        return false;
    }

    @objid ("e819a047-d6c8-4334-ac6e-ae1a7e00d322")
    @Override
    public boolean canTarget(MObject linkElement, MObject to) {
        return false;
    }

    @objid ("888bcb45-0206-4d75-8406-f48bdf969083")
    @Override
    public MDependency getDefaultCompositionDep(MObject owner, MObject composed) {
        // The visitor did not find, try a more generic approach
        SmClass depTarget = ((SmObjectImpl) composed).getClassOf();
        SmClass depSource = ((SmObjectImpl) owner).getClassOf();
        return getDefaultCompositionDep(depSource, depTarget);
    }

    @objid ("601a37bd-dbfa-49b3-98bd-9d829bf0e451")
    @Override
    public List<MDependency> getDeps(MObject source, MObject target) {
        List<MDependency> results = new ArrayList<>();
        
        SmClass classDest = ((SmObjectImpl) target).getClassOf();
        
        // Get all the 'non-component' dependencies supported by the metaclass of the 'from' object
        List<SmDependency> allDependency = ((SmObjectImpl) source).getClassOf().getAllReferenceDepDef();
        
        // Loop through the dependencies to find the first one whose supported target type matches the metaclass of the 'to' object.
        for (SmDependency dep : allDependency) {
            if (classDest.hasBase(dep.getTarget())) {
                results.add(dep);
            }
        }
        return results;
    }

    @objid ("f57512a7-25ba-4755-9132-109cd715f477")
    @Override
    public MObject getSource(MObject aLink) {
        for (MDependency dep : aLink.getMClass().getLinkMetaclassSources()) {
            for (MObject val : aLink.mGet(dep)) {
                return val;
            }
        }
        return null;
    }

    @objid ("cc112d67-fd4f-4a62-a1c0-aadd6e670dbd")
    @Override
    public MObject getTarget(MObject aLink) {
        for (MDependency dep : aLink.getMClass().getLinkMetaclassTargets()) {
            for (MObject val : aLink.mGet(dep)) {
                return val;
            }
        }
        return null;
    }

    @objid ("91e8621d-e42d-4b8e-8c15-f44ea6522e2d")
    @Override
    public boolean isLink(MClass metaclass) {
        return metaclass.isLinkMetaclass();
    }

    @objid ("8caa85fe-bea3-43bd-896b-a6b675d44377")
    @Override
    public void setSource(MObject link, MObject oldSource, MObject newSource) throws IllegalArgumentException {
        throw new UnsupportedOperationException("setSource not supported");
    }

    @objid ("63e13927-79bb-4f8d-b526-fbb43d06ccf1")
    @Override
    public void setTarget(MObject link, MObject oldTarget, MObject newTarget) throws IllegalArgumentException {
        throw new UnsupportedOperationException("setSource not supported");
    }

    @objid ("e56e7455-6027-40a2-9d80-957baa900cfb")
    @Override
    public SmDependency getDefaultCompositionDep(MClass depSource, MClass depTarget) {
        // Get all the 'component' dependencies supported by the metaclass of the 'from' object
        List<SmDependency> allCompoDeps = ((SmClass) depSource).getAllComponentDepDef();
        
        // Loop through the dependencies to find the first one whose supported target type matches
        // the metaclass of the 'to' object.
        for (SmDependency compoDep : allCompoDeps) {
            if (depTarget.hasBase(compoDep.getTarget())) {
                // This dependency is a good candidate but it has to be checked against some exceptions
                // whether or not there are potential exceptions is indicated by the presence of the "dependOnClass" directive
                // on the SmDependency
        
                // standard case, got it!
                return compoDep;
            }
        }
        return null;
    }

}
