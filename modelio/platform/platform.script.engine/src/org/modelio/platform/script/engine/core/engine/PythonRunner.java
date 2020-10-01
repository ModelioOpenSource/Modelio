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

package org.modelio.platform.script.engine.core.engine;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.URL;
import java.nio.file.Path;
import java.util.Collection;
import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.jface.viewers.ISelection;
import org.modelio.api.modelio.Modelio;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.platform.script.engine.plugin.ScriptEnginePlugin;
import org.modelio.vbasic.files.FileUtils;
import org.osgi.framework.Bundle;
import org.python.core.Py;

/**
 * Jython script runner.
 */
@objid ("008ed532-8bd5-1065-a2b8-001ec947cd2a")
public class PythonRunner implements IScriptRunner {
    @objid ("00760a48-cbcd-1065-a2b8-001ec947cd2a")
    private final ClassLoaderScriptEngine engine;

    @objid ("00762c1c-cbcd-1065-a2b8-001ec947cd2a")
    private PrintWriter outputWriter;

    @objid ("007632b6-cbcd-1065-a2b8-001ec947cd2a")
    private PrintWriter errorWriter;

    @objid ("00763996-cbcd-1065-a2b8-001ec947cd2a")
    private PrintWriter commandWriter;

    @objid ("00763f36-cbcd-1065-a2b8-001ec947cd2a")
    PythonRunner(ScriptEngine scriptEngine) {
        this.engine = new ClassLoaderScriptEngine(scriptEngine);
        
        this.outputWriter = new PrintWriter(System.out);
        this.commandWriter = this.outputWriter;
        this.errorWriter = this.outputWriter;
    }

    @objid ("0076521e-cbcd-1065-a2b8-001ec947cd2a")
    @Override
    public void runFile(Path file, ISelection selection, Collection<Element> selectedElements) throws ScriptException {
        final String fileName = file.getFileName().toString();
        final String extension = fileName.substring(fileName.lastIndexOf('.') + 1);
        
        assert extension.equals("py");
        assert this.engine != null;
        
        this.engine.bindStandardVariables(selection, selectedElements, Modelio.getInstance().getModelingSession());
        
        try {
            try (FileReader reader = new FileReader(file.toFile())) {
                this.engine.eval(reader);
                this.commandWriter.println();
            } catch (FileNotFoundException e) {
                ScriptEnginePlugin.LOG.error(ScriptEnginePlugin.PLUGIN_ID, e);
                this.errorWriter.println(ScriptEnginePlugin.I18N.getMessage("FileNotFound", e.getMessage()));
            } catch (IOException e) {
                ScriptEnginePlugin.LOG.error(ScriptEnginePlugin.PLUGIN_ID, e);
                e.printStackTrace(this.errorWriter);
            }
        
        } catch (ScriptException e) {
            this.errorWriter.println(e.getLocalizedMessage());
            this.errorWriter.println(e.getCause());
            throw e;
        } catch (RuntimeException e) {
            ScriptEnginePlugin.LOG.error(ScriptEnginePlugin.PLUGIN_ID, e);
            e.printStackTrace(this.errorWriter);
        } catch (Exception e) {
            ScriptEnginePlugin.LOG.error(ScriptEnginePlugin.PLUGIN_ID, e);
            e.printStackTrace(this.errorWriter);
            throw e;
        } finally {
            this.outputWriter.flush();
            this.errorWriter.flush();
            this.commandWriter.flush();
        }
    }

    @objid ("00767370-cbcd-1065-a2b8-001ec947cd2a")
    @Override
    public void runScript(String script, ISelection selection, Collection<Element> selectedElements) throws ScriptException {
        assert this.engine != null;
        
        this.engine.bindStandardVariables(selection, selectedElements, Modelio.getInstance().getModelingSession());
        
        try {
            evalScript(script);
        } catch (ScriptException e) {
            this.errorWriter.println(e.getLocalizedMessage());
            this.errorWriter.println(e.getCause());
            throw e;
        } catch (RuntimeException e) {
            ScriptEnginePlugin.LOG.error(ScriptEnginePlugin.PLUGIN_ID, e);
            e.printStackTrace(this.errorWriter);
        } catch (Exception e) {
            ScriptEnginePlugin.LOG.error(ScriptEnginePlugin.PLUGIN_ID, e);
            e.printStackTrace(this.errorWriter);
            throw e;
        } finally {
            this.commandWriter.flush();
            this.outputWriter.flush();
            this.errorWriter.flush();
        }
    }

