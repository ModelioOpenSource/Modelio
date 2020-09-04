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

package org.modelio.vcore.model.api;

import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;
import com.modeliosoft.modelio.javadesigner.annotations.mdl;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.model.impl.CompositionRootGetter;
import org.modelio.vcore.model.impl.ElementConfigurator;
import org.modelio.vcore.model.impl.ElementNamer;
import org.modelio.vcore.model.impl.ModelFactory;
import org.modelio.vcore.model.impl.UmlFragmentContentInitializer;
import org.modelio.vcore.model.spi.mtools.IAuthTool;
import org.modelio.vcore.model.spi.mtools.IModelTool;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Aggregation and access point for all model tools.
 * <p>
 * Some of them are accessible without a {@link GProject}, others need to call {@link #get(GProject)} first.
 * <p>
 * <h2>Changelog</h2>
 * <ul>
 * <li>
 * <h3>Modelio 3.6</h3> Before Modelio 3.6 all methods were static. Now some of them need to get a {@link MTools} instance from a {@link ICoreSession} (or a {@link MObject} ). This class now regroups all accesses to factories and other model tools.</li>
 * </ul>
 * 
 * @since < 3.3
 */
@objid ("00193be2-d357-1097-bcec-001ec947cd2a")
public class MTools {
    @objid ("91d96560-306f-46ed-b362-872ee9c55d52")
    private static IAuthTool authTool;

    @mdl.prop
    @objid ("7018434a-7149-4e44-b20a-c190268257be")
    private final IElementConfiguratorService configurator;

    @mdl.propgetter
    public IElementConfiguratorService getConfigurator() {
        // Automatically generated method. Please do not modify this code.
        return this.configurator;
    }

    @objid ("b63e4d3c-640c-4ba9-9295-26cd09ee858d")
    private static Map<ICoreSession, MTools> instances = new WeakHashMap<>();

    /**
     * The model factories service.
     * <p>
     * Looks like Abstract factory design pattern.
     */
    @objid ("7605760b-499a-4802-9b02-06e8021ae53a")
    private IModelFactoryService modelFactory;

    @objid ("0db9ca95-89ab-4c32-9485-fe94fe01b2fb")
    private static IModelTool modelTool;

    @mdl.prop
    @objid ("975cb1da-d241-4790-b51e-831e5b4f8451")
    private final IElementNamerService namer;

    @mdl.propgetter
    public IElementNamerService getNamer() {
        // Automatically generated method. Please do not modify this code.
        return this.namer;
    }

    @mdl.prop
    @objid ("2cae1294-df9a-44b4-af8e-16209deefb14")
    private final IRepositoryContentInitializerService populator;

    @mdl.propgetter
    public IRepositoryContentInitializerService getPopulator() {
        // Automatically generated method. Please do not modify this code.
        return this.populator;
    }

    @mdl.prop
    @objid ("b665e985-19b1-415e-b1db-ae56b94cb914")
    private final IRepositoryRootGetterService rootGetter;

    @mdl.propgetter
    public IRepositoryRootGetterService getRootGetter() {
        // Automatically generated method. Please do not modify this code.
        return this.rootGetter;
    }

    /**
     * Get the model tools from a model object.
     * @param obj a model object
     * @return the tools for the project of this model object.
     */
    @objid ("a08c845c-d46d-4fbe-b779-c15535048afd")
    public static MTools get(MObject obj) {
        return get(CoreSession.getSession(obj));
    }

    /**
     * Get the model tools for a project.
     * @param session an opened core session.
     * @return the tools for this project.
     */
    @objid ("1b75010b-b865-45dd-b3f4-323cf057fcc0")
    public static MTools get(ICoreSession session) {
        Objects.requireNonNull(session);
        MTools ret = MTools.instances.get(session);
        if (ret == null) {
            synchronized (MTools.instances) {
                ret = MTools.instances.get(session);
                if (ret == null) {
                    ret = new MTools(session);
                    MTools.instances.put(session, ret);
                }
            }
        }
        return ret;
    }

    /**
     * @return the authorization testing tool.
     */
    @objid ("00599f98-d3c9-1097-bcec-001ec947cd2a")
    public static IAuthTool getAuthTool() {
        return MTools.authTool;
    }

    /**
     * Get the service used to get a model factory.
     * @return the model factories service.
     * @since 3.6
     */
    @objid ("b4cd8665-ad7b-4b94-b683-28212de7f3c4")
    public IModelFactoryService getModelFactories() {
        return this.modelFactory;
    }

    /**
     * Get the service used to get a model factory.
     * @return the model factories service.
     * @deprecated renamed to {@link #getModelFactories()} since 3.6 .
     */
    @objid ("498266a4-f00a-4f14-bba1-3e6911cab736")
    @Deprecated
    public IModelFactoryService getModelFactory() {
        return this.modelFactory;
    }

    /**
     * Get a specific model factory.
     * <p>
     * This is a convenience method replacing <code>{@link #getModelFactories()}.{@link IModelFactoryService#getFactory(Class) getFactory(Class<? extends IModelFactory>)}</code>
     * @param factoryCls the model factory interface class.
     * @return the matching model factory
     * @throws java.lang.IllegalArgumentException if there is no model factory implementing the class or interface.
     */
    @objid ("945d9d46-2ed5-429e-908f-c7998cd9ddd9")
    public <T extends IModelFactory> T getModelFactory(java.lang.Class<T> factoryCls) throws IllegalArgumentException {
        return this.modelFactory.getFactory(factoryCls);
    }

    /**
     * @return the copy, clone and move tool.
     */
    @objid ("0059844a-d3c9-1097-bcec-001ec947cd2a")
    public static IModelTool getModelTool() {
        return MTools.modelTool;
    }

    /**
     * Initialize {@link MTools} so that {@link #getModelTool()} and {@link #getAuthTool()} work.
     * <p>
     * This method should be called by GProject on first open.
     * @param modelTool the model tool
     * @param authTool the auth tool
     */
    @objid ("6253215a-0830-452a-a165-adc462c1b76e")
    public static synchronized void initializeMTools(IModelTool modelTool, IAuthTool authTool) {
        // Note : this is an ugly way to initialize these but both objects currently need the metamodel.
        // This method should be called by GProject on first open.
        
        if (MTools.authTool != null || MTools.modelTool != null) {
            throw new IllegalStateException();
        }
        
        Objects.requireNonNull(modelTool);
        Objects.requireNonNull(authTool);
        
        MTools.authTool = authTool;
        MTools.modelTool = modelTool;
    }

    /**
     * Private constructor.
     * <p>
     * Builds all project related tools.
     * @param proj the related project
     */
    @objid ("39f94803-cd04-479d-8745-c361c0d011da")
    private MTools(ICoreSession proj) {
        this.namer = new ElementNamer();
        this.populator = new UmlFragmentContentInitializer();
        this.configurator = new ElementConfigurator();
        this.rootGetter = new CompositionRootGetter(proj);
        this.modelFactory = new ModelFactory(proj);
    }

}
