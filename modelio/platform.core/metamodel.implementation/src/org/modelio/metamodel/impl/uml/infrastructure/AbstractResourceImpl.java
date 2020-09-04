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

/* WARNING: GENERATED FILE -  DO NOT EDIT
     Metamodel: Infrastructure, version 2.1.02, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Apr 17, 2018
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

    @objid ("2083df50-c3fe-40cf-9ad9-dadce1fc9cf5")
    @Override
    public String getMimeType() {
        return (String) getAttVal(((AbstractResourceSmClass)getClassOf()).getMimeTypeAtt());
    }

    @objid ("dfb9b3e4-08d5-4717-9954-59d779f6cf99")
    @Override
    public void setMimeType(String value) {
        setAttVal(((AbstractResourceSmClass)getClassOf()).getMimeTypeAtt(), value);
    }

    @objid ("d187c309-a8fb-4ea7-93fa-d3cd9f685210")
    @Override
    public String getStorageInfo() {
        return (String) getAttVal(((AbstractResourceSmClass)getClassOf()).getStorageInfoAtt());
    }

    @objid ("9d171c02-8673-4289-8ed6-f5cdce172f4f")
    @Override
    public void setStorageInfo(String value) {
        setAttVal(((AbstractResourceSmClass)getClassOf()).getStorageInfoAtt(), value);
    }

    @objid ("e0ca6964-1d8e-46c0-a1d0-8504ffbfd0d0")
    @Override
    public ResourceType getType() {
        Object obj = getDepVal(((AbstractResourceSmClass)getClassOf()).getTypeDep());
        return (obj instanceof ResourceType)? (ResourceType)obj : null;
    }

    @objid ("d27f4647-046e-4e16-bf3a-f73405e0e018")
    @Override
    public void setType(ResourceType value) {
        appendDepVal(((AbstractResourceSmClass)getClassOf()).getTypeDep(), (SmObjectImpl)value);
    }

    @objid ("0d13452e-f025-419b-b59f-49e9da0ad461")
    @Override
    public ModelElement getSubject() {
        Object obj = getDepVal(((AbstractResourceSmClass)getClassOf()).getSubjectDep());
        return (obj instanceof ModelElement)? (ModelElement)obj : null;
    }

    @objid ("b13f6136-4487-4683-bb31-427e60b5abef")
    @Override
    public void setSubject(ModelElement value) {
        appendDepVal(((AbstractResourceSmClass)getClassOf()).getSubjectDep(), (SmObjectImpl)value);
    }

    @objid ("17baa664-5e9f-40c6-bfc3-95301fc30a34")
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

    @objid ("f19d0ec2-066d-432c-8719-d1342285a262")
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

    @objid ("aa2632b7-6f9b-4576-96ab-d0ac2ba440a3")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitAbstractResource(this);
    }

}
