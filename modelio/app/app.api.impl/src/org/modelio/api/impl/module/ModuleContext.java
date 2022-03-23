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
package org.modelio.api.impl.module;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.ListResourceBundle;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Objects;
import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.script.ScriptEngine;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.modelio.api.impl.app.ModelioContext;
import org.modelio.api.impl.log.LogService;
import org.modelio.api.impl.services.ModelioServices;
import org.modelio.api.modelio.IModelioContext;
import org.modelio.api.modelio.IModelioServices;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.module.IModule;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.api.module.context.configuration.IModuleAPIConfiguration;
import org.modelio.api.module.context.configuration.IModuleUserConfiguration;
import org.modelio.api.module.context.i18n.I18nSupport;
import org.modelio.api.module.context.log.ILogService;
import org.modelio.api.module.context.project.IProjectStructure;
import org.modelio.api.module.script.IScriptService;
import org.modelio.gproject.gproject.GProject;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.ResourceType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.platform.project.services.IProjectService;

@objid ("62afb94f-d400-4e71-a8cc-7f733298d3a6")
public final class ModuleContext implements IModuleContext {
    @objid ("7ab515e2-63d0-4fc6-b276-3695b55efe78")
    private IEclipseContext eclipseContext;

    @objid ("ab8c8c68-46c5-4607-9a33-edcab9701700")
    private final IModuleUserConfiguration configuration;

    /**
     * The I18nSupport implementation
     */
    @objid ("52c27a92-3a89-4721-9e0f-f8c1235af49d")
    private final I18nSupport i18nSupport;

    /**
     * The Jython engine.
     */
    @objid ("38bb8ae7-7224-4bff-a40f-f9bdbc6fb073")
    private javax.script.ScriptEngine jythonEngine = null;

    @objid ("1a85f53d-980d-4450-9c53-1faceadc6690")
    private final LogService logService;

    @objid ("479aedf1-c4a5-4717-b178-2232767c26f8")
    private IModule module;

    @objid ("61b7fff2-ccf8-43cd-a3ac-7522e52acb4a")
    private ModuleComponent moduleComponent;

    @objid ("945b3a1b-83dd-42de-97de-5d30dcfa047a")
    private final IModuleAPIConfiguration peerConfiguration;

    @objid ("c9501288-da8e-456e-aaca-a56ac281b5ec")
    public  ModuleContext(ModuleComponent moduleElement, IModuleUserConfiguration moduleUserConfiguration, IModuleAPIConfiguration moduleApiConfiguration, IEclipseContext eclipseContext) {
        this.moduleComponent = moduleElement;
        this.configuration = moduleUserConfiguration;
        this.peerConfiguration = moduleApiConfiguration;
        this.logService = new LogService(moduleElement != null ? moduleElement.getName() : "");
        this.i18nSupport = new I18nSupportImpl(getManifestBundle(), this.logService);
        this.eclipseContext = eclipseContext;
        
    }

    @objid ("5b9f5073-d8d4-4723-922c-b603d2dd3b81")
    @Override
    public IModuleUserConfiguration getConfiguration() {
        return this.configuration;
    }

    @objid ("a232fed8-3eeb-4248-a93e-a1ecd490d54c")
    @Override
    public I18nSupport getI18nSupport() {
        return this.i18nSupport;
    }

    @objid ("633d7907-ff15-4140-a968-bb5e7b937123")
    @Override
    public ScriptEngine getJythonEngine() {
        Objects.requireNonNull(this.module, "Module should not be null.");
        if (this.jythonEngine == null) {
            IScriptService scriptService = getModelioServices().getService(IScriptService.class);
            this.jythonEngine = scriptService.getScriptEngine(this.module.getClass().getClassLoader());
        
            // preset a few variables
            this.jythonEngine.put("SESSION", getModelingSession());
            this.jythonEngine.put("MODULE", this.module);
            this.jythonEngine.put("modelingSession", getModelingSession());
            this.jythonEngine.put("module", this.module);
            this.jythonEngine.put("moduleContext", this);
        }
        return this.jythonEngine;
    }

    @objid ("e01b4a1a-1c81-4656-9e52-9a27fa91c738")
    @Override
    public ILogService getLogService() {
        return this.logService;
    }

    @objid ("cadc87c7-6c7e-4318-9618-d90e347951cb")
    @Override
    public ModuleComponent getModel() {
        return this.moduleComponent;
    }

    @objid ("9de4bcd2-0c90-405a-a8a9-563a66e07c06")
    @Override
    public IModelingSession getModelingSession() {
        return getModelioServices().getService(IModelingSession.class);
    }

    @objid ("972dc699-0c87-419d-a6bd-f36eb3ee8455")
    @Override
    public IModelioContext getModelioContext() {
        return new ModelioContext(this.eclipseContext.get(IProjectService.class));
    }

    @objid ("eb656f71-87bc-4cc9-9081-488496f0d225")
    @Override
    public final IModelioServices getModelioServices() {
        return ModelioServices.getInstance();
    }

    @objid ("a92e6671-03c2-4c97-9f16-1bcdb714dcd6")
    @Override
    public IModuleAPIConfiguration getPeerConfiguration() {
        return this.peerConfiguration;
    }

    @objid ("74853afd-df91-4d66-87f8-3c1538e799c0")
    @Override
    public IProjectStructure getProjectStructure() {
        return new ProjectStructure(GProject.getProject(this.moduleComponent));
    }

    @objid ("1141bccd-8f7b-4adb-8b98-c5009980a966")
    @Override
    public void setModule(IModule module) {
        this.module = module;
    }