    @objid ("007694f4-cbcd-1065-a2b8-001ec947cd2a")
    @Override
    public void setCommandStream(PrintWriter writer) {
        this.commandWriter = writer;
    }

    @objid ("0076b1dc-cbcd-1065-a2b8-001ec947cd2a")
    @Override
    public void setOutputStream(PrintWriter writer) {
        this.outputWriter = writer;
        this.engine.getContext().setWriter(this.outputWriter);
    }

    @objid ("0076ceec-cbcd-1065-a2b8-001ec947cd2a")
    @Override
    public void setErrorStream(PrintWriter writer) {
        this.errorWriter = writer;
        this.engine.getContext().setErrorWriter(this.errorWriter);
    }

    @objid ("00770c72-cbcd-1065-a2b8-001ec947cd2a")
    @Override
    public void bind(String key, Object value) {
        this.engine.getContext().setAttribute(key, value, ScriptContext.ENGINE_SCOPE);
    }

    @objid ("00772f4a-cbcd-1065-a2b8-001ec947cd2a")
    @Override
    public void unbind(String key) {
        this.engine.getContext().removeAttribute(key, ScriptContext.ENGINE_SCOPE);
    }

    @objid ("00774db8-cbcd-1065-a2b8-001ec947cd2a")
    @Override
    public Object getBinding(String key) {
        return this.engine.getContext().getAttribute(key, ScriptContext.ENGINE_SCOPE);
    }

    @objid ("00972dfe-9860-1069-96f6-001ec947cd2a")
    private void evalScript(String script) throws ScriptException {
        // Feedback the evaluated script text in the command writer
        this.commandWriter.println(script.trim());
        this.commandWriter.println();
        this.commandWriter.flush();
        
        // Run the script
        Object ret = this.engine.eval(script);
        this.commandWriter.println();
        
        // Print the returned value to the console
        if (ret != null) {
            final String retvar = "value_returned_by_script";
            this.engine.getContext().setAttribute(retvar, ret, ScriptContext.ENGINE_SCOPE);
            this.engine.eval(this.engine.getFactory().getOutputStatement(retvar));
            this.engine.getContext().removeAttribute(retvar, ScriptContext.ENGINE_SCOPE);
        }
    }

    @objid ("0007df82-99ab-10ed-8812-001ec947cd2a")
    @Override
    public ScriptEngine getEngine() {
        return this.engine;
    }

    @objid ("000806d8-99ab-10ed-8812-001ec947cd2a")
    @Override
    public void clearClassloader() {
        this.engine.clearClassloader();
    }

    @objid ("0008215e-99ab-10ed-8812-001ec947cd2a")
    @Override
    public void addClassLoader(ClassLoader base) {
        this.engine.addClassLoader(base);
    }

    /**
     * This class encapsulates a Jython {@link ScriptEngine} initialize the Jython environment.
     * <p>
     * The engine's ClassLoader is lazily initialized, and needs to be verified before each evaluation to avoid problems when Modelio uses several engines at the same time.
     * </p>
     */
    @objid ("9ad20361-551e-49d6-9ac3-3df3ded3fab5")
    private static class ClassLoaderScriptEngine implements ScriptEngine {
        @objid ("a927e4d4-5402-41b5-8fcf-244944032ffa")
        private static final String CLASSLOADER_VARIABLE = "CLASSLOADER";

        /**
         * Jython interpreter initialization script.
         * <ol>
         * <li>import the 'sys' built-in module</li>
         * <li>complete sys.path with the /Lib sub-directory that contains standard modules like os, zlib adn so on...</li>
         * </ol>
         */
        @objid ("a86a1791-9fca-478a-8dbf-8c04a0d61cfe")
        private static final String INIT_SCRIPT = new StringBuilder("import sys\n")
        .append("if  sys.path.count('__pyclasspath__/Lib') == 0:\n")
        .append("\tsys.path.append('__pyclasspath__/Lib')\n")
        .append("sys.prefix = '.'\n").toString();

