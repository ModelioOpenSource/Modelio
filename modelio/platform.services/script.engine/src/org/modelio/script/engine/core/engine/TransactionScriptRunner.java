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

package org.modelio.script.engine.core.engine;

import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.Collection;
import javax.script.ScriptEngine;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ISelection;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.metamodel.uml.infrastructure.Element;

/**
 * {@link IScriptRunner Script runner} that opens a transaction before running the script.
 */
@objid ("cd5de623-03b2-48a5-a644-b54167250a04")
public class TransactionScriptRunner implements IScriptRunner {
    @objid ("7ea62848-2583-476e-b8b9-f830225fd3b7")
    private IScriptRunner runner;

    /**
     * Constructor
     * 
     * @param runner the adapted runner.
     */
    @objid ("89cc69b2-ed76-461f-b726-b29fd740d516")
    public TransactionScriptRunner(IScriptRunner runner) {
        this.runner = runner;
    }

    @objid ("415360a8-b3c2-4772-af92-2f22c4270743")
    @Override
    public void runFile(Path file, ISelection selection, Collection<Element> selectedElements) {
        IModelingSession modelingSession = (IModelingSession) getBinding("modelingSession");
        if (modelingSession != null) {
            try (ITransaction t = modelingSession.createTransaction("Execute " + file + " file")) {
                this.runner.runFile(file, selection, selectedElements);
                t.commit();
            }
        } else {
            this.runner.runFile(file, selection, selectedElements);
        }
    }

    @objid ("c425984b-9c4d-4f6b-94f2-ca8584381121")
    @Override
    public void runScript(String script, ISelection selection, Collection<Element> elements) {
        IModelingSession modelingSession = (IModelingSession) getBinding("modelingSession");
        if (modelingSession != null) {
            try (ITransaction t = modelingSession.createTransaction("Execute script.")) {
                this.runner.runScript(script, selection, elements);
                t.commit();
            }
        } else {
            this.runner.runScript(script, selection, elements);
        }
    }

    @objid ("3d609f2f-8046-453b-94e9-8b09584bd06e")
    @Override
    public void setCommandStream(PrintWriter stream) {
        this.runner.setCommandStream(stream);
    }

    @objid ("b9b57916-b83b-4aeb-b632-392ab4db8732")
    @Override
    public void setOutputStream(PrintWriter stream) {
        this.runner.setOutputStream(stream);
    }

    @objid ("64a412d1-ec55-45a5-9ae7-4cd75ce78a54")
    @Override
    public void setErrorStream(PrintWriter stream) {
        this.runner.setErrorStream(stream);
    }

    @objid ("ba92d717-a28a-48c5-9d1b-1c8870521629")
    @Override
    public void bind(String name, Object value) {
        this.runner.bind(name, value);
    }

    @objid ("4dd3ff1c-c8ba-42c4-a60e-5f7acf347f77")
    @Override
    public void unbind(String name) {
        this.runner.unbind(name);
    }

    @objid ("fd38d081-f623-4e87-b254-5ff4324f1f46")
    @Override
    public Object getBinding(String key) {
        return this.runner.getBinding(key);
    }

    @objid ("dab350e2-7d07-4730-b39b-084e36c51add")
    @Override
    public ScriptEngine getEngine() {
        return this.runner.getEngine();
    }

    @objid ("d0f905ac-a8c1-42e1-a74e-ae99a516769a")
    @Override
    public void clearClassloader() {
        this.runner.clearClassloader();
    }

    @objid ("5b37619d-8ce8-4fa5-8741-00af166b513b")
    @Override
    public void addClassLoader(ClassLoader base) {
        this.runner.addClassLoader(base);
    }

}
