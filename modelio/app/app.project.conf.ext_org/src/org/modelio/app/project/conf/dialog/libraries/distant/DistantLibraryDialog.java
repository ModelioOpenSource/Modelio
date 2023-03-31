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
package org.modelio.app.project.conf.dialog.libraries.distant;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystemException;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.URIUtil;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.modelio.app.project.conf.dialog.common.OptionalAuthPanelProvider;
import org.modelio.app.project.conf.dialog.common.ScopeHelper;
import org.modelio.app.project.conf.plugin.AppProjectConfExt;
import org.modelio.gproject.core.IGModelFragment;
import org.modelio.gproject.data.project.DefinitionScope;
import org.modelio.gproject.data.project.GProjectPartDescriptor;
import org.modelio.gproject.data.project.GProjectPartDescriptor.GProjectPartType;
import org.modelio.gproject.data.project.GProperties;
import org.modelio.gproject.data.project.auth.AuthDescriptor;
import org.modelio.platform.ui.UIColor;
import org.modelio.platform.ui.dialog.ModelioDialog;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.net.UriConnection;
import org.modelio.vbasic.net.UriConnections;

/**
 * Dialog box used to instantiate a new HTTP fragment descriptor to mount in a project.
 * <p>
 * An HTTP fragment has several configuration points:
 * <ul>
 * <li>A name (mandatory)</li>
 * <li>An URL (mandatory)</li>
 * <li>A user name (optional)</li>
 * <li>A password (optional)</li>
 * </ul>
 * </p>
 * <p>
 * Use {@link DistantLibraryDialog#getFragmentDescriptor()} to get the new fragment.
 * </p>
 */
@objid ("7d4c33c9-3adc-11e2-916e-002564c97630")
public final class DistantLibraryDialog extends ModelioDialog {
    @objid ("180366d4-e1f1-4412-9885-b3026e5f9349")
    private final boolean isLocalFragment;

    @objid ("49950b86-83df-4124-9da7-44227f36373d")
    List<String> invalidIds;

    @objid ("d9a338c7-d8d1-4f07-8f5d-fb1929165e33")
    private final boolean allowProjectAuth;

    @objid ("7d4c33ca-3adc-11e2-916e-002564c97630")
    private GProjectPartDescriptor result;

    @objid ("7d4c33d0-3adc-11e2-916e-002564c97630")
    private ExmlUrlFragmentPanel panel;

    @objid ("60ab2ba2-3ef7-11e2-9bd5-002564c97630")
    private final IGModelFragment editedFragment;

    @objid ("1e4660e6-5ba7-4ad5-95dc-d00e9ac91a1e")
    private Text fragmentIdText;

    @objid ("a337af22-69a8-4e5f-a4bb-ae695e6fb7ec")
    Button addButton;

    @objid ("4cb171be-8afa-428e-a2fd-3b744a246387")
    Button editButton;

    /**
     * initialize the dialog.
     * @param parentShell a SWT shell
     * @param fragment the fragment to edit
     * @param allowProjectAuth Allow the fragment to use the project authentication data
     * @param allFragmentsIds all existing fragments identifiers. used to forbid using them again.
     */
    @objid ("7d4c33d1-3adc-11e2-916e-002564c97630")
    public  DistantLibraryDialog(final Shell parentShell, final IGModelFragment fragment, final boolean allowProjectAuth, final List<String> allFragmentsIds) {
        super(parentShell);
        this.editedFragment = fragment;
        this.isLocalFragment = fragment == null || fragment.getDefinitionScope() == DefinitionScope.LOCAL;
        this.invalidIds = allFragmentsIds;
        this.allowProjectAuth = allowProjectAuth;
        
    }

