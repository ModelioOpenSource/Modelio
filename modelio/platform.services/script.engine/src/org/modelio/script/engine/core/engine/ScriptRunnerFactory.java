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

package org.modelio.script.engine.core.engine;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.script.engine.plugin.ScriptEnginePlugin;
import org.python.core.Options;

/**
 * Factory to use to get a {@link IScriptRunner}.
 */
@objid ("001385c6-8bde-1065-a2b8-001ec947cd2a")
public class ScriptRunnerFactory {
    @objid ("00758578-cbcd-1065-a2b8-001ec947cd2a")
    private final ScriptEngineManager scriptEngineManager;

    /**
     * Singleton pattern.
     */
    @objid ("0075993c-cbcd-1065-a2b8-001ec947cd2a")
    private static ScriptRunnerFactory instance = null;

    /**
     * Get the factory.
     * 
     * @return the factory.
     */
    @objid ("0075b64c-cbcd-1065-a2b8-001ec947cd2a")
    public static ScriptRunnerFactory getInstance() {
        if (ScriptRunnerFactory.instance == null) {
            ScriptRunnerFactory.instance = new ScriptRunnerFactory();
        }
        return ScriptRunnerFactory.instance;
    }

    @objid ("0075a864-cbcd-1065-a2b8-001ec947cd2a")
    private ScriptRunnerFactory() {
        this.scriptEngineManager = new ScriptEngineManager(ScriptEnginePlugin.class.getClassLoader());
        
        for (ScriptEngineFactory ef : this.scriptEngineManager.getEngineFactories()) {
            ScriptEnginePlugin.LOG.debug("script engine : %s %S (%s %s)", ef.getLanguageName(), ef.getLanguageVersion(),
                    ef.getEngineName(), ef.getEngineVersion());
        }
    }

    /**
     * Get a script runner
     * 
     * @param scriptingLanguage a script language
     * @return the script runner.
     */
    @objid ("0075db2c-cbcd-1065-a2b8-001ec947cd2a")
    public IScriptRunner getScriptRunner(String scriptingLanguage) {
        switch (scriptingLanguage) {
        case "python":
        case "jython":
            // Needed to load Jython 2.7-b3 engine.
            Options.importSite = false;
        
            ScriptEngine engine = this.scriptEngineManager.getEngineByName("jython");
        
            return (engine != null) ? new PythonRunner(engine) : null;
        default:
            return null;
        }
    }

    /**
     * Get a script runner that runs the scripts in a transaction.
     * 
     * @param scriptingLanguage a script language
     * @return the script runner.
     */
    @objid ("8f058a43-b0a0-4b8d-b8f7-10578f809852")
    public IScriptRunner getTransactionalScriptRunner(String scriptingLanguage) {
        return new TransactionScriptRunner(getScriptRunner(scriptingLanguage));
    }

}
