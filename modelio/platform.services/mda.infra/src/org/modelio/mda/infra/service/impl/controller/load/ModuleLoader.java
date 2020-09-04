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

package org.modelio.mda.infra.service.impl.controller.load;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.module.IModule;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.api.module.context.configuration.IModuleAPIConfiguration;
import org.modelio.api.module.context.configuration.IModuleUserConfiguration;
import org.modelio.api.module.lifecycle.ModuleException;
import org.modelio.gproject.fragment.FragmentState;
import org.modelio.gproject.fragment.IProjectFragment;
import org.modelio.gproject.gproject.GProject;
import org.modelio.gproject.module.GModule;
import org.modelio.gproject.module.IMetamodelFragmentHandle;
import org.modelio.gproject.module.IModuleHandle;
import org.modelio.mda.infra.plugin.MdaInfra;
import org.modelio.mda.infra.service.CompatibilityHelper;
import org.modelio.mda.infra.service.IRTModule;
import org.modelio.mda.infra.service.ModuleContextFactory;
import org.modelio.mda.infra.service.impl.IRTModuleAccess;
import org.modelio.mda.infra.service.impl.common.BrokenIModule;
import org.modelio.mda.infra.service.impl.common.FakeIModule;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.version.ModelioVersion;

/**
 * This class instantiates the module main class .
 * <p>
 * Does not call any module class method except the constructor.
 * <p>
 * If the module cannot be loaded a {@link BrokenIModule} is returned. Then
 * {@link IRTModule#getDownError()} returns the failure cause.
 */
@objid ("8a81c9bc-f34b-11e1-9458-001ec947c8cc")
public class ModuleLoader {
    @objid ("7f2a6960-0263-11e2-9fca-001ec947c8cc")
    private static final String FAKE_MODULE_CLASS_NAME = "<FAKE>";

    @objid ("b91d1222-f3cf-44b4-99a9-138b18328063")
    private final GProject gProject;

    @objid ("1e16235d-f378-4c00-8938-4e6b5b3f2ccb")
    private final IRTModuleAccess rtModule;

    @objid ("86155341-fb8c-47a6-a816-4b2dc37dc8fe")
    private final GModule gModule;

    @objid ("5b657199-e5f8-4e95-9ff9-7be5fa459146")
    private final IModuleHandle moduleHandle;

    /**
     * @param rtModule the module
     */
    @objid ("36d66263-ae93-4f00-8eb3-993dcb071285")
    public ModuleLoader(final IRTModuleAccess rtModule) {
        this.rtModule = rtModule;
        this.gProject = rtModule.getGModule().getProject();
        this.gModule = rtModule.getGModule();
        this.moduleHandle = this.gModule.getModuleHandle();
    }

    /**
     * @return the loaded module implementation
     * @throws org.modelio.api.module.lifecycle.ModuleException on failure, if sanity checks fail
     */
    @objid ("8a81c9ac-f34b-11e1-9458-001ec947c8cc")
    @SuppressWarnings("resource")
    public IModule loadModule() throws ModuleException {
        // Sanity checks
        checkModule();
        
        // 2) Get and load the module main class
        String mainClassName = this.moduleHandle.getMainClassName();
        if (ModuleLoader.FAKE_MODULE_CLASS_NAME.equals(mainClassName)) {
            // Fake module is added to the registry to get access to stereotype
            // icons
            return new FakeIModule(this.gModule,
                    this.rtModule.getConfiguration(),
                    this.rtModule.getModuleApiConfiguration());
        } else if (!ModuleLoader.isCompatibleWithModelio(this.moduleHandle)) {
            String msg = MdaInfra.I18N.getMessage("ModuleLoader.IncompatibleModelioVersion", this.rtModule.getName(), this.rtModule.getVersion(), ModelioVersion.VERSION, this.moduleHandle.getBinaryVersion());
            throw new ModuleException(msg);
        
        } else {
            // Setup the module class loader:
            ClassLoader classLoader = ModuleClassLoaderFactory
                    .setupClassLoader(this.rtModule);
        
            this.rtModule.setClassLoader(classLoader);
        
            // Instantiate the user defined module class
            IModule iModule = instantiateModuleMainClass(classLoader,
                    mainClassName, this.rtModule.getConfiguration(),
                    this.rtModule.getModuleApiConfiguration());
        
            return iModule;
        }
    }