    @objid ("7d4c33d4-3adc-11e2-916e-002564c97630")
    @Override
    public Control createContentArea(final Composite parent) {
        // fragment data area
        final Composite data = new Composite(parent, SWT.NONE);
        data.setLayout(new GridLayout(2, false));
        data.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        
        // fragment name
        final Label label = new Label(data, SWT.NULL);
        label.setText(AppProjectConfExt.I18N.getString("DistantLibraryDialog.FragmentId")); //$NON-NLS-1$
        
        this.fragmentIdText = new Text(data, SWT.BORDER | SWT.SINGLE);
        this.fragmentIdText.setText(""); //$NON-NLS-1$
        this.fragmentIdText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        this.fragmentIdText.addModifyListener(new ModifyListener() {
        
            @Override
            public void modifyText(final ModifyEvent e) {
                isFragmentIdValid();
            }
        });
        
        // fragment type-specific panel
        this.panel = new ExmlUrlFragmentPanel(data, SWT.NONE, this.allowProjectAuth);
        GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
        gd.horizontalSpan = 2;
        this.panel.setLayoutData(gd);
        
        // fragment type description message
        final Label fragmentDescription = new Label(data, SWT.NONE);
        fragmentDescription.setText(AppProjectConfExt.I18N.getString("DistantLibraryDialog.Description")); //$NON-NLS-1$
        fragmentDescription.setForeground(UIColor.LABEL_TIP_FG);
        gd = new GridData(SWT.FILL, SWT.FILL, true, false);
        gd.horizontalSpan = 2;
        fragmentDescription.setLayoutData(gd);
        return data;
    }

    @objid ("7d4c33da-3adc-11e2-916e-002564c97630")
    @Override
    public void addButtonsInButtonBar(final Composite parent) {
        if (this.editedFragment == null) {
            createButton(parent, Window.CANCEL, IDialogConstants.CANCEL_LABEL, true);
            this.addButton = createButton(parent, Window.OK, AppProjectConfExt.I18N.getString("DistantLibraryDialog.AddFragment"), true); //$NON-NLS-1$
            this.addButton.setEnabled(false);
        } else if (this.isLocalFragment) {
            createButton(parent, Window.CANCEL, IDialogConstants.CANCEL_LABEL, true);
            this.editButton = createButton(parent, Window.OK, AppProjectConfExt.I18N.getString("DistantLibraryDialog.EditFragment"), true); //$NON-NLS-1$
            this.editButton.setEnabled(false);
        } else {
            createButton(parent, Window.CANCEL, IDialogConstants.CLOSE_LABEL, true);
        }
        
    }

    @objid ("7d4e9529-3adc-11e2-916e-002564c97630")
    @Override
    public void init() {
        if (this.editedFragment == null) {
            getShell().setText(AppProjectConfExt.I18N.getString("DistantLibraryDialog.ShellAddTitle")); //$NON-NLS-1$
            setTitle(AppProjectConfExt.I18N.getString("DistantLibraryDialog.AddTitle")); //$NON-NLS-1$
            setMessage(AppProjectConfExt.I18N.getString("DistantLibraryDialog.AddMessage")); //$NON-NLS-1$
        } else {
            getShell().setText(AppProjectConfExt.I18N.getString("DistantLibraryDialog.ShellEditTitle")); //$NON-NLS-1$
            setTitle(AppProjectConfExt.I18N.getString("DistantLibraryDialog.EditTitle")); //$NON-NLS-1$
            setMessage(AppProjectConfExt.I18N.getString("DistantLibraryDialog.EditMessage")); //$NON-NLS-1$
        
            this.fragmentIdText.setText(this.editedFragment.getId());
            this.fragmentIdText.setEnabled(this.editedFragment.getDefinitionScope() == DefinitionScope.LOCAL);
            this.panel.setEdited(this.editedFragment);
        }
        
    }

    @objid ("7d4e9532-3adc-11e2-916e-002564c97630")
    @Override
    protected void okPressed() {
        final GProjectPartDescriptor fragmentDescriptor = new GProjectPartDescriptor(GProjectPartType.HTTPFRAGMENT, this.fragmentIdText.getText(), null, ScopeHelper.getScope(this.fragmentIdText));
        
        try {
            this.panel.updateFragmentModel(fragmentDescriptor);
        } catch (final URISyntaxException e) {
            // Display and log the message, and prevent dialog from closing.
            AppProjectConfExt.LOG.error(e);
            setErrorMessage(e.getLocalizedMessage());
            return;
        }
        this.result = fragmentDescriptor;
        super.okPressed();
        
    }