    /**
     * Get the {@link ResourceBundle} corresponding to the localized module.properties file in the module resources.
     * @return the resource bundle
     * @throws MissingResourceException if the file is not found
     */
    @objid ("21f5581f-e179-4598-bc60-ac1f67cfd149")
    private ResourceBundle getManifestBundle() throws MissingResourceException {
        Path moduleResourcesPath = getConfiguration().getModuleResourcesPath();
        if (moduleResourcesPath == null) {
            return new ListResourceBundle() {
                @Override
                protected Object[][] getContents() {
                    return new Object[0][0];
                }
            };
        }
        try (final URLClassLoader cl = new URLClassLoader(
                new URL[] { moduleResourcesPath.toUri().toURL() })) {
            // Create a class loader initialized with the 'manifest' directory
            // in module resource, then give it to ResourceBundle.getBundle(...)
            return ResourceBundle.getBundle("module", Locale.getDefault(), cl);
        } catch (MalformedURLException e) {
            throw new MissingResourceException(e.getLocalizedMessage(),
                    "module", "");
        } catch (MissingResourceException e) {
            MissingResourceException e2 = new MissingResourceException(
                    e.getLocalizedMessage() + " in '"
                            + moduleResourcesPath.toUri().toString() + "'",
                    e.getClassName(), e.getKey());
            e2.initCause(e);
            getLogService().error(e.getLocalizedMessage());
            throw e2;
        } catch (IOException e) {
            getLogService().error(e);
        }
        return null;
    }

    @objid ("9f03ba65-bb3c-483d-a771-1f87d7efa99b")
    private static class I18nSupportImpl implements I18nSupport {
        @objid ("66660170-280a-4be4-8cc2-766dfe1a4d1e")
        private final ResourceBundle I18N;

        @objid ("0a49591e-9c3c-4ce0-874f-6139fe94a9b9")
        private final ILogService logService;

        @objid ("0018e7b7-35e2-4eba-bbcb-920221421f0b")
        public  I18nSupportImpl(ResourceBundle b, ILogService logService) {
            this.I18N = b;
            this.logService = logService;
            
        }

        @objid ("a8be668e-3b67-494c-81a9-aedf35453e57")
        @Override
        public String getDescription(PropertyDefinition pdef) {
            PropertyTableDefinition table = pdef.getOwner();
            return getString("%propertydefinition."
                                + (table != null ? table.getName() : "") + "."
                                + pdef.getName() + ".description");
            
        }

        @objid ("da3d69b3-2569-4ebf-96dc-bc535aa60e88")
        @Override
        public ResourceBundle getI18N() {
            return this.I18N;
        }

        @objid ("d7e1affe-0036-40a7-8b8f-14f6ed23a967")
        @Override
        public String getLabel(Stereotype stereotype) {
            return getString(stereotype.getLabelKey());
        }

        @objid ("57d06897-9c98-43cb-a364-91c8435a9ee9")
        @Override
        public String getLabel(TagType tagType) {
            return getString(tagType.getLabelKey());
        }

        @objid ("e6030b6e-7235-4fa5-b923-4f80aa575597")
        @Override
        public String getLabel(NoteType noteType) {
            return getString(noteType.getLabelKey());
        }

        @objid ("df070356-466c-4eda-84fa-cfa2ea840c75")
        @Override
        public String getLabel(ResourceType docType) {
            return getString(docType.getLabelKey());
        }

        @objid ("8695b24f-026e-41ef-b3d2-385738b8c115")
        @Override
        public String getString(String key) {
            if (key == null) {
                return "";
            }
            if (this.I18N == null) {
                return key;
            }
            if (key.startsWith("%")) {
                try {
                    return this.I18N.getString(key.substring(1));
                } catch (MissingResourceException e) {
                    this.logService.warning("Missing Resource :'" + key + "'");
                    // ApiImpl.LOG.debug(e);
                }
            }
            return key;
        }

        @objid ("3ead43c8-95d8-40e6-abc2-942abdb3dd7b")
        @Override
        public String getLabel(PropertyDefinition pdef) {
            PropertyTableDefinition table = pdef.getOwner();
            return getString("%propertydefinition."
                                + (table != null ? table.getName() : "") + "."
                                + pdef.getName() + ".label");
            
        }

        @objid ("4f00ca6e-3e96-40f2-a49a-fcab5486454e")
        @Override
        public String getDescription(NoteType element) {
            return getString(element.getLabelKey().replace(".label", ".description"));
        }

        @objid ("9e53812f-cb13-4614-b9b3-7be0ad018016")
        @Override
        public String getDescription(ResourceType element) {
            return getString(element.getLabelKey().replace(".label", ".description"));
        }

        @objid ("060a91cc-1fba-447a-9fb2-82d60f015c5a")
        @Override
        public String getDescription(Stereotype element) {
            return getString(element.getLabelKey().replace(".label", ".description"));
        }

        @objid ("ffe260d4-15ee-4637-a6ba-9d4317b3b4d3")
        @Override
        public String getDescription(TagType element) {
            return getString(element.getLabelKey().replace(".label", ".description"));
        }

        @objid ("59075806-87d9-4c79-86b8-1db81a15c65c")
        @Override
        public String getDescription(Profile element) {
            return getString("%profile." + element.getName() + ".description");
        }

        @objid ("318b4a96-351a-4c3b-9372-69dda7f4afc8")
        @Override
        public String getLabel(Profile element) {
            return getString("%profile." + element.getName() + ".label");
        }

    }

}
