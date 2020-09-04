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

package org.modelio.editors.richnote.gui.creation.file;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.modelio.editors.richnote.api.RichNoteFormat;
import org.modelio.editors.richnote.gui.creation.RichNoteDescriptor.CreationMode;
import org.modelio.editors.richnote.gui.creation.RichNoteDescriptor;
import org.modelio.ui.panel.IPanelListener;

@objid ("6ce1862e-2e03-4d5d-8601-a64b9317f321")
class FilePanelController {
    @objid ("603b2967-f459-4f72-95f3-45d185b8242e")
    private RichNoteDescriptor data;

    @objid ("f56ab803-ce4d-4545-8572-4828dc8443f2")
    private List<IPanelListener> listeners = new ArrayList<>();

    @objid ("7787352a-bba0-4d75-bc9b-897ae8bd9958")
    private FilePanelUI ui;

    @objid ("b7bf316e-7add-4ef6-bf36-7db9aa348c1c")
    public synchronized void addListener(IPanelListener l) {
        if (this.listeners.contains(l)) {
            throw new InvalidParameterException("Listener already registered");
        }
        this.listeners.add(l);
    }

    @objid ("a28e3da1-018e-4b59-81d0-ef7134eb8ccc")
    public Control createUi(Composite parent) {
        this.ui = new FilePanelUI(this);
        Control control = this.ui.createUI(parent);
        this.ui.update(this.data);
        return control;
    }

    @objid ("8c3ba246-980d-4227-be30-dd74e55e27f1")
    public void dispose() {
        this.ui.dispose();
        this.ui = null;
    }

    @objid ("5cf14a9c-da9f-4e45-9f8f-54fa89697931")
    public RichNoteDescriptor getData() {
        return this.data;
    }

    @objid ("04be4246-2759-4a9d-b895-76d68f402a12")
    public Control getUi() {
        return this.ui.top;
    }

    @objid ("5b149659-3c8c-48b0-82d6-99d045c75c09")
    public void onContentChanged(String value, boolean isValidate) {
        // Make sure there is a change
        if (Objects.equals(this.data.getPath(), value)) {
            return;
        }
        
        this.data.setPath(value);
        if (isValidate) {
            this.ui.update(this.data);
        }
        fireListeners(this.data, isValidate);
    }

    @objid ("ce312b3a-72c2-4100-ad63-875c0afcff65")
    public void onInitializeChanged(boolean selection) {
        this.ui.update(this.data);
    }

    @objid ("2b9f28b8-2531-4a4e-9a88-6c3a72b8465f")
    public void onMimeTypeSelected(RichNoteFormat value) {
        // Make sure there is a change
        if (Objects.equals(this.data.getChosenMimeType(), value)) {
            return;
        }
        
        this.data.setMimeType(value);
        
        this.ui.update(this.data);
        fireListeners(this.data, true);
    }

    @objid ("71135f3b-cf3a-40b2-91cf-603da8e1a4be")
    public void onSetCreationMode(CreationMode value) {
        // Make sure there is a change
        if (Objects.equals(this.data.getCreationMode(), value)) {
            return;
        }
        
        this.data.setCreationMode(value);
        
        this.ui.update(this.data);
        fireListeners(this.data, true);
    }

    @objid ("00d625de-865f-434f-bd62-253037a280ec")
    public synchronized void removeListener(IPanelListener l) {
        this.listeners.remove(l);
    }

    @objid ("1ed552dc-ef4e-46a9-8652-e451dfe94b66")
    public void setData(RichNoteDescriptor data) {
        this.data = data;
        if (this.ui != null) {
            this.ui.update(this.data);
        }
    }

    @objid ("29bd7a93-d132-4910-9a82-5d991a0b7098")
    private void fireListeners(RichNoteDescriptor wizardData, boolean isValidate) {
        this.listeners.forEach(l -> l.dataChanged(wizardData, isValidate));
    }

    @objid ("4fa39a8a-f9d2-4e80-9ed7-f26bff762ea1")
    public void setErrorIndication(String s) {
        this.ui.setErrorIndication(s);
    }

}
