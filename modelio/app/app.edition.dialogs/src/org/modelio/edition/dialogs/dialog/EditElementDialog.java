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
package org.modelio.edition.dialogs.dialog;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.modelio.edition.dialogs.PanelDescriptor;
import org.modelio.edition.dialogs.plugin.EditionDialogs;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.platform.mda.infra.ModuleI18NService;
import org.modelio.platform.model.ui.MetamodelLabels;
import org.modelio.platform.ui.dialog.ModelioDialog;
import org.modelio.platform.ui.panel.IPanelProvider;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.model.change.IModelChangeEvent;
import org.modelio.vcore.session.api.model.change.IModelChangeListener;
import org.modelio.vcore.session.api.model.change.IStatusChangeEvent;
import org.modelio.vcore.session.api.model.change.IStatusChangeListener;

/**
 * Modelio Dialog box displaying the properties of a model element for edition.
 * <p>
 * The dialog box displays several tabs:
 * <ul>
 * <li>a specialized {@link IPanelProvider} instance to actually gather the UML properties and tagged values tables of the edited
 * element and to display/edit them. The specialized {@link IPanelProvider} implementation class is obtained from the
 * {@link PanelDescriptor}. The specialized panel can be thought as an edition wizard providing advanced viewing and edition of
 * an object.</li>
 * <li>a notes & constraints panel displaying the notes and constrains held by the edited element</li>
 * <li>a generic UML/MDA view of the edited element where UML properties and MDA annotations are displayed in a generic way (table
 * of properties)</li>
 * <li>an audit tab where the current audit reports for the edited element are displayed.</li>
 * <li>several module tabs, where the specific views provided by the modules for the edited element are shown.
 * </ul>
 * The dialog installs itself as a {@link IModelChangeListener} whose strategy consists in re-setting the input of the element panel
 * each time a model change event occurs. It also deals with deleted object by closing the dialog and invalid object by setting the
 * input of the element panel to null.
 */
@objid ("61e20035-5087-4ab7-9840-43bcce555ecd")
public class EditElementDialog extends ModelioDialog implements IModelChangeListener, IStatusChangeListener {
    @objid ("da10a0a4-9f38-443f-9c4c-4d1aec55349d")
    private TabFolder tabFolder;

    @objid ("07a9e0a7-6a31-4e38-98f1-844b797c5888")
    private ICoreSession coreSession;

    /**
     * The model element being displayed and edited
     */
    @objid ("2ae84722-7112-417f-991b-27c45ec4247c")
    private Element editedElement;

    @objid ("108bc72c-92a3-44bb-8471-baf7ed789b5b")
    private List<PanelDescriptor> panelDescriptors;

    @objid ("12733530-6b5e-4fa4-85ea-7f0df9a3ce2a")
    private List<IPanelProvider> tabbedPanels = new ArrayList<>();

    /**
     * @param parentShell the parent SWT shell
     * @param panelDescriptors the dialog panel descriptors
     * @param coreSession the modeling session
     */
    @objid ("e11d9728-db21-4462-8460-cd4e87f90d44")
    public  EditElementDialog(Shell parentShell, List<PanelDescriptor> panelDescriptors, ICoreSession coreSession) {
        super(parentShell);
        this.coreSession = coreSession;
        this.panelDescriptors = panelDescriptors;
        
    }