    /**
     * @return the edited fragment descriptor.
     */
    @objid ("7d4e9535-3adc-11e2-916e-002564c97630")
    public GProjectPartDescriptor getFragmentDescriptor() {
        return this.result;
    }

    @objid ("bf598c5b-d2cc-4984-9df2-8041af99a7e3")
    protected void isFragmentIdValid() {
        final String fragmentId = this.fragmentIdText.getText();
        if (this.invalidIds.contains(fragmentId)) {
            if (this.editedFragment != null) {
                if (fragmentId.equals(this.editedFragment.getId())) {
                    fragmentIdIsValid();
                    return;
                }
            }
        } else if (!fragmentId.isEmpty()) {
            fragmentIdIsValid();
            return;
        }
        fragmentIdIsInvalid(fragmentId);
        
    }

    @objid ("bcbd175f-5580-415b-acae-d774a0e80e3e")
    private void fragmentIdIsValid() {
        setErrorMessage(null);
        if (this.addButton != null) {
            this.addButton.setEnabled(true);
        }
        if (this.editButton != null) {
            this.editButton.setEnabled(true);
        }
        this.fragmentIdText.setForeground(this.fragmentIdText.getDisplay().getSystemColor(SWT.COLOR_DARK_GREEN));
        
    }

    @objid ("2217df42-c3b2-4958-8036-640809a43b54")
    private void fragmentIdIsInvalid(final String fragmentId) {
        if (!fragmentId.isEmpty()) {
            setErrorMessage(AppProjectConfExt.I18N.getMessage("DistantLibraryDialog.ErrorMessage.ExistAlready", fragmentId));
        } else {
            setErrorMessage(null);
        }
        if (this.addButton != null) {
            this.addButton.setEnabled(false);
        }
        if (this.editButton != null) {
            this.editButton.setEnabled(false);
        }
        this.fragmentIdText.setForeground(this.fragmentIdText.getDisplay().getSystemColor(SWT.COLOR_RED));
        
    }

    @objid ("c55eae58-9524-4a16-ba3c-8be949cf9804")
    @Override
    protected String getHelpId() {
        return AppProjectConfExt.I18N.getString("DistantLibraryDialog.HelpId");
    }

    /**
     * HTTP fragment panel.
     */
    @objid ("7d4e9576-3adc-11e2-916e-002564c97630")
    private static class ExmlUrlFragmentPanel extends Composite {
        @objid ("ae9f5b4d-458f-44f4-b6b9-04c2b2bb6842")
        private final boolean allowProjectAuth;

        @objid ("36465423-93e1-4505-8236-61116550c873")
        Text urlText;

        @objid ("eb430d8a-f830-44be-89f9-09cdc4e906d3")
        Text message;

        @objid ("b88e6390-36d1-49d3-bab9-1001ea0b52a5")
        private OptionalAuthPanelProvider authPanel;

        /**
         * Initialize the panel.
         * @param parent the parent composite.
         * @param style the style of widget to construct
         * @param allowProjectAuth Allow the fragment to use the project authentication data.
         */
        @objid ("7d4e957c-3adc-11e2-916e-002564c97630")
        public  ExmlUrlFragmentPanel(final Composite parent, final int style, final boolean allowProjectAuth) {
            super(parent, style);
            this.allowProjectAuth = allowProjectAuth;
            setLayout(new FillLayout());
            createContents(this);
            
        }

        @objid ("7d4e9582-3adc-11e2-916e-002564c97630")
        public void updateFragmentModel(final GProjectPartDescriptor fragmentDescriptor) throws URISyntaxException {
            if (checkUrl()) {
                final URI uri = new URI(this.urlText.getText());
                fragmentDescriptor.setLocation(uri);
                fragmentDescriptor.setProperties(new GProperties());
            
                this.authPanel.updateFragmentDescriptor(fragmentDescriptor);
            } else {
                MessageDialog.openError(null, AppProjectConfExt.I18N.getString("DistantLibraryDialog.InvalidUrl"), this.message.getText()); //$NON-NLS-1$
                throw new URISyntaxException(this.urlText.getMessage(), this.message.getText());
            }
            
        }

