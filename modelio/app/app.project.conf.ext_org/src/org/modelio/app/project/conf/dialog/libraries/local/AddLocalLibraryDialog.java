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
package org.modelio.app.project.conf.dialog.libraries.local;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.modelio.app.project.conf.dialog.ProjectModel;
import org.modelio.app.project.conf.dialog.libraries.local.property.RamcPropertyComposite;
import org.modelio.app.project.conf.plugin.AppProjectConfExt;
import org.modelio.gproject.core.IGModelFragment;
import org.modelio.gproject.data.project.GProjectPartDescriptor;
import org.modelio.gproject.data.ramc.IModelComponentInfos;
import org.modelio.gproject.data.ramc.ModelComponentArchive;
import org.modelio.platform.ui.UIFont;
import org.modelio.platform.ui.UIImages;
import org.modelio.platform.ui.dialog.ModelioDialog;
import org.modelio.vbasic.files.FileUtils;

/**
 * Dialog box used to instantiate a new RAMC fragment descriptor to mount in a project.
 * <p>
 * A ramc fragment has only one configuration point:
 * <ul>
 * <li>A .ramc file path</li>
 * </ul>
 * </p>
 * <p>
 * Use {@link #getFragmentDescriptor()} to get the new fragment.
 * </p>
 */
@objid ("7d5357e9-3adc-11e2-916e-002564c97630")
public final class AddLocalLibraryDialog extends ModelioDialog {
    @objid ("d9f30cf3-ff30-488b-b707-a751cbfa785c")
    List<String> existingFragmentIds;

    @objid ("7d5357f0-3adc-11e2-916e-002564c97630")
    private RamcFragmentPanel panel;

    @objid ("fbfeefb4-eee4-4b88-a376-412c5f748d4b")
    private ModelComponentArchive modelComponentArchive;

    @objid ("b4ec2dca-3184-4fe1-8a86-5c59eaaa7239")
    private GProjectPartDescriptor fragmentDescriptor;

    @objid ("184b9a28-6eda-40e8-96e1-f51c29757b90")
    private RamcPropertyComposite propertyComposite;

    @objid ("2bf2034e-4e7f-4585-97d4-eead1a40920d")
    private IModelComponentInfos fragmentInfos;

    @objid ("6c5d9c12-91fc-4f02-9c11-1eae3e85fe23")
    Composite area;

    @objid ("f3c1958d-d5e8-4bd6-a747-469b071b9139")
    private Button addBtn;

    @objid ("b5816707-4ca0-4bf4-82f1-8ffd9e12a0c1")
    private final ProjectModel projectAdapter;

    @objid ("7d5357f1-3adc-11e2-916e-002564c97630")
    public  AddLocalLibraryDialog(final Shell parentShell, final ProjectModel projectAdapter) {
        super(parentShell);
        this.projectAdapter = projectAdapter;
        this.existingFragmentIds = projectAdapter.getFragmentIdList();
        
    }

    @objid ("7d5357f4-3adc-11e2-916e-002564c97630")
    @Override
    public Control createContentArea(final Composite parent) {
        // fragment data area
        this.area = new Composite(parent, SWT.NONE);
        final GridLayout layout = new GridLayout(1, false);
        layout.marginHeight = 1;
        layout.marginWidth = 1;
        layout.horizontalSpacing = 1;
        layout.verticalSpacing = 1;
        this.area.setLayout(layout);
        this.area.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        
        
        // fragment type-specific panel
        this.panel = new RamcFragmentPanel(this.area, SWT.NONE);
        final GridData gd1 = new GridData(SWT.FILL, SWT.FILL, true, false);
        // gd1.horizontalSpan = 2;
        this.panel.setLayoutData(gd1);
        
        // fragment properties composite
        final Group parametersGroup = new Group(this.area, SWT.NONE);
        parametersGroup.setText(AppProjectConfExt.I18N.getString("AddLocalLibraryDialog.Panel.title")); //$NON-NLS-1$
        final GridLayout layout2 = new GridLayout(1, true);
        layout2.marginHeight = 1;
        layout2.marginWidth = 1;
        layout2.horizontalSpacing = 1;
        layout2.verticalSpacing = 1;
        parametersGroup.setLayout(layout2);
        parametersGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        parametersGroup.setFont(UIFont.NORMALB);
        this.propertyComposite = new RamcPropertyComposite(parametersGroup, SWT.NONE, this.fragmentInfos, this.projectAdapter);
        this.propertyComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        return this.area;
    }