    /**
     * Perform sanity checks on the module before loading it
     * @param gModule
     * the GModule
     * @param rtModuleHandle
     * the IModuleHandle
     * @throws org.modelio.api.module.lifecycle.ModuleException if the module cannot be loaded.
     */
    @objid ("9aa1c953-f84d-40b5-97e0-0a3a110c5815")
    private void checkModule() throws ModuleException {
        if (this.gModule.getModuleHandle() == null) {
            // No valid module handle means the module is broken.
            // Should not happen...
            throw new ModuleException("No module handle found.");
        }
        
        final IProjectFragment modelFragment = this.gModule.getModelFragment();
        if (modelFragment == null) {
            throw new ModuleException("The module has no MDA model fragment.");
        } else if (modelFragment.getState() != FragmentState.UP_FULL) {
            // The fragment must be up
            if (modelFragment.getDownError() != null) {
                throw ModuleLoader.toModuleException(modelFragment.getDownError());
            } else {
                throw new ModuleException("The module fragment is in '"
                        + modelFragment.getState() + "' state.");
            }
        } else if (this.gModule.getModuleElement() == null) {
            throw new ModuleException(
                    String.format(
                            "The '%s' module has no RAMC model element with {%s} UUID.",
                            this.gModule.getName(), this.gModule
                                    .getModuleHandle().getUid()));
        }
    }

    /**
     * Load all metamodel fragments and register them in the modeling session.
     * @param classLoader the class loader to use
     * @return the loaded metamodel fragments
     * @throws org.modelio.api.module.lifecycle.ModuleException in case of error loading a fragment
     */
    @objid ("fa24dd00-47ae-4ca1-abaf-2684ea31e88a")
    public List<ISmMetamodelFragment> loadMetamodelFragments(ClassLoader classLoader) throws ModuleException {
        List<ISmMetamodelFragment> mmFrags = new ArrayList<>();
        
        for (IMetamodelFragmentHandle mmfHandle : this.moduleHandle
                .getMetamodelFragments()) {
            String mmfClassName = mmfHandle.getClassName();
            String mmfName = mmfHandle.getName() + " " + mmfHandle.getVersion();
        
            try {
                // Look for the fragment class
                Class<?> cls = classLoader.loadClass(mmfClassName);
        
                // Check the class
                if (!ISmMetamodelFragment.class.isAssignableFrom(cls)) {
                    String msg = MdaInfra.I18N.getMessage("ModuleLoader.mmf.BadClass", mmfName, mmfClassName, ISmMetamodelFragment.class.getName());
                    throw new ModuleException(msg);
                }
        
                // Instantiate fragment
                ISmMetamodelFragment mmf = (ISmMetamodelFragment) cls
                        .newInstance();
        
                // Load the fragment
                this.gProject.getSession().getMetamodelSupport()
                        .addMetamodelFragment(mmf);
        
                mmFrags.add(mmf);
        
            } catch (ClassNotFoundException e) {
                String msg = MdaInfra.I18N.getMessage("ModuleLoader.mmf.ClassNotFoundException", mmfName, mmfClassName, e.getLocalizedMessage());
                throw new ModuleException(msg, e);
            } catch (NoClassDefFoundError e) {
                String msg = MdaInfra.I18N.getMessage("ModuleLoader.mmf.NoClassDefFoundError", mmfName, mmfClassName, e.getLocalizedMessage());
                throw new ModuleException(msg, e);
            } catch (SecurityException e) {
                String msg = MdaInfra.I18N.getMessage("ModuleLoader.mmf.SecurityException", mmfName, mmfClassName, e.getLocalizedMessage());
                throw new ModuleException(msg, e);
            } catch (InstantiationException e) {
                String msg = MdaInfra.I18N.getMessage("ModuleLoader.mmf.InstantiationException", mmfName, mmfClassName, e.getLocalizedMessage());
                throw new ModuleException(msg, e);
            } catch (IllegalAccessException e) {
                String msg = MdaInfra.I18N.getMessage("ModuleLoader.mmf.IllegalAccessException", mmfName, mmfClassName, e.getLocalizedMessage());
                throw new ModuleException(msg, e);
            } catch (ExceptionInInitializerError e) {
                Throwable cause = e.getCause();
                if (cause == null) {
                    cause = e;
                }
                String msg = MdaInfra.I18N.getMessage("ModuleLoader.mmf.ExceptionInInitializerError", mmfName, mmfClassName, cause.toString());
                throw new ModuleException(msg, e);
            } catch (LinkageError e) {
                Throwable cause = e.getCause();
                if (cause == null) {
                    cause = e;
                }
                String msg = MdaInfra.I18N.getMessage("ModuleLoader.mmf.LinkageError", mmfName, mmfClassName, cause.getLocalizedMessage());
                throw new ModuleException(msg, e);
            } catch (RuntimeException e) {
                String msg = MdaInfra.I18N.getMessage("ModuleLoader.mmf.RuntimeException", mmfName, mmfClassName, e.toString());
                throw new ModuleException(msg, e);
            }
        } // for loop
        return mmFrags;
    }

