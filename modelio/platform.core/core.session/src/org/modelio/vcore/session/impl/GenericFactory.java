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

package org.modelio.vcore.session.impl;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.session.api.repository.IRepositorySupport;
import org.modelio.vcore.session.plugin.VCoreSession;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;

/**
 * Generic object factory.
 */
@objid ("0032d7f0-4153-1ffc-8433-001ec947cd2a")
public final class GenericFactory {
    @objid ("ea04edd3-b72b-4971-8b9f-4cd293337801")
    private SmMetamodel metamodel;

    @objid ("8e6ffa65-4469-11e2-91c9-001ec947ccaf")
    private IRepositorySupport repoSupport;

    @objid ("a204b0c9-caa4-11e1-8052-001ec947ccaf")
    private SmFactory smFactory;

    /**
     * Create an instance of 'metaclass'.
     * <p>
     * The new object will belong to the same repository as the 'referent' object. The 'referent' object is NOT the composition
     * owner of the created object.
     * @param metaclass the metaclass of the object to create.
     * @param referent the referent object
     * @return the created object
     */
    @objid ("0032dd36-4153-1ffc-8433-001ec947cd2a")
    public MObject create(MClass metaclass, MObject referent) {
        return this.smFactory.createObject((SmClass) metaclass, this.repoSupport.getRepository(referent));
    }

    /**
     * Create an instance of 'metaclass'.
     * <p>
     * The new object will belong to the given repository .
     * @param metaclass the metaclass of the object to create.
     * @param repository the repository
     * @return the created object
     */
    @objid ("0032e0f6-4153-1ffc-8433-001ec947cd2a")
    public MObject create(MClass metaclass, IRepository repository) {
        return this.smFactory.createObject((SmClass) metaclass, repository);
    }

    /**
     * Create an instance of 'metaclass'. The new object will belong to the same repository as the 'referent' object. The 'referent'
     * object is NOT the composition owner of the created object.
     * @param metaclass the metaclass name of the object to create.
     * @param referent the referent object
     * @return the created object
     */
    @objid ("0032e420-4153-1ffc-8433-001ec947cd2a")
    public MObject create(String metaclass, MObject referent) {
        SmClass cls = getSmClass(metaclass);
        return this.smFactory.createObject(cls, this.repoSupport.getRepository(referent));
    }

    /**
     * Create an instance of 'metaclass'. The new object will belong to the given repository.
     * @param metaclass the metaclass name of the object to create.
     * @param repository the repository that will contain the object.
     * @return the created object
     */
    @objid ("0032ea2e-4153-1ffc-8433-001ec947cd2a")
    public MObject create(String metaclass, IRepository repository) {
        SmClass cls = getSmClass(metaclass);
        
        SmObjectImpl ret = this.smFactory.createObject(cls, repository);
        return ret;
    }

    /**
     * Create an instance of 'metaclass' and define 'parent' as its composition owner using the dependency 'dep'.
     * <p>
     * The new object will belong to the same repository as the 'parent' object.
     * @param metaclass the metaclass name
     * @param parent the composition owner
     * @param depName the name of the metamodel relation from the owner to the created element.
     * @return the created object
     */
    @objid ("8e6ffa6c-4469-11e2-91c9-001ec947ccaf")
    public MObject create(String metaclass, MObject parent, String depName) {
        SmClass cls = getSmClass(metaclass);
        
        SmDependency dep = ((SmObjectImpl) parent).getClassOf().getDependencyDef(depName);
        if (dep == null) {
            throw new IllegalArgumentException(depName + " is not a metamodel relation on '" + metaclass + "'.");
        }
        
        SmObjectImpl ret = this.smFactory.createObject(cls, this.repoSupport.getRepository(parent));
        
        ((SmObjectImpl) parent).appendDepVal(dep, ret);
        return ret;
    }

