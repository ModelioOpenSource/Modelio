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

package org.modelio.vcore.model.spi.mm;

import java.util.Collection;
import java.util.HashSet;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.meta.descriptor.MClassRef;

/**
 * Resume of metamodel changes between 2 versions.
 * @author cma
 * @since 3.6
 */
@objid ("5f60a610-31e5-4acc-910e-004c25b3eb22")
public class MetamodelChangeDescriptor {
    @objid ("a8e8d90b-af9a-4dda-81dc-2a9e1593e9b9")
    private final Collection<MClassRef> addedClass = new HashSet<>();

    @objid ("a84b6842-48ea-4d8c-8965-dfb902a8bbb8")
    private final Collection<MClassRef> deletedClass = new HashSet<>();

    @objid ("8d0f5755-d70c-4307-9b02-39a2daac5f55")
    private final Collection<MClassRef> addedCmsNode = new HashSet<>();

    @objid ("841c3b8e-90e8-4349-aca4-51197e621343")
    private final Collection<MClassRef> removedCmsNode = new HashSet<>();

    /**
     * Add a new CMS node
     * @param mmFragName the metamodel fragment name
     * @param mClassName the metaclass name
     * @return this instance.
     */
    @objid ("d4c0eced-8ef8-43c5-88f5-0bcfb1a36171")
    public MetamodelChangeDescriptor newCmsNode(String mmFragName, String mClassName) {
        this.addedCmsNode.add(new MClassRef(mmFragName,mClassName));
        return this;
    }

    /**
     * Add a new CMS node
     * @param mmFragName the metamodel fragment name
     * @param mClassName the metaclass name
     * @return this instance.
     */
    @objid ("f3aea66c-f163-42a1-84b2-5074bf820524")
    public MetamodelChangeDescriptor oldCmsNode(String mmFragName, String mClassName) {
        this.removedCmsNode.add(new MClassRef(mmFragName,mClassName));
        return this;
    }

    /**
     * Add a new metaclass.
     * @param mmFragName the metamodel fragment name
     * @param mClassName the metaclass name
     * @return this instance.
     */
    @objid ("f37cccc6-4a70-452c-9ec8-974957dc077a")
    public MetamodelChangeDescriptor addClass(String mmFragName, String mClassName) {
        this.addedClass.add(new MClassRef(mmFragName,mClassName));
        return this;
    }

    /**
     * Register a deleted metaclass.
     * @param mmFragName the metamodel fragment name
     * @param mClassName the metaclass name
     * @return this instance.
     */
    @objid ("98063499-8704-40fe-a2e6-da2ff637e7b2")
    public MetamodelChangeDescriptor classRemoved(String mmFragName, String mClassName) {
        this.deletedClass.add(new MClassRef(mmFragName,mClassName));
        return this;
    }

    /**
     * @return the new metaclasses
     */
    @objid ("c13339db-0fab-4caa-bced-435b080af24a")
    public Collection<MClassRef> getAddedClasses() {
        return this.addedClass;
    }

    /**
     * @return the new CMS node classes
     */
    @objid ("a9fdfb4a-d5cd-4dd4-b2f8-e25a2fe8c332")
    public Collection<MClassRef> getAddedCmsNodes() {
        return this.addedCmsNode;
    }

    /**
     * @return the deleted metaclasses.
     */
    @objid ("2acc9fb9-49d8-4421-93c4-66c7a8c066af")
    public Collection<MClassRef> getDeletedClasses() {
        return this.deletedClass;
    }

    /**
     * @return the metaclasses that are not CMS node anymore.
     */
    @objid ("465d4361-083c-4899-b25e-9599b6aae3aa")
    public Collection<MClassRef> getRemovedCmsNodes() {
        return this.removedCmsNode;
    }

}