    /**
     * Fetch by reflexion the main class of this module and instantiate it.
     * @param classLoader the module class loader
     * @param mainClassName the module main class name
     * @param moduleUserConfiguration module user configuration
     * @param moduleApiConfiguration module api configuration
     * @return the loaded module
     * @throws org.modelio.api.module.lifecycle.ModuleException in case of error
     */
    @objid ("7f175707-0263-11e2-9fca-001ec947c8cc")
    public IModule instantiateModuleMainClass(ClassLoader classLoader, String mainClassName, IModuleUserConfiguration moduleUserConfiguration, IModuleAPIConfiguration moduleApiConfiguration) throws ModuleException {
        assert (moduleUserConfiguration != null);
        assert (moduleApiConfiguration != null);
        
        // Clear the ResourceBundles cache for the module class loader,
        // otherwise the module resources bundle are not
        // found when upgrading a module and non understandable
        // MissingResourceException are thrown.
        ResourceBundle.clearCache(classLoader);
        
        Class<?> mainClass = null;
        
        // Try to get the module main class
        try {
            mainClass = classLoader.loadClass(mainClassName);
            if (!IModule.class.isAssignableFrom(mainClass)) {
                throw new ModuleException(mainClass.getName()
                        + " main class does not implement "
                        + IModule.class.getName() + " .");
            }
        
        } catch (ClassNotFoundException e) {
            final ModuleException e2 = new ModuleException(String.format(
                    "The '%1$s' module main class has not been found: %2$s",
                    this.moduleHandle.getName(), e.getMessage()), e);
            throw e2;
        } catch (NoClassDefFoundError e) {
            final ModuleException e2 = new ModuleException(String.format(
                    "Cannot load '%1$s' module main class: %2$s",
                    this.moduleHandle.getName(), e.getMessage()), e);
            throw e2;
        }
        
        // Try to instantiate a module for V35, if fail try the V34 option
        try {
            return callConstructorV35(moduleUserConfiguration,
                    moduleApiConfiguration, mainClass);
        } catch (NoSuchMethodException e) {
            return callConstructorV34(moduleUserConfiguration,
                    moduleApiConfiguration, mainClass);
        
        }
    }

    /**
     * Check the module version is compatible with the current Modelio
     * @param rtModuleHandle the module to check.
     * @return <code>true</code> if this module is not compatible with the
     * current Modelio.
     */
    @objid ("6fd59629-2f29-11e2-9ab7-002564c97630")
    private static boolean isCompatibleWithModelio(IModuleHandle rtModuleHandle) {
        return CompatibilityHelper.isCompatible(CompatibilityHelper
                        .getCompatibilityLevel(ModelioVersion.VERSION,
                                rtModuleHandle.getBinaryVersion()));
    }

