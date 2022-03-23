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
package org.modelio.gproject.model.impl.importer.core;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.session.api.model.IModel;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("71634561-83c1-433f-9e79-5fbf93a561c1")
public abstract class AbstractObjectFinder implements IObjectFinder {
    @objid ("dd2b64b3-6618-4421-bc7f-a13757ab6231")
    protected final MMetamodel metamodel;

    @objid ("121a2bf1-128f-46da-adc3-f81df35563df")
    protected final IModel searchedSession;

    @objid ("7e6e5e8d-6df5-4cb0-b2b7-2d7da9037d94")
    public  AbstractObjectFinder(IModel searchedSession, MMetamodel metamodel) {
        this.searchedSession = searchedSession;
        this.metamodel = metamodel;
        
    }

    @objid ("34503f1e-2de5-47fb-b93d-94b249899525")
    @Override
    public SmAttribute getSameAttribute(SmAttribute dep) {
        if (isTargetMm(dep.getOwner())) {
            return dep;
        } else {
            return (SmAttribute) getSameMetaclass(dep.getOwner()).getAttribute(dep.getName());
        }
        
    }

    @objid ("200bac10-0220-4dd2-8598-5de1cc4168f7")
    @Override
    public SmDependency getSameDependency(SmDependency dep) {
        if (isTargetMm(dep.getSource())) {
            return dep;
        } else {
            return (SmDependency) getSameMetaclass(dep.getSource()).getDependency(dep.getName());
        }
        
    }

    @objid ("e9facf8c-b3c3-42ff-b536-3a680ca73e41")
    @Override
    public MClass getSameMetaclass(MClass srcClassof) {
        if (isTargetMm(srcClassof)) {
            return srcClassof;
        } else {
            return this.metamodel.getMClass(srcClassof.getQualifiedName());
        }
        
    }

    @objid ("84392164-6dee-47c8-935b-5b661c9f7f04")
    protected boolean isTargetMm(MClass srcClassof) {
        return srcClassof.getMetamodel() == this.metamodel;
    }

}
