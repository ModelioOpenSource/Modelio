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
package org.modelio.linkeditor.panel;

import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.EventTopic;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.printing.PrinterData;
import org.eclipse.swt.widgets.Composite;
import org.modelio.linkeditor.plugin.LinkEditor;
import org.modelio.platform.core.events.ModelioEventTopics;
import org.modelio.platform.core.picking.IPickingSession;
import org.modelio.vcore.session.api.model.change.IModelChangeEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Link Editor panel provider.
 */
@objid ("8d1891c7-35d5-4cb1-95a9-87fa5d493166")
public class LinkEditorPanelProvider implements ILinkEditor {
    @objid ("93ea9df5-2fc5-413a-b175-75ebac4e2184")
    @Inject
    private IEclipseContext eclipseContext;

    /**
     * The controller of the provided link editor panel
     */
    @objid ("2197c95d-b358-46d9-bc09-6c161d29a91d")
    private LinkEditorPanelController controller;

    /**
     * The UI of the provided link editor panel
     */
    @objid ("79156853-4b7a-4d6f-82f8-eea9c7a0c201")
    private LinkEditorPanelUi ui;

    @objid ("aeba0b84-10d2-4d7b-9f01-24646d9bd461")
    private LinkEditorConfigurator configurator;

    @objid ("e3a2678d-4180-4322-80f7-b9ed9491774b")
    @Override
    public boolean isRelevantFor(Object input) {
        return (input instanceof MObject);
    }

    @objid ("a7be6b5e-f47b-4386-8820-405b2f887ef5")
    @Override
    public Composite createPanel(Composite parent) {
        this.ui = this.controller.createUi(parent);
        return this.ui.getComposite();
    }

    /**
     * Return the UI top level composite
     */
    @objid ("e2d50c7d-f8be-4636-97fb-82516c79bda0")
    @Override
    public Composite getPanel() {
        return this.ui.getComposite();
    }

    @objid ("7500de09-3b93-436f-b868-4c447722691a")
    @Override
    public String getHelpTopic() {
        return LinkEditor.I18N.getString("LinkEditorPanelProvider.HELP_TOPIC");
    }

    @objid ("63611f6f-48b1-49ca-83fd-533a62bf9146")
    @Override
    public MObject getInput() {
        return this.controller == null ? null : this.controller.getInput();
    }

    @objid ("71dd6b80-5fcf-4b95-bb84-fd85db5e843e")
    @Override
    public void setInput(Object input) {
        MObject mObj = (input instanceof MObject) ? (MObject) input : null;
        MObject curInput = getInput();
        if (! Objects.equals(mObj, curInput)) {
            this.controller.setInput(mObj);
        }
        
    }

    @objid ("41a856b2-381d-492b-b384-7f789f194fb2")
    @Override
    public void dispose() {
        if (this.ui != null && this.ui.getComposite() != null) {
            this.ui.getComposite().dispose();
        }
        preDestroy();
        
    }

    /**
     * Build a controller with injected fields.
     * @param context an Eclipse 4 context
     */
    @objid ("ea3c6550-e3f9-470d-9980-90f6db6616fb")
    @PostConstruct
    void postConstruct(IEclipseContext context) {
        LinkEditorConfiguration configurationData = new LinkEditorConfiguration();
        
        // Instantiate controller with E4 injector
        IEclipseContext staticContext = EclipseContextFactory.create();
        staticContext.set(ILinkEditorConfiguration.class, configurationData);
        this.controller = ContextInjectionFactory.make(LinkEditorPanelController.class, context, staticContext);
        staticContext.dispose();
        
        // Instantiate configurator
        this.configurator = new LinkEditorConfigurator(configurationData);
        this.configurator.addPropertyChangeListener(
                evt -> this.controller.onConfigurationChanged());
        
    }

    /**
     * Called when a model change occurred => refresh the ui contents.
     */
    @objid ("b374877a-5a02-48e8-98f9-43b5f8f311f9")
    @Override
    public void modelChanged(IModelChangeEvent event) {
        this.controller.onModelChanged();
    }

    @objid ("41166207-f43d-4c62-a454-4f1c84ac3194")
    @Override
    public void setEditMode(boolean onOff) {
        this.controller.setEditMode(onOff);
    }

    /**
     * Get the configurator for this link editor
     * @return the configurator for this link editor
     */
    @objid ("4c11f62e-6cab-47fc-b8ef-15a7a8c2d1a5")
    @Override
    public ILinkEditorConfigurator getConfigurator() {
        return this.configurator;
    }

    @objid ("00bed1a7-36ed-481e-a000-fec4181ca513")
    @Override
    public boolean isEditMode() {
        return this.controller.isEditMode();
    }

    @objid ("64c0d1cc-475b-4820-9366-fb7e8f3ee548")
    @Inject
    @Optional
    void onPickingStart(@EventTopic(ModelioEventTopics.PICKING_START) final IPickingSession session) {
        this.controller.startPicking(session);
    }

    @objid ("ff087b18-b88c-45d6-9304-6849ea4683d3")
    @Inject
    @Optional
    void onPickingStop(@SuppressWarnings("unused")
    @EventTopic(ModelioEventTopics.PICKING_STOP) final IPickingSession session) {
        this.controller.stopPicking(session);
    }

    @objid ("0d554716-da40-489a-8c31-1c4b5523a1b0")
    @PreDestroy
    void preDestroy() {
        this.configurator = null;
        this.controller = null;
        this.ui = null;
        
    }

    @objid ("b37fc864-8bf0-428a-b1a5-ece8c1bb5860")
    @Override
    public double getZoomLevel() {
        return this.controller.getZoomLevel();
    }

    @objid ("b1e5f9d2-b7d1-43fb-8ab6-9c86f173ec4f")
    @Override
    public void setZoomLevel(double level) {
        this.controller.setZoomLevel(level);
    }

    @objid ("709aedfc-9678-419f-b563-ecdfec825674")
    @Override
    public void print(PrinterData data) {
        this.controller.print(data);
    }

    @objid ("c1130b19-2607-4dac-94e9-50688bc449a0")
    @Override
    public Image getImage() {
        return new ImageBuilder().makeImage(this.ui.getGraphicalViewer().getRootEditPart());
    }

}