    /**
     * Convert any exception to a {@link ModuleException} by wrapping it if
     * needed.
     * @param cause an exception
     * @return a ModuleException.
     */
    @objid ("146d7c39-cc97-422e-9a3a-8648112ad405")
    private static ModuleException toModuleException(Throwable cause) {
        if (cause == null) {
            return new ModuleException(
                    MdaInfra.I18N.getMessage("BrokenModule.nocause"));
        } else if (cause instanceof ModuleException) {
            return (ModuleException) cause;
        } else if (cause instanceof IOException) {
            return new ModuleException(
                    FileUtils.getLocalizedMessage((IOException) cause), cause);
        } else if (cause instanceof RuntimeException || cause instanceof Error) {
            return new ModuleException(cause.toString(), cause);
        } else {
            return new ModuleException(cause.getLocalizedMessage(), cause);
        }
    }

    /**
     * Instantiate a fake module.
     * @return a fake module
     */
    @objid ("0b61c1c7-8587-4149-8407-0d81b2a10937")
    public IModule createFakeModule() {
        return new FakeIModule(this.gModule, this.rtModule.getConfiguration(), this.rtModule.getModuleApiConfiguration());
    }

    /**
     * Instantiate a fake module.
     * @return a fake module
     */
    @objid ("2c872102-3024-45d6-b0c4-2d87928c45b6")
    public IModule createBrokenModule() {
        return new BrokenIModule(this.gModule, this.rtModule.getConfiguration(), this.rtModule.getDownError());
    }

    /**
     * Call the static install(String projectPath, String moduleResourcesPath)
     * method on the module main class using a temporary class loader.
     * @throws org.modelio.api.module.lifecycle.ModuleException on failure
     */
    @objid ("1abed92c-5371-41c4-9d85-9f38627e4d9b")
    public void callStaticMethodInstall() throws ModuleException {
        // Construct a class loader on these informations.
        // NOTE: the call to #install may actually bring in something that will
        // complete the class loader created in ModuleLoader#loadModule
        final ClassLoader moduleClassLoader = ModuleClassLoaderFactory.setupClassLoader(this.rtModule);
        
        if (moduleClassLoader instanceof URLClassLoader) {
            callModuleStaticMethodInstall((URLClassLoader) moduleClassLoader);
        } else {
            callPluginStaticMethodInstall(moduleClassLoader);
        }
    }

    /**
     * @param moduleUserConfiguration
     * @param moduleApiConfiguration
     * @throws ModuleException
     * @param mainClass @return
     */
    @objid ("cc67ab5e-5029-4893-9fc4-b8b9bb37f07e")
    private IModule callConstructorV34(IModuleUserConfiguration moduleUserConfiguration, IModuleAPIConfiguration moduleApiConfiguration, Class<?> mainClass) throws ModuleException {
        try {
            // Instantiate the module:
        
            // Constructor parameters types
            Class<?>[] classParamArray = { IModelingSession.class,
                    ModuleComponent.class, IModuleUserConfiguration.class,
                    IModuleAPIConfiguration.class };
        
            // Get the constructor
            Constructor<?> constructor = mainClass
                    .getConstructor(classParamArray);
        
            // Prepare a moduleContext. Setting a Mdoulecontext on the V34 module ensures compatibility
            IModuleContext moduleContext = ModuleContextFactory.getInstance()
                    .createModuleContext(this.gModule.getModuleElement(),
                            moduleUserConfiguration, moduleApiConfiguration);
        
            // Create the new instance by calling the constructor.
            Object[] initParamArray = { moduleContext.getModelingSession(),
                    this.gModule.getModuleElement(), moduleUserConfiguration,
                    moduleApiConfiguration };
            IModule iModule = (IModule) constructor.newInstance(initParamArray);
        
            iModule.initModulecontext(moduleContext);
            moduleContext.setModule(iModule);
        
            return iModule;
        
        } catch (SecurityException e) {
            final ModuleException e2 = new ModuleException(
                    String.format(
                            "Security violation while loading the '%1$s' module: %2$s ",
                            this.moduleHandle.getName(), e.getMessage()));
            e2.initCause(e);
            throw e2;
        } catch (NoSuchMethodException e) {
            final ModuleException e2 = new ModuleException(
                    String.format(
                            "The '%1$s' module class constructor has not been found: %2$s",
                            this.moduleHandle.getName(), e.getMessage()));
            e2.initCause(e);
            throw e2;
        } catch (IllegalArgumentException e) {
            final ModuleException e2 = new ModuleException(
                    String.format(
                            "Illegal argument error while initalizing the '%1$s' module: %2$s ",
                            this.moduleHandle.getName(), e.getMessage()));
            e2.initCause(e);
            throw e2;
        } catch (InstantiationException e) {
            final ModuleException e2 = new ModuleException(String.format(
                    "Cannot instantiate the '%1$s' module class: %2$s ",
                    this.moduleHandle.getName(), e.getMessage()));
            e2.initCause(e);
            throw e2;
        } catch (IllegalAccessException e) {
            final ModuleException e2 = new ModuleException(
                    String.format(
                            "Illegal access error occured while initalizing the '%1$s' module: %2$s ",
                            this.moduleHandle.getName(), e.getMessage()));
            e2.initCause(e);
            throw e2;
        } catch (InvocationTargetException e) {
            Throwable cause = e;
            while (cause.getCause() != null) {
                cause = cause.getCause();
            }
            final ModuleException e2 = new ModuleException(
                    String.format(
                            "Exception thrown while initalizing the '%1$s' module: %2$s\n",
                            this.moduleHandle.getName(), cause.getMessage()));
            e2.initCause(e);
            throw e2;
        } catch (ExceptionInInitializerError e) {
            Throwable cause = e.getCause();
            if (cause == null) {
                cause = e;
            }
            final ModuleException e2 = new ModuleException(String.format(
                    "Error thrown while initalizing the '%1$s' module: %2$s ",
                    this.moduleHandle.getName(), cause.getMessage()));
            e2.initCause(e);
            throw e2;
        } catch (LinkageError e) {
            Throwable cause = e.getCause();
            if (cause == null) {
                cause = e;
            }
            final ModuleException e2 = new ModuleException(
                    String.format(
                            "Linkage error thrown while initalizing the '%1$s' module: %2$s ",
                            this.moduleHandle.getName(), cause.getMessage()));
            e2.initCause(cause);
            throw e2;
        }
    }

