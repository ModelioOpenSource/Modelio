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

package org.modelio.linkeditor.panel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.linkeditor.panel.ILinkEditorConfiguration.Orientation;

@objid ("f3b3150b-e222-44f1-b0a1-6c36a5bee410")
class LinkEditorConfigurator implements ILinkEditorConfigurator {
    @objid ("da4fac31-cb20-460a-a330-43a37899ddc6")
    private boolean batch = false;

    /**
     * The configuration data managed by this configurator
     */
    @objid ("ecc0b992-6c4a-4721-9e2a-9fcc7ca1233c")
    private LinkEditorConfiguration config;

    @objid ("f9633a7a-77aa-4290-93fd-d31f6ee3c768")
    private PropertyChangeSupport listeners = new PropertyChangeSupport(this);

    @objid ("8bf02c63-0dff-455e-916d-8d606ef000a3")
    public LinkEditorConfigurator(LinkEditorConfiguration config) {
        this.config = config;
    }

    @objid ("432635be-54f8-4c48-bb56-f7047dabf7c6")
    @Override
    public int getLeftDepth() {
        return this.config.getLeftDepth();
    }

    @objid ("de9cd8a6-16f5-4f83-b2a9-3ebe3129fded")
    @Override
    public int getRightDepth() {
        return this.config.getRightDepth();
    }

    @objid ("d3e2954b-3752-47b4-a1ec-f54b8506f897")
    @Override
    public void setLeftDepth(int depth) {
        this.config.setLeftDepth(depth);
        refreshLinkEditor();
    }

    @objid ("220a989a-8299-4c28-bb06-4ec475483b9e")
    @Override
    public void setRightDepth(int depth) {
        this.config.setRightDepth(depth);
        refreshLinkEditor();
    }

    @objid ("f57a76aa-4e9d-4b5c-8378-cbbdff18bbf7")
    @Override
    public Orientation getLayoutOrientation() {
        return this.config.getLayoutOrientation();
    }

    @objid ("d7b4f129-a54e-442a-911a-6e81654d9ee6")
    @Override
    public void setLayoutOrientation(Orientation o) {
        switch (o) {
        
        case Vertical:
        case Horizontal:
            // The user chose an effective orientation => use it
            this.config.setLayoutOrientation(o);
        case Auto:
            // The user rely on the current configuration preferred orientation
        
        }
        
        refreshLinkEditor();
    }

    @objid ("1bc506bb-df83-4d12-a397-24327103136c")
    @Override
    public ILinkEditorFilter getLinkFilter() {
        return this.config.getLinkFilter();
    }

    @objid ("f84a5b62-d4f7-4743-afae-d3f6166d8817")
    @Override
    public void setLinkFilter(ILinkEditorFilter linkFilter) {
        this.config.setLinkFilter(linkFilter);
        refreshLinkEditor();
    }

    @objid ("fac662da-1a07-48dd-bf2c-7dc7a361566f")
    private void refreshLinkEditor() {
        if (!this.batch) {
            firePropertyChange();
        }
    }

    @objid ("6db8979f-1408-4816-a581-2878a4c5a669")
    @Override
    public void apply(ILinkEditorConfiguration aConfig) {
        // Disable refresh while updating several fields of the configuration
        this.batch = true;
        this.config.setLeftDepth(aConfig.getLeftDepth());
        this.config.setRightDepth(aConfig.getRightDepth());
        this.config.setLayoutOrientation(aConfig.getLayoutOrientation());
        this.config.setLinkFilter(aConfig.getLinkFilter());
        // Now let's refresh once
        this.batch = false;
        refreshLinkEditor();
    }

    @objid ("30dc6a2c-48d3-46f3-9a61-5b7edf72c3ca")
    private void firePropertyChange() {
        // FIXME fire a relevant event
        this.listeners.firePropertyChange(new PropertyChangeEvent(this, "CONFIG", null, null));
    }

    @objid ("34d41839-09bd-4122-bd10-3716e14b5ebd")
    @Override
    public void addPropertyChangeListener(final PropertyChangeListener listener) {
        // Making sure a listener is only added once.
        this.listeners.removePropertyChangeListener(listener);
        this.listeners.addPropertyChangeListener(listener);
    }

    @objid ("03362eec-2c69-4849-9ec7-770ffe3c102f")
    @Override
    public void removePropertyChangeListener(final PropertyChangeListener listener) {
        this.listeners.removePropertyChangeListener(listener);
    }

}
