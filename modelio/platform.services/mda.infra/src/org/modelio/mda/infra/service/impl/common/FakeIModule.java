/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.mda.infra.service.impl.common;

import java.util.Map;
import java.util.ResourceBundle;
import javax.script.ScriptEngine;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.api.modelio.IModelioContext;
import org.modelio.api.modelio.IModelioServices;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.module.IModule;
import org.modelio.api.module.IPeerModule;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.api.module.context.configuration.IModuleAPIConfiguration;
import org.modelio.api.module.context.configuration.IModuleUserConfiguration;
import org.modelio.api.module.context.i18n.I18nSupport;
import org.modelio.api.module.context.log.ILogService;
import org.modelio.api.module.context.project.IProjectStructure;
import org.modelio.api.module.license.ILicenseInfos;
import org.modelio.api.module.lifecycle.IModuleLifeCycleHandler;
import org.modelio.api.module.lifecycle.ModuleException;
import org.modelio.api.module.parameter.IParameterEditionModel;
import org.modelio.gproject.module.GModule;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.vbasic.version.Version;

/**
 * This class represents special modules that just contains extensions or not
 * yet loaded modules.
 * <p>
 * LocalModule was a FakeModule.
 */
@objid ("b317e521-f11c-11e1-af52-001ec947c8cc")
public class FakeIModule implements IModule {
    @objid ("df9b4dec-7ba5-4f81-bbcc-4d657141a38c")
    private GModule gmodule;

    @objid ("b317e523-f11c-11e1-af52-001ec947c8cc")
    private IModuleLifeCycleHandler lifecyleHandler;

    @objid ("d57c21c6-6536-48a0-837f-ff4a3a2d75ad")
    private ResourceBundle manifestBundle;

    @objid ("b317e528-f11c-11e1-af52-001ec947c8cc")
    private ModuleComponent moduleComponent;

    @objid ("b317e525-f11c-11e1-af52-001ec947c8cc")
    private IModuleUserConfiguration moduleConfiguration;

    @objid ("b317e526-f11c-11e1-af52-001ec947c8cc")
    protected IParameterEditionModel parameterEditionModel;

    @objid ("b317e524-f11c-11e1-af52-001ec947c8cc")
    private IPeerModule peerModule;

    /**
     * Instantiate a fake module.
     * @param modelingSession a modeling session.
     * 
     * @param gmodule the low level module.
     * @param moduleUserConfiguration the user version of the module configuration
     * @param moduleApiConfiguration the api version of the module configuration
     */
    @objid ("b317e52b-f11c-11e1-af52-001ec947c8cc")
    public FakeIModule(final GModule gmodule, final IModuleUserConfiguration moduleUserConfiguration, final IModuleAPIConfiguration moduleApiConfiguration) {
        this.gmodule = gmodule;
        this.moduleComponent = gmodule.getModuleElement();
        this.moduleConfiguration = moduleUserConfiguration;
        this.lifecyleHandler = new IModuleLifeCycleHandler() {
        
            @Override
            public void upgrade(final Version oldVersion,
                    final Map<String, String> oldParameters) throws ModuleException {
                // Empty
            }
        
            @Override
            public void unselect() throws ModuleException {
                // Empty
            }
        
            @Override
            public void stop() throws ModuleException {
                // Empty
            }
        
            @Override
            public boolean start() throws ModuleException {
                return false;
            }
        
            @Override
            public boolean select() throws ModuleException {
                return false;
            }
        };
        
        this.peerModule = new DefaultPeerModule(this.moduleComponent,
                moduleApiConfiguration);
    }

    /**
     * Used to return the module description.
     * 
     * @return The module description
     */
    @objid ("b317e540-f11c-11e1-af52-001ec947c8cc")
    @Override
    public String getDescription() {
        return "";
    }

    @objid ("301a2420-ba51-4e56-9bc0-8ff331acd0be")
    @Override
    public Image getImage(final Stereotype stereotype, final ImageType type) {
        return null;
    }

    @objid ("f65c24e8-1a74-400d-9e3d-c87821f680ea")
    @Override
    public Image getImage(final Profile profile, final ImageType imageType) {
        return null;
    }

    /**
     * Get the module label that is displayed in dialog boxes and other GUIU
     * parts.
     * 
     * @return The module label.
     */
    @objid ("b317e559-f11c-11e1-af52-001ec947c8cc")
    @Override
    public String getLabel() {
        return getName();
    }

    @objid ("4e7caa03-87cc-47b7-bb2e-0bd4564efc72")
    @Override
    public ILicenseInfos getLicenseInfos() {
        return new UndefinedLicenseInfos();
    }

    @objid ("46651944-e116-45ae-bb18-79bc5d48592a")
    @Override
    public IModuleLifeCycleHandler getLifeCycleHandler() {
        return this.lifecyleHandler;
    }

    @objid ("3b08b192-8d1f-4141-9896-896954eae749")
    @Override
    public org.modelio.api.module.context.IModuleContext getModuleContext() {
        return new FakeModuleContext();
    }

