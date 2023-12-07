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
     Metamodel: Infrastructure, version 2.1.04, by Modeliosoft
     Generator version: 3.14.00
     Generated on: May 3, 2023
*/

package org.modelio.metamodel.impl.uml.infrastructure;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
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

    @objid ("f0e612fd-b65f-43b0-bfc0-8047f6f9843b")
    @Override
    public String getMimeType() {
        return (String) getAttVal(((AbstractResourceSmClass)getClassOf()).getMimeTypeAtt());
    }

    @objid ("5a7bd8c8-33c3-4698-a18a-6376c97ed41d")
    @Override
    public void setMimeType(String value) {
        setAttVal(((AbstractResourceSmClass)getClassOf()).getMimeTypeAtt(), value);
    }

    @objid ("9fdec9fc-0604-4f96-96d8-d5c3cd608efa")
    @Override
    public String getStorageInfo() {
        return (String) getAttVal(((AbstractResourceSmClass)getClassOf()).getStorageInfoAtt());
    }

    @objid ("6126ff7e-03d0-43e0-a817-bf26af69c055")
    @Override
    public void setStorageInfo(String value) {
        setAttVal(((AbstractResourceSmClass)getClassOf()).getStorageInfoAtt(), value);
    }

    @objid ("605d78e5-1662-4c3d-9333-fb41a45c6e69")
    @Override
    public ResourceType getType() {
        Object obj = getDepVal(((AbstractResourceSmClass)getClassOf()).getTypeDep());
        return (obj instanceof ResourceType)? (ResourceType)obj : null;
    }

    @objid ("a14d60e1-6096-4562-a300-a02bdabfc8f5")
    @Override
    public void setType(ResourceType value) {
        appendDepVal(((AbstractResourceSmClass)getClassOf()).getTypeDep(), (SmObjectImpl)value);
    }

    @objid ("3171c087-59ba-400f-ac0c-a4927af95cae")
    @Override
    public ModelElement getSubject() {
        Object obj = getDepVal(((AbstractResourceSmClass)getClassOf()).getSubjectDep());
        return (obj instanceof ModelElement)? (ModelElement)obj : null;
    }

    @objid ("ae1fee38-b185-48f3-8583-800a6825addc")
    @Override
    public void setSubject(ModelElement value) {
        appendDepVal(((AbstractResourceSmClass)getClassOf()).getSubjectDep(), (SmObjectImpl)value);
    }

    @objid ("e7846ece-34b7-4dde-9e93-ea793cd6c21c")
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

    @objid ("fa3dc3c1-2f05-49e9-a56a-4555f8b803b9")
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

    @objid ("fab38fe9-9196-4f52-9bd5-ee8e8b995804")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitAbstractResource(this);
    }

}