        /**
         * Classloader initialization script.
         * <ol>
         * <li>import the 'sys' built-in module</li>
         * <li>set the class loader to {@value #CLASSLOADER_VARIABLE} which is a Java variable exported to Jython using engine.put()</li>
         * </ol>
         */
        @objid ("0ae8469c-da4c-4ec2-91f4-72984c01e97b")
        private static final String SETCLASSLOADER_SCRIPT = new StringBuilder("import sys\n")
        .append("sys.setClassLoader (")
        .append(ClassLoaderScriptEngine.CLASSLOADER_VARIABLE)
        .append(")\n").toString();

        /**
         * Relative path for the script engine initialization script in the plugin's resources.
         */
        @objid ("9952ec09-3189-4c89-9482-1856157694c0")
        private static final String INITENGINE_SCRIPT_PATH = "res/initengine.py";

        @objid ("e52263d8-445f-46df-88ce-563fd959dbb2")
        private final ScriptEngine engine;

        @objid ("00065e1e-99ab-10ed-8812-001ec947cd2a")
        private ScriptClassLoader classLoader;

        @objid ("ef0961fd-3c4d-4143-b397-5d0af5e4c64d")
        private ClassLoaderScriptEngine(ScriptEngine scriptEngine) {
            this.engine = scriptEngine;
            
            try {
                this.engine.eval(ClassLoaderScriptEngine.INIT_SCRIPT);
            } catch (ScriptException e) {
                ScriptEnginePlugin.LOG.debug("Evaluation of the script engine initialization failed: %s", e.getMessage());
                ScriptEnginePlugin.LOG.debug(ClassLoaderScriptEngine.INIT_SCRIPT);
                ScriptEnginePlugin.LOG.error(e);
            }
        }

        @objid ("53fcc78d-6096-4da6-9ce1-8f1eef2ff56c")
        @Override
        public Object eval(String script, ScriptContext context) throws ScriptException {
            enforceCurrentClassLoader();
            return this.engine.eval(script, context);
        }

        @objid ("5c451d9d-70e7-49ab-abe3-bfeddeed921f")
        @Override
        public Object eval(Reader reader, ScriptContext context) throws ScriptException {
            enforceCurrentClassLoader();
            return this.engine.eval(reader, context);
        }

        @objid ("77b9b3de-0abc-4728-886e-6ec80b32f11e")
        @Override
        public Object eval(String script) throws ScriptException {
            enforceCurrentClassLoader();
            return this.engine.eval(script);
        }

        @objid ("029f45b9-8a2e-42b0-a2e1-70f6804d358c")
        @Override
        public Object eval(Reader reader) throws ScriptException {
            enforceCurrentClassLoader();
            return this.engine.eval(reader);
        }

        @objid ("486f5a0c-e883-470e-a17b-5aff95cb40b5")
        @Override
        public Object eval(String script, Bindings n) throws ScriptException {
            enforceCurrentClassLoader();
            return this.engine.eval(script, n);
        }

        @objid ("5155a670-9e93-401f-8d13-e09389ac8a2b")
        @Override
        public Object eval(Reader reader, Bindings n) throws ScriptException {
            enforceCurrentClassLoader();
            return this.engine.eval(reader, n);
        }

        @objid ("049a60c1-451f-4d63-a5bb-e18e3989b821")
        @Override
        public void put(String key, Object value) {
            this.engine.put(key, value);
        }

        @objid ("ea8209b1-23dc-4896-a8a4-e0b5acbd3c46")
        @Override
        public Object get(String key) {
            return this.engine.get(key);
        }

        @objid ("6a14b7d4-b653-4a63-b4c2-1bedd357d81a")
        @Override
        public Bindings getBindings(int scope) {
            return this.engine.getBindings(scope);
        }

        @objid ("5771951d-8aec-44f2-a0d0-1673c7bf1912")
        @Override
        public void setBindings(Bindings bindings, int scope) {
            this.engine.setBindings(bindings, scope);
        }

        @objid ("0200fb25-465a-4aaa-89d1-250ae8c0a842")
        @Override
        public Bindings createBindings() {
            return this.engine.createBindings();
        }

        @objid ("c25379a9-b957-4ba5-8c0a-8569461f466e")
        @Override
        public ScriptContext getContext() {
            return this.engine.getContext();
        }

        @objid ("724a516c-78e1-4964-9e3f-a6d7c4cc9e2f")
        @Override
        public void setContext(ScriptContext context) {
            this.engine.setContext(context);
        }