    @objid ("7d5357fa-3adc-11e2-916e-002564c97630")
    @Override
    public void addButtonsInButtonBar(final Composite parent) {
        createButton(parent, Window.CANCEL, IDialogConstants.CANCEL_LABEL, true);
        this.addBtn = createButton(parent, Window.OK, AppProjectConfExt.I18N.getString("AddLocalLibraryDialog.AddFragment"), true); //$NON-NLS-1$
        this.addBtn.setEnabled(false);
        
    }

    @objid ("7d5357fe-3adc-11e2-916e-002564c97630")
    @Override
    public void init() {
        getShell().setText(AppProjectConfExt.I18N.getString("AddLocalLibraryDialog.ShellTitle")); //$NON-NLS-1$
        setTitle(AppProjectConfExt.I18N.getString("AddLocalLibraryDialog.Title")); //$NON-NLS-1$
        setMessage(AppProjectConfExt.I18N.getString("AddLocalLibraryDialog.Message")); //$NON-NLS-1$
        final Point parentLocation = getShell().getParent().getLocation();
        
        getShell().setLocation(parentLocation.x + 100, parentLocation.y + 100);
        getShell().setSize(600, 700);
        getShell().setMinimumSize(600, 700);
        
    }

    @objid ("7d535807-3adc-11e2-916e-002564c97630")
    @Override
    protected void okPressed() {
        // FIXME Replace with the following code to allow HTTP ramc fragments
        // try(UriPathAccess acc = new UriPathAccess(URI, IAuthData)) {
        // Path p = acc.getPath();
        final Path archivePath = Paths.get(this.panel.text.getText());
        this.modelComponentArchive = new ModelComponentArchive(archivePath, true);
        try {
            this.fragmentDescriptor = this.modelComponentArchive.getFragmentDescriptor();
        } catch (final IOException e) {
            // Display and log the message, and prevent dialog from closing.
            AppProjectConfExt.LOG.error(e);
            setErrorMessage(FileUtils.getLocalizedMessage(e));
            return;
        } catch (final RuntimeException e) {
            // Display and log the message, and prevent dialog from closing.
            AppProjectConfExt.LOG.error(e);
            setErrorMessage(MessageFormat.format(
                    "Unexpected {0}: {1}",
                    e.getClass().getSimpleName(),
                    e.getLocalizedMessage()));
            return;
        }
        
        super.okPressed();
        
    }

    @objid ("7d53580a-3adc-11e2-916e-002564c97630")
    public GProjectPartDescriptor getFragmentDescriptor() {
        return this.fragmentDescriptor;
    }

    @objid ("4d93dac4-342c-422b-8be0-d2287e3339af")
    public ModelComponentArchive getModelComponentArchive() {
        return this.modelComponentArchive;
    }

    @objid ("13e84e83-b486-452e-95ec-7aefd59f1357")
    void showFragmentInfos() {
        this.fragmentInfos = getFragmentInfos();
        if (this.fragmentInfos != null) {
            this.propertyComposite.setFragmentInfos(this.fragmentInfos);
            this.propertyComposite.refresh();
        }
        this.area.layout();
        
    }