    @objid ("8fa7c864-c068-11e1-8c0a-002564c97630")
    @Override
    public void addButtonsInButtonBar(Composite parent) {
        Button closeButton = createButton(parent, IDialogConstants.CLOSE_ID, IDialogConstants.CLOSE_LABEL, true);
        closeButton.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                EditElementDialog.this.close();
            }
        
            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
                EditElementDialog.this.close();
            }
        });
        return;
    }

    @objid ("d3781c49-5caf-42d9-a418-c31d718ba520")
    @Override
    public Control createContentArea(final Composite parent) {
        this.tabFolder = new TabFolder(parent, SWT.BORDER);
        
        Point preferredSize = new Point(800, 600); // editPanel.getPreferredSize();
        GridData gd = new GridData(preferredSize.x, preferredSize.y);
        gd.horizontalAlignment = SWT.FILL;
        gd.grabExcessHorizontalSpace = true;
        gd.verticalAlignment = SWT.FILL;
        gd.grabExcessVerticalSpace = true;
        this.tabFolder.setLayoutData(gd);
        
        // Create the panels
        for (PanelDescriptor desc : this.panelDescriptors) {
            IPanelProvider panel = desc.getPanel();
            if (panel != null) {
                createTabbedPanel(this.tabFolder, panel, desc.getPanelLabel());
            }
        }
        
        // Set the input
        setEditedElement(this.editedElement);
        
        // This listener avoids closing the dialog when pressing enter to validate an edition field.
        parent.addTraverseListener(new TraverseListener() {
            @Override
            public void keyTraversed(TraverseEvent event) {
                if ((event.character == SWT.CR) || (event.character == SWT.KEYPAD_CR) || (event.character == SWT.ESC)) {
                    event.doit = false;
                }
            }
        });
        
        // Plug model change listener ( the listener is un-plugged in the close() method)
        if (this.coreSession != null) {
            this.coreSession.getModelChangeSupport().addModelChangeListener(this);
            this.coreSession.getModelChangeSupport().addStatusChangeListener(this);
        }
        
        // Un-plug model change listener on Shell disposal
        parent.getShell().addDisposeListener((e) -> {
            if (this.coreSession != null) {
                this.coreSession.getModelChangeSupport().removeModelChangeListener(this);
                this.coreSession.getModelChangeSupport().removeStatusChangeListener(this);
                this.coreSession = null;
            }
        
            for (IPanelProvider panelProvider : this.tabbedPanels) {
                panelProvider.dispose();
            }
        });
        return this.tabFolder;
    }

    @objid ("8fa7c861-c068-11e1-8c0a-002564c97630")
    @Override
    public void init() {
        setLogoImage(null);
        // Put the messages in the banner area
        final String name = this.editedElement.getName();
        setTitle(EditionDialogs.I18N.getMessage("EditElementDialog.Title", name, name.length()));
        final String type;
        if (this.editedElement instanceof ModelElement && !((ModelElement) this.editedElement).getExtension().isEmpty()) {
            type = ModuleI18NService.getStereotypeLabel((ModelElement) this.editedElement);
        } else {
            type = MetamodelLabels.getString(this.editedElement.getMClass().getName());
        }
        setMessage(EditionDialogs.I18N.getMessage("EditElementDialog.Message", type));
        getShell().setText(EditionDialogs.I18N.getMessage("EditElementDialog.shellTitle"));
        
        // Center the shell on the screen
        getShell().pack(true);
        Point size = getShell().getSize();
        Rectangle parentBounds = getShell().getParent().getBounds();
        getShell().setLocation(parentBounds.x + parentBounds.width / 2 - size.x / 2,
                parentBounds.y + parentBounds.height / 2 - size.y / 2);
        
    }

    @objid ("81119e6d-3446-4d79-8566-c5fe980e241f")
    @Override
    public void modelChanged(IModelChangeEvent event) {
        getShell().getDisplay().syncExec(() -> {
            // Deal with deleted and invalid elements
            if (this.editedElement != null) {
                if (this.editedElement.isDeleted()) {
                    close();
                    return;
                }
                if (!this.editedElement.isValid()) {
                    for (IPanelProvider panel : this.tabbedPanels) {
                        panel.setInput(null);
                    }
                    return;
                }
            }
            // Simplest strategy here : setInput on element Panel
            StructuredSelection input = new StructuredSelection(this.editedElement);
            for (IPanelProvider panel : this.tabbedPanels) {
                panel.setInput(input);
            }
        });
        
    }

    /**
     * Set the edited element.
     * @param editedElement the new edited element.
     */
    @objid ("8fa7c869-c068-11e1-8c0a-002564c97630")
    public void setEditedElement(Element editedElement) {
        if (editedElement != null) {
            ISelection selection = new StructuredSelection(editedElement);
            for (IPanelProvider panel : this.tabbedPanels) {
                try {
                    panel.setInput(selection);
                } catch (Throwable e) {
                    EditionDialogs.LOG.error(e);
                }
            }
        }
        this.editedElement = editedElement;
        
    }

    @objid ("b3cf7cfc-844c-4b19-b82c-6c561580b543")
    @Override
    public void statusChanged(IStatusChangeEvent event) {
        getShell().getDisplay().asyncExec(() -> {
            // Deal with deleted and invalid elements
            if (this.editedElement != null) {
                if (this.editedElement.isDeleted()) {
                    close();
                    return;
                }
                if (!this.editedElement.isValid()) {
                    for (IPanelProvider panel : this.tabbedPanels) {
                        panel.setInput(null);
                    }
                    return;
                }
            }
            // Simplest strategy here : setInput on element Panel
            ISelection selection = new StructuredSelection(this.editedElement);
            for (IPanelProvider panel : this.tabbedPanels) {
                panel.setInput(selection);
            }
        });
        
    }

    /**
     * Return the selected tab help topic or the dialog topic if no help is defined for the active panel.
     */
    @objid ("38e0e5c2-af12-4357-a084-60a0240e0685")
    @Override
    protected String getHelpId() {
        // try to use the selected panel help
        if (this.tabFolder != null) {
            int tabIdx = this.tabFolder.getSelectionIndex();
            if (tabIdx != -1) {
                String topic = this.tabbedPanels.get(tabIdx).getHelpTopic();
                if (topic != null && !topic.isEmpty()) {
                    return topic;
                }
            }
        }
        
        // return dialog help
        return EditionDialogs.I18N.getString("EditElementDialog.HELP_TOPIC");
    }

    @objid ("fe6a8847-a680-4d31-9b16-3bbbc2354a0f")
    private IPanelProvider createTabbedPanel(TabFolder aTabFolder, IPanelProvider panel, String label) {
        TabItem tabItem = new TabItem(aTabFolder, SWT.NULL);
        tabItem.setText(label);
        
        // GTK3 issue (see https://bugs.eclipse.org/bugs/show_bug.cgi?id=534089)
        // If the panel contains a table using 'composite' cell editors seems that we MUST call tabItem.setControl(c);
        // BEFORE creating the table
        // As a workaround use an intermediate Composite between the panel provider control and the tab item. This way the composite can be set to the tab item before creating the panel (ie the table).
        Composite workaroundComposite = new Composite(aTabFolder, SWT.NONE);
        workaroundComposite.setLayout(new GridLayout(1, false));
        // Set the tabItem control immediately
        tabItem.setControl(workaroundComposite);
        
        // Now create the panel possibly using a table with composite cell editors balh blah blah ...
        Control top = (Control) panel.createPanel(workaroundComposite);
        top.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        this.tabbedPanels.add(panel);
        return panel;
    }

}