        @objid ("59c4d930-a958-404b-940f-a2cff655bb0f")
        @Override
        public ScriptEngineFactory getFactory() {
            return this.engine.getFactory();
        }

        @objid ("b7faeff2-12e9-4af8-9ed2-e311d495bc3b")
        private void initClassLoader() {
            this.classLoader = new ScriptClassLoader();
            this.engine.put(ClassLoaderScriptEngine.CLASSLOADER_VARIABLE, this.classLoader);
            
            try {
                this.engine.eval(ClassLoaderScriptEngine.SETCLASSLOADER_SCRIPT);
            } catch (ScriptException e) {
                ScriptEnginePlugin.LOG.debug("Initialization of the script engine failed");
                ScriptEnginePlugin.LOG.error(e);
            }
            
            final String initScript = getInitScriptPath();
            if (initScript != null) {
                try (FileReader r = new FileReader(initScript)) {
                    this.engine.eval(r);
                } catch (ScriptException e) {
                    ScriptEnginePlugin.LOG.debug("Evaluation of the '%s' initialization script engine failed: %s", initScript, e.getMessage());
                    ScriptEnginePlugin.LOG.error(e);
                } catch (IOException e) {
                    ScriptEnginePlugin.LOG.debug("Reading of the '%s' initialization script failed: %s", initScript, FileUtils.getLocalizedMessage(e));
                    ScriptEnginePlugin.LOG.error(e);
                }
            }
        }

        @objid ("b52975bd-4af2-4121-b059-b67018bf2946")
        public void addClassLoader(ClassLoader base) {
            if (this.classLoader == null) {
                initClassLoader();
            }
            this.classLoader.add(base);
        }

        /**
         * @return a full path for the engine's initialization script, pre-loading classes from the Module API and Metamodel.
         */
        @objid ("7c16d9bf-6525-4f82-95c3-f9fa5ed94df1")
        private String getInitScriptPath() {
            Bundle bundle = ScriptEnginePlugin.getContext().getBundle();
            
            try {
                org.eclipse.core.runtime.Path ipath = new org.eclipse.core.runtime.Path(ClassLoaderScriptEngine.INITENGINE_SCRIPT_PATH);
                URL url = FileLocator.find(bundle, ipath, null);
                if (url == null) {
                    throw new FileNotFoundException(ipath.toString());
                }
            
                return FileLocator.toFileURL(url).getPath();
            } catch (IOException e) {
                ScriptEnginePlugin.LOG.error("'%s' plugin file not found: %s", ClassLoaderScriptEngine.INITENGINE_SCRIPT_PATH, FileUtils.getLocalizedMessage(e));
                ScriptEnginePlugin.LOG.error(e);
                return null;
            }
        }

        /**
         * Make sure Jython uses {@link #classLoader} before evaluating scripts.
         */
        @objid ("1434f800-e6dc-4e22-b573-1efb52083797")
        private void enforceCurrentClassLoader() {
            if (this.classLoader != null && Py.defaultSystemState.getClassLoader() != this.classLoader) {
                Py.defaultSystemState.setClassLoader(this.classLoader);
            }
        }

        @objid ("268b8821-dda6-4917-92e6-1bc729c5f379")
        public void clearClassloader() {
            this.classLoader = null;
            this.engine.put(ClassLoaderScriptEngine.CLASSLOADER_VARIABLE, this.classLoader);
            
            try {
                this.engine.eval(ClassLoaderScriptEngine.SETCLASSLOADER_SCRIPT);
            } catch (ScriptException e) {
                ScriptEnginePlugin.LOG.debug("Initialization of the script engine failed");
                ScriptEnginePlugin.LOG.error(e);
            }
        }

        @objid ("0096ff5a-9860-1069-96f6-001ec947cd2a")
        private void bindStandardVariables(ISelection selection, Collection<Element> selectedElements, IModelingSession modelingSession) {
            // local scope bindings
            this.engine.put("selectedElements", selectedElements);
            this.engine.put("selection", selection);
            this.engine.put("modelingSession", modelingSession);
            
            this.engine.put("elements", selectedElements);
            if (selectedElements == null || selectedElements.isEmpty()) {
                this.engine.put("elt", null);
            } else {
                this.engine.put("elt", selectedElements.iterator().next());
            }
            this.engine.put("selection", selection);
            this.engine.put("session", modelingSession);
        }

    }

}