        @objid ("7d4e9585-3adc-11e2-916e-002564c97630")
        private void createContents(final Composite parent) {
            final Group data = new Group(parent, 0);
            data.setText(AppProjectConfExt.I18N.getString("DistantLibraryDialog.Panel.title")); //$NON-NLS-1$
            data.setLayout(new GridLayout(2, false));
            
            // URL
            Label label = new Label(data, SWT.NONE);
            label.setText(AppProjectConfExt.I18N.getString("DistantLibraryDialog.Url.label")); //$NON-NLS-1$
            
            this.urlText = new Text(data, SWT.BORDER);
            this.urlText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
            
            // Authentication
            label = new Label(data, SWT.NONE);
            label.setText(AppProjectConfExt.I18N.getString("DistantLibraryDialog.Auth.label")); //$NON-NLS-1$
            GridDataFactory.defaultsFor(label).align(SWT.BEGINNING, SWT.BEGINNING).indent(0, 5).applyTo(label);
            
            this.authPanel = new OptionalAuthPanelProvider(this.allowProjectAuth);
            this.authPanel.createPanel(data);
            this.authPanel.getPanel().setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
            this.authPanel.setInput(null);
            
            // Check button
            final Button button = new Button(data, SWT.PUSH);
            button.setText(AppProjectConfExt.I18N.getString("DistantLibraryDialog.Checkit.label")); //$NON-NLS-1$
            button.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
            
            button.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(final SelectionEvent event) {
                    validUrl();
                }
            });
            
            this.message = new Text(data, SWT.WRAP | SWT.READ_ONLY | SWT.MULTI);
            this.message.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
            this.message.setBackground(data.getBackground());
            
        }

        @objid ("7d50f689-3adc-11e2-916e-002564c97630")
        @SuppressWarnings("nls")
        boolean checkUrl() {
            final String[] toTest = new String[] { "", "admin", "model", ".index" };
            final String baseurl = this.urlText.getText();
            
            boolean available = false;
            try {
                final URI base = URIUtil.fromString(baseurl);
                for (final String sub : toTest) {
                    final URI url = URIUtil.append(base, sub);
                    final UriConnection connection = UriConnections.createConnection(url);
                    connection.setAuthenticationData(this.authPanel.getInput());
            
                    try (InputStream is = connection.getInputStream();) {
                        is.close();
                        this.message.setText(AppProjectConfExt.I18N.getString("DistantLibraryDialog.CheckResult.validRepository"));
                        available = true;
                    }
                }
            } catch (final FileSystemException e) {
                this.message.setText(FileUtils.getLocalizedMessage(e));
            } catch (final FileNotFoundException e) {
                if (e.getMessage().startsWith(baseurl)) {
                    this.message.setText(AppProjectConfExt.I18N.getMessage("DistantLibraryDialog.CheckResult.NotFound", baseurl));
                } else {
                    this.message.setText(e.getLocalizedMessage());
                }
            } catch (IOException | URISyntaxException e) {
                this.message.setText(e.getLocalizedMessage());
            }
            
            getShell().layout(new Control[] { this.message });
            return available;
        }

        @objid ("7d50f68d-3adc-11e2-916e-002564c97630")
        public void setEdited(final IGModelFragment fragment) {
            this.urlText.setText(fragment.getDescriptor().getLocation().toString());
            final AuthDescriptor authConf = fragment.getAuth();
            this.authPanel.setInput(authConf.getData());
            
            if (fragment.getDefinitionScope() == DefinitionScope.SHARED) {
                this.urlText.setEditable(false);
                this.authPanel.setEnabled(authConf.getScope() != DefinitionScope.SHARED);
            }
            
        }

        @objid ("94011d7c-3b7d-4214-8c49-5c05d96374ae")
        void validUrl() {
            final Color redColor = getDisplay().getSystemColor(SWT.COLOR_RED);
            final boolean valid = checkUrl();
            
            final Color color = valid ? getDisplay().getSystemColor(SWT.COLOR_DARK_GREEN)
                    : redColor;
            
            this.urlText.setForeground(color);
            this.message.setForeground(color);
            
        }

    }

}
