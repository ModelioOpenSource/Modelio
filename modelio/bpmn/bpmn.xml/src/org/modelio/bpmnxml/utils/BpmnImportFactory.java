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
package org.modelio.bpmnxml.utils;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.repository.IRepositorySupport;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.session.impl.SmFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;

/**
 * Generic object factory.
 */
@objid ("bb17a2b1-d8b8-49c9-b44d-63973674e85d")
public final class BpmnImportFactory {
    @objid ("193f0912-a585-4d7c-852b-6675ea4d63f0")
    private SmMetamodel metamodel;

    @objid ("70541dcb-65f5-45d8-8c81-69d2919d4595")
    private IRepositorySupport repoSupport;

    @objid ("53e5c42a-3f46-461f-a24e-735e34081b62")
    private SmFactory smFactory;

    @objid ("c0d34870-b197-4dec-bffc-2d6dc10a431f")
    private ICoreSession session;

    /**
     * Initialize a generic factory.
     * @param smFactory a core factory.
     * @param repoSupport a repository support.
     */
    @objid ("fa561a35-a73e-48aa-90a1-0b722183fd2b")
    public  BpmnImportFactory(CoreSession session) {
        this.repoSupport = session.getRepositorySupport();
        this.smFactory = session.getSmFactory();
        this.metamodel = session.getMetamodel();
        this.session = session;
        
    }

    /**
     * Create an instance of 'metaclass' and define 'parent' as its composition owner using the dependency 'depName'.
     * The new object will belong to the same repository as the 'parent' object.
     * @param metaclass a metamodel class java interface
     * @param parent the new element owner
     * @param depName the metamodel relation from the owner to the created element.
     * @return the created object
     */
    @objid ("6e377083-a295-42aa-9c23-68259668f3a2")
    @SuppressWarnings("unchecked")
    public <T extends MObject> T createWithId(Class<T> metaclass, MObject parent, String depName, String id) {
        assert(id != null && !id.equals(""));
        
        MObject newObj = null;
        try{
          newObj = this.smFactory.createObject(this.metamodel.getMClass(metaclass), this.repoSupport.getRepository(parent),id);
        }catch(IllegalArgumentException e){
            MObject element = this.session.getModel().findById(metaclass,id);
            if(element != null && element.isDeleted() ){
                ((SmObjectImpl)element).getMetaOf().objUndeleted((SmObjectImpl)element);
                newObj = element;
            }else{
                newObj = this.smFactory.createObject(this.metamodel.getMClass(metaclass), this.repoSupport.getRepository(parent));
            }
        }
        
                ((SmObjectImpl) parent).appendDepVal((SmDependency) parent.getMClass().getDependency(depName), (SmObjectImpl) newObj);
        return (T) newObj;
    }

    /**
     * Create an instance of 'metaclass'. The new object will belong to the same repository as the 'referent' object. The 'referent'
     * object is NOT the composition owner of the created object.
     * @param <T>
     * the metaclass interface of the object to create.
     * @param metaclass the metaclass of the object to create.
     * @param referent the referent object
     * @
     * @return the created object
     */
    @objid ("b7e6a93c-5ad3-434b-a181-4510dd4fa20e")
    @SuppressWarnings("unchecked")
    public <T extends MObject> T createWithId(Class<T> metaclass, MObject referent, String id) {
        assert(id != null && !id.equals(""));       
        
        MObject newObj = null;
        try{
            newObj = this.smFactory.createObject(this.metamodel.getMClass(metaclass), this.repoSupport.getRepository(referent),id);
          }catch(IllegalArgumentException e){
              MObject element = this.session.getModel().findById(metaclass,id);
              if(element != null && element.isDeleted() ){
                  ((SmObjectImpl)element).getMetaOf().objUndeleted((SmObjectImpl)element);
                  newObj = element;
              }else{
                 newObj = this.smFactory.createObject(this.metamodel.getMClass(metaclass), this.repoSupport.getRepository(referent));
             }
          }
        return (T) newObj;
    }

    /**
     * Create an instance of 'metaclass' and define 'parent' as its composition owner using the dependency 'depName'.
     * The new object will belong to the same repository as the 'parent' object.
     * @param metaclass a metamodel class java interface
     * @param parent the new element owner
     * @param depName the metamodel relation from the owner to the created element.
     * @return the created object
     */
    @objid ("59f80de3-bed3-482a-a12a-75071de5b207")
    @SuppressWarnings("unchecked")
    public <T extends MObject> T create(Class<T> metaclass, MObject parent, String depName) {
        MObject newObj = this.smFactory.createObject(this.metamodel.getMClass(metaclass), this.repoSupport.getRepository(parent));
        ((SmObjectImpl) parent).appendDepVal((SmDependency) parent.getMClass().getDependency(depName), (SmObjectImpl) newObj);
        return (T) newObj;
    }

    /**
     * Create an instance of 'metaclass'. The new object will belong to the same repository as the 'referent' object. The 'referent'
     * object is NOT the composition owner of the created object.
     * @param <T>
     * the metaclass interface of the object to create.
     * @param metaclass the metaclass of the object to create.
     * @param referent the referent object
     * @
     * @return the created object
     */
    @objid ("7f3a962c-784f-4cf7-93c5-647f64d6f2e9")
    @SuppressWarnings("unchecked")
    public <T extends MObject> T create(Class<T> metaclass, MObject referent) {
        return (T) this.smFactory.createObject(this.metamodel.getMClass(metaclass), this.repoSupport.getRepository(referent));
    }

}