    @objid ("6fc7d5f2-6ed1-4536-8ec3-a49322fe7587")
    IModelComponentInfos getFragmentInfos() {
        IModelComponentInfos infos = null;
        final Path archivePath = Paths.get(this.panel.text.getText());
        final ModelComponentArchive archive = new ModelComponentArchive(archivePath, true);
        try {
            infos = archive.getInfos();
            setErrorMessage(null);
        } catch (final IOException error) {
            setErrorMessage(FileUtils.getLocalizedMessage(error));
            AppProjectConfExt.LOG.error(error);
        }
        return infos;
    }

    @objid ("9647a0db-a39b-4887-b87a-acc0fdb22d5e")
    void isFragmentValid() {
        boolean valid = this.fragmentInfos != null;
        if (valid) {
            final String fragmentId = this.fragmentInfos.getName() + " " + this.fragmentInfos.getVersion();
            if (this.existingFragmentIds.contains(fragmentId)) {
                valid = false; // already exist
                setErrorMessage(AppProjectConfExt.I18N.getMessage("AddLocalLibraryDialog.ErrorMessage.AlreadyExist", fragmentId));
            }
        }
        
        if (valid) {
            this.addBtn.setEnabled(true);
            this.panel.text.setForeground(this.panel.text.getDisplay().getSystemColor(SWT.COLOR_DARK_GREEN));
            setErrorMessage(null);
        } else {
            this.addBtn.setEnabled(false);
            this.panel.text.setForeground(this.panel.text.getDisplay().getSystemColor(SWT.COLOR_RED));
        }
        
    }

    @objid ("12aade3b-8453-4487-b8b1-d59d97e7b334")
    @Override
    protected String getHelpId() {
        return AppProjectConfExt.I18N.getString("AddLocalLibraryDialog.HelpId");
    }

    /**
     * Model component fragment panel.
     */
    @objid ("7d55b962-3adc-11e2-916e-002564c97630")
    private class RamcFragmentPanel extends Composite {
        @objid ("ac32e19a-4d60-407d-8444-ccdb4a5251fc")
        protected Text text;

        /**
         * Initialize the panel.
         * @param parent the parent composite.
         * @param style the style of widget to construct
         */
        @objid ("7d55b965-3adc-11e2-916e-002564c97630")
        public  RamcFragmentPanel(final Composite parent, final int style) {
            super(parent, style);
            
            createContents(this);
            
        }

        @objid ("7d55b96e-3adc-11e2-916e-002564c97630")
        private void createContents(final Composite parent) {
            final GridLayout layout = new GridLayout(3, false);
            setLayout(layout);
            
            final Label label = new Label(this, SWT.NONE);
            label.setText(AppProjectConfExt.I18N.getString("AddLocalLibraryDialog.Panel.label")); //$NON-NLS-1$
            
            this.text = new Text(this, SWT.BORDER);
            this.text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
            
            final Button button = new Button(this, SWT.PUSH);
            button.setImage(UIImages.FILECHOOSE);
            
            button.addSelectionListener(new SelectionListener() {
            
                @Override
                public void widgetSelected(final SelectionEvent e) {
                    final FileDialog fd = new FileDialog(getShell(), SWT.OPEN);
                    fd.setText(AppProjectConfExt.I18N.getString("AddLocalLibraryDialog.Panel.select")); //$NON-NLS-1$
                    final String[] filterExt = { "*.ramc" }; //$NON-NLS-1$
                    fd.setFilterExtensions(filterExt);
                    final String selected = fd.open();
                    if (selected != null) {
                        RamcFragmentPanel.this.text.setText(selected);
                        showFragmentInfos();
                        // test exist
                        isFragmentValid();
                    }
                }
            
                @Override
                public void widgetDefaultSelected(final SelectionEvent e) {
                    // nothing to do
                }
            
            });
            setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
            return;
        }

        @objid ("7d55b972-3adc-11e2-916e-002564c97630")
        public void setEdited(final IGModelFragment fragment) {
            this.text.setText(fragment.getDescriptor().getLocation().toString());
        }

    }

}
