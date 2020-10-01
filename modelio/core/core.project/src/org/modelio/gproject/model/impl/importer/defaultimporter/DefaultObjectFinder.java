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

package org.modelio.gproject.model.impl.importer.defaultimporter;

import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.model.impl.importer.core.AbstractObjectFinder;
import org.modelio.gproject.model.impl.importer.core.IObjectFinder;
import org.modelio.metamodel.uml.infrastructure.AbstractProject;
import org.modelio.vcore.session.api.model.IModel;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Default implementation of {@link IObjectFinder}.
 */
@objid ("00689656-d3aa-108f-8d81-001ec947cd2a")
public class DefaultObjectFinder extends AbstractObjectFinder {
    @objid ("9c3e132c-5e2e-41e9-9b89-925a882c2afb")
    public DefaultObjectFinder(IModel searchedSession, MMetamodel metamodel) {
        super(searchedSession, metamodel);
    }

    @objid ("0068a948-d3aa-108f-8d81-001ec947cd2a")
    @Override
    public SmObjectImpl getSameObject(final SmObjectImpl searchedObject) {
        MClass srcClassof = searchedObject.getMClass();
        MClass classof = getSameMetaclass(srcClassof);
        
        // Find by identifier
        SmObjectImpl ret = (SmObjectImpl) this.searchedSession.findById(classof, searchedObject.getUuid());
        
        // Find by other methods if failed
        if (ret == null) {
            if (searchedObject instanceof AbstractProject) {
                // Find all AbstractProject by name
                ret = (SmObjectImpl) getSameAbstractProject((AbstractProject) searchedObject);
            }
        }
        return ret;
    }

    @objid ("00693052-d3aa-108f-8d81-001ec947cd2a")
    protected MObject getSameAbstractProject(AbstractProject anObject) {
        // Look for a project with same metaclass and same name
        Collection<? extends MObject> projects = this.searchedSession.findByClass(anObject.getMClass());
        for (MObject aProject : projects) {
            if (anObject.getName().equals(aProject.getName())) {
                return aProject;
            }
        }
        
        // no project with same name, return the first modifiable one
        MObject first = null;
        for (MObject obj : projects) {
            if (obj.isModifiable()) {
                return obj;
            } else if (first == null) {
                first = obj;
            }
        }
        
        // no modifiable project, return the first one
        return first;
    }

}
