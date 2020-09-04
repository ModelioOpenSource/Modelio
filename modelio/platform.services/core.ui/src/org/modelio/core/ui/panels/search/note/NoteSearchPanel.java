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

package org.modelio.core.ui.panels.search.note;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.modelio.core.ui.panels.search.ISearchController;
import org.modelio.core.ui.panels.search.ISearchPanel;
import org.modelio.core.ui.plugin.CoreUi;
import org.modelio.model.search.engine.ISearchCriteria;
import org.modelio.model.search.engine.searchers.note.NoteSearchCriteria;
import org.modelio.ui.CoreFontRegistry;
import org.modelio.vcore.session.api.ICoreSession;

/**
 * Model search criteria panel. It is composed of:
 * <ul>
 * <li>a name pattern field</li>
 * <li>a metaclass selection table</li>
 * <li>a checkbox to include or not the 'ramc' elements</li<
 * <li>a text field to enter a (unique) stereotype name</li>
 * </ul>
 */
@objid ("ecdf6679-4109-4a33-8384-95952139b94c")
public class NoteSearchPanel implements ISearchPanel {
    @objid ("eae38d46-ceb0-4bea-8bc1-3fe01da1c7bc")
    private static final String DEFAULT_NOTE_TYPE = "description";

    @objid ("3237f1c0-0607-4932-95a0-e02bac097ff1")
    private Group topGroup;

    @objid ("66eab421-89d8-44fd-a4b6-bbf9c3974abf")
    private Text textfield = null;

    @objid ("7cddad72-eb49-4660-9c07-23cc5d587701")
    private Button includeRamcCheckBox;

    @objid ("a6cf406f-04ac-4721-88c0-aae880703c39")
    private Button caseSensitiveCheckBox;

    @objid ("9906b9f3-2b40-4160-9d12-c82cd9526928")
    private Text notetypeText;

    @objid ("de6a0ca7-4047-4dd1-81de-7bb67554a213")
    private NoteSearchCriteria searchCriteria;

    @objid ("c7ebacd5-569d-4b58-a6d1-5e3f0eae51d8")
    @Override
    public void initialize(Composite parent, ICoreSession session, ISearchController searchController) {
        this.topGroup = new Group(parent, SWT.NONE);
        this.topGroup.setText(CoreUi.I18N.getString("NoteSearch.CriteriaGroup.label")); //$NON-NLS-1$
        this.topGroup.setFont(CoreFontRegistry.getModifiedFont(topGroup.getFont(), SWT.BOLD, 1.0f));
        
        final GridLayout gridLayout = new GridLayout();
        GridData gridData = null;
        gridLayout.numColumns = 3;
        this.topGroup.setLayout(gridLayout);
        
        // The name pattern field and its label
        final Label nameLabel = new Label(this.topGroup, SWT.NONE);
        nameLabel.setText(CoreUi.I18N.getString("NoteSearch.TextPattern.label")); //$NON-NLS-1$
        this.textfield = new Text(this.topGroup, SWT.SINGLE | SWT.BORDER);
        
        gridData = new GridData(SWT.FILL, SWT.TOP, true, false);
        this.textfield.setLayoutData(gridData);
        
        this.textfield.setToolTipText(CoreUi.I18N.getString("NoteSearch.TextPattern.tooltip"));
        // Prevent CR from going to the default button
        this.textfield.addTraverseListener(new TraverseListener() {
            @Override
            public void keyTraversed(TraverseEvent e) {
                if (e.detail == SWT.TRAVERSE_RETURN) {
                    e.doit = false;
                    e.detail = SWT.TRAVERSE_NONE;
                }
            }
        });
        
        this.textfield.addModifyListener(new ModifyListener() {
            @Override
            public void modifyText(ModifyEvent e) {
                final Text text = (Text) e.getSource();
                if (NoteSearchCriteria.isValidExpression(text.getText())) {
                    text.setForeground(text.getDisplay().getSystemColor(SWT.COLOR_LIST_FOREGROUND));
                } else {
                    text.setForeground(text.getDisplay().getSystemColor(SWT.COLOR_RED));
                }
            }
        });
        
        this.caseSensitiveCheckBox = new Button(this.topGroup, SWT.CHECK);
        this.caseSensitiveCheckBox.setText("");
        this.caseSensitiveCheckBox.setToolTipText(CoreUi.I18N.getString("NoteSearch.TextCase.tooltip"));
        gridData = new GridData(SWT.RIGHT, SWT.CENTER, false, false);
        this.caseSensitiveCheckBox.setLayoutData(gridData);
        
        // The 'note type' criterion
        final Label stereotypeLabel = new Label(this.topGroup, SWT.NONE);
        stereotypeLabel.setText(CoreUi.I18N.getString("NoteSearch.TypePattern.label"));
        
        this.notetypeText = new Text(this.topGroup, SWT.SINGLE | SWT.BORDER);
        gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
        gridData.horizontalSpan = 2;
        this.notetypeText.setLayoutData(gridData);
        this.notetypeText.setToolTipText(CoreUi.I18N.getString("NoteSearch.TypePattern.tooltip"));
        
        // Setup default values for criteria
        final NoteSearchCriteria defaultCriteria = new NoteSearchCriteria();
        defaultCriteria.setExpression(CoreUi.I18N.getString("NoteSearch.TextPattern.default"));
        defaultCriteria.setNoteType(DEFAULT_NOTE_TYPE);
        defaultCriteria.setCaseSensitive(false);
        setCriteria(defaultCriteria);
    }

    @objid ("2be34ff5-c625-4e3e-b324-e2532a865a0c")
    @Override
    public Control getControl() {
        return this.topGroup;
    }

    @objid ("428a0594-16ca-4d40-b79e-c9cb93a03db4")
    @Override
    public ISearchCriteria getCriteria() {
        this.searchCriteria.reset();
        this.searchCriteria.setExpression(this.textfield.getText());
        this.searchCriteria.setNoteType(this.notetypeText.getText().trim());
        this.searchCriteria.setCaseSensitive(this.caseSensitiveCheckBox.getSelection());
        return this.searchCriteria;
    }

    @objid ("15bd7a44-9ca8-4ac0-b22e-3693ce6e4006")
    @Override
    public void setCriteria(ISearchCriteria searchCriteria) {
        assert (searchCriteria instanceof NoteSearchCriteria);
        final NoteSearchCriteria criteria = (NoteSearchCriteria) searchCriteria;
        
        this.searchCriteria = criteria;
        
        this.textfield.setText(criteria.getExpression());
        this.notetypeText.setText(criteria.getNoteType());
        this.caseSensitiveCheckBox.setSelection(this.searchCriteria.isCaseSensitive());
    }

    /**
     * Default constructor
     */
    @objid ("adfc7124-f6c7-4ac1-b570-5c5ed6ef4025")
    public NoteSearchPanel() {
    }

}
