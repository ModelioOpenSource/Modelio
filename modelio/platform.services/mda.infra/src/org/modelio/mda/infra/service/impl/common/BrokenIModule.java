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

import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
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
import org.modelio.api.module.lifecycle.IModuleLifeCycleHandler;
import org.modelio.gproject.module.GModule;
import org.modelio.mda.infra.plugin.MdaInfra;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.vbasic.version.Version;

/**
 * {@link IModule} Implementation for broken modules.
 * 
 * @author cmarin
 */
@objid ("c5296d3c-bad5-49e6-bfa7-069c449b30df")
public class BrokenIModule implements IModule {
    @objid ("f100ea81-b195-456c-ab3e-e92bceb0df49")
    private Throwable downError;

    @objid ("8aed89e6-1120-4617-921f-054cfb75bd15")
    private GModule gModule;

    @objid ("b3120521-4239-436e-a751-ae2494514224")
    private ModuleComponent moduleComponent;

    @objid ("3777f9ae-92e2-4715-bbe8-40b5ee6bddc9")
    private IModuleUserConfiguration moduleConfiguration;

    @objid ("94b72ae8-302c-4c7c-a6d4-27fe5d0e3a27")
    private Image moduleImage;

    /**
     * @param gmodule the GModule
     * @param userConfiguration the module configuration
     * @param downError the broken cause
     */
    @objid ("1b98cff1-630d-4ae2-a93d-b75d2dcf20d5")
    public BrokenIModule(GModule gmodule, IModuleUserConfiguration userConfiguration, Throwable downError) {
        this.gModule = gmodule;
        this.moduleComponent = gmodule.getModuleElement();
        this.moduleConfiguration = userConfiguration;
        this.downError = downError;
        
        loadModuleImage();
    }

    @objid ("d83e8454-ccdb-47b2-9da0-44721c7a240c")
    @Override
    public String getDescription() {
        final StringWriter sw = new StringWriter();
        final PrintWriter sb = new PrintWriter(sw);
        
        Throwable cause = this.downError;
        if (cause != null) {
            String scause = cause.getLocalizedMessage();
            sb.println(MdaInfra.I18N.getMessage("BrokenModule.desc", getName(),
                    getVersion(), scause));
        
            sb.println(MdaInfra.I18N.getMessage("BrokenModule.cause"));
            sb.println();
            cause.printStackTrace(sb);
        }
        return sw.toString();
    }

    @objid ("5ea8ccb2-ea2f-4a23-beea-55c9501b0571")
    @Override
    public Image getImage(Stereotype stereotype, ImageType type) {
        return null;
    }

    @objid ("ac03ae5e-2387-4404-9917-9a6a559542c4")
    @Override
    public Image getImage(Profile profile, ImageType imageType) {
        return null;
    }

    @objid ("adf58748-d93b-4343-b0e3-595d4cda0162")
    @Override
    public String getLabel() {
        return this.gModule.getName();
    }

    @objid ("59dab94c-273e-4fa1-a674-33b983cb339d")
    @Override
    public IModuleLifeCycleHandler getLifeCycleHandler() {
        return new BrokenModuleLifecycleHandler(this, this.downError);
    }

    @objid ("e73cfaff-4731-435d-a606-6e6650d9304e")
    @Override
    public org.modelio.api.module.context.IModuleContext getModuleContext() {
        return new BrokenModuleContext();
    }

    @objid ("682a6bdf-374a-4878-a6e9-e4b4e413ea1e")
    @Override
    public final Image getModuleImage() {
        return this.moduleImage;
    }

    @objid ("250f82f9-0716-404c-81b8-6eb339b4a355")
    @Override
    public String getModuleImagePath() {
        return null;
    }

    @objid ("1626445a-9412-44e6-8623-92dad0dadab7")
    @Override
    public String getName() {
        return this.gModule.getName();
    }

    @objid ("2b6857ab-6cfa-455f-b43b-d7c384729932")
    @Override
    public IPeerModule getPeerModule() {
        return new BrokenPeerModule(getName(), getVersion(), null);
    }

    @objid ("078c567f-5aa8-4293-b99f-cfb9a37b6cfa")
    @Override
    public Version getRequiredModelioVersion() {
        // ModuleComponent is not mounted, unable to compute a required version
        return null;
    }

    @objid ("fa838757-98e0-429a-ae1d-ca099e538c21")
    @Override
    public Version getVersion() {
        return this.gModule.getVersion();
    }

    @objid ("5fd03019-aec3-46b2-8d24-ed34a15b69f9")
    @Override
    public final void initModulecontext(IModuleContext moduleContext) {
        // Nothing to do. Broken module has no ModuleContext.
    }

    @objid ("9ea7fb9f-632b-4c6b-ae17-701fcebfa4cd")
    private void loadModuleImage() {
        try {
            String relativePath = getModuleImagePath();
            if (relativePath != null) {
                final Path moduleDirectory = getModuleContext().getConfiguration()
                        .getModuleResourcesPath();
                Path imageFile = moduleDirectory.resolve(relativePath
                        .substring(1));
        
                if (Files.isRegularFile(imageFile)) {
                    this.moduleImage = new Image(null, imageFile.toFile()
                            .getAbsolutePath());
                }
            }
        } catch (Exception e) {
            MdaInfra.LOG.error(e.getMessage());
        }
    }

    @objid ("d559bc44-c49b-40dd-b5b3-7b68355e9e37")
    private final class BrokenModuleContext implements IModuleContext {
        @objid ("61b6f1c6-be33-49f4-92db-73163b4793ad")
        @Override
        public void setModule(IModule iModule) {
            // Nothing to do
        }

        @objid ("5c2a7e5c-4973-4697-9207-e1731ff62706")
        @Override
        public IProjectStructure getProjectStructure() {
            return null;
        }

        @objid ("e831bcf7-43ad-43db-acde-dba246ee120e")
        @Override
        public IModuleAPIConfiguration getPeerConfiguration() {
            return null;
        }

        @objid ("2983b246-063b-4695-bb68-d3da999ab1e6")
        @Override
        public IModelioServices getModelioServices() {
            return null;
        }

        @objid ("cd37b89a-1016-4a04-8b24-3eb85ab18b93")
        @Override
        public IModelioContext getModelioContext() {
            return null;
        }

        @objid ("52764a78-ca73-4f69-8d49-68b17f7ddb46")
        @Override
        public ILogService getLogService() {
            return null;
        }

        @objid ("e781f884-3ebe-4c97-8af2-5d961303db5f")
        @Override
        public I18nSupport getI18nSupport() {
            return FallbackModuleI18n.instance;
        }

        @objid ("1b76d112-59b7-40a0-a285-80ba001b24ed")
        @Override
        public IModuleUserConfiguration getConfiguration() {
            return BrokenIModule.this.moduleConfiguration;
        }

        @objid ("ac9ff989-6b9d-4f28-9943-176d0926a621")
        @Override
        public ScriptEngine getJythonEngine() {
            return null;
        }

        @objid ("4d317503-a494-4b46-bf3a-0a36ff9789d2")
        @Override
        public ModuleComponent getModel() {
            return BrokenIModule.this.moduleComponent;
        }

        @objid ("4f69f3b5-3966-451f-b04f-dffa09f96f09")
        @Override
        public IModelingSession getModelingSession() {
            // No available session
            return null;
        }

    }

}
