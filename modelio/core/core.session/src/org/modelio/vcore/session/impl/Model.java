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
package org.modelio.vcore.session.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.session.UnknownMetaclassException;
import org.modelio.vcore.session.api.model.IMObjectFilter;
import org.modelio.vcore.session.api.model.IModel;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.session.api.repository.IRepositorySupport;
import org.modelio.vcore.session.impl.cache.CacheManager;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;
import org.modelio.vcore.smkernel.meta.SmClass;

/**
 * Implementation of {@link IModel}.
 */
@objid ("008c5258-5f00-10c8-842f-001ec947cd2a")
public class Model implements IModel {
    @objid ("008c5c1c-5f00-10c8-842f-001ec947cd2a")
    private GenericFactory genericFactory;

    @objid ("008c706c-5f00-10c8-842f-001ec947cd2a")
    private final CacheManager cacheManager;

    @objid ("008c8052-5f00-10c8-842f-001ec947cd2a")
    private final IRepositorySupport repositorySupport;

    @objid ("feaf1ad9-3ecf-457d-9f9f-f1e7a08f0f3d")
    private MMetamodel metamodel;

    @objid ("008c8f7a-5f00-10c8-842f-001ec947cd2a")
     Model(CacheManager cacheManager, IRepositorySupport repositorySupport, MMetamodel metamodel) {
        this.cacheManager = cacheManager;
        this.repositorySupport = repositorySupport;
        this.metamodel = metamodel;
        
    }

    @objid ("003a9062-61a6-10c8-842f-001ec947cd2a")
    @SuppressWarnings("unchecked")
    @Override
    public <T extends MObject> Collection<T> findByAtt(Class<T> metaclass, final String att, Object val) {
        MClass cls = this.metamodel.getMClass(metaclass);
        return (Collection<T>) findByAtt(cls, att, val);
    }

    @objid ("008d50c2-5f00-10c8-842f-001ec947cd2a")
    @Override
    public Collection<? extends MObject> findByAtt(MClass cls, final String att, Object val) {
        return findByAtt(cls, true, att, val);
    }

    @objid ("003a3cb6-61a6-10c8-842f-001ec947cd2a")
    @Override
    public <T extends MObject> Collection<T> findByClass(Class<T> metaclass) {
        return findByClass(metaclass, true);
    }

    @objid ("008d9f50-5f00-10c8-842f-001ec947cd2a")
    @Override
    public Collection<? extends MObject> findByClass(MClass cls, boolean withSubClasses) {
        Set<MObject> results = new HashSet<>();
        this.cacheManager.findByClass(cls, withSubClasses, results);
        
        SmClass smCls = (SmClass) cls;
        for (IRepository base : this.repositorySupport.getRepositories()) {
            results.addAll(base.findByClass(smCls, withSubClasses));
        }
        
        // Exclude deleted objects
        results.removeAll(this.cacheManager.getDeletedObjects());
        return results;
    }

    @objid ("003afa84-61a6-10c8-842f-001ec947cd2a")
    @SuppressWarnings("unchecked")
    @Override
    public <T extends MObject> T findById(Class<T> metaclass, final String siteIdentifier) {
        MClass cls = this.metamodel.getMClass(metaclass);
        return (T) findById(cls, siteIdentifier);
    }

    @objid ("008dd3f8-5f00-10c8-842f-001ec947cd2a")
    @Override
    public SmObjectImpl findById(MClass cls, final String siteIdentifier) {
        SmObjectImpl ret = this.cacheManager.findById(cls, siteIdentifier);
        if (ret == null) {
            SmClass smCls = (SmClass) cls;
            for (IRepository base : this.repositorySupport.getRepositories()) {
                ret = base.findById(smCls, siteIdentifier);
                if (ret != null && ret.isValid()) {
                    return ret;
                }
            }
        }
        return ret;
    }

    @objid ("008e0a44-5f00-10c8-842f-001ec947cd2a")
    @Override
    public MObject findByRef(MRef ref) throws UnknownMetaclassException {
        MClass cls = this.metamodel.getMClass(ref.mc);
        if (cls == null) {
            throw new UnknownMetaclassException(ref.mc);
        }
        return findById(cls, ref.uuid);
    }

    @objid ("008e33a2-5f00-10c8-842f-001ec947cd2a")
    @Override
    public GenericFactory getGenericFactory() {
        // Automatically generated method. Please delete this comment before
        // entering specific code.
        return this.genericFactory;
    }

    @objid ("8e68d369-4469-11e2-91c9-001ec947ccaf")
    void setGenericFactory(GenericFactory genericFactory) {
        this.genericFactory = genericFactory;
    }