    /**
     * @param moduleUserConfiguration
     * @param moduleApiConfiguration
     * @throws ModuleException
     * @throws NoSuchMethodException
     * @param mainClass @return
     */
    @objid ("d6d9254f-efa7-4906-b679-03a408796b61")
    private IModule callConstructorV35(IModuleUserConfiguration moduleUserConfiguration, IModuleAPIConfiguration moduleApiConfiguration, Class<?> mainClass) throws ModuleException, NoSuchMethodException {
        try {
            // Instantiate the module:
        
            // Constructor parameters types
            Class<?>[] classParamArray = { IModuleContext.class };
        
            // Get the constructor
            Constructor<?> constructor = mainClass
                    .getConstructor(classParamArray);
        
            // Create the new instance by calling the constructor.
            IModuleContext moduleContext = ModuleContextFactory.getInstance()
                    .createModuleContext(this.gModule.getModuleElement(),
                            moduleUserConfiguration, moduleApiConfiguration);
        
            Object[] initParamArray = { moduleContext };
        
            IModule iModule = (IModule) constructor.newInstance(initParamArray);
            moduleContext.setModule(iModule);
        
            return iModule;
        
        } catch (SecurityException e) {
            final ModuleException e2 = new ModuleException(
                    String.format(
                            "Security violation while loading the '%1$s' module: %2$s ",
                            this.moduleHandle.getName(), e.getMessage()));
            e2.initCause(e);
            throw e2;
        
        } catch (IllegalArgumentException e) {
            final ModuleException e2 = new ModuleException(
                    String.format(
                            "Illegal argument error while initalizing the '%1$s' module: %2$s ",
                            this.moduleHandle.getName(), e.getMessage()));
            e2.initCause(e);
            throw e2;
        } catch (InstantiationException e) {
            final ModuleException e2 = new ModuleException(String.format(
                    "Cannot instantiate the '%1$s' module class: %2$s ",
                    this.moduleHandle.getName(), e.getMessage()));
            e2.initCause(e);
            throw e2;
        } catch (IllegalAccessException e) {
            final ModuleException e2 = new ModuleException(
                    String.format(
                            "Illegal access error occured while initalizing the '%1$s' module: %2$s ",
                            this.moduleHandle.getName(), e.getMessage()));
            e2.initCause(e);
            throw e2;
        } catch (InvocationTargetException e) {
            Throwable cause = e;
            while (cause.getCause() != null) {
                cause = cause.getCause();
            }
            final ModuleException e2 = new ModuleException(
                    String.format(
                            "Exception thrown while initalizing the '%1$s' module: %2$s\n",
                            this.moduleHandle.getName(), cause.getMessage()));
            e2.initCause(e);
            throw e2;
        } catch (ExceptionInInitializerError e) {
            Throwable cause = e.getCause();
            if (cause == null) {
                cause = e;
            }
            final ModuleException e2 = new ModuleException(String.format(
                    "Error thrown while initalizing the '%1$s' module: %2$s ",
                    this.moduleHandle.getName(), cause.getMessage()));
            e2.initCause(e);
            throw e2;
        } catch (LinkageError e) {
            Throwable cause = e.getCause();
            if (cause == null) {
                cause = e;
            }
            final ModuleException e2 = new ModuleException(
                    String.format(
                            "Linkage error thrown while initalizing the '%1$s' module: %2$s ",
                            this.moduleHandle.getName(), cause.getMessage()));
            e2.initCause(cause);
            throw e2;
        }
    }

