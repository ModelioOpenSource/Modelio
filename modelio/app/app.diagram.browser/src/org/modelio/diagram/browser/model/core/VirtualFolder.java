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

package org.modelio.diagram.browser.model.core;

import com.modeliosoft.modelio.javadesigner.annotations.mdl;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.modelio.metamodel.uml.infrastructure.Element;

/**
 * A VirtualFolder is used to render a non modifiable folder in the browser tree.
 * 
 * A VirtualFolder holds a object 'delegate' that is passed at construction time and that can be retrieved later in the
 * VirtualFolder method implementations. The delegate object holds no particular semantic.
 * 
 * A VirtualFolder can also hold an optional "represented element", which is an Element used to choose an icon for the folder in he
 * tree. This mechanism allows for defining folders that actually display as model elements (although they do not provide ANY
 * edition facility).
 * 
 * 
 * @author pvlaemyn
 */
@objid ("003b0830-0d4f-10c6-842f-001ec947cd2a")
public abstract class VirtualFolder implements ITreeContentProvider {
    @mdl.prop
    @objid ("003b7c7a-0d4f-10c6-842f-001ec947cd2a")
    private Element representedElement;

    @mdl.propgetter
    public Element getRepresentedElement() {
        // Automatically generated method. Please do not modify this code.
        return this.representedElement;
    }

    @mdl.propsetter
    public void setRepresentedElement(Element value) {
        // Automatically generated method. Please do not modify this code.
        this.representedElement = value;
    }

    @mdl.prop
    @objid ("00734d80-0d6c-10c6-842f-001ec947cd2a")
    private String name;

    @mdl.propgetter
    public String getName() {
        // Automatically generated method. Please do not modify this code.
        return this.name;
    }

    @mdl.propsetter
    public void setName(String value) {
        // Automatically generated method. Please do not modify this code.
        this.name = value;
    }

    @objid ("003b75f4-0d4f-10c6-842f-001ec947cd2a")
    private final Object delegate;

    @objid ("003c16d0-0d4f-10c6-842f-001ec947cd2a")
    public VirtualFolder(Object delegate) {
        this.delegate = delegate;
    }

    @objid ("003c4b28-0d4f-10c6-842f-001ec947cd2a")
    public Object getDelegate() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.delegate;
    }

    @objid ("003c6838-0d4f-10c6-842f-001ec947cd2a")
    @Override
    public void dispose() {
        // do nothing
    }

    @objid ("003c8016-0d4f-10c6-842f-001ec947cd2a")
    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        // do nothing
    }

    @objid ("003ca7c6-0d4f-10c6-842f-001ec947cd2a")
    @Override
    public Object[] getElements(Object inputElement) {
        return null;
    }

}
