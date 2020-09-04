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

package org.modelio.script.engine.core.engine;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Path;
import java.util.Collection;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.jface.viewers.ISelection;
import org.modelio.api.modelio.Modelio;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.script.engine.plugin.ScriptEnginePlugin;
import org.modelio.vbasic.files.FileUtils;
import org.osgi.framework.Bundle;

/**
 * Jython script runner.
 */
@objid ("008ed532-8bd5-1065-a2b8-001ec947cd2a")
public class PythonRunner implements IScriptRunner {
    /**
     * Jython 2.7 interpreter initialization string.
     * <ol>
     * <li>import the 'sys' built-in module</li>
     * <li>set the class loader to 'CLASSLOADER' which is a Java variable exported to Jython using engine.put()</li>
     * <li>complete sys.path with the /Lib sub-directory that contains standard modules like os, zlib adn so on...</li>
     * </ol>
     */
    @objid ("004696d2-fb86-10ee-9fe5-001ec947cd2a")
    private static final String JYTHON_INIT = "import sys\n"
            + "sys.setClassLoader (CLASSLOADER)\n"
            + "if  sys.path.count('__pyclasspath__/Lib') == 0:\n"
            + "\tsys.path.append('__pyclasspath__/Lib')\n"
            + "sys.prefix = '.'\n";

    @objid ("6058ceb1-9f9c-4655-b38d-c535a4f745cd")
    private static final String INITSCRIPT = "res/initengine.py";

    @objid ("00760a48-cbcd-1065-a2b8-001ec947cd2a")
    private final javax.script.ScriptEngine engine;

    @objid ("00762c1c-cbcd-1065-a2b8-001ec947cd2a")
    private PrintWriter outputWriter;

    @objid ("007632b6-cbcd-1065-a2b8-001ec947cd2a")
    private PrintWriter errorWriter;

    @objid ("00763996-cbcd-1065-a2b8-001ec947cd2a")
    private PrintWriter commandWriter;

    @objid ("00065e1e-99ab-10ed-8812-001ec947cd2a")
    private ScriptClassLoader classLoader;

    @objid ("00763f36-cbcd-1065-a2b8-001ec947cd2a")
    PythonRunner(ScriptEngine scriptEngine) {
        this.engine = scriptEngine;
        
        this.outputWriter = new PrintWriter(System.out);
        this.commandWriter = this.outputWriter;
        this.errorWriter = this.outputWriter;
        
        this.engine.put("CLASSLOADER", this.classLoader);
        
        
        try {
            this.engine.eval(PythonRunner.JYTHON_INIT);
        } catch (ScriptException e) {
            ScriptEnginePlugin.LOG.debug("Evaluation of the initialization script engine failed: %s", e.getMessage());
            ScriptEnginePlugin.LOG.debug(PythonRunner.JYTHON_INIT);
            ScriptEnginePlugin.LOG.error(e);
        }
    }

    @objid ("0076521e-cbcd-1065-a2b8-001ec947cd2a")
    @Override
    public void runFile(Path file, ISelection selection, Collection<Element> selectedElements) {
        final String fileName = file.getFileName().toString();
        final String extension = fileName.substring(fileName.lastIndexOf('.') + 1);
        
        assert (extension.equals("py"));
        assert (this.engine != null);
        
        IModelingSession modelingSession = Modelio.getInstance().getModelingSession();
        bindVariables(selection, selectedElements, modelingSession);
        
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
    public void runScript(String script, ISelection selection, Collection<Element> selectedElements) {
        assert (this.engine != null);
        
        IModelingSession modelingSession = Modelio.getInstance().getModelingSession();
        bindVariables(selection, selectedElements, modelingSession);
        
        try {
            evalScript(script);
        } catch (ScriptException e) {
            this.errorWriter.println(e.getLocalizedMessage());
            this.errorWriter.println(e.getCause());
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

    @objid ("0096ff5a-9860-1069-96f6-001ec947cd2a")
    private void bindVariables(ISelection selection, Collection<Element> selectedElements, IModelingSession modelingSession) {
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
        this.classLoader = null;
        this.engine.put("CLASSLOADER", this.classLoader);
        this.engine.put("classloader", this.classLoader);
        
        final String init = "import sys\n" + "sys.setClassLoader (CLASSLOADER)\n";
        try {
            this.engine.eval(init);
        } catch (ScriptException e) {
            ScriptEnginePlugin.LOG.debug("Initialization of the script engine failed");
            ScriptEnginePlugin.LOG.error(e);
        }
    }

    @objid ("0008215e-99ab-10ed-8812-001ec947cd2a")
    @Override
    public void addClassLoader(ClassLoader base) {
        if (this.classLoader == null) {
            this.classLoader = new ScriptClassLoader();
            this.engine.put("CLASSLOADER", this.classLoader);
            this.engine.put("classloader", this.classLoader);
        
            final String init = this.JYTHON_INIT;
            try {
                this.engine.eval(init);
            } catch (ScriptException e) {
                ScriptEnginePlugin.LOG.debug("Initialization of the script engine failed");
                ScriptEnginePlugin.LOG.error(e);
            }
            
            final String initScript = getInitScript();
            if (initScript != null) {
                try (FileReader r = new FileReader(initScript)){
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
        this.classLoader.add(base);
    }

    @objid ("7c16d9bf-6525-4f82-95c3-f9fa5ed94df1")
    private String getInitScript() {
        Bundle bundle = ScriptEnginePlugin.getContext().getBundle();
        
        try {
            org.eclipse.core.runtime.Path ipath = new org.eclipse.core.runtime.Path(INITSCRIPT);
            URL url = FileLocator.find(bundle, ipath, null);
            if (url == null) {
                throw new FileNotFoundException(ipath.toString());
            }
        
            String path = FileLocator.toFileURL(url).getPath();
            return path;
        } catch (IOException e) {
            ScriptEnginePlugin.LOG.error("'%s' plugin file not found: %s", INITSCRIPT, FileUtils.getLocalizedMessage(e));
            ScriptEnginePlugin.LOG.error(e);
            return null;
        }
    }

}
