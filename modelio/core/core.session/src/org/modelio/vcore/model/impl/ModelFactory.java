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

package org.modelio.vcore.model.impl;

import java.util.Map;
import java.util.WeakHashMap;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.model.api.IModelFactory;
import org.modelio.vcore.model.api.IModelFactoryService;
import org.modelio.vcore.model.spi.AbstractModelFactory;
import org.modelio.vcore.model.spi.IModelFactoryProvider;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MMetamodelFragment;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.services.MetamodelExtensionPoint;

/**
 * Modelio model factory entry point.
 * 
 * @author cmarin
 * @since 3.6
 */
@objid ("4e5c4d8a-6040-4651-b8c6-8d432433445c")
public class ModelFactory extends AbstractModelFactory implements IModelFactoryService {
    @objid ("8b78412a-3540-4f49-b59c-95ffe2fb51e3")
    private final Map<MMetamodelFragment, IModelFactory> dispatcher = new WeakHashMap<>(8);

    /**
     * Get the metamodel extension point.
     * <p>
     * Use it to register metamodel extensions.
     * @return the metamodel extension point.
     */
    @objid ("134143b7-6fb7-48af-a4d8-b0a4322f30b5")
    private final MetamodelExtensionPoint<IModelFactoryProvider> metamodelExtensionPoint;

    @objid ("fcbef6cc-f543-40c8-a157-11c321b72120")
    private final ICoreSession session;

    @objid ("1d408021-8aa1-4a3a-a74b-ff601b5a947d")
    @Override
    public MObject createElement(MClass metaclass) {
        return getFactory(metaclass).createElement(metaclass);
    }

    @objid ("75790d60-2cf1-4979-9e6a-e60752bf23ed")
    @Override
    public MObject createElement(MClass metaclass, MObject owner, MDependency dependency) {
        return getFactory(metaclass).createElement(metaclass, owner, dependency);
    }

    @objid ("8dbe6281-dc9f-44ec-88b3-c04255a3f0da")
    public ModelFactory(ICoreSession session) {
        super(session.getMetamodel());
        
        this.session = session;
        this.metamodelExtensionPoint = new MetamodelExtensionPoint<>();
    }

    @objid ("b361d1d8-7bd6-4227-8633-77d99625edf3")
    @Override
    public IModelFactory getFactory(MClass mc) {
        MMetamodelFragment mmf = mc.getOrigin();
        return getFactory(mmf);
    }

    @objid ("eab4c19e-d79b-44e2-ac4f-4253a3470a7d")
    @Override
    public IModelFactory getFactory(MMetamodelFragment mmf) {
        IModelFactory ret = this.dispatcher.get(mmf);
        if (ret == null) {
            // Note : this is not thread safe but currently only one transaction may run at a time 
            IModelFactoryProvider svc = this.metamodelExtensionPoint.getService(mmf);
            if (svc == null)
                throw new IllegalStateException(String.format("No IModelFactoryProvider for '%s' metamodel.", mmf.getName()));
            
            ret = svc.getFactory(this.session);
            if (ret == null)
                throw new NullPointerException(String.format("IModelFactoryProvider for '%s' metamodel does not produce IModelFactoryMmService.", mmf.getName()));
            
            this.dispatcher.put(mmf, ret);
        }
        return ret;
    }

    /**
     * Get the specific model factory implementing the given java class/interface.
     * 
     * @param factoryCls the model factory java interface/
     * @return the found model factory
     * @throws java.lang.IllegalArgumentException if there is no model factory implementing the class or interface.
     */
    @objid ("abe97b9b-437a-4d38-a9ce-6d7a0ea8780e")
    @Override
    @SuppressWarnings("unchecked")
    public <T extends IModelFactory> T getFactory(Class<T> factoryCls) throws IllegalArgumentException {
        for (IModelFactory service : this.dispatcher.values()) {
            if (factoryCls.isInstance(service))
                return (T) service;
        }
        
        for (MMetamodelFragment metamodelFragment : this.metamodel.getFragments()) {
            IModelFactory s = findFactory(metamodelFragment);
            if (s != null && factoryCls.isInstance(s))
                return (T) s;
        }
        
        throw new IllegalArgumentException("No model factory typed "+factoryCls.getName());
    }

    @objid ("23a14d4b-7207-4ad7-8daa-1aa74fec0e01")
    private IModelFactory findFactory(MMetamodelFragment mmf) {
        IModelFactory ret = this.dispatcher.get(mmf);
        if (ret == null) {
            // Note : this is not thread safe but currently only one transaction may run at a time 
            IModelFactoryProvider svc = this.metamodelExtensionPoint.getService(mmf);
            if (svc != null) {
                ret = svc.getFactory(this.session);
                if (ret == null)
                    throw new NullPointerException(String.format("IModelFactoryProvider for '%s' metamodel does not produce IModelFactoryMmService.", mmf.getName()));
        
                this.dispatcher.put(mmf, ret);
            }
        }
        return ret;
    }

    @objid ("eee4439a-b346-49a2-844b-ef1ad3fc02c2")
    @Override
    public MetamodelExtensionPoint<IModelFactoryProvider> getMetamodelExtensionPoint() {
        // Automatically generated method. Please do not modify this code.
        return this.metamodelExtensionPoint;
    }

}
