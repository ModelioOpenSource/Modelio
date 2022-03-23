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

import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.script.ScriptException;
import org.eclipse.jface.viewers.ISelection;
import org.modelio.metamodel.uml.infrastructure.Element;

@objid ("0077808a-cbcd-1065-a2b8-001ec947cd2a")
public interface IScriptRunner {
    @objid ("0077c20c-cbcd-1065-a2b8-001ec947cd2a")
    void runFile(Path file, ISelection selection, Collection<Element> selectedElements) throws ScriptException;

    @objid ("0077d2ec-cbcd-1065-a2b8-001ec947cd2a")
    void runScript(String script, ISelection selection, Collection<Element> elements) throws ScriptException;

    @objid ("0077ec96-cbcd-1065-a2b8-001ec947cd2a")
    void setCommandStream(PrintWriter stream);

    @objid ("0077f722-cbcd-1065-a2b8-001ec947cd2a")
    void setOutputStream(PrintWriter stream);

    @objid ("007801d6-cbcd-1065-a2b8-001ec947cd2a")
    void setErrorStream(PrintWriter stream);

    @objid ("00780cf8-cbcd-1065-a2b8-001ec947cd2a")
    void bind(String name, Object value);

    @objid ("00781d42-cbcd-1065-a2b8-001ec947cd2a")
    void unbind(String name);

    @objid ("007828c8-cbcd-1065-a2b8-001ec947cd2a")
    Object getBinding(String key);

    @objid ("0079753e-99aa-10ed-8812-001ec947cd2a")
    javax.script.ScriptEngine getEngine();

    @objid ("007994c4-99aa-10ed-8812-001ec947cd2a")
    void clearClassloader();

    @objid ("00799c58-99aa-10ed-8812-001ec947cd2a")
    void addClassLoader(ClassLoader base);

}