    @objid ("503ce30f-7c5a-4241-a177-a244eb315258")
    @Override
    public Collection<? extends MObject> findByAtt(MClass cls, String att, Object val, IMObjectFilter filter) {
        final Collection<? extends MObject> rawResults = findByAtt(cls, true, att, val);
        if (filter != null) {
            List<MObject> filteredResults = new ArrayList<>(rawResults.size());
            for (MObject obj : rawResults) {
                if (filter.accept(obj)) {
                    filteredResults.add(obj);
                }
            }
            return filteredResults;
        } else {
            return rawResults;
        }
        
    }

    @objid ("b82ef254-b434-40a2-a065-444a89a3ee76")
    @Override
    public Collection<? extends MObject> findByClass(MClass cls, IMObjectFilter filter) {
        final Collection<? extends MObject> rawResults = findByClass(cls, true);
        if (filter != null) {
            List<MObject> filteredResults = new ArrayList<>(rawResults.size());
            for (MObject obj : rawResults) {
                if (filter.accept(obj)) {
                    filteredResults.add(obj);
                }
            }
            return filteredResults;
        } else {
            return rawResults;
        }
        
    }

    @objid ("3f9628df-81bd-4c13-858f-b851846ffe91")
    @Override
    public MObject findById(MClass cls, String siteIdentifier, IMObjectFilter filter) {
        final MObject rawResult = findById(cls, siteIdentifier);
        if (rawResult != null) {
            return (filter == null) || filter.accept(rawResult) ? rawResult : null;
        }
        return rawResult;
    }

    @objid ("6cc8a793-b6e1-4456-8230-1d7c3123a03a")
    @Override
    public MObject findByRef(MRef ref, IMObjectFilter filter) throws UnknownMetaclassException {
        final MObject rawResult = findByRef(ref);
        return (filter == null) || filter.accept(rawResult) ? rawResult : null;
    }

    @objid ("e463195f-4c87-448a-b49c-962c2f94ab67")
    @Override
    public <T extends MObject> Collection<T> findByClass(Class<T> metaclass, IMObjectFilter filter) {
        final Collection<T> rawResults = findByClass(metaclass);
        if (filter != null) {
            List<T> filteredResults = new ArrayList<>(rawResults.size());
            for (T obj : rawResults) {
                if (filter.accept(obj)) {
                    filteredResults.add(obj);
                }
            }
            return filteredResults;
        } else {
            return rawResults;
        }
        
    }

    @objid ("6a8bfb6a-3ea1-4529-84d4-d905bffb07a9")
    @Override
    public <T extends MObject> Collection<T> findByAtt(Class<T> metaclass, String att, Object val, IMObjectFilter filter) {
        final Collection<T> rawResults = findByAtt(metaclass, att, val);
        if (filter != null) {
            List<T> filteredResults = new ArrayList<>(rawResults.size());
            for (T obj : rawResults) {
                if (filter.accept(obj)) {
                    filteredResults.add(obj);
                }
            }
            return filteredResults;
        } else {
            return rawResults;
        }
        
    }

    @objid ("9d88f704-17c0-4089-8c16-3c8b5a09e91c")
    @Override
    public <T extends MObject> T findById(Class<T> metaclass, String siteIdentifier, IMObjectFilter filter) {
        final T rawResult = findById(metaclass, siteIdentifier);
        if (rawResult != null) {
            return (filter == null) || filter.accept(rawResult) ? rawResult : null;
        }
        return rawResult;
    }

    @objid ("d77d671d-a596-453d-bc4b-6cfe72a6dca8")
    @Override
    public Collection<? extends MObject> findByAtt(MClass cls, boolean withSubClasses, String att, Object val) {
        Set<MObject> results = new HashSet<>();
        
        this.cacheManager.findByAtt(cls, withSubClasses, att, val, results);
        SmClass smCls = (SmClass) cls;
        
        for (IRepository base : this.repositorySupport.getRepositories()) {
            results.addAll(base.findByAtt(smCls, withSubClasses, att, val));
        }
        
        // Exclude deleted objects
        results.removeAll(this.cacheManager.getDeletedObjects());
        return results;
    }

    @objid ("eabe376f-7803-4657-8f0c-2c444cc34a6b")
    @Override
    @SuppressWarnings("unchecked")
    public <T extends MObject> Collection<T> findByClass(Class<T> metaclass, boolean withSubClasses) {
        MClass cls = this.metamodel.getMClass(metaclass);
        return (Collection<T>) findByClass(cls, withSubClasses);
    }

    @objid ("83727aa5-acfe-4a64-85b1-71d2f1664d19")
    @Override
    public Collection<? extends MObject> findByClass(MClass cls) {
        return findByClass(cls, true);
    }

}