    @objid ("30162673-a2d7-4d04-b4ec-22c503b2be18")
    private void callPluginStaticMethodInstall(ClassLoader classLoader) throws ModuleException {
        Class<?> mainClass = null;
        try {
            mainClass = classLoader.loadClass(this.moduleHandle.getMainClassName());
        } catch (ClassNotFoundException e) {
            // Could not find main class in class loader,
            ModuleException e2 = new ModuleException(String.format(
                    "The '%1$s' module class has not been found.\n (%2$s)",
                    this.moduleHandle.getMainClassName(), e.getMessage()),
                    e);
            throw e2;
        } catch (NoClassDefFoundError e) {
            MdaInfra.LOG.error(e);
        
            ModuleException e2 = new ModuleException(String.format(
                    "The '%1$s' module class couldn't find the '%2$s' class .",
                    this.moduleHandle.getMainClassName(),
                    e.getMessage()));
            e2.initCause(e);
            throw e2;
        }
        
        // Invoke install(String projectPath, String moduleResourcesPath) method
        try {
            // Declare the parameters of the install method.
            Class<?>[] classParamArray = { String.class, String.class };
            Object[] initParamArray = { this.gProject.getProjectPath(),
                    this.moduleHandle.getResourcePath() };
        
            Method installMethod = mainClass.getMethod("install", classParamArray);
        
            // Invoke install(...) method .
            installMethod.invoke(null, initParamArray);
        
        } catch (NullPointerException npe) {
            MdaInfra.LOG.error(npe);
            ModuleException e2 = new ModuleException(MdaInfra.I18N.getMessage("ModuleExceptionMessage.InstallIsNotStatic", this.moduleHandle.getName())); //$NON-NLS-1$
            e2.initCause(npe);
            throw e2;
        } catch (SecurityException e) {
            MdaInfra.LOG.error(e);
            ModuleException e2 = new ModuleException(String.format("Security violation while loading the '%1$s' module:\n %2$s ", this.moduleHandle.getName(), e.getMessage()));
            e2.initCause(e);
            throw e2;
        } catch (NoClassDefFoundError e) {
            MdaInfra.LOG.error(e);
        
            ModuleException e2 = new ModuleException( String.format(
                            "The '%1$s' module class couldn't find the '%2$s' class .",
                            this.moduleHandle.getMainClassName(),
                            e.getMessage()));
            e2.initCause(e);
            throw e2;
        } catch (NoSuchMethodException e) {
            // No method to call...
        } catch (IllegalAccessException e) {
            MdaInfra.LOG.error(e);
            ModuleException e2 = new ModuleException(
                    String.format(
                            "Illegal access error occured while initalizing the '%1$s' module: %2$s ",
                            this.moduleHandle.getName(), e.getMessage()));
            e2.initCause(e);
            throw e2;
        } catch (InvocationTargetException e) {
            MdaInfra.LOG.error(e);
            Throwable cause = e.getCause();
            if (cause == null) {
                cause = e;
            }
            ModuleException e2 = new ModuleException(
                    String.format(
                            "Exception thrown while initalizing the '%1$s' module: %2$s ",
                            this.moduleHandle.getName(), cause.getMessage()));
            e2.initCause(cause);
            throw e2;
        }
    }

