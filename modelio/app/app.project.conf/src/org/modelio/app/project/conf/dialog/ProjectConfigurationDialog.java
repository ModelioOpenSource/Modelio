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

package org.modelio.app.project.conf.dialog;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.modelio.app.project.conf.plugin.AppProjectConf;
import org.modelio.gproject.gproject.GProject;
import org.modelio.platform.rcp.extensionpoint.ExtensionPointContributionManager;
import org.modelio.platform.ui.dialog.ModelioDialog;

/**
 * Project configuration dialog box.
 * <p>
 * It consists of a {@link TabFolder} displaying all the different parts of a project configuration.
 * </p>
 * <p>
 * Pages are contributed to this dialog through an eclipse extension point. See plugin.xml file...
 * </p>
 */
@objid ("a7410963-33f6-11e2-a514-002564c97630")
public final class ProjectConfigurationDialog extends ModelioDialog {
    @objid ("ae6fece5-4381-11e2-b513-002564c97630")
    private String pageToSelect;

    @objid ("050e8817-e7f6-4819-891c-6f2f06657a23")
    private static final String CONFPAGE_EXTENSION_ID = "org.modelio.app.project.conf.page";

    @objid ("a7410967-33f6-11e2-a514-002564c97630")
    protected ProjectModel model;

    @objid ("1e00c95b-7dd0-4065-b987-723a4c3b03ca")
    private final MApplication application;

    @objid ("5d149ea0-0088-4b53-bf4c-ba4e318d499c")
    private final FormToolkit toolkit;

    @objid ("5ddb8fdf-c047-4e47-8ba6-c897e561addd")
    protected TabFolder tabFolder;

    @objid ("a7410968-33f6-11e2-a514-002564c97630")
    public ProjectConfigurationDialog(final MApplication application, final GProject openedProject, final Shell parentShell) {
        super(parentShell);
        this.application = application;
        this.model = new ProjectModel(openedProject);
        this.toolkit = new FormToolkit(parentShell.getDisplay());
    }

    @objid ("a741096d-33f6-11e2-a514-002564c97630")
    @Override
    public final Control createContentArea(final Composite parent) {
        parent.addTraverseListener(new TraverseListener() {
            @Override
            public void keyTraversed(final TraverseEvent event) {
                if (event.character == SWT.CR || event.character == SWT.KEYPAD_CR || event.character == SWT.ESC) {
                    event.doit = false;
                }
            }
        });
        
        this.tabFolder = new TabFolder(parent, SWT.NONE);
        this.tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        // no layout to add on the tab folder.
        
        addPages();
        return null;
    }

    @objid ("a7410974-33f6-11e2-a514-002564c97630")
    @Override
    public void init() {
        setLogoImage(null);
        // Put the messages in the banner area
        getShell().setText(AppProjectConf.I18N.getString("ProjectConfiguration.BoxTitle")); //$NON-NLS-1$
        setTitle(AppProjectConf.I18N.getString("ProjectConfiguration.Title")); //$NON-NLS-1$
        setMessage(AppProjectConf.I18N.getString("ProjectConfiguration.Subtitle")); //$NON-NLS-1$
    }

    @objid ("a7410977-33f6-11e2-a514-002564c97630")
    @Override
    public final void addButtonsInButtonBar(final Composite parent) {
        createButton(parent, IDialogConstants.OK_ID, IDialogConstants.CLOSE_LABEL, false);
    }

    @objid ("a1616d40-3d2f-11e2-97a2-002564c97630")
    protected void addPages() {
        for (final IConfigurationElement e : new ExtensionPointContributionManager(ProjectConfigurationDialog.CONFPAGE_EXTENSION_ID).getExtensions("confpage")) {
            parseConfPage(this, e);
        }
    }

    /**
     * Select a specific page in the project configurator.
     * See constants for available page names.
     * 
     * @param page the page to select.
     */
    @objid ("ae61a48e-4381-11e2-b513-002564c97630")
    public void setSelectedPage(final String page) {
        this.pageToSelect = page;
    }

    @objid ("51a62d00-4510-11e2-a5d7-bc305ba4815c")
    @Override
    protected Point getInitialSize() {
        return new Point(1024, 768);
    }

    @objid ("6bea975f-1139-4ecf-8f3b-91b543f30410")
    protected void parseConfPage(final ProjectConfigurationDialog dlg, final IConfigurationElement elt) {
        final String label = elt.getAttribute("label");
        final String name = elt.getAttribute("name");
        Object page;
        try {
            page = elt.createExecutableExtension("page");
        
            if (page instanceof IProjectConfPage) {
                dlg.createConfPage(label, name, (IProjectConfPage) page);
            }
        
        } catch (final CoreException | NoClassDefFoundError e) {
            AppProjectConf.LOG.error(e);
        }
    }

    @objid ("d838070f-b8f3-4e34-8176-7446b4ae5d91")
    private void createConfPage(final String label, final String name, final IProjectConfPage page) {
        page.createControls(this.toolkit, this.application, this.tabFolder);
        page.setInput(this.model);
        final TabItem tabItem = new TabItem(this.tabFolder, SWT.NULL);
        tabItem.setText(label);
        tabItem.setControl(page.getControl());
        tabItem.setData(page);
        if (name.equals(this.pageToSelect)) {
            this.tabFolder.setSelection(tabItem);
        }
    }

    @objid ("5aa0afb4-6d15-4926-847c-62d2aba7e950")
    @Override
    protected String getHelpId() {
        if (this.tabFolder != null) {
            int tabIdx = this.tabFolder.getSelectionIndex();
            if (tabIdx != -1) {
                TabItem tab = this.tabFolder.getItem(tabIdx);
                IProjectConfPage page = (IProjectConfPage) tab.getData();
        
                String topic = page.getHelpTopic();
                if (topic != null && !topic.isEmpty()) {
                    return topic;
                }
            }
        }
        return AppProjectConf.I18N.getString("ProjectConfiguration.HelpId");
    }

}
