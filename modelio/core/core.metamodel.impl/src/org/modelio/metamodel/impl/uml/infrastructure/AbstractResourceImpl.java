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

/* WARNING: GENERATED FILE -  DO NOT EDIT
     Metamodel: Infrastructure, version 2.1.03, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Dec 13, 2018
*/
package org.modelio.metamodel.impl.uml.infrastructure;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.infrastructure.AbstractResourceData;
import org.modelio.metamodel.uml.infrastructure.AbstractResource;
import org.modelio.metamodel.uml.infrastructure.IResourceHandle;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ResourceType;
import org.modelio.metamodel.visitors.IInfrastructureVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("e557ff4d-6c79-4d5c-9bbc-696edadbc636")
public class AbstractResourceImpl extends ModelElementImpl implements AbstractResource {
    @objid ("aaa0ad0e-a9a9-4a8a-a07a-a4f7cb865d40")
    private static final String EMBEDDED_PREFIX = BlobResourceHandle.STORAGE_INFO_PREFIX;

    /**
     * Get access to the represented resource.
     * <p>
     * Returns null if the element is not yet initialized.
     * 
     * @return an access to the represented resource.
     */
    @objid ("1660e785-cba2-44d2-b6e7-c6e9e0ddd351")
    @Override
    public final IResourceHandle getHandle() {
        String storageInfo = getStorageInfo();
        if (storageInfo==null || storageInfo.isEmpty() || storageInfo.equals(EMBEDDED_PREFIX)) {
            return null;
        } else if (storageInfo.startsWith(EMBEDDED_PREFIX)) {
            return new BlobResourceHandle(this);
        } else {
            return new UriResourceHandle(this);
        }
    }

    /**
     * Initialize the element to embed a resource.
     * <p>
     * Use the returned handle to store the resource content.
     * 
     * @param fileName a file name that will be used in case of file extraction.
     * @return a handle to write the resource content.
     */
    @objid ("af7b5e2a-3bf6-4117-b8ce-bac84a431ce1")
    @Override
    public final IResourceHandle createEmbeddedResource(String fileName) {
        if (getHandle() != null) {
            throw new IllegalStateException(String.format("%s is already initialized.", this));
        }
        
        setStorageInfo(EMBEDDED_PREFIX+fileName);
        return new BlobResourceHandle(this);
    }

    @objid ("bb306ba5-8768-441d-a66c-f1e3f02ed0d5")
    @Override
    public final IResourceHandle createExternalResource(String resourceLocation) {
        if (getHandle() != null) {
            throw new IllegalStateException(String.format("%s is already initialized.", this));
        }
        
        setStorageInfo(resourceLocation);
        return new UriResourceHandle(this);
    }

    @objid ("94ab5175-70b0-4878-8954-02a91dd186c5")
    @Override
    public boolean isEmbedded() {
        return getStorageInfo().startsWith(EMBEDDED_PREFIX);
    }

    @objid ("7506ebef-9b6a-49b2-8588-d1e37daf9996")
    @Override
    public String getMimeType() {
        return (String) getAttVal(((AbstractResourceSmClass)getClassOf()).getMimeTypeAtt());
    }

    @objid ("522ef5ed-0adf-49ff-9a01-a5922707d1e0")
    @Override
    public void setMimeType(String value) {
        setAttVal(((AbstractResourceSmClass)getClassOf()).getMimeTypeAtt(), value);
    }

    @objid ("2e90bce0-4c47-4487-b9f5-ae526673d842")
    @Override
    public String getStorageInfo() {
        return (String) getAttVal(((AbstractResourceSmClass)getClassOf()).getStorageInfoAtt());
    }

    @objid ("b38ed43d-f34f-492e-8882-77eb25ba930f")
    @Override
    public void setStorageInfo(String value) {
        setAttVal(((AbstractResourceSmClass)getClassOf()).getStorageInfoAtt(), value);
    }

    @objid ("3c86e079-bec8-4931-b1da-7905b5fad90a")
    @Override
    public ResourceType getType() {
        Object obj = getDepVal(((AbstractResourceSmClass)getClassOf()).getTypeDep());
        return (obj instanceof ResourceType)? (ResourceType)obj : null;
    }

    @objid ("e28d2a83-bf96-4346-a9d6-ac9afaddc1b1")
    @Override
    public void setType(ResourceType value) {
        appendDepVal(((AbstractResourceSmClass)getClassOf()).getTypeDep(), (SmObjectImpl)value);
    }

    @objid ("2ca13ea5-664a-4f85-9cb4-ecfd36311ef9")
    @Override
    public ModelElement getSubject() {
        Object obj = getDepVal(((AbstractResourceSmClass)getClassOf()).getSubjectDep());
        return (obj instanceof ModelElement)? (ModelElement)obj : null;
    }

    @objid ("7caf5bd6-7e5d-4da8-b540-206ebd8e8837")
    @Override
    public void setSubject(ModelElement value) {
        appendDepVal(((AbstractResourceSmClass)getClassOf()).getSubjectDep(), (SmObjectImpl)value);
    }

    @objid ("3e1ec899-50c0-4de4-b742-d94a8e81a02b")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Subject
        obj = (SmObjectImpl)this.getDepVal(((AbstractResourceSmClass)getClassOf()).getSubjectDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("7099510a-4e41-4c28-ae8b-664a3291d1d5")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Subject
        dep = ((AbstractResourceSmClass)getClassOf()).getSubjectDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("5d2470ff-64a8-424c-a908-93dfd0b7c5ef")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitAbstractResource(this);
    }

}