    /**
     * Always returns null.
     * 
     * @return null.
     */
    @objid ("b317e567-f11c-11e1-af52-001ec947c8cc")
    @Override
    public Image getModuleImage() {
        return null;
    }

    @objid ("735d5918-d254-4f33-b24a-6bb51badf83d")
    @Override
    public String getModuleImagePath() {
        return null;
    }

    /**
     * Used to return the module name.
     * <p>
     * <p>
     * The module name corresponds to the name of the module, as defined in the
     * <i>MDA Designer<i> tool.
     * 
     * @return The module name
     */
    @objid ("b31a4782-f11c-11e1-af52-001ec947c8cc")
    @Override
    public String getName() {
        return this.gmodule.getName();
    }

    /**
     * Returns the peer module, connected to this module.
     * <p>
     * The peer module represents the public services of this current module.
     * 
     * @return The associated peer module
     */
    @objid ("b31a478e-f11c-11e1-af52-001ec947c8cc")
    @Override
    public IPeerModule getPeerModule() {
        return this.peerModule;
    }

    @objid ("d81947c8-6373-4c32-a6d3-6f56059ed19f")
    @Override
    public Version getRequiredModelioVersion() {
        return new Version(0, 0, 0);
    }

    /**
     * Used to return the module version.
     * 
     * @return The module version
     */
    @objid ("b31a47a8-f11c-11e1-af52-001ec947c8cc")
    @Override
    public Version getVersion() {
        return this.gmodule.getVersion();
    }

    /**
     * Method automatically called just after the creation of the module.
     * <p>
     * <p>
     * The module is automatically instantiated at the beginning of the mda
     * lifecycle and the constructor implementation is not accessible to the
     * module developer.
     * <p>
     * <p>
     * The <code>init</code> method allows the developer to execute the desired
     * initialization code at this step. For example, this is the perfect place
     * to register any IExpertise this module provides.
     * <p>
     * <p>
     * This method should never be called by the developer because it is already
     * invoked by the tool.
     */
    @objid ("b31a47b7-f11c-11e1-af52-001ec947c8cc")
    @Override
    public void init() {
        // Nothing to do.
    }

    @objid ("ae9554d9-3eba-4686-938c-99f707ae1107")
    @Override
    public final void initModulecontext(final IModuleContext moduleContext) {
        // Nothing to do. Fake module has no ModuleContext.
    }

    /**
     * Method automatically called just before the disposal of the module.
     * <p>
     * The <code>uninit</code> method allows the developer to execute the
     * desired un-initialization code at this step. For example, if an IExpertise
     * have been registered in the {@link #init()} method, this method is the
     * perfect place to remove them.
     * <p>
     * This method should never be called by the developer because it is already
     * invoked by the tool.
     */
    @objid ("b31ca9e1-f11c-11e1-af52-001ec947c8cc")
    @Override
    public void uninit() {
        // Nothing to do.
    }

    @objid ("f224dd58-412b-40a0-80cf-959b3ab3d2d8")
    private final class FakeModuleContext implements IModuleContext {
        @objid ("2c36b867-3179-43de-89fc-c6a3e8c1c152")
        @Override
        public void setModule(final IModule iModule) {
            // Nothing to do
        }

        @objid ("b49f70c6-a629-477d-88b0-5bbf460f23be")
        @Override
        public IProjectStructure getProjectStructure() {
            return null;
        }

        @objid ("044bb93d-d56e-42fb-aeef-09b9f342b7f3")
        @Override
        public IModuleAPIConfiguration getPeerConfiguration() {
            return null;
        }

        @objid ("58289b5a-bdc9-41da-b410-615108a24903")
        @Override
        public IModelioServices getModelioServices() {
            return null;
        }

        @objid ("32cd7c72-685f-4ef4-ada4-e3d61b48e5ef")
        @Override
        public IModelioContext getModelioContext() {
            return null;
        }

        @objid ("f2b1457c-b32b-4681-9cd4-cf3d2b0e666b")
        @Override
        public ILogService getLogService() {
            return null;
        }

        @objid ("9e15507a-31da-4318-b908-deb8007077fd")
        @Override
        public I18nSupport getI18nSupport() {
            return FallbackModuleI18n.instance;
        }

        @objid ("bed0b496-26ed-48af-a412-486f7d29c165")
        @Override
        public IModuleUserConfiguration getConfiguration() {
            return FakeIModule.this.moduleConfiguration;
        }

        @objid ("9a669389-a305-4101-9694-a9d5e6264b79")
        @Override
        public ScriptEngine getJythonEngine() {
            return null;
        }

        @objid ("2a7e0771-34ac-4217-a40e-81f5ee0a5757")
        @Override
        public ModuleComponent getModel() {
            return FakeIModule.this.moduleComponent;
        }

        @objid ("e7e92725-ff6c-4c89-8201-41e6ed2339e8")
        @Override
        public IModelingSession getModelingSession() {
            // No available session
            return null;
        }

    }

}