    /**
     * Create an instance of 'metaclass' and define 'parent' as its composition owner using the dependency 'depName'.
     * The new object will belong to the same repository as the 'parent' object.
     * @param metaclass a metamodel class java interface
     * @param parent the new element owner
     * @param depName the metamodel relation from the owner to the created element.
     * @return the created object
     */
    @objid ("92ebb082-ccf5-11e1-b3c5-001ec947ccaf")
    public <T extends MObject> T create(Class<T> metaclass, MObject parent, String depName) {
        @SuppressWarnings("unchecked")
        T newObj = (T) create(this.metamodel.getMClass(metaclass), parent, parent.getMClass().getDependency(depName));
        return newObj;
    }

    /**
     * Create an instance of 'metaclass' and define 'parent' as its composition owner using the dependency 'dep'. The new object
     * will belong to the same repository as the 'parent' object.
     * @param metaclass a metamodel class
     * @param parent the new element owner
     * @param dep the metamodel relation from the owner to the created element.
     * @return the created object
     */
    @objid ("440fead1-41e4-44ea-a2b8-6a34fb15418e")
    public MObject create(MClass metaclass, MObject parent, MDependency dep) {
        MObject ret = create(metaclass, parent);
        ((SmObjectImpl) parent).appendDepVal((SmDependency) dep, (SmObjectImpl) ret);
        return ret;
    }

    /**
     * Create an instance of 'metaclass'. The new object will belong to the given repository.
     * @param <T>
     * the metaclass interface of the object to create.
     * @param metaclass the metaclass of the object to create.
     * @param repository the repository where the model object will be stored.
     * @return the created object
     */
    @objid ("5da82a6c-b753-4fad-b2d4-e8e15c34096b")
    @SuppressWarnings("unchecked")
    public <T extends MObject> T create(Class<T> metaclass, IRepository repository) {
        return (T) this.smFactory.createObject(getMetaclass(metaclass), repository);
    }

    /**
     * Create an instance of 'metaclass'. The new object will belong to the same repository as the 'referent' object. The 'referent'
     * object is NOT the composition owner of the created object.
     * @param <T>
     * the metaclass interface of the object to create.
     * @param metaclass the metaclass of the object to create.
     * @param referent the referent object
     * @return the created object
     */
    @objid ("8c4d685c-0c7c-4086-86db-40dc3d54b122")
    @SuppressWarnings("unchecked")
    public <T extends MObject> T create(Class<T> metaclass, MObject referent) {
        return (T) this.smFactory.createObject(getMetaclass(metaclass), this.repoSupport.getRepository(referent));
    }

    /**
     * Initialize a generic factory.
     * @param smFactory a core factory.
     * @param repoSupport a repository support.
     */
    @objid ("a204b0ca-caa4-11e1-8052-001ec947ccaf")
    GenericFactory(SmFactory smFactory, IRepositorySupport repoSupport, SmMetamodel metamodel) {
        this.repoSupport = repoSupport;
        this.smFactory = smFactory;
        this.metamodel = metamodel;
    }

    @objid ("f923f3c4-b15a-4fb5-b10b-d58dce4e4a42")
    private <T extends MObject> SmClass getMetaclass(Class<T> metaclass) throws IllegalArgumentException {
        SmClass cls = this.metamodel.getMClass(metaclass);
        if (cls == null) {
            throw new IllegalArgumentException(metaclass.getSimpleName() + " is not a metamodel class.");
        }
        if (cls.isFake()) {
            throw new IllegalArgumentException(VCoreSession.getMessage("GenericFactory.fakeMetaclass", cls.getQualifiedName()));
        }
        return cls;
    }

    @objid ("977d6ced-95be-4a5d-a578-0b8f1f4603a5")
    private SmClass getSmClass(String metaclass) throws IllegalArgumentException {
        SmClass cls = this.metamodel.getMClass(metaclass);
        if (cls == null) {
            throw new IllegalArgumentException(metaclass + " is not a metamodel class.");
        }
        if (cls.isFake()) {
            throw new IllegalArgumentException(VCoreSession.getMessage("GenericFactory.fakeMetaclass", cls.getQualifiedName()));
        }
        return cls;
    }

}
