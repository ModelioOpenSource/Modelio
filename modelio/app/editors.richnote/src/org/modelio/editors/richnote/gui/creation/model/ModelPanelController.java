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

package org.modelio.editors.richnote.gui.creation.model;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.modelio.editors.richnote.gui.creation.RichNoteDescriptor;
import org.modelio.metamodel.uml.infrastructure.ResourceType;
import org.modelio.ui.panel.IPanelListener;

@objid ("5b6da15d-c996-4a04-9514-13f84a95a729")
class ModelPanelController {
    @objid ("bca0d8ec-3200-47ae-8523-085551df75f8")
    private RichNoteDescriptor data;

    @objid ("1e414fbe-b332-4ea5-bbaa-8a483cfc8897")
    private List<IPanelListener> listeners = new ArrayList<>();

    @objid ("13323bb8-2279-4971-a469-fbc11896b8fa")
    private ModelPanelUI ui;

    @objid ("6efb8a11-9c96-45e1-8c60-8f6b2e2edf03")
    public synchronized void addListener(IPanelListener l) {
        if (this.listeners.contains(l)) {
            throw new InvalidParameterException("Listener already registered");
        }
        this.listeners.add(l);
    }

    @objid ("23228778-3b2e-4c38-94e0-95e2d435646c")
    public Control createUi(Composite parent) {
        this.ui = new ModelPanelUI(this);
        Control control = this.ui.createUI(parent);
        this.ui.update(this.data);
        return control;
    }

    @objid ("405640a7-b53d-46eb-8bf2-2106edb632ee")
    public void dispose() {
        this.ui.dispose();
        this.ui = null;
    }

    @objid ("db671ba3-d1ff-4630-ae9a-b8c52bca1804")
    public RichNoteDescriptor getData() {
        return this.data;
    }

    @objid ("2ed209c0-dcab-43bd-9d9d-f2c1b18da870")
    public Control getUi() {
        return this.ui.top;
    }

    @objid ("d61a6c6b-5110-414a-9e99-9aed02b0eaf3")
    public void onAbstractChanged(String value, boolean isValidate) {
        // Make sure there is a change
        if (Objects.equals(this.data.getAbstract(), value)) {
            return;
        }
        
        this.data.setAbstract(value);
        if (isValidate) {
            this.ui.update(this.data);
        }
        fireListeners(this.data, isValidate);
    }

    @objid ("7a712fd2-add2-4516-bde4-21c0af9c1943")
    public void onDocTypeChanged(ResourceType value) {
        // Make sure there is a change
        if (Objects.equals(this.data.getDocumentType(), value)) {
            return;
        }
        
        this.data.setDocumentType(value);
        fireListeners(this.data, true);
    }

    @objid ("0bfb16d1-119e-43d0-a610-177f0dc69850")
    public void onNameChanged(String value, boolean isValidate) {
        // Make sure there is a change
        if (Objects.equals(this.data.getName(), value)) {
            return;
        }
        
        this.data.setName(value);
        if (isValidate) {
            this.ui.update(this.data);
        }
        fireListeners(this.data, isValidate);
    }

    @objid ("59c1eb85-4d96-43db-ba04-8e0350d1b8e0")
    public synchronized void removeListener(IPanelListener l) {
        this.listeners.remove(l);
    }

    @objid ("9740c450-73a4-413e-b371-537e96e55846")
    public void setData(RichNoteDescriptor data) {
        this.data = data;
        if (this.ui != null) {
            this.ui.update(this.data);
        }
    }

    @objid ("f5f08bf0-35cf-47f1-bbf9-8b4d37ef57f6")
    private void fireListeners(RichNoteDescriptor wizardData, boolean isValidate) {
        this.listeners.forEach(l -> l.dataChanged(wizardData, isValidate));
    }

}