    @objid ("25468569-3f74-446d-928a-0acfb475c103")
    private void callModuleStaticMethodInstall(URLClassLoader moduleClassLoader) throws ModuleException {
        try (URLClassLoader classLoader = moduleClassLoader) {
            Class<?> mainClass = null;
            try {
                mainClass = classLoader.loadClass(this.moduleHandle
                        .getMainClassName());
            } catch (ClassNotFoundException e) {
                // Could not find main class in class loader,
                ModuleException e2 = new ModuleException(String.format(
                        "The '%1$s' module class has not been found.\n (%2$s)",
                        this.moduleHandle.getMainClassName(), e.getMessage()),
                        e);
                throw e2;
            } catch (NoClassDefFoundError e) {
                MdaInfra.LOG.error(e);
                // dump the classpath
                classLoader.getURLs();
                StringBuilder sUrls = new StringBuilder();
                for (URL url : classLoader.getURLs()) {
                    sUrls.append(" - ");
                    sUrls.append(url.toString());
                    sUrls.append("\n");
                }
        
                ModuleException e2 = new ModuleException(
                        String.format(
                                "The '%1$s' module class couldn't find the '%2$s' class in the following classpath:\n (%3$s)",
                                this.moduleHandle.getMainClassName(),
                                e.getMessage(), sUrls));
                e2.initCause(e);
                throw e2;
            }
        
            // Invoke install(String projectPath, String moduleResourcesPath)
            // method
            try {
        
                // Declare the parameters of the install method.
                Class<?>[] classParamArray = { String.class, String.class };
                Object[] initParamArray = { this.gProject.getProjectPath(),
                        this.moduleHandle.getResourcePath() };
        
                Method installMethod = mainClass.getMethod("install",
                        classParamArray);
        
                // Invoke install(...) method .
                installMethod.invoke(null, initParamArray);
        
            } catch (NullPointerException npe) {
                MdaInfra.LOG.error(npe);
                ModuleException e2 = new ModuleException(MdaInfra.I18N.getMessage("ModuleExceptionMessage.InstallIsNotStatic", this.moduleHandle.getName())); //$NON-NLS-1$
                e2.initCause(npe);
                throw e2;
            } catch (SecurityException e) {
                MdaInfra.LOG.error(e);
                ModuleException e2 = new ModuleException(String.format("Security violation while loading the '%1$s' module:\n %2$s ", this.moduleHandle.getName(), e.getMessage()));
                e2.initCause(e);
                throw e2;
            } catch (NoClassDefFoundError e) {
                MdaInfra.LOG.error(e);
                // dump the classpath
                classLoader.getURLs();
                StringBuilder sUrls = new StringBuilder();
                for (URL url : classLoader.getURLs()) {
                    sUrls.append(" - ");
                    sUrls.append(url.toString());
                    sUrls.append("\n");
                }
        
                ModuleException e2 = new ModuleException(
                        String.format(
                                "The '%1$s' module class couldn't find the '%2$s' class in the following classpath:\n (%3$s)",
                                this.moduleHandle.getMainClassName(),
                                e.getMessage(), sUrls));
                e2.initCause(e);
                throw e2;
            } catch (NoSuchMethodException e) {
                // No method to call...
            } catch (IllegalAccessException e) {
                MdaInfra.LOG.error(e);
                ModuleException e2 = new ModuleException(
                        String.format(
                                "Illegal access error occured while initalizing the '%1$s' module: %2$s ",
                                this.moduleHandle.getName(), e.getMessage()));
                e2.initCause(e);
                throw e2;
            } catch (InvocationTargetException e) {
                MdaInfra.LOG.error(e);
                Throwable cause = e.getCause();
                if (cause == null) {
                    cause = e;
                }
                ModuleException e2 = new ModuleException(
                        String.format(
                                "Exception thrown while initalizing the '%1$s' module: %2$s ",
                                this.moduleHandle.getName(), cause.getMessage()));
                e2.initCause(cause);
                throw e2;
            }
        } catch (IOException e) {
            MdaInfra.LOG.error(e);
        }
    }

}
