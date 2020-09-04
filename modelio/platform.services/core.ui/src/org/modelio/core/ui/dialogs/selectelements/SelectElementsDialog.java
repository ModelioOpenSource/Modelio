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

package org.modelio.core.ui.dialogs.selectelements;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.modelio.core.ui.panels.selectelements.SelectElementsPanel;
import org.modelio.core.ui.plugin.CoreUi;
import org.modelio.model.search.engine.ISearchCriteria;
import org.modelio.model.search.engine.ISearchEngine;
import org.modelio.ui.dialog.ModelioDialog;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Provides a Modelio dialog used to select p model elements among n candidates.
 * The dialog is based on a {@link SelectElementsPanel}
 * 
 * @author phv
 */
@objid ("88a6a88e-d742-433e-84e6-8783f0097b2d")
public class SelectElementsDialog extends ModelioDialog {
    @objid ("40e78116-43b8-4b46-92c7-ceac53fc8bb4")
    private final String titleKey;

    @objid ("e78fa107-79b1-4883-80d4-4f378a1a6175")
    private final SelectElementsPanel selectElementsPanel;

    @objid ("f3c731aa-aacb-4116-a8e2-56bce70a0311")
    private Composite top;

    @objid ("aa3155f4-28ec-401d-b5d2-3e48ccfe3f21")
    private List<MObject> content;

    /**
     * Create a model element selection dialog that allows to select 'p'
     * elements among a list of 'n' candidates.<br/>
     * The candidates are defined by the session, searcher and searchCriteria
     * parameters. They are searched in the model when the dialog is popped.
     * <br/>
     */
    @objid ("e0f5d128-6590-4e1f-b3b2-61bb74e00dbf")
    public SelectElementsDialog(final Shell parentShell, final ICoreSession session, final ISearchEngine searcher, final ISearchCriteria searchCriteria, final String titleKey, final List<MObject> selection) {
        this(parentShell, session, searcher, searchCriteria, titleKey, selection, true);
    }

    @objid ("ac29ba44-16d5-4f69-a4b8-489d749feece")
    @Override
    public Control createContentArea(final Composite parent) {
        this.top = this.selectElementsPanel.createPanel(parent);
        this.top.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        return this.top;
    }

    @objid ("34326219-3770-465d-bcbf-b7a4faa980c9")
    @Override
    public void addButtonsInButtonBar(final Composite parent) {
        createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
        createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CLOSE_LABEL, false);
    }

    @objid ("7fbb9653-bc6a-45e6-b287-09945c4b9233")
    @Override
    public void init() {
        setLogoImage(null);
        
        getShell().setText(CoreUi.I18N.getString(this.titleKey));
        setTitle(CoreUi.I18N.getString(this.titleKey));
        setMessage(CoreUi.I18N.getString("SelectElementsPanel.MultipleInputHelp"));
    }

    @objid ("f66633bf-1065-4cb6-916a-039da4100239")
    public void setContent(final List<MObject> content) {
        this.content = content;
        this.selectElementsPanel.setInput(this.content);
    }

    @objid ("98a36122-9c08-4cc2-8acc-f20df9b75c30")
    public List<MObject> getContent() {
        return this.content;
    }

    /**
     * Create a model element selection dialog that allows to select 'p'
     * elements among a list of 'n' candidates.<br/>
     * The candidates are defined by the session, searcher and searchCriteria
     * parameters. They are searched in the model when the dialog is popped.
     * <br/>
     * @param parentShell
     * @param session
     * @param searcher
     * @param searchCriteria
     * @param titleKey
     * @param selection
     * @param autorunSearch
     */
    @objid ("167ded70-15f4-4906-8127-aa8bba584616")
    public SelectElementsDialog(final Shell parentShell, final ICoreSession session, final ISearchEngine searcher, final ISearchCriteria searchCriteria, final String titleKey, final List<MObject> selection, final boolean autorunSearch) {
        super(parentShell);
        
        this.titleKey = titleKey;
        this.selectElementsPanel = new SelectElementsPanel(session, searcher, searchCriteria,
                autorunSearch ? SelectElementsPanel.SearchMode.AUTO : SelectElementsPanel.SearchMode.USER);
        setContent(selection);
        
        // Hack the shell style uglily set by ObjingDialog so that
        // the dialog is not modal.
        int style = getShellStyle();
        style &= ~(SWT.PRIMARY_MODAL | SWT.APPLICATION_MODAL | SWT.SYSTEM_MODAL);
        setShellStyle(style);
    }

    @objid ("f61656da-ba76-4266-b57e-c5a3d36c3cca")
    @Override
    protected void okPressed() {
        this.selectElementsPanel.dispose();
        super.okPressed();
    }

    @objid ("c230dd65-0914-4b38-9261-5b226126ce5f")
    @Override
    protected void cancelPressed() {
        this.selectElementsPanel.dispose();
        super.cancelPressed();
    }

}
